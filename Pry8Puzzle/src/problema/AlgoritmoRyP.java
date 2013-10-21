/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package problema;

import java.util.*;

/**
 *
 * @author David
 */
public class AlgoritmoRyP {

    Queue hijosc=new PriorityQueue(); // Cola que hace el recorrido por anchura del arbol 
    Nodo tableroInicial; // Una instancia de la clase Nodo. El nodo raiz del Arbol 
    
    int numgenerados;
    int numanalizados;
    int numpodados;
   
    public static int MAX = 5;
    private int cota;
    private int valor;   
    private int valorSolucion;
            
    public AlgoritmoRyP(Nodo tableroInicial) {
        this.tableroInicial = tableroInicial;
    }
    
  
    public Nodo RyP_laMejor() throws InterruptedException {
        this.numgenerados = 0;
        this.numanalizados = 0;
        this.numpodados = 0;
        //Queue<Nodo> estructura = new PriorityQueue<Nodo>();
        //estructura.offer(tableroInicial);
        List<Nodo> estructura = new LinkedList<Nodo>();
        estructura.add(tableroInicial);
        Nodo prometedor;
        int nhijos = 0;
        List<Nodo> hijosN;
        Nodo solucion = this.noHaySolucion();
        valorSolucion = MAX;
        this.ponerCota(valorSolucion);
        Long numMovimientos = 0l;
        while (!estructura.isEmpty()) {
            // Se obtiene el nodo 
            prometedor = estructura.get(0);
            estructura.remove(prometedor);
            if (prometedor != null) {
                numanalizados = incrementar(numanalizados, 1);
                //nhijos = this.expandir(prometedor);
                List<Nodo> auxList = new ArrayList<Nodo>();
                auxList.add(prometedor);
                nhijos = this.expandir(auxList, numMovimientos++);
                prometedor = auxList.get(0);
                numgenerados = incrementar(numgenerados, nhijos);
                hijosN = prometedor.getHijosNodo();// CONTIENE LA LISTA CON LOS HIJOS PROMETEDORES DEL NODO SELECCIONADO
                eliminar(prometedor);
                for (int i = 0; i < nhijos; i++) {
                    System.out.println("\n");
                     System.out.println(hijosN.get(i).toString());
                    if (esAceptable(hijosN.get(i))) {
                        if (esSolucion(hijosN.get(i))) {
                            this.valor = this.valor(hijosN.get(i));
                            if (valor < valorSolucion) {
                                this.eliminar(solucion);
                                solucion = hijosN.get(i);
                                valorSolucion = valor;
                                this.ponerCota(valor);
                            }
                            return hijosN.get(i);
                        } else {
                            estructura.add(hijosN.get(i));
                            ordenarPorPrioridad(estructura, null);
                        }
                    } else {
                        this.eliminar(hijosN.get(i));
                        this.incrementar(numpodados, 1);
                    }
                }
            }
        }
        return solucion;
    }
    
    public void ordenarPorPrioridad(List<?> lista, final String nombreCampo) {
        Collections.sort(lista, new Comparator() {
            public int compare(Object obj1, Object obj2) {

                Nodo campo1 = (Nodo) obj1;
                Nodo campo2 = (Nodo) obj2;
                if (campo1.getTablero() != null && campo2.getTablero() != null) {
                    if (campo1.getTablero().h2() > campo2.getTablero().h2()) {
                        return 1;
                    } else if (campo1.getTablero().h2() < campo2.getTablero().h2()) {
                        return -1;
                    }
                    return 0;
                }
                return 0;
            }
        });
    }
    
    private int incrementar(int i, int incremento) {
        return i + incremento;
    }
    
    /**
     * 
     * Este metodo es para copiar un nodo y añadirle el tablero que va a contener el siguiente movimiento: 
     *
     * @param n es el nodo que se va a copiar. 
     * @return . 
     */
    private Nodo copiar(Nodo n1) {
        Nodo nuevo = duplicar(n1);
        Nodo n2 = new Nodo(new Tablero(n1.getTablero()), n1.getProfundidad());
        n2.setSiguiente(nuevo);
        return n2;
    }

    /**
     *
     * Esta función utiliza la que duplica un nodo dado
     *
     * @param n es el nodo que se va a copiar.
     * @return .
     */
    private Nodo duplicar(Nodo n1) {
        Nodo n2 = new Nodo(new Tablero(n1.getTablero()), n1.getProfundidad());
        n2.setSiguiente(n1.getSiguiente());
        if (n1.getSiguiente() != null) {
            duplicar(n1.getSiguiente());
        }
        return n2;
    }

