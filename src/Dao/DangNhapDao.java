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
    
    public boolean login(MongoCollection<Document> collection, String username, String password) 
    {
        // Tạo tài liệu truy vấn với username
        Document query = new Document("username", username);

        // Tìm tài liệu tương ứng
        Document account = collection.find(query).first();

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        String storedHashedPassword = account.getString("password");

        String hashedPassword = hashPassword(password);

        if (storedHashedPassword.equals(hashedPassword)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid password.");
            return false;
        }
    }
    public String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }
    
    public String getRoleByUsernameAndPassword(String username, String password) {
        // Tạo query để tìm tài khoản với username và password
        String hashPass=hashPassword(password);
        Document query = new Document("username", username).append("password", hashPass);
        
        // Tìm tài khoản trong collection
        Document account = collection.find(query).first();
        
        // Nếu tìm thấy tài khoản, lấy thông tin role
        if (account != null) {
            return account.getString("role"); // Giả sử "role" là trường lưu quyền của tài khoản
        }
        
        return null;
    }
}
