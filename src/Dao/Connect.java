/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;



/**
 *
 * @author 84862
 */
public class Connect{
    public static MongoClient mongoClient=new MongoClient("localhost",27017);
    public static MongoDatabase database = mongoClient.getDatabase("QuanLyKhuyenMai");
    
    MongoCollection<Document> collection =database.getCollection("Account");
    public void checkConnection() {
        try {
            // Kiểm tra kết nối bằng cách lấy số lượng document trong collection
            long count = collection.countDocuments();
            System.out.println("Kết nối thành công tới MongoDB! Số lượng document trong collection 'Customer': " + count);
        } catch (MongoException ex) {
            // Nếu có lỗi xảy ra khi kết nối
            System.out.println("Kết nối không thành công. Lỗi: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Tạo đối tượng Connect và gọi phương thức checkConnection để kiểm tra
        Connect connect = new Connect();
        connect.checkConnection();
    }
}
