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

    public SanPhamDao() {
        // Kết nối tới MongoDB
        collection = Connect.database.getCollection("Product");
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

    // Đóng kết nối
    public void close() {
        // Đóng kết nối nếu cần thiết
    }
}
