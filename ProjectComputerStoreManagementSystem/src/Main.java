import java.util.ArrayList;

class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public void displayInfo() {
        System.out.println("Product: " + name + ", Category: " + category + ", Price: " + price + ", Stock: " + stockQuantity);
    }
}

class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isLoyalCustomer;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean getIsLoyalCustomer() { return isLoyalCustomer; }
    public void setIsLoyalCustomer(boolean isLoyalCustomer) { this.isLoyalCustomer = isLoyalCustomer; }

    public void displayInfo() {
        System.out.println("Customer: " + firstName + " " + lastName + ", Email: " + email + ", Loyal: " + isLoyalCustomer);
    }
}

class Order {
    private int id;
    private Customer customer;
    private Product[] products;
    private int[] quantities;
    private String orderDate;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Product[] getProducts() { return products; }
    public void setProducts(Product[] products) { this.products = products; }

    public int[] getQuantities() { return quantities; }
    public void setQuantities(int[] quantities) { this.quantities = quantities; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double calculateTotalValue() {
        double total = 0;
        for (int i = 0; i < products.length; i++) {
            total += products[i].getPrice() * quantities[i];
        }
        return total;
    }

    public void applyDiscount() {
        if (customer.getIsLoyalCustomer()) {
            for (int i = 0; i < products.length; i++) {
                products[i].setPrice(products[i].getPrice() * 0.9);
            }
        }
    }

    public void displayDetails() {
        System.out.println("Order ID: " + id + ", Status: " + status);
        customer.displayInfo();
        for (int i = 0; i < products.length; i++) {
            System.out.print("x" + quantities[i] + " ");
            products[i].displayInfo();
        }
        System.out.println("Total: " + calculateTotalValue());
    }
}

class ComputerStore {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private int orderCounter = 1;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Order createOrder(Customer customer, Product[] orderProducts, int[] quantities) {
        Order order = new Order();
        order.setId(orderCounter++);
        order.setCustomer(customer);
        order.setProducts(orderProducts);
        order.setQuantities(quantities);
        order.setOrderDate("2025-03-27");
        order.setStatus("New");
        orders.add(order);
        return order;
    }

    public void updateStockAfterOrder(Order order) {
        Product[] products = order.getProducts();
        int[] quantities = order.getQuantities();
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            p.setStockQuantity(p.getStockQuantity() - quantities[i]);
        }
    }

    public void changeOrderStatus(int orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setStatus(newStatus);
                break;
            }
        }
    }

    public void displayProductsInCategory(String category) {
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                p.displayInfo();
            }
        }
    }

    public void displayCustomerOrders(int customerId) {
        for (Order order : orders) {
            if (order.getCustomer().getId() == customerId) {
                order.displayDetails();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ComputerStore store = new ComputerStore();

        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Laptop Dell XPS 13");
        p1.setCategory("Laptop");
        p1.setPrice(4999.99);
        p1.setStockQuantity(10);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Mouse Logitech MX Master 3");
        p2.setCategory("Mouse");
        p2.setPrice(349.99);
        p2.setStockQuantity(30);

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Monitor Samsung 27\"");
        p3.setCategory("Monitor");
        p3.setPrice(1299.99);
        p3.setStockQuantity(15);

        store.addProduct(p1);
        store.addProduct(p2);
        store.addProduct(p3);

        Customer c1 = new Customer();
        c1.setId(1);
        c1.setFirstName("John");
        c1.setLastName("Smith");
        c1.setEmail("john.smith@example.com");
        c1.setIsLoyalCustomer(true);

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setFirstName("Anna");
        c2.setLastName("Brown");
        c2.setEmail("anna.brown@example.com");
        c2.setIsLoyalCustomer(false);

        store.addCustomer(c1);
        store.addCustomer(c2);

        Product[] orderProducts1 = {p1, p2};
        int[] orderQuantities1 = {1, 1};
        Order order1 = store.createOrder(c1, orderProducts1, orderQuantities1);
        order1.applyDiscount();
        store.updateStockAfterOrder(order1);

        System.out.println("Order 1:");
        order1.displayDetails();

        store.changeOrderStatus(order1.getId(), "Completed");

        System.out.println("\nOrder 1 After Status Change:");
        order1.displayDetails();

        Product[] orderProducts2 = {p3, p2};
        int[] orderQuantities2 = {2, 1};
        Order order2 = store.createOrder(c2, orderProducts2, orderQuantities2);
        store.updateStockAfterOrder(order2);

        System.out.println("\nOrders of John Smith:");
        store.displayCustomerOrders(1);

        System.out.println("\nCurrent stock of Laptop:");
        store.displayProductsInCategory("Laptop");

        System.out.println("\nCurrent stock of Mouse:");
        store.displayProductsInCategory("Mouse");
    }
}
