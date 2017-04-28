/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PHIEU_MUON")
public class PhieuMuon {
    @Id
    @Column(name="MAPHIEU")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int maphieu;
    @Column(name="TIENCOC")
    float tiencoc;
    @Column(name="NGAYTHUE")
    @Temporal(TemporalType.DATE)
    Date ngaythue;
    @Column(name="NGAYTRA")
    @Temporal(TemporalType.DATE)
    Date ngaytra;
    @Column(name="TRANGTHAI")
    String tt;
    @ManyToOne
    @JoinColumn(name = "MANV")
    NhanVien nhanvien;
    @ManyToOne
    @JoinColumn(name = "MADG")
    DocGia docgia;
    
    @OneToMany(mappedBy = "ctpm_id.phieumuon",cascade = CascadeType.ALL)
    List<ChiTietPhieuMuon> ctpm = new ArrayList<ChiTietPhieuMuon>();

    public PhieuMuon() {
    }

    public PhieuMuon(int maphieu, float tiencoc, NhanVien nhanvien, DocGia docgia) {
        this.maphieu = maphieu;
        this.tiencoc = tiencoc;
        this.nhanvien = nhanvien;
        this.docgia = docgia;
    }

    public PhieuMuon(float tiencoc, Date ngaythue, NhanVien nhanvien, DocGia docgia) {
        this.tiencoc = tiencoc;
        this.ngaythue = ngaythue;
        this.nhanvien = nhanvien;
        this.docgia = docgia;
    }
    

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public float getTiencoc() {
        return tiencoc;
    }

    public void setTiencoc(float tiencoc) {
        this.tiencoc = tiencoc;
    }

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public DocGia getDocgia() {
        return docgia;
    }

    public void setDocgia(DocGia docgia) {
        this.docgia = docgia;
    }

    public Date getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(Date ngaythue) {
        this.ngaythue = ngaythue;
    }

    public Date getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }

    public List<ChiTietPhieuMuon> getCtpm() {
        return ctpm;
    }

    public void setCtpm(List<ChiTietPhieuMuon> ctpm) {
        this.ctpm = ctpm;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }
    
    
}
