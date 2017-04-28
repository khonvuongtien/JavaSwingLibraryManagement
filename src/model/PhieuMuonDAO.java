/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author khonvt
 */
public class PhieuMuonDAO {
    DAO dao;
    public PhieuMuonDAO() {
         dao = new DAO();
    }
    
    public List<PhieuMuon> getAllPhieuMuon(){
        dao.beginTransaction();
        List<PhieuMuon> list_phieumuon = dao.getAllObjects("PhieuMuon");
        dao.commitTransaction();
        dao.closeAll();
        return list_phieumuon;
    }
    
    public void savePhieuMuon(PhieuMuon pm){
        dao.beginTransaction();
        dao.saveObject(pm);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updatePhieuMuon(PhieuMuon pm){
        dao.beginTransaction();
        dao.Update(pm);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deletePhieuMuon(PhieuMuon pm){                 
        dao.beginTransaction();
        dao.DeleteObject(pm);
        dao.commitTransaction();
        dao.closeAll();
    }
    
   public List<PhieuMuon> filterPhieuMuon(String ngaythue, String tt){
        return dao.getObjectsWithCriteria("PhieuMuon", "e.ngaythue = '"+ngaythue+"' and e.tt like '"+tt+"'");
    }
   
   public PhieuMuon getPhieuMuon(int ID){
       return (PhieuMuon)dao.getObject(new PhieuMuon(), ID);
   }
    
}
