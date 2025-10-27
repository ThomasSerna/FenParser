/*
*
* Clase encargada de la logica principal del programa del programa
*
*/

package parse;

import com.sun.source.tree.ReturnTree;

public class FenParser {

    // Propiedades FEN
    private int[][] tablero = new int[8][8];
    private String colorActivo;
    private String disponibilidadEnroque;
    private String casillaCapturaAlPaso;
    private int relojMediasJugadas;
    private int numeroJugadaCompleta;

    private String Piezas = "PNBRQKpnbrqk";
    private String coloresActivo = "bw";

    public void parser(String texto){

        String[] notacionFen = texto.split(" ");

        /*
         * [0] tablero
         * [1] color activo
         * [2] disponibilidad enroque
         * [3] casilla captura al paso
         * [4] reloj medias jugadas
         * [5] numero jugada completa
         */

        // La notacion tiene 6 campos
        if (notacionFen.length != 6){
            // Excepcion -----------------------------------------------------------------------------------------------
        }

        /*
        *
        * Configuración tablero
        *
        */

        String[] tableroFen = notacionFen[0].split("/");

        // La notacion de las filas deben ser 8
        if (tableroFen.length == 8) {
            // Excepcion -----------------------------------------------------------------------------------------------
        }

        // Se reinicia el tablero
        tablero = new int[8][8];

        // Analiza cada fila del tablero y lo asigna al arreglo 8x8 su posición correspondiente
        for (int i = 0; i < tableroFen.length; i++) {
            String[] fichaTablero = tableroFen[i].split("");
            int columna = 0;

            // Da el valor a cada casilla
            for (int j = 0; j < fichaTablero.length; j++) {
                String ficha = fichaTablero[j];

                // Confirma si es numero o ficha
                if (esNumero(ficha)) {
                    columna += Integer.parseInt(ficha);
                } else if (Piezas.contains(ficha)) {
                    tablero[i][columna] = convPieza(ficha.charAt(0));
                    columna++;
                } else {
                    // Excepcion ---------------------------------------------------------------------------------------
                }

                if (columna >= 8) {
                    break;
                }
            }
        }

        /*
         *
         * Configuracion color activo
         *
         */

        if ((!coloresActivo.contains(notacionFen[1])) || (notacionFen[1].length() != 1)){
            // Excepción -----------------------------------------------------------------------------------------------
        }

        colorActivo = notacionFen[1];

        /*
         *
         * Disponibilidad Enroque
         *
         */

        if (!verificarEnroque(notacionFen[2])){
            // Excepción -----------------------------------------------------------------------------------------------
        }

        disponibilidadEnroque = notacionFen[2];

        /*
         *
         * Casilla captura al paso
         *
         */

        if (true){
            // Excepción -----------------------------------------------------------------------------------------------
        }

        casillaCapturaAlPaso = notacionFen[3];

        /*
         *
         * Reloj medias jugadas
         *
         */

        if (true){
            // Excepción -----------------------------------------------------------------------------------------------
        }

        relojMediasJugadas = Integer.parseInt(notacionFen[4]);

        /*
         *
         * Numero de jugadas completas
         *
         */

        if (true){
            // Excepción -----------------------------------------------------------------------------------------------
        }

        numeroJugadaCompleta = Integer.parseInt(notacionFen[5]);

    }

    // Metodo para confirmar si un string es un numero, ejm esNumero("2") == true
    private boolean esNumero(String s) {
        return s != null && !s.isEmpty() && s.chars().allMatch(Character::isDigit);
    }

    private boolean verificarEnroque(String s){
        String enroque = "KQkq";

        if (s.equals("-")){
            return true;
        }
        if (s.length() > 4 || s.contains("-") || s.isEmpty()){
            return false;
        }

        String[] nLetras = s.split("");
        String[] duplicados = new String[s.length()];

        for (int i = 0; i < nLetras.length; i++){
            if (!enroque.contains(nLetras[i])){
                return false;
            }

        }

        return true;
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
                // Excepcion -------------------------------------------------------------------------------------------
                return 0;
        }
    }

    /*
    *
    *  Setters y getters
    *
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
