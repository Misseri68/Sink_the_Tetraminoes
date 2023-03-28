package org.madrid.hundir_los_tetraminos;
import java.util.InputMismatchException;
import java.util.Scanner;

 /**
 * Clase Juego
    * Esta Clase se encarga de la logica del Juego asi como Sistemas como turnos o Vida.
 */

public class Juego {

    public static void main (String[]args) {
        juego();
    }
    public static Scanner scanner = new Scanner(System.in);
    public static Tetramino[] crearTetraminos() {
        Tetramino[] listaTetraminos = new Tetramino[Jugador.MAX_NUM_BARCOS];
        for (int i = 0; i < listaTetraminos.length; i++) {
            listaTetraminos[i] = new Tetramino();
        }
        return listaTetraminos;
    }

    /**
     * Constructor de Juego
     */
    public Juego(){

    }

    /**
     * Funcion que determina si la casilla a sido Seleccionada antes
     * @param casillaSeleccionada Objeto casillaSeleccionada Derivado desde la Clase Tablero
     * @param casillasReveladas Objeto casillaRevelada Derivado desde la Clase Tablero
     * @return seleccionadaBefore Boolean que dice si la casilla seleccionada está en el array casillasReveladas (para no repetir shot)
     */
    public static boolean haSidoSeleccionadaAntes(Casilla casillaSeleccionada, Casilla[] casillasReveladas){
            boolean seleccionadaBefore = false;
        for (int i = 0; i < casillasReveladas.length ; i++) {
            if (casillasReveladas[i].equals(casillaSeleccionada)){
                seleccionadaBefore=true;
            }
            if (seleccionadaBefore) break;
        }
        return seleccionadaBefore;
    }

    /**
     * @return int que devuelve un valor aleatorio entre 1 y 9
     */
    public static int numeroRandomInt(){
        double doubleRandom = Math.random()*10;
        int intRandom = (int) doubleRandom;
        return intRandom;
    }

    static Tablero tableroHumano = new Tablero();
    static Tablero tableroOrdenador = new Tablero();
    static Jugador humano = new Jugador("", tableroHumano, crearTetraminos(), Jugador.MAX_NUM_BARCOS);
    static Jugador ordenador = new Jugador("ordenador", tableroOrdenador, crearTetraminos(), Jugador.MAX_NUM_BARCOS);
    private static Casilla casillaSeleccionada = new Casilla();

