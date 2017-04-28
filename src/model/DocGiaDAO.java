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
public class DocGiaDAO {
    DAO dao;
    public DocGiaDAO() {
         dao = new DAO();
    }
    
    public List<DocGia> getAllDocGia(){
        dao.beginTransaction();
        List<DocGia> list_dg = dao.getAllObjects("DocGia");
        dao.commitTransaction();
        dao.closeAll();
        return list_dg;
    }
    
    public void saveDocGia(DocGia dg){
        dao.beginTransaction();
        dao.saveObject(dg);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void updateDocGia(DocGia dg){
        dao.beginTransaction();
        dao.Update(dg);
        dao.commitTransaction();
        dao.closeAll();
    }
    
    public void deleteDocGia(DocGia dg){
         
        dao.beginTransaction();
        dao.DeleteObject(dg);
        dao.commitTransaction();
        dao.closeAll();
    }
    public List<DocGia> filterDocGia(String ten, String gt){
        return dao.getObjectsWithCriteria("DocGia", "e.tendg like '%"+ten+"%' and e.gioitinh like '"+gt+"'");
    }
    
    public DocGia getDocGiaWithId(int id){
        dao.beginTransaction();
        DocGia dg = (DocGia)dao.getObject(new DocGia(),id);
        dao.closeAll();
        return dg;
    }
}
