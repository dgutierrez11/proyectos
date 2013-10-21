/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problema;

import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase se encarga de almacenar la soluciones que se van construyendo del problema
 * respectivo, y de almacenar los nodos hijos prometedores que este posee para reconstruir la solucion final.
 * 
 */
public class Nodo {

    Tablero tablero; 
    List<Nodo> hijosNodo;  //Lista de hijos prometedores de tipo Nodo. 
    // Variable necesaria para obtener informacion y recuperar la informacion
    Nodo siguiente;
    Long profundidad; // indica el numero de movimientos (profundidad)
    
    /**
     * Crea un Nodo a partir de un nivel dentro del arbol y el tamaño del vector solución
     * 
     */
    public Nodo() {
        this.tablero  = new Tablero();
        hijosNodo = new LinkedList<Nodo>();
    }
    
    public Nodo(Tablero tablero, Long profundidad) {
        this.tablero  = tablero;
        hijosNodo = new LinkedList<Nodo>();
        this.profundidad = profundidad;
    }
     
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    /**
     * Obtiene Los hijos prometedores asociados al Nodo.
     * @return La lista de hijos prometedores del Nodo.
     */
    public List getHijosNodo() {
        return hijosNodo;
    }

    
    /**
     * Asocia un hijo prometedor al Nodo.
     * @param hijosNodo, El hijo prometedor que sera asociado a un Nodo. 
     */
    
    public void agregarHijoNodo(Nodo hijosNodo) {
        this.hijosNodo.add(hijosNodo);
    }
    
    /**
     * Compara si dos nodos son iguales comparando su tablero
     * @param n  Nodo con el que se quiere comparar
     */
    public boolean sonIguales(Nodo n) {
        if (this.getTablero().sonIguales(n.getTablero()))
            return true;
        return false;
    }
 
    /**
     * Método encargado de retornar un String con la solución que lleva construida el Nodo.
     * @return Un String con la solución construida por el Nodo.
     */
    @Override
    public String toString(){
        return this.tablero.toString();    
    }
    /**
     * Metodo get para el atributo siguiente
     *
     * @return
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Metodo set para el atributo siguiente
     *
     * @param siguiente
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Metodo get para el atributo profundidad
     *
     * @return numeroMovimientos
     */
    public Long getProfundidad() {
        return profundidad;
    }

    /**
     * Metodo set para el atributo profundidad
     *
     * @param numeroMovimientos
     */
    public void setProfundidad(Long profundidad) {
        this.profundidad = profundidad;
    }
}
