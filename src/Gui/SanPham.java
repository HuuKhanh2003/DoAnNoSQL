/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Dao.SanPhamDao;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author HOANG LONG
 */
public class SanPham extends javax.swing.JPanel {

    /**
     * Creates new form NguoiKhieuNai
     */
    SanPhamDao handleSanPham = new SanPhamDao();
    private boolean isUpdatingFromTable = false;
    
    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public SanPham() {
        initComponents();
        hienThi();
        addComboBoxListener();
        addSearchButtonListener();
    }

    private void addComboBoxListener() {
    CB_LoaiSP.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isUpdatingFromTable) {
                String selectedCategory = (String) CB_LoaiSP.getSelectedItem();
                if (selectedCategory != null) {
                    hienThiSanPhamTheoLoai(selectedCategory);
                }
            }
        }
    });
    }

    private void hienThiSanPhamTheoLoai(String categoryID) {
        List<Pojo.SanPham> ds = handleSanPham.getProductsByCategory(categoryID);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã sản phẩm");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Giá");
        dtm.addColumn("Loại sản phẩm");
        dtm.addColumn("Chương trình spuyến mãi");
        dtm.addColumn("Có chương trình spuyến mãi");
        dtm.setNumRows(ds.size());
        
        for (int i = 0; i < ds.size(); i++) {
            Pojo.SanPham ls = ds.get(i);
            dtm.setValueAt(ls.getId(), i, 0);
            dtm.setValueAt(ls.getProductName(), i, 1);
            dtm.setValueAt(ls.getPrice(), i, 2);
            dtm.setValueAt(ls.getCategoryID(), i, 3);
            dtm.setValueAt(ls.getPromotionIDs(), i, 4);
            dtm.setValueAt(ls.getIsPromotionProgram() == true, i, 5);
        }

        tbl_SanPham.setModel(dtm);
    }
    
    private void addSearchButtonListener() {
    btn_Tim.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = txt_Tim.getText();
            List<Pojo.SanPham> searchResults = handleSanPham.searchProducts(keyword);
            
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sản phẩm");
            dtm.addColumn("Tên sản phẩm");
            dtm.addColumn("Giá");
            dtm.addColumn("Loại sản phẩm");
            dtm.addColumn("Chương trình spuyến mãi");
            dtm.addColumn("Có chương trình spuyến mãi");
            dtm.setNumRows(searchResults.size());
            
            for (int i = 0; i < searchResults.size(); i++) {
                Pojo.SanPham sp = searchResults.get(i);
                dtm.setValueAt(sp.getId(), i, 0);
                dtm.setValueAt(sp.getProductName(), i, 1);
                dtm.setValueAt(sp.getPrice(), i, 2);
                dtm.setValueAt(sp.getCategoryID(), i, 3);
                dtm.setValueAt(sp.getPromotionIDs(), i, 4);
                dtm.setValueAt(sp.getIsPromotionProgram() == true, i, 5);
            }
            tbl_SanPham.setModel(dtm);
        }
    });
    }
    
    private void hienThi()
    {
        List<String> dsSanPham = handleSanPham.getAllProductCategories();
        for(String loaiSP:dsSanPham)
        {
            CB_LoaiSP.addItem(loaiSP);
        }
        List<Pojo.SanPham> ds = handleSanPham.getAllProducts();
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 5;
            }
        };
        dtm.addColumn("Mã sản phẩm");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Giá");
        dtm.addColumn("Loại sản phẩm");
        dtm.addColumn("Chương trình khuyến mãi");
        dtm.addColumn("Có chương trình spuyến mãi");
        dtm.setNumRows(ds.size());
        for(int i=0;i<ds.size();i++)
        {
            Pojo.SanPham ls = ds.get(i);
            dtm.setValueAt(ls.getId(), i, 0);
            dtm.setValueAt(ls.getProductName(),i, 1);
            dtm.setValueAt(ls.getPrice(), i, 2);
            dtm.setValueAt(ls.getCategoryID(), i, 3);
            dtm.setValueAt(ls.getPromotionIDs(), i, 4);
            dtm.setValueAt(ls.getIsPromotionProgram() == true, i, 5);
        }
        tbl_SanPham.setModel(dtm);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        txt_Ten = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Upadte = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        CB_LoaiSP = new javax.swing.JComboBox<>();
        txt_Gia = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_Tim = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_CTKM = new javax.swing.JTextField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1370, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(1370, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Mã sản phẩm:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 57, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Tên sản phẩm:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Sản phẩm");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Giá:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Loại sản phẩm:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, -1, -1));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 71, -1, -1));
        jPanel1.add(txt_Ma, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 310, 30));

        tbl_SanPham.setForeground(new java.awt.Color(0, 51, 204));
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
        tbl_SanPham.setRowHeight(40);
        tbl_SanPham.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tbl_SanPham.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPham);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1370, 380));
        jPanel1.add(txt_Ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 310, 30));

        btn_Them.setBackground(new java.awt.Color(0, 255, 0));
        btn_Them.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(255, 255, 255));
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 70, 30));

        btn_Xoa.setBackground(new java.awt.Color(255, 0, 51));
        btn_Xoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 60, 30));

        btn_Upadte.setBackground(new java.awt.Color(0, 255, 204));
        btn_Upadte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Upadte.setForeground(new java.awt.Color(255, 255, 255));
        btn_Upadte.setText("Sửa");
        btn_Upadte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpadteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Upadte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 60, 30));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/reset_1.png"))); // NOI18N
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btn_LamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 40, 30));

        jPanel1.add(CB_LoaiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 290, 30));
        jPanel1.add(txt_Gia, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 92, 290, 30));

        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/search.png"))); // NOI18N
        jPanel1.add(btn_Tim, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 40, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Tìm kiếm:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        txt_Tim.setToolTipText("Tìm Mã sản phẩm/ Tên sản phẩm/ Loại sản phẩm");
        jPanel1.add(txt_Tim, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 250, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Mã CTKM:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, -1, -1));
        jPanel1.add(txt_CTKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 290, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_SanPham.getSelectedRow();
        if (selectedRow != -1) {
            isUpdatingFromTable = true; // Đặt cờ trước khi cập nhật
            String ma = tbl_SanPham.getValueAt(selectedRow, 0).toString();
            String ten = tbl_SanPham.getValueAt(selectedRow, 1).toString();
            String price = tbl_SanPham.getValueAt(selectedRow, 2).toString();
            String loaiSP = tbl_SanPham.getValueAt(selectedRow, 3).toString();
            String ctkm = tbl_SanPham.getValueAt(selectedRow, 4).toString();

            txt_Ma.setText(ma);
            txt_Ten.setText(ten);
            txt_Gia.setText(price);
            CB_LoaiSP.setSelectedItem(loaiSP);
            txt_CTKM.setText(ctkm);
        }
        isUpdatingFromTable = false;
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        String ma = txt_Ma.getText();
        String ten= txt_Ten.getText();
        String priceString = txt_Gia.getText().trim();
        Double price = Double.parseDouble(priceString);
        String loaiSP = (String) CB_LoaiSP.getSelectedItem();
        // Chuyển đổi ngày sinh từ String sang Date
        
        String promotionIDs = txt_CTKM.getText();

        Pojo.SanPham sp = new Pojo.SanPham();
        sp.setId(ma);
        sp.setProductName(ten);
        sp.setPrice(price);
        sp.setPromotionIDs(promotionIDs);
        sp.setCategoryID(loaiSP);
        sp.setIsPromotionProgram(false);

        // Gọi hàm themNguoiKhieuNai để thêm vào cơ sở dữ liệu
        boolean success = handleSanPham.addProduct(sp);

        // Thông báo kết quả
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
            hienThi();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
         //TODO add your handling code here:
         int selectedRow = tbl_SanPham.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa.");
            return;
        }

        // Lấy mã sản phẩm từ hàng được chọn
        String ma = tbl_SanPham.getValueAt(selectedRow, 0).toString();

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Có"
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi hàm xóa để xóa spỏi cơ sở dữ liệu
            boolean success = handleSanPham.deleteProduct(ma);

            // Thông báo kết quả và cập nhật bảng
            if (success) {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!");
                hienThi(); 
            } else {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!");
            }
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_UpadteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpadteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbl_SanPham.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa.");
            return;
        }

        // Lấy dữ liệu từ các trường nhập liệu
        String ma = txt_Ma.getText();
        String ten= txt_Ten.getText();
        String priceString = txt_Gia.getText().trim();
        Double price = Double.parseDouble(priceString);
        String loaiSP = (String) CB_LoaiSP.getSelectedItem();
        String promotionIDs = txt_CTKM.getText();

        // Tạo đối tượng NguoiKhieuNai
        Pojo.SanPham sp = new Pojo.SanPham();
        sp.setId(ma);
        sp.setProductName(ten);
        sp.setPrice(price);
        sp.setPromotionIDs(promotionIDs);
        sp.setCategoryID(loaiSP);
        sp.setIsPromotionProgram(false);

        // Gọi hàm SuaNguoiKhieuNai để cập nhật vào cơ sở dữ liệu
        boolean success = handleSanPham.updateProduct(sp);

        // Thông báo kết quả và cập nhật bảng
        if (success) {
            JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!");
            hienThi();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại!");
        }
    }//GEN-LAST:event_btn_UpadteActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_Ma.setText("");
        txt_Ten.setText("");
        txt_Gia.setText("");
        txt_CTKM.setText("");
        
        hienThi();
        
        buttonGroup1.clearSelection();

    }//GEN-LAST:event_btn_LamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_LoaiSP;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_Upadte;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txt_CTKM;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_Ten;
    private javax.swing.JTextField txt_Tim;
    // End of variables declaration//GEN-END:variables
}
