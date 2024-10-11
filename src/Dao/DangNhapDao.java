/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.Connect.database;
import static Dao.Connect.mongoClient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class DangNhapDao {

    public static MongoCollection<Document> collection;
    public DangNhapDao() {
        // Kết nối tới MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("QuanLyKhuyenMai");
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
}
