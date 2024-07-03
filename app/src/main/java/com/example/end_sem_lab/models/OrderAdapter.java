package com.example.end_sem_lab.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.end_sem_lab.models.Order;
import com.example.end_sem_lab.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderIdTextView.setText("Order ID: " + order.getId());
        holder.customerIdTextView.setText("Customer ID: " + order.getCustomerId());
        holder.productIdTextView.setText("Product ID: " + order.getProductId());
        holder.quantityTextView.setText("Quantity: " + order.getQuantity());
        holder.orderDateTextView.setText("Order Date: " + order.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTextView, customerIdTextView, productIdTextView, quantityTextView, orderDateTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            customerIdTextView = itemView.findViewById(R.id.customerIdTextView);
            productIdTextView = itemView.findViewById(R.id.productIdTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            orderDateTextView = itemView.findViewById(R.id.orderDateTextView);
        }
    }
}
