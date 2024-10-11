/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Dao.NhanVienDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUU KHANH
 */
public class NhanVien extends javax.swing.JPanel {

    /**
     * Creates new form NhanVien
     */
    NhanVienDao handleEmployee = new NhanVienDao();
    public NhanVien() {
        initComponents();
        hienThi();
    }
    
    private void hienThi() {
    List<String> listPosition = handleEmployee.getEmployeePosition();
    for (String position : listPosition) {
        cbx_position.addItem(position);
    }

    List<String> listGender = handleEmployee.getEmployeeGender();
    for (String gender : listGender) {
        cbx_gender.addItem(gender);
    }

    List<Pojo.Employee> ds = handleEmployee.getAllEmployees();
    DefaultTableModel dtm = new DefaultTableModel();
    dtm.addColumn("Mã nhân viên");
    dtm.addColumn("Tên nhân viên");
    dtm.addColumn("Vị trí");
    dtm.addColumn("SĐT");
    dtm.addColumn("Giới tính");
    dtm.addColumn("Ngày sinh");
    dtm.setNumRows(ds.size());
    // Định dạng ngày
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    for(int i=0;i<ds.size();i++)
        {
            Pojo.Employee ls = ds.get(i);
            dtm.setValueAt(ls.getId(), i, 0);
            dtm.setValueAt(ls.getNameEmployee(),i, 1);
            dtm.setValueAt(ls.getPosition(), i, 2);
            dtm.setValueAt(ls.getPhone(), i, 3);
            dtm.setValueAt(ls.getGender(), i, 4);
            //dtm.setValueAt((ls.getBod() != null) ? dateFormat.format(ls.getBod()) : "", i, 5);
        }
    
    tbl_Employee.setModel(dtm);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_idEmployee = new javax.swing.JTextField();
        txt_nameEmployee = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        cbx_position = new javax.swing.JComboBox<>();
        cbx_gender = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Employee = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        txt_bod = new javax.swing.JFormattedTextField();

        jLabel1.setText("Nhân viên");

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Phone");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Chức vụ");

        jLabel7.setText("Ngày sinh");

