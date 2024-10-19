/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;


import Dao.KhachHangDao;
import Dao.KhuyenMaiDao;
import Dao.LoaiSanPhamDao;
import Dao.SanPhamDao;
import Pojo.KhuyenMai.AppliedTo;
import Pojo.KhuyenMai.Conditions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
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
    
    List<String> selectedprodIDs = new ArrayList<>();
    List<String> selectedprodTypeIDs = new ArrayList<>();
    String selectedCustomerType = null;
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
            handleKhuyenMai.checkAndUpdatePromotion(ls.getId());
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
        
        dtm1.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2) {
                Boolean isChecked = (Boolean) dtm1.getValueAt(row, 2);
                String id = (String) dtm1.getValueAt(row, 0);

                if (isChecked != null && isChecked) {
                    if (!selectedprodIDs.contains(id)) {
                        selectedprodIDs.add(id);
                    }
                } else {
                    selectedprodIDs.remove(id);
                }
            }
        });
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
        dtm2.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 1) {
                Boolean isChecked = (Boolean) dtm2.getValueAt(row, 1);
                String id = (String) dtm2.getValueAt(row, 0);

                if (isChecked != null && isChecked) {
                    for (int i = 0; i < dtm2.getRowCount(); i++) {
                        if (i != row) {
                            dtm2.setValueAt(false, i, 1);
                        }
                    }
                    selectedCustomerType = id;

                } else {
                    selectedCustomerType = null;
                }

                // Debug: print the currently selected customer type
                System.out.println("Selected Customer Type: " + selectedCustomerType);
            }
        });
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

        dtm3.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2) {
                Boolean isChecked = (Boolean) dtm3.getValueAt(row, 2);
                String id = (String) dtm3.getValueAt(row, 0);

                if (isChecked != null && isChecked) {
                    if (!selectedprodTypeIDs.contains(id)) {
                        selectedprodTypeIDs.add(id);
                    }
                } else {
                    selectedprodTypeIDs.remove(id);
                }
            }
        });
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
        Chk_ApDung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Chk_ApDung.isSelected()) {
                    tbl_SanPham.setEnabled(true);
                    tbl_SanPham.setFocusable(true);
                    tbl_SanPham.getTableHeader().setEnabled(true);
                    tbl_LoaiSanPham.setEnabled(true);
                    tbl_LoaiSanPham.setFocusable(true);
                    tbl_LoaiSanPham.getTableHeader().setEnabled(true);
                    tbl_LoaiKhachHang.setEnabled(true);
                    tbl_LoaiKhachHang.setFocusable(true);
                    tbl_LoaiKhachHang.getTableHeader().setEnabled(true);
                } else {
                    tbl_SanPham.setEnabled(false);
                    tbl_SanPham.setFocusable(false);
                    tbl_SanPham.getTableHeader().setEnabled(false);
                    tbl_LoaiSanPham.setEnabled(false);
                    tbl_LoaiSanPham.setFocusable(false);
                    tbl_LoaiSanPham.getTableHeader().setEnabled(false);
                    tbl_LoaiKhachHang.setEnabled(false);
                    tbl_LoaiKhachHang.setFocusable(false);
                    tbl_LoaiKhachHang.getTableHeader().setEnabled(false);
                }
            }
        });
        chk_DieuKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chk_DieuKien.isSelected()) {
                    txt_GiaTriNhoNhat.setEnabled(true);
                } else {
                    txt_GiaTriNhoNhat.setEnabled(false);
                }
            }
        });
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
        btn_Add = new javax.swing.JButton();
        btn_Del = new javax.swing.JButton();
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

        txt_NgayBD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        txt_NgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayBDActionPerformed(evt);
            }
        });
        add(txt_NgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 201, 218, -1));

        txt_NgayKT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
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
        tbl_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhuyenMaiMouseClicked(evt);
            }
        });
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

        btn_Add.setText(">>>");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });
        add(btn_Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 60, -1));

        btn_Del.setText("<<<");
        add(btn_Del, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 60, 30));

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

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_KhuyenMai.getSelectedRow();
        if (selectedRow != -1) {
            //isUpdatingFromTable = true; // Đặt cờ trước khi cập nhật
            String ma = tbl_KhuyenMai.getValueAt(selectedRow, 0).toString();
            String ten = tbl_KhuyenMai.getValueAt(selectedRow, 1).toString();
            String percent = tbl_KhuyenMai.getValueAt(selectedRow, 2).toString();
            String startDate = tbl_KhuyenMai.getValueAt(selectedRow, 3).toString();
            String endDate=tbl_KhuyenMai.getValueAt(selectedRow, 4).toString();
            
            txt_Ma.setText(ma);
            txt_Ten.setText(ten);
            txt_Giam.setText(percent);
            txt_NgayBD.setText(startDate);
            txt_NgayKT.setText(endDate);
            if (chk_DieuKien.isSelected()) {
                String minOrderValue = tbl_KhuyenMai.getValueAt(selectedRow, 8).toString();
                txt_GiaTriNhoNhat.setText(minOrderValue);
            }
            else{
                txt_GiaTriNhoNhat.setText("");
            }
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
    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        String ma = txt_Ma.getText();
        String ten= txt_Ten.getText();
        String giamGiaString = txt_Giam.getText().trim();
        int giamGia = Integer.parseInt(giamGiaString);
        
        String ngayBD = txt_NgayBD.getText().trim();
        String ngayKT = txt_NgayKT.getText().trim();
        String gtnnString = txt_GiaTriNhoNhat.getText().trim();
        int gtnn = Integer.parseInt(gtnnString);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        
        AppliedTo apDung = new AppliedTo(selectedprodIDs,selectedprodTypeIDs);
        
        Conditions dieuKien = new Conditions(gtnn,selectedCustomerType);
        
        Pojo.KhuyenMai km = new Pojo.KhuyenMai();
        km.setId(ma);
        km.setPromotionName(ten);
        km.setDiscountPercent(giamGia);
        Date bd;
        try {
            bd = dateFormat.parse(ngayBD);
            km.setStartDate(bd);
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date kt;
        try {
            kt = dateFormat.parse(ngayKT);
            km.setEndDate(kt); 
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        km.setAppliedTo(apDung);
        km.setConditions(dieuKien);

        boolean success = handleKhuyenMai.addPromotion(km);

        // Thông báo kết quả
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
            hienThi();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
        }
    }//GEN-LAST:event_btn_AddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Chk_ApDung;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Del;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chk_DieuKien;
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
