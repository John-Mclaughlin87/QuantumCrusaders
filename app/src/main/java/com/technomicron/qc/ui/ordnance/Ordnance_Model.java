package com.technomicron.qc.ui.ordnance;

import androidx.lifecycle.ViewModel;

public class Ordnance_Model extends ViewModel {

    String title;
    String price;
    String attack_boost;
    String defence_boost;
    String quantity_owned;


    public Ordnance_Model(String title, String price, String attack_boost, String defence_boost, String quantity_owned) {
        this.title = title;
        this.price = price;
        this.attack_boost = attack_boost;
        this.defence_boost = defence_boost;
        this.quantity_owned = quantity_owned;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getAttack_boost() { return attack_boost; }
    public String getDefence_boost() {
        return defence_boost;
    }
    public String getQuantity_owned() {
        return quantity_owned;
    }
}