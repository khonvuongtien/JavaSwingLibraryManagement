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
public class NhaXuatBanDAO {
     DAO dao;
    public NhaXuatBanDAO() {
         dao = new DAO();
    }
    
    public List<NhaXuatBan> getAllNhaXuatBan(){
        dao.beginTransaction();
        List<NhaXuatBan> list_nxb = dao.getAllObjects("NhaXuatBan");
        dao.commitTransaction();
        dao.closeAll();
        return list_nxb;
    }
    
    public void saveNhaXuatBan(NhaXuatBan nxb){
        dao.beginTransaction();
        dao.saveObject(nxb);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updateNhaXuatBan(NhaXuatBan nxb){
        dao.beginTransaction();
        dao.Update(nxb);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deleteNhaXuatBan(NhaXuatBan nxb){
         
        dao.beginTransaction();
        dao.DeleteObject(nxb);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public List<String> getDistinctNhaXuatBan(){
        return dao.DistinctQuery(new NhaXuatBan(), "ten");
    }
    public List<NhaXuatBan> filterNhaXuatBan(String nxb){
        return dao.getObjectsWithCriteria("NhaXuatBan", "e.ten like '%"+nxb+"%'");
    }
}

