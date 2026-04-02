import java.util.*;

class Vehicle {
    int id;
    String name;
    String type;
    int qty;

    Vehicle(int id, String name, String type, int qty) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.qty = qty;
    }
}

class Renter {
    int id;
    int pin;
    ArrayList<Integer> rented;

    Renter(int id, int pin) {
        this.id = id;
        this.pin = pin;
        rented = new ArrayList<>();
    }
}

class AdminVehicle {
    static int pin = 1234;
}

public class VehicleRental {

    static Scanner sc = new Scanner(System.in);

    static Vehicle[] vehicles = new Vehicle[5];
    static Renter[] renters = new Renter[5];

    static {
        vehicles[0] = new Vehicle(1, "Activa", "Scooter", 4);
        vehicles[1] = new Vehicle(2, "Pulsar", "Bike", 3);
        vehicles[2] = new Vehicle(3, "Swift", "Car", 2);
        vehicles[3] = new Vehicle(4, "Innova", "Car", 1);
        vehicles[4] = new Vehicle(5, "Access 125", "Scooter", 2);

        renters[0] = new Renter(1, 1111);
        renters[1] = new Renter(2, 2222);
        renters[2] = new Renter(3, 3333);
        renters[3] = new Renter(4, 4444);
        renters[4] = new Renter(5, 5555);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== VEHICLE RENTAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Renter Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                adminLogin();
            } else if (ch == 2) {
                renterLogin();
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

        if (pin != AdminVehicle.pin) {
            System.out.println("Invalid PIN...");
            return;
        }

        System.out.println("\n1. Add Vehicle");
        System.out.println("2. Remove Vehicle");
        System.out.println("3. View Vehicles");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                addVehicle();
                break;
            case 2:
                removeVehicle();
                break;
            case 3:
                viewVehicles();
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void renterLogin() {
        System.out.print("Enter Renter ID: ");
        int id = sc.nextInt();

        if (id < 1 || id > 5) {
            System.out.println("Invalid Renter ID...");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (renters[id - 1].pin != pin) {
            System.out.println("Invalid PIN...");
            return;
        }

        System.out.println("\n1. View Vehicles");
        System.out.println("2. Rent Vehicle");
        System.out.println("3. Return Vehicle");
        System.out.println("4. My Rented Vehicles");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                viewVehicles();
                break;
            case 2:
                rentVehicle(id);
                break;
            case 3:
                returnVehicle(id);
                break;
            case 4:
                myVehicles(id);
                break;
            default:
                System.out.println("Invalid Choice...");
        }
    }

    static void addVehicle() {
        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v != null && v.id == id) {
                System.out.print("Vehicle already exists. Add quantity: ");
                int q = sc.nextInt();
                v.qty += q;
                System.out.println("Quantity Updated...");
                return;
            }
        }

        System.out.print("Enter Vehicle Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                vehicles[i] = new Vehicle(id, name, type, qty);
                System.out.println("Vehicle Added Successfully...");
                return;
            }
        }

        System.out.println("Vehicle Inventory Full...");
    }

    static void removeVehicle() {
        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && vehicles[i].id == id) {
                vehicles[i] = null;
                System.out.println("Vehicle Removed...");
                return;
            }
        }

        System.out.println("Vehicle Not Found...");
    }

    static void viewVehicles() {
        System.out.println("\nVEHICLE ID\tNAME\t\tTYPE\t\tQTY");

        for (Vehicle v : vehicles) {
            if (v != null) {
                System.out.println(v.id + "\t\t" + v.name + "\t\t" + v.type + "\t\t" + v.qty);
            }
        }
    }

    static void rentVehicle(int rid) {
        System.out.print("Enter Vehicle ID to Rent: ");
        int id = sc.nextInt();

        for (Vehicle v : vehicles) {
            if (v != null && v.id == id) {

                if (v.qty <= 0) {
                    System.out.println("Vehicle Not Available...");
                    return;
                }

                if (renters[rid - 1].rented.contains(id)) {
                    System.out.println("Already Rented...");
                    return;
                }

                v.qty--;
                renters[rid - 1].rented.add(id);
                System.out.println("Vehicle Rented Successfully...");
                return;
            }
        }

        System.out.println("Vehicle Not Found...");
    }

    static void returnVehicle(int rid) {
        System.out.print("Enter Vehicle ID to Return: ");
        int id = sc.nextInt();

        if (!renters[rid - 1].rented.contains(id)) {
            System.out.println("You did not rent this vehicle...");
            return;
        }

        for (Vehicle v : vehicles) {
            if (v != null && v.id == id) {
                v.qty++;
                renters[rid - 1].rented.remove(Integer.valueOf(id));
                System.out.println("Vehicle Returned Successfully...");
                return;
            }
        }
    }

    static void myVehicles(int rid) {
        ArrayList<Integer> list = renters[rid - 1].rented;

        if (list.isEmpty()) {
            System.out.println("No Rented Vehicles...");
            return;
        }

        System.out.println("Your Rented Vehicles:");

        for (int id : list) {
            for (Vehicle v : vehicles) {
                if (v != null && v.id == id) {
                    System.out.println(v.id + " - " + v.name);
                }
            }
        }
    }
}
