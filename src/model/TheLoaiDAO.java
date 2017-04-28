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
public class TheLoaiDAO {
    DAO dao;
    public TheLoaiDAO() {
         dao = new DAO();
    }
    
    public List<TheLoai> getAllTheLoai(){
        dao.beginTransaction();
        List<TheLoai> list_tl = dao.getAllObjects("TheLoai");
        dao.commitTransaction();
        dao.closeAll();
        return list_tl;
    }
    
    public void saveTheLoai(TheLoai tl){
        dao.beginTransaction();
        dao.saveObject(tl);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updateTheLoai(TheLoai tl){
        dao.beginTransaction();
        dao.Update(tl);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deleteTheLoai(TheLoai tl){
         
        dao.beginTransaction();
        dao.DeleteObject(tl);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public List<String> getDistinctTheLoai(){
        return dao.DistinctQuery(new TheLoai(), "tentl");
    }
    public List<TheLoai> filterTheLoai(String tl){
        return dao.getObjectsWithCriteria("TheLoai", "e.tentl like '%"+tl+"%'");
    }
}
