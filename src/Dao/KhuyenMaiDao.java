/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.KhuyenMai;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class KhuyenMaiDao {
    public static MongoCollection<Document> collection;

    public KhuyenMaiDao() {
        collection = Connect.database.getCollection("Promotions");
    }
    
    
    public void checkAndUpdatePromotion(String promotionID) {
        Document promotion = collection.find(new Document("_id", promotionID)).first();

        if (promotion != null) {
            Date endDate = promotion.getDate("endDate");

            Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (today.after(endDate)) {
                removePromotionFromProducts(promotionID);
            }
        }
    }
    
    public void removePromotionFromProducts(String promotionID) {
        MongoCollection<Document> collectionProducts = Connect.database.getCollection("Product");

        collectionProducts.updateMany(
            new Document("promotionIDs", promotionID),
            new Document("$set", new Document("isPromotionProgram", false))
                .append("$pull", new Document("promotionIDs", promotionID))
        );
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
        // Xử lý lỗi nếu có
        promotions.sort(Comparator.comparing(KhuyenMai::getId));
        return promotions;
    }
    public Document getPromotionById(String promotionId) {
        Document query = new Document("_id", promotionId);
        Document promotionDoc = collection.find(query).first();
        return promotionDoc;
    }

    // Thêm khuyến mãi mới
    public boolean addPromotion(KhuyenMai promotion) {
        try{
            Document doc = new Document("_id", promotion.getId())
                .append("promotionName", promotion.getPromotionName())
                .append("discountPercent", promotion.getDiscountPercent())
                .append("startDate", promotion.getStartDate())
                .append("endDate", promotion.getEndDate())
                .append("appliedTo", new Document("products", promotion.getAppliedTo().getProducts())
                        .append("categories", promotion.getAppliedTo().getCategories())
                )
                .append("conditions", new Document("minOrderValue", promotion.getConditions().getMinOrderValue())
                        .append("customerTier", promotion.getConditions().getCustomerTier()));

        // Insert the document into the collection
            collection.insertOne(doc);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    // Xóa khuyến mãi theo ID
    public boolean deletePromotion(String id) {
        try{
            collection.deleteOne(new Document("_id", id));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    // Cập nhật thông tin khuyến mãi
    public boolean updatePromotion(String id, KhuyenMai updatedPromotion) {
        try{
            Document updateDoc = new Document("$set", new Document("promotionName", updatedPromotion.getPromotionName())
//                .append("promotionType", updatedPromotion.getPromotionType())
            .append("discountPercent", updatedPromotion.getDiscountPercent())
            .append("startDate", updatedPromotion.getStartDate())
            .append("endDate", updatedPromotion.getEndDate())
            .append("appliedTo", new Document("products", updatedPromotion.getAppliedTo().getProducts())
                    .append("categories", updatedPromotion.getAppliedTo().getCategories()))
            .append("conditions", new Document("minOrderValue", updatedPromotion.getConditions().getMinOrderValue())
//                        .append("minQuantity", updatedPromotion.getConditions().getMinQuantity())
                    .append("customerTier", updatedPromotion.getConditions().getCustomerTier())));

            collection.updateOne(new Document("_id", id), updateDoc);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    // Phân tích Document thành đối tượng KhuyenMai
    private KhuyenMai parsePromotion(Document doc) {
        Document appliedToDoc = doc.get("appliedTo", Document.class);
        Document conditionsDoc = doc.get("conditions", Document.class);

        // Chuyển đổi các trường 'startDate' và 'endDate' từ chuỗi sang Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = doc.getDate("startDate");
        Date endDate = doc.getDate("endDate");

        KhuyenMai.AppliedTo appliedTo = new KhuyenMai.AppliedTo(
                (List<String>) appliedToDoc.get("products"),
                (List<String>) appliedToDoc.get("categories")
        );

        KhuyenMai.Conditions conditions = new KhuyenMai.Conditions(
                conditionsDoc.getInteger("minOrderValue"),
                conditionsDoc.getString("customerTier")
        );

        return new KhuyenMai(
                doc.getString("_id"),
                doc.getString("promotionName"),
                doc.getInteger("discountPercent"),
                startDate,
                endDate,
                appliedTo,
                conditions
        );
    }
   public double getKhuyenMaiPercent(String maKhuyenMai) {
        // Truy vấn collection "KhuyenMai" để lấy tỷ lệ phần trăm khuyến mãi
        Document promotion = collection.find(new Document("_id", maKhuyenMai)).first();

        if (promotion != null && promotion.containsKey("discountPercent")) {
            Object discountValue = promotion.get("discountPercent");

            if (discountValue instanceof Double) {
                return (Double) discountValue;
            } else if (discountValue instanceof Integer) {
                return ((Integer) discountValue).doubleValue(); // Chuyển đổi Integer sang Double
            }
        }

        // Nếu không có khuyến mãi hoặc discountPercent là null, trả về 0
        return 0.0;
    }


}
