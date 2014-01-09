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
        this.werteList.add(wert);
        this.m = m;
        this.max = wert;
        this.min = wert;
    }

    public LinkedList<T> getWerte() {
        return werteList;
    }

    public boolean addWert(T wert) {
        // nur einfügen, wenn noch Platz ist.
        if (werteList.size() < 2 * m) {
            //einfügeposition suchen
            int i = 0;
            boolean found = false;
            while (!found) {
                if (werteList.get(i) != null) {
                    if (wert.compareTo(werteList.get(i)) < 0) {
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
            if (i == 0){
                min = wert;
            }
            return true;
        }
        return false;
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
    public T getMin(){
        return min;
    }

    public T getMax(){
        return max;
    }    
    
    public void paintNode(int hoehe){ //male Knoten
        for (int i=0; i<hoehe; i++){
            System.out.print("      ");
        }
        for (int i=0; i < werteList.size(); i++)
        System.out.println(werteList.get(i) + "  ");
    }
}
