package lab8;

import java.util.List;
import java.util.Scanner;

public class Lab8 {

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(100);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Оберіть опцію:");
            System.out.println("1. Додати товар");
            System.out.println("2. Видалити товар");
            System.out.println("3. Показати весь товар на складі");
            System.out.println("4. Вийти");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Помилка введення. Будь ласка, введіть ціле число.");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addProduct(warehouse, scanner);
                    break;
                case 2:
                    issueProduct(warehouse, scanner);
                    break;
                case 3:
                    showAllProducts(warehouse);
                    break;
                case 4:
                    System.out.println("Дякуємо за використання програми. До побачення!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void addProduct(Warehouse warehouse, Scanner scanner) {
        System.out.println("Введіть назву товару:");
        String name = scanner.next();

        int quantity = 0;

        try {
            System.out.println("Введіть кількість товару:");
            quantity = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Помилка введення. Кількість товару повинна бути цілим числом.");
            scanner.nextLine();
            return;
        }

        if (quantity < 0) {
            System.out.println("Помилка введення. Кількість товару повинна бути не від'ємною.");
            return;
        }

        Product product = new Product(name, quantity);

        try {
            warehouse.receiveProducts(product);
        } catch (WarehouseException e) {
            System.out.println("Виникла помилка на складі: " + e.getMessage());
        }
    }

    private static void issueProduct(Warehouse warehouse, Scanner scanner) {
        System.out.println("Введіть назву товару для видалення:");
        String name = scanner.next();

        int quantity = 0;

        try {
            System.out.println("Введіть кількість товару для видалення:");
            quantity = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Помилка введення. Кількість товару повинна бути цілим числом.");
            scanner.nextLine();
            return;
        }

        if (quantity < 0) {
            System.out.println("Помилка введення. Кількість товару повинна бути не від'ємною.");
            return;
        }

        Product product = new Product(name, quantity);

        try {
            warehouse.issueProducts(product);
        } catch (WarehouseException e) {
            System.out.println("Виникла помилка на складі: " + e.getMessage());
        }
    }

    private static void showAllProducts(Warehouse warehouse) {
        List<Product> products = warehouse.getProducts();

        System.out.println("Весь товар на складі:");

        if (products.isEmpty()) {
            System.out.println("Склад порожній.");
        } else {
            for (Product product : products) {
                System.out.println(product.getName() + ": " + product.getQuantity() + " одиниць");
            }
        }
    }
}
