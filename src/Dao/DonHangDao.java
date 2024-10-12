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
                String orderDateString = doc.getString("orderDate");
                Date orderDate = null;
                if (orderDateString != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    orderDate = dateFormat.parse(orderDateString);
                }

                // Lấy danh sách sản phẩm từ MongoDB
                List<Document> productDocs = (List<Document>) doc.get("products");
                List<DonHang.Product> products = new ArrayList<>();

                if (productDocs != null) {
                    for (Document productDoc : productDocs) {
                        DonHang.Product product = new DonHang.Product(
                                productDoc.getString("productID"),
                                productDoc.getInteger("quantity"),
                                productDoc.getDouble("price"),
                                productDoc.getString("promotionID"),
                                productDoc.getDouble("discountedPrice"),
                                productDoc.getDouble("totalAmount")
                        );
                        products.add(product);
                    }
                }

                DonHang order = new DonHang(
                        doc.getString("_id"),
                        doc.getString("customerID"),
                        orderDate,
                        products
                );
                orders.add(order);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orders.sort(Comparator.comparing(DonHang::getId));
        return orders; // Trả về danh sách tất cả đơn hàng
    }

}
