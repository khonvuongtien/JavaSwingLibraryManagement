/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NHAN_VIEN")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MANV")            
    int manv;
    @Column(name="TENDANGNHAP")
    String tendangnhap;
    @Column(name="TENNV")
    String tennv;
    @Column(name="MATKHAU")
    String matkhau;
    @Column(name="SDT")
    String sdt;
    @Column(name="DIACHI")
    String diachi;
    @Column(name="TUOI")
    int tuoi;
    @Column(name="CMND")
    String CMND;
    @Column(name="GIOITINH")
    String gioitinh;
    @Column(name="CAPBAC")
    String capbac;
    @OneToMany(mappedBy = "nhanvien", cascade = CascadeType.REMOVE)
    Set<PhieuMuon> phieu;

    public NhanVien() {
    }
    
    public NhanVien(String tendangnhap, String tennv, String matkhau, String sdt, String diachi, String CMND, String gioitinh, String capbac) {
        this.tendangnhap = tendangnhap;
        this.tennv = tennv;
        this.matkhau = matkhau;
        this.sdt = sdt;
        this.diachi = diachi;
     
        this.CMND = CMND;
        this.gioitinh = gioitinh;
        this.capbac = capbac;
    }

    public NhanVien(String tendangnhap, String tennv, String matkhau, String sdt, String diachi, int tuoi, String CMND, String gioitinh, String capbac) {
        this.tendangnhap = tendangnhap;
        this.tennv = tennv;
        this.matkhau = matkhau;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tuoi = tuoi;
        this.CMND = CMND;
        this.gioitinh = gioitinh;
        this.capbac = capbac;
    }
    
    public NhanVien(String tendangnhap, String tennv, String matkhau, String sdt, String diachi, int tuoi, String CMND, String gioitinh, String capbac, Set<PhieuMuon> phieu) {
        this.tendangnhap = tendangnhap;
        this.tennv = tennv;
        this.matkhau = matkhau;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tuoi = tuoi;
        this.CMND = CMND;
        this.gioitinh = gioitinh;
        this.capbac = capbac;
        this.phieu = phieu;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCapbac() {
        return capbac;
    }

    public void setCapbac(String capbac) {
        this.capbac = capbac;
    }

    public Set<PhieuMuon> getPhieu() {
        return phieu;
    }

    public void setPhieu(Set<PhieuMuon> phieu) {
        this.phieu = phieu;
    }

        
    
}
