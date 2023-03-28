package org.madrid.hundir_los_tetraminos;

 /**
 * Clase Tetramino
    * Esta Clase se encarga de Aportar Forma a los Tetraminos asi como Rotaciones.
 */

public class Tetramino {
    private TipoTetramino tipoTetramino;
    private int[][] FormaTetramino;
    private int orientacion;
    private int HP;
    private Casilla[] posicion = new Casilla[4];

    /**
     * Constructor de tetramino que pondra un valor de vida por defecto de 4 y escogera un tipo aleatorio de Tetramino
     */
    public Tetramino() {
        TipoTetramino[] listaTipoTetraminos = TipoTetramino.values();
        double tipoRandomDouble = Math.random()*7;
        int tipoRandom = (int) tipoRandomDouble;
        this.tipoTetramino=listaTipoTetraminos[tipoRandom];
        HP=4;
        for (int i = 0; i < posicion.length; i++) {
            posicion[i]= new Casilla(-1,-1);
        }
    }

    public Tetramino(int orientacion, Casilla[] posicion) {
        this();
        this.orientacion = orientacion;
        this.posicion = posicion;
    }

    public Tetramino(TipoTetramino tipoTetramino, int orientacion, int HP, Casilla[] posicion) {
       this(orientacion, posicion);
       this.HP=HP;
    }


    /**
     * Metodo para Definir los Tetraminos y su orientacion
     * @param tipoTetranimo Es el Tipo de Forma de la pieza y su vida
     * @param orientacion Es la Posicion especifica de la forma en la cual esta rotada
     * @return Tetraminos definidos y orientacion para los mismos
     */
    public int[][] definirTetramino(TipoTetramino tipoTetranimo, int orientacion){
        switch (tipoTetramino){
            case LINEA:
                switch(orientacion){
                    case 1:
                    case 3:
                        for (int i = 0; i < 4; i++) {
                        }
                        FormaTetramino= new int[4][2];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[2][0] =1;
                        FormaTetramino[3][0] =1;
                        break;
                    case 2:
                    case 4:
                        FormaTetramino= new int[2][4];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[0][2] =1;
                        FormaTetramino[0][3] =1;
                        break;
                }
                break;

            case CRUZ:
                switch (orientacion){
                    case 1:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[0][2] =1;
                        FormaTetramino[1][1] =1;
                        break;
                    case 2:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][1] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[2][1] =1;
                        FormaTetramino[1][0] =1;
                        break;
                    case 3:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[1][2] =1;
                        FormaTetramino[0][1] =1;
                        break;
                    case 4:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[2][0] =1;
                        FormaTetramino[1][1] =1;
                        break;
                }
                break;

            case CUADRADO:
                FormaTetramino= new int[2][2];
                FormaTetramino[0][0] = 1;
                FormaTetramino[0][1] = 1;
                FormaTetramino[1][0] = 1;
                FormaTetramino[1][1] = 1;
                break;

            case L:
                switch (orientacion){
                    case 1:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[0][2] =1;
                        FormaTetramino[1][0] =1;
                        break;
                    case 2:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][1] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[2][1] =1;
                        FormaTetramino[0][0] =1;
                        break;
                    case 3:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[1][2] =1;
                        FormaTetramino[0][2] =1;
                        break;
                    case 4:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[2][0] =1;
                        FormaTetramino[2][1] =1;
                        break;
                }
                break;

            case INVERTL:
                switch(orientacion){
                    case 1:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[0][2] =1;
                        FormaTetramino[1][2] =1;
                        break;
                    case 2:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][1] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[2][1] =1;
                        FormaTetramino[2][0] =1;
                        break;
                    case 3:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[1][2] =1;
                        FormaTetramino[0][0] =1;
                        break;
                    case 4:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[2][0] =1;
                        FormaTetramino[0][1] =1;
                        break;
                }
                break;

            case Z:
                switch(orientacion){
                    case 1:
                    case 3:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[1][2] =1;
                        break;
                    case 2:
                    case 4:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][1] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[2][0] =1;
                        break;
                }
                break;

            case INVERTZ:
                switch(orientacion){
                    case 1:
                    case 3:
                        FormaTetramino= new int[2][3];
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[0][1] =1;
                        FormaTetramino[0][2] =1;
                        break;
                    case 2:
                    case 4:
                        FormaTetramino= new int[3][2];
                        FormaTetramino[0][0] =1;
                        FormaTetramino[1][0] =1;
                        FormaTetramino[1][1] =1;
                        FormaTetramino[2][1] =1;
                        break;
                }
                break;
        }
        return FormaTetramino;
    }

    /**
     * @return Tetramino con su forma ya declarada asi como las Orientaciones disponibles del mismo.
     */

    public TipoTetramino getTipoTetramino() {
        return tipoTetramino;
    }

    public void setTipoTetramino (TipoTetramino tipoTetramino) {
        tipoTetramino = tipoTetramino;
    }

    public int getOrientacion() {
        return orientacion;
    }

    /**
     * Metodo para poder seleccionar la orientacion de un tetramino
     * @param orientacion Rotacion del Tetramino especificada anteriormente en el metodo DefinirTetramino
     */
    public void setOrientacion(int orientacion) {
        if (orientacion<1 || orientacion>4){
            System.out.println("Error, orientación debe ser entre 1 y 4, elige otra. (Por defecto se pondrá orientación 1");
            orientacion=1;
        }
        else this.orientacion = orientacion;
    }
    /**
     * @return Rotacion del tetramino
     */

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Casilla[] getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla[] posicion) {
        this.posicion = posicion;
    }

    /**
     * Metodo para mostrar si esta tocado
     */
    public void tocado(){
        setHP(HP-1);
        System.out.println("A el barco le quedan " + getHP() + " HP.");
    }

    /**
     * Metodo que muestra si el barco esta en estado HUNDIDO
     * @return esHundido , boolean que comprueba que la vida de un barco sea 0 y establece las casillas del array posicion como hundido
     */
    public boolean esHundido(){
        boolean esHundido = false;
        if(this.HP<=0){
                esHundido=true;
                for (Casilla casilla : posicion) {
                    casilla.setEstado(Estado.HUNDIDO);
                }
        }
        return esHundido;
    }
}
