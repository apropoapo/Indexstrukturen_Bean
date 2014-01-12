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
        this.m = m;
        wurzel = new Datenknoten(m, wert);
        hoehe = 1;
    }

    public void paint() {
        paintKnoten(wurzel, this.hoehe);
    }

    public void paintKnoten(Knoten<T> knoten, int hoehe) {
        if (knoten instanceof DirectoryKnoten) {
            DirectoryKnoten<T> dknoten = (DirectoryKnoten<T>) knoten;
            knoten.paintNode(hoehe);
            System.out.println();


            LinkedList<Knoten> pointer = dknoten.getPointer();
            for (int i = 0; i < pointer.size(); i++) {
                if (i < pointer.size()) {
                    paintKnoten(pointer.get(i), hoehe - 1);
                    
                }
            }
            System.out.println();
        } else if (knoten instanceof Datenknoten) {
            knoten.paintNode(hoehe);

        }
    }

    public boolean addWert(T wert) {
        LinkedList<Knoten> pfad = new LinkedList<>();
        pfad.add(wurzel);
        return addWertRekursiv(wert, wurzel, pfad);
    }

    private boolean addWertRekursiv(T wert, Knoten knoten, LinkedList<Knoten> pfad) {
        int i = 0;

        if (knoten instanceof DirectoryKnoten) {
            System.out.println("Directoryknoten"); // #### TEST
            // Knoten in Directoryknoten umwandeln
            DirectoryKnoten<T> dknoten = (DirectoryKnoten<T>) knoten;

            LinkedList<T> werte = dknoten.getWerte();

            // Einfügeposition suchen
            System.out.println("werte.get(i).compareTo(wert) < 0 --> " + (werte.get(i).compareTo(wert) < 0)); // #### TEST
            while (i < werte.size() && werte.get(i).compareTo(wert) < 0) {
                
                i++;
                System.out.println("In While: i=" + i); // #### TEST
            }
            System.out.println("Nach WHile: i=" + i); // #### TEST
            // Knoten auswählen in dem der Wert liegen muss
            LinkedList<Knoten> pointer = dknoten.getPointer();
            Knoten knoten2 = pointer.get(i);
            pfad.add(knoten2);

            // rekursiv in dem gefunden Knoten weiter suchen
            addWertRekursiv(wert, knoten2, pfad);
            return true;

        } else {// Knoten ist Datenknoten
            
            // Einfügeposition suchen
            LinkedList<T> werte = knoten.getWerte();
            System.out.println("werte.get(i).compareTo(wert) < 0 --> " + (werte.get(i).compareTo(wert) < 0)); // #### TEST
            while (i < werte.size() && werte.get(i).compareTo(wert) < 0) {
                
                i++;
                System.out.println("In While: i=" + i); // #### TEST
            }
            
            // Einfügetest
            boolean overflow = knoten.addWert(wert);
            System.out.println("kein overflow --> " + overflow); // #### TEST
            if (overflow) {
                return true;
            } else {
                System.out.println("fordere split an bei (wert, i, pfad)--> " + wert +" "+ i + " " + pfad); // #### TEST
                split(wert, knoten, i, pfad);

                // toDo Split
                return false;
            }
        }
    }

    public boolean split(T wert, Knoten alterKnoten, int position, LinkedList<Knoten> pfad) {
        // neuen Knoten erstllen und mit Hälfte der Elemente füllen
        if (alterKnoten instanceof DirectoryKnoten) {
            // Casten 
            DirectoryKnoten alterDKnoten = (DirectoryKnoten) alterKnoten;
            //neuen DirectoryKnoten erstellen und Hälfe der Werte übertragen
            LinkedList<T> werte = alterDKnoten.getWerte();
            T temp = werte.get(0);
            DirectoryKnoten neuerKnoten = new DirectoryKnoten(m, temp);
            alterDKnoten.removeWert(temp);
            // Werte übertragen
            for (int i = 1; i < m-1; i++) {
                temp = werte.get(i);
                neuerKnoten.addWert(temp);
                alterDKnoten.removeWert(temp);
            }
            
            // Pointer übertragen
            LinkedList<Knoten> pointer = alterDKnoten.getPointer();
            System.out.println("HIER SCHAUEN: pointer.size() --> "+ pointer.size() ); //#### Test
            int anzahlPointer = neuerKnoten.getSize()+1;
            System.out.println("HIER SCHAUEN: neuerKnoten.getSize()+1 --> "+ anzahlPointer ); //#### Test
            Knoten tempKnoten;
            for (int i = 0; i < anzahlPointer ; i++) {
                tempKnoten = pointer.get(i);
                neuerKnoten.addLastPointer(tempKnoten);
                alterDKnoten.removePointer(tempKnoten);
            }
            // mittleren Wert im Vaterknoten einfügen
            temp = werte.get(0);

            // Vaterknoten auswählen
            // Prüfen ob Vaterknoten existiert
            DirectoryKnoten vaterknoten;
            if (pfad.size() > 1) { // Vaterknoten existiert bereits
                vaterknoten = (DirectoryKnoten) pfad.get(pfad.size() - 2);

                // den neuen Pointer im Vaterknoten setzen
                vaterknoten.setPointer(position, neuerKnoten);

                // alten Wert löschen
                alterKnoten.removeWert(temp);
                
                // Wert im Vaterknoten einfügen - true wenn kein Überlauf, false wenn Überlauf
                if (vaterknoten.addWert(temp)) {
                    return true;
                } else {
                    return false;
                }

            } else { // neuen Vaterknoten anlegen
                wurzel = new DirectoryKnoten(m, temp, neuerKnoten, alterKnoten);
                hoehe += 1;
            }
            
        } else {
            // neuen Datenknoten erstellen und Hälfe der Werte übertragen
            LinkedList<T> werte = alterKnoten.getWerte();
            T temp = werte.get(0);
            Datenknoten neuerKnoten = new Datenknoten(m, temp);
            alterKnoten.removeWert(temp);
            for (int i = 0; i < m-1; i++) {
                temp = werte.get(i);
                neuerKnoten.addWert(temp);
                alterKnoten.removeWert(temp);
            }
            // mittleren Wert im Vaterknoten einfügen
            temp = werte.get(0);

            // Vaterknoten auswählen
            // Prüfen ob Vaterknoten existiert
            DirectoryKnoten vaterknoten;
            if (pfad.size() > 1) { // Vaterknoten existiert bereits
                vaterknoten = (DirectoryKnoten) pfad.get(pfad.size() - 2);

                // den neuen Pointer im Vaterknoten setzen
                vaterknoten.setPointer(position, neuerKnoten);

                // Wert im Vaterknoten einfügen - true wenn kein Überlauf, false wenn Überlauf
                if (vaterknoten.addWert(temp)) {
                    return true;
                } else {
                    LinkedList<T> vaterWerte = vaterknoten.getWerte();
                    pfad.remove(alterKnoten);
                    split(vaterWerte.get(m), vaterknoten, m, pfad);
                    return false;
                }

            } else { // neuen Vaterknoten anlegen
                wurzel = new DirectoryKnoten(m, temp, neuerKnoten, alterKnoten);
                hoehe += 1;
            }

        }
        return true;

    }
}
