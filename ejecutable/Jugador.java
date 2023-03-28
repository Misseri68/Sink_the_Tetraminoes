package com.example.hundir_los_tetraminos;
import java.util.InputMismatchException;
import java.util.Scanner;

 /**
 * Clase Jugador
    * Esta Clase se encarga de los atributos y metodos especificos del Jugador.
 */

    public class Jugador {

    Scanner scanner = new Scanner(System.in);
    private String nombre;
    private Tablero tablero;
    private Tetramino[] listaTetraminos = new Tetramino[MAX_NUM_BARCOS];
    private int barcosRestantes;
    public final static int MAX_NUM_BARCOS = 5;

    //array que guarda casillas ya reveladas/seleccionadas (para que no se repitan). Tendrá como máximo 10x10 (100) casillas/posiciones (casillas que revela el JUGADOR sobre el tablero del ORDENADOR)..
    private Casilla casillaSeleccionada = new Casilla();

        /**
         * Constructor de Jugador
         * @param nombre , nombre del Jugador
         * @param tablero , tablero que pertenece al Jugador
         * @param listaTetraminos , array de objetos Tetramino
         * @param barcosRestantes , que indica la cantidad de barcos que le queda al jugador.
         */
    public Jugador(String nombre, Tablero tablero, Tetramino[] listaTetraminos, int barcosRestantes) {
        this();
        this.nombre = nombre;
        this.tablero = tablero;
        this.listaTetraminos = listaTetraminos;
        this.barcosRestantes = barcosRestantes;

    }

    public Jugador() {
        this.barcosRestantes=MAX_NUM_BARCOS;
        this.nombre= "";
        this.tablero = new Tablero();
        for (int i = 0; i < listaTetraminos.length; i++) {
            this.listaTetraminos[i]= new Tetramino();
        }

    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tetramino[] getListaTetraminos() {
        return listaTetraminos;
    }

    public void setListaTetraminos(Tetramino[] listaTetraminos) {
        this.listaTetraminos = listaTetraminos;
    }

    public int getBarcosRestantes() {
        return barcosRestantes;
    }

    public void setBarcosRestantes(int barcosRestantes) {
        this.barcosRestantes = barcosRestantes;
    }



    public int filaAInt(char selecFila){
        int intFila = 0;
        intFila = Character.toUpperCase(selecFila) - 65;

        return intFila;
    }

        /**
         * Comprobacion de que el input de jugador a la hora de colocar barcos no sea incorrecto.
         * @param tablero Objeto derivado de la Clase Tablero que en este caso sirve para determinar la posicion a colocar por medio de un Scanner
         * @return Error en caso de que el input del usuario este fuera de los limites.
         */

    public Casilla inputSeleccionarCasilla(Tablero tablero){
        int selectedFila;
        int selectedColumn = 0;
        //FILAS:
        do {
            System.out.println("Elige una Fila (A-J)");
            selectedFila = filaAInt(scanner.next().charAt(0));
            //validar fila
            if (selectedFila > 9 || selectedFila < 0) {
                System.err.println("Error, las coordenadas deben ser de A a J.");
            }
        }
            while (selectedFila>9 || selectedFila<0);
        //COLUMNAS:
        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.println("Elige una Columna (1-10)");
                selectedColumn = scanner.nextInt() - 1;
                if (selectedColumn>=0 && selectedColumn<10)  {
                    inputValido=true;
                } else if (selectedColumn > 9 || selectedColumn < 0) {
                    throw new IllegalArgumentException("Error, las coordenadas deben ser del 1 al 10.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error, Debes ingresar un numero entero.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        //Seleccionar casillas:
        this.casillaSeleccionada = tablero.getArrayTablero()[selectedFila][selectedColumn];
        return casillaSeleccionada;
    }

    public void setNombreScanner(){
        this.nombre=scanner.nextLine();
    }

        /**
         * Metodo de comprobacion de Ataque a casilla y Aplicacion de estados a la misma
         * @param casilla Objeto derivado de la clase Casilla
         */
    public void recibirAtaque(Casilla casilla){
        if(casilla.getEstado().equals(Estado.AGUA)) {
            System.out.println("Agua!");
        }
        else if (casilla.getEstado().equals(Estado.BARCO)){
            System.out.println("Tocado!");
            tablero.getArrayTablero()[casilla.getFila()][casilla.getColumna()].setEstado(Estado.TOCADO);
            tablero.getArrayTablero()[casilla.getFila()][casilla.getColumna()].getAliasTetramino().tocado();
            if (casilla.getAliasTetramino().getHP()<=0){
                casilla.setEstado(Estado.HUNDIDO);
                casilla.getAliasTetramino().esHundido();
                System.out.println("Hundido!");
                setBarcosRestantes(getBarcosRestantes()-1);
                System.out.println("Barcos restantes: " + getBarcosRestantes());
                for (int i = 0; i <casilla.getAliasTetramino().getPosicion().length; i++) {
                    tablero.getArrayTablero()[casilla.getAliasTetramino().getPosicion()[i].getFila()][casilla.getAliasTetramino().getPosicion()[i].getColumna()].setEstado(Estado.HUNDIDO);
                }
            }
        }
    }
}
