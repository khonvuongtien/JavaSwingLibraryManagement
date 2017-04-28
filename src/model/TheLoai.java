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
@Table(name="THE_LOAI")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MATL")
    int matl;
    @Column(name="TENTL")
    String tentl;
    @OneToMany(mappedBy = "tl")
    Set<Sach> sach;    

    public TheLoai() {
    }

    public TheLoai(String tentl, Set<Sach> sach) {
        this.tentl = tentl;
        this.sach = sach;
    }

    public TheLoai(String tentl) {
        this.tentl = tentl;
    }

    public int getMatl() {
        return matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public Set<Sach> getSach() {
        return sach;
    }

    public void setSach(Set<Sach> sach) {
        this.sach = sach;
    }
    
}
