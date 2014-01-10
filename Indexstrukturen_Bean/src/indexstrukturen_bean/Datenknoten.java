/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package indexstrukturen_bean;

/**
 *
 * @author blub
 */
public class Datenknoten<T extends Comparable> extends Knoten<T>{
    private Datenknoten next;
    
    public Datenknoten(int m, T wert){
        super(m, wert);
        
    }
    public Datenknoten(int m, T wert, Datenknoten next){
        super(m, wert);
        this.next = next;
    }
    
    public Datenknoten getNext(){
        return next;
    }
    
    public void setNext(){
        this.next = next;
    }
}
