/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int capacity;
    private List<Product> products;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public void receiveProducts(Product product) throws WarehouseException {
        Product existingProduct = findProduct(product.getName());

        if (existingProduct != null) {
            existingProduct.updateQuantity(product.getQuantity());
            System.out.println(product.getQuantity() + " одиниць товару " + product.getName()
                    + " додано до існуючого на складі. Загальна кількість: " + existingProduct.getQuantity());
        } else {
            products.add(product);
            System.out.println(product.getQuantity() + " одиниць товару " + product.getName()
                    + " прийнято на склад. Загальна кількість: " + product.getQuantity());
        }
    }

    public void issueProducts(Product product) throws WarehouseException {
        Product existingProduct = findProduct(product.getName());

        if (existingProduct != null) {
            int remainingQuantity = existingProduct.getQuantity() - product.getQuantity();

            if (remainingQuantity < 0) {
                throw new WarehouseException("Немає достатньо товару " + product.getName() + " на складі.");
            }

            existingProduct.updateQuantity(-product.getQuantity());
            System.out.println(product.getQuantity() + " одиниць товару " + product.getName()
                    + " видалено зі складу. Загальна кількість: " + existingProduct.getQuantity());
        } else {
            
            
            throw new WarehouseException("Товар " + product.getName() + " не знайдено на складі.");
        }
    }

    private Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }
}
