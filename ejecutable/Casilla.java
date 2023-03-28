package com.example.hundir_los_tetraminos;

 /**
 * Clase Casilla
    * Esta Clase se encarga de los atributos de las casillas.
 */

public class Casilla {
    private int Fila;
    private int Columna;
    private Estado estado;
    private Tetramino aliasTetramino;

    Casilla(){
        this.estado=Estado.AGUA;
    }

    /**
     * Constructor de Casilla
     * @param fila Int de Fila
     * @param columna Int de Columna
     * @param estado Objeto de Estado derivado de la clase Estado
     */
    public Casilla(int fila, int columna, Estado estado) {
        this.Fila = fila;
        this.Columna = columna;
        this.estado = Estado.AGUA;
    }

    public Casilla(int fila, int columna) {
        Fila = fila;
        Columna = columna;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int fila) {
        Fila = fila;
    }

    public int getColumna() {
        return Columna;
    }

    public void setColumna(int columna) {
        Columna = columna;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {this.estado = estado;}

    public Tetramino getAliasTetramino() {
        return aliasTetramino;
    }

    public void setAliasTetramino(Tetramino aliasTetramino) {
        this.aliasTetramino = aliasTetramino;
    }

}

