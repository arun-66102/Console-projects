import java.util.*;

// User Parameters
class User{
    int acc_no;
    int pin;
    int balance;
    Stack<String> transactions;
    User(int acc_no, int pin, int balance){
        this.acc_no = acc_no;
        this.pin = pin;
        this.balance = balance;
        transactions = new Stack<>();
    }
}

// Admin Parameters
class Admin{
    static int atm_balance = 10000;
    static int ad_pin = 19892006;
}

public class ATMmachine{
    static User[] users = new User[10];

    static {
        users[0] = new User(1, 1234, 5000);
        users[1] = new User(2, 5678, 3000);
        users[2] = new User(3, 9012, 7000);
        users[3] = new User(4, 3456, 2000);
        users[4] = new User(5, 7890, 6000);
        users[5] = new User(6, 4321, 4000);
        users[6] = new User(7, 8765, 8000);
        users[7] = new User(8, 6543, 1000);
        users[8] = new User(9, 2109, 9000);
        users[9] = new User(10, 1098, 1500);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){

            // ATM user verification
            System.out.println("Welcome User!!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice(1/2/3): ");
            int ch = sc.nextInt();

            // Role-Based Access Control
            if(ch == 1){
                adminops();
            }else if(ch == 2){
                userops();
            }else if(ch == 3){
                break;
            }else{
                System.out.println("Invalid Choice. Try Again!!");
            }
    }
        System.out.println("Thank You!!");
    }

    static void userops(){
        Scanner sc = new Scanner(System.in);

        // Getting Input
        System.out.print("Enter your ACCOUNT NUMBER to continue: ");
        int tacc_no = sc.nextInt();
        if(tacc_no < 1 || tacc_no > 10){
            System.out.println("Invalid Credentials...");
            return;
        }
        System.out.print("Enter your PIN to continue: ");
        int tpin = sc.nextInt();

        // PIN Validation
        if(users[tacc_no-1].pin == tpin){
            System.out.println("Welcome User!!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Change PIN");
            System.out.println("5. Money Transfer");
            System.out.println("6. Mini Statement");
            System.out.print("Enter your choice(1/2/3/4/5/6): ");
            int choice = sc.nextInt();

            // Choice Based working
            switch (choice) {
                case 1:
                    getDeposit(tacc_no, users);
                    break;
                case 2:
                    Withdrawal(tacc_no, users);
                    break;
                case 3:
                    getBalance(tacc_no, users);
                    break;
                case 4:
                    changePIN(tacc_no, users);
                    break;
                case 5:
                    moneyTransfer(tacc_no, users);
                    break;
                case 6:
                    miniStatement(tacc_no, users);
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again!!");
                    break;
            }
        }else{
            System.out.println("Invalid PIN. Try again!!");
        }
    }

    static void adminops(){
        Scanner sc = new Scanner(System.in);

        // PIN Input
        System.out.print("Enter your PIN to continue: ");
        int temp = sc.nextInt();

        // PIN Validation
        if(temp == Admin.ad_pin){
            System.out.println("Welcome Admin!!");
            System.out.println("1. Deposit");
            System.out.println("2. Balance check");
            System.out.print("Enter your choice(1/2): ");
            int ch = sc.nextInt();

            // Choice Based Working
            if(ch == 1){
                System.out.print("Enter the amount to be deposited: ");
                int deps = sc.nextInt();
                Admin.atm_balance += deps;
                System.out.println("Current Balance is "+Admin.atm_balance);
            }else if(ch == 2){
                System.out.println("Your ATM machine balance is "+Admin.atm_balance);
            }else{
                System.out.println("Invalid Choice. Try Again!!");
            }

        }else{
            System.out.println("Invalid PIN. Try again!!");
        }
    }

    // Deposit
    static void getDeposit(int acc, User[] users){
        Scanner sc = new Scanner(System.in);
        int deps;
        while(true){
            System.out.print("Enter the amount to be deposited: ");
            deps = sc.nextInt();
            boolean d100 = (deps%100 == 0);
            boolean d200 = (deps%200 == 0);
            boolean d500 = (deps%500 == 0);

            // Deposit Validation
            if(d100 || d200 || d500){
                break;
            }

            System.out.println("Amount Should be in multiples of 100, 200 and 500...");
        }
        Admin.atm_balance += deps;
        users[acc-1].balance += deps;
        users[acc-1].transactions.push("Deposit "+deps);
        System.out.println("Current Balance is "+users[acc-1].balance);
    }

    // Withdrawal
    static void Withdrawal(int acc, User[] users){
        Scanner sc = new Scanner(System.in);
        int with;
        while(true){
            System.out.print("Enter the amount to be Withdrawn: ");
            with = sc.nextInt();
            if(with > Admin.atm_balance){
                System.out.println("Insufficient ATM Balance...");
                continue;
            }else if(with > users[acc-1].balance){
                System.out.println("Insufficient Account Balance...");
                continue;
            }

            boolean d100 = (with%100 == 0);
            boolean d200 = (with%200 == 0);
            boolean d500 = (with%500 == 0);
            if(d100 || d200 || d500){
                break;
            }
            System.out.println("Amount Should be in multiples of 100, 200 and 500...");
        }
        Admin.atm_balance -= with;
        users[acc-1].balance -= with;
        users[acc-1].transactions.push("Withdrawal "+with);
        System.out.println("Current Balance is "+users[acc-1].balance);
    }

    // Balance Enquiry
    static void getBalance(int acc, User[] users){
        System.out.println("Your Account Balance is "+users[acc-1].balance);
    }

    // Change PIN
    static void changePIN(int acc, User[] users){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your new PIN: ");
        int npin = sc.nextInt();
        users[acc-1].pin = npin;
        System.out.println("PIN Changed Successfully...");
    }

    // Money Transfer
    static void moneyTransfer(int acc, User[] users){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ACCOUNT NUMBER(Reciever): ");
        int racc = sc.nextInt();

        // Account Verification
        if(racc < 1 || racc > 10){
            System.out.println("Invalid Credentials...");
            return;
        }else if(racc == acc){
            System.out.println("Cannot transfer to same account...");
            return;
        }else{
            System.out.print("Enter the Amount to be Transferred: ");
            int trans = sc.nextInt();
            if(trans <= 0){
                System.out.println("Invalid transfer amount...");
                return;
            }else if(trans > users[acc-1].balance){
                System.out.println("Insufficient Account Balance...");
                return;
            }
            users[acc-1].balance -= trans;
            users[racc-1].balance += trans;
            users[acc-1].transactions.push("Transferred to "+racc+" "+trans);
            users[racc-1].transactions.push("Transferred from "+acc+" "+trans);
            System.out.println("Successfully Transferred...");
        }
    }

    // Mini Statement
    static void miniStatement(int acc, User[] users){
        System.out.println("MINI STATEMENT");
        System.out.println("ACCOUNT NUMBER: "+acc);
        System.out.println("DETAILS OF RECENT TRANSACTIONS...");
        if(users[acc-1].transactions.isEmpty()){
            System.out.println("No Recent Transactions...");
        }else{
            System.out.println(users[acc-1].transactions.peek());
        }
        System.out.println("YOUR ACCOUNT BALANCE IS "+users[acc-1].balance);
    }
}