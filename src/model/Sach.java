/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ly Nhan
 */
@Entity
@Table(name="SACH")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MASACH")
    int masach;
    @Column(name="TENSACH")
    String tensach;
    @Column(name="SOTRANG")
    int sotrang;
    @Column(name="NGONNGU")
    String ngonngu;
    @Column(name="SOLUONGTON")
    int soluongton;
    
    
    
    @ManyToOne
    @JoinColumn(name="MANXB")
    NhaXuatBan nxb;
    
    @ManyToOne
    @JoinColumn(name="MATL")
    TheLoai tl;
    
    
    
    @OneToMany(mappedBy = "ctpm_id.sach",cascade = CascadeType.ALL)
    List<ChiTietPhieuMuon> ctpm = new ArrayList<ChiTietPhieuMuon>();

    public Sach() {
    }

    public Sach(String tensach, int sotrang, String ngonngu, int soluongton) {
        this.tensach = tensach;
        this.sotrang = sotrang;
        this.ngonngu = ngonngu;
        this.soluongton = soluongton;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

   

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSotrang() {
        return sotrang;
    }

    public void setSotrang(int sotrang) {
        this.sotrang = sotrang;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public NhaXuatBan getNxb() {
        return nxb;
    }

    public void setNxb(NhaXuatBan nxb) {
        this.nxb = nxb;
    }

    public TheLoai getTl() {
        return tl;
    }

    public void setTl(TheLoai tl) {
        this.tl = tl;
    }

    public List<ChiTietPhieuMuon> getCtpm() {
        return ctpm;
    }

    public void setCtpm(List<ChiTietPhieuMuon> ctpm) {
        this.ctpm = ctpm;
    }
    
    
}
