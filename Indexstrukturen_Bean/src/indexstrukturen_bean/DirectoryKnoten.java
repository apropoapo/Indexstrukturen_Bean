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
public class DirectoryKnoten<T extends Comparable> extends Knoten<T>{
    LinkedList<Knoten> pointer;
    public DirectoryKnoten(int m, T wert, Knoten left, Knoten right){
        super(m, wert);
        pointer = new LinkedList<Knoten>();
        pointer.addLast(left);
        pointer.addLast(right);
    }
    
    public DirectoryKnoten(int m, T wert){
        super(m, wert);
        pointer = new LinkedList<Knoten>();

    }
    
    public LinkedList<Knoten> getPointer(){
        return pointer;
    }
    
    public void setPointer(int position, Knoten knoten){
        pointer.add(position, knoten);
    }
    
    public void addLastPointer(Knoten knoten){
        pointer.addLast(knoten);
    }
    
    public void removePointer(int position){
        pointer.remove(position);
    }

}
