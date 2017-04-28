/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EmbeddedId;

/**
 *
 * @author Ly Nhan
 */
public class ChiTietPhieuMuonDAO {
    @EmbeddedId
    public chitietphieumuon_identity ctpm_id = new chitietphieumuon_identity();
    DAO dao;
    public ChiTietPhieuMuonDAO(){
        dao = new DAO();
    }
    public List<ChiTietPhieuMuon> getAllChiTietPhieuMuon(){
        dao.beginTransaction();
        List<ChiTietPhieuMuon> list_ChiTietPhieuMuon = dao.getAllObjects("ChiTietPhieuMuon");
        dao.commitTransaction();
        dao.closeAll();
        return list_ChiTietPhieuMuon;
    }
    
}
