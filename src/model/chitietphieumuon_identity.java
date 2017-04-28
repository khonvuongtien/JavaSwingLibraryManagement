/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author khonvt
 */
@Embeddable
public class chitietphieumuon_identity implements Serializable {
    @ManyToOne
    public Sach sach;
    @ManyToOne
    public PhieuMuon phieumuon;

    public chitietphieumuon_identity() {
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public PhieuMuon getPhieumuon() {
        return phieumuon;
    }

    public void setPhieumuon(PhieuMuon phieumuon) {
        this.phieumuon = phieumuon;
    }
    
    
}
