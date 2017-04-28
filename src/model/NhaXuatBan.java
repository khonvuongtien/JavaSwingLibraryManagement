/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author khonvt
 */
@Entity
@Table(name="NHA_XB")
public class NhaXuatBan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MANXB")
    int manxb;
    @Column(name="DIACHI")
    String diachi;
    @Column(name="SDT")
    String sdt;
    @Column(name="TEN")
    String ten;
    @OneToMany(mappedBy = "nxb")
    Set<Sach> sach;

    public NhaXuatBan() {
    }

    public NhaXuatBan(String diachi, String sdt, String ten) {
        this.diachi = diachi;
        this.sdt = sdt;
        this.ten = ten;
    }

    public int getManxb() {
        return manxb;
    }

    public void setManxb(int manxb) {
        this.manxb = manxb;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    
}
