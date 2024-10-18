/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;


import Dao.KhachHangDao;
import Dao.KhuyenMaiDao;
import Dao.LoaiSanPhamDao;
import Dao.SanPhamDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author HUU KHANH
 */
public class KhuyenMai extends javax.swing.JPanel {

    /**
     * Creates new form NguoiKhieuNai
     */
    KhuyenMaiDao handleKhuyenMai = new KhuyenMaiDao();
    KhachHangDao handleKhachHang=new KhachHangDao();
    SanPhamDao handleSanPham=new SanPhamDao();
    LoaiSanPhamDao handleLoaiSanPham=new LoaiSanPhamDao();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public KhuyenMai() {
        initComponents();
        hienThi();
    }

    private void hienThi()
    {
        List<Pojo.KhuyenMai> ds = handleKhuyenMai.getAllPromotions();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã khuyến mãi");
        dtm.addColumn("Tên khuyến mãi");
        dtm.addColumn("Giảm giá");
        dtm.addColumn("Ngày bắt đầu");
        dtm.addColumn("Ngày kết thúc");
        dtm.addColumn("Sản phẩm");
        dtm.addColumn("Loại sản phẩm");
        dtm.addColumn("Loại khách hàng");
        dtm.addColumn("Giá trị nhỏ nhất");
        dtm.setNumRows(ds.size());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<ds.size();i++)
        {
            Pojo.KhuyenMai ls = ds.get(i);
            dtm.setValueAt(ls.getId(), i, 0);
            dtm.setValueAt(ls.getPromotionName(),i, 1);
            dtm.setValueAt(ls.getDiscountPercent(), i, 2);
            dtm.setValueAt(ls.getStartDate() !=null ? dateFormat.format(ls.getStartDate()):"", i, 3);
            dtm.setValueAt(ls.getEndDate() !=null ? dateFormat.format(ls.getEndDate()):"", i, 4);
            dtm.setValueAt(ls.getAppliedTo().getProducts(), i, 5);
            dtm.setValueAt(ls.getAppliedTo().getCategories(), i, 6);
            dtm.setValueAt(ls.getConditions().getCustomerTier(), i, 7);
            dtm.setValueAt(ls.getConditions().getMinOrderValue(), i, 8);
        }
        tbl_KhuyenMai.setModel(dtm);
        List<Pojo.SanPham> ds1 = handleSanPham.getAllProducts();
        DefaultTableModel dtm1 = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        dtm1.addColumn("Mã sản phẩm");
        dtm1.addColumn("Tên sản phẩm");
        dtm1.addColumn("Trạng thái");
        dtm1.setNumRows(ds1.size());
        for(int i=0;i<ds1.size();i++)
        {
            Pojo.SanPham ls = ds1.get(i);
            dtm1.setValueAt(ls.getId(), i, 0);
            dtm1.setValueAt(ls.getProductName(),i, 1);
            dtm1.setValueAt(false,i, 2);
        }
        tbl_SanPham.setModel(dtm1);
        List<Pojo.KhachHang> ds2 = handleKhachHang.getAllCustomers();
        Set<String> uniqueCustomerTiers = new HashSet<>();
        for (Pojo.KhachHang kh : ds2) {
            uniqueCustomerTiers.add(kh.getTier());
        }
        DefaultTableModel dtm2 = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        dtm2.addColumn("Loại khách hàng");
        dtm2.addColumn("Trạng thái");
        dtm2.setNumRows(uniqueCustomerTiers.size());
        int rowIndex1 = 0;
        for (String customerTier : uniqueCustomerTiers) {
            dtm2.setValueAt(customerTier, rowIndex1, 0);
            dtm2.setValueAt(false, rowIndex1, 1);
            rowIndex1++;
        }
        tbl_LoaiKhachHang.setModel(dtm2);
        List<Pojo.LoaiSanPham> ds3 = handleLoaiSanPham.getAllCategories();
        DefaultTableModel dtm3 = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        dtm3.addColumn("Mã loại sản phẩm");
        dtm3.addColumn("Loại sản phẩm");
        dtm3.addColumn("Trạng thái");
        dtm3.setNumRows(ds3.size());

        for(int i=0;i<ds3.size();i++)
        {
            Pojo.LoaiSanPham ls = ds3.get(i);
            dtm3.setValueAt(ls.getId(), i, 0);
            dtm3.setValueAt(ls.getCategoryName(),i, 1);
            dtm3.setValueAt(false,i, 2);
        }