    /**
     * Try catch que determina si los valores de Orientacion se estan saliendo de Los valores requeridos
     * @return Error en caso de que los valores de Orientacion esten Fuera de lo Establecido
     */
    public static int scannerOrientacion(){
        boolean inputValido = false;
        int orientacion =1;
        while (!inputValido) {
            try {
                orientacion= scanner.nextInt();
                if (orientacion>=1 && orientacion<=4)  {
                    inputValido=true;
                } else if (orientacion > 4 || orientacion < 1) {
                    throw new IllegalArgumentException("Error, la orientación debe ser entre 1 y 4.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error, debes ingresar un numero entero.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                scanner.nextLine();

            }
        }
        return orientacion;
    }

    /**
     * Metodo que Inicializa el Tablero e indica los tetraminos del jugador y los Inputs a recibir.
     */
    public static void inicializarTableroHumano(){
        int orientacion = 0;
        for (int i = 0; i < Jugador.MAX_NUM_BARCOS; i++) {
            //Informa al humano de que tetraminos tiene
            if (i == 0) System.out.print("Tus tetraminos son: ");
            System.out.print(humano.getListaTetraminos()[i].getTipoTetramino() + ", ");
        }
        System.out.println();
        //Bucle for que recorrerá la lista de tetraminos del jugador HUMANO. Establecerá los tetraminos por input.
        for (int i = 0; i < Jugador.MAX_NUM_BARCOS; i++) {
            tableroHumano.imprimirTablero(tableroHumano);
            System.out.println("Selecciona dónde quieres colocar la pieza " + humano.getListaTetraminos()[i].getTipoTetramino());
            casillaSeleccionada = humano.inputSeleccionarCasilla(tableroHumano);
            System.out.println("Introduce del 1 al 4 la orientación.");
            orientacion=scannerOrientacion();
            //Comprobación de que se pueda poner la ficha en la casilla (Si ha hay un barco no podrá, y si coge el tamaño de la ficha y se sale del array tablero tampoco.)
            if (!humano.getTablero().comprobarCasillaInicializar(humano.getListaTetraminos()[i], humano.getListaTetraminos()[i].getTipoTetramino(), orientacion, casillaSeleccionada)) {
                System.err.println("Pieza mal colocada, prueba de nuevo");
                i--;
            } else {
                char filaChar = (char) (casillaSeleccionada.getFila() + 65);
                humano.getTablero().inicializarTablero(humano.getListaTetraminos()[i], humano.getListaTetraminos()[i].getTipoTetramino(), humano.getTablero(), orientacion, casillaSeleccionada);
                System.out.println("Has colocado la ficha " + humano.getListaTetraminos()[i].getTipoTetramino() + " en la casilla " + filaChar + (casillaSeleccionada.getColumna() + 1));
            }
        }
    }

    /**
     * Iniciclizacion del Tablero del Ordenador/Oponente
     */
    public static void inicializarTableroOrdenador(){
        int orientacion;
        //Bucle for que recorrerá la lista de tetraminos del jugador ORDENADOR. Establecerá los tetraminos de forma aleatoria.
        for (int i = 0; i < Jugador.MAX_NUM_BARCOS; i++) {
            casillaSeleccionada = ordenador.getTablero().getArrayTablero()[numeroRandomInt()][numeroRandomInt()];
            double randomOrientacion = Math.random() * 4;
            int randomIntorientacion = (int) (randomOrientacion);
            orientacion = randomIntorientacion + 1;
            if (!ordenador.getTablero().comprobarCasillaInicializar(ordenador.getListaTetraminos()[i], ordenador.getListaTetraminos()[i].getTipoTetramino(), orientacion, casillaSeleccionada)) {
                i--;
            } else {
                ordenador.getTablero().inicializarTablero(ordenador.getListaTetraminos()[i], ordenador.getListaTetraminos()[i].getTipoTetramino(), ordenador.getTablero(), orientacion, casillaSeleccionada);
            }
        }
    }

    /**
     * Funcion que registra los ataques del jugador al ordenador.
     */
    public static void humanoAtacaOrdenador(){
    //Se comprobará si esa casilla ya se ha seleccionado antes con un array de CasillasReveladas (en función del tablero)
        do {
            //Se imprime el tableroOrdenador para poder ver las casillas por elegir.
            tableroOrdenador.imprimirTableroEnemigo(tableroOrdenador);
        //El humano elegirá una casilla del tableroOrdenador.
            System.out.println("Elige la casilla a la que quieres disparar.");
            casillaSeleccionada = humano.inputSeleccionarCasilla(tableroOrdenador);
        //Si la casillaSeleccionada coincide con las casillasReveladas del tableroOrdenador se volverá a pedir una casillaSeleccionada nueva (continuando el bucle while).
            if ((haSidoSeleccionadaAntes(casillaSeleccionada, tableroOrdenador.getCasillasReveladas()))) {
                System.err.println("Ya has seleccionado esa casilla antes, elige otra!");
            }
        }while (haSidoSeleccionadaAntes(casillaSeleccionada, tableroOrdenador.getCasillasReveladas()));
    //Si no coincide con las casillasReveladas del tableroOrdenador, (es decir, si esa casilla no había sido seleccionada aneriormente) buscará con un array la primera posición en la que no haya ninguna casilla revelada guardada del array casillasReveladas()
    //El valor de la fila (o columna) -1 (que es establecido al inicialziar el array) implica que la casilla de esa posición está libre para que se establezca la seleccionada.
        for (int i = 0; i < tableroOrdenador.getCasillasReveladas().length; i++) {
            if (tableroOrdenador.getCasillasReveladas()[i].getFila() == -1) {
                //Mete la casillaSeleccionada en la posición del array en la que la fila == 1.
                tableroOrdenador.getCasillasReveladas()[i] = casillaSeleccionada;
                break;

            }
        }
    //Al guardar la Casilla revelada, se procede a atacar al jugador ORDENADOR.
        System.out.println("Disparo a "+ casillaSeleccionada.getFila() +casillaSeleccionada.getColumna());
        ordenador.recibirAtaque(casillaSeleccionada);
    }

    /**
     * Funcion que muestra ataques del Ordenador al Jugador
     */
    public static void ordenadorAtacaHumano(){
    //Se comprobará si la casilla ya se ha seleccionado antes con el array de CasillasReveladas[]
        do {
            casillaSeleccionada = humano.getTablero().getArrayTablero()[numeroRandomInt()][numeroRandomInt()];
    //Si la casilla del tablero HUMANO ya se había seleccionado antes volverá a pedir que se seleccione una nueva.
            if ((haSidoSeleccionadaAntes(casillaSeleccionada, tableroHumano.getCasillasReveladas()))) {
                System.out.println("Ordenador, esa casilla ya se había seleccionado antes...");
            }
        } while (haSidoSeleccionadaAntes(casillaSeleccionada, tableroHumano.getCasillasReveladas()));
    //Si la casillaSeleccionada no ha sido seleccionada anteriormente se guardará en el array casillasReveladas del tablero HUMANO.
        for (int i = 0; i < tableroHumano.getCasillasReveladas().length; i++){
            if (tableroHumano.getCasillasReveladas()[i].getFila() == -1) {
                tableroHumano.getCasillasReveladas()[i] = casillaSeleccionada;
                break;
            }
        }
    //El ordenador disparará a la casillaSeleccionada.
        System.out.println("Disparo a "+ casillaSeleccionada.getFila() +casillaSeleccionada.getColumna());
        humano.recibirAtaque(casillaSeleccionada);
        tableroHumano.imprimirTablero(tableroHumano);
    }

    /**
     * Metodo que compone las palabras que se mostraran en el Juego asi como el sistema de vida.
     */
    public static void juego() {
        int orientacion;
    //Definimos el nombre del jugador humano
        System.out.println("Introduce tu nombre");
        humano.setNombreScanner();
    //Inicializamos el tablero del HUMANO (selección de casillas, orientación, y posicionamiento de los tetraminos.)
        System.out.println("Coloca tus barcos.");
        inicializarTableroHumano();
    //Inicializamos el tablero del ORDENADOR (selección de casillas, orientación, y posicionamiento de los tetraminos, aleatorio.)
        System.out.println("Ahora el ordenador colocará sus fichas...");
        inicializarTableroOrdenador();
    //Aquì concluye la creación de los tableros con sus tetraminos.

    //El juego no acabará hasta que uno de los jugadores se quede sin barcos.
        while (humano.getBarcosRestantes() >= 1 && ordenador.getBarcosRestantes() >= 1) {
    //Atacar HUMANO (hacia el ordenador)

         humanoAtacaOrdenador();

    //Si el ordenador ha perdido el ultimo barco, no pedirá al ordenador que ataque otra casilla, ya que si no pongo esta línea podría darse el caso en el que los dos se quedan sin barcos a la vez y no hay un ganador claro.

        if (ordenador.getBarcosRestantes() == 0) break;

    //Atacar ORDENADOR (hacia el humano)
        System.out.println("El ordenador está eligiendo una casilla que disparar...");
        ordenadorAtacaHumano();
    //Fin del while que repite los ataques hasta que se acaben los barcos.
    }
        if (ordenador.getBarcosRestantes() == 0) {
            System.out.println("Enhorabuena " + humano.getNombre() + ", has ganado <3!!!");
        } else {
            System.out.println("Qué pena, has perdido. :)");
        }
    }


}