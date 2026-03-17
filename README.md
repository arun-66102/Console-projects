# OOPS Learning Projects

I have developed these projects as part of my learning period in Object-Oriented Programming (OOPS).

## Projects

- ATM Machine (`ATMmachine.java`)

## Project Explanation

### ATM Machine

This is a console-based ATM simulation developed in Java. It supports two roles:

- `Admin`: Can log in with admin PIN, deposit money to ATM storage, and check total ATM balance.
- `User`: Can log in with account number and PIN to perform banking operations.

### Key Features

- Role-based login for Admin and User
- Deposit and withdrawal with amount validation
- Balance enquiry
- PIN change
- Money transfer between users
- Mini statement using transaction history

### OOPS Concepts Used

- `Class and Object`: `User` and `Admin` classes model real-world entities.
- `Encapsulation (basic)`: User/account related data and operations are grouped in program structure.
- `Abstraction`: Separate methods are used for each ATM operation (`getDeposit`, `Withdrawal`, `moneyTransfer`, etc.).
- `Static Members`: Shared ATM-level data such as `atm_balance` and admin PIN are managed with static fields.
