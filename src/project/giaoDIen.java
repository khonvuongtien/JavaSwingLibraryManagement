/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ChiTietPhieuMuon;
import model.DAO;
import model.DocGia;
import model.DocGiaDAO;
import model.NhaXuatBan;
import model.NhaXuatBanDAO;
import model.NhanVien;
import model.NhanVienDAO;
import model.PhieuMuon;
import model.PhieuMuonDAO;
import model.Sach;
import model.SachDAO;
import model.TheLoai;
import model.TheLoaiDAO;
import model.chitietphieumuon_identity;




public class giaoDIen extends javax.swing.JFrame {

    Object[][] nv_data ;
    Object[] nv_columns = {"Ma nhan vien","Ten nhan vien","Ten dang nhap","Mat Khau","SDT"
            ,"Dia chi","Tuoi","CMND","Gioi tinh","Cap bac"};
    DefaultTableModel tbl_nv_model = new DefaultTableModel(nv_data,nv_columns);
    
    Object[][] dg_data ;
    Object[] dg_columns = {"Ma","Ten","Gioi tinh","Nghe","CMND","SDT","Dia Chi","Ngay cap the"};
    DefaultTableModel tbl_dg_model = new DefaultTableModel(dg_data,dg_columns);
    
    Object[][] tl_data ;
    Object[] tl_columns = {"Ma The Loai","Ten The Loai"};
    DefaultTableModel tbl_tl_model = new DefaultTableModel(tl_data,tl_columns);
    
    Object[][] nxb_data ;
    Object[] nxb_columns = {"Ma NXB","Ten","Dia Chi","SDT"};
    DefaultTableModel tbl_nxb_model = new DefaultTableModel(nxb_data,nxb_columns);

    Object[][] sach_data ;
    Object[] sach_columns = {"Ma Sach","Ten","So Trang","Ngon Ngu",
        "So Luong Ton","NXB","The Loai"};
    DefaultTableModel tbl_sach_model = new DefaultTableModel(sach_data,sach_columns);
    DefaultTableModel tbl_msach_model = new DefaultTableModel(sach_data,sach_columns);
    
    Object[][] phieumuon_data;
    Object[] phieumuon_columns = {"Ma Sach","Ten Sach","So luong"};
    DefaultTableModel tbl_phieumuon_model = new DefaultTableModel(phieumuon_data,phieumuon_columns);
    
    Object[][] phieumuon_data_qlpm;
    Object[] phieumuon_qlpm_columns = {"Ma Sach","Ten Sach","So luong"};
    DefaultTableModel tbl_qlpm_ctpm_model = new DefaultTableModel(phieumuon_data_qlpm,phieumuon_qlpm_columns);
    
    Object[][] pmTongHop_data;
    Object[] pmTongHop_columns = {"Mã phiếu","Đọc giả","Tiền cọc","Nhân viên","Ngày thuê","Ngày tra","Trạng thái"};
    DefaultTableModel tbl_pmTongHop_model = new DefaultTableModel(pmTongHop_data,pmTongHop_columns);
    
    
    
    Object[][] trasach_data;
    Object[] trasach_columns = {"Mã phiếu","Đọc giả","Tiền cọc","Nhân viên","Ngày thuê","Ngày tra","Trạng thái"};
    DefaultTableModel tbl_trasach_model = new DefaultTableModel(trasach_data,trasach_columns);
    
    Object[][] tbl_trasach_ctpm_data;
    Object[] tbl_trasach_ctpm_columns = {"Mã sách","Tên sách","Số lượng"};
    DefaultTableModel tbl_trasach_ctpm_model = new DefaultTableModel (tbl_trasach_ctpm_data,tbl_trasach_ctpm_columns);
    
    List<Sach> updatedSachList;
    NhanVien crntUser;
    DocGia dg4phieumuon=null;
    
    public giaoDIen() {
        initComponents();
        capNhatPhieuQuaHan();
        tbl_nv.setModel(tbl_nv_model);
        //set width column ma nhan vien
        tbl_nv.getColumnModel().getColumn(0).setPreferredWidth(100);
        //set width column ten nhan vien
        tbl_nv.getColumnModel().getColumn(1).setPreferredWidth(110);
        
        
        nv_del_btn.setVisible(false);
        nv_cancel_btn.setVisible(false);
        nv_upd_btn.setVisible(false);
        nv_reset_passwd.setVisible(false);
        btn_qlpm_suaSach.setVisible(false);
        btn_trasach_giahan.setVisible(false);
        btn_trasach_trahet.setVisible(false);
        showTableNhanVien();
        
        
        //config table dg
        tbl_dg.setModel(tbl_dg_model);
        tbl_dg.getColumnModel().getColumn(7).setPreferredWidth(110);      
        
        
        dg_del_btn.setVisible(false);
        dg_cancel_btn.setVisible(false);
        dg_update_btn.setVisible(false);
        showTableDocGia();
        
        //config table tl
        tbl_tl.setModel(tbl_tl_model);

        
        tl_del_btn.setVisible(false);
        tl_cancel_btn.setVisible(false);
        tl_update_btn.setVisible(false);
        showTableTheLoai();
        
        //config table nxb
        tbl_nxb.setModel(tbl_nxb_model);
       
        
        nxb_del_btn.setVisible(false);
        nxb_cancel_btn.setVisible(false);
        nxb_update_btn.setVisible(false);
        showTableNhaXuatBan();
        
        //config table sach
        tbl_sach.setModel(tbl_sach_model);
        addDataFromListToComboBox(sach_tl,new TheLoaiDAO().getDistinctTheLoai());
        //addDataFromListToComboBox(sach_ke,new ViTriDAO().getDistinctKeSach());
        addDataFromListToComboBox(sach_nxb,new NhaXuatBanDAO().getDistinctNhaXuatBan());
        addDataFromListToComboBox(sach_tl1,new TheLoaiDAO().getDistinctTheLoai());
        //addDataFromListToComboBox(sach_ke1,new ViTriDAO().getDistinctKeSach());
        addDataFromListToComboBox(sach_nxb1,new NhaXuatBanDAO().getDistinctNhaXuatBan());
        
        
        sach_del_btn.setVisible(false);
        sach_cancel_btn.setVisible(false);
        sach_update_btn.setVisible(false);
        showTableSach();       
        
        //config muon sach
        tbl_msach.setModel(tbl_msach_model);
                     
        phieumuon_crntDate.setText(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now()));
        
        tbl_phieumuon.setModel(tbl_phieumuon_model);
        
        //của Nhân
        tbl_qlpm_ctpm.setModel(tbl_qlpm_ctpm_model);
        btn_pmTongHop_update.setVisible(false);
        btn_pmTongHop_delete.setVisible(false);
        btn_pmTongHop_cancel.setVisible(false);
        
        btn_pmTongHop_xoaPM.setVisible(false);
        tbl_pmTongHop.setModel(tbl_pmTongHop_model);
        showTablePMTongHop();
        
