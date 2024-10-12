/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.DonHang;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
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

                // Phân tích ngày đặt hàng
                String orderDateString = doc.getString("orderDate");
                Date orderDate = null;
                if (orderDateString != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    orderDate = dateFormat.parse(orderDateString);
                }

                // Lấy danh sách sản phẩm từ MongoDB
                List<Document> productDocs = (List<Document>) doc.get("products");
                List<DonHang.Product> products = new ArrayList<>();

                double totalAmount = 0.0; // Khởi tạo tổng số tiền

                if (productDocs != null) {
                    for (Document productDoc : productDocs) {
                        // Lấy giá trị và kiểm tra null
                        Double price = productDoc.getDouble("price");
                        if (price == null) {
                            price = 0.0; // Giá trị mặc định
                        }

                        Double discountedPrice = productDoc.getDouble("discountedPrice");
                        if (discountedPrice == null) {
                            discountedPrice = 0.0; // Giá trị mặc định
                        }

                        int quantity = productDoc.getInteger("quantity", 0); // Giá trị mặc định là 0
                        totalAmount += price * quantity; // Tính tổng số tiền

                        // Tạo sản phẩm
                        DonHang.Product product = new DonHang.Product(
                            productDoc.getString("productID"),
                            quantity,
                            price,
                            productDoc.getString("promotionID"),
                            discountedPrice
                        );
                        products.add(product);
                    }
                }

                // Tạo đối tượng DonHang và thêm vào danh sách
                DonHang order = new DonHang(
                    doc.getString("_id"),
                    doc.getString("customerID"),
                    orderDate,
                    products,
                    totalAmount // Gán tổng số tiền
                );
                orders.add(order);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Sắp xếp danh sách đơn hàng theo ID
        orders.sort(Comparator.comparing(DonHang::getId));

        return orders; // Trả về danh sách tất cả đơn hàng
    }

    public Pojo.DonHang getOrderById(String orderId) {
        Document query = new Document("_id", orderId);
        Document doc = collection.find(query).first();
        
        if (doc != null) {
            // Parse các thông tin từ Document
            String customerID = doc.getString("customerID");
            String orderDateString = doc.getString("orderDate");
            Date orderDate = null;
            if (orderDateString != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    orderDate = dateFormat.parse(orderDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // Lấy danh sách sản phẩm
            List<Document> productDocs = (List<Document>) doc.get("products");
            List<Pojo.DonHang.Product> products = new ArrayList<>();
            if (productDocs != null) {
                for (Document productDoc : productDocs) {
                    // Lấy giá trị và kiểm tra null
                    Double price = productDoc.getDouble("price") != null ? productDoc.getDouble("price") : 0.0;
                    Double discountedPrice = productDoc.getDouble("discountedPrice") != null ? productDoc.getDouble("discountedPrice") : 0.0;

                    // Tạo sản phẩm
                    Pojo.DonHang.Product product = new Pojo.DonHang.Product(
                        productDoc.getString("productID"),
                        productDoc.getInteger("quantity"),
                        price,
                        productDoc.getString("promotionID"),
                        discountedPrice
                    );
                    products.add(product);
                }
            }

            // Tạo và trả về đối tượng DonHang
            return new Pojo.DonHang(orderId, customerID, orderDate, products, doc.getDouble("totalAmount"));
        }

        return null; // Nếu không tìm thấy đơn hàng
    }

}
