/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.SanPham;
import java.math.BigDecimal;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.Document;

/**
 *
 * @author HOANG LONG
 */
public class SanPhamDao {
    public static MongoCollection<Document> collection;
    public static MongoCollection<Document> collection_Promotion;
    public SanPhamDao() {
        // Kết nối tới MongoDB
        collection = Connect.database.getCollection("Product");
        collection_Promotion=Connect.database.getCollection("Promotions");
    }
    public List<SanPham> getAllProducts() {
        List<SanPham> products = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Double price;
                Object priceObject = doc.get("price");
                price = ((Double) priceObject);
                SanPham product = new SanPham(
                        doc.getString("_id"),
                        doc.getString("productName"),
                        price,
                        doc.getString("categoryID"),
                        doc.getString("promotionIDs"),
                        doc.getBoolean("isPromotionProgram")
                );
                products.add(product);
            }
        } finally {
            cursor.close();
        }

        return products;
    }
    

    // Hàm thêm sản phẩm
    public boolean addProduct(SanPham product) {
        try {
            // Tạo tài liệu từ thông tin sản phẩm
            Document doc = new Document("_id", product.getId())
                    .append("productName", product.getProductName())
                    .append("price", product.getPrice())
                    .append("categoryID", product.getCategoryID())
                    .append("promotionIDs", product.getPromotionIDs())
                    .append("isPromotionProgram", product.getIsPromotionProgram());

            // Thêm tài liệu vào collection
            collection.insertOne(doc);
            System.out.println("Sản phẩm đã được thêm thành công!");
            return true;  // Thêm thành công
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;  // Thêm thất bại
        }
    }


    // Hàm xóa sản phẩm theo ID
    public boolean deleteProduct(String productId) {
        // Sử dụng câu lệnh xóa
        DeleteResult result = collection.deleteOne(Filters.eq("_id", productId));

        // Kiểm tra xem có tài liệu nào được xóa không
        if (result.getDeletedCount() > 0) {
            System.out.println("Sản phẩm đã được xóa thành công!");
            return true; // Trả về true nếu xóa thành công
        } else {
            System.out.println("Không tìm thấy sản phẩm để xóa!");
            return false; // Trả về false nếu không tìm thấy sản phẩm
        }
    }


    // Hàm sửa thông tin sản phẩm
    public boolean updateProduct(SanPham product) {
    // Sử dụng câu lệnh cập nhật
        UpdateResult result = collection.updateOne(
            Filters.eq("_id", product.getId()),
            Updates.combine(
                Updates.set("productName", product.getProductName()),
                Updates.set("price", product.getPrice()),
                Updates.set("categoryID", product.getCategoryID()),
                Updates.set("promotionIDs", product.getPromotionIDs()),
                Updates.set("isPromotionProgram", product.getIsPromotionProgram())
            )
        );

        // Kiểm tra xem có tài liệu nào được cập nhật không
        if (result.getModifiedCount() > 0) {
            System.out.println("Sản phẩm đã được cập nhật thành công!");
            return true; // Trả về true nếu cập nhật thành công
        } else {
            System.out.println("Không tìm thấy sản phẩm để cập nhật!");
            return false; // Trả về false nếu không tìm thấy sản phẩm
        }
    }

    public List<String> getAllProductCategories() {
    List<String> categoryIDs = new ArrayList<>();
    
    // Thực hiện truy vấn để lấy tất cả sản phẩm
    MongoCursor<Document> cursor = collection.find().iterator();

    try {
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            // Lấy giá trị categoryID từ tài liệu (document)
            String categoryID = doc.getString("categoryID");
            
            // Kiểm tra nếu giá trị categoryID không null và chưa có trong danh sách categoryIDs
            if (categoryID != null && !categoryIDs.contains(categoryID)) {
                categoryIDs.add(categoryID); // Thêm vào danh sách
            }
        }
    } finally {
        cursor.close();
    }

    return categoryIDs; // Trả về danh sách loại sản phẩm
    }
    
    public List<String> getAllProductIDs() {
    List<String> productIDs = new ArrayList<>();
    
    // Thực hiện truy vấn để lấy tất cả sản phẩm
    MongoCursor<Document> cursor = collection.find().iterator();

    try {
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String productID = doc.getString("_id");
            
            if (productID != null && !productIDs.contains(productID)) {
                productIDs.add(productID);
            }
        }
    } finally {
        cursor.close();
    }

    return productIDs;
    }
    
    public List<SanPham> getProductsByCategory(String categoryID) {
        List<SanPham> allProducts = getAllProducts();
        List<SanPham> result = new ArrayList<>();

        for (SanPham sp : allProducts) {
            if (sp.getCategoryID().equals(categoryID)) {
                result.add(sp); // Nếu categoryID khớp, thêm vào kết quả
            }
        }

        return result;
    }
    
    public List<SanPham> searchProducts(String keyword) {
    List<SanPham> allProducts = getAllProducts(); // Lấy tất cả sản phẩm
    List<SanPham> result = new ArrayList<>();

    // Lọc sản phẩm theo mã, tên hoặc loại sản phẩm
    for (SanPham sp : allProducts) {
        if (sp.getId().toLowerCase().contains(keyword.toLowerCase()) || 
            sp.getProductName().toLowerCase().contains(keyword.toLowerCase()) || 
            sp.getCategoryID().toLowerCase().contains(keyword.toLowerCase())) {
            result.add(sp); // Thêm vào kết quả nếu khớp từ khóa
        }
    }

    return result;
    }
    