        //config tra sach
        tbl_trasach.setModel(tbl_trasach_model);
        tbl_trasach_ctpm.setModel(tbl_trasach_ctpm_model);
        showTableTraSach();
        
        
    }
    
    public void capNhatPhieuQuaHan(){
        Calendar cal = Calendar.getInstance();                
        Calendar cal2 = Calendar.getInstance();
        PhieuMuonDAO pmDAO = new PhieuMuonDAO();
        List<PhieuMuon> listPM = pmDAO.getAllPhieuMuon();
        
        
        
        for (int i = 0; i<listPM.size();i++){
            cal2.setTime(listPM.get(i).getNgaytra());
            if(cal.compareTo(cal2) != -1){
                listPM.get(i).setTt("qua han");
                new PhieuMuonDAO().updatePhieuMuon(listPM.get(i));
            }
        }
    }
    
    public void hideNV(){
        tab_pane.remove(nv_panel);
    }

    void resetTable(DefaultTableModel tblModel){
        for(int i=tblModel.getRowCount()-1;i>=0;i--){
            tblModel.removeRow(i);
        }
    }
    
    void addDataFromListToComboBox(JComboBox box, List<?> list){
        list.forEach(e->box.addItem(e));
    }
    
    public void showTableTraSach(){
        PhieuMuonDAO pmDAO = new PhieuMuonDAO();
        List<PhieuMuon> listPM = pmDAO.getAllPhieuMuon();
        resetTable(tbl_trasach_model);
        for (int i = 0; i < listPM.size(); i++){
            Object[] tmpRow = new Object[]
            {
                listPM.get(i).getMaphieu(),listPM.get(i).getDocgia().getTendg(),
                listPM.get(i).getTiencoc(),listPM.get(i).getNhanvien().getTennv(),listPM.get(i).getNgaythue(),
                listPM.get(i).getNgaytra(),listPM.get(i).getTt()
            };
            tbl_trasach_model.addRow(tmpRow);
        }
    }
    public void showTableNhanVien(){
        NhanVienDAO nvDAO = new NhanVienDAO();
        List<NhanVien> listNhanVien = nvDAO.getAllNhanVien();
        resetTable(tbl_nv_model);
        for (int i = 0; i < listNhanVien.size(); i++) {
            String pwd="";
            for (int j = 0; j < listNhanVien.get(i).getMatkhau().length(); j++) {
                pwd+="*";
            }
            Object[] tmpRow =new Object[]
            {
                listNhanVien.get(i).getManv(),listNhanVien.get(i).getTennv(),listNhanVien.get(i).getTendangnhap(),pwd
            ,listNhanVien.get(i).getSdt(),listNhanVien.get(i).getDiachi(),listNhanVien.get(i).getTuoi(),listNhanVien.get(i).getCMND()
                    ,listNhanVien.get(i).getGioitinh(),listNhanVien.get(i).getCapbac()
            };
            tbl_nv_model.addRow(tmpRow);
        }
        
    }
    // của Nhân
    public void showTablePMTongHop(){
        PhieuMuonDAO pmDAO = new PhieuMuonDAO();
        List<PhieuMuon> listPM = pmDAO.getAllPhieuMuon();
        resetTable(tbl_pmTongHop_model);
        for (int i = 0; i < listPM.size(); i++){
            Object[] tmpRow = new Object[]
            {
                listPM.get(i).getMaphieu(),listPM.get(i).getDocgia().getTendg(),
                listPM.get(i).getTiencoc(),listPM.get(i).getNhanvien().getTennv(),listPM.get(i).getNgaythue(),
                listPM.get(i).getNgaytra(),listPM.get(i).getTt()
            };
            tbl_pmTongHop_model.addRow(tmpRow);
        }
        
    
    }
   
    public void showTableDocGia(){
        DocGiaDAO dgDAO = new DocGiaDAO();
        List<DocGia> listDocGia = dgDAO.getAllDocGia();
        resetTable(tbl_dg_model);
        for (int i = 0; i < listDocGia.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listDocGia.get(i).getMadg(),listDocGia.get(i).getTendg(),listDocGia.get(i).getGioitinh(),listDocGia.get(i).getNghenghiep()
                    ,listDocGia.get(i).getCmnd(),listDocGia.get(i).getSdt(),listDocGia.get(i).getDiachi(),listDocGia.get(i).getNgaycapthe()
            };
            tbl_dg_model.addRow(tmpRow);
        }
        
    }
    
    public void showTableTheLoai(){
        TheLoaiDAO tlDAO = new TheLoaiDAO();
        List<TheLoai> listTheLoai = tlDAO.getAllTheLoai();
        resetTable(tbl_tl_model);
        for (int i = 0; i < listTheLoai.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listTheLoai.get(i).getMatl(), listTheLoai.get(i).getTentl()
            };
            tbl_tl_model.addRow(tmpRow);
        }
        
    }
    
    public void showTableNhaXuatBan(){
        NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
        List<NhaXuatBan> listNhaXuatBan = nxbDAO.getAllNhaXuatBan();
        resetTable(tbl_nxb_model);
        for (int i = 0; i < listNhaXuatBan.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listNhaXuatBan.get(i).getManxb(), listNhaXuatBan.get(i).getTen(),
                listNhaXuatBan.get(i).getDiachi(),listNhaXuatBan.get(i).getSdt()
            };
            tbl_nxb_model.addRow(tmpRow);
        }
        
    }
    
    public void showTableSach(){                
        SachDAO sachDAO = new SachDAO();
        List<Sach> listSach = sachDAO.getAllSach();
        resetTable(tbl_sach_model);
        for (int i = 0; i < listSach.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listSach.get(i).getMasach(), listSach.get(i).getTensach(),listSach.get(i).getSotrang(),listSach.get(i).getNgonngu()
                ,listSach.get(i).getSoluongton(),listSach.get(i).getNxb().getTen(),listSach.get(i).getTl().getTentl()
            };
            tbl_sach_model.addRow(tmpRow);
            tbl_msach_model.addRow(tmpRow);
        }
        
        
    }
    
    public void showTableMSach(){                
        SachDAO sachDAO = new SachDAO();
        List<Sach> listSach = sachDAO.getAllSach();
        resetTable(tbl_msach_model);
        for (int i = 0; i < listSach.size(); i++) {
            Object[] tmpRow =new Object[]
            {
                listSach.get(i).getMasach(), listSach.get(i).getTensach(),listSach.get(i).getSotrang(),listSach.get(i).getNgonngu()
                ,listSach.get(i).getSoluongton(),listSach.get(i).getNxb().getTen(),listSach.get(i).getTl().getTentl()
            };
            tbl_msach_model.addRow(tmpRow);
        }
        
        
    }
    public void showTableChiTietPhieuMuon(){
        PhieuMuon PM = new PhieuMuon();
        int row = tbl_pmTongHop.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(tbl_pmTongHop.getValueAt(row, 2)));
        List<ChiTietPhieuMuon> listCTPM = PM.getCtpm();
        ChiTietPhieuMuon CTPM = new ChiTietPhieuMuon();
        chitietphieumuon_identity ctpm_id = new chitietphieumuon_identity();
            Object[] tmpROw = new Object[]
            {
                PM.getCtpm()
            };
    }
    public void showFormThongTin(NhanVien nv){
        tt_tf_ma.setText(String.valueOf(nv.getManv()));
        tt_tf_ten.setText(nv.getTennv());
        tt_tf_tdn.setText(nv.getTendangnhap());
        tt_tf_sdt.setText(nv.getSdt());
        tt_tf_diachi.setText(nv.getDiachi());
        tt_tf_cmnd.setText(nv.getCMND());
        tt_tf_capbac.setText(nv.getCapbac());
        tt_tf_tuoi.setText(String.valueOf(nv.getTuoi()));
        tt_tf_gioitinh.setText(nv.getGioitinh());
        crntUser = nv;
        phieumuon_crntUser.setText(crntUser.getTennv());
    }
    
    public void resetFormNhanVien(){
        nv_tf_manv.setText("");
        nv_tf_tennv.setText("");
        nv_tf_tdn.setText("");
        nv_tf_sdt.setText("");
//        nv_tf_mk.setText("");
        nv_tf_cmnd.setText("");
        nv_tf_diachi.setText("");
        nv_tf_tuoi.setText("");
        nv_btn_group.clearSelection();
        nv_capbac.setSelectedIndex(0);
    }
    
    public void resetFormDocGia(){
        dg_tf_madg.setText("");
        dg_tf_tendg.setText("");
        dg_tf_sdt.setText("");
        dg_tf_nghe.setText("");
        dg_tf_cmnd.setText("");
        dg_tf_diachi.setText("");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();       
        dg_ngay.setDate(date);
        dg_btn_group.clearSelection();
        
    }
    public void resetFormTheLoai(){
        tl_tf_matl.setText("");
        tl_tf_ten.setText("");
        
    }
    
    public void resetFormTraSach(){
        lbl_TraSach_dg.setText("");
        lbl_TraSach_maphieu.setText("");
        lbl_TraSach_ngayThue.setText("");
        lbl_TraSach_ngayTra.setText("");
        lbl_TraSach_nhanvien.setText("");
        lbl_TraSach_tiencoc.setText("");
    }
    
    public void resetFormNhaXuatBan(){
        nxb_tf_manxb.setText("");
        nxb_tf_ten.setText("");
        nxb_tf_diachi.setText("");
        nxb_tf_sdt.setText("");
    }
    
    public void resetFormSach(){
        sach_tf_ma.setText("");
        sach_tf_ten.setText("");
        sach_tf_ngonngu.setText("");
        
        sach_tf_sl.setText("");
        sach_tf_sotrang.setText("");
        
        sach_tl.setSelectedIndex(0);
       // sach_ke.setSelectedIndex(0);
        sach_nxb.setSelectedIndex(0);
    }
    public void resetFormPhieuMuon(){
        phieumuon_tf_madg.setText("");
        phieumuon_tf_tien.setText("");
        resetTable(tbl_phieumuon_model);
        tbl_msach.clearSelection();
    }
    
    public void resetFormQLPM_CTPM(){
        lbl_pmTongHop_maphieu.setText("");
        lbl_pmTongHop_ngayThue.setText("");
        lbl_pmTongHop_NgayTra.setText("");
        txt_pmTongHop_docGia.setText("");
        txt_pmTongHop_tienCoc.setText("");
        resetTable(tbl_qlpm_ctpm_model);     
    }
    
    String checkPhoneNumber(String str){        
        try{
            Integer.parseInt(str);
            if(str.startsWith("09")){
            if(str.length() == 10){
                return "";
            }else{
                return "So Dien Thoai Khong Hop Le";
            }
        }else if(str.startsWith("01")){
            if(str.length()==11){
                return "";
            }else{
                return "So Dien Thoai Khong Hop Le";
            }
        }           
            
        }catch(Exception e){
            return "So Dien Thoai Khong Hop Le";
        }
        return "So Dien Thoai Khong Hop Le";
    }    
    
    
    
    public boolean validateNhanVienForm(){
        if(nv_tf_tennv.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ten Nhan Vien khong duoc de trong", "LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            if(nv_tf_tdn.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Ten Dang Nhap khong duoc de trong", "LOI", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
                if(!checkPhoneNumber(nv_tf_sdt.getText()).equals("")){
                    JOptionPane.showMessageDialog(null,checkPhoneNumber(nv_tf_sdt.getText()) , "LOI", JOptionPane.ERROR_MESSAGE);    
                    return false;
                }else{
                    if(nv_tf_diachi.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Dia Chi khong duoc de trong", "LOI", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }else{
                        if(!nv_tf_tuoi.getText().equals("")){
                            //tuoi khac rong
                            try{
                                Integer.parseInt(nv_tf_tuoi.getText());
                                if(nv_tf_cmnd.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "CMND khong duoc de trong", "LOI", JOptionPane.ERROR_MESSAGE);
                                    return false;
                                }else{
                                    try{
                                        Integer.parseInt(nv_tf_cmnd.getText());
                                        if(nv_tf_cmnd.getText().length()!=9){
                                            JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                                            return false;
                                        }else{
                                            try{
                                                nv_btn_group.getSelection().isSelected();                                                
                                            }catch(NullPointerException e){
                                                JOptionPane.showMessageDialog(null, "Chon Gioi Tinh", "LOI", JOptionPane.ERROR_MESSAGE);  
                                                return false;
                                            }
                                        }
                                    }catch(Exception e){
                                      JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                                      return false;
                                    }
                                }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Tuoi khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);    
                                return false;
                            }
                            //
                        }else{
                            //tuoi = rong
                            
                                
                                if(nv_tf_cmnd.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "CMND khong duoc de trong", "LOI", JOptionPane.ERROR_MESSAGE);
                                    return false;
                                }else{
                                    try{
                                        Integer.parseInt(nv_tf_cmnd.getText());
                                        if(nv_tf_cmnd.getText().length()!=9){
                                            JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                                            return false;
                                        }else{
                                            try{
                                                nv_btn_group.getSelection().isSelected();                                                
                                            }catch(NullPointerException e){
                                                JOptionPane.showMessageDialog(null, "Chon Gioi Tinh", "LOI", JOptionPane.ERROR_MESSAGE);  
                                                return false;
                                            }
                                        }
                                    }catch(Exception e){
                                      JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                                      return false;
                                    }
                                }
                        }
                        
                    }
                }
            }
        }
        return true;
    }
        
    
    public boolean validateDocGiaForm(){
        if(dg_tf_tendg.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ten Doc Gia ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try{
                dg_btn_group.getSelection().isSelected();
                
                    
                        try{
                           Integer.parseInt(dg_tf_cmnd.getText());
                           if(dg_tf_cmnd.getText().length()!=9){
                               JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                                return false;
                            }else{
                                  if(!checkPhoneNumber(dg_tf_sdt.getText()).equals("")){
                                          JOptionPane.showMessageDialog(null,checkPhoneNumber(dg_tf_sdt.getText()) , "LOI", JOptionPane.ERROR_MESSAGE);    
                                          return false;
                                      }else{
                                          if(dg_ngay.getDate()==null){
                                          JOptionPane.showMessageDialog(null,checkPhoneNumber(dg_tf_sdt.getText()) , "LOI", JOptionPane.ERROR_MESSAGE);    
                                          return false;
                                          }else{
                                              return true;
                                          }
                                      }         
                                 }
                        }catch(Exception e){
                           JOptionPane.showMessageDialog(null, "CMND khong hop le", "LOI", JOptionPane.ERROR_MESSAGE);  
                            return false;
                        }
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Vui long chon gioi tinh","LOI", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
    }
   
    public boolean validateSachForm(){
        if(sach_tf_ten.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ten Sach khong duoc de trong","LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            if(!sach_tf_sotrang.getText().equals("")){
                try{
                    Integer.parseInt(sach_tf_sotrang.getText());
                    if(sach_tf_ngonngu.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Ngon Ngu khong duoc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                    return false;
                }else{
                    if(sach_tf_sl.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "So Luong khong duoc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }else{
                        try{
                            Integer.parseInt(sach_tf_sl.getText());
                            return true;
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "So Luong khong hop le","LOI", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                }
                    //
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "So Trang khong hop le","LOI", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }else{
                                    
                    if(sach_tf_ngonngu.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Ngon Ngu khong duoc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                    return false;
                }else{
                    if(sach_tf_sl.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "So Luong khong duoc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }else{
                        try{
                            Integer.parseInt(sach_tf_sl.getText());
                            return true;
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "So Luong khong hop le","LOI", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                }
                
                
            }
        }
        
    }
        
    public boolean validateTheLoaiForm(){
        if(tl_tf_ten.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ten The Loai ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean validateNhaXuatBanForm(){
        if(nxb_tf_ten.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ten Nha Xuat Ban ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            if(nxb_tf_diachi.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Dia Chi ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                return false;
            }else{
                if(nxb_tf_sdt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "So Dien Thoai ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
                    return false;
                }else{
                    if(!checkPhoneNumber(nxb_tf_sdt.getText()).equals("")){
                        JOptionPane.showMessageDialog(null, checkPhoneNumber(nxb_tf_sdt.getText()),"LOI", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }            
        }
        return true;
    }
    
    public boolean validatePhieuMuonForm(){
        if(phieumuon_tf_madg.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ma Doc Gia ko dc de trong","LOI", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            DocGiaDAO dgDAO = new DocGiaDAO();
            //load: error no proxy .
            dg4phieumuon = dgDAO.getDocGiaWithId(Integer.parseInt(phieumuon_tf_madg.getText()));
            //System.out.println(dg4phieumuon.getMadg());
            if(dg4phieumuon==null){
                JOptionPane.showMessageDialog(null, "Mã đọc giả không tồn tại","LỖI", JOptionPane.ERROR_MESSAGE);
                return false;
            }else{
                try{
                    Float.parseFloat(phieumuon_tf_tien.getText());
                    if(tbl_phieumuon.getRowCount()>0){
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Phiếu mượn không có sách","LỖI", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Tiền cọc không hợp lệ","LỖI", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
        }
        
    }
    
    public JTable getTbl_qlpm_ctpm(){
        return tbl_qlpm_ctpm;
    }
    
    public DefaultTableModel getTbl_qlpm_ctpm_model(){
        return tbl_qlpm_ctpm_model;
    }
    public void getListFromsach(List<Sach> sachList){
        updatedSachList = sachList;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dg_btn_group = new javax.swing.ButtonGroup();
        nv_btn_group = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        BIGDATA = new javax.swing.JPanel();
        tab_pane = new javax.swing.JTabbedPane();
        dg_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dg_tf_nghe = new javax.swing.JTextField();
        dg_tf_cmnd = new javax.swing.JTextField();
        dg_tf_diachi = new javax.swing.JTextField();
        dg_tf_sdt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dg = new javax.swing.JTable();
        dg_them_btn = new javax.swing.JButton();
        dg_update_btn = new javax.swing.JButton();
        dg_del_btn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        dg_tf_search = new javax.swing.JTextField();
        dg_nam = new javax.swing.JRadioButton();
        dg_nu = new javax.swing.JRadioButton();
        dg_tf_tendg = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        dg_search_gioitinh = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        dg_rs_btn = new javax.swing.JButton();
        dg_cancel_btn = new javax.swing.JButton();
        dg_tf_madg = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        dg_ngay = new com.toedter.calendar.JDateChooser();
        jLabel73 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_phieumuon = new javax.swing.JTable();
        phieumuon_tf_madg = new javax.swing.JTextField();
        phieumuon_crntDate = new javax.swing.JLabel();
        phieumuon_crntUser = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        phieumuon_tf_tien = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        msach_tf_tensach = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_msach = new javax.swing.JTable();
        jButton26 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        qlpm_pane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_pmTongHop = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        pmTongHop_tt_search = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        lbl_pmTongHop_maphieu = new javax.swing.JLabel();
        lbl_pmTongHop_ngayThue = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_qlpm_ctpm = new javax.swing.JTable();
        btn_pmTongHop_update = new javax.swing.JButton();
        btn_pmTongHop_delete = new javax.swing.JButton();
        btn_pmTongHop_cancel = new javax.swing.JButton();
        btn_pmTongHop_xoaPM = new javax.swing.JButton();
        btn_qlpm_suaSach = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        lbl_pmTongHop_NgayTra = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        pmTongHop_ngaythue_search = new com.toedter.calendar.JDateChooser();
        btnPrint = new javax.swing.JButton();
        txt_pmTongHop_tienCoc = new javax.swing.JLabel();
        txt_pmTongHop_docGia = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_trasach = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_trasach_ctpm = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_trasach_trahet = new javax.swing.JButton();
        btn_trasach_giahan = new javax.swing.JButton();
        lbl_TraSach_maphieu = new javax.swing.JLabel();
        lbl_TraSach_ngayTra = new javax.swing.JLabel();
        lbl_TraSach_dg = new javax.swing.JLabel();
        lbl_TraSach_nhanvien = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        lbl_TraSach_tiencoc = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_TraSach_ngayThue = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_tl = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        tl_tf_ten = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tl_them_btn = new javax.swing.JButton();
        tl_del_btn = new javax.swing.JButton();
        tl_update_btn = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        nxb_tf_diachi = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        nxb_tf_sdt = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        nxb_them_btn = new javax.swing.JButton();
        nxb_del_btn = new javax.swing.JButton();
        nxb_update_btn = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_nxb = new javax.swing.JTable();
        nxb_tf_ten = new javax.swing.JTextField();
        tl_rs_btn = new javax.swing.JButton();
        tl_cancel_btn = new javax.swing.JButton();
        nxb_rs_btn = new javax.swing.JButton();
        nxb_cancel_btn = new javax.swing.JButton();
        tl_tf_matl = new javax.swing.JLabel();
        nxb_tf_manxb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sach_tf_ten = new javax.swing.JTextField();
        sach_tf_sotrang = new javax.swing.JTextField();
        sach_tf_ngonngu = new javax.swing.JTextField();
        sach_tf_sl = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sach = new javax.swing.JTable();
        sach_them_btn = new javax.swing.JButton();
        sach_del_btn = new javax.swing.JButton();
        sach_update_btn = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        sach_tl = new javax.swing.JComboBox<>();
        sach_tf_ten_search = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        sach_nxb = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        sach_tl1 = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        sach_nxb1 = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        sach_rs_btn = new javax.swing.JButton();
        sach_cancel_btn = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        sach_tf_ma = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        nv_panel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_nv = new javax.swing.JTable();
        nv_btn_search = new javax.swing.JButton();
        nv_search = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        nv_tf_tdn = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        nv_tf_sdt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        nv_tf_diachi = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        nv_tf_tuoi = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        nv_tf_cmnd = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        nv_nam = new javax.swing.JRadioButton();
        nv_nu = new javax.swing.JRadioButton();
        jLabel42 = new javax.swing.JLabel();
        nv_capbac = new javax.swing.JComboBox<>();
        nv_them_btn = new javax.swing.JButton();
        nv_del_btn = new javax.swing.JButton();
        nv_upd_btn = new javax.swing.JButton();
        nv_cancel_btn = new javax.swing.JButton();
        nv_rs_btn = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        nv_tf_tennv = new javax.swing.JTextField();
        nv_tf_manv = new javax.swing.JLabel();
        nv_reset_passwd = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        tt_tf_ma = new javax.swing.JLabel();
        tt_tf_ten = new javax.swing.JLabel();
        tt_tf_tdn = new javax.swing.JLabel();
        tt_tf_sdt = new javax.swing.JLabel();
        tt_tf_diachi = new javax.swing.JLabel();
        tt_tf_tuoi = new javax.swing.JLabel();
        tt_tf_cmnd = new javax.swing.JLabel();
        tt_tf_gioitinh = new javax.swing.JLabel();
        tt_tf_capbac = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(170, 251, 234));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HỆ THỐNG QUẢN LÝ THƯ VIỆN");

        jLabel40.setForeground(new java.awt.Color(51, 0, 255));
        jLabel40.setText("Đăng xuất");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        BIGDATA.setBackground(new java.awt.Color(255, 255, 255));
        BIGDATA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tab_pane.setBackground(new java.awt.Color(255, 255, 255));
        tab_pane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tab_pane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tab_pane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tab_pane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab_pane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dg_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Mã đọc giả");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Tên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Giới tính");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nghề nghiệp");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("CMND");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Địa chỉ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("SDT");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Ngày cấp thẻ");

        dg_tf_cmnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_tf_cmndActionPerformed(evt);
            }
        });

        tbl_dg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đọc giả", "Tên", "Giới tính", "Nghề nghiệp", "CMND", "Địa chỉ", "SDT", "Ngày cấp thẻ"
            }
        ));
        tbl_dg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dg);

        dg_them_btn.setBackground(new java.awt.Color(255, 255, 255));
        dg_them_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_them_btn.setText("Thêm");
        dg_them_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_them_btnActionPerformed(evt);
            }
        });

        dg_update_btn.setBackground(new java.awt.Color(255, 255, 255));
        dg_update_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_update_btn.setText("Sửa");
        dg_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_update_btnActionPerformed(evt);
            }
        });

        dg_del_btn.setBackground(new java.awt.Color(255, 255, 255));
        dg_del_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_del_btn.setText("Xóa");
        dg_del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_del_btnActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 255));
        jButton4.setText("Tìm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        dg_btn_group.add(dg_nam);
        dg_nam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_nam.setForeground(new java.awt.Color(0, 0, 255));
        dg_nam.setText("Nam");

        dg_btn_group.add(dg_nu);
        dg_nu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_nu.setForeground(new java.awt.Color(255, 0, 51));
        dg_nu.setText("Nữ");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Giới tính");

        dg_search_gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nam", "nu" }));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 0, 0));
        jLabel51.setText("*");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 0, 0));
        jLabel52.setText("*");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 0, 0));
        jLabel53.setText("*");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 0, 0));
        jLabel55.setText("*");

        dg_rs_btn.setBackground(new java.awt.Color(255, 255, 255));
        dg_rs_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_rs_btn.setText("Reset");
        dg_rs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_rs_btnActionPerformed(evt);
            }
        });

        dg_cancel_btn.setBackground(new java.awt.Color(255, 255, 255));
        dg_cancel_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dg_cancel_btn.setText("Hủy");
        dg_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dg_cancel_btnActionPerformed(evt);
            }
        });

        jButton6.setText("Refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 0, 0));
        jLabel73.setText("*");

        javax.swing.GroupLayout dg_panelLayout = new javax.swing.GroupLayout(dg_panel);
        dg_panel.setLayout(dg_panelLayout);
        dg_panelLayout.setHorizontalGroup(
            dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dg_panelLayout.createSequentialGroup()
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dg_panelLayout.createSequentialGroup()
                                .addComponent(dg_nam)
                                .addGap(18, 18, 18)
                                .addComponent(dg_nu))
                            .addComponent(dg_tf_nghe, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(dg_tf_tendg)
                            .addComponent(dg_tf_madg)))
                    .addGroup(dg_panelLayout.createSequentialGroup()
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dg_them_btn)
                            .addComponent(dg_rs_btn))
                        .addGap(44, 44, 44)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dg_cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dg_panelLayout.createSequentialGroup()
                                .addComponent(dg_del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(dg_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(dg_panelLayout.createSequentialGroup()
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(15, 15, 15)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dg_tf_cmnd, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(dg_tf_diachi, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(dg_tf_sdt, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(dg_ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel55)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(dg_panelLayout.createSequentialGroup()
                        .addComponent(dg_tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel48)
                        .addGap(29, 29, 29)
                        .addComponent(dg_search_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButton6)
                        .addGap(0, 302, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dg_panelLayout.setVerticalGroup(
            dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(dg_search_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6))
                    .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(dg_tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)
                        .addComponent(dg_tf_madg)))
                .addGap(18, 18, 18)
                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dg_panelLayout.createSequentialGroup()
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(dg_tf_tendg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dg_nam)
                            .addComponent(dg_nu)
                            .addComponent(jLabel52))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dg_tf_nghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dg_tf_cmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(dg_tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dg_panelLayout.createSequentialGroup()
                                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(dg_tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55))
                                .addGap(14, 14, 14)
                                .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(dg_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel73))
                        .addGap(16, 16, 16)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dg_them_btn)
                            .addComponent(dg_update_btn)
                            .addComponent(dg_del_btn))
                        .addGap(18, 18, 18)
                        .addGroup(dg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dg_rs_btn)
                            .addComponent(dg_cancel_btn)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 174, Short.MAX_VALUE))
        );

        tab_pane.addTab("Đọc giả", dg_panel);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setText("Đọc giả");

        jLabel80.setText("Ngày");

        jLabel81.setText("Nhân viên");

        tbl_phieumuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng"
            }
        ));
        jScrollPane8.setViewportView(tbl_phieumuon);

        phieumuon_crntDate.setText("date");

        phieumuon_crntUser.setText("jLabel22");

        jLabel22.setText("Tiền cọc");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addGap(18, 18, 18)
                                .addComponent(phieumuon_crntUser, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel80))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phieumuon_tf_tien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phieumuon_tf_madg, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phieumuon_crntDate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(phieumuon_tf_madg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(phieumuon_tf_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(phieumuon_crntDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(phieumuon_crntUser))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel82.setText("Sách");

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setText("Tìm");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tbl_msach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng"
            }
        ));
        jScrollPane9.setViewportView(tbl_msach);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(18, 18, 18)
                        .addComponent(msach_tf_tensach, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(msach_tf_tensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton26.setText("Hoàn tất");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton25.setText("Thêm");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton11.setText("Xóa");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton25)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jSeparator3))
        );

        tab_pane.addTab("Mượn sách", jPanel2);

        qlpm_pane.setBackground(new java.awt.Color(255, 255, 255));

        tbl_pmTongHop.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_pmTongHop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pmTongHopMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_pmTongHop);

        jLabel41.setText("Ngày Thuê");

        jButton3.setText("Tìm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel83.setText("Trạng thái");

        pmTongHop_tt_search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dang muon", "qua han", "da tra" }));
        pmTongHop_tt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmTongHop_tt_searchActionPerformed(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel84.setText("Mã phiếu");

        jLabel85.setText("Ngày");

        jLabel86.setText("Tiền cọc");

        jLabel87.setText("Đọc giả");

        tbl_qlpm_ctpm.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(tbl_qlpm_ctpm);

        btn_pmTongHop_update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pmTongHop_update.setText("Cập nhật");
        btn_pmTongHop_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pmTongHop_updateActionPerformed(evt);
            }
        });

        btn_pmTongHop_delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pmTongHop_delete.setText("Xóa");
        btn_pmTongHop_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pmTongHop_deleteActionPerformed(evt);
            }
        });

        btn_pmTongHop_cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pmTongHop_cancel.setText("Hủy");
        btn_pmTongHop_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pmTongHop_cancelActionPerformed(evt);
            }
        });

        btn_pmTongHop_xoaPM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pmTongHop_xoaPM.setText("Xóa Phiếu Mượn");
        btn_pmTongHop_xoaPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pmTongHop_xoaPMActionPerformed(evt);
            }
        });

        btn_qlpm_suaSach.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_qlpm_suaSach.setText("Sửa sách");
        btn_qlpm_suaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qlpm_suaSachActionPerformed(evt);
            }
        });

        jLabel88.setText("Ngày trả");

        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout qlpm_paneLayout = new javax.swing.GroupLayout(qlpm_pane);
        qlpm_pane.setLayout(qlpm_paneLayout);
        qlpm_paneLayout.setHorizontalGroup(
            qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(qlpm_paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pmTongHop_ngaythue_search, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel83)
                        .addGap(18, 18, 18)
                        .addComponent(pmTongHop_tt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addComponent(btn_pmTongHop_xoaPM)
                                .addGap(68, 68, 68)
                                .addComponent(btnPrint)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel84)
                                            .addComponent(jLabel85))
                                        .addGap(18, 18, 18)
                                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_pmTongHop_ngayThue)
                                            .addComponent(lbl_pmTongHop_maphieu)))
                                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel88)
                                            .addComponent(jLabel87))
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_pmTongHop_docGia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_pmTongHop_NgayTra)
                                            .addComponent(txt_pmTongHop_tienCoc))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_pmTongHop_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_pmTongHop_update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_pmTongHop_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlpm_paneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_qlpm_suaSach))
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel86))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        qlpm_paneLayout.setVerticalGroup(
            qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlpm_paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel83)
                        .addComponent(pmTongHop_tt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton7))
                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pmTongHop_ngaythue_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(2, 2, 2)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qlpm_paneLayout.createSequentialGroup()
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel84)
                                    .addComponent(lbl_pmTongHop_maphieu))
                                .addGap(18, 18, 18)
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel85)
                                    .addComponent(lbl_pmTongHop_ngayThue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel88)
                                    .addComponent(lbl_pmTongHop_NgayTra))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(qlpm_paneLayout.createSequentialGroup()
                                .addComponent(btn_pmTongHop_update)
                                .addGap(18, 18, 18)
                                .addComponent(btn_pmTongHop_delete)
                                .addGap(18, 18, 18)
                                .addComponent(btn_pmTongHop_cancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(txt_pmTongHop_tienCoc))
                        .addGap(30, 30, 30)
                        .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(txt_pmTongHop_docGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qlpm_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_qlpm_suaSach)
                    .addComponent(btn_pmTongHop_xoaPM)
                    .addComponent(btnPrint))
                .addGap(48, 48, 48))
        );

        tab_pane.addTab("Quản lý phiếu mượn", qlpm_pane);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        tbl_trasach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Ngày", "Đọc giả", "Nhân viên", "Trạng thái"
            }
        ));
        tbl_trasach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_trasachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_trasach);

        jLabel16.setText("Mã phiếu");

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton23.setText("Tìm");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tbl_trasach_ctpm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng"
            }
        ));
        jScrollPane10.setViewportView(tbl_trasach_ctpm);

        jLabel18.setText("Mã phiếu");

        jLabel19.setText("Ngày trả");

        jLabel20.setText("Đọc giả");

        jLabel21.setText("Nhân viên");

        btn_trasach_trahet.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_trasach_trahet.setText("Trả hết");
        btn_trasach_trahet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trasach_trahetActionPerformed(evt);
            }
        });

        btn_trasach_giahan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_trasach_giahan.setText("Gia hạn");
        btn_trasach_giahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trasach_giahanActionPerformed(evt);
            }
        });

        jLabel89.setText("Tiền cọc");

        jLabel17.setText("Ngày mượn");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btn_trasach_giahan, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_trasach_trahet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_TraSach_ngayTra)
                                    .addComponent(lbl_TraSach_dg)
                                    .addComponent(lbl_TraSach_maphieu)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel89))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_TraSach_tiencoc)
                                    .addComponent(lbl_TraSach_nhanvien)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_TraSach_ngayThue)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lbl_TraSach_maphieu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lbl_TraSach_ngayTra))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lbl_TraSach_dg))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(lbl_TraSach_tiencoc))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(lbl_TraSach_nhanvien))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lbl_TraSach_ngayThue))
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_trasach_trahet, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(btn_trasach_giahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        tab_pane.addTab("Trả sách", jPanel13);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Thể loại");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Nhà xuất bản");

        tbl_tl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thể loại", "Tên thể loại"
            }
        ));
        tbl_tl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tlMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_tl);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Mã thể loại");

        tl_tf_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_tf_tenActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Tên thể loại");

        tl_them_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tl_them_btn.setText("Thêm");
        tl_them_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_them_btnActionPerformed(evt);
            }
        });

        tl_del_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tl_del_btn.setText("Xóa");
        tl_del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_del_btnActionPerformed(evt);
            }
        });

        tl_update_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tl_update_btn.setText("Sửa");
        tl_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_update_btnActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton16.setText("Tìm");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Mã NXB");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Địa chỉ");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Tên");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("SDT");

        nxb_them_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nxb_them_btn.setText("Thêm");
        nxb_them_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxb_them_btnActionPerformed(evt);
            }
        });

        nxb_del_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nxb_del_btn.setText("Xóa");
        nxb_del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxb_del_btnActionPerformed(evt);
            }
        });

        nxb_update_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nxb_update_btn.setText("Sửa");
        nxb_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxb_update_btnActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton20.setText("Tìm");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        tbl_nxb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NXB", "Tên", "Địa chỉ", "SDT"
            }
        ));
        tbl_nxb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nxbMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_nxb);

        tl_rs_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tl_rs_btn.setText("Reset");
        tl_rs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_rs_btnActionPerformed(evt);
            }
        });

        tl_cancel_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tl_cancel_btn.setText("Hủy");
        tl_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tl_cancel_btnActionPerformed(evt);
            }
        });

        nxb_rs_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nxb_rs_btn.setText("Reset");
        nxb_rs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxb_rs_btnActionPerformed(evt);
            }
        });

        nxb_cancel_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nxb_cancel_btn.setText("Hủy");
        nxb_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxb_cancel_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tl_tf_ten)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(tl_tf_matl)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tl_them_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tl_rs_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tl_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tl_del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tl_cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_tf_sdt))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_tf_manxb)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_tf_ten))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(nxb_them_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_rs_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nxb_cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(tl_tf_matl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tl_tf_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tl_them_btn)
                            .addComponent(tl_del_btn)
                            .addComponent(jButton16)
                            .addComponent(tl_update_btn)
                            .addComponent(tl_rs_btn)
                            .addComponent(tl_cancel_btn))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel38)
                            .addComponent(nxb_tf_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nxb_tf_manxb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(nxb_tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(nxb_tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nxb_them_btn)
                            .addComponent(nxb_del_btn)
                            .addComponent(jButton20)
                            .addComponent(nxb_update_btn)
                            .addComponent(nxb_rs_btn)
                            .addComponent(nxb_cancel_btn))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        tab_pane.addTab("Phân loại", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Mã sách");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Tựa sách");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Số trang");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Ngôn ngữ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Số lượng tồn");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Thể loại");

        sach_tf_sotrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_tf_sotrangActionPerformed(evt);
            }
        });

        sach_tf_ngonngu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_tf_ngonnguActionPerformed(evt);
            }
        });

        tbl_sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tựa sách", "Số trang", "Ngôn ngữ", "Số lượng tồn", "Mã thể loại", "Kệ số"
            }
        ));
        tbl_sach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sach);

        sach_them_btn.setText("Thêm");
        sach_them_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_them_btnActionPerformed(evt);
            }
        });

        sach_del_btn.setText("Xóa");
        sach_del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_del_btnActionPerformed(evt);
            }
        });

        sach_update_btn.setText("Sửa");
        sach_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_update_btnActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setText("Tìm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("NXB");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Thể loại");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("NXB");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 0, 51));
        jLabel74.setText("*");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 0, 51));
        jLabel75.setText("*");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 0, 51));
        jLabel77.setText("*");

        sach_rs_btn.setText("Reset");
        sach_rs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_rs_btnActionPerformed(evt);
            }
        });

        sach_cancel_btn.setText("Hủy");
        sach_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_cancel_btnActionPerformed(evt);
            }
        });

        jButton14.setText("Refresh");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 0, 51));
        jLabel76.setText("*");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(36, 36, 36)
                                .addComponent(sach_tf_ngonngu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sach_tf_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(sach_tf_sotrang)
                                    .addComponent(sach_tf_ma))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel74))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(67, 67, 67)
                        .addComponent(sach_nxb, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sach_tf_sl, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(sach_tl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addComponent(jLabel76)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sach_them_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sach_rs_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sach_cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(sach_del_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sach_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sach_tf_ten_search, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)
                                .addComponent(sach_tl1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(sach_nxb1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(sach_tf_ten_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(sach_tf_ma))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(sach_tf_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)
                            .addComponent(jLabel43)
                            .addComponent(sach_tl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(sach_nxb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(sach_tf_sotrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(sach_tf_ngonngu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(sach_tf_sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(sach_tl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(sach_nxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sach_del_btn)
                            .addComponent(sach_them_btn)
                            .addComponent(sach_update_btn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sach_rs_btn)
                            .addComponent(sach_cancel_btn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(167, Short.MAX_VALUE))))
        );

        tab_pane.addTab("Sách", jPanel3);

        nv_panel.setBackground(new java.awt.Color(255, 255, 255));

        tbl_nv.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_nv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nvMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_nv);

        nv_btn_search.setBackground(new java.awt.Color(255, 255, 255));
        nv_btn_search.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nv_btn_search.setText("Tìm");
        nv_btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_btn_searchActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Tên");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Mã nhân viên");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Tên");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Tên Đăng Nhập");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("SDT");

        nv_tf_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_tf_sdtActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Địa chỉ");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Tuổi");

        nv_tf_tuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_tf_tuoiActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("CMND");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Giới tính");

        nv_btn_group.add(nv_nam);
        nv_nam.setText("Nam");
        nv_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_namActionPerformed(evt);
            }
        });

        nv_btn_group.add(nv_nu);
        nv_nu.setText("Nữ");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Cấp bậc");

        nv_capbac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "boss", "quan ly", "nhan vien" }));

        nv_them_btn.setBackground(new java.awt.Color(255, 255, 255));
        nv_them_btn.setText("Thêm");
        nv_them_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_them_btnActionPerformed(evt);
            }
        });

        nv_del_btn.setBackground(new java.awt.Color(255, 255, 255));
        nv_del_btn.setText("Xóa");
        nv_del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_del_btnActionPerformed(evt);
            }
        });

        nv_upd_btn.setBackground(new java.awt.Color(255, 255, 255));
        nv_upd_btn.setText("Sửa");
        nv_upd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_upd_btnActionPerformed(evt);
            }
        });

        nv_cancel_btn.setBackground(new java.awt.Color(255, 255, 255));
        nv_cancel_btn.setText("Hủy");
        nv_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_cancel_btnActionPerformed(evt);
            }
        });

        nv_rs_btn.setBackground(new java.awt.Color(255, 255, 255));
        nv_rs_btn.setText("Reset");
        nv_rs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_rs_btnActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 0, 51));
        jLabel65.setText("*");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 0, 51));
        jLabel66.setText("*");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 0, 51));
        jLabel67.setText("*");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 0, 51));
        jLabel68.setText("*");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 0, 51));
        jLabel69.setText("*");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 0, 51));
        jLabel70.setText("*");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 0, 51));
        jLabel72.setText("*");

        nv_reset_passwd.setBackground(new java.awt.Color(255, 255, 255));
        nv_reset_passwd.setText("Reset Pass");
        nv_reset_passwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nv_reset_passwdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel47)
                                            .addComponent(jLabel24))
                                        .addGap(18, 18, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel25)
                                        .addGap(81, 81, 81)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nv_tf_tdn)
                                    .addComponent(nv_tf_sdt)
                                    .addComponent(nv_tf_tennv, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel42))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nv_nam)
                                        .addGap(18, 18, 18)
                                        .addComponent(nv_nu)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(nv_capbac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel26)
                                            .addComponent(jLabel28))
                                        .addGap(65, 65, 65)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nv_tf_diachi, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                            .addComponent(nv_tf_tuoi)
                                            .addComponent(nv_tf_cmnd)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(28, 28, 28)
                                        .addComponent(nv_tf_manv)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel67))
                                    .addComponent(jLabel68))
                                .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel65)
                            .addComponent(jLabel72))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nv_them_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv_rs_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nv_del_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv_cancel_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nv_upd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv_reset_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(nv_tf_manv))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(nv_tf_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(nv_tf_tdn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv_tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel67))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(nv_tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(nv_tf_tuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(nv_tf_cmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(nv_nam)
                    .addComponent(nv_nu)
                    .addComponent(jLabel70))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv_capbac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel72))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv_them_btn)
                    .addComponent(nv_del_btn)
                    .addComponent(nv_upd_btn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv_cancel_btn)
                    .addComponent(nv_rs_btn)
                    .addComponent(nv_reset_passwd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton13.setText("Refresh");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nv_panelLayout = new javax.swing.GroupLayout(nv_panel);
        nv_panel.setLayout(nv_panelLayout);
        nv_panelLayout.setHorizontalGroup(
            nv_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nv_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(nv_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                    .addGroup(nv_panelLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nv_search, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nv_btn_search)
                        .addGap(123, 123, 123)
                        .addComponent(jButton13)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        nv_panelLayout.setVerticalGroup(
            nv_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nv_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nv_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, nv_panelLayout.createSequentialGroup()
                        .addGroup(nv_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nv_btn_search)
                            .addComponent(nv_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jButton13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pane.addTab("Quản lý nhân viên", nv_panel);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Mã nhân viên");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setText("Tên");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("Tên đăng nhập");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("SDT");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("Địa chỉ");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("Tuổi");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("CMND");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("Giới tính");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setText("Quyền");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setText("Đổi mật khẩu");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText("Đăng xuất");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel63.setText("Phần mềm");

        jLabel71.setText("Hệ thống quản lý thư viện v0.7 Beta");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel56)
                            .addComponent(jLabel54)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel64))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tt_tf_ma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tt_tf_ten)
                                    .addComponent(tt_tf_tdn)
                                    .addComponent(tt_tf_sdt)
                                    .addComponent(tt_tf_diachi)
                                    .addComponent(tt_tf_tuoi)
                                    .addComponent(tt_tf_cmnd)
                                    .addComponent(tt_tf_gioitinh)
                                    .addComponent(tt_tf_capbac))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel63))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(tt_tf_ma))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(tt_tf_ten))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(tt_tf_tdn))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(tt_tf_sdt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(tt_tf_diachi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(tt_tf_tuoi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(tt_tf_cmnd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(tt_tf_gioitinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(tt_tf_capbac))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton10))
                        .addContainerGap(187, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pane.addTab("Thông tin", jPanel8);

        javax.swing.GroupLayout BIGDATALayout = new javax.swing.GroupLayout(BIGDATA);
        BIGDATA.setLayout(BIGDATALayout);
        BIGDATALayout.setHorizontalGroup(
            BIGDATALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 1354, Short.MAX_VALUE)
        );
        BIGDATALayout.setVerticalGroup(
            BIGDATALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BIGDATALayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tab_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BIGDATA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BIGDATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel40MouseClicked

    private void tbl_tlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tlMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            tl_tf_matl.setText(String.valueOf(tbl_tl_model.getValueAt(tbl_tl.getSelectedRow(), 0)));
            tl_tf_ten.setText(String.valueOf(tbl_tl_model.getValueAt(tbl_tl.getSelectedRow(), 1)));
            
            tl_del_btn.setVisible(true);
            tl_cancel_btn.setVisible(true);
            tl_update_btn.setVisible(true);
            
            tl_them_btn.setVisible(false);
            tl_rs_btn.setVisible(false);
        }
    }//GEN-LAST:event_tbl_tlMouseClicked

    private void tl_tf_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_tf_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tl_tf_tenActionPerformed

    private void tl_them_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_them_btnActionPerformed
        if(!validateTheLoaiForm()){
            return;
        }
        TheLoai tl = new TheLoai(tl_tf_ten.getText());
        TheLoaiDAO tlDAO = new TheLoaiDAO();
        tlDAO.saveTheLoai(tl);
        resetFormTheLoai();
        showTableTheLoai();

        sach_tl.removeAllItems();
        addDataFromListToComboBox(sach_tl,new TheLoaiDAO().getDistinctTheLoai());

    }//GEN-LAST:event_tl_them_btnActionPerformed

    private void tl_del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_del_btnActionPerformed
        TheLoai tl = new TheLoai(tl_tf_ten.getText());
        tl.setMatl(Integer.parseInt(tl_tf_matl.getText()));
        TheLoaiDAO tlDAO = new TheLoaiDAO();
        tlDAO.deleteTheLoai(tl);
        resetFormTheLoai();
        showTableTheLoai();
        tl_del_btn.setVisible(false);
        tl_cancel_btn.setVisible(false);
        tl_update_btn.setVisible(false);
        
        tl_them_btn.setVisible(true);
        tl_rs_btn.setVisible(true);
    }//GEN-LAST:event_tl_del_btnActionPerformed

    private void tl_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_update_btnActionPerformed