        tbl_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Employee);

        btn_Add.setText("Thêm");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Delete.setText("Xóa");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Update.setText("Sửa");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Clear.setText("Làm mới");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        txt_bod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nameEmployee))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_idEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(31, 31, 31)
                        .addComponent(cbx_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(220, 220, 220))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_bod, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btn_Add)
                        .addGap(32, 32, 32)
                        .addComponent(btn_Delete)
                        .addGap(37, 37, 37)
                        .addComponent(btn_Update)
                        .addGap(37, 37, 37)
                        .addComponent(btn_Clear)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txt_idEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txt_nameEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cbx_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Add)
                    .addComponent(btn_Delete)
                    .addComponent(btn_Update)
                    .addComponent(btn_Clear))
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        String id = txt_idEmployee.getText().trim();
    String name = txt_nameEmployee.getText().trim();
    String position = (String) cbx_position.getSelectedItem();
    String phone = txt_phone.getText().trim();
    String gender = (String) cbx_gender.getSelectedItem();
    String bod = txt_bod.getText().trim();

    // Kiểm tra các trường đầu vào không được để trống
    if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || bod.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
        return;
    }

    // Khởi tạo đối tượng Employee và thiết lập các giá trị cho nó
    Pojo.Employee employee = new Pojo.Employee();
    employee.setId(id);
    employee.setNameEmployee(name);
    employee.setPosition(position);
    employee.setPhone(phone);
    employee.setGender(gender);

    // Chuyển đổi chuỗi ngày tháng thành Date trước khi gán vào employee
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng phù hợp với dữ liệu
    dateFormat.setLenient(false); // Đặt chế độ kiểm tra nghiêm ngặt
    try {
        Date birthDate = dateFormat.parse(bod);
        employee.setBod(birthDate);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
        return;
    }

    // Gọi hàm addEmployee để thêm nhân viên vào cơ sở dữ liệu
    boolean success = handleEmployee.addEmployee(employee);

    // Thông báo kết quả
    if (success) {
        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
        hienThi(); // Cập nhật lại bảng hoặc giao diện
    } else {
        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
    }
    }//GEN-LAST:event_btn_AddActionPerformed

    private void tbl_EmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EmployeeMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_Employee.getSelectedRow();
        if (selectedRow != -1) {
            String id = tbl_Employee.getValueAt(selectedRow, 0).toString();
            String name = tbl_Employee.getValueAt(selectedRow, 1).toString();
            String position = tbl_Employee.getValueAt(selectedRow, 2).toString();
            String phone = tbl_Employee.getValueAt(selectedRow, 3).toString();
            String gender = tbl_Employee.getValueAt(selectedRow, 4).toString();
            String bodString = tbl_Employee.getValueAt(selectedRow, 5).toString();

            // Cập nhật thông tin vào các trường nhập liệu
            txt_idEmployee.setText(id);
            txt_nameEmployee.setText(name);
            cbx_position.setSelectedItem(position);
            txt_phone.setText(phone);
            cbx_gender.setSelectedItem(gender);

            // Chuyển đổi và đặt giá trị cho JFormattedTextField
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date bod = dateFormat.parse(bodString); // Chuyển đổi chuỗi thành Date
                txt_bod.setValue(bod); // Đặt giá trị cho JFormattedTextField
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
                e.printStackTrace(); // Ghi lại thông tin lỗi
            }
        }
    }//GEN-LAST:event_tbl_EmployeeMouseClicked

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbl_Employee.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa.");
            return;
        }

        // Lấy mã nhân viên từ hàng được chọn
        String id = tbl_Employee.getValueAt(selectedRow, 0).toString();

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi hàm xóa để xóa khỏi cơ sở dữ liệu
            boolean success = handleEmployee.deleteEmployee(id);
            // Thông báo kết quả
            JOptionPane.showMessageDialog(null, success ? "Xóa nhân viên thành công!" : "Xóa nhân viên thất bại!");
            if (success) {
                hienThi(); // Cập nhật bảng
            }
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbl_Employee.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa.");
            return;
        }

        // Lấy dữ liệu từ các trường nhập liệu
        String id = txt_idEmployee.getText().trim();
        String name = txt_nameEmployee.getText().trim();
        String position = (String) cbx_position.getSelectedItem();
        String phone = txt_phone.getText().trim();
        String gender = (String) cbx_gender.getSelectedItem();

        // Kiểm tra ngày sinh
        Date bod;
        try {
            bod = (Date) txt_bod.getValue(); // Giá trị trong JFormattedTextField
            if (bod == null) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
                return;
            }
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
            return;
            
        }
        // Tạo đối tượng Employee
        Pojo.Employee employee = new Pojo.Employee(id, name, position, phone, gender, bod);

        // Gọi hàm updateEmployee để cập nhật vào cơ sở dữ liệu
        boolean success = handleEmployee.updateEmployee(employee);
        // Thông báo kết quả
        JOptionPane.showMessageDialog(null, success ? "Sửa thông tin nhân viên thành công!" : "Sửa thông tin nhân viên thất bại!");
        if (success) {
            hienThi(); // Cập nhật bảng
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
         txt_idEmployee.setText("");
        txt_nameEmployee.setText(""); 
        txt_phone.setText(""); 
        txt_bod.setValue(null); 
        cbx_position.setSelectedIndex(-1); 
        cbx_gender.setSelectedIndex(-1);
    }//GEN-LAST:event_btn_ClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> cbx_gender;
    private javax.swing.JComboBox<String> cbx_position;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Employee;
    private javax.swing.JFormattedTextField txt_bod;
    private javax.swing.JTextField txt_idEmployee;
    private javax.swing.JTextField txt_nameEmployee;
    private javax.swing.JTextField txt_phone;
    // End of variables declaration//GEN-END:variables
}
