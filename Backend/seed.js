// Run once with: npm run seed
// Creates db.json with sample data. Running it again resets everything.
const fs = require("fs");
const bcrypt = require("bcryptjs");

// Money rule: USD is stored in cents (1248050 = $12,480.50)
//             KHR is stored in riel  (25600000 = ៛25,600,000)
const db = {
  users: [
    {
      id: 1,
      name: "Sokha Kim",
      email: "sokha@example.com",
      passwordHash: bcrypt.hashSync("password123", 10),
    },
  ],
  accounts: [
    { id: 1, userId: 1, type: "savings", currency: "USD", number: "4821", balance: 1248050, isDefault: true },
    { id: 2, userId: 1, type: "current", currency: "USD", number: "7305", balance: 321500, isDefault: false },
    { id: 3, userId: 1, type: "savings", currency: "KHR", number: "1190", balance: 25600000, isDefault: false },
    { id: 4, userId: 1, type: "current", currency: "KHR", number: "6642", balance: 4180000, isDefault: false },
  ],
  scheduledPayments: [
    { id: 1, userId: 1, title: "Rent", kind: "Standing order", currency: "USD", amount: 35000, date: "2026-10-20" },
    { id: 2, userId: 1, title: "Electricity bill", kind: "Auto pay", currency: "KHR", amount: 186500, date: "2026-10-25" },
    { id: 3, userId: 1, title: "Loan repayment", kind: "Due", currency: "USD", amount: 20000, date: "2026-11-01" },
  ],
  favorites: [
    { id: 1, userId: 1, name: "Mom", initials: "MK", color: "blue" },
    { id: 2, userId: 1, name: "Sophea", initials: "SP", color: "yellow" },
    { id: 3, userId: 1, name: "Landlord", initials: "LD", color: "green" },
    { id: 4, userId: 1, name: "Electric", initials: "EC", color: "purple" },
  ],
  promotions: [
    { id: 1, title: "0% fee transfers", titleKm: "ផ្ទេរមិនគិតកម្រៃ", validUntil: "2026-10-31", color: "blue" },
    { id: 2, title: "Earn 4.5% savings", titleKm: "ការប្រាក់សន្សំ", validUntil: "2026-12-31", color: "yellow" },
  ],
};

fs.writeFileSync("db.json", JSON.stringify(db, null, 2));
console.log("db.json created. Login: sokha@example.com / password123");
