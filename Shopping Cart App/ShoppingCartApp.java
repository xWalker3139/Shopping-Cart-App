import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class ShoppingCart {
    private ArrayList<Product> cart;
    private double discount;
    private final double TAX_RATE = 0.07;

    public ShoppingCart() {
        this.cart = new ArrayList<>();
        this.discount = 0;
    }

    public void addProduct(Product product) {
        cart.add(product);
        System.out.println(product.name + " has been added to your cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your cart contains:");
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.price;
        }

        if (discount > 0) {
            System.out.println("Applying discount: " + (discount * 100) + "%");
            total -= total * discount;
        }

        double tax = total * TAX_RATE;
        System.out.println("Applying tax: $" + tax);
        total += tax;

        return total;
    }

    public void applyDiscount(double discountRate) {
        if (discountRate > 0 && discountRate <= 1) {
            this.discount = discountRate;
            System.out.println("Discount of " + (discountRate * 100) + "% applied.");
        } else {
            System.out.println("Invalid discount rate.");
        }
    }
}

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        boolean shopping = true;

        while (shopping) {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Apply Discount");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    cart.addProduct(new Product(name, price));
                    break;

                case 2:
                    cart.viewCart();
                    break;

                case 3:
                    System.out.print("Enter discount (e.g., 0.10 for 10%): ");
                    double discountRate = scanner.nextDouble();
                    cart.applyDiscount(discountRate);
                    break;

                case 4:
                    System.out.println("Calculating total...");
                    double total = cart.calculateTotal();
                    System.out.println("Total amount (after tax and discount): $" + total);
                    break;

                case 5:
                    shopping = false;
                    System.out.println("Thank you for shopping with us!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }

        scanner.close();
    }
}
