package br.ufc.quixada.dadm.variastelas.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Contato {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String name;

    public String ende;

    public String phone;

    public Contato(){

    }

    public Contato(String nome, String ende, String phone){
        this.name=nome;
        this.ende=ende;
        this.phone=phone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnde() {
        return ende;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
