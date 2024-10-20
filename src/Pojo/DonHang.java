/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author HUU KHANH
 */
public class DonHang {
    private String id;
    private String customerID;
    private Date orderDate;
    private List<Product> products;
    private double totalAmount;
    private String employeeID;
    private boolean isCheckVoucher;

    public boolean isIsCheckVoucher() {
        return isCheckVoucher;
    }

    public void setIsCheckVoucher(boolean isCheckVoucher) {
        this.isCheckVoucher = isCheckVoucher;
    }

    
    // Constructor
    public DonHang(String id, String customerID, Date orderDate, List<Product> products, double totalAmount, String employeeID,boolean isCheckVoucher) {
        this.id = id;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.products = products;
        this.totalAmount = totalAmount;
        this.employeeID = employeeID;
        this.isCheckVoucher=isCheckVoucher;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public DonHang() {
       
    }


    // Getters và Setters cho Order
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    // Lớp Product bên trong Order
    public static class Product {
        private String productID;
        private int quantity;
        private double price;
        private String promotionID;
        private double discountedPrice;

        // Constructor
        public Product(String productID, int quantity, double price, String promotionID, double discountedPrice) {
            this.productID = productID;
            this.quantity = quantity;
            this.price = price;
            this.promotionID = promotionID;
            this.discountedPrice = discountedPrice;
        }

        // Getters và Setters cho Product
        public String getProductID() {
            return productID;
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPromotionID() {
            return promotionID;
        }

        public void setPromotionID(String promotionID) {
            this.promotionID = promotionID;
        }

        public double getDiscountedPrice() {
            return discountedPrice;
        }

        public void setDiscountedPrice(double discountedPrice) {
            this.discountedPrice = discountedPrice;
        }
    }
}
