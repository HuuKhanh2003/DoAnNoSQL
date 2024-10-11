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
    private List<String> promotionIDs;
    private int voucherQuantity;
    
    // Constructor
    public KhachHang(String id, String customerName, String email, String tier, List<String> promotionIDs, int voucherQuantity) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.tier = tier;
        this.promotionIDs = promotionIDs;
        this.voucherQuantity = voucherQuantity;
    }
    public KhachHang() {
        this.promotionIDs = new ArrayList<>();
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

    public List<String> getPromotionIDs() {
        return promotionIDs;
    }

    public void setPromotionIDs(List<String> promotionIDs) {
        this.promotionIDs = promotionIDs;
    }

    public int getVoucherQuantity() {
        return voucherQuantity;
    }

    public void setVoucherQuantity(int voucherQuantity) {
        this.voucherQuantity = voucherQuantity;
    }

}
