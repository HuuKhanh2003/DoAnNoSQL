/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.PopupMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

/**
 *
 * @author 84862
 */
public class Start extends javax.swing.JFrame {

    /**
     * Creates new form Start
     */
    pbThead t1;
    private PopupMenu panel;
    public Start() {
        initComponents();
        t1=new pbThead(prog_Start);
        t1.start();
        image();
    }
    public void image()
    {
        String imagePath = "C:\\Users\\HUU KHANH\\Desktop\\anhdaidien.jpg";

        // Tạo ImageIcon từ hình ảnh
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Lấy kích thước của JLabel
        int labelWidth = ImageDaiDien.getWidth();
        int labelHeight = ImageDaiDien.getHeight();

        // Chỉnh sửa kích thước hình ảnh để lấp đầy JLabel
        Image scaledImage = originalIcon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        ImageDaiDien.setIcon(scaledIcon);
    }
    
    class pbThead extends Thread
    {
        JProgressBar pbar;
        pbThead (JProgressBar pbar)
        {
            pbar=prog_Start;
        }
        
        public void run()
        {
            int min=0;
            int max= 100;
            prog_Start.setMaximum(min);
            prog_Start.setMaximum(max);
            prog_Start.setValue(0);
           
            for(int i=min;i<=max;i+=10)
            {
                prog_Start.setValue(i);
                try {
                    sleep(500);
                    txt_PhanTram.setText(i+"%");
                    if(i==100)
                    {
                       
                        DangNhap temp= new DangNhap(new FrmTong());
                        temp.setVisible(true);
                        Start.this.setVisible(false);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
               
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

        panel_Start = new javax.swing.JPanel();
        prog_Start = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        txt_PhanTram = new javax.swing.JLabel();
        ImageDaiDien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_Start.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prog_Start.setBackground(new java.awt.Color(255, 255, 255));
        prog_Start.setForeground(new java.awt.Color(0, 204, 0));
        panel_Start.add(prog_Start, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 790, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Loading");
        panel_Start.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, -1, -1));

        txt_PhanTram.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_PhanTram.setForeground(new java.awt.Color(0, 51, 204));
        txt_PhanTram.setText("100%");
        panel_Start.add(txt_PhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, -1, -1));
        panel_Start.add(ImageDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 400));

        getContentPane().add(panel_Start, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageDaiDien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel_Start;
    private javax.swing.JProgressBar prog_Start;
    private javax.swing.JLabel txt_PhanTram;
    // End of variables declaration//GEN-END:variables
}
