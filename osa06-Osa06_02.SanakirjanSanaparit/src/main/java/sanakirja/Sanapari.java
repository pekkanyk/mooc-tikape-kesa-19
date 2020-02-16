/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja;

/**
 *
 * @author pekkanyk
 */
public class Sanapari {
    private String sana;
    private String kaannos;
    
    public Sanapari (String sana, String kaannos){
        this.sana = sana;
        this.kaannos = kaannos;
    }

    public String getSana() {
        return sana;
    }

    public String getKaannos() {
        return kaannos;
    }
    
}
