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
public class SachDAO {
     DAO dao;
    public SachDAO() {
         dao = new DAO();
    }
    
    public List<Sach> getAllSach(){
        dao.beginTransaction();
        List<Sach> list_sach = dao.getAllObjects("Sach");
        dao.commitTransaction();
        dao.closeAll();
        return list_sach;
    }
    
    public void saveSach(Sach sach){       
        dao.saveObject(sach);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updateSach(Sach sach){

        dao.Update(sach);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deleteSach(Sach sach){                 
        dao.beginTransaction();
        dao.DeleteObject(sach);
        dao.commitTransaction();
        dao.closeAll();
    }
    public List<Sach> filterSach(String s){
        dao.beginTransaction();
        List<Sach> ss = dao.getObjectsWithCriteria("Sach", "e.tensach like '%"+s+"%'");
        dao.flush();        
        dao.clear();
        return ss;
    }
    
    public List<Sach> filterSach(String ten, String nxb, String tl){
        return dao.getObjectsWithCriteria("Sach", "e.tensach like '%"+ten+"%' and e.nxb.ten like '"+nxb+"' and e.tl.tentl like '"+tl+"'");
    }
}
