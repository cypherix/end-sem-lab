package com.example.end_sem_lab;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.end_sem_lab.models.OrderAdapter;
import com.example.end_sem_lab.Helpers.DBHelperOrder;
import com.example.end_sem_lab.models.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelperOrder dbHelperOrder;
    RecyclerView ordersRecyclerView;
    OrderAdapter orderAdapter;
    List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelperOrder = new DBHelperOrder(this);
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();

        // Adding a new customer
        if (dbHelperOrder.addCustomer("John Doe", "john@example.com", "1234567890")) {
            Toast.makeText(this, "Customer added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add customer", Toast.LENGTH_SHORT).show();
        }

        // Adding a new product
        if (dbHelperOrder.addProduct("Product 1", 10.5, 100)) {
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
        }

        // Adding a new order
        if (dbHelperOrder.addOrder(1, 1, 2, "2023-07-02")) {
            Toast.makeText(this, "Order added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add order", Toast.LENGTH_SHORT).show();
        }

        // Fetching all orders
        Cursor cursor = dbHelperOrder.getAllOrders();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int orderId = cursor.getInt(cursor.getColumnIndex("id"));
                int customerId = cursor.getInt(cursor.getColumnIndex("customer_id"));
                int productId = cursor.getInt(cursor.getColumnIndex("product_id"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                String orderDate = cursor.getString(cursor.getColumnIndex("order_date"));

                Order order = new Order(orderId, customerId, productId, quantity, orderDate);
                orderList.add(order);
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
        }

        orderAdapter = new OrderAdapter(orderList);
        ordersRecyclerView.setAdapter(orderAdapter);
    }
}