if(!validateTheLoaiForm()){
    return;
}
        TheLoai tl = new TheLoai(tl_tf_ten.getText());
        tl.setMatl(Integer.parseInt(tl_tf_matl.getText()));
        TheLoaiDAO tlDAO = new TheLoaiDAO();
        tlDAO.updateTheLoai(tl);
        resetFormTheLoai();
        showTableTheLoai();
        tl_del_btn.setVisible(false);
        tl_cancel_btn.setVisible(false);
        tl_update_btn.setVisible(false);
        
        tl_them_btn.setVisible(true);
        tl_rs_btn.setVisible(true);
    }//GEN-LAST:event_tl_update_btnActionPerformed

    private void nxb_them_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxb_them_btnActionPerformed
        
        NhaXuatBan nxb = new NhaXuatBan(nxb_tf_diachi.getText(), nxb_tf_sdt.getText(), nxb_tf_ten.getText());
        if(!validateNhaXuatBanForm()){
            return;
        }else{
            NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
            nxbDAO.saveNhaXuatBan(nxb);
            resetFormNhaXuatBan();
            showTableNhaXuatBan();
            
            sach_nxb.removeAllItems();
            addDataFromListToComboBox(sach_nxb,new NhaXuatBanDAO().getDistinctNhaXuatBan());
        }
        

        

    }//GEN-LAST:event_nxb_them_btnActionPerformed

    private void nxb_del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxb_del_btnActionPerformed
        NhaXuatBan nxb = new NhaXuatBan(nxb_tf_diachi.getText(), nxb_tf_sdt.getText(), nxb_tf_ten.getText());
        nxb.setManxb(Integer.parseInt(nxb_tf_manxb.getText()));
        NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
        nxbDAO.deleteNhaXuatBan(nxb);
        resetFormNhaXuatBan();
        showTableNhaXuatBan();
        nxb_del_btn.setVisible(false);
        nxb_cancel_btn.setVisible(false);
        nxb_update_btn.setVisible(false);
        
        nxb_them_btn.setVisible(true);
        nxb_rs_btn.setVisible(true);
    }//GEN-LAST:event_nxb_del_btnActionPerformed

    private void nxb_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxb_update_btnActionPerformed
        NhaXuatBan nxb = new NhaXuatBan(nxb_tf_diachi.getText(), nxb_tf_sdt.getText(), nxb_tf_ten.getText());
        nxb.setManxb(Integer.parseInt(nxb_tf_manxb.getText()));
        if(!validateNhaXuatBanForm()){
            return;
        }else{
            NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
            nxbDAO.updateNhaXuatBan(nxb);
            
            resetFormNhaXuatBan();
            showTableNhaXuatBan();
            nxb_del_btn.setVisible(false);
        nxb_cancel_btn.setVisible(false);
        nxb_update_btn.setVisible(false);
        
        nxb_them_btn.setVisible(true);
        nxb_rs_btn.setVisible(true);
        }
        
    }//GEN-LAST:event_nxb_update_btnActionPerformed

    private void tbl_nxbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nxbMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            nxb_tf_manxb.setText(String.valueOf(tbl_nxb_model.getValueAt(tbl_nxb.getSelectedRow(), 0)));
            nxb_tf_ten.setText(String.valueOf(tbl_nxb_model.getValueAt(tbl_nxb.getSelectedRow(), 1)));
            nxb_tf_diachi.setText(String.valueOf(tbl_nxb_model.getValueAt(tbl_nxb.getSelectedRow(), 2)));
            nxb_tf_sdt.setText(String.valueOf(tbl_nxb_model.getValueAt(tbl_nxb.getSelectedRow(), 3)));
            
            nxb_del_btn.setVisible(true);
            nxb_cancel_btn.setVisible(true);
            nxb_update_btn.setVisible(true);
            
            nxb_them_btn.setVisible(false);
            nxb_rs_btn.setVisible(false);
        }
    }//GEN-LAST:event_tbl_nxbMouseClicked

    private void sach_tf_sotrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_tf_sotrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sach_tf_sotrangActionPerformed

    private void sach_tf_ngonnguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_tf_ngonnguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sach_tf_ngonnguActionPerformed

    private void sach_them_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_them_btnActionPerformed
