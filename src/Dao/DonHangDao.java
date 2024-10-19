/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.DonHang;
import Pojo.SanPham;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.conversions.Bson;

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
   
   // Thêm đơn hàng mới
    public void addOrder(DonHang order) {
        Document orderDoc = new Document("_id", order.getId())
                .append("customerID", order.getCustomerID())
                .append("orderDate", order.getOrderDate())
                .append("products", createProductDocuments(order.getProducts()))
                .append("totalAmount", order.getTotalAmount());

        collection.insertOne(orderDoc);
        System.out.println("Đơn hàng đã được thêm thành công!");
    }

    // Xóa đơn hàng
    public void deleteOrder(String orderId) {
        Bson filter = Filters.eq("_id", orderId);
        collection.deleteOne(filter);
        System.out.println("Đơn hàng đã được xóa!");
    }

    // Sửa đơn hàng
    public void updateOrder(DonHang order) {
        // Bước 1: Xác định điều kiện lọc đơn hàng theo _id
        Bson filter = Filters.eq("_id", order.getId());

        // Bước 2: Chuẩn bị mảng products để đưa vào MongoDB (thêm mới, xóa cũ)
        List<Document> productDocuments = new ArrayList<>();
        for (DonHang.Product product : order.getProducts()) {
            Document productDoc = new Document()
                .append("productID", product.getProductID())
                .append("quantity", product.getQuantity())
                .append("price", product.getPrice())
                .append("promotionID", product.getPromotionID())
                .append("discountedPrice", product.getDiscountedPrice());
            productDocuments.add(productDoc);
        }

        // Bước 3: Chuẩn bị bản cập nhật cho đơn hàng
        Bson update = Updates.combine(
            Updates.set("customerID", order.getCustomerID()),
            Updates.set("orderDate", order.getOrderDate()),
            Updates.set("products", productDocuments), 
            Updates.set("totalAmount", order.getTotalAmount())
        );

        // Bước 4: Thực hiện cập nhật đơn hàng trong MongoDB
        collection.updateOne(filter, update);
        System.out.println("Đơn hàng đã được cập nhật!");
    }



    private List<Document> createProductDocuments(List<DonHang.Product> products) {
        List<Document> productDocs = new ArrayList<>();
        for (DonHang.Product product : products) {
            Document productDoc = new Document("productID", product.getProductID())
                    .append("quantity", product.getQuantity())
                    .append("price", product.getPrice())
                    .append("promotionID", product.getPromotionID())
                    .append("discountedPrice", product.getDiscountedPrice());

            productDocs.add(productDoc);
        }
        return productDocs;
    }

    public List<Object[]> getAllRowsFromTable(JTable table) {
        List<Object[]> rowDataList = new ArrayList<>();

        // Lấy TableModel từ JTable
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Duyệt qua tất cả các hàng trong bảng
        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            // Tạo mảng chứa dữ liệu của từng hàng
            Object[] rowData = new Object[model.getColumnCount()];

            // Duyệt qua tất cả các cột của hàng hiện tại
            for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                // Lấy giá trị của ô (cell) tại vị trí rowIndex, colIndex
                rowData[colIndex] = model.getValueAt(rowIndex, colIndex);
            }

            // Thêm hàng vào danh sách
            rowDataList.add(rowData);
        }
    
        return rowDataList;
    }
}
