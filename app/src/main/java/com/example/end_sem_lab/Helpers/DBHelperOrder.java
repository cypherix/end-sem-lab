package com.example.end_sem_lab.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperOrder extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Column names for Customers
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_EMAIL = "email";
    private static final String COLUMN_CUSTOMER_PHONE = "phone";

    // Table and Column names for Products
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_STOCK = "stock";

    // Table and Column names for Orders
    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ORDER_ID = "id";
    private static final String COLUMN_ORDER_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_ORDER_PRODUCT_ID = "product_id";
    private static final String COLUMN_ORDER_QUANTITY = "quantity";
    private static final String COLUMN_ORDER_DATE = "order_date";

    public DBHelperOrder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Customers table
        String createCustomersTableSQL = "CREATE TABLE " + TABLE_CUSTOMERS + " (" +
                COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " +
                COLUMN_CUSTOMER_EMAIL + " TEXT, " +
                COLUMN_CUSTOMER_PHONE + " TEXT)";
        db.execSQL(createCustomersTableSQL);

        // Create Products table
        String createProductsTableSQL = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " REAL, " +
                COLUMN_PRODUCT_STOCK + " INTEGER)";
        db.execSQL(createProductsTableSQL);

        // Create Orders table
        String createOrdersTableSQL = "CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDER_CUSTOMER_ID + " INTEGER, " +
                COLUMN_ORDER_PRODUCT_ID + " INTEGER, " +
                COLUMN_ORDER_QUANTITY + " INTEGER, " +
                COLUMN_ORDER_DATE + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_ORDER_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMERS + "(" + COLUMN_CUSTOMER_ID + "), " +
                "FOREIGN KEY(" + COLUMN_ORDER_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID + "))";
        db.execSQL(createOrdersTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed, and recreate the database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    // Method to add a new customer
    public boolean addCustomer(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_NAME, name);
        contentValues.put(COLUMN_CUSTOMER_EMAIL, email);
        contentValues.put(COLUMN_CUSTOMER_PHONE, phone);
        long result = db.insert(TABLE_CUSTOMERS, null, contentValues);
        return result != -1; // If result is -1, insertion failed
    }

    // Method to add a new product
    public boolean addProduct(String name, double price, int stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, name);
        contentValues.put(COLUMN_PRODUCT_PRICE, price);
        contentValues.put(COLUMN_PRODUCT_STOCK, stock);
        long result = db.insert(TABLE_PRODUCTS, null, contentValues);
        return result != -1; // If result is -1, insertion failed
    }

    // Method to add a new order
    public boolean addOrder(int customerId, int productId, int quantity, String orderDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ORDER_CUSTOMER_ID, customerId);
        contentValues.put(COLUMN_ORDER_PRODUCT_ID, productId);
        contentValues.put(COLUMN_ORDER_QUANTITY, quantity);
        contentValues.put(COLUMN_ORDER_DATE, orderDate);
        long result = db.insert(TABLE_ORDERS, null, contentValues);
        return result != -1; // If result is -1, insertion failed
    }

    // Method to get all orders
    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDERS, null);
    }

    // Method to get orders by customer ID
    public Cursor getOrdersByCustomerId(int customerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ORDERS, null, COLUMN_ORDER_CUSTOMER_ID + "=?", new String[]{String.valueOf(customerId)}, null, null, null);
    }

    // Method to get all products
    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
    }
}
