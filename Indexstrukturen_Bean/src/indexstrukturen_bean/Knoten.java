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
abstract class Knoten<T extends Comparable> {

    private T max, min;
    private int m;
    private LinkedList<T> werteList;

    public Knoten(int m, T wert) {
        werteList = new LinkedList<T>();
        this.werteList.addFirst(wert);
        this.m = m;
        this.max = wert;
        this.min = wert;
    }

    public LinkedList<T> getWerte() {
        return werteList;
    }

    public boolean addWert(T wert) {
        boolean result;
        // einfügen, egal ob noch Platz ist oder nicht. false rausgeben, falls kein platz mehr war.
        
        
        if (werteList.size() < 2 * m) {
            result = true;
        } else {
            result=false;
        }
        //einfügeposition suchen
        int i = 0;
        boolean found = false;
        while (!found) {
            if (i < werteList.size()) { // Index in Bound?
                if (wert.compareTo(werteList.get(i)) > 0) {
                    i++;
                } else {
                    found = true;
                }
            } else {
                found = true;
                max = wert;
            }

        }
        werteList.add(i, wert);
        if (i == 0) {
            min = wert;
        }
        System.out.print("im Knoten, addWerte, Knoteninhalt: ");
        for (int j=0; j<werteList.size(); j++){
             System.out.print( werteList.get(j)+ " "); // #### TEST
        }
        System.out.println();
        return result;
    }

    public boolean removeWert(T wert) {
        // nur entfernen, wenn noch minimale Füllgrenze nicht unterschritten wird.
        if (werteList.size() > m) {
            if (this.werteList.remove(wert)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(T wert) {
        if (this.werteList.contains(wert)) {
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        return this.werteList.size();
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public void paintNode(int hoehe) { //male Knoten
        System.out.print(hoehe + " ");
        for (int i = 0; i < hoehe; i++) {
            System.out.print("      ");
        }
        System.out.print("| ");
        for (int i = 0; i < werteList.size(); i++) {
            System.out.print(werteList.get(i) + "  ");
        }
        System.out.print(" |");
    }
}