//   public List<SanPham> getProductsByCustomerTier(String tier) {
//    List<SanPham> products = new ArrayList<>();
//
//    // Tìm các chương trình khuyến mãi áp dụng cho tier khách hàng cụ thể
//    MongoCursor<Document> promoCursor = collection_Promotion.find(new Document("conditions.customerTier", tier)).iterator();
//    Set<String> validProductIDs = new HashSet<>();
//
//    try {
//        // Lấy danh sách các sản phẩm từ các chương trình khuyến mãi
//        while (promoCursor.hasNext()) {
//            Document promoDoc = promoCursor.next();
//            
//            // Kiểm tra xem trường "appliedTo" có tồn tại và không phải là null
//            if (promoDoc.containsKey("appliedTo")) {
//                Document appliedToDoc = (Document) promoDoc.get("appliedTo");
//                
//                // Kiểm tra xem "products" có tồn tại trong "appliedTo"
//                if (appliedToDoc.containsKey("products")) {
//                    List<String> promoProductIDs = appliedToDoc.getList("products", String.class);
//                    validProductIDs.addAll(promoProductIDs);
//                }
//            }
//        }
//    } finally {
//        promoCursor.close();
//    }
//
//    // Nếu không có sản phẩm nào được áp dụng khuyến mãi cho tier này, trả về danh sách rỗng
//    if (validProductIDs.isEmpty()) {
//        return products;
//    }
//
//    // Truy vấn sản phẩm theo danh sách sản phẩm hợp lệ
//    MongoCursor<Document> productCursor = collection.find(new Document("_id", new Document("$in", validProductIDs))).iterator();
//
//    try {
//        while (productCursor.hasNext()) {
//            Document doc = productCursor.next();
//            Double price;
//            Object priceObject = doc.get("price");
//            price = ((Double) priceObject);
//
//            // Tạo đối tượng SanPham từ dữ liệu lấy được
//            SanPham product = new SanPham(
//                doc.getString("_id"),
//                doc.getString("productName"),
//                price,
//                doc.getString("categoryID"),
//                doc.getString("promotionIDs"),
//                doc.getBoolean("isPromotionProgram")
//            );
//            products.add(product);
//        }
//    } finally {
//        productCursor.close();
//    }
//
//    return products;
//}

    public List<SanPham> getProductsByCustomerTier(String tier) {
    List<SanPham> products = new ArrayList<>();

    // Lấy tất cả các sản phẩm từ collection sản phẩm
    MongoCursor<Document> productCursor = collection.find().iterator();

    try {
        // Duyệt qua tất cả sản phẩm
        while (productCursor.hasNext()) {
            Document doc = productCursor.next();
            
            // Kiểm tra và lấy giá trị price một cách an toàn
            Double price = 0.0;
            Object priceObject = doc.get("price");
            if (priceObject instanceof Double) {
                price = (Double) priceObject;
            } else if (priceObject instanceof Integer) {
                price = ((Integer) priceObject).doubleValue();
            }

            // Lấy thông tin khuyến mãi từ sản phẩm
            String promotionIDs = "";  // Mặc định không có khuyến mãi

            // Kiểm tra các chương trình khuyến mãi áp dụng cho loại khách hàng này
            MongoCursor<Document> promoCursor = collection_Promotion.find(new Document("conditions.customerTier", tier)).iterator();
            try {
                while (promoCursor.hasNext()) {
                    Document promoDoc = promoCursor.next();
                    
                    // Kiểm tra xem chương trình khuyến mãi này có áp dụng cho sản phẩm này không
                    if (promoDoc.containsKey("appliedTo")) {
                        Document appliedToDoc = (Document) promoDoc.get("appliedTo");
                        
                        // Kiểm tra sản phẩm này có nằm trong danh sách sản phẩm áp dụng khuyến mãi không
                        if (appliedToDoc.containsKey("products")) {
                            List<String> promoProductIDs = appliedToDoc.getList("products", String.class);
                            if (promoProductIDs.contains(doc.getString("_id"))) {
                                promotionIDs = promoDoc.getString("_id");  // Lấy mã khuyến mãi
                                break;  // Dừng ngay khi tìm được khuyến mãi cho sản phẩm này
                            }
                        }
                    }
                }
            } finally {
                promoCursor.close();
            }

            // Tạo đối tượng SanPham từ dữ liệu lấy được
            SanPham product = new SanPham(
                doc.getString("_id"),
                doc.getString("productName"),
                price,
                doc.getString("categoryID"),
                promotionIDs,  // Thêm khuyến mãi nếu có
                doc.getBoolean("isPromotionProgram", false)  // Nếu không có trường này, mặc định là false
            );
            products.add(product);
        }
    } finally {
        productCursor.close();
    }

    return products;
}


    // Đóng kết nối
    public void close() {
        // Đóng kết nối nếu cần thiết
    }
}
