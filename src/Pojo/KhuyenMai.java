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
public class KhuyenMai {
    private String id;
    private String promotionName;
    private int discountPercent;
    private Date startDate;
    private Date endDate;
    private AppliedTo appliedTo;
    private Conditions conditions;

    // Constructors
    public KhuyenMai() {}

    public KhuyenMai(String id, String promotionName, int discountPercent, Date startDate,
                     Date endDate, AppliedTo appliedTo, Conditions conditions) {
        this.id = id;
        this.promotionName = promotionName;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.appliedTo = appliedTo;
        this.conditions = conditions;
    }

    // Getters and Setters for main fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public AppliedTo getAppliedTo() {
        return appliedTo;
    }

    public void setAppliedTo(AppliedTo appliedTo) {
        this.appliedTo = appliedTo;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    // Inner class for appliedTo object
    public static class AppliedTo {
        private List<String> products;
        private List<String> categories;

        public AppliedTo(List<String> products, List<String> categories) {
            this.products = products;
            this.categories = categories;
        }

        // Getters and Setters for AppliedTo
        public List<String> getProducts() {
            return products;
        }

        public void setProducts(List<String> products) {
            this.products = products;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
    }

    // Inner class for conditions object
    public static class Conditions {
        //private int minQuantity;
        private String customerTier;

        public Conditions(String customerTier) {
            //this.minQuantity = minQuantity;
            this.customerTier = customerTier;
        }

//        public int getMinQuantity() {
//            return minQuantity;
//        }
//
//        public void setMinQuantity(int minQuantity) {
//            this.minQuantity = minQuantity;
//        }

        public String getCustomerTier() {
            return customerTier;
        }

        public void setCustomerTier(String customerTier) {
            this.customerTier = customerTier;
        }
    }
}
