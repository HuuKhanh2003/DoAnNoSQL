/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;
import Dao.DangNhapDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 84862
 */
public class FrmTong extends javax.swing.JFrame {
    /**
     * Creates new form FrmTong
     */
    DangNhap dangNhap= new DangNhap();

    public FrmTong(DangNhap dn) {
        initComponents();
        dangNhap=dn;
        txt_ChucVu.setText(dangNhap.role);
        txt_TenDangNhap.setText(dangNhap.name);
        Date ngayGioHienTai = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayGioFormatted = sdf.format(ngayGioHienTai);
        // Gán ngày giờ vào trường văn bản
        txt_NgayGio.setText(ngayGioFormatted);
        JPanel newPanel = new FormTrangChu();

        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);

        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
        phanQuyen();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMenu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_TrangChu = new javax.swing.JLabel();
        lb_DangXuat = new javax.swing.JLabel();
        lb_KhuyenMai = new javax.swing.JLabel();
        lb_KhachHang = new javax.swing.JLabel();
        lb_SanPham = new javax.swing.JLabel();
        lb_NhanVien = new javax.swing.JLabel();
        lb_DonHang = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Menu_Thongtin = new javax.swing.JLabel();
        Menu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_TenDangNhap = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ChucVu = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_NgayGio = new javax.swing.JLabel();
        panel_GiaoDien = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMenu.setBackground(new java.awt.Color(0, 51, 204));
        pnMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnMenu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 3, 770));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/zoomin.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel2.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(51, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 255, 0));
        jLabel1.setText("CALL");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 120, -1));

        jLabel2.setBackground(new java.awt.Color(0, 51, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("OMI");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, -1));

        pnMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 100));

        lb_TrangChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_TrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lb_TrangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-home-50.png"))); // NOI18N
        lb_TrangChu.setText("  Trang Chủ");
        lb_TrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_TrangChuMouseClicked(evt);
            }
        });
        pnMenu.add(lb_TrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 320, 80));

        lb_DangXuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lb_DangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/dangxuat.png"))); // NOI18N
        lb_DangXuat.setText("Đăng xuất");
        lb_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_DangXuatMouseClicked(evt);
            }
        });
        pnMenu.add(lb_DangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 310, 80));

        lb_KhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_KhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        lb_KhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_KhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-free-50.png"))); // NOI18N
        lb_KhuyenMai.setText("   Khuyến mãi");
        lb_KhuyenMai.setToolTipText("");
        lb_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_KhuyenMaiMouseClicked(evt);
            }
        });
        pnMenu.add(lb_KhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 310, 80));

        lb_KhachHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_KhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lb_KhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_KhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/userKN.png"))); // NOI18N
        lb_KhachHang.setText("   Khách hàng");
        lb_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_KhachHangMouseClicked(evt);
            }
        });
        pnMenu.add(lb_KhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 310, 80));

        lb_SanPham.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_SanPham.setForeground(new java.awt.Color(255, 255, 255));
        lb_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/product.png"))); // NOI18N
        lb_SanPham.setText("  Sản phẩm");
        lb_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_SanPhamMouseClicked(evt);
            }
        });
        pnMenu.add(lb_SanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 360, 70));

        lb_NhanVien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/employee.png"))); // NOI18N
        lb_NhanVien.setText("   Nhân viên");
        lb_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_NhanVienMouseClicked(evt);
            }
        });
        pnMenu.add(lb_NhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 360, 70));

        lb_DonHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_DonHang.setForeground(new java.awt.Color(255, 255, 255));
        lb_DonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-cart-50.png"))); // NOI18N
        lb_DonHang.setText("   Đơn hàng");
        lb_DonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_DonHangMouseClicked(evt);
            }
        });
        pnMenu.add(lb_DonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 360, 70));

        getContentPane().add(pnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 770));

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu_Thongtin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/user.png"))); // NOI18N
        Menu_Thongtin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu_ThongtinMouseClicked(evt);
            }
        });
        jPanel1.add(Menu_Thongtin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 50, 60));

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/menu1.png"))); // NOI18N
        Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuMouseEntered(evt);
            }
        });
        jPanel1.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên Đăng Nhập :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, -1, -1));

        txt_TenDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_TenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        txt_TenDangNhap.setText("Hoàng Gia");
        jPanel1.add(txt_TenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quyền :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, -1, -1));

        txt_ChucVu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_ChucVu.setForeground(new java.awt.Color(255, 255, 255));
        txt_ChucVu.setText("Chủ Tịch");
        jPanel1.add(txt_ChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ngày :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 50, -1, -1));

        txt_NgayGio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_NgayGio.setForeground(new java.awt.Color(255, 255, 255));
        txt_NgayGio.setText("jLabel2");
        jPanel1.add(txt_NgayGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 50, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 1370, -1));

        panel_GiaoDien.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel_GiaoDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1370, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
         closeMenubar();
    }//GEN-LAST:event_closeMouseClicked

   public void phanQuyen()
    {
        String quyen=dangNhap.role.toString();
        if(quyen.equals("employee"))
        {
            lb_KhuyenMai.setEnabled(false);
            lb_NhanVien.setEnabled(false);
        }
        else if(quyen.equals("manage"))
        {
            
        }
        else
        {
            lb_KhuyenMai.setEnabled(true);
            lb_KhachHang.setEnabled(true);
            lb_NhanVien.setEnabled(true);
            
        }
    }
    private void lblClear()
    {
        lb_KhuyenMai.setForeground(Color.WHITE);
        lb_DangXuat.setForeground(Color.WHITE);
        lb_KhachHang.setForeground(Color.WHITE);
    }
    private void MenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuMouseClicked
        // TODO add your handling code here:
        openMenubar();
    }//GEN-LAST:event_MenuMouseClicked

    private void lb_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_DangXuatMouseClicked
        // TODO add your handling code here:
        DangNhap temp=new DangNhap();
        temp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lb_DangXuatMouseClicked

    private void lb_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_KhuyenMaiMouseClicked