Sach s = null;
        if(!validateSachForm()){
            return;
        }       

        s = new Sach(sach_tf_ten.getText(),Integer.parseInt(sach_tf_sotrang.getText()),sach_tf_ngonngu.getText(),Integer.parseInt(sach_tf_sl.getText()));
        TheLoai tl = new TheLoaiDAO().filterTheLoai(String.valueOf(sach_tl.getSelectedItem())).get(0);
        NhaXuatBan nxb = new NhaXuatBanDAO().filterNhaXuatBan(String.valueOf(sach_nxb.getSelectedItem())).get(0);
        s.setTl(tl);
        s.setNxb(nxb);
        SachDAO sDAO = new SachDAO();
        List<Sach> list = sDAO.filterSach(sach_tf_ten.getText());
        if(list.isEmpty()){
            sDAO.saveSach(s);
            resetFormSach();
            //reset tbl sach form muonsach avoid stack
            resetTable(tbl_msach_model);
            showTableSach();
        }else{
            JOptionPane.showMessageDialog(null, "Sach da co, vui long cap nhat so luong","LOI",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sach_them_btnActionPerformed

    private void sach_del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_del_btnActionPerformed
        Sach s = new Sach(sach_tf_ten.getText(),Integer.parseInt(sach_tf_sotrang.getText()),sach_tf_ngonngu.getText(),Integer.parseInt(sach_tf_sl.getText()));
        s.setMasach(Integer.parseInt(sach_tf_ma.getText()));
        TheLoai tl = new TheLoaiDAO().filterTheLoai(String.valueOf(sach_tl.getSelectedItem())).get(0);
        NhaXuatBan nxb = new NhaXuatBanDAO().filterNhaXuatBan(String.valueOf(sach_nxb.getSelectedItem())).get(0);
        s.setTl(tl);
        s.setNxb(nxb);
        SachDAO sDAO = new SachDAO();                
        sDAO.deleteSach(s);
        resetFormSach();
        showTableSach();
        sach_del_btn.setVisible(false);
        sach_cancel_btn.setVisible(false);
        sach_update_btn.setVisible(false);
        
        sach_them_btn.setVisible(true);
        sach_rs_btn.setVisible(true);
    }//GEN-LAST:event_sach_del_btnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            SachDAO sDAO = new SachDAO();
        
        
        List<Sach> listS = sDAO.filterSach(sach_tf_ten_search.getText(),String.valueOf(sach_nxb1.getSelectedItem()),String.valueOf(sach_tl1.getSelectedItem()) );
        resetTable(tbl_sach_model);
        if(!listS.isEmpty()){
            
            listS.forEach(e->{
                Object[] tmpRow = {
                   e.getMasach(), e.getTensach(),e.getSotrang(),e.getNgonngu()
                ,e.getSoluongton(),e.getNxb().getTen(),e.getTl().getTentl()
                };
                tbl_sach_model.addRow(tmpRow);
            });

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbl_nvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nvMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            nv_tf_manv.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 0)));
            nv_tf_tennv.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 1)));
            nv_tf_tdn.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 2)));
