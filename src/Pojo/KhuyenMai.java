/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.util.List;

/**
 *
 * @author HUU KHANH
 */
public class KhuyenMai {
    private String id;
    private String promotionName;
    private String promotionType;
    private int discountPercent;
    private String startDate;
    private String endDate;

    // Embedded classes for nested objects
    private AppliedTo appliedTo;
    private Conditions conditions;

    // Constructors
    public KhuyenMai() {}

    public KhuyenMai(String id, String promotionName, String promotionType, int discountPercent, String startDate,
                     String endDate, AppliedTo appliedTo, Conditions conditions) {
        this.id = id;
        this.promotionName = promotionName;
        this.promotionType = promotionType;
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

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
        private List<String> customers;

        public AppliedTo(List<String> products, List<String> categories, List<String> customers) {
            this.products = products;
            this.categories = categories;
            this.customers = customers;
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

        public List<String> getCustomers() {
            return customers;
        }

        public void setCustomers(List<String> customers) {
            this.customers = customers;
        }
    }

    // Inner class for conditions object
    public static class Conditions {
        private int minOrderValue;
        private int minQuantity;
        private String customerTier;

        public Conditions(int minOrderValue, int minQuantity, String customerTier) {
            this.minOrderValue = minOrderValue;
            this.minQuantity = minQuantity;
            this.customerTier = customerTier;
        }

        // Getters and Setters for Conditions
        public int getMinOrderValue() {
            return minOrderValue;
        }

        public void setMinOrderValue(int minOrderValue) {
            this.minOrderValue = minOrderValue;
        }

        public int getMinQuantity() {
            return minQuantity;
        }

        public void setMinQuantity(int minQuantity) {
            this.minQuantity = minQuantity;
        }

        public String getCustomerTier() {
            return customerTier;
        }

        public void setCustomerTier(String customerTier) {
            this.customerTier = customerTier;
        }
    }
}
