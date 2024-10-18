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
    MongoCursor<Document> cursor = collection.find().iterator();
    
    try {
        while (cursor.hasNext()) {
            Document doc = cursor.next();

            Date orderDate = doc.getDate("orderDate");

            // Lấy danh sách sản phẩm từ MongoDB
            List<Document> productDocs = (List<Document>) doc.get("products");
            List<DonHang.Product> products = new ArrayList<>();
            double totalAmount = 0.0;

            if (productDocs != null) {
                for (Document productDoc : productDocs) {
                    Double price = productDoc.getDouble("price");
                    if (price == null) {
                        price = 0.0;
                    }

                    Double discountedPrice = productDoc.getDouble("discountedPrice");
                    if (discountedPrice == null) {
                        discountedPrice = 0.0;
                    }

                    int quantity = productDoc.getInteger("quantity", 0);
                    totalAmount += price * quantity;

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

            DonHang order = new DonHang(
                doc.getString("_id"),
                doc.getString("customerID"),
                orderDate,
                products,
                totalAmount
            );
            orders.add(order);
        }
    } finally {
        cursor.close();
    }

    orders.sort(Comparator.comparing(DonHang::getId));

    return orders;
}

    public Pojo.DonHang getOrderById(String orderId) {
        Document query = new Document("_id", orderId);
        Document doc = collection.find(query).first();

        if (doc != null) {
            String customerID = doc.getString("customerID");
            Date orderDate = doc.getDate("orderDate");

            List<Document> productDocs = (List<Document>) doc.get("products");
            List<Pojo.DonHang.Product> products = new ArrayList<>();
            if (productDocs != null) {
                for (Document productDoc : productDocs) {
                    Double price = productDoc.getDouble("price") != null ? productDoc.getDouble("price") : 0.0;
                    Double discountedPrice = productDoc.getDouble("discountedPrice") != null ? productDoc.getDouble("discountedPrice") : 0.0;

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

            return new Pojo.DonHang(orderId, customerID, orderDate, products, doc.getDouble("totalAmount"));
        }

        return null;
    }
    public void removeProductFromOrder(String orderId, String productId) {
        Document query = new Document("_id", orderId);
        Document doc = collection.find(query).first();

        if (doc != null) {
            List<Document> productDocs = (List<Document>) doc.get("products");
            if (productDocs != null) {
                // Tìm và xóa sản phẩm theo productID
                productDocs.removeIf(productDoc -> productDoc.getString("productID").equals(productId));

                // Cập nhật danh sách sản phẩm sau khi xóa vào MongoDB
                Document update = new Document("products", productDocs);
                collection.updateOne(query, new Document("$set", update));

                System.out.println("Product removed successfully.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }
    public void updateProductInOrder(String orderId, Pojo.DonHang.Product updatedProduct) {
        Document query = new Document("_id", orderId);
        Document doc = collection.find(query).first();

        if (doc != null) {
            List<Document> productDocs = (List<Document>) doc.get("products");
            if (productDocs != null) {
                for (Document productDoc : productDocs) {
                    if (productDoc.getString("productID").equals(updatedProduct.getProductID())) {
                        // Cập nhật thông tin sản phẩm
                        productDoc.put("quantity", updatedProduct.getQuantity());
                        productDoc.put("price", updatedProduct.getPrice());
                        productDoc.put("promotionID", updatedProduct.getPromotionID());
                        productDoc.put("discountedPrice", updatedProduct.getDiscountedPrice());
                        break;
                    }
                }

                // Cập nhật danh sách sản phẩm sau khi sửa vào MongoDB
                Document update = new Document("products", productDocs);
                collection.updateOne(query, new Document("$set", update));

                System.out.println("Product updated successfully.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }
    public void updateTotalAmount(String orderId) {
        Document query = new Document("_id", orderId);
        Document doc = collection.find(query).first();

        if (doc != null) {
            List<Document> productDocs = (List<Document>) doc.get("products");
            double totalAmount = 0.0;

            if (productDocs != null) {
                for (Document productDoc : productDocs) {
                    int quantity = productDoc.getInteger("quantity");
                    double discountedPrice = productDoc.getDouble("discountedPrice");
                    totalAmount += quantity * discountedPrice;
                }
            }

            // Cập nhật tổng số tiền vào MongoDB
            Document update = new Document("totalAmount", totalAmount);
            collection.updateOne(query, new Document("$set", update));

            System.out.println("Total amount updated successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }


}
