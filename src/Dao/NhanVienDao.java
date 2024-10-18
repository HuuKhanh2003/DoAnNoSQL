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
import org.bson.types.ObjectId;

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
                    doc.getObjectId("_id").toString(),
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
    employees.sort(Comparator.comparing(NhanVien::getNameEmployee));
    return employees;
}

    public boolean addEmployee(NhanVien employee) {
    try {
        Date bod = employee.getBod();

        Document doc = new Document()
                .append("nameEmployee", employee.getNameEmployee())
                .append("position", employee.getPosition())
                .append("phone", employee.getPhone())
                .append("gender", employee.getGender())
                .append("bod", bod);

        collection.insertOne(doc);
        System.out.println("Thêm nhân viên thành công!");
        return true;
    } catch (Exception e) {
        System.out.println("Lỗi khi thêm nhân viên: " + e.getMessage());
        return false;
    }
}

public boolean deleteEmployee(String employeeId) {
    try {
        // Chuyển đổi chuỗi employeeId thành ObjectId
        ObjectId objectId = new ObjectId(employeeId);

        // Thực hiện xóa dựa trên ObjectId
        DeleteResult result = collection.deleteOne(Filters.eq("_id", objectId));

        if (result.getDeletedCount() > 0) {
            System.out.println("Nhân viên đã được xóa thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên để xóa!");
            return false;
        }
    } catch (IllegalArgumentException e) {
        // Bắt lỗi nếu chuỗi employeeId không hợp lệ để chuyển thành ObjectId
        System.out.println("ID không hợp lệ: " + employeeId);
        return false;
    } catch (Exception e) {
        System.out.println("Lỗi khi xóa nhân viên: " + e.getMessage());
        return false;
    }
//    public boolean deleteEmployee(String employeeId) {
//        DeleteResult result = collection.deleteOne(Filters.eq("_id", employeeId));
//
//        if (result.getDeletedCount() > 0) {
//            System.out.println("Nhân viên đã được xóa thành công!");
//            return true;
//        } else {
//            System.out.println("Không tìm thấy nhân viên để xóa!");
//            return false;
//        }
//    }
}
    public boolean updateEmployee(NhanVien employee) {
        try {
        // Chuyển đổi ID của nhân viên thành ObjectId nếu cần thiết
        ObjectId objectId = new ObjectId(employee.getId());

        // Lấy Date trực tiếp từ đối tượng employee
        Date bod = employee.getBod();

        // Thực hiện update với các trường dữ liệu mới
        UpdateResult result = collection.updateOne(
                Filters.eq("_id", objectId), // Sử dụng ObjectId
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
    } catch (IllegalArgumentException e) {
        // Bắt lỗi nếu chuỗi ID không hợp lệ để chuyển thành ObjectId
        System.out.println("ID không hợp lệ: " + employee.getId());
        return false;
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
