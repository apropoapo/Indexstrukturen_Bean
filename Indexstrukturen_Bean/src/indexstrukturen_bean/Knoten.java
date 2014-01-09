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
abstract class Knoten<T> {
    private T wert;
    public Knoten(){
        this.wert = wert;
    }
    
    public T getKey(){
        return wert;
    }
    
    public void setKey(T wert){
        this.wert = wert;
    }
}
