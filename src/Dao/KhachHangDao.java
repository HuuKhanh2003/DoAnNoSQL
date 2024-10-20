/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Pojo.KhachHang;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class KhachHangDao {
    public static MongoCollection<Document> collection;

    public KhachHangDao() {
        // Kết nối tới MongoDB
        collection = Connect.database.getCollection("Customer");
    }
    public List<KhachHang> getAllCustomers() {
        List<KhachHang> customers = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                KhachHang customer = new KhachHang(
                        doc.getString("_id"),
                        doc.getString("customerName"),
                        doc.getString("email"),
                        doc.getString("tier"),
                        
                        doc.getInteger("voucherQuantity")
                );
                customers.add(customer);
            }
        } finally {
            cursor.close();
        }
         // Sắp xếp danh sách khách hàng theo mã (theo _id)
        customers.sort(Comparator.comparing(KhachHang::getId));

        return customers;
    }
    public List<KhachHang> getCustomersByName(String name) {
    // Truy vấn trực tiếp và chuyển đổi kết quả
    return collection.find(new Document("customerName", new Document("$regex", name).append("$options", "i")))
            .map(doc -> new KhachHang(
                    doc.getString("_id"),
                    doc.getString("customerName"),
                    doc.getString("email"),
                    doc.getString("tier"),
                    
                    doc.getInteger("voucherQuantity")))
            .into(new ArrayList<>());
}
        public List<String> getCustomerTiersByID(String id) {
        // Truy vấn trực tiếp và chuyển đổi kết quả
        List<String> tierList = new ArrayList<>();

        collection.find(new Document("tier", new Document("$regex", id).append("$options", "i")))
            .forEach(doc -> {
                String customerTier = doc.getString("tier");
                if (!tierList.contains(customerTier)) {
                    tierList.add(customerTier); // Thêm vào danh sách nếu chưa có
                }
            });

        return tierList;
    }

    // Hàm thêm khách hàng
    public boolean addCustomer(KhachHang customer) {
        try {
            // Tạo tài liệu từ thông tin khách hàng
            Document doc = new Document("_id", customer.getId())
                    .append("customerName", customer.getCustomerName())
                    .append("email", customer.getEmail())
                    .append("tier", customer.getTier())
                    
                    .append("voucherQuantity", customer.getVoucherQuantity());

            // Thêm tài liệu vào collection
            collection.insertOne(doc);
            System.out.println("Khách hàng đã được thêm thành công!");
            return true;  // Thêm thành công
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            return false;  // Thêm thất bại
        }
    }


    // Hàm xóa khách hàng theo ID
    public boolean deleteCustomer(String customerId) {
        // Sử dụng câu lệnh xóa
        DeleteResult result = collection.deleteOne(Filters.eq("_id", customerId));

        // Kiểm tra xem có tài liệu nào được xóa không
        if (result.getDeletedCount() > 0) {
            System.out.println("Khách hàng đã được xóa thành công!");
            return true; // Trả về true nếu xóa thành công
        } else {
            System.out.println("Không tìm thấy khách hàng để xóa!");
            return false; // Trả về false nếu không tìm thấy khách hàng
        }
    }


    // Hàm sửa thông tin khách hàng
    public boolean updateCustomer(KhachHang customer) {
    // Sử dụng câu lệnh cập nhật
        UpdateResult result = collection.updateOne(
            Filters.eq("_id", customer.getId()),
            Updates.combine(
                Updates.set("customerName", customer.getCustomerName()),
                Updates.set("email", customer.getEmail()),
                Updates.set("tier", customer.getTier()),
                
                Updates.set("voucherQuantity", customer.getVoucherQuantity())
            )
        );

        // Kiểm tra xem có tài liệu nào được cập nhật không
        if (result.getModifiedCount() > 0) {
            System.out.println("Khách hàng đã được cập nhật thành công!");
            return true; // Trả về true nếu cập nhật thành công
        } else {
            System.out.println("Không tìm thấy khách hàng để cập nhật!");
            return false; // Trả về false nếu không tìm thấy khách hàng
        }
    }

    public List<String> getAllCustomerTiers() {
        List<String> tiers = new ArrayList<>();

        // Thực hiện truy vấn để lấy tất cả khách hàng
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Lấy giá trị tier từ tài liệu (document)
                String tier = doc.getString("tier");

                // Kiểm tra nếu giá trị tier không null và chưa có trong danh sách tiers
                if (tier != null && !tiers.contains(tier)) {
                    tiers.add(tier); // Thêm vào danh sách
                }
            }
        } finally {
            cursor.close();
        }

        return tiers; // Trả về danh sách loại khách hàng
    }
    public List<String> getCustomerID() {
        List<String> ids = new ArrayList<>();

        // Thực hiện truy vấn để lấy tất cả khách hàng
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Lấy giá trị tier từ tài liệu (document)
                String id = doc.getString("_id");

                // Kiểm tra nếu giá trị tier không null và chưa có trong danh sách tiers
                if (id != null && !ids.contains(id)) {
                    ids.add(id); // Thêm vào danh sách
                }
            }
        } finally {
            cursor.close();
        }

        return ids; // Trả về danh sách loại khách hàng
    }
    public List<String> getAllCustomerByName() {
        List<String> names = new ArrayList<>();

        // Thực hiện truy vấn để lấy tất cả khách hàng
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Lấy giá trị tier từ tài liệu (document)
                String name = doc.getString("customerName");

                // Kiểm tra nếu giá trị tier không null và chưa có trong danh sách tiers
                if (name != null && !names.contains(name)) {
                    names.add(name); // Thêm vào danh sách
                }
            }
        } finally {
            cursor.close();
        }

        return names; // Trả về danh sách loại khách hàng
    }
    // Đóng kết nối
    public void close() {
        // Đóng kết nối nếu cần thiết
    }
    
    public String getCustomerTiersByID(String customerID) {
        // Truy vấn và lấy tên mức độ khách hàng duy nhất từ cơ sở dữ liệu
        Document query = new Document("_id", customerID); // Điều kiện lọc theo customerID
        Document customerDoc = collection.find(query).first(); // Lấy một tài liệu đầu tiên khớp với customerID

        if (customerDoc != null) {
            return customerDoc.getString("tier"); // Lấy giá trị của trường "tier"
        } else {
            return null; // Nếu không tìm thấy khách hàng nào, trả về null
        }
    }

}
