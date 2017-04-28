/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CHI_TIET_PHIEU_MUON")
@AssociationOverrides({
        @AssociationOverride(joinColumns = @JoinColumn(name="MASACH"),name = "ctpm_id.sach"),
        @AssociationOverride(joinColumns = @JoinColumn(name="MAPHIEU"),name = "ctpm_id.phieumuon")
})
public class ChiTietPhieuMuon {
    @EmbeddedId
    public chitietphieumuon_identity ctpm_id = new chitietphieumuon_identity();
    @Column(name="SOLUONG")
    int soluong;
  

    public ChiTietPhieuMuon() {
    }
    
    public void setPhieuMuon(PhieuMuon pm){
        ctpm_id.setPhieumuon(pm);
    }
    
    public void setSach(Sach s){
        ctpm_id.setSach(s);
    }
    
    public Sach getSach(){
        return ctpm_id.getSach();
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
}
