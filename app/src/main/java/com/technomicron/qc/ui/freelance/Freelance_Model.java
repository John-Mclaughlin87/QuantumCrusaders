package com.technomicron.qc.ui.freelance;
import androidx.lifecycle.ViewModel;

public class Freelance_Model extends ViewModel {

    String username;
    String userID;
    String attack;
    String defense;
    String covertAttack;
    String covertDefense;
    String value;

    public Freelance_Model(String username, String userID, String attack, String defense, String covertAttack, String covertDefense, String value) {
        this.userID = userID;
        this.username = username;
        this.attack = attack;
        this.defense = defense;
        this.covertAttack = covertAttack;
        this.covertDefense = covertDefense;
        this.value = value;
    }


    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() { return defense; }

    public String getCovertAttack() {
        return covertAttack;
    }

    public String getCovertDefense() { return covertDefense; }

    public String getValue() { return value ; }
}
