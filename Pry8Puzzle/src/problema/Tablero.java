package problema;

/**
 * Representa el tablero de 3 x 3 en el juego 8-puzzle<br>
 * <b>inv:</b> <br>
 * La matriz tablero est� inicializada con 3 x 3 posiciones <br>
 * 0 <= huecoX <= 2 <br>
 * 0 <= huecoY <= 2 <br>
 * En el tablero debe haber una posici�n marcada como HUECO y las otras deben contener todos los n�meros entre 1 y 8.
 */
public class Tablero
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante usada para identificar el hueco en el tablero
     */
    public final static int HUECO = -1;

    /**
     * Movimiento del hueco hacia la izquierda
     */
    public final static int IZQUIERDA = 0;

    /**
     * Movimiento del hueco hacia la derecha
     */
    public final static int DERECHA = 1;

    /**
     * Movimiento del hueco hacia arriba
     */
    public final static int ARRIBA = 2;

    /**
     * Movimiento del hueco hacia abajo
     */
    public final static int ABAJO = 3;

    /**
     * Movimiento inv�lido en el tablero actual
     */
    public final static int INDEFINIDO = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La matriz que representa el estado actual del tablero. <br>
     */
    private byte[][] tablero;
    /**
     * Coordenada X del hueco en el tablero
     */
    private short huecoX;

    /**
     * Coordenada Y del hueco en el tablero
     */
    private short huecoY;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un tablero de manera aleatoria
     */
    public Tablero( )
    {
        // Crea la matriz
        tablero = new byte[3][3];
        // Llena el tablero con valores consecutivos de 1 a 9
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                tablero[ i ][ j ] = ( byte ) ( j + ( i * 3 ) + 1 );
            }
        }
        // Sit�a el hueco en la �ltima posici�n del tablero (reemplazando el 9)
        tablero[ 2 ][ 2 ] = HUECO;
        huecoX = 2;
        huecoY = 2;
        // Hace un cierto n�mero de movimientos aleatorios
      /*  int nJugadas = ( int ) ( Math.random( ) * 1000 );
        while( nJugadas > 0 )
        {
            // Escoge aleatoriamente uno de los 4 movimientos posibles
            int movimiento = ( ( int ) ( Math.random( ) * 4 ) ) % 4;
            if( movimientoValido( movimiento ) )
                mover( movimiento );
            nJugadas--;
        }*/
        tablero = new byte[][]{
            {1, 2, 3},{5, 6, -1},{7, 8, 4}  
        };
        huecoX = 1;
        huecoY = 2;
        //return tablero[ 0 ][ 0 ] == 1 && tablero[ 0 ][ 1 ] == 2 && tablero[ 0 ][ 2 ] == 3 && tablero[ 1 ][ 0 ] == 4 && tablero[ 1 ][ 1 ] == 5 && tablero[ 1 ][ 2 ] == 6 && tablero[ 2 ][ 0 ] == 7 && tablero[ 2 ][ 1 ] == 8 && tablero[ 2 ][ 2 ] == HUECO;
        verificarInvariante( );
    }
    /**
     * Crea un tablero como una copia de otro tablero que recibe como par�metro
     * @param copia Copia del tablero a partir del cual se va a crear el nuevo tablero
     */
    public Tablero( Tablero copia )
    {
        tablero = new byte[3][3];
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                tablero[ i ][ j ] = copia.tablero[ i ][ j ];
            }
        }
        huecoX = copia.huecoX;
        huecoY = copia.huecoY;
        verificarInvariante( );
    }

    /**
     * Crea un tablero como una copia de otro tablero, en el cual se debe hacer un movimiento en la direcci�n que se recibe como par�metro. Un movimiento en esa direcci�n es
     * posible dado el estado del tablero.
     * @param copia Tablero a partir del cual se va a crear el nuevo tablero
     * @param direccion Direcci�n en la que se va a afectuar el movimiento
     */
    public Tablero( Tablero copia, int direccion )
    {
        this( copia );
        mover( direccion );
    }
    
    public Tablero(int[][] tab) {

        tablero = new byte[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[ i][ j] = (byte) tab[ i][ j];
                if (tablero[ i][ j] == 0) {
                    tablero[ i][ j] = HUECO;
                    huecoX = (short) i;
                    huecoY = (short) j;
                }
            }
        }
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Realiza un movimiento desplazando la pieza definida por los par�metros <br>
     * (x,y) al lugar del hueco. <br>
     * @param x Coordenada x de la pieza que se debe mover - 0 <= x <= 2
     * @param y Coordenada y de la pieza que se debe mover - 0 <= y <= 2
     * @return el movimiento que se hizo
     */
    public int mover( int x, int y )
    {
        int movimiento = INDEFINIDO;
        // Mover seg�n la posici�n relativa del hueco
        if( huecoX == x - 1 && huecoY == y && huecoX < 2 )
        {
            mover( ABAJO );
            movimiento = ABAJO;
        }
        else if( huecoX == x + 1 && huecoY == y && huecoX > 0 )
        {
            mover( ARRIBA );
            movimiento = ARRIBA;
        }
        else if( huecoX == x && huecoY == y - 1 && huecoY < 2 )
        {
            mover( DERECHA );
            movimiento = DERECHA;
        }
        else if( huecoX == x && huecoY == y + 1 && huecoY > 0 )
        {
            mover( IZQUIERDA );
            movimiento = IZQUIERDA;
        }
        verificarInvariante( );
        return movimiento;
    }

    /**
     * Hace en el tablero un movimiento en la direcci�n descrita en el par�metro. <br>
     * <b>post:</b>
     * @param direccion El movimiento que ser� realizado - direcci�n pertenece a {ARRIBA, ABAJO, IZQUIERDA, DERECHA}, el movimiento es v�lido para el estado actual del tablero
     */
    public void mover( int direccion )
    {
        switch( direccion )
        {
            case IZQUIERDA:
                tablero[ huecoX ][ huecoY ] = tablero[ huecoX ][ huecoY - 1 ];
                tablero[ huecoX ][ huecoY - 1 ] = HUECO;
                huecoY--;
                break;
            case DERECHA:
                tablero[ huecoX ][ huecoY ] = tablero[ huecoX ][ huecoY + 1 ];
                tablero[ huecoX ][ huecoY + 1 ] = HUECO;
                huecoY++;
                break;
            case ABAJO:
                tablero[ huecoX ][ huecoY ] = tablero[ huecoX + 1 ][ huecoY ];
                tablero[ huecoX + 1 ][ huecoY ] = HUECO;
                huecoX++;
                break;
            case ARRIBA:
                tablero[ huecoX ][ huecoY ] = tablero[ huecoX - 1 ][ huecoY ];
                tablero[ huecoX - 1 ][ huecoY ] = HUECO;
                huecoX--;
                break;
        }
        verificarInvariante( );
    }

    /**
     * Indica si el movimiento en la direcci�n especificada es v�lido
     * @param direccion Direcci�n a validar
     */
    public boolean movimientoValido( int direccion )
    {
        return ( direccion == IZQUIERDA && huecoY > 0 ) || ( direccion == DERECHA && huecoY < 2 ) || ( direccion == ABAJO && huecoX < 2 ) || ( direccion == ARRIBA && huecoX > 0 );
    }

    /**
     * Verifica si ya se termin� el juego
     * @return Retorna true si el juego ya se termin�
     */
    public boolean verificarFinal( )
    {
        return tablero[ 0 ][ 0 ] == 1 && tablero[ 0 ][ 1 ] == 2 && tablero[ 0 ][ 2 ] == 3 && tablero[ 1 ][ 0 ] == 4 && tablero[ 1 ][ 1 ] == 5 && tablero[ 1 ][ 2 ] == 6 && tablero[ 2 ][ 0 ] == 7 && tablero[ 2 ][ 1 ] == 8 && tablero[ 2 ][ 2 ] == HUECO;
    }

    public byte[][] tableroSolucion() {
       return new byte[][]{
            {1, 2, 3}, {5, 8, 6}, {-1, 7, 4}
        };
    }
    /**
     * Retorna la direcci�n inversa a la direcci�n dada
     * @param direccion Direcci�n de la que se desea la inversa - direcci�n pertenece a {ARRIBA, ABAJO, IZQUIERDA, DERECHA}
     */
    public int darInversa( int direccion )
    {
        switch( direccion )
        {
            case IZQUIERDA:
                return DERECHA;
            case DERECHA:
                return IZQUIERDA;
            case ARRIBA:
                return ABAJO;
            case ABAJO:
                return ARRIBA;
            default:
                return INDEFINIDO;
        }
    }

    /**
     * Calcula un valor para el estado del tablero usando la distancia Manhattan (la distancia en una grilla de cada ficha a su posici�n final). Si la funci�n vale 0,
     * significa que este es un estado del juego en el que ya se termin�.<br>
     * La funci�n usada para calcular el valor del estado no es totalmente correcta, pero generalmente valores peque�os indican proximidad a la soluci�n.<br>
     * Puede ocurrir sin embargo que seguir un camino hacia estados con valores peque�os lleve al juego a un m�nimo local del cu�l no puede salir sino explorando �rboles de
     * mayor profundidad.
     * @return Retorna el valor del estado
     */
    public int evaluarEstado( )
    {
        int distancia = 0;
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                int numero = tablero[ i ][ j ];
                if( numero != Tablero.HUECO )
                {
                    // Calcular la posici�n donde deber�a estar el n�mero
                    int posicionEsperadaX = ( numero - 1 ) / 3;
                    int posicionEsperadaY = ( numero - 1 ) % 3;
                    distancia += Math.abs( posicionEsperadaX - i ) + Math.abs( posicionEsperadaY - j );
                }
            }
        }
        return distancia;
    }

    public int h() {
        int cuenta = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                double temp = (j + (i - 1)*3) % 9;
                int numero = tablero[ i-1][ j-1];
                if (numero != temp) {
                    cuenta++;
                }
            }
        }
        return cuenta;
    }
    
    public int h2() {
        int cuenta = 0;
        byte[][] tableroFinal = this.tableroSolucion();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[ i][ j] != tableroFinal[ i][ j]) {
                    cuenta++;
                }
            }
        }
        return cuenta;
    }
        
    /**
     * Retorna la ficha que se encuentra en la direcci�n especifica con relaci�n <br>
     * a la posici�n del hueco
     * @param direccion Direcci�n para retornar la ficha
     * @return Ficha que se encuentra en la direcci�n espeficada. Si no hay ficha <br>
     *         en tal direcci�n ser retorna INDEFINIDO
     */
    public int darFicha( int direccion )
    {
        switch( direccion )
        {
            case IZQUIERDA:
                return tablero[ huecoX ][ huecoY - 1 ];
            case DERECHA:
                return tablero[ huecoX ][ huecoY + 1 ];
            case ARRIBA:
                return tablero[ huecoX - 1 ][ huecoY ];
            case ABAJO:
                return tablero[ huecoX + 1 ][ huecoY ];
            default:
                return INDEFINIDO;
        }
    }

    /**
     * Retorna una copia de la matriz que representa al tablero
     * @return La matriz del tablero
     */
    public int[][] darTablero( )
    {
        int[][] copiaTablero = new int[3][3];
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                copiaTablero[ i ][ j ] = tablero[ i ][ j ];
            }
        }
        return copiaTablero;
    }
   public static int[][] darSolucion(byte[][] tableroSolucion)
    {
  
        int[][] copiaTablero = new int[3][3];
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                copiaTablero[ i ][ j ] = tableroSolucion[ i ][ j ];
            }
        }
        return copiaTablero;
    }
    /**
     * Metodo que compara si dos tableros son iguales
     * @return false si son diferentes
     *         true si son iguales
     */
    public boolean sonIguales(Tablero tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.tablero[ i][ j] != tablero.tablero[ i][ j]) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Retorna la cadena que representa al tablero
     * @return Cadena que representa el tablero
     */
    public String toString( )
    {
        return "" + tablero[ 0 ][ 0 ] + "-" + tablero[ 0 ][ 1 ] + "-" + tablero[ 0 ][ 2 ] + "\n" + tablero[ 1 ][ 0 ] + "-" + tablero[ 1 ][ 1 ] + "-" + tablero[ 1 ][ 2 ] + "\n" + tablero[ 2 ][ 0 ] + "-" + tablero[ 2 ][ 1 ] + "-" + tablero[ 2 ][ 2 ];

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que en el tablero aparezcan todos los n�mero y el hueco
     * @return true si en el tablero aparecen los 8 n�meros y el hueco. false en caso contrario
     */
    private boolean todosPresentes( )
    {
        boolean[] numerosEncontrados = new boolean[8];
        boolean huecoEncontrado = false;
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                if( tablero[ i ][ j ] == HUECO )
                    huecoEncontrado = true;
                else
                    numerosEncontrados[ tablero[ i ][ j ] - 1 ] = true;
            }
        }
        // Verifica que haya encontrado el hueco y los 8 n�meros
        if( !huecoEncontrado )
            return false;
        for( int i = 0; i < numerosEncontrados.length; i++ )
            if( !numerosEncontrados[ i ] )
                return false;
        return true;
    }

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b> <br>
     * La matriz tablero est� inicializada con 3 x 3 posiciones <br>
     * 0 <= huecoX <= 2 <br>
     * 0 <= huecoY <= 2 <br>
     * En el tablero debe haber una posici�n marcada como HUECO y las otras deben contener todos los n�meros entre 1 y 8.
     */
    private void verificarInvariante( )
    {
        assert tablero.length == 3 : "Dimensi�n inv�lida";
        assert 0 <= huecoX && huecoX <= 2 : "huecoX inv�lido";
        assert 0 <= huecoY && huecoY <= 2 : "huecoY inv�lido";
        for( int i = 0; i < 3; i++ )
            assert tablero[ i ].length == 3 : "Dimensi�n inv�lida";
        assert todosPresentes( ) : "Tablero inv�lido";
    }
}
