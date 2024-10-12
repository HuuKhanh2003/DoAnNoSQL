/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Dao.KhachHangDao;
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
public class KhachHang extends javax.swing.JPanel {

    /**
     * Creates new form NguoiKhieuNai
     */
    KhachHangDao handleKhachHang = new KhachHangDao();
    
    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public KhachHang() {
        initComponents();
        hienThi();
    }

    private void hienThi()
    {
        List<String> dsKhachHang = handleKhachHang.getAllCustomerTiers();
        for(String loaiKH:dsKhachHang)
        {
            CB_LoaiKH.addItem(loaiKH);
        }
        List<Pojo.KhachHang> ds = handleKhachHang.getAllCustomers();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã khách hàng");
        dtm.addColumn("Tên khách hàng");
        dtm.addColumn("Email");
        dtm.addColumn("Loại khách hàng");
        dtm.addColumn("Chương trình khuyến mãi");
        dtm.addColumn("Số vourcher");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dtm.setNumRows(ds.size());
        for(int i=0;i<ds.size();i++)
        {
            Pojo.KhachHang ls = ds.get(i);
            dtm.setValueAt(ls.getId(), i, 0);
            dtm.setValueAt(ls.getCustomerName(),i, 1);
            dtm.setValueAt(ls.getEmail(), i, 2);
            dtm.setValueAt(ls.getTier(), i, 3);
            dtm.setValueAt(ls.getPromotionIDs(), i, 4);
            dtm.setValueAt(ls.getVoucherQuantity(), i, 5);
        }
        tbl_KhachHang.setModel(dtm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhachHang = new javax.swing.JTable();
        txt_Ten = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Upadte = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        CB_LoaiKH = new javax.swing.JComboBox<>();
        txt_SoVour = new javax.swing.JTextField();

        jLabel1.setText("Mã khách hàng:");

        jLabel2.setText("Tên khách hàng:");

        jLabel3.setText("Khách Hàng");

        jLabel4.setText("Email:");

        jLabel5.setText("Loại khách hàng:");

        jLabel6.setText("Số voucher:");

        tbl_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KhachHang);

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Upadte.setText("Sửa");
        btn_Upadte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpadteActionPerformed(evt);
            }
        });

        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btn_Them)
                .addGap(34, 34, 34)
                .addComponent(btn_Xoa)
                .addGap(31, 31, 31)
                .addComponent(btn_Upadte)
                .addGap(51, 51, 51)
                .addComponent(btn_LamMoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(txt_SoVour, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CB_LoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(255, 255, 255)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_Ma, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(txt_Ten, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .addGap(109, 109, 109)
                            .addComponent(jLabel5)))
                    .addContainerGap(199, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(CB_LoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_SoVour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_Xoa)
                    .addComponent(btn_Upadte)
                    .addComponent(btn_LamMoi))
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)
                        .addComponent(txt_Ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(291, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhachHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_KhachHang.getSelectedRow();
        if(selectedRow != -1)
        {
            String ma = tbl_KhachHang.getValueAt(selectedRow, 0).toString();
            String ten = tbl_KhachHang.getValueAt(selectedRow, 1).toString();
            String email = tbl_KhachHang.getValueAt(selectedRow, 2).toString();
            String loaiKH = tbl_KhachHang.getValueAt(selectedRow, 3).toString();
            String soVoucher = tbl_KhachHang.getValueAt(selectedRow, 5).toString();
            
            
            txt_Ma.setText(ma);
            txt_Ten.setText(ten);
            txt_Email.setText(email);
            
            txt_SoVour.setText(soVoucher);
            CB_LoaiKH.setSelectedItem(loaiKH);
        }
    }//GEN-LAST:event_tbl_KhachHangMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        String ma = txt_Ma.getText();
        String ten= txt_Ten.getText();
        String email = txt_Email.getText();
        String soVourcher = txt_SoVour.getText();
        String loaiKH = (String) CB_LoaiKH.getSelectedItem();
        // Chuyển đổi ngày sinh từ String sang Date
        
        int voucherQuantity;
        try {
        voucherQuantity = Integer.parseInt(soVourcher);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng voucher phải là số hợp lệ!");
            return;
        }
        List<String> promotionIDs = new ArrayList<>();

        Pojo.KhachHang kh = new Pojo.KhachHang();
        kh.setId(ma);
        kh.setCustomerName(ten);
        kh.setEmail(email);
        kh.setPromotionIDs(promotionIDs);
        kh.setTier(loaiKH);
        kh.setVoucherQuantity(voucherQuantity);

        // Gọi hàm themNguoiKhieuNai để thêm vào cơ sở dữ liệu
        boolean success = handleKhachHang.addCustomer(kh);

        // Thông báo kết quả
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
            hienThi();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
         //TODO add your handling code here:
         int selectedRow = tbl_KhachHang.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa.");
            return;
        }

        // Lấy mã khách hàng từ hàng được chọn
        String ma = tbl_KhachHang.getValueAt(selectedRow, 0).toString();

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Có"
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi hàm xóa để xóa khỏi cơ sở dữ liệu
            boolean success = handleKhachHang.deleteCustomer(ma);

            // Thông báo kết quả và cập nhật bảng
            if (success) {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
                hienThi(); 
            } else {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
            }
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_UpadteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpadteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbl_KhachHang.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa.");
            return;
        }

        // Lấy dữ liệu từ các trường nhập liệu
        String ma = txt_Ma.getText();
        String ten= txt_Ten.getText();
        String email = txt_Email.getText();
        String soVourcher = txt_SoVour.getText();
        String loaiKH = (String) CB_LoaiKH.getSelectedItem();
        // Chuyển đổi ngày sinh từ String sang Date
        
        int voucherQuantity;
        try {
        voucherQuantity = Integer.parseInt(soVourcher);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng voucher phải là số hợp lệ!");
            return;
        }
        List<String> promotionIDs = new ArrayList<>();

        // Tạo đối tượng NguoiKhieuNai
        Pojo.KhachHang kh = new Pojo.KhachHang();
        kh.setId(ma);
        kh.setCustomerName(ten);
        kh.setEmail(email);
        kh.setPromotionIDs(promotionIDs);
        kh.setTier(loaiKH);
        kh.setVoucherQuantity(voucherQuantity);

        // Gọi hàm SuaNguoiKhieuNai để cập nhật vào cơ sở dữ liệu
        boolean success = handleKhachHang.updateCustomer(kh);

        // Thông báo kết quả và cập nhật bảng
        if (success) {
            JOptionPane.showMessageDialog(null, "Sửa khách hàng thành công!");
            hienThi();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại!");
        }
    }//GEN-LAST:event_btn_UpadteActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_Ma.setText("");
        txt_Ten.setText("");
        txt_Email.setText("");
        
        buttonGroup1.clearSelection();
        txt_SoVour.setText("");

    }//GEN-LAST:event_btn_LamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_LoaiKH;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Upadte;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_KhachHang;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_SoVour;
    private javax.swing.JTextField txt_Ten;
    // End of variables declaration//GEN-END:variables
}
