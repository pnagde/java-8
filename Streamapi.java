package com.tekjar.stream;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {

    static List<Product> products;


    public static void printTotalNumberOfPhoneByDifferentManufacturer(){
        //Print total number of Phone in the inventory by different manufacturer
        products
                .stream()
                .filter(product -> product.getCategory()
                        .equalsIgnoreCase("Phone"))
                .collect(Collectors.groupingBy(Product::getManufacturer, Collectors.counting()))
                .entrySet().forEach(System.out::println);
    }

    public static void printPhonesNameUnder15K(){
        //print phones name under 15,000 price
        products
                .stream()
                .filter(product -> product.getPrice() < 15000.0)
                .map(Product::getProductName)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        loadData(); //load sample data

        //Print total number of Phone in the inventory by different manufacturer
        products
                .stream()
                .filter(product -> product.getCategory()
                        .equalsIgnoreCase("Phone"))
                .collect(Collectors.groupingBy(Product::getManufacturer, Collectors.counting()))
                .entrySet().forEach(System.out::println);

        System.out.println("*********************************************************");

        //Print all Product names
        products
                .stream()
                .map(Product::getProductName)
                .distinct()
                .forEach(System.out::println);

        System.out.println("*********************************************************");

        //print phones name under 15,000 price
        products
                .stream()
                .filter(product -> product.getPrice() < 15000.0)
                .map(Product::getProductName)
                .forEach(System.out::println);

        System.out.println("*********************************************************");

        //print Highest paid phone name in inventory
        System.out.println(
                products
                        .stream()
                        .filter(product -> product.getCategory().equalsIgnoreCase("Phone"))
                        .max(Comparator.comparingDouble(Product::getPrice))
                        .map(Product::getProductName)
                        .get());

        System.out.println("*********************************************************");

        //print Product count by category

        products
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("*********************************************************");

        //print Average price of Iphone

        System.out.println(
                products
                .stream()
                        .filter(product -> product.getManufacturer().equalsIgnoreCase("Apple")
                                && product.getCategory().equalsIgnoreCase("Phone")).
                collect(Collectors.averagingDouble(Product::getPrice)));

        System.out.println("*********************************************************");

        //print lowest price laptop

        System.out.println(products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Laptop"))
                .min(Comparator.comparingDouble(Product::getPrice)).get().getProductName());

        System.out.println("*********************************************************");

        //print second-highest price phone by Manufacturer

        products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Phone"))
                .collect(Collectors.groupingBy(Product::getManufacturer)).entrySet().forEach(
                        product->{
                            product.getValue().stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                    .limit(2).skip(1).map(Product::getProductName).forEach(System.out::println);
                        }
                );

    }

    public static void loadData() {
        products = new ArrayList<>();

        products.add(new Product(101, "Samsung S10", "Phone", 10999.0, 10, "Samsung", "5 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(102, "Samsung S11 5G", "Phone", 29999.0, 12, "Samsung", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(103, "Samsung S22 plus", "Phone", 69999.0, 1, "Samsung", "12 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(104, "Google Pixel 5", "Phone", 34999.0, 12, "Google Inc", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(105, "Google Pixel 6", "Phone", 36999.0, 34, "Google Inc", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(106, "Google Pixel 7", "Phone", 46999.0, 19, "Google Inc", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(107, "Google Pixel 7a", "Phone", 59999.0, 16, "Google Inc", "12 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(108, "Google Pixel 8", "Phone", 79999.0, 12, "Google Inc", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(109, "Google Pixel 4", "Phone", 32999.0, 20, "Google Inc", "8 GB RAM, 128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(110, "OnePlus 10 pro", "Phone", 49999.0, 18, "OnePlus", "12 GB RAM, 256 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(111, "OnePlus 11 pro", "Phone", 69999.0, 11, "OnePlus", "12 GB RAM, 256 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(112, "Iphone 13", "Phone", 59999.0, 0, "Apple", "128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(113, "Iphone 12", "Phone", 49999.0, 7, "Apple", "128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(114, "Lenovo gaming L154", "Laptop", 80999.0, 1, "Lenovo", "128 GB ROM", "https://tekjar.in/image1"));
        products.add(new Product(114, "Acer Predator", "Laptop", 90999.0, 9, "Acer", "1TB ROM", "https://tekjar.in/image1"));
        products.add(new Product(114, "MacBook air", "Laptop", 120999.0, 6, "Apple", "1TB ROM", "https://tekjar.in/image1"));
        products.add(new Product(114, "Asus TUF", "Laptop", 70999.0, 7, "Asus", "1TB ROM", "https://tekjar.in/image1"));
        products.add(new Product(114, "Acer Nitro 5", "Laptop", 60999.0, 5, "Acer", "1TB ROM", "https://tekjar.in/image1"));

    }

}

class Product {
    private int productId;
    private String productName;
    private String category;
    private double price;
    private int stockQuantity;
    private String manufacturer;
    private String description;
    private String imageUrl;


    public Product(int productId, String productName, String category, double price, int stockQuantity, String manufacturer, String description, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.manufacturer = manufacturer;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters and setters for all fields
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

