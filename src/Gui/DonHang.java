/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Dao.DonHangDao;
import Dao.KhachHangDao;
import Dao.KhuyenMaiDao;
import Dao.SanPhamDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.types.ObjectId;

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
    KhuyenMaiDao handleKhuyenMai=new KhuyenMaiDao();
    SanPhamDao handleSanPham=new SanPhamDao();
    public DonHang() {
        initComponents();
        hienThiDonHang();
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
    double tongTien=0;
    private void hienThiChiTietDonHang(String orderId) {
        Pojo.DonHang donHang = handleDonHang.getOrderById(orderId); // Lấy thông tin chi tiết đơn hàng

        DefaultTableModel dtmChiTiet = new DefaultTableModel();
        dtmChiTiet.addColumn("Mã sản phẩm");
        dtmChiTiet.addColumn("Số lượng");
        dtmChiTiet.addColumn("Giá");
        dtmChiTiet.addColumn("Mã khuyến mãi");
        dtmChiTiet.addColumn("Giá khuyến mãi");
        //dtmChiTiet.addColumn("Thành tiền"); // Thêm cột Thành tiền

        if (donHang != null && donHang.getProducts() != null) {
            double tongThanhTien=0;
            double tongThanhTien1=0;
            
            for (Pojo.DonHang.Product product : donHang.getProducts()) {
                double thanhTien = product.getQuantity() * product.getPrice();
                double thanhTien1= product.getQuantity()*product.getDiscountedPrice();
                dtmChiTiet.addRow(new Object[]{
                    product.getProductID(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getPromotionID(),
                    product.getDiscountedPrice(),
                    //thanhTien
                });
                tongThanhTien+=thanhTien;
                tongThanhTien1+=thanhTien1;
            }
            if(tongThanhTien>=500000)
            {
                tongTien+=tongThanhTien1;
            }
            else
            {
                tongTien=tongThanhTien;
            }
            txt_TongTien.setText(""+tongTien);
        }

        // Thiết lập model cho bảng chi tiết đơn hàng
        tbl_ChiTietDonHang.setModel(dtmChiTiet);
    }
    
    private void hienThiDSSanPham(String customerID) {
        String customerTier=handleKhachHang.getCustomerTiersByID(customerID);
        List<Pojo.SanPham> dsSanPham = handleSanPham.getProductsByCustomerTier(customerTier);
        DefaultTableModel dtm = new DefaultTableModel();

        // Thêm các cột cho bảng đơn hàng
        dtm.addColumn("Mã sản phẩm");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Loại sản phẩm");
        dtm.addColumn("Giá");
        dtm.addColumn("Khuyến mãi");
        for (Pojo.SanPham sanPham : dsSanPham) {
            dtm.addRow(new Object[]{
                sanPham.getId(),
                sanPham.getProductName(),
                sanPham.getCategoryID(),
                sanPham.getPrice(),
                sanPham.getPromotionIDs()
            });
        }

        // Thiết lập model cho bảng đơn hàng
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DonHang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ChiTietDonHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
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
        btn_ThemSP = new javax.swing.JButton();
        txt_MaSP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        refresh_CTDH = new javax.swing.JButton();
        chk_IsVoucher = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        btn_LuuCTDH = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1370, 700));
        setPreferredSize(new java.awt.Dimension(1370, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Chi tiết đơn hàng");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, -1));

        tbl_DonHang.setForeground(new java.awt.Color(0, 51, 204));
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
        tbl_DonHang.setRowHeight(40);
        tbl_DonHang.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tbl_DonHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_DonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DonHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DonHang);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 490, 170));

        tbl_ChiTietDonHang.setForeground(new java.awt.Color(0, 51, 204));
        tbl_ChiTietDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Số lượng", "Giá", "Mã khuyến mãi", "Giá đã khuyến mãi", "Thành tiền"
            }
        ));
        tbl_ChiTietDonHang.setToolTipText("");
        tbl_ChiTietDonHang.setGridColor(new java.awt.Color(0, 51, 204));
        tbl_ChiTietDonHang.setRowHeight(40);
        tbl_ChiTietDonHang.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tbl_ChiTietDonHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_ChiTietDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietDonHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_ChiTietDonHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ChiTietDonHang);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 640, 470));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Mã đơn hàng:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 51, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Ngày lập:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 85, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Mã nhân viên:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Tổng tiền:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 650, -1, -1));
        add(txt_MaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 120, -1));
        add(txt_TongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 650, 120, -1));
        add(txt_NgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 120, -1));

        CB_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_KHActionPerformed(evt);
            }
        });
        add(CB_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 120, -1));

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, 30));

        btn_Sua.setText("Sửa");
        add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, 30));

        btn_LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/reset_1.png"))); // NOI18N
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });
        add(btn_LamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 30, 30));

        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/search.png"))); // NOI18N
        add(btn_TimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 40, 30));

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
        jScrollPane3.setViewportView(tbl_SanPham);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 490, 210));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("Sản phẩm:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 80, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Số lượng:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 80, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Giá:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 60, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Khuyến mãi:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 100, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Giá giảm:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 80, 20));
        add(txt_SoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 120, 20));
        add(txt_Gia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 120, 20));

        txt_KhuyenMai.setEnabled(false);
        add(txt_KhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 110, 20));

        txt_GiaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaGiamActionPerformed(evt);
            }
        });
        add(txt_GiaGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 388, 110, -1));

        btn_ThemSP.setText(">>");
        btn_ThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPActionPerformed(evt);
            }
        });
        add(btn_ThemSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 60, 30));

        txt_MaSP.setEnabled(false);
        add(txt_MaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 120, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 204));
        jLabel11.setText("Đơn hàng");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("Sản phẩm");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        refresh_CTDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/reset_1.png"))); // NOI18N
        refresh_CTDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_CTDHActionPerformed(evt);
            }
        });
        add(refresh_CTDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 40, 30));

        chk_IsVoucher.setAlignmentX(1.0F);
        chk_IsVoucher.setHideActionText(true);
        chk_IsVoucher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_IsVoucher.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_IsVoucher.setIconTextGap(10);
        chk_IsVoucher.setMaximumSize(new java.awt.Dimension(25, 25));
        chk_IsVoucher.setMinimumSize(new java.awt.Dimension(25, 25));
        chk_IsVoucher.setPreferredSize(new java.awt.Dimension(25, 25));
        chk_IsVoucher.setRolloverEnabled(false);
        add(chk_IsVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 30, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setText("Sử dụng voucher");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, 20));

        btn_LuuCTDH.setText("Lưu");
        btn_LuuCTDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuCTDHActionPerformed(evt);
            }
        });
        add(btn_LuuCTDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText("Khách hàng:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));
        add(txt_Ma, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 120, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        txt_MaNV.setText("");
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
            txt_MaNV.setText(ma);
            txt_NgayLap.setText(ngayLap);
            CB_KH.setSelectedItem(khachHang);
            txt_TongTien.setText(tongTien);
            
        }
    }//GEN-LAST:event_tbl_DonHangMouseClicked

    private void txt_GiaGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaGiamActionPerformed

    private void tbl_ChiTietDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietDonHangMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_ChiTietDonHang.getSelectedRow();
        if(selectedRow != -1)
        {
            String ma = tbl_ChiTietDonHang.getValueAt(selectedRow, 0).toString();
            String soLuong = tbl_ChiTietDonHang.getValueAt(selectedRow, 1).toString();
            String gia=tbl_ChiTietDonHang.getValueAt(selectedRow, 2).toString();
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
        int selectedRow = tbl_SanPham.getSelectedRow();
        if (selectedRow == -1) {
            System.out.println("Vui lòng chọn sản phẩm trước khi thêm.");
            return;
        }

        String maSP = tbl_SanPham.getValueAt(selectedRow, 0).toString(); // Cột chứa mã sản phẩm
        String tenSP = tbl_SanPham.getValueAt(selectedRow, 1).toString(); // Cột chứa tên sản phẩm
        String loaiSP = tbl_SanPham.getValueAt(selectedRow, 2).toString(); // Cột chứa loại sản phẩm
        double gia = Double.parseDouble(tbl_SanPham.getValueAt(selectedRow, 3).toString()); // Cột chứa giá
        String maKhuyenMai = tbl_SanPham.getValueAt(selectedRow, 4).toString(); // Cột chứa mã khuyến mãi (nếu có)

        String customerId=CB_KH.getSelectedItem().toString();
        String customerTier = handleKhachHang.getCustomerTiersByID(customerId);
        // Mặc định số lượng = 1
        int soLuong = 1;

        // Kiểm tra chương trình khuyến mãi dựa vào mã khuyến mãi
        double tyLeKhuyenMai = handleKhuyenMai.getKhuyenMaiPercent(maKhuyenMai); // Hàm để lấy tỷ lệ khuyến mãi từ collection
        double giaKhuyenMai = gia * (1 - tyLeKhuyenMai / 100);

        // Tính tổng tiền (ThanhTien = Gia * SoLuong)
        double thanhTien = gia * soLuong;

        // Tạo đối tượng sản phẩm để thêm vào bảng chi tiết đơn hàng
        Object[] rowData = new Object[] {
            maSP, soLuong, gia, maKhuyenMai, giaKhuyenMai, thanhTien
        };

        // Thêm sản phẩm vào bảng chi tiết đơn hàng
        DefaultTableModel model = (DefaultTableModel) tbl_ChiTietDonHang.getModel();
        model.addRow(rowData);

        System.out.println("Sản phẩm đã được thêm vào chi tiết đơn hàng.");
        
    }//GEN-LAST:event_btn_ThemSPActionPerformed

    private void refresh_CTDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_CTDHActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbl_ChiTietDonHang.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_refresh_CTDHActionPerformed

    private void tbl_ChiTietDonHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietDonHangMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_ChiTietDonHangMousePressed

    private void CB_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_KHActionPerformed
        // TODO add your handling code here:
        String customerID=CB_KH.getSelectedItem().toString();
        hienThiDSSanPham(customerID);
    }//GEN-LAST:event_CB_KHActionPerformed

    
    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        String orderID = txt_MaNV.getText();
        String customerID = CB_KH.getSelectedItem().toString();
        Date orderDate;
        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(txt_NgayLap.getText());
            double totalAmount = 0;
            ObjectId idEmployee = new ObjectId("671288853ec5e6060ab93c2a");
            Pojo.DonHang donHang = new Pojo.DonHang(orderID, customerID, orderDate, new ArrayList<>(), 0, idEmployee.toString());
            List<Object[]> ctdh = handleDonHang.getAllRowsFromTable(tbl_ChiTietDonHang);
            for (Object[] row : ctdh) {
                String productID = row[0].toString();
                int quantity = Integer.parseInt(row[1].toString());
                double price = Double.parseDouble(row[2].toString());
                String promotionID = row[3].toString();
                double discountedPrice = Double.parseDouble(row[4].toString());

                // Tạo đối tượng Product và thêm vào danh sách trong DonHang
                Pojo.DonHang.Product sanPham = new Pojo.DonHang.Product(
                    productID,
                    quantity,
                    price,
                    promotionID,
                    discountedPrice
                );
                donHang.getProducts().add(sanPham);
                donHang.setTotalAmount(donHang.getTotalAmount() + discountedPrice * quantity);
            }
            handleDonHang.addOrder(donHang);
        } catch (ParseException ex) {
            Logger.getLogger(DonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        handleDonHang.deleteOrder(txt_MaNV.getText());
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_LuuCTDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuCTDHActionPerformed
        // TODO add your handling code here:
        String orderID = txt_MaNV.getText();
        String customerID = CB_KH.getSelectedItem().toString();
        Date orderDate;
        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(txt_NgayLap.getText());
            double totalAmount = 0;
            ObjectId idEmployee = new ObjectId("671288853ec5e6060ab93c2a");
            Pojo.DonHang donHang = new Pojo.DonHang(orderID, customerID, orderDate, new ArrayList<>(), 0, idEmployee.toString());
            List<Object[]> ctdh = handleDonHang.getAllRowsFromTable(tbl_ChiTietDonHang);
            for (Object[] row : ctdh) {
                String productID = row[0].toString();
                int quantity = Integer.parseInt(row[1].toString());
                double price = Double.parseDouble(row[2].toString());
                String promotionID = row[3].toString();
                double discountedPrice = Double.parseDouble(row[4].toString());

                // Tạo đối tượng Product và thêm vào danh sách trong DonHang
                Pojo.DonHang.Product sanPham = new Pojo.DonHang.Product(
                    productID,
                    quantity,
                    price,
                    promotionID,
                    discountedPrice
                );
                donHang.getProducts().add(sanPham);
                donHang.setTotalAmount(donHang.getTotalAmount() + discountedPrice * quantity);
            }
            handleDonHang.updateOrder(donHang);
        } catch (ParseException ex) {
            Logger.getLogger(DonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }//GEN-LAST:event_btn_LuuCTDHActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_KH;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_LuuCTDH;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_ThemSP;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JCheckBox chk_IsVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JButton refresh_CTDH;
    private javax.swing.JTable tbl_ChiTietDonHang;
    private javax.swing.JTable tbl_DonHang;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_GiaGiam;
    private javax.swing.JTextField txt_KhuyenMai;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JFormattedTextField txt_NgayLap;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TongTien;
    // End of variables declaration//GEN-END:variables
}
