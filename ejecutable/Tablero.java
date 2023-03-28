package com.example.hundir_los_tetraminos;

    /**
    * Clase del tablero
        * Esta clase Generara un Tablero tanto para el Jugador como para el oponente y los metodos para asi funcionar con el resto de clases.
        * @author Ari-Dragos
        * @version 23/03/2023 1.0
    */
    public class Tablero {
        private final Casilla[][] arrayTablero = new Casilla[10][10];
    //Aqui establecemos un Array de casillasReveladas que tendra un valor de 100 en representacion de las 100 casillas disponibles en el tablero
        private Casilla[] casillasReveladas=new Casilla[100];

    /**
    * Colores de ANSI para las casillas
    */
    //Aqui cogemos y ponemos los colores en ANSI
    public static final String ANSI_RESET= "\u001B[0m";
    public static final String ANSI_AZUL = "\u001b[34;1m";
    public static final String ANSI_VERDE = "\u001b[32;1m";
    public static final String ANSI_ROJO = "\u001b[31m";

    /**
     * Constructor de la clase Tablero que contiene los metodos mencionados anteriormente y en este constructor se crean objetos casilla con un Estado Especifico en este caso Agua
     */
    //Aqui hacemos un for i que recorre toda la longitud del tablero y aplica como estado por defecto AGUA
    public Tablero() {
        for (int i = 0; i < arrayTablero.length; i++) {
            for (int j = 0; j < arrayTablero.length; j++) {
                this.arrayTablero[i][j] = new Casilla(i,j,Estado.AGUA);
            }
        }
    //Aqui ponemos un for i que recorre un array de Casillas[] llamado casillasReveladas para mostrar las casillas una vez golpeadas
        for (int i = 0; i < casillasReveladas.length; i++) {
            this.casillasReveladas[i]=new Casilla(-1,-1);
        }
    }
    /**
     * Metodos que especifican la construccion e implementacion de los objetos casilla en una matriz de 10x10
     * @return Posiciones para el objeto Casilla[][] en una matriz de 10x10.
     */

    public Casilla[][] getArrayTablero() {
        return arrayTablero;
    }
    public Casilla[] getCasillasReveladas() {
        return casillasReveladas;
    }
    public void setCasillasReveladas(Casilla[] casillasReveladas) {
        this.casillasReveladas = casillasReveladas;
    }

    /**
     * Métodos para comprobar que las casillas que vayan a seleccionarse cumplan unas condiciones (poner tetraminos)¡
     * @param tetramino es un Objeto derivado de la clase Tetramino que sirve para definir la vida y los valores del objeto
     * @param tipoTetramino es un Objeto derivado de la clase Tetramino que especifica la forma del Tetramino a Colocar
     * @param casillaSeleccionada es un Objeto que sirve para determinar que casilla a escogido el usuario y si se puede colocar el objeto Tetramino en ella y aplicar un estado acorde a ello
     * @param orientacion es un int que es utilizado por un switch derivado de la clase tetramino que usamos en este metodo para definir la orientacion del tetramino en definirTetramino()
     * @return Casilla Seleccionada debido a que se cumplen las condiciones
     */
    //un método para inicializar el tablero (colocar los barcos de forma automática o pidiendo datos).
    public boolean comprobarCasillaInicializar(Tetramino tetramino, TipoTetramino tipoTetramino, int orientacion, Casilla casillaSeleccionada) {
        //Si se intenta poner un tetramino encima de una casilla con barco volverá a pedir que se ponga la misma ficha con i-- en el Juego, lo mismo si se saldría del array tablero.
        boolean tetraminoColocable = true;
        //Comprobar si las casillas que van a ser seleccionadas tienen barcos
        for (int m = 0; m < tetramino.definirTetramino(tipoTetramino, orientacion).length; m++) {
            for (int n = 0; n < tetramino.definirTetramino(tipoTetramino, orientacion)[0].length; n++) {
                if (casillaSeleccionada.getFila() + tetramino.definirTetramino(tipoTetramino, orientacion).length > arrayTablero.length || casillaSeleccionada.getColumna() + tetramino.definirTetramino(tipoTetramino, orientacion)[0].length > arrayTablero.length || arrayTablero[casillaSeleccionada.getFila() + m][casillaSeleccionada.getColumna() + n].getEstado().equals(Estado.BARCO)) {
                    tetraminoColocable = false;
                }
            }
        }
        return tetraminoColocable;
    }
    /**
     * Metodo Funcion para la funcionalidad de colocar barcos
     * @param tetramino Objeto derivado de la clase Tetramino que sirve para establecer puntos de vida al objeto y atributos al mismo.
     * @param tipoTetramino Objeto derivado de la clase Tetramino que sirve para establecer la forma del objeto en el Tablero.
     * @param tablero Objeto de la Clase Tablero que sirve para definir el area de juego en funcion a Casillas que cuentan como Objetos.
     * @param orientacion Int que es utilizado por un switch derivado de la clase Tetramino que sirve para determinar la forma en la que se "orienta" el objeto
     * @param casillaSeleccionada Objeto que sirve para determinar que casilla se a accionado por el usuario.
     */
    public void inicializarTablero(Tetramino tetramino, TipoTetramino tipoTetramino, Tablero tablero, int orientacion, Casilla casillaSeleccionada) {
    //variable auxiliar que solo llegará hasta 4 para el array posicion[] del tetramino.
        int l = 0;
        //Cogerá el array formaTetramino (según su tipo y su orientacion para crear la forma del tetramino.
        for (int j = 0; j < tetramino.definirTetramino(tipoTetramino, orientacion).length; j++) {
            for (int k = 0; k < tetramino.definirTetramino(tipoTetramino, orientacion)[0].length; k++) {
                //Si las casillas que devuelve la formaTetramino[] == 1 , establecerá las casillas como BARCO, añadirá la casilla al array de casillas del objeto tetramino posicion[], y les dará un alias.
                if (tetramino.definirTetramino(tipoTetramino, orientacion)[j][k] == 1) {
                    tablero.getArrayTablero()[casillaSeleccionada.getFila() + j][casillaSeleccionada.getColumna() + k].setEstado(Estado.BARCO);
                    tablero.getArrayTablero()[casillaSeleccionada.getFila() + j][casillaSeleccionada.getColumna() + k].setAliasTetramino(tetramino);
                    tablero.getArrayTablero()[casillaSeleccionada.getFila() + j][casillaSeleccionada.getColumna() + k].getAliasTetramino().getPosicion()[l].setFila(casillaSeleccionada.getFila() + j);
                    tablero.getArrayTablero()[casillaSeleccionada.getFila() + j][casillaSeleccionada.getColumna() + k].getAliasTetramino().getPosicion()[l].setColumna(casillaSeleccionada.getColumna() + k);
                    l++;
                }
            }
        }
        }

      /**
       * Funcion para imprimir el tablero despues de pasar los metodos anteriormente mencionados.
       * @param tablero1 Objeto de Tablero
       */
    public void imprimirTablero(Tablero tablero1) {
        System.out.print("    ");
        for (int i = 0; i < arrayTablero.length ; i++) {
            System.out.print( (i+1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < arrayTablero.length; i++) {
            char filaChar = (char) (i+65);
            System.out.print(filaChar + "  ");
            for (int j = 0; j < arrayTablero.length; j++) {
                if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.AGUA)) {
                    System.out.print(ANSI_AZUL + "◼  " + ANSI_RESET);
                } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.BARCO)) {
                    System.out.print(ANSI_VERDE + "▣  " + ANSI_RESET);
                } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.TOCADO)) {
                    System.out.print(ANSI_ROJO + "▢  " + ANSI_RESET);
                } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.HUNDIDO)) {
                    System.out.print(ANSI_ROJO + "▧  " + ANSI_RESET);
                }
            }
            System.out.println();

        }
    }
        /**
         * Metodo para comprobar si la casilla Se ha desvelado con y asi mostrar las casillas golpeadas con sus estados mostrados
         * @param casilla Objeto Casilla Declarado anteriormente
         * @param tablero Objeto Tablero declarado anteriormente
         * @return Casilla golpeada mostrando estado Agua en caso de golpear agua asi como Tocado o Hundido
         */

    public boolean casillaEsDesvelada(Casilla casilla, Tablero tablero){
       boolean casillaEsDesvelada=false;
        for (int i = 0; i < casillasReveladas.length; i++) {
            if (tablero.getCasillasReveladas()[i].equals(casilla)){
                casillaEsDesvelada=true;
            }
        }
       return casillaEsDesvelada;
    }

        /**
         * Funcion para Imprimir el tablero del enemigo
         * @param tablero1 Objeto derivado de Tablero
         */
    public void imprimirTableroEnemigo(Tablero tablero1) {
        System.out.print("    ");
        for (int i = 0; i < arrayTablero.length ; i++) {
            System.out.print( (i+1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < arrayTablero.length; i++) {
            char filaChar = (char) (i+65);
            System.out.print(filaChar + "  ");
            for (int j = 0; j < arrayTablero.length; j++) {
                //Si la casilla ya ha sido desvelada antes:
                if (casillaEsDesvelada(arrayTablero[i][j], tablero1)){
                    if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.AGUA)) {
                        System.out.print(ANSI_AZUL + "◼  " + ANSI_RESET);
                    } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.BARCO)) {
                        System.out.print(ANSI_VERDE + "▣  " + ANSI_RESET);
                    } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.TOCADO)) {
                        System.out.print(ANSI_ROJO + "▢  " + ANSI_RESET);
                    } else if (tablero1.arrayTablero[i][j].getEstado().equals(Estado.HUNDIDO)) {
                        System.out.print(ANSI_ROJO + "▧  " + ANSI_RESET);
                    }
                }
                else{
                    System.out.print("◼  ");
                }
            }
            System.out.println();

        }
    }

    public static void main (String[]args) {
    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();
    System.out.println(jugador1.getTablero().getArrayTablero()[3][1].getFila());
    System.out.println(jugador1.getTablero().getArrayTablero()[3][1].getColumna());
        jugador1.getTablero().getArrayTablero()[3][1].setEstado(Estado.TOCADO);
        jugador1.getTablero().getArrayTablero()[9][9].setEstado(Estado.HUNDIDO);
    System.out.println(jugador1.getTablero().getArrayTablero()[3][1].getEstado());
        System.out.println(jugador1.getTablero().getArrayTablero()[9][9].getEstado());

    jugador1.getTablero().imprimirTablero(jugador1.getTablero());
    }
}