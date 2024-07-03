package com.example.end_sem_lab.models;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private String orderDate;

    public Order(int id, int customerId, int productId, int quantity, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
