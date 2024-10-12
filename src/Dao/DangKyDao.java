/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import static Dao.Connect.database;
import static Dao.Connect.mongoClient;
import static Dao.DangNhapDao.collection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
/**
 *
 * @author 84862
 */
public class DangKyDao {
    public static MongoCollection<Document> collection;
    
    public DangKyDao() {
        // Kết nối tới MongoDB
        collection = database.getCollection("Account");
    }
    public boolean isUsernameTaken(MongoCollection<Document> collection, String username) {
        Document query = new Document("username", username);
        Document account = collection.find(query).first();
        return account != null;
    }

   public boolean registerAccount(MongoCollection<Document> collection,String email, String username, String password) {
    if (isUsernameTaken(collection, username)) {
        return false;
    }

    String hashedPassword = hashPassword(password);

    Document newAccount = new Document("username", username)
                          .append("password", hashedPassword)
                          .append("role","employee")
                          .append("email", email);
    collection.insertOne(newAccount);
    
    return true;
}
    public String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

}
