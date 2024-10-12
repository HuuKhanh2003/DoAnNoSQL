/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.KhachHangDao.collection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import Pojo.NhanVien;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author HUU KHANH
 */
public class NhanVienDao {
    public static MongoCollection<Document> collection;
    
    public NhanVienDao() {
        collection = Connect.database.getCollection("Employee");
    }
    
    public List<NhanVien> getAllEmployees() {
    List<NhanVien> employees = new ArrayList<>();
    MongoCursor<Document> cursor = collection.find().iterator();
    
    try {
        while (cursor.hasNext()) {
            Document doc = cursor.next();
//            String bodString = doc.getString("bod"); 
//            Date bod = null;
//            if (bodString != null) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                bod = dateFormat.parse(bodString);
//            }
            Date bod = doc.getDate("bod");
            NhanVien employee = new NhanVien(
                    doc.getString("_id"),
                    doc.getString("nameEmployee"),
                    doc.getString("position"),
                    doc.getString("phone"),
                    doc.getString("gender"),
                    bod
            );
            employees.add(employee);
        }
    } finally{
        cursor.close();
    }
    employees.sort(Comparator.comparing(NhanVien::getId));
    return employees;
}

    public boolean addEmployee(NhanVien employee) {
    try {
        // Không cần chuyển đổi Date sang String, lưu trực tiếp Date vào MongoDB
        Date bod = employee.getBod();

        Document doc = new Document("_id", employee.getId())
                .append("nameEmployee", employee.getNameEmployee())
                .append("position", employee.getPosition())
                .append("phone", employee.getPhone())
                .append("gender", employee.getGender())
                .append("bod", bod); // Lưu trực tiếp kiểu Date

        collection.insertOne(doc);
        System.out.println("Thêm nhân viên thành công!");
        return true;
    } catch (Exception e) {
        System.out.println("Lỗi khi thêm nhân viên: " + e.getMessage());
        return false;
    }
}


    public boolean deleteEmployee(String employeeId) {
        DeleteResult result = collection.deleteOne(Filters.eq("_id", employeeId));

        if (result.getDeletedCount() > 0) {
            System.out.println("Nhân viên đã được xóa thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên để xóa!");
            return false;
        }
    }

    public boolean updateEmployee(NhanVien employee) {
    try {
        // Lấy Date trực tiếp từ đối tượng employee
        Date bod = employee.getBod();

        UpdateResult result = collection.updateOne(
                Filters.eq("_id", employee.getId()),
                Updates.combine(
                        Updates.set("nameEmployee", employee.getNameEmployee()),
                        Updates.set("position", employee.getPosition()),
                        Updates.set("phone", employee.getPhone()),
                        Updates.set("gender", employee.getGender()),
                        Updates.set("bod", bod) // Lưu trực tiếp kiểu Date
                )
        );

        if (result.getModifiedCount() > 0) {
            System.out.println("Cập nhật nhân viên thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên để cập nhật!");
            return false;
        }
    } catch (Exception e) {
        System.out.println("Lỗi khi cập nhật nhân viên: " + e.getMessage());
        return false; 
    }
}


    public List<String> getEmployeePosition() {
        List<String> positions = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String position = doc.getString("position");

                if (position != null && !positions.contains(position)) {
                    positions.add(position);
                }
            }
        }
        return positions;
    }

    public List<String> getEmployeeGender() {
        List<String> genders = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String gender = doc.getString("gender");

                if (gender != null && !genders.contains(gender)) {
                    genders.add(gender);
                }
            }
        }
        return genders;
    }
}
