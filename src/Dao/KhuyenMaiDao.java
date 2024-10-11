/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.KhuyenMai;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class KhuyenMaiDao {
    private final MongoCollection<Document> collection;

    public KhuyenMaiDao(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // Lấy danh sách tất cả các khuyến mãi
    public List<KhuyenMai> getAllPromotions() {
        List<KhuyenMai> promotions = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                KhuyenMai promotion = parsePromotion(doc);
                promotions.add(promotion);
            }
        }

        // Sắp xếp danh sách khuyến mãi theo mã (_id)
        promotions.sort(Comparator.comparing(KhuyenMai::getId));
        return promotions;
    }

    // Thêm khuyến mãi mới
    public void addPromotion(KhuyenMai promotion) {
        Document doc = new Document("_id", promotion.getId())
                .append("promotionName", promotion.getPromotionName())
                .append("promotionType", promotion.getPromotionType())
                .append("discountPercent", promotion.getDiscountPercent())
                .append("startDate", promotion.getStartDate())
                .append("endDate", promotion.getEndDate())
                .append("appliedTo", new Document("products", promotion.getAppliedTo().getProducts())
                        .append("categories", promotion.getAppliedTo().getCategories())
                        .append("customers", promotion.getAppliedTo().getCustomers()))
                .append("conditions", new Document("minOrderValue", promotion.getConditions().getMinOrderValue())
                        .append("minQuantity", promotion.getConditions().getMinQuantity())
                        .append("customerTier", promotion.getConditions().getCustomerTier()));

        collection.insertOne(doc);
    }

    // Xóa khuyến mãi theo ID
    public void deletePromotion(String id) {
        collection.deleteOne(new Document("_id", id));
    }

    // Cập nhật thông tin khuyến mãi
    public void updatePromotion(String id, KhuyenMai updatedPromotion) {
        Document updateDoc = new Document("$set", new Document("promotionName", updatedPromotion.getPromotionName())
                .append("promotionType", updatedPromotion.getPromotionType())
                .append("discountPercent", updatedPromotion.getDiscountPercent())
                .append("startDate", updatedPromotion.getStartDate())
                .append("endDate", updatedPromotion.getEndDate())
                .append("appliedTo", new Document("products", updatedPromotion.getAppliedTo().getProducts())
                        .append("categories", updatedPromotion.getAppliedTo().getCategories())
                        .append("customers", updatedPromotion.getAppliedTo().getCustomers()))
                .append("conditions", new Document("minOrderValue", updatedPromotion.getConditions().getMinOrderValue())
                        .append("minQuantity", updatedPromotion.getConditions().getMinQuantity())
                        .append("customerTier", updatedPromotion.getConditions().getCustomerTier())));

        collection.updateOne(new Document("_id", id), updateDoc);
    }

    // Phân tích Document thành đối tượng KhuyenMai
    private KhuyenMai parsePromotion(Document doc) {
        Document appliedToDoc = doc.get("appliedTo", Document.class);
        Document conditionsDoc = doc.get("conditions", Document.class);

        KhuyenMai.AppliedTo appliedTo = new KhuyenMai.AppliedTo(
                (List<String>) appliedToDoc.get("products"),
                (List<String>) appliedToDoc.get("categories"),
                (List<String>) appliedToDoc.get("customers")
        );

        KhuyenMai.Conditions conditions = new KhuyenMai.Conditions(
                conditionsDoc.getInteger("minOrderValue"),
                conditionsDoc.getInteger("minQuantity"),
                conditionsDoc.getString("customerTier")
        );

        return new KhuyenMai(
                doc.getString("_id"),
                doc.getString("promotionName"),
                doc.getString("promotionType"),
                doc.getInteger("discountPercent"),
                doc.getString("startDate"),
                doc.getString("endDate"),
                appliedTo,
                conditions
        );
    }
}
