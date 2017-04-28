/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DOC_GIA")
public class DocGia {
    @Id
    @Column(name="MADG")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int madg;
    @Column(name="TENDG")
    String tendg;
    @Column(name="GIOITINH")
    String gioitinh;
    @Column(name="NGHENGHIEP")
    String nghenghiep;
    @Column(name="CMND")
    String cmnd;
    @Column(name="SDT")
    String sdt;
    @Column(name="DIACHI")
    String diachi;
    @Column(name="NGAYCAPTHE")
    @Temporal(TemporalType.DATE)
    Date ngaycapthe;
    @OneToMany(mappedBy = "docgia", cascade = CascadeType.REMOVE)
    Set<PhieuMuon> phieu;

    public DocGia() {
    }

    public DocGia(String tendg, String gioitinh, String nghenghiep, String cmnd, String sdt, String diachi, Date ngaycapthe) {        
        this.tendg = tendg;
        this.gioitinh = gioitinh;
        this.nghenghiep = nghenghiep;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngaycapthe = ngaycapthe;
    }

        
    public DocGia(int madg, String tendg, String gioitinh, String nghenghiep, String cmnd, String sdt, String diachi, Date ngaycapthe, Set<PhieuMuon> phieu) {
        this.madg = madg;
        this.tendg = tendg;
        this.gioitinh = gioitinh;
        this.nghenghiep = nghenghiep;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngaycapthe = ngaycapthe;
        this.phieu = phieu;
    }

    public int getMadg() {
        return madg;
    }

    public void setMadg(int madg) {
        this.madg = madg;
    }

    public String getTendg() {
        return tendg;
    }

    public void setTendg(String tendg) {
        this.tendg = tendg;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNghenghiep() {
        return nghenghiep;
    }

    public void setNghenghiep(String nghenghiep) {
        this.nghenghiep = nghenghiep;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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

    public Date getNgaycapthe() {
        return ngaycapthe;
    }

    public void setNgaycapthe(Date ngaycapthe) {
        this.ngaycapthe = ngaycapthe;
    }

    public Set<PhieuMuon> getPhieu() {
        return phieu;
    }

    public void setPhieu(Set<PhieuMuon> phieu) {
        this.phieu = phieu;
    }
    
    
}
