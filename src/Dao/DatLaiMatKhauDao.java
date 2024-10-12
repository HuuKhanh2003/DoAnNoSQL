/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.Connect.database;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author 84862
 */
public class DatLaiMatKhauDao {
     public static MongoCollection<Document> collection;
    public DatLaiMatKhauDao()
    {
        collection = database.getCollection("Account");
    }
    
     public boolean updateAccount(String username, String newPassword) {
        try {
            // Tìm tài khoản theo username
            Document account = collection.find(eq("username", username)).first();
            if (account != null) {
                collection.updateOne(
                        eq("username", username),
                        new Document("$set", new Document("password", hashPassword(newPassword)))
                );
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     
    public String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }
}