//            nv_tf_mk.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 3)));
            nv_tf_sdt.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 4)));
            nv_tf_diachi.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 5)));
            nv_tf_tuoi.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 6)));
            nv_tf_cmnd.setText(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 7)));
            if(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 8)).equalsIgnoreCase("nam")){
                nv_nam.setSelected(true);
                nv_nu.setSelected(false);
            }else{
                nv_nam.setSelected(false);
                nv_nu.setSelected(true);
            }

            nv_capbac.setSelectedItem(String.valueOf(tbl_nv_model.getValueAt(tbl_nv.getSelectedRow(), 9)));
        }
        nv_del_btn.setVisible(true);
        nv_cancel_btn.setVisible(true);
        nv_upd_btn.setVisible(true);
        nv_reset_passwd.setVisible(true);
        
        nv_them_btn.setVisible(false);
        nv_rs_btn.setVisible(false);
    }//GEN-LAST:event_tbl_nvMouseClicked

    private void nv_btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_btn_searchActionPerformed

        List<NhanVien> nv = new NhanVienDAO().filterNhanVien(nv_search.getText());
        resetTable(tbl_nv_model);
        if(!nv.isEmpty()){
            
            nv.forEach(e->{
                Object[] tmpRow = {
                    e.getManv(),e.getTennv(),e.getTendangnhap(),e.getMatkhau()
                    ,e.getSdt(),e.getDiachi(),e.getTuoi(),e.getCMND()
                    ,e.getGioitinh(),e.getCapbac()
                };
                tbl_nv_model.addRow(tmpRow);
            });

        }
    }//GEN-LAST:event_nv_btn_searchActionPerformed

    private void nv_tf_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_tf_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nv_tf_sdtActionPerformed

    private void nv_tf_tuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_tf_tuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nv_tf_tuoiActionPerformed

    private void nv_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nv_namActionPerformed

    private void nv_them_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_them_btnActionPerformed
        NhanVien nv=null;
        if(!validateNhanVienForm()){
            return;
        }

        if(String.valueOf(nv_capbac.getSelectedItem()).equalsIgnoreCase("boss")){
            JOptionPane.showMessageDialog(null,"Không có quyền thêm boss","LỖI",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(nv_nam.isSelected()){
            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),"1", nv_tf_sdt.getText(), nv_tf_diachi.getText(),  nv_tf_cmnd.getText(), "nam", String.valueOf(nv_capbac.getSelectedItem()));
        }else{
            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),"1", nv_tf_sdt.getText(), nv_tf_diachi.getText(),  nv_tf_cmnd.getText(), "nu", String.valueOf(nv_capbac.getSelectedItem()));
        }

        NhanVienDAO nvDAO = new NhanVienDAO();
        List<NhanVien> list = nvDAO.filterNhanVien(nv_tf_tdn.getText());
        if(list.isEmpty()){
            nvDAO.saveNhanVien(nv);
            resetFormNhanVien();
            showTableNhanVien();
        }else{
            JOptionPane.showMessageDialog(null, "Tai Khoan da ton tai", "LOI", JOptionPane.ERROR_MESSAGE);
            nv_tf_tdn.setText("");
            nv_tf_tdn.requestFocus();
        }
    }//GEN-LAST:event_nv_them_btnActionPerformed

    private void nv_del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_del_btnActionPerformed

        DAO dao = new DAO();
        dao.beginTransaction();
        NhanVien nv = (NhanVien) dao.getObject(new NhanVien(), Integer.parseInt(nv_tf_manv.getText()));
        
        
        if(crntUser.getCapbac().equalsIgnoreCase("boss")){
            //if current logged in user is boss            
            if(nv.getCapbac().equalsIgnoreCase("boss")){
                
            }else{
                dao.DeleteObject(nv);
                dao.commitTransaction();
                dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            }
                
            
        }else{
            if(nv.getCapbac().equalsIgnoreCase("boss")){
                //if picked user is boss
            }else{
                
                dao.DeleteObject(nv);
                dao.commitTransaction();
                dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            }
        }
    }//GEN-LAST:event_nv_del_btnActionPerformed

    private void nv_upd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_upd_btnActionPerformed
        if(!validateNhanVienForm()){
            return;
        }
        DAO dao = new DAO();
        dao.beginTransaction();
        NhanVien nv = (NhanVien) dao.getObject(new NhanVien(), Integer.parseInt(nv_tf_manv.getText()));
