/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOANG LONG
 */
public class SanPham {
    private String id;
    private String productName;
    private BigDecimal price;
    private String categoryID;
    private List<String> promotionIDs;
    private boolean isPromotionProgram;

    // Constructor
    public SanPham(String id, String productName, BigDecimal price, String categoryID, List<String> promotionIDs, boolean isPromotionProgram) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.categoryID = categoryID;
        this.promotionIDs = promotionIDs;
        this.isPromotionProgram = isPromotionProgram;
    }
    public SanPham() {
        this.promotionIDs = new ArrayList<>(); // Khởi tạo mặc định là một danh sách rỗng
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public List<String> getPromotionIDs() {
        return promotionIDs;
    }

    public void setPromotionIDs(List<String> promotionIDs) {
        this.promotionIDs = promotionIDs;
    }

    public boolean getIsPromotionProgram() {
        return isPromotionProgram;
    }

    public void setIsPromotionProgram(boolean isPromotionProgram) {
        this.isPromotionProgram = isPromotionProgram;
    }

}