        tbl_LoaiSanPham.setModel(dtm3);
        tbl_KhuyenMai.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbl_KhuyenMai.getSelectedRow();
                if (selectedRow != -1) {
                    List<String> promotionProductCodes = (List<String>) dtm.getValueAt(selectedRow, 5);
                    List<String> productTypes = (List<String>) dtm.getValueAt(selectedRow, 6);
                    String customerTypes = (String) dtm.getValueAt(selectedRow, 7);
                    String[] customerTypeArray = customerTypes.split(",");
                    
                    for (int i = 0; i < tbl_LoaiSanPham.getRowCount(); i++) {
                        String productType = (String) dtm3.getValueAt(i, 0);
                        boolean shouldBeChecked = false;

                        for (String prodType : productTypes) {
                            if (prodType.equals(productType)) {
                                shouldBeChecked = true;
                                break;
                            }
                        }

                        dtm3.setValueAt(shouldBeChecked, i, 2);
                    }

                    
                    for (int i = 0; i < tbl_LoaiKhachHang.getRowCount(); i++) {
                        String customerTier = (String) dtm2.getValueAt(i, 0);
                        boolean shouldBeChecked = false;

                        for (String promoCustomerType : customerTypeArray) {
                            if (promoCustomerType.trim().equalsIgnoreCase(customerTier)) {
                                shouldBeChecked = true;
                                break;
                            }
                        }

                        dtm2.setValueAt(shouldBeChecked, i, 1);
                    }

                    for (int i = 0; i < tbl_SanPham.getRowCount(); i++) {
                        String productCode = (String) dtm1.getValueAt(i, 0);
                        boolean shouldBeChecked = false;

                        for (String promoCode : promotionProductCodes) {
                            if (promoCode.equals(productCode)) {
                                shouldBeChecked = true;
                                break;
                            }
                        }

                        dtm1.setValueAt(shouldBeChecked, i, 2);
                    }
                }
            }
        });
    }
    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        int selectedRow = tbl_KhuyenMai.getSelectedRow();
        if (selectedRow != -1) {
            //isUpdatingFromTable = true; // Đặt cờ trước khi cập nhật
            String ma = tbl_KhuyenMai.getValueAt(selectedRow, 0).toString();
            String ten = tbl_KhuyenMai.getValueAt(selectedRow, 1).toString();
            String percent = tbl_KhuyenMai.getValueAt(selectedRow, 2).toString();
            String startDate = tbl_SanPham.getValueAt(selectedRow, 3).toString();
            String endDate=tbl_KhuyenMai.getValueAt(selectedRow, 4).toString();
            txt_Ma.setText(ma);
            txt_Ten.setText(ten);
            txt_Giam.setText(percent);
            txt_NgayBD.setText(startDate);
            txt_NgayKT.setText(endDate);
            // Truy vấn chi tiết khuyến mãi từ CSDL để lấy thông tin appliedTo
            Document promoDoc = handleKhuyenMai.getPromotionById(ma); // Lấy khuyến mãi bằng ID
            if (promoDoc != null) {
                // Kiểm tra nếu đối tượng appliedTo tồn tại
                if (promoDoc.containsKey("appliedTo")) {
                    Chk_ApDung.setSelected(true); // Tích checkbox nếu appliedTo tồn tại
                } else {
                    Chk_ApDung.setSelected(false); // Bỏ tích nếu appliedTo không tồn tại
                }
            } else {
                Chk_ApDung.setSelected(false); // Bỏ tích nếu không tìm thấy khuyến mãi
            }
                    
            
        }
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Chk_ApDung = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        chk_DieuKien = new javax.swing.JCheckBox();
        txt_Ma = new javax.swing.JTextField();
        txt_Ten = new javax.swing.JTextField();
        txt_Giam = new javax.swing.JTextField();
        txt_GiaTriNhoNhat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_NgayBD = new javax.swing.JFormattedTextField();
        txt_NgayKT = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_LoaiSanPham = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_LoaiKhachHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã khuyến mãi:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 84, -1, -1));

        jLabel2.setText("Tên chương trình khuyến mãi");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 124, -1, -1));

        jLabel3.setText("Giảm giá (%):");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 164, -1, -1));

        jLabel4.setText("Ngày bắt đầu:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 204, -1, -1));

        jLabel5.setText("Ngày kết thúc:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 244, -1, -1));

        Chk_ApDung.setText("Áp dụng");
        Chk_ApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk_ApDungActionPerformed(evt);
            }
        });
        add(Chk_ApDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jLabel9.setText("Trị giá HD nhỏ nhất:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        chk_DieuKien.setText("Điều kiện:");
        chk_DieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_DieuKienActionPerformed(evt);
            }
        });
        add(chk_DieuKien, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));
        add(txt_Ma, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 81, 216, -1));
        add(txt_Ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 121, 139, -1));
        add(txt_Giam, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 161, 221, -1));
        add(txt_GiaTriNhoNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 76, -1));

        jLabel12.setText("Chương trình khuyến mãi");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 24, -1, -1));

        txt_NgayBD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy/MM/dd"))));
        txt_NgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayBDActionPerformed(evt);
            }
        });
        add(txt_NgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 201, 218, -1));

        txt_NgayKT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy/MM/dd"))));
        add(txt_NgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 241, 217, -1));

        tbl_KhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_KhuyenMai);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 387, 300));

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
        jScrollPane2.setViewportView(tbl_SanPham);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 300, 91));

        tbl_LoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_LoaiSanPham);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 300, 90));

        tbl_LoaiKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_LoaiKhachHang);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 300, 100));

        jButton1.setText(">>>");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 60, -1));

        jButton2.setText("<<<");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 60, 30));

        jButton3.setText("Sửa");
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 610, -1, -1));

        btn_Them.setText("Thêm ");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        btn_Sua.setText("Sửa");
        add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        btn_Xoa.setText("Xóa");
        add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void Chk_ApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk_ApDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Chk_ApDungActionPerformed

    private void chk_DieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_DieuKienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_DieuKienActionPerformed

    private void txt_NgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayBDActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Chk_ApDung;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chk_DieuKien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbl_KhuyenMai;
    private javax.swing.JTable tbl_LoaiKhachHang;
    private javax.swing.JTable tbl_LoaiSanPham;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txt_GiaTriNhoNhat;
    private javax.swing.JTextField txt_Giam;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JFormattedTextField txt_NgayBD;
    private javax.swing.JFormattedTextField txt_NgayKT;
    private javax.swing.JTextField txt_Ten;
    // End of variables declaration//GEN-END:variables
}