//        if(nv_nam.isSelected()){
//            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),nv_tf_mk.getText(), nv_tf_sdt.getText(), nv_tf_diachi.getText(), Integer.parseInt(nv_tf_tuoi.getText()), nv_tf_cmnd.getText(), "nam", String.valueOf(nv_capbac.getSelectedItem()));
//        }else{
//            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),nv_tf_mk.getText(), nv_tf_sdt.getText(), nv_tf_diachi.getText(), Integer.parseInt(nv_tf_tuoi.getText()), nv_tf_cmnd.getText(), "nu", String.valueOf(nv_capbac.getSelectedItem()));
//        }
//        nv.setManv(Integer.parseInt(nv_tf_manv.getText()));
        
        
        if(crntUser.getCapbac().equalsIgnoreCase("boss")){
            //if current logged in user is boss            
                //nvDAO.updateNhanVien(nv);
                dao.Update(nv);
                dao.commitTransaction();
                dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            
        }else{
            if(nv.getCapbac().equalsIgnoreCase("boss")){
                //if picked user is boss
            }else{
                
               dao.Update(nv);
               dao.commitTransaction();
               dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            }
        }
    }//GEN-LAST:event_nv_upd_btnActionPerformed

    private void nv_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_cancel_btnActionPerformed
        resetFormNhanVien();
        tbl_nv.clearSelection();
        nv_del_btn.setVisible(false);
        nv_cancel_btn.setVisible(false);
        nv_upd_btn.setVisible(false);
        nv_reset_passwd.setVisible(false);
        
        nv_them_btn.setVisible(true);
        nv_rs_btn.setVisible(true);
    }//GEN-LAST:event_nv_cancel_btnActionPerformed

    private void nv_rs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_rs_btnActionPerformed
        resetFormNhanVien();
        showTableNhanVien();
    }//GEN-LAST:event_nv_rs_btnActionPerformed

    private void dg_tf_cmndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_tf_cmndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dg_tf_cmndActionPerformed

    private void tbl_dgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dgMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            dg_tf_madg.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 0)));
            dg_tf_tendg.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 1)));

            if(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 2)).equalsIgnoreCase("nam")){
                dg_nam.setSelected(true);
                dg_nu.setSelected(false);
            }else{
                dg_nam.setSelected(false);
                dg_nu.setSelected(true);
            }

            dg_tf_nghe.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 3)));
            dg_tf_cmnd.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 4)));
            dg_tf_sdt.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 5)));
            dg_tf_diachi.setText(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 6)));

            try {
                dg_ngay.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tbl_dg_model.getValueAt(tbl_dg.getSelectedRow(), 7))) );
                dg_del_btn.setVisible(true);
                dg_cancel_btn.setVisible(true);
                dg_update_btn.setVisible(true);
                
                dg_them_btn.setVisible(false);
                dg_rs_btn.setVisible(false);
            } catch (ParseException ex) {

                System.out.println(ex.getCause());
            }
            
        }
    }//GEN-LAST:event_tbl_dgMouseClicked

    private void dg_them_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_them_btnActionPerformed
        DocGia dg=null;
        if(!validateDocGiaForm()){
            return;
        }
        if(dg_nam.isSelected()){
            dg = new DocGia( dg_tf_tendg.getText(), "nam", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
        }
        else{
            dg = new DocGia( dg_tf_tendg.getText(), "nu", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
        }
        DocGiaDAO dgDAO = new DocGiaDAO();
        dgDAO.saveDocGia(dg);
        resetFormDocGia();
        showTableDocGia();
    }//GEN-LAST:event_dg_them_btnActionPerformed

    private void dg_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_update_btnActionPerformed
        DocGia dg=null;
        if(!validateDocGiaForm()){
            return;
        }
        if(dg_nam.isSelected()){

            dg = new DocGia( dg_tf_tendg.getText(), "nam", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
            dg.setMadg(Integer.parseInt(dg_tf_madg.getText()));
            //System.out.println(dg.getMadg());
        }
        else{

            dg = new DocGia( dg_tf_tendg.getText(), "nu", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
            dg.setMadg(Integer.parseInt(dg_tf_madg.getText()));
            System.out.println(dg.getMadg());
        }
        DocGiaDAO dgDAO = new DocGiaDAO();
        dgDAO.updateDocGia(dg);
        showTableDocGia();
        resetFormDocGia();
        dg_del_btn.setVisible(false);
        dg_cancel_btn.setVisible(false);
        dg_update_btn.setVisible(false);
        
        dg_them_btn.setVisible(true);
        dg_rs_btn.setVisible(true);
    }//GEN-LAST:event_dg_update_btnActionPerformed

    private void dg_del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_del_btnActionPerformed
        DocGia dg=null;
        if(dg_nam.isSelected()){

            dg = new DocGia( dg_tf_tendg.getText(), "nam", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
            dg.setMadg(Integer.parseInt(dg_tf_madg.getText()));
//            System.out.println(dg.getMadg());
        }
        else{

            dg = new DocGia( dg_tf_tendg.getText(), "nu", dg_tf_nghe.getText(), dg_tf_cmnd.getText(), dg_tf_sdt.getText(), dg_tf_diachi.getText(), dg_ngay.getDate());
            dg.setMadg(Integer.parseInt(dg_tf_madg.getText()));
            System.out.println(dg.getMadg());
        }
        DocGiaDAO dgDAO = new DocGiaDAO();
        dgDAO.deleteDocGia(dg);
        showTableDocGia();
        resetFormDocGia();
        dg_del_btn.setVisible(false);
        dg_cancel_btn.setVisible(false);
        dg_update_btn.setVisible(false);
        
        dg_them_btn.setVisible(true);
        dg_rs_btn.setVisible(true);
    }//GEN-LAST:event_dg_del_btnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        List<DocGia> dg = new DocGiaDAO().filterDocGia(dg_tf_search.getText(),String.valueOf(dg_search_gioitinh.getSelectedItem()));
        if(!dg.isEmpty()){
            resetTable(tbl_dg_model);
            dg.forEach(e->{
                Object[] tmpRow = {
                    e.getMadg(),e.getTendg(),e.getGioitinh(),e.getNghenghiep()
                    ,e.getCmnd(),e.getSdt(),e.getDiachi(),e.getNgaycapthe()
                };
                tbl_dg_model.addRow(tmpRow);
            });
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        resetPassword rspForm = new resetPassword();
        rspForm.reopenFrame(this);
        rspForm.passNV(crntUser);
        rspForm.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void dg_rs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_rs_btnActionPerformed
        resetFormDocGia();
        showTableDocGia();
    }//GEN-LAST:event_dg_rs_btnActionPerformed

    private void dg_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dg_cancel_btnActionPerformed
        resetFormDocGia();
        tbl_dg.clearSelection();
        dg_del_btn.setVisible(false);
        dg_cancel_btn.setVisible(false);
        dg_update_btn.setVisible(false);
        
        dg_them_btn.setVisible(true);
        dg_rs_btn.setVisible(true);
    }//GEN-LAST:event_dg_cancel_btnActionPerformed

    private void sach_rs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_rs_btnActionPerformed
        resetFormSach();
        showTableSach();
    }//GEN-LAST:event_sach_rs_btnActionPerformed

    private void sach_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_cancel_btnActionPerformed
        resetFormSach();
        tbl_sach.clearSelection();
        sach_del_btn.setVisible(false);
        sach_cancel_btn.setVisible(false);
        sach_update_btn.setVisible(false);
        
        sach_them_btn.setVisible(true);
        sach_rs_btn.setVisible(true);
    }//GEN-LAST:event_sach_cancel_btnActionPerformed

    private void tl_rs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_rs_btnActionPerformed
        resetFormTheLoai();
        showTableTheLoai();
    }//GEN-LAST:event_tl_rs_btnActionPerformed

    private void tl_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tl_cancel_btnActionPerformed
        resetFormTheLoai();
        tbl_tl.clearSelection();
        tl_del_btn.setVisible(false);
        tl_cancel_btn.setVisible(false);
        tl_update_btn.setVisible(false);
        
        tl_them_btn.setVisible(true);
        tl_rs_btn.setVisible(true);
    }//GEN-LAST:event_tl_cancel_btnActionPerformed

    private void nxb_rs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxb_rs_btnActionPerformed
        resetFormNhaXuatBan();
        showTableNhaXuatBan();
    }//GEN-LAST:event_nxb_rs_btnActionPerformed

    private void nxb_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxb_cancel_btnActionPerformed
        resetFormNhaXuatBan();
        tbl_nxb.clearSelection();
        nxb_del_btn.setVisible(false);
        nxb_cancel_btn.setVisible(false);
        nxb_update_btn.setVisible(false);
        
        nxb_them_btn.setVisible(true);
        nxb_rs_btn.setVisible(true);
    }//GEN-LAST:event_nxb_cancel_btnActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(tbl_phieumuon.getSelectedRow()!=-1){
            tbl_phieumuon_model.removeRow(tbl_phieumuon.getSelectedRow());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        
        if(tbl_msach.getSelectedRow()!=-1){            
            if(tbl_phieumuon.getRowCount()>0){                
                for(int i = 0 ; i < tbl_phieumuon.getRowCount() ; i++){
                    if(Integer.parseInt(String.valueOf(tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 0)) )==Integer.parseInt(String.valueOf(tbl_phieumuon_model.getValueAt(i, 0)) ) ) {
                        if(Integer.parseInt(String.valueOf(tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 4)) ) > 0){
                            tbl_phieumuon_model.setValueAt(Integer.parseInt(String.valueOf(tbl_phieumuon_model.getValueAt(i, 2)) )+1,i, 2);                        
                            tbl_msach_model.setValueAt(Integer.parseInt(String.valueOf(tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 4)) )-1,tbl_msach.getSelectedRow(), 4);
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
                if(Integer.parseInt(String.valueOf(tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 4)) ) > 0){
                    tbl_msach_model.setValueAt(Integer.parseInt(String.valueOf(tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 4)) )-1,tbl_msach.getSelectedRow(), 4);
                    Object[] tmpRow = {tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 0), tbl_msach_model.getValueAt(tbl_msach.getSelectedRow(), 1),1};
                    tbl_phieumuon_model.addRow(tmpRow);      
                    tbl_msach.clearSelection();          
                }  
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void tbl_sachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sachMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            sach_tf_ma.setText(String.valueOf(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 0)));
            sach_tf_ten.setText(String.valueOf(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 1)));
            sach_tf_sotrang.setText(String.valueOf(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 2)));
            sach_tf_ngonngu.setText(String.valueOf(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 3)));
            sach_tf_sl.setText(String.valueOf(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 4)));
           
           sach_nxb.setSelectedItem(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 5));
            sach_tl.setSelectedItem(tbl_sach_model.getValueAt(tbl_sach.getSelectedRow(), 6));
        sach_del_btn.setVisible(true);
        sach_cancel_btn.setVisible(true);
        sach_update_btn.setVisible(true);
        
        sach_them_btn.setVisible(false);
        sach_rs_btn.setVisible(false);
        }
        
    }//GEN-LAST:event_tbl_sachMouseClicked

    private void sach_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_update_btnActionPerformed
         Sach s = null;
        if(!validateSachForm()){
            return;
        }       

        s = new Sach(sach_tf_ten.getText(),Integer.parseInt(sach_tf_sotrang.getText()),sach_tf_ngonngu.getText(),Integer.parseInt(sach_tf_sl.getText()));
        s.setMasach(Integer.parseInt(sach_tf_ma.getText()));
        TheLoai tl = new TheLoaiDAO().filterTheLoai(String.valueOf(sach_tl.getSelectedItem())).get(0);
        NhaXuatBan nxb = new NhaXuatBanDAO().filterNhaXuatBan(String.valueOf(sach_nxb.getSelectedItem())).get(0);
        s.setTl(tl);
        s.setNxb(nxb);
        
        SachDAO sDAO = new SachDAO();
        List<Sach> list = sDAO.filterSach(sach_tf_ten.getText());
        if(list.size()<=1){
            sDAO.updateSach(s);
            resetFormSach();
            showTableSach();
        sach_del_btn.setVisible(false);
        sach_cancel_btn.setVisible(false);
        sach_update_btn.setVisible(false);
        
        sach_them_btn.setVisible(true);
        sach_rs_btn.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Sach da co, vui long cap nhat so luong","LOI",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sach_update_btnActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
    if(!validatePhieuMuonForm()){
        return;
    }else{
        try {
            Date crntDate = new SimpleDateFormat("yyyy/MM/dd").parse(phieumuon_crntDate.getText());
            PhieuMuon pm = new PhieuMuon(Float.parseFloat(phieumuon_tf_tien.getText()),crntDate,crntUser,dg4phieumuon);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(crntDate);
            cal.add(Calendar.MONTH, 1);
            
            pm.setNgaytra(cal.getTime());
            pm.setTt("dang muon");
            DAO dao = new DAO();
            dao.beginTransaction();            
            for(int i=0 ; i<tbl_phieumuon.getRowCount() ; i++){
                ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon();
                ctpm.setPhieuMuon(pm);
                Sach s = (Sach)dao.getObject(new Sach(), Integer.parseInt(String.valueOf(tbl_phieumuon.getValueAt(i, 0))));
                
                ctpm.setSach(s);
                ctpm.setSoluong(Integer.parseInt(String.valueOf(tbl_phieumuon.getValueAt(i, 2))) );
                if(s.getSoluongton()>0){
                s.setSoluongton(s.getSoluongton()-Integer.parseInt(String.valueOf(tbl_phieumuon.getValueAt(i, 2))) );
                dao.Update(s);
                dao.flush();
                }else{
                    JOptionPane.showMessageDialog(null,"Het Sach","LOI",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                pm.getCtpm().add(ctpm);             
            }
            dao.saveObject(pm);
            dao.commitTransaction();
            dao.closeAll();
            
            resetFormPhieuMuon();
            showTableSach();
            
            showTableMSach();
            showTablePMTongHop();
            
        } catch (ParseException ex) {
            Logger.getLogger(giaoDIen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
        List<Sach> s = new SachDAO().filterSach(msach_tf_tensach.getText());
        resetTable(tbl_msach_model);
        if(!s.isEmpty()){
            
            s.forEach(e->{
                Object[] tmpRow = {
                    e.getMasach(), e.getTensach(),e.getSotrang(),e.getNgonngu()
                ,e.getSoluongton(),e.getNxb().getTen(),e.getTl().getTentl()
                };
                tbl_msach_model.addRow(tmpRow);
            });

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetTable(tbl_phieumuon_model);
        showTableMSach();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         resetTable(tbl_phieumuon_model);
         phieumuon_tf_madg.setText("");
        phieumuon_tf_tien.setText("");
         tbl_msach.clearSelection();
         showTableMSach();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        showTableDocGia();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        showTableNhanVien();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        showTableSach();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        showTablePMTongHop();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_qlpm_suaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qlpm_suaSachActionPerformed
        sach s = new sach();
        s.passingFrame(this);
        s.setVisible(true);
    }//GEN-LAST:event_btn_qlpm_suaSachActionPerformed

    private void btn_pmTongHop_xoaPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pmTongHop_xoaPMActionPerformed
        PhieuMuonDAO pmDAO = new PhieuMuonDAO();
        PhieuMuon pm = pmDAO.getPhieuMuon(Integer.parseInt(String.valueOf(tbl_pmTongHop.getValueAt(tbl_pmTongHop.getSelectedRow(), 0))));
        
        
        pmDAO.deletePhieuMuon(pm);
        resetFormQLPM_CTPM();
        showTablePMTongHop();

    }//GEN-LAST:event_btn_pmTongHop_xoaPMActionPerformed

    private void btn_pmTongHop_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pmTongHop_cancelActionPerformed
        // TODO add your handling code here:
        resetFormQLPM_CTPM();
        tbl_pmTongHop.clearSelection();
        btn_pmTongHop_update.setVisible(false);
        btn_pmTongHop_delete.setVisible(false);
        btn_pmTongHop_cancel.setVisible(false);
        btn_pmTongHop_xoaPM.setVisible(false);
        btn_qlpm_suaSach.setVisible(false);
    }//GEN-LAST:event_btn_pmTongHop_cancelActionPerformed

    private void btn_pmTongHop_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pmTongHop_updateActionPerformed
        
        //PhieuMuon pm = new PhieuMuonDAO().getPhieuMuon(Integer.parseInt(lbl_pmTongHop_maphieu.getText()));
        DAO dao = new DAO();
        dao.beginTransaction();
        PhieuMuon pm = (PhieuMuon)dao.getObject(new PhieuMuon(),Integer.parseInt(lbl_pmTongHop_maphieu.getText()));
//        PhieuMuon pm = new PhieuMuon();
//        pm.setMaphieu(Integer.parseInt(lbl_pmTongHop_maphieu.getText()));
//        try {
//            pm.setNgaythue(new SimpleDateFormat("yyyy-MM-dd").parse(lbl_pmTongHop_ngayThue.getText()));
//            pm.setNgaytra(new SimpleDateFormat("yyyy-MM-dd").parse(lbl_pmTongHop_NgayTra.getText()));
//        } catch (ParseException ex) {
//            Logger.getLogger(giaoDIen.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        for(int i = 0 ; i < tbl_qlpm_ctpm.getRowCount() ; i++){
            ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon();
            ctpm.setPhieuMuon(pm);
            Sach s ;
            for(int j = 0 ; j < updatedSachList.size() ; j ++){
                
                if(Integer.parseInt(String.valueOf(tbl_qlpm_ctpm.getValueAt(i, 0))) == updatedSachList.get(j).getMasach() ){                    
                    s = (Sach)dao.loadObject(new Sach(), updatedSachList.get(j).getMasach());
                    //s.setMasach(updatedSachList.get(j).getMasach());
                    s.setSoluongton(updatedSachList.get(j).getSoluongton());
                    dao.Update(s);
                    dao.flush();
                    ctpm.setSach(s);
                    ctpm.setSoluong(Integer.parseInt(String.valueOf(tbl_qlpm_ctpm.getValueAt(i, 2))));
                    break;
                }
            }
            pm.getCtpm().add(ctpm);
                        
        }
        dao.Update(pm);
        dao.commitTransaction();
        dao.closeAll();
        
        resetFormQLPM_CTPM();
        tbl_pmTongHop.clearSelection();
        btn_pmTongHop_update.setVisible(false);
        btn_pmTongHop_delete.setVisible(false);
        btn_pmTongHop_cancel.setVisible(false);
        btn_pmTongHop_xoaPM.setVisible(false);
    }//GEN-LAST:event_btn_pmTongHop_updateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PhieuMuonDAO pmDAO = new PhieuMuonDAO();

        List<PhieuMuon> listPM = pmDAO.filterPhieuMuon(new SimpleDateFormat("yyyy-MM-dd").format(pmTongHop_ngaythue_search.getDate()),  String.valueOf(pmTongHop_tt_search.getSelectedItem()));
        resetTable(tbl_pmTongHop_model);
        if(!listPM.isEmpty()){

            listPM.forEach(e->{
                Object[] tmpRow = {
                    e.getMaphieu(),e.getDocgia().getTendg(),
                    e.getTiencoc(),e.getNhanvien().getTennv(),e.getNgaythue(),
                    e.getNgaytra(),e.getTt()
                };
                tbl_pmTongHop_model.addRow(tmpRow);
            });

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_pmTongHopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pmTongHopMouseClicked
        // TODO add your handling code here:

        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            lbl_pmTongHop_maphieu.setText(String.valueOf(tbl_pmTongHop_model.getValueAt(tbl_pmTongHop.getSelectedRow(), 0)));
            txt_pmTongHop_docGia.setText(String.valueOf(tbl_pmTongHop_model.getValueAt(tbl_pmTongHop.getSelectedRow(), 1)));
            txt_pmTongHop_tienCoc.setText(String.valueOf(tbl_pmTongHop_model.getValueAt(tbl_pmTongHop.getSelectedRow(), 2)));
            lbl_pmTongHop_ngayThue.setText(String.valueOf(tbl_pmTongHop_model.getValueAt(tbl_pmTongHop.getSelectedRow(), 4)));
            lbl_pmTongHop_NgayTra.setText(String.valueOf(tbl_pmTongHop_model.getValueAt(tbl_pmTongHop.getSelectedRow(), 5)));

            PhieuMuonDAO pmDAO = new PhieuMuonDAO();
            PhieuMuon pm = pmDAO.getPhieuMuon(Integer.parseInt(lbl_pmTongHop_maphieu.getText()));
            List<ChiTietPhieuMuon> listCTPM = pm.getCtpm();
            resetTable(tbl_qlpm_ctpm_model);
            for( int i = 0; i < listCTPM.size() ; i ++){
                Sach s = listCTPM.get(i).getSach();
                Object[] tmpRow = {
                    s.getMasach(),s.getTensach(),listCTPM.get(i).getSoluong()
                };
                tbl_qlpm_ctpm_model.addRow(tmpRow);
            }
        }

        btn_pmTongHop_update.setVisible(true);
        btn_pmTongHop_delete.setVisible(true);
        btn_pmTongHop_cancel.setVisible(true);
        
        btn_pmTongHop_xoaPM.setVisible(true);
        
        btn_qlpm_suaSach.setVisible(true);

    }//GEN-LAST:event_tbl_pmTongHopMouseClicked

    private void tbl_trasachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_trasachMouseClicked
        if(evt.getID()==MouseEvent.MOUSE_CLICKED){
            lbl_TraSach_maphieu.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 0)));
            lbl_TraSach_dg.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 1)));
            lbl_TraSach_nhanvien.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(),3)));
            lbl_TraSach_tiencoc.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 2)));
            lbl_TraSach_ngayThue.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 4)));
            lbl_TraSach_ngayTra.setText(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 5)));
