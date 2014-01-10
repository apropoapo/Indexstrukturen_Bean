/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexstrukturen_bean;

import java.util.LinkedList;

/**
 *
 * @author blub
 */
public class einfacherPraefix_B_plus_Baum<T extends Comparable> {

    private int hoehe;
    private int m;
    private Knoten wurzel;

    public einfacherPraefix_B_plus_Baum(int m, T wert) {
        Datenknoten tmp = new Datenknoten(m, wert);
        wurzel = new DirectoryKnoten(m, wert, null, tmp);
        hoehe= 2;
    }

    public void paint() {
        paintKnoten(wurzel, this.hoehe);
    }

    public void paintKnoten(Knoten<T> knoten, int hoehe) {
        if (knoten instanceof DirectoryKnoten) {
            knoten.paintNode(hoehe);

            LinkedList<T> pointer = knoten.getWerte();
            for (int i = 0; i < pointer.size(); i++) {
                if (pointer.get(i) != null) {
                    paintKnoten((Knoten<T>) pointer.get(i), hoehe - 1);
                }
            }
        } else if (knoten instanceof Datenknoten) {
             knoten.paintNode(hoehe);  
        }
    }

    public boolean addWert() {
        return false;
    }
}
