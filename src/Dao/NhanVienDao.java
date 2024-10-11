/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.KhachHangDao.collection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import Pojo.Employee;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author HUU KHANH
 */
public class NhanVienDao {
    public static MongoCollection<Document> collection;
    
    public NhanVienDao() {
        collection = Connect.database.getCollection("Employee");
    }
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Employee employee = new Employee(
                        doc.getString("_id"),
                        doc.getString("nameEmployee"),
                        doc.getString("position"),
                        doc.getString("phone"),
                        doc.getString("gender"),
                        doc.getDate("bod")
                );
                employees.add(employee);
            }
        }
        employees.sort(Comparator.comparing(Employee::getId));
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        try {
            Document doc = new Document("_id", employee.getId())
                    .append("nameEmployee", employee.getNameEmployee())
                    .append("position", employee.getPosition())
                    .append("phone", employee.getPhone())
                    .append("gender", employee.getGender())
                    .append("bod", employee.getBod()); // bod phải là Date
            collection.insertOne(doc);
            System.out.println("Thêm nhân viên thành công!");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(String employeeId) {
        // Sử dụng câu lệnh xóa
        DeleteResult result = collection.deleteOne(Filters.eq("_id", employeeId));

        // Kiểm tra xem có tài liệu nào được xóa không
        if (result.getDeletedCount() > 0) {
            System.out.println("Nhân viên đã được xóa thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên để xóa!");
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {
        // Sử dụng câu lệnh cập nhật
        UpdateResult result = collection.updateOne(
                Filters.eq("_id", employee.getId()),
                Updates.combine(
                        Updates.set("nameEmployee", employee.getNameEmployee()),
                        Updates.set("position", employee.getPosition()),
                        Updates.set("phone", employee.getPhone()),
                        Updates.set("gender", employee.getGender()),
                        Updates.set("bod", employee.getBod()) // bod phải là Date
                )
        );

        // Kiểm tra xem có tài liệu nào được cập nhật không
        if (result.getModifiedCount() > 0) {
            System.out.println("Cập nhật nhân viên thành công!");
            return true; // Trả về true nếu cập nhật thành công
        } else {
            System.out.println("Không tìm thấy nhân viên để cập nhật!");
            return false; // Trả về false nếu không tìm thấy nhân viên
        }
    }

    public List<String> getEmployeePosition() {
        List<String> positions = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String position = doc.getString("position");

                if (position != null && !positions.contains(position)) {
                    positions.add(position); // Thêm vào danh sách
                }
            }
        }
        return positions; // Trả về danh sách vị trí nhân viên
    }

    public List<String> getEmployeeGender() {
        List<String> genders = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String gender = doc.getString("gender");

                if (gender != null && !genders.contains(gender)) {
                    genders.add(gender); // Thêm vào danh sách
                }
            }
        }
        return genders; // Trả về danh sách giới tính nhân viên
    }
}
