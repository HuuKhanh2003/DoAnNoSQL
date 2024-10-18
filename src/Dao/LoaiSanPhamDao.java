/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.SanPhamDao.collection;
import Pojo.LoaiSanPham;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class LoaiSanPhamDao {
    public static MongoCollection<Document> collection;

    public LoaiSanPhamDao() {
        // Kết nối tới MongoDB
        collection = Connect.database.getCollection("Category");
    }
    public List<LoaiSanPham> getAllCategories() {
        List<LoaiSanPham> categories = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                LoaiSanPham category = new LoaiSanPham(
                        doc.getString("_id"),
                        doc.getString("categoryName"),
                        doc.getString("description")
                );
                categories.add(category);
            }
        } finally {
            cursor.close();
        }

        return categories;
    }
}