//            lbl_TraSach_ngayTra.setText(String.valueOf(tbl_tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(), 5)));

            PhieuMuonDAO pmDAO = new PhieuMuonDAO();
            PhieuMuon pm = pmDAO.getPhieuMuon(Integer.parseInt(lbl_TraSach_maphieu.getText()));
            List<ChiTietPhieuMuon> listCTPM = pm.getCtpm();
            resetTable(tbl_trasach_ctpm_model);
            for( int i = 0; i < listCTPM.size() ; i ++){
                Sach s = listCTPM.get(i).getSach();
                Object[] tmpRow = {
                    s.getMasach(),s.getTensach(),listCTPM.get(i).getSoluong()
                };
                tbl_trasach_ctpm_model.addRow(tmpRow);
            }
        }
        btn_trasach_giahan.setVisible(true);
        btn_trasach_trahet.setVisible(true);
    }//GEN-LAST:event_tbl_trasachMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
            MessageFormat header = new MessageFormat("Page header");
            MessageFormat footer = new MessageFormat("Page,1,number,integer");
            tbl_pmTongHop.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btn_pmTongHop_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pmTongHop_deleteActionPerformed
        // TODO add your handling code here:
        System.out.println(updatedSachList.get(0).getSoluongton());
    }//GEN-LAST:event_btn_pmTongHop_deleteActionPerformed

    private void btn_trasach_trahetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trasach_trahetActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn trả hết ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            DAO dao = new DAO();
            PhieuMuonDAO pmDAO = new PhieuMuonDAO();
            dao.beginTransaction();
            PhieuMuon pm = (PhieuMuon)dao.getObject(new PhieuMuon(),Integer.parseInt(lbl_TraSach_maphieu.getText()));
            String trangThai = "Da tra";
            pm.setTt(trangThai);
            dao.Update(pm);
            dao.commitTransaction();
            dao.closeAll();
        }
        resetTable(tbl_trasach_model);
        resetTable(tbl_pmTongHop_model);
        resetTable(tbl_trasach_ctpm_model);
        showTablePMTongHop();
        showTableTraSach();
        
    }//GEN-LAST:event_btn_trasach_trahetActionPerformed

    private void btn_trasach_giahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trasach_giahanActionPerformed
        if(String.valueOf(tbl_trasach_model.getValueAt(tbl_trasach.getSelectedRow(),6)).equalsIgnoreCase("qua han")){
            JOptionPane.showMessageDialog(this, "Các phiếu đã quá hạn sẽ không được gia hạn!","LỖI",JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            int option = JOptionPane.showConfirmDialog(this, "Thời gian trả sách sẽ được tăng thêm 1 tháng. Bạn có muốn gia hạn phiếu mượn ?", "XÁC NHẬN", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            DAO dao = new DAO();
            dao.beginTransaction();
            PhieuMuon pm = (PhieuMuon)dao.getObject(new PhieuMuon(),Integer.parseInt(lbl_TraSach_maphieu.getText()));
            try {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(lbl_TraSach_ngayTra.getText());
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.MONTH,1);
                pm.setNgaytra(c.getTime());
                pm.setTt("dang muon");
                dao.Update(pm);
                dao.commitTransaction();
                dao.closeAll();
            } catch (ParseException ex) {
                Logger.getLogger(giaoDIen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        capNhatPhieuQuaHan();
        resetTable(tbl_trasach_model);
        resetTable(tbl_pmTongHop_model);
        resetTable(tbl_trasach_ctpm_model);
        showTablePMTongHop();
        showTableTraSach();
        
        }
        
    }//GEN-LAST:event_btn_trasach_giahanActionPerformed

    private void pmTongHop_tt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmTongHop_tt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pmTongHop_tt_searchActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         new Login().setVisible(true);
        this.setVisible(false);                              
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void nv_reset_passwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nv_reset_passwdActionPerformed
            DAO dao = new DAO();
        dao.beginTransaction();
        NhanVien nv = (NhanVien) dao.getObject(new NhanVien(), Integer.parseInt(nv_tf_manv.getText()));
        nv.setMatkhau("1");
//        if(nv_nam.isSelected()){
//            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),nv_tf_mk.getText(), nv_tf_sdt.getText(), nv_tf_diachi.getText(), Integer.parseInt(nv_tf_tuoi.getText()), nv_tf_cmnd.getText(), "nam", String.valueOf(nv_capbac.getSelectedItem()));
//        }else{
//            nv = new NhanVien(nv_tf_tdn.getText(), nv_tf_tennv.getText(),nv_tf_mk.getText(), nv_tf_sdt.getText(), nv_tf_diachi.getText(), Integer.parseInt(nv_tf_tuoi.getText()), nv_tf_cmnd.getText(), "nu", String.valueOf(nv_capbac.getSelectedItem()));
//        }
//        nv.setManv(Integer.parseInt(nv_tf_manv.getText()));
        if(!validateNhanVienForm()){
            return;
        }
        
        if(crntUser.getCapbac().equalsIgnoreCase("boss")){
            //if current logged in user is boss            
                //nvDAO.updateNhanVien(nv);
                dao.Update(nv);
                dao.commitTransaction();
                dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            
        }else{
            if(nv.getCapbac().equalsIgnoreCase("boss")){
                //if picked user is boss
            }else{
                
               dao.Update(nv);
               dao.commitTransaction();
               dao.closeAll();
                showTableNhanVien();
                resetFormNhanVien();

                nv_del_btn.setVisible(false);
                nv_cancel_btn.setVisible(false);
                nv_upd_btn.setVisible(false);
                nv_reset_passwd.setVisible(false);

                nv_them_btn.setVisible(true);
                nv_rs_btn.setVisible(true);
            }
        }
    }//GEN-LAST:event_nv_reset_passwdActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        resetTable(tbl_tl_model);
        TheLoaiDAO tlDAO = new TheLoaiDAO();
        List<TheLoai> listTL = tlDAO.filterTheLoai(tl_tf_ten.getText());
        listTL.stream().forEach(e -> {
            Object[] tmpRow = {e.getMatl(), e.getTentl()}; 
            tbl_tl_model.addRow(tmpRow);
        });
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        resetTable(tbl_nxb_model);
        NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
        List<NhaXuatBan> listNXB = nxbDAO.filterNhaXuatBan(nxb_tf_ten.getText());
        listNXB.stream().forEach(e -> {
            Object[] tmpRow = {e.getManxb(), e.getTen(),
                e.getDiachi(),e.getSdt()}; 
            tbl_nxb_model.addRow(tmpRow);
        });
        
    }//GEN-LAST:event_jButton20ActionPerformed

    
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
            java.util.logging.Logger.getLogger(giaoDIen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(giaoDIen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(giaoDIen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(giaoDIen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new giaoDIen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BIGDATA;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btn_pmTongHop_cancel;
    private javax.swing.JButton btn_pmTongHop_delete;
    private javax.swing.JButton btn_pmTongHop_update;
    private javax.swing.JButton btn_pmTongHop_xoaPM;
    private javax.swing.JButton btn_qlpm_suaSach;
    private javax.swing.JButton btn_trasach_giahan;
    private javax.swing.JButton btn_trasach_trahet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup dg_btn_group;
    private javax.swing.JButton dg_cancel_btn;
    private javax.swing.JButton dg_del_btn;
    private javax.swing.JRadioButton dg_nam;
    private com.toedter.calendar.JDateChooser dg_ngay;
    private javax.swing.JRadioButton dg_nu;
    private javax.swing.JPanel dg_panel;
    private javax.swing.JButton dg_rs_btn;
    private javax.swing.JComboBox<String> dg_search_gioitinh;
    private javax.swing.JTextField dg_tf_cmnd;
    private javax.swing.JTextField dg_tf_diachi;
    private javax.swing.JLabel dg_tf_madg;
    private javax.swing.JTextField dg_tf_nghe;
    private javax.swing.JTextField dg_tf_sdt;
    private javax.swing.JTextField dg_tf_search;
    private javax.swing.JTextField dg_tf_tendg;
    private javax.swing.JButton dg_them_btn;
    private javax.swing.JButton dg_update_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_TraSach_dg;
    private javax.swing.JLabel lbl_TraSach_maphieu;
    private javax.swing.JLabel lbl_TraSach_ngayThue;
    private javax.swing.JLabel lbl_TraSach_ngayTra;
    private javax.swing.JLabel lbl_TraSach_nhanvien;
    private javax.swing.JLabel lbl_TraSach_tiencoc;
    private javax.swing.JLabel lbl_pmTongHop_NgayTra;
    private javax.swing.JLabel lbl_pmTongHop_maphieu;
    private javax.swing.JLabel lbl_pmTongHop_ngayThue;
    private javax.swing.JTextField msach_tf_tensach;
    private javax.swing.ButtonGroup nv_btn_group;
    private javax.swing.JButton nv_btn_search;
    private javax.swing.JButton nv_cancel_btn;
    private javax.swing.JComboBox<String> nv_capbac;
    private javax.swing.JButton nv_del_btn;
    private javax.swing.JRadioButton nv_nam;
    private javax.swing.JRadioButton nv_nu;
    private javax.swing.JPanel nv_panel;
    private javax.swing.JButton nv_reset_passwd;
    private javax.swing.JButton nv_rs_btn;
    private javax.swing.JTextField nv_search;
    private javax.swing.JTextField nv_tf_cmnd;
    private javax.swing.JTextField nv_tf_diachi;
    private javax.swing.JLabel nv_tf_manv;
    private javax.swing.JTextField nv_tf_sdt;
    private javax.swing.JTextField nv_tf_tdn;
    private javax.swing.JTextField nv_tf_tennv;
    private javax.swing.JTextField nv_tf_tuoi;
    private javax.swing.JButton nv_them_btn;
    private javax.swing.JButton nv_upd_btn;
    private javax.swing.JButton nxb_cancel_btn;
    private javax.swing.JButton nxb_del_btn;
    private javax.swing.JButton nxb_rs_btn;
    private javax.swing.JTextField nxb_tf_diachi;
    private javax.swing.JLabel nxb_tf_manxb;
    private javax.swing.JTextField nxb_tf_sdt;
    private javax.swing.JTextField nxb_tf_ten;
    private javax.swing.JButton nxb_them_btn;
    private javax.swing.JButton nxb_update_btn;
    private javax.swing.JLabel phieumuon_crntDate;
    private javax.swing.JLabel phieumuon_crntUser;
    private javax.swing.JTextField phieumuon_tf_madg;
    private javax.swing.JTextField phieumuon_tf_tien;
    private com.toedter.calendar.JDateChooser pmTongHop_ngaythue_search;
    private javax.swing.JComboBox<String> pmTongHop_tt_search;
    private javax.swing.JPanel qlpm_pane;
    private javax.swing.JButton sach_cancel_btn;
    private javax.swing.JButton sach_del_btn;
    private javax.swing.JComboBox<String> sach_nxb;
    private javax.swing.JComboBox<String> sach_nxb1;
    private javax.swing.JButton sach_rs_btn;
    private javax.swing.JLabel sach_tf_ma;
    private javax.swing.JTextField sach_tf_ngonngu;
    private javax.swing.JTextField sach_tf_sl;
    private javax.swing.JTextField sach_tf_sotrang;
    private javax.swing.JTextField sach_tf_ten;
    private javax.swing.JTextField sach_tf_ten_search;
    private javax.swing.JButton sach_them_btn;
    private javax.swing.JComboBox<String> sach_tl;
    private javax.swing.JComboBox<String> sach_tl1;
    private javax.swing.JButton sach_update_btn;
    private javax.swing.JTabbedPane tab_pane;
    private javax.swing.JTable tbl_dg;
    private javax.swing.JTable tbl_msach;
    private javax.swing.JTable tbl_nv;
    private javax.swing.JTable tbl_nxb;
    private javax.swing.JTable tbl_phieumuon;
    private javax.swing.JTable tbl_pmTongHop;
    private javax.swing.JTable tbl_qlpm_ctpm;
    private javax.swing.JTable tbl_sach;
    private javax.swing.JTable tbl_tl;
    private javax.swing.JTable tbl_trasach;
    private javax.swing.JTable tbl_trasach_ctpm;
    private javax.swing.JButton tl_cancel_btn;
    private javax.swing.JButton tl_del_btn;
    private javax.swing.JButton tl_rs_btn;
    private javax.swing.JLabel tl_tf_matl;
    private javax.swing.JTextField tl_tf_ten;
    private javax.swing.JButton tl_them_btn;
    private javax.swing.JButton tl_update_btn;
    private javax.swing.JLabel tt_tf_capbac;
    private javax.swing.JLabel tt_tf_cmnd;
    private javax.swing.JLabel tt_tf_diachi;
    private javax.swing.JLabel tt_tf_gioitinh;
    private javax.swing.JLabel tt_tf_ma;
    private javax.swing.JLabel tt_tf_sdt;
    private javax.swing.JLabel tt_tf_tdn;
    private javax.swing.JLabel tt_tf_ten;
    private javax.swing.JLabel tt_tf_tuoi;
    private javax.swing.JLabel txt_pmTongHop_docGia;
    private javax.swing.JLabel txt_pmTongHop_tienCoc;
    // End of variables declaration//GEN-END:variables
}
