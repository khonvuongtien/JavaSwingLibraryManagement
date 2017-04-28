/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;


public class NhanVienDAO {

    DAO dao;
    public NhanVienDAO() {
         dao = new DAO();
    }
    
    public List<NhanVien> getAllNhanVien(){
        dao.beginTransaction();
        List<NhanVien> list_nv = dao.getAllObjects("NhanVien");
        dao.commitTransaction();
        dao.closeAll();
        return list_nv;
    }
    
    public void saveNhanVien(NhanVien nv){
        dao.beginTransaction();
        dao.saveObject(nv);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updateNhanVien(NhanVien nv){
        dao.beginTransaction();
        dao.Update(nv);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deleteNhanVien(NhanVien nv){
        //nv = (NhanVien)dao.getObject(nv, nv.getManv());
        dao.beginTransaction();
        dao.DeleteObject(nv);
        dao.commitTransaction();
        dao.closeAll();
    }
    
//    public NhanVien getNhanVienWithID(int ID){
//        return (NhanVien)dao.getObject(new NhanVien(), ID);
//    }
    
    public List<NhanVien> getNhanVienWithCrit(String username, String password){
        //throw [createQuery is not valid without active transaction] when using getCurrentSession
        return dao.getObjectsWithCriteria("NhanVien", "e.tendangnhap like '"+username+"' and e.matkhau like '"+password+"'");        
    }
    
    public List<NhanVien> filterNhanVien(String tennv){
        return dao.getObjectsWithCriteria("NhanVien", "e.tennv like '%"+tennv+"%'");
    }
}
