package view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import exception.CatchException;

import java.util.Locale;
import java.util.Scanner;

public class View {
    private static  final CustomerController customerController = new CustomerController();
    private static final ProductController productController = new ProductController();
    private static final OrderController orderController = new OrderController();
    public static void View()
    {
        System.out.println(
        "   \t\t\t\t            ██╗ ██████╗  ██████╗   ██████╗  \n"+
        "   \t\t\t\t            ██║ ██╔══██╗ ██╔═██╗ ██╔════╝  \n"+
        "   \t\t\t\t            ██║ ██║  ██║ ██████ ╔╝ ██║       \n"+
        "   \t\t\t\t            ██  ██║  ██║ ██║ ██ ╔══██╗ ██║       \n"+
        "   \t\t\t\t       █████╔╝ ███ ███╔╝ ██████╔╝ ╚██████╗  \n"+
        "   \t\t\t\t       ╚════╝  ╚═════╝  ╚═════╝   ╚═════╝  "
        );
    }
    public static void Menus() throws CatchException
    {
        try {
            System.out.println("[1] This is Product.");
            System.out.println("[2] This is Customer.");
            System.out.println("[3] This is Order.");
            System.out.println("[0/99] Exit");
            System.out.println("=".repeat(98));
            System.out.print("[+] Insert option of Application: ");
            String n = new Scanner(System.in).nextLine();
            switch (n)
            {
                case "1":
                    productMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                case "3":
                    orderMenu();
                    break;
                case "0","99":
                    System.exit(0);
                default:
                    throw new CatchException("Invalid option");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static void customerMenu() throws CatchException {
        try {
            while (true) {
                System.out.println("-".repeat(45) + "Menu of Customer" + "-".repeat(40));
                System.out.println("[1] Option Insert Customer");
                System.out.println("[2] Option queryAllCustomer");
                System.out.println("[3] Option Delete Customer");
                System.out.println("[4] Option Search Customer");
                System.out.println("[5] Option Update Customer");
                System.out.println("[b] Back to Main Menu");
                System.out.println("[0,99] Exit");
                System.out.println("=".repeat(98));
                System.out.print("[+] Insert option of Customer:");
                String n = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT).trim();
                switch (n) {
                    case "1":
                        customerController.insertCustomer();
                        break;
                    case "2":
                        customerController.getAllCustomers().forEach(System.out::println);
                        break;
                    case "3":
                        customerController.deleteCustomer();
                        break;
                    case "4":
                        System.out.println(customerController.searchCustomer());
                        break;
                    case "5":
                        customerController.
                                updateCustomer();
                        break;
                    case "B":
                        Menus();
                        break;
                    case "0", "99":
                        System.exit(0);
                    default:
                        throw new CatchException("Invalid option");
                }
            }
        } catch (CatchException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void productMenu() throws CatchException
    {
        try {
            while (true) {
                System.out.println("-".repeat(40) + "Menu of Product" + "-".repeat(40));
                System.out.println("[1] Option Insert Product");
                System.out.println("[2] Option queryAllProduct");
                System.out.println("[3] Option Delete Product");
                System.out.println("[4] Option Search Product");
                System.out.println("[5] Option Update Product");
                System.out.println("[b] Back to Main Menu");
                System.out.println("[0,99] Exit");
                System.out.println("=".repeat(98));
                System.out.print("[+] Insert option of Product:");
                String n = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT).trim();
                switch (n) {
                    case "1":
                        productController.insertProduct();
                        break;
                    case "2":
                        productController.getAllProduct().forEach(System.out::println);
                        break;
                    case "3":
                        productController.deleteProduct();
                        break;
                    case "4":
                        System.out.println(productController.searchProduct());
                        break;
                    case "5":
                        productController.updateProduct();
                        break;
                    case "B":
                        Menus();
                        break;
                    case "0","99":
                        System.exit(0);
                    default:
                        throw new CatchException("Invalid option");
                }
            }
        }catch (CatchException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void orderMenu()throws CatchException
    {
        try {
            while (true) {
                System.out.println("-".repeat(40) + "Menu of Order" + "-".repeat(40));
                System.out.println("[1] Option Insert Order");
                System.out.println("[2] Option queryAllOrder");
                System.out.println("[3] Option Delete Order");
                System.out.println("[4] Option Search Order");
                System.out.println("[5] Option Update Order");
                System.out.println("[b] Option Back to Main Menu");
                System.out.println("[0,99] Exit");
                System.out.println("=".repeat(98));
                System.out.print("[+] Insert option of Order:");
                String n = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT).trim();
                switch (n) {
                    case "1":
                        orderController.insertOrder();
                        break;
                    case "2":
                        orderController.getAllOrders().forEach(System.out::println);
                        break;
                    case "3":
                        orderController.deleteOrder();
                        break;
                    case "4":
                        System.out.println(orderController.searchOrder());
                        break;
                    case "5":
                        orderController.updateProduct();
                        break;
                    case "B":
                        Menus();
                        break;
                    case "0", "99":
                        System.exit(0);
                    default:
                        throw new CatchException("Invalid option");
                }
            }
        }catch (CatchException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void UI() throws CatchException{
        View();
        System.out.println("-".repeat(30)+"This is application Function" + "-".repeat(40));
        Menus();
    }

}
