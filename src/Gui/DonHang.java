/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Dao.DonHangDao;
import Dao.KhachHangDao;
import Dao.SanPhamDao;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AD
 */
public class DonHang extends javax.swing.JPanel {

    /**
     * Creates new form DonHang
     */
    DonHangDao handleDonHang = new DonHangDao();
    KhachHangDao handleKhachHang=new KhachHangDao();
    SanPhamDao handleSanPham=new SanPhamDao();
    public DonHang() {
        initComponents();
        hienThiDonHang();
        hienThiDSSanPham();
        List<String> dsKhachHang = handleKhachHang.getCustomerID();
        for(String KH:dsKhachHang)
        {
            CB_KH.addItem(KH);
        }
        
        
        
        
    tbl_DonHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbl_DonHang.getSelectedRow();
                if (selectedRow >= 0) {
                    String selectedOrderId = tbl_DonHang.getValueAt(selectedRow, 0).toString();
                    hienThiChiTietDonHang(selectedOrderId);
                }
            }
        }
    });
    }

    private void hienThiDonHang() {
        List<Pojo.DonHang> dsDonHang = handleDonHang.getAllOrders();
        DefaultTableModel dtm = new DefaultTableModel();

        // Thêm các cột cho bảng đơn hàng
        dtm.addColumn("Mã đơn hàng");
        dtm.addColumn("Mã khách hàng");
        dtm.addColumn("Ngày đặt hàng");
        dtm.addColumn("Tổng tiền");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Pojo.DonHang donHang : dsDonHang) {
            dtm.addRow(new Object[]{
                donHang.getId(),
                donHang.getCustomerID(),
                dateFormat.format(donHang.getOrderDate()),
                donHang.getTotalAmount()
            });
        }

        // Thiết lập model cho bảng đơn hàng
        tbl_DonHang.setModel(dtm);
    }

    private void hienThiChiTietDonHang(String orderId) {
        Pojo.DonHang donHang = handleDonHang.getOrderById(orderId); // Lấy thông tin chi tiết đơn hàng

        DefaultTableModel dtmChiTiet = new DefaultTableModel();
        dtmChiTiet.addColumn("Mã sản phẩm");
        dtmChiTiet.addColumn("Số lượng");
        dtmChiTiet.addColumn("Giá");
        dtmChiTiet.addColumn("Mã khuyến mãi");
        dtmChiTiet.addColumn("Giá khuyến mãi");
        dtmChiTiet.addColumn("Thành tiền"); // Thêm cột Thành tiền

        if (donHang != null && donHang.getProducts() != null) {
            for (Pojo.DonHang.Product product : donHang.getProducts()) {
                double thanhTien = product.getQuantity() * product.getPrice(); // Tính Thành tiền
                dtmChiTiet.addRow(new Object[]{
                    product.getProductID(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getPromotionID(),
                    product.getDiscountedPrice(),
                    thanhTien // Thêm Thành tiền vào bảng
                });
            }
        }

        // Thiết lập model cho bảng chi tiết đơn hàng
        tbl_ChiTietDonHang.setModel(dtmChiTiet);
    }
    
    private void hienThiDSSanPham() {
        List<Pojo.SanPham> dsSanPham = handleSanPham.getAllProducts();
        DefaultTableModel dtm = new DefaultTableModel();

        // Thêm các cột cho bảng đơn hàng
        dtm.addColumn("Mã sản phẩm");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Loại sản phẩm");
        dtm.addColumn("Giá");
        for (Pojo.SanPham sanPham : dsSanPham) {
            dtm.addRow(new Object[]{
                sanPham.getId(),
                sanPham.getProductName(),
                sanPham.getCategoryID(),
                sanPham.getPrice()
            });
        }

        // Thiết lập model cho bảng đơn hàng
        tbl_DonHang.setModel(dtm);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DonHang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ChiTietDonHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();
        txt_TongTien = new javax.swing.JTextField();
        txt_NgayLap = new javax.swing.JFormattedTextField();
        CB_KH = new javax.swing.JComboBox<>();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_SoLuong = new javax.swing.JTextField();
        txt_Gia = new javax.swing.JTextField();
        txt_KhuyenMai = new javax.swing.JTextField();
        txt_GiaGiam = new javax.swing.JTextField();
        btn_SuaSP = new javax.swing.JButton();
        btn_XoaSP = new javax.swing.JButton();
        btn_ThemSP = new javax.swing.JButton();
        txt_MaSP = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Đơn hàng");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 19, -1, -1));

        tbl_DonHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_DonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DonHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DonHang);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 122, 624, 187));

        tbl_ChiTietDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Số lượng", "Giá", "Mã khuyến mãi", "Giá khuyến mãi", "Thành tiền"
            }
        ));
        tbl_ChiTietDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietDonHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ChiTietDonHang);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 419, 103));

        jLabel2.setText("Mã đơn hàng:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 51, -1, -1));

        jLabel3.setText("Ngày lập:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

        jLabel4.setText("Khách hàng:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 51, -1, -1));

        jLabel5.setText("Tổng tiền:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 85, -1, -1));
        add(txt_Ma, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 48, 165, -1));
        add(txt_TongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 160, -1));
        add(txt_NgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 82, 165, -1));

        add(CB_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 48, 163, -1));

        btn_Them.setText("Thêm");
        add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 315, -1, -1));

        btn_Xoa.setText("Xóa");
        add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 315, -1, -1));

        btn_Sua.setText("Sửa");
        add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 315, -1, -1));

        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });
        add(btn_LamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 315, -1, -1));

        btn_TimKiem.setText("Tìm kiếm");
        add(btn_TimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 315, -1, -1));

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_SanPham);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 378, 620, 110));

        jLabel6.setText("Sản phẩm:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 51, -1, -1));

        jLabel7.setText("Số lượng:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 82, 55, 22));

        jLabel8.setText("Giá:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 128, -1, -1));

        jLabel9.setText("Khuyến mãi:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 51, -1, -1));

        jLabel10.setText("Giá giảm:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 85, -1, -1));
        add(txt_SoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 104, -1));
        add(txt_Gia, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 104, -1));
        add(txt_KhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(951, 48, 101, -1));

        txt_GiaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaGiamActionPerformed(evt);
            }
        });
        add(txt_GiaGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(951, 88, 101, -1));

        btn_SuaSP.setText("Sửa");
        btn_SuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaSPActionPerformed(evt);
            }
        });
        add(btn_SuaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 164, -1, -1));

        btn_XoaSP.setText("Xóa");
        btn_XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPActionPerformed(evt);
            }
        });
        add(btn_XoaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 164, -1, -1));

        btn_ThemSP.setText(">>");
        btn_ThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPActionPerformed(evt);
            }
        });
        add(btn_ThemSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 410, 50, 40));
        add(txt_MaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 100, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_Ma.setText("");
        txt_NgayLap.setText("");
        txt_TongTien.setText("");
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void tbl_DonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DonHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_DonHang.getSelectedRow();
        if(selectedRow != -1)
        {
            String ma = tbl_DonHang.getValueAt(selectedRow, 0).toString();
            String ngayLap = tbl_DonHang.getValueAt(selectedRow, 2).toString();
            String khachHang=tbl_DonHang.getValueAt(selectedRow, 1).toString();
            String tongTien = tbl_DonHang.getValueAt(selectedRow, 3).toString();
            txt_Ma.setText(ma);
            txt_NgayLap.setText(ngayLap);
            CB_KH.setSelectedItem(khachHang);
            txt_TongTien.setText(tongTien);
            
        }
    }//GEN-LAST:event_tbl_DonHangMouseClicked

    private void txt_GiaGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaGiamActionPerformed

    private void btn_SuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaSPActionPerformed
        // TODO add your handling code here:
        String orderId = txt_Ma.getText();
        String productId = txt_MaSP.getText();
        int quantity = Integer.parseInt(txt_SoLuong.getText());
        double price = Double.parseDouble(txt_Gia.getText());
        double discountedPrice = Double.parseDouble(txt_GiaGiam.getText());
        String promotionId = txt_KhuyenMai.getText();

        // Tạo đối tượng sản phẩm mới
        Pojo.DonHang.Product updatedProduct = new Pojo.DonHang.Product(productId, quantity, price, promotionId, discountedPrice);

        // Gọi phương thức cập nhật sản phẩm
        handleDonHang.updateProductInOrder(orderId, updatedProduct);

        // Thông báo thành công
        JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
    }//GEN-LAST:event_btn_SuaSPActionPerformed

    private void btn_XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XoaSPActionPerformed

    private void tbl_ChiTietDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietDonHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_ChiTietDonHang.getSelectedRow();
        if(selectedRow != -1)
        {
            String ma = tbl_ChiTietDonHang.getValueAt(selectedRow, 0).toString();
            String soLuong = tbl_ChiTietDonHang.getValueAt(selectedRow, 2).toString();
            String gia=tbl_ChiTietDonHang.getValueAt(selectedRow, 1).toString();
            String khuyenMai = tbl_ChiTietDonHang.getValueAt(selectedRow, 3).toString();
            String giaGiam=tbl_ChiTietDonHang.getValueAt(selectedRow, 4).toString();
            txt_MaSP.setText(ma);
            txt_SoLuong.setText(soLuong);
            txt_Gia.setText(gia);
            txt_KhuyenMai.setText(khuyenMai);
            txt_GiaGiam.setText(giaGiam);
        }
    }//GEN-LAST:event_tbl_ChiTietDonHangMouseClicked

    private void btn_ThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_KH;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_SuaSP;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_ThemSP;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_ChiTietDonHang;
    private javax.swing.JTable tbl_DonHang;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_GiaGiam;
    private javax.swing.JTextField txt_KhuyenMai;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JFormattedTextField txt_NgayLap;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TongTien;
    // End of variables declaration//GEN-END:variables
}
