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
public class Indexstrukturen_Bean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Datenknoten<String> dk = new Datenknoten<String>(2, "hallo");
        //dk.paintNode(2);
        einfacherPraefix_B_plus_Baum<String> baum = new einfacherPraefix_B_plus_Baum<String>(1, "Sven");
        
        System.out.println("Heike einfügen: " + baum.addWert("Heike"));
        System.out.println("Anna einfügen: " + baum.addWert("Anna")); 
          System.out.println("Helga einfügen: " + baum.addWert("Helga"));
           System.out.println("Zoo einfügen: " + baum.addWert("Zoo"));
         // System.out.println("Bob einfügen: " + baum.addWert("Bob"));
         // System.out.println("Heikz einfügen: " + baum.addWert("Heikz"));
       // System.out.println("Anke einfügen: " + baum.addWert("Anke"));
        baum.paint();
        
    }
    
}
