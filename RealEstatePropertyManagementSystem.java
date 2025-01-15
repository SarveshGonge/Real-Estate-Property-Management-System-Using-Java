import java.util.*;

class Property {
    int propertyID;
    String address;
    double price;
    boolean isAvailable;

    public Property(int propertyID, String address, double price) {
        this.propertyID = propertyID;
        this.address = address;
        this.price = price;
        this.isAvailable = true;
    }

    public void displayPropertyInfo() {
        System.out.println("Property ID: " + propertyID);
        System.out.println("Address: " + address);
        System.out.println("Price: " + price);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("-------------------------------");
    }

    public void markAsRented() {
        this.isAvailable = false;
    }

    public void markAsAvailable() {
        this.isAvailable = true;
    }
}

class Tenant {
    int tenantID;
    String name;
    String contactInfo;

    public Tenant(int tenantID, String name, String contactInfo) {
        this.tenantID = tenantID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void displayTenantInfo() {
        System.out.println("Tenant ID: " + tenantID);
        System.out.println("Name: " + name);
        System.out.println("Contact Info: " + contactInfo);
        System.out.println("-------------------------------");
    }
}

class Lease {
    int leaseID;
    Property property;
    Tenant tenant;
    Date startDate;
    Date endDate;
    double monthlyRent;

    public Lease(int leaseID, Property property, Tenant tenant, Date startDate, Date endDate, double monthlyRent) {
        this.leaseID = leaseID;
        this.property = property;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRent = monthlyRent;
    }

    public void displayLeaseInfo() {
        System.out.println("Lease ID: " + leaseID);
        System.out.println("Property ID: " + property.propertyID);
        System.out.println("Tenant ID: " + tenant.tenantID);
        System.out.println("Lease Start Date: " + startDate);
        System.out.println("Lease End Date: " + endDate);
        System.out.println("Monthly Rent: " + monthlyRent);
        System.out.println("-------------------------------");
    }
}

public class RealEstatePropertyManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Property> properties = new ArrayList<>();
    static List<Tenant> tenants = new ArrayList<>();
    static List<Lease> leases = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Real Estate Property Management System");
            System.out.println("1. Add Property");
            System.out.println("2. Add Tenant");
            System.out.println("3. Create Lease");
            System.out.println("4. View Properties");
            System.out.println("5. View Tenants");
            System.out.println("6. View Leases");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addProperty();
                    break;
                case 2:
                    addTenant();
                    break;
                case 3:
                    createLease();
                    break;
                case 4:
                    viewProperties();
                    break;
                case 5:
                    viewTenants();
                    break;
                case 6:
                    viewLeases();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void addProperty() {
        System.out.print("Enter Property ID: ");
        int propertyID = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        properties.add(new Property(propertyID, address, price));
        System.out.println("Property added successfully!");
    }

    public static void addTenant() {
        System.out.print("Enter Tenant ID: ");
        int tenantID = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        tenants.add(new Tenant(tenantID, name, contactInfo));
        System.out.println("Tenant added successfully!");
    }

    public static void createLease() {
        System.out.print("Enter Lease ID: ");
        int leaseID = scanner.nextInt();
        System.out.print("Enter Property ID: ");
        int propertyID = scanner.nextInt();
        Property property = findPropertyById(propertyID);

        if (property != null && property.isAvailable) {
            System.out.print("Enter Tenant ID: ");
            int tenantID = scanner.nextInt();
            Tenant tenant = findTenantById(tenantID);

            if (tenant != null) {
                scanner.nextLine();  
                System.out.print("Enter Lease Start Date (YYYY-MM-DD): ");
                String startDateStr = scanner.nextLine();
                System.out.print("Enter Lease End Date (YYYY-MM-DD): ");
                String endDateStr = scanner.nextLine();
                System.out.print("Enter Monthly Rent: ");
                double monthlyRent = scanner.nextDouble();

                Date startDate = java.sql.Date.valueOf(startDateStr);
                Date endDate = java.sql.Date.valueOf(endDateStr);
                Lease lease = new Lease(leaseID, property, tenant, startDate, endDate, monthlyRent);
                leases.add(lease);
                property.markAsRented();
                System.out.println("Lease created successfully!");
            } else {
                System.out.println("Tenant not found.");
            }
        } else {
            System.out.println("Property not available or not found.");
        }
    }

    public static void viewProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
        } else {
            for (Property property : properties) {
                property.displayPropertyInfo();
            }
        }
    }

    public static void viewTenants() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants available.");
        } else {
            for (Tenant tenant : tenants) {
                tenant.displayTenantInfo();
            }
        }
    }

    public static void viewLeases() {
        if (leases.isEmpty()) {
            System.out.println("No leases available.");
        } else {
            for (Lease lease : leases) {
                lease.displayLeaseInfo();
            }
        }
    }

    public static Property findPropertyById(int propertyID) {
        for (Property property : properties) {
            if (property.propertyID == propertyID) {
                return property;
            }
        }
        return null;
    }

    public static Tenant findTenantById(int tenantID) {
        for (Tenant tenant : tenants) {
            if (tenant.tenantID == tenantID) {
                return tenant;
            }
        }
        return null;
    }
}