    /**
     *
     * Este metodo da a conocer si es una solucion o no el nodo en el que estoy
     * entonces lo que el hace es mirar si el nodo que estoy evaluando es una
     * hoja si es una hoja retorna true si no returna false.
     *
     * @param n es el nodo nodo a evaluar como solucion.
     * @return true o false, es soluón o no solución.
     */
    public boolean esSolucion(Nodo n) {
        //return n.tablero.verificarFinal();
       // return n.getTablero().evaluarEstado() == 0;
        return n.getTablero().h2()== 0;
    }

    /**
     * Este metodo se encarga de la ramificación de un nodo candidato, es decir,
     * de construir los nodos hijos del nodo seleccionado. Antes de asociar un
     * nodo hijo al nodo seleccionado, pregunta si este es prometedor (es decir,
     * si a través de ste es posible encontrar una solución); si no es
     * prometedor, realiza la poda respectiva; si es prometedor, asocia este
     * nodo como nodo hijo del nodo seleccionado.
     *
     * @param estructura
     * @param profundidad
     * @return El numero de hijos prometedores que tiene el Nodo seleccionado.
     *
     */
    public int expandir(List<Nodo> estructura, Long profundidad) {
        int numHijos = 0;
        if (estructura.get(0) != null) {
            Nodo prom = estructura.get(0);
            estructura.clear();
            this.valor =  prom.getTablero().evaluarEstado();          
            Tablero aux = prom.getTablero();
            // Se debe crear un nuevo nivel en el �rbol de juego
            for (int i = Tablero.IZQUIERDA; i <= Tablero.ABAJO; i++) {
                if (aux.movimientoValido(i)) {
                    Nodo nodoCopia = this.copiar(prom);
                    Tablero hijo = new Tablero(aux, i);
                    nodoCopia.setTablero(hijo);
                    nodoCopia.setProfundidad(profundidad);
                    prom.agregarHijoNodo(nodoCopia);
                    numHijos++;
                }
            }
             estructura.add(prom);
        }
        return numHijos;
    }

    /**
     * Compara si dos nodos son iguales comparando su tablero
     *
     * @param n Nodo con el que se quiere comparar
     */
    public boolean esAceptable(Nodo n) {
        /**
         * mira si ese movimiento ya lo ha hecho antes
         */
        Nodo aux = n.getSiguiente();
        while (aux != null) {
            if (n.sonIguales(aux)) {
                return false;
            }
            aux = aux.getSiguiente();
        }
        return true;
    }

    /**
     * Este método se encarga de verificar que la reina a ubicar no este
     * amenazada por otra reina.
     *
     * @param s vector que contiene las reinas y soluciones
     * @param k nivel del arbol
     * @param j colunma correspondiente
     * @return true o false
     *
     */
    public boolean esPrometedor(int[] s, int k, int j) {
        for (int l = 0; l < k; l++) {
            if ((s[l] == j) || (ValAbs(s[l], j) == k - l)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo para que calcula el valor absoluto de la diferencia de dos enteros
     * no negativos.
     *
     * @param a un numero
     * @param b un numero
     * @return retorna la resta de a-b o b-a
     */
    public int ValAbs(int a, int b) {
        if (a > b) {
            return a - b;
        } else {
            return b - a;
        }
    }

    public Integer h(Nodo n) {
        return 1;
    }

    public void ponerCota(int c) {
        this.cota = c;
    }

    /**
     * Metodo que devuelve el valor asociado a un nodo, y se utiliza para
     * comparar soluciones con la cota superior encontrada hasta el momento.
     *
     * @param n nodo del que se obtiene el valor
     * @return el valor del nodo
     */
    public int valor(Nodo n) {
        return n.getTablero().evaluarEstado();
    }

    public void eliminar(Nodo n) {
        if (n != null) {
            if (n.getSiguiente() != null) {
                eliminar(n.getSiguiente());
            }
            n = null;
        }
    }

    public Nodo noHaySolucion() {
        return null;
    }

    public void imprimir(Nodo n){
    }
    
    
    public int getNumgenerados() {
        return numgenerados;
    }

    public void setNumgenerados(int numgenerados) {
        this.numgenerados = numgenerados;
    }

    public int getNumanalizados() {
        return numanalizados;
    }

    public void setNumanalizados(int numanalizados) {
        this.numanalizados = numanalizados;
    }

    public int getNumpodados() {
        return numpodados;
    }

    public void setNumpodados(int numpodados) {
        this.numpodados = numpodados;
    }
}
