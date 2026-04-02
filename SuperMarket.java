import java.util.*;

class Product {
    int id;
    String name;
    String brand;
    int qty;

    Product(int id, String name, String brand, int qty) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.qty = qty;
    }
}

class Customer {
    int id;
    int pin;
    ArrayList<Integer> purchased;

    Customer(int id, int pin) {
        this.id = id;
        this.pin = pin;
        purchased = new ArrayList<>();
    }
}

class Admin {
    static int pin = 1234;
}

public class SuperMarket {

    static Scanner sc = new Scanner(System.in);

    static Product[] products = new Product[5];
    static Customer[] customers = new Customer[5];

    static {
        products[0] = new Product(1, "Rice Bag", "Aashirvaad", 10);
        products[1] = new Product(2, "Milk Packet", "Aavin", 15);
        products[2] = new Product(3, "Shampoo", "Clinic Plus", 8);
        products[3] = new Product(4, "Biscuits", "Britannia", 20);
        products[4] = new Product(5, "Detergent", "Surf Excel", 6);

        customers[0] = new Customer(1, 1111);
        customers[1] = new Customer(2, 2222);
        customers[2] = new Customer(3, 3333);
        customers[3] = new Customer(4, 4444);
        customers[4] = new Customer(5, 5555);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== SUPERMARKET MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                adminLogin();
            } else if (ch == 2) {
                customerLogin();
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

        System.out.println("\n1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. View Products");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                addProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                viewProducts();
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void customerLogin() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        if (id < 1 || id > 5) {
            System.out.println("Invalid Customer ID...");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (customers[id - 1].pin != pin) {
            System.out.println("Invalid PIN...");
            return;
        }

        System.out.println("\n1. View Products");
        System.out.println("2. Buy Product");
        System.out.println("3. Return Product");
        System.out.println("4. My Purchased Products");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                viewProducts();
                break;
            case 2:
                buyProduct(id);
                break;
            case 3:
                returnProduct(id);
                break;
            case 4:
                myProducts(id);
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Product p : products) {
            if (p != null && p.id == id) {
                System.out.print("Product already exists. Add quantity: ");
                int q = sc.nextInt();
                p.qty += q;
                System.out.println("Quantity Updated...");
                return;
            }
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Brand Name: ");
        String brand = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = new Product(id, name, brand, qty);
                System.out.println("Product Added Successfully...");
                return;
            }
        }

        System.out.println("Inventory Full...");
    }

    static void removeProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].id == id) {
                products[i] = null;
                System.out.println("Product Removed...");
                return;
            }
        }

        System.out.println("Product Not Found...");
    }

    static void viewProducts() {
        System.out.println("\nPRODUCT ID\tNAME\t\tBRAND\t\tQTY");

        for (Product p : products) {
            if (p != null) {
                System.out.println(p.id + "\t\t" + p.name + "\t\t" + p.brand + "\t\t" + p.qty);
            }
        }
    }

    static void buyProduct(int cid) {
        System.out.print("Enter Product ID to Buy: ");
        int id = sc.nextInt();

        for (Product p : products) {
            if (p != null && p.id == id) {

                if (p.qty <= 0) {
                    System.out.println("Product Out Of Stock...");
                    return;
                }

                if (customers[cid - 1].purchased.contains(id)) {
                    System.out.println("Already Purchased...");
                    return;
                }

                p.qty--;
                customers[cid - 1].purchased.add(id);
                System.out.println("Product Purchased Successfully...");
                return;
            }
        }

        System.out.println("Product Not Found...");
    }

    static void returnProduct(int cid) {
        System.out.print("Enter Product ID to Return: ");
        int id = sc.nextInt();

        if (!customers[cid - 1].purchased.contains(id)) {
            System.out.println("You did not purchase this product...");
            return;
        }

        for (Product p : products) {
            if (p != null && p.id == id) {
                p.qty++;
                customers[cid - 1].purchased.remove(Integer.valueOf(id));
                System.out.println("Product Returned Successfully...");
                return;
            }
        }
    }

    static void myProducts(int cid) {
        ArrayList<Integer> list = customers[cid - 1].purchased;

        if (list.isEmpty()) {
            System.out.println("No Purchased Products...");
            return;
        }

        System.out.println("Your Purchased Products:");

        for (int id : list) {
            for (Product p : products) {
                if (p != null && p.id == id) {
                    System.out.println(p.id + " - " + p.name);
                }
            }
        }
    }
}
