import java.util.*;

class Book {
    int id;
    String name;
    String author;
    int qty;

    Book(int id, String name, String author, int qty) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.qty = qty;
    }
}

class Student {
    int id;
    int pin;
    ArrayList<Integer> borrowed;

    Student(int id, int pin) {
        this.id = id;
        this.pin = pin;
        borrowed = new ArrayList<>();
    }
}

class Admin {
    static int pin = 1234;
}

public class Library {

    static Scanner sc = new Scanner(System.in);

    static Book[] books = new Book[5];
    static Student[] students = new Student[5];

    static {
        books[0] = new Book(1, "Java", "James Gosling", 3);
        books[1] = new Book(2, "Python", "Guido", 2);
        books[2] = new Book(3, "C Programming", "Dennis Ritchie", 4);
        books[3] = new Book(4, "DBMS", "Korth", 1);
        books[4] = new Book(5, "DSA", "Karumanchi", 2);

        students[0] = new Student(1, 1111);
        students[1] = new Student(2, 2222);
        students[2] = new Student(3, 3333);
        students[3] = new Student(4, 4444);
        students[4] = new Student(5, 5555);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                adminLogin();
            } else if (ch == 2) {
                studentLogin();
            } else if (ch == 3) {
                System.out.println("Thank You...");
                break;
            } else {
                System.out.println("Invalid Choice...");
            }
        }
    }

    static void adminLogin() {
        System.out.print("Enter Admin PIN: ");
        int pin = sc.nextInt();

        if (pin != Admin.pin) {
            System.out.println("Invalid PIN...");
            return;
        }

        System.out.println("\n1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View Books");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                viewBooks();
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void studentLogin() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        if (id < 1 || id > 5) {
            System.out.println("Invalid Student ID...");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (students[id - 1].pin != pin) {
            System.out.println("Invalid PIN...");
            return;
        }

        System.out.println("\n1. View Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. My Borrowed Books");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                viewBooks();
                break;
            case 2:
                borrowBook(id);
                break;
            case 3:
                returnBook(id);
                break;
            case 4:
                myBooks(id);
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Book b : books) {
            if (b != null && b.id == id) {
                System.out.print("Book already exists. Add quantity: ");
                int q = sc.nextInt();
                b.qty += q;
                System.out.println("Quantity Updated...");
                return;
            }
        }

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = new Book(id, name, author, qty);
                System.out.println("Book Added Successfully...");
                return;
            }
        }

        System.out.println("Library Full...");
    }

    static void removeBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].id == id) {
                books[i] = null;
                System.out.println("Book Removed...");
                return;
            }
        }

        System.out.println("Book Not Found...");
    }

    static void viewBooks() {
        System.out.println("\nBOOK ID\tNAME\t\tAUTHOR\t\tQTY");

        for (Book b : books) {
            if (b != null) {
                System.out.println(b.id + "\t\t" + b.name + "\t\t" + b.author + "\t\t" + b.qty);
            }
        }
    }

    static void borrowBook(int sid) {
        System.out.print("Enter Book ID to Borrow: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b != null && b.id == id) {

                if (b.qty <= 0) {
                    System.out.println("Book Not Available...");
                    return;
                }

                if (students[sid - 1].borrowed.contains(id)) {
                    System.out.println("Already Borrowed...");
                    return;
                }

                b.qty--;
                students[sid - 1].borrowed.add(id);
                System.out.println("Book Borrowed Successfully...");
                return;
            }
        }

        System.out.println("Book Not Found...");
    }

    static void returnBook(int sid) {
        System.out.print("Enter Book ID to Return: ");
        int id = sc.nextInt();

        if (!students[sid - 1].borrowed.contains(id)) {
            System.out.println("You did not borrow this book...");
            return;
        }

        for (Book b : books) {
            if (b != null && b.id == id) {
                b.qty++;
                students[sid - 1].borrowed.remove(Integer.valueOf(id));
                System.out.println("Book Returned Successfully...");
                return;
            }
        }
    }

    static void myBooks(int sid) {
        ArrayList<Integer> list = students[sid - 1].borrowed;

        if (list.isEmpty()) {
            System.out.println("No Borrowed Books...");
            return;
        }

        System.out.println("Your Borrowed Books:");

        for (int id : list) {
            for (Book b : books) {
                if (b != null && b.id == id) {
                    System.out.println(b.id + " - " + b.name);
                }
            }
        }
    }
}