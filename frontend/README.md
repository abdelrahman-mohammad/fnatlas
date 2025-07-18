# FNAtlas Frontend

A React-based frontend for the FNAtlas Fortnite Creative Analytics Dashboard.

## Getting Started

### 1. Install Dependencies

```bash
cd frontend
npm install
```

### 2. Start Development Server

```bash
npm run dev
```

The frontend will run on `http://localhost:5173`

## Project Structure

```
frontend/src/
├── pages/
│   ├── LoginPage.jsx      # Login/Register page
│   └── ProfilePage.jsx    # User profile page
├── services/
│   └── api.js            # API service layer
├── App.jsx               # Main app with routing
└── main.jsx              # Entry point
```

## Testing the Application

### 1. Login/Register Flow

1. **Visit:** `http://localhost:5173`
2. **Register a new user:**

   - Click "Register" tab
   - Enter username, email, password
   - Click "Register" button
   - Should see "Registration successful!" message

3. **Login with your account:**
   - Click "Login" tab
   - Enter your username and password
   - Click "Login" button
   - Should automatically redirect to profile page

### 2. Profile Page

After successful login, you should see:

- ~~Your username and email displayed~~
- ~~A red "Logout" button~~
