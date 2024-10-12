/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.DonHang;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HUU KHANH
 */
public class DonHangDao {
    public static MongoCollection<Document> collection;
    public DonHangDao() {
        // Kết nối tới MongoDB
        collection = Connect.database.getCollection("Order");
    }
    public List<DonHang> getAllOrders() {
        List<DonHang> orders = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                String id = doc.getString("_id");
                String customerID = doc.getString("customerID");
                Date orderDate = doc.getDate("orderDate");
                List<DonHang.Product> products = new ArrayList<>();

                // Lấy danh sách sản phẩm
                List<Document> productDocs = (List<Document>) doc.get("products");
                for (Document productDoc : productDocs) {
                    String productID = productDoc.getString("productID");
                    int quantity = productDoc.getInteger("quantity");
                    double price = productDoc.getDouble("price");
                    String promotionID = productDoc.getString("promotionID");
                    double discountedPrice = productDoc.getDouble("discountedPrice");

                    // Tính toán tổng tiền cho sản phẩm
                    double totalAmount = quantity * discountedPrice;

                    // Tạo đối tượng Product và thêm vào danh sách
                    DonHang.Product product = new DonHang.Product(productID, quantity, price, promotionID, discountedPrice, totalAmount);
                    products.add(product);
                }

                // Tạo đối tượng DonHang và thêm vào danh sách
                DonHang order = new DonHang(id, customerID, orderDate, products);
                orders.add(order);
            }
        }
        return orders; // Trả về danh sách tất cả đơn hàng
    }
}
