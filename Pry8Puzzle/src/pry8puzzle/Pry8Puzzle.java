/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pry8puzzle;

import java.util.logging.Level;
import java.util.logging.Logger;
import problema.AlgoritmoRyP;
import problema.Nodo;

/**
 *
 * @author David
 */
public class Pry8Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Nodo tableroInicial = new Nodo();
        AlgoritmoRyP prueba1 = new AlgoritmoRyP(tableroInicial);
        System.out.println(tableroInicial.toString());
        try {
            Nodo solucion = prueba1.RyP_laMejor();
            System.out.println("\nSolucion: \n");
             System.out.println(solucion.toString());
             System.out.println("\nNodo analizados: \n");
             System.out.println(prueba1.getNumanalizados());
             System.out.println("\nNodo generados: \n");
             System.out.println(prueba1.getNumgenerados());
             System.out.println("\nNodo podados: \n");
             System.out.println(prueba1.getNumpodados());
        } catch (InterruptedException ex) {
            Logger.getLogger(Pry8Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
