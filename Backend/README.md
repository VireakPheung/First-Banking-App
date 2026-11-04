# Banking App API

A small REST API built with Node.js and Express. It stores data in a JSON file (`db.json`) instead of a database.

## Run it

```bash
npm install
npm run seed     # creates db.json with sample data
npm start        # http://localhost:3000
```

Sample login: `sokha@example.com` / `password123`

## Money format

- USD is stored in **cents** (`1248050` = $12,480.50)
- KHR is stored in **riel** (`25600000` = ៛25,600,000)
- The app is responsible for formatting these numbers for display.

## Endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | /auth/register | no | Create a user, returns token |
| POST | /auth/login | no | Returns token and user |
| GET | /users/me | yes | Logged-in user |
| GET | /accounts | yes | All accounts (`?currency=USD` or `KHR`) |
| GET | /accounts/summary | yes | Total balance, calculated |
| GET | /accounts/default | yes | Default account for the Home card |
| GET | /scheduled-payments | yes | Upcoming payments, sorted by date |
| GET | /favorites | yes | Saved recipients |
| GET | /promotions | no | Promotion banners |

Send the token as a header: `Authorization: Bearer <token>`

## Screens

- **Accounts screen:** `/accounts`, `/accounts/summary`
- **Home screen:** `/users/me`, `/accounts/default`, `/scheduled-payments`, `/favorites`, `/promotions`
