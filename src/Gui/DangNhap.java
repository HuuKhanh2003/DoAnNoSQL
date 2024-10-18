/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

import Dao.DangNhapDao;
import javax.swing.JOptionPane;

/**
 *
 * @author HUU KHANH
 */
public class DangNhap extends javax.swing.JFrame {

    String username="";
    String password="";
    String role="";
    String name="";
    /**
     * Creates new form DangNhap
     */
    public DangNhap() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        quenmatkhau = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_TaiKhoan = new javax.swing.JTextField();
        txt_MatKhau = new javax.swing.JTextField();
        btn_DangNhap = new javax.swing.JButton();
        btn_formdangky = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(865, 448));
        setPreferredSize(new java.awt.Dimension(865, 448));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(432, 432));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OMICALL");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 421, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 430, 70));

        jLabel4.setFont(new java.awt.Font("Vivaldi", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kính chào quý khách !");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 400, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Tài Khoản :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/user_1.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Mật Khẩu :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 232, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/nhaplai.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        quenmatkhau.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        quenmatkhau.setForeground(new java.awt.Color(0, 51, 204));
        quenmatkhau.setText("forgot password ?");
        quenmatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quenmatkhauMouseClicked(evt);
            }
        });
        getContentPane().add(quenmatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 164, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("LOGIN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, -1, -1));
        getContentPane().add(txt_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 182, 310, 30));
        getContentPane().add(txt_MatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 310, 30));

        btn_DangNhap.setBackground(new java.awt.Color(0, 51, 204));
        btn_DangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_DangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btn_DangNhap.setText("Log in");
        btn_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangNhapActionPerformed(evt);
            }
        });
        getContentPane().add(btn_DangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, 121, 43));

        btn_formdangky.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_formdangky.setForeground(new java.awt.Color(0, 51, 204));
        btn_formdangky.setFocusCycleRoot(true);
        btn_formdangky.setLabel("Sign up");
        btn_formdangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_formdangkyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_formdangky, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 353, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("I don't have an account ?");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 164, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public boolean kiemTraNhapDuLieu()
    {
        if(txt_MatKhau.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mật Khẩu.", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(txt_TaiKhoan.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tài Khoản.", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    private void btn_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangNhapActionPerformed

        DangNhapDao dangNhapDao = new DangNhapDao();
        username=txt_TaiKhoan.getText().toString();
        password=txt_MatKhau.getText().toString();
        // Gọi phương thức đăng nhập
        boolean success = dangNhapDao.login(username,password);

        if (success==true) {
            if (kiemTraNhapDuLieu()) {
                role=dangNhapDao.getRoleByUsernameAndPassword(username, password);
                name=dangNhapDao.getEmployeeNameByUsernameAndPassword(username, password);
                this.setVisible(false);
                FrmTong temp = new FrmTong(this);
                temp.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tài Khoản Không Tồn Tại.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_DangNhapActionPerformed

    private void btn_formdangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_formdangkyActionPerformed
        // TODO add your handling code here:
        DangKy temp= new DangKy();
        temp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_formdangkyActionPerformed

    private void quenmatkhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quenmatkhauMouseClicked
        // TODO add your handling code here:
        QuenMatKhau temp= new QuenMatKhau();
        temp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_quenmatkhauMouseClicked

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangNhap;
    private javax.swing.JButton btn_formdangky;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel quenmatkhau;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}
