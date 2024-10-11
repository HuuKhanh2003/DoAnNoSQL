/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.Connect;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import static Dao.Connect.mongoClient; // Import mongoClient from Connect class
import static Dao.Connect.database; 
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class DangNhapDao {

    public static MongoCollection<Document> collection;
    public DangNhapDao() {
        // Kết nối tới MongoDB
        collection = database.getCollection("Account");
    }

    // Hàm đăng nhập
    public static String dangNhap(String username, String password) {
        // Tìm tài khoản với username và password khớp
        Document query = new Document("username", username).append("password", password);
        Document account = collection.find(query).first();

        // Kiểm tra nếu tài khoản tồn tại
        if (account != null) {
            String role = account.getString("role");
            System.out.println("Đăng nhập thành công! Vai trò của bạn là: " + role);
            return role;
        } else {
            System.out.println("Thông tin đăng nhập không đúng.");
            return null;
        }
    }

    // Đóng kết nối
    public void close() {
        mongoClient.close();
    }
}
