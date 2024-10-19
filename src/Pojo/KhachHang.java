/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUU KHANH
 */
public class KhachHang {
    private String id;
    private String customerName;
    private String email;
    private String tier;
    private int voucherQuantity;
    
    // Constructor
    public KhachHang(String id, String customerName, String email, String tier,  int voucherQuantity) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.tier = tier;
        this.voucherQuantity = voucherQuantity;
    }
    public KhachHang() {
        
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

   

    public int getVoucherQuantity() {
        return voucherQuantity;
    }

    public void setVoucherQuantity(int voucherQuantity) {
        this.voucherQuantity = voucherQuantity;
    }

}
