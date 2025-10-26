/*
*
* Clase encargada de la logica principal del programa del programa
*
*/


package parse;

public class FenParser {

    private int[][] tablero;
    private String colorActivo;
    private String disponibilidadEnroque;
    private String casillaCapturaAlPaso;
    private int relojMediasJugadas;
    private int numeroJugadaCompleta;

    public void parser(String texto){


        System.out.println("parser");
        tablero = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    // Metodo que convierte el caracter de una pieza a su definido en numeros
    private int convPieza(char pieza) {
        switch (pieza) {
            // Piezas Blancas
            case 'P':
                return 1; // Peon
            case 'N':
                return 2; // Caballo
            case 'B':
                return 3; // Alfil
            case 'R':
                return 4; // Torre
            case 'Q':
                return 5; // Dama
            case 'K':
                return 6; // Rey

            // Piezas negras
            case 'p':
                return -1; // Peón
            case 'n':
                return -2; // Caballo
            case 'b':
                return -3; // Alfil
            case 'r':
                return -4; // Torre
            case 'q':
                return -5; // Dama
            case 'k':
                return -6; // Rey

            // Si el carácter no es una pieza, entonces es vacio
            default:
                return 0;
        }
    }

    /*
    *  Setters y getters
    */

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public String getColorActivo() {
        return colorActivo;
    }

    public void setColorActivo(String colorActivo) {
        this.colorActivo = colorActivo;
    }

    public String getDisponibilidadEnroque() {
        return disponibilidadEnroque;
    }

    public void setDisponibilidadEnroque(String disponibilidadEnroque) {
        this.disponibilidadEnroque = disponibilidadEnroque;
    }

    public String getCasillaCapturaAlPaso() {
        return casillaCapturaAlPaso;
    }

    public void setCasillaCapturaAlPaso(String casillaCapturaAlPaso) {
        this.casillaCapturaAlPaso = casillaCapturaAlPaso;
    }

    public int getRelojMediasJugadas() {
        return relojMediasJugadas;
    }

    public void setRelojMediasJugadas(int relojMediasJugadas) {
        this.relojMediasJugadas = relojMediasJugadas;
    }

    public int getNumeroJugadaCompleta() {
        return numeroJugadaCompleta;
    }

    public void setNumeroJugadaCompleta(int numeroJugadaCompleta) {
        this.numeroJugadaCompleta = numeroJugadaCompleta;
    }

}
