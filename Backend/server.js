const express = require("express");
const cors = require("cors");
const fs = require("fs");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");

const app = express();
app.use(cors());
app.use(express.json());

const PORT = process.env.PORT || 3000;
const JWT_SECRET = process.env.JWT_SECRET || "change-this-secret-in-production";
const DB_FILE = "db.json";
const KHR_PER_USD = 4000; // sample exchange rate

// ---------- helpers ----------
function readDb() {
  return JSON.parse(fs.readFileSync(DB_FILE, "utf8"));
}

function writeDb(db) {
  fs.writeFileSync(DB_FILE, JSON.stringify(db, null, 2));
}

function nextId(list) {
  return list.length ? Math.max(...list.map((item) => item.id)) + 1 : 1;
}

function publicUser(user) {
  // never send the password hash to the app
  const { passwordHash, ...safe } = user;
  return safe;
}

function makeToken(user) {
  return jwt.sign({ userId: user.id }, JWT_SECRET, { expiresIn: "7d" });
}

// ---------- auth middleware ----------
function auth(req, res, next) {
  const header = req.headers.authorization || "";
  const token = header.startsWith("Bearer ") ? header.slice(7) : null;
  if (!token) return res.status(401).json({ error: "Missing token" });

  try {
    req.userId = jwt.verify(token, JWT_SECRET).userId;
    next();
  } catch {
    res.status(401).json({ error: "Invalid or expired token" });
  }
}

// ---------- auth routes ----------
app.post("/auth/register", (req, res) => {
  const { name, email, password } = req.body;
  if (!name || !email || !password) {
    return res.status(400).json({ error: "name, email and password are required" });
  }

  const db = readDb();
  if (db.users.some((u) => u.email.toLowerCase() === email.toLowerCase())) {
    return res.status(409).json({ error: "Email already registered" });
  }

  const user = {
    id: nextId(db.users),
    name,
    email,
    passwordHash: bcrypt.hashSync(password, 10),
  };
    db.users.push(user);

  // starter accounts for every new user (demo money for the portfolio)
  const firstId = nextId(db.accounts);
  db.accounts.push(
    { id: firstId, userId: user.id, type: "savings", currency: "USD",
      number: String(1000 + Math.floor(Math.random() * 9000)),
      balance: 100000, isDefault: true },   // $1,000.00
    { id: firstId + 1, userId: user.id, type: "savings", currency: "KHR",
      number: String(1000 + Math.floor(Math.random() * 9000)),
      balance: 4000000, isDefault: false }  // 4,000,000 riel
  );

  writeDb(db);

  res.status(201).json({ token: makeToken(user), user: publicUser(user) });
});

app.post("/auth/login", (req, res) => {
  const { email, password } = req.body;
  const db = readDb();
  const user = db.users.find((u) => u.email.toLowerCase() === (email || "").toLowerCase());

  if (!user || !bcrypt.compareSync(password || "", user.passwordHash)) {
    return res.status(401).json({ error: "Wrong email or password" });
  }
  res.json({ token: makeToken(user), user: publicUser(user) });
});

// ---------- user ----------
app.get("/users/me", auth, (req, res) => {
  const user = readDb().users.find((u) => u.id === req.userId);
  if (!user) return res.status(404).json({ error: "User not found" });
  res.json(publicUser(user));
});

// ---------- accounts ----------
// GET /accounts            -> all accounts of the logged-in user
// GET /accounts?currency=USD -> only USD (the filter chips)
app.get("/accounts", auth, (req, res) => {
  let accounts = readDb().accounts.filter((a) => a.userId === req.userId);
  if (req.query.currency) {
    accounts = accounts.filter((a) => a.currency === req.query.currency.toUpperCase());
  }
  res.json(accounts);
});

// The "Total balance" card. It is calculated, not stored.
app.get("/accounts/summary", auth, (req, res) => {
  const accounts = readDb().accounts.filter((a) => a.userId === req.userId);

  const usdCents = accounts
    .filter((a) => a.currency === "USD")
    .reduce((sum, a) => sum + a.balance, 0);
  const khr = accounts
    .filter((a) => a.currency === "KHR")
    .reduce((sum, a) => sum + a.balance, 0);

  // convert riel to USD cents: riel / rate * 100
  const khrAsUsdCents = Math.round((khr / KHR_PER_USD) * 100);

  res.json({
    totalUsdCents: usdCents + khrAsUsdCents,
    usdCents,
    khr,
    rate: KHR_PER_USD,
  });
});

// The big card on the Home screen
app.get("/accounts/default", auth, (req, res) => {
  const accounts = readDb().accounts.filter((a) => a.userId === req.userId);
  const account = accounts.find((a) => a.isDefault) || accounts[0];
  if (!account) return res.status(404).json({ error: "No accounts" });
  res.json(account);
});

// ---------- home screen lists ----------
app.get("/scheduled-payments", auth, (req, res) => {
  const list = readDb()
    .scheduledPayments.filter((p) => p.userId === req.userId)
    .sort((a, b) => a.date.localeCompare(b.date));
  res.json(list);
});

app.get("/favorites", auth, (req, res) => {
  res.json(readDb().favorites.filter((f) => f.userId === req.userId));
});

// promotions are the same for everyone, so no login needed
app.get("/promotions", (req, res) => {
  res.json(readDb().promotions);
});

// ---------- 404 + start ----------
app.use((req, res) => res.status(404).json({ error: "Route not found" }));

app.listen(PORT, () => {
  console.log(`API running at http://localhost:${PORT}`);
});
