# 🎓 OOPS Console Projects

> A collection of console-based Java projects built to practice and demonstrate **Object-Oriented Programming** concepts.

## 📋 Table of Contents

- [About](#about)
- [Projects](#projects)
  - [1. ATM Machine](#1-atm-machine)
  - [2. Library Management System](#2-library-management-system)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [How to Run](#how-to-run)
- [Tech Stack](#tech-stack)

---

## About

These projects were developed as part of my **OOP learning journey**. Each project simulates a real-world system using core Java and fundamental OOP principles — classes, objects, encapsulation, abstraction, static members, and composition.

All projects are **menu-driven console applications** with role-based access (Admin / User).

---

## Projects

### 1. ATM Machine

**Source:** [`ATMmachine.java`](ATMmachine.java)

A console-based ATM simulation with Admin and User roles.

#### Features

| Feature            | Description                                        |
| ------------------ | -------------------------------------------------- |
| Role-Based Login   | Separate flows for Admin and User                  |
| Deposit            | Add money (multiples of 100 / 200 / 500)           |
| Withdrawal         | Withdraw with ATM & account balance validation     |
| Balance Enquiry    | View current account balance                       |
| PIN Change         | Update account PIN                                 |
| Money Transfer     | Transfer funds between user accounts               |
| Mini Statement     | View the most recent transaction via `Stack`        |

#### Class Structure

```
User
├── acc_no    : int
├── pin       : int
├── balance   : int
└── transactions : Stack<String>

Admin
├── atm_balance : static int  (shared ATM cash pool)
└── ad_pin      : static int

ATMmachine
├── users[]     : User[10]
├── main()
├── userops()
├── adminops()
├── getDeposit()
├── Withdrawal()
├── getBalance()
├── changePIN()
├── moneyTransfer()
└── miniStatement()
```

#### Default Credentials

**Admin**

| Field | Value      |
| ----- | ---------- |
| PIN   | `19892006` |

**Users** (10 pre-loaded accounts)

| Account No. | PIN    | Balance |
| ----------- | ------ | ------- |
| 1           | `1234` | ₹5,000  |
| 2           | `5678` | ₹3,000  |
| 3           | `9012` | ₹7,000  |
| 4           | `3456` | ₹2,000  |
| 5           | `7890` | ₹6,000  |
| 6           | `4321` | ₹4,000  |
| 7           | `8765` | ₹8,000  |
| 8           | `6543` | ₹1,000  |
| 9           | `2109` | ₹9,000  |
| 10          | `1098` | ₹1,500  |

#### Menu Flow

```
Main Menu
├── 1. Admin Login
│   ├── 1. Deposit to ATM
│   └── 2. Check ATM Balance
├── 2. User Login
│   ├── 1. Deposit
│   ├── 2. Withdrawal
│   ├── 3. Balance Enquiry
│   ├── 4. Change PIN
│   ├── 5. Money Transfer
│   └── 6. Mini Statement
└── 3. Exit
```

---

### 2. Library Management System

**Source:** [`Library.java`](Library.java)

A console-based library system where Admins manage books and Students can borrow and return them.

#### Features

| Feature            | Description                                        |
| ------------------ | -------------------------------------------------- |
| Admin Login        | PIN-based authentication                           |
| Add Book           | Add a new book or update quantity of existing book  |
| Remove Book        | Remove a book by ID                                |
| View Books         | Display all books with ID, name, author, and qty   |
| Student Login      | ID + PIN authentication                            |
| Borrow Book        | Borrow a book (decrements qty, prevents duplicates)|
| Return Book        | Return a borrowed book (increments qty)            |
| My Borrowed Books  | View list of books currently borrowed               |

#### Class Structure

```
Book
├── id     : int
├── name   : String
├── author : String
└── qty    : int

Student
├── id       : int
├── pin      : int
└── borrowed : ArrayList<Integer>

Admin
└── pin : static int

Library
├── books[]    : Book[5]
├── students[] : Student[5]
├── main()
├── adminLogin()
├── studentLogin()
├── addBook()
├── removeBook()
├── viewBooks()
├── borrowBook()
├── returnBook()
└── myBooks()
```

#### Default Credentials

**Admin**

| Field | Value  |
| ----- | ------ |
| PIN   | `1234` |

**Students**

| Student ID | PIN    |
| ---------- | ------ |
| 1          | `1111` |
| 2          | `2222` |
| 3          | `3333` |
| 4          | `4444` |
| 5          | `5555` |

#### Pre-loaded Books

| ID | Title          | Author         | Qty |
| -- | -------------- | -------------- | --- |
| 1  | Java           | James Gosling  | 3   |
| 2  | Python         | Guido          | 2   |
| 3  | C Programming  | Dennis Ritchie | 4   |
| 4  | DBMS           | Korth          | 1   |
| 5  | DSA            | Karumanchi     | 2   |

#### Menu Flow

```
Main Menu
├── 1. Admin Login
│   ├── 1. Add Book
│   ├── 2. Remove Book
│   └── 3. View Books
├── 2. Student Login
│   ├── 1. View Books
│   ├── 2. Borrow Book
│   ├── 3. Return Book
│   └── 4. My Borrowed Books
└── 3. Exit
```

---

## OOP Concepts Demonstrated

| Concept              | Where Used                                                                 |
| -------------------- | -------------------------------------------------------------------------- |
| **Class & Object**   | `User`, `Admin`, `Book`, `Student`, `Library`, `ATMmachine`                |
| **Encapsulation**    | Data and behaviour grouped into meaningful classes                         |
| **Abstraction**      | Separate methods for each operation (`getDeposit`, `borrowBook`, etc.)     |
| **Static Members**   | Shared state — `atm_balance`, admin PINs, arrays, `Scanner`               |
| **Composition**      | `Student` contains `ArrayList<Integer>` for borrowed books; `User` uses `Stack<String>` |

---

## How to Run

> **Prerequisite:** JDK 8 or higher installed and `javac` / `java` available in your PATH.

### ATM Machine

```bash
javac ATMmachine.java
java ATMmachine
```

### Library Management System

```bash
javac Library.java
java Library
```

---

## Tech Stack

| Tool     | Version       |
| -------- | ------------- |
| Language | Java (JDK 8+) |
| IDE      | Any / Terminal |
| VCS      | Git            |

---

<p align="center">
  <i>Built for learning — feedback and contributions welcome! ⭐</i>
</p>