//        // TODO add your handling code here:
        JPanel newPanel = new KhuyenMai();
//
//        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);

        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }//GEN-LAST:event_lb_KhuyenMaiMouseClicked

   
    private void Menu_ThongtinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu_ThongtinMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Menu_ThongtinMouseClicked

    private void lb_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NhanVienMouseClicked
        // TODO add your handling code here:
        JPanel newPanel = new NhanVien();

        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);
        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }//GEN-LAST:event_lb_NhanVienMouseClicked

    private void lb_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_KhachHangMouseClicked
        // TODO add your handling code here:
        JPanel newPanel = new FrmKhachHang();

        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);

        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }//GEN-LAST:event_lb_KhachHangMouseClicked

    private void lb_TrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_TrangChuMouseClicked
        // TODO add your handling code here:
        JPanel newPanel = new FormTrangChu();

        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);

        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }//GEN-LAST:event_lb_TrangChuMouseClicked

    
    private void MenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuMouseEntered

      private void lb_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        JPanel newPanel = new SanPham();

        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);
        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }                                       

    private void lb_DonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_DonHangMouseClicked
        // TODO add your handling code here:
        JPanel newPanel = new DonHang();
        //JPanel newPanel = new NhanVien();
        // Loại bỏ tất cả các thành phần con của panel_GiaoDien
        panel_GiaoDien.removeAll();

        // Thêm formPhong vào panel_GiaoDien
        panel_GiaoDien.setLayout(new BorderLayout()); // Đảm bảo bố cục hợp lý
        panel_GiaoDien.add(newPanel, BorderLayout.CENTER);
        // Cập nhật giao diện người dùng
        panel_GiaoDien.revalidate();
        panel_GiaoDien.repaint();
    }//GEN-LAST:event_lb_DonHangMouseClicked
    int width =303;
    int height=770;

    private void openMenubar()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < width; i++)
                {
                    pnMenu.setSize(i, height);
                    try 
                    {
                        Thread.sleep(2);
                    } 
                    catch (InterruptedException ex) {
                        Logger.getLogger(FrmTong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    private void closeMenubar()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = width; i > 0; i--)
                {
                    pnMenu.setSize(i, height);
                    try 
                    {
                        Thread.sleep(2);
                    } 
                    catch (InterruptedException ex) {
                        Logger.getLogger(FrmTong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTong(new DangNhap()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel Menu_Thongtin;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lb_DangXuat;
    public javax.swing.JLabel lb_DonHang;
    private javax.swing.JLabel lb_KhachHang;
    private javax.swing.JLabel lb_KhuyenMai;
    public javax.swing.JLabel lb_NhanVien;
    public javax.swing.JLabel lb_SanPham;
    private javax.swing.JLabel lb_TrangChu;
    public javax.swing.JPanel panel_GiaoDien;
    public javax.swing.JPanel pnMenu;
    public javax.swing.JLabel txt_ChucVu;
    private javax.swing.JLabel txt_NgayGio;
    public javax.swing.JLabel txt_TenDangNhap;
    // End of variables declaration//GEN-END:variables
    private void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
