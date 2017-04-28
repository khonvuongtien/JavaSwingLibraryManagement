/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO;
import model.Sach;
import model.SachDAO;

/**
 *
 * @author Ly Nhan
 */
public class sach extends javax.swing.JFrame {

    
    Object[][] danhSach_sach_data;
    Object[] danhSach_sach_columns = {"Mã Sách","Tên Sách","Số lượng còn"};
    DefaultTableModel tbl_sach_danhSach_model = new DefaultTableModel(danhSach_sach_data,danhSach_sach_columns);
    
    giaoDIen gd = null ;
    public sach() {
        initComponents();
        tbl_sach_danhSach.setModel(tbl_sach_danhSach_model);
        chon.setVisible(false);
        showTableDanhSach();
    }
    void resetTable(DefaultTableModel tblModel){
        for(int i=tblModel.getRowCount()-1;i>=0;i--){
            tblModel.removeRow(i);
        }
    }
    public void showTableDanhSach(){
        SachDAO sachDAO = new SachDAO();
        List<Sach> listSach = sachDAO.getAllSach();
        for (int i = 0; i < listSach.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listSach.get(i).getMasach(), listSach.get(i).getTensach()
                ,listSach.get(i).getSoluongton()
            };
            
               tbl_sach_danhSach_model.addRow(tmpRow);      
        }
        
    }
    
    public void passingFrame(giaoDIen gd){
        this.gd = gd;
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
        sach_tf_tensach_filter = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sach_danhSach = new javax.swing.JTable();
        chon = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Sách");

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbl_sach_danhSach.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sach_danhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sach_danhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sach_danhSach);

        chon.setText("Chọn");
        chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chonActionPerformed(evt);
            }
        });

        jButton3.setText("Đóng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chon, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(sach_tf_tensach_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sach_tf_tensach_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chon)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_sach_danhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sach_danhSachMouseClicked
        chon.setVisible(true);
    }//GEN-LAST:event_tbl_sach_danhSachMouseClicked

    private void chonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chonActionPerformed
        if(tbl_sach_danhSach.getSelectedRow()!=-1){                        
                for(int i = 0 ; i < gd.getTbl_qlpm_ctpm().getRowCount() ; i++){
                    if(Integer.parseInt(String.valueOf(tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 0)) ) == Integer.parseInt(String.valueOf(gd.getTbl_qlpm_ctpm_model().getValueAt(i, 0)) ) ) {
                        if(Integer.parseInt(String.valueOf(tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 2)) ) > 0){
                            gd.getTbl_qlpm_ctpm_model().setValueAt(Integer.parseInt(String.valueOf(gd.getTbl_qlpm_ctpm_model().getValueAt(i, 2)) )+1,i, 2);                        
                            tbl_sach_danhSach.setValueAt(Integer.parseInt(String.valueOf(tbl_sach_danhSach.getValueAt(tbl_sach_danhSach.getSelectedRow(), 2)) )-1,tbl_sach_danhSach.getSelectedRow(), 2);
                            return;
                        }else{
                            JOptionPane.showMessageDialog(null,"Het Sach","LOI",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                    }
                    
                }
            }
            else{
                
            }   
        if(Integer.parseInt(String.valueOf(tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 2)) ) > 0){
                tbl_sach_danhSach_model.setValueAt(Integer.parseInt(String.valueOf(tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 2)) )-1,tbl_sach_danhSach.getSelectedRow(), 2);
                Object[] tmpRow = {tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 0), tbl_sach_danhSach_model.getValueAt(tbl_sach_danhSach.getSelectedRow(), 1),1};
                gd.getTbl_qlpm_ctpm_model().addRow(tmpRow);      
                gd.getTbl_qlpm_ctpm().clearSelection();            
        }
                
        
    }//GEN-LAST:event_chonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
                List<Sach> sach = new ArrayList();
        for(int i=0 ; i<tbl_sach_danhSach.getRowCount() ; i++){
            Sach s = new Sach();
            s.setMasach(Integer.parseInt(String.valueOf(tbl_sach_danhSach.getValueAt(i, 0))));
            s.setSoluongton(Integer.parseInt(String.valueOf(tbl_sach_danhSach.getValueAt(i, 2))));
            sach.add(s);
        }
        gd.getListFromsach(sach);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        DAO dao = new DAO();
        
        sach_tf_tensach_filter.getText();
        resetTable(tbl_sach_danhSach_model);
        dao.beginTransaction();
        
        List<Sach> listS = (List<Sach>)dao.getObjectsWithCriteria("Sach", "e.tensach like '%"+sach_tf_tensach_filter.getText()+"%'");
        dao.commitTransaction();
        dao.closeAll();
        listS.stream().forEach(e -> {
            Object[] tmpRow= new Object[]{
            e.getMasach(), e.getTensach()
                ,e.getSoluongton()
            };
            tbl_sach_danhSach_model.addRow(tmpRow);
        });
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(sach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField sach_tf_tensach_filter;
    private javax.swing.JTable tbl_sach_danhSach;
    // End of variables declaration//GEN-END:variables
}