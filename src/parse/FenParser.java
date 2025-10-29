/*
*
* Clase encargada de la logica principal del programa del programa
*
*/

package parse;

public class FenParser {

    // Propiedades FEN
    private int[][] tablero = new int[8][8];
    private String colorActivo;
    private String disponibilidadEnroque;
    private String casillaCapturaAlPaso;
    private int relojMediasJugadas;
    private int numeroJugadaCompleta;

    // Variables para comprobaciones posteriores
    private String Piezas = "PNBRQKpnbrqk";
    private String coloresActivo = "bw";

    public void parser(String texto) throws FenParseException{

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
            throw new FenParseException("La notación FEN debe contener 6 campos separados por espacios");
        }

        /*
        *
        * Configuración tablero
        *
        */

        String[] tableroFen = notacionFen[0].split("/");

        // La notacion de las filas deben ser 8
        if (tableroFen.length != 8) {
            throw new FenParseException("La notación FEN debe tener 8 filas separadas por '/'");
        }

        // Se reinicia el tablero
        tablero = new int[8][8];

        // Analiza cada fila del tablero y le asigna al arreglo 8x8 su posición correspondiente
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
                    throw new FenParseException("Carácter inválido en FEN en fila " + (i + 1) + " columna aproximada " + (columna + 1) + ": '" + ficha + "'");
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
            throw new FenParseException("Color activo inválido: '" + notacionFen[1] + "'. Debe ser 'w' o 'b'.");
        }

        colorActivo = notacionFen[1];

        /*
         *
         * Disponibilidad Enroque
         *
         */

        if (!verificarEnroque(notacionFen[2])){
            throw new FenParseException("Disponibilidad de enroque inválida: '" + notacionFen[2] + "'. Debe ser '-', o combinación de 'KQkq' sin duplicados en orden.");
        }

        disponibilidadEnroque = notacionFen[2];

        /*
         *
         * Casilla captura al paso
         *
         */

        if (!verificarCasillaAlPaso(notacionFen[3])){
            throw new FenParseException("Casilla de captura al paso inválida: '" + notacionFen[3] + "'. Debe ser '-' o una casilla como 'e3'/'d6'.");
        }

        casillaCapturaAlPaso = notacionFen[3];

        /*
         *
         * Reloj medias jugadas
         *
         */

        if ((!esNumero(notacionFen[4])) || (Integer.parseInt(notacionFen[4]) < 0)){
            throw new FenParseException("Reloj de medias jugadas inválido: '" + notacionFen[4] + "'. Debe ser un entero >= 0.");
        }

        relojMediasJugadas = Integer.parseInt(notacionFen[4]);

        /*
         *
         * Numero de jugadas completas
         *
         */

        if ((!esNumero(notacionFen[5])) || (Integer.parseInt(notacionFen[5]) <= 0)){
            throw new FenParseException("Número de jugada completa inválido: '" + notacionFen[5] + "'. Debe ser un entero > 0.");
        }

        numeroJugadaCompleta = Integer.parseInt(notacionFen[5]);

    }

    // Metodo para confirmar si un string es un numero, ejm esNumero("2") == true
    private boolean esNumero(String s) {
        return s != null && !s.isEmpty() && s.chars().allMatch(Character::isDigit);
    }

    private boolean verificarEnroque(String s){
        if (s.equals("-")){
            return true;
        }
        if (s.length() > 4 || s.contains("-") || s.isEmpty()){
            return false;
        }

        String ordenCorrecto = "KQkq";
        int ultimoIndicePermitido = -1;

        // Verificacion de orden y los duplicados
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Buscamos la posicion del carácter actual en el string de orden correcto
            int indiceActual = ordenCorrecto.indexOf(c);

            if (indiceActual == -1) {
                return false;
            }

            if (s.indexOf(c) != i) {
                return false; // Es un duplicado
            }

            // Comprobamos si el índice de este carácter es menor que el del anterior.
            // ej. "qK", i=1, c='K'. indiceActual=0. ultimoIndicePermitido=3. 0 < 3 == false.
            if (indiceActual < ultimoIndicePermitido) {
                return false; // Error de orden
            }

            ultimoIndicePermitido = indiceActual;
        }

        return true;
    }

    private boolean verificarCasillaAlPaso(String s) {
        String cas1 = "abcdefgh";
        String cas2 = "36";
        String[] casilla = s.split("");

        if (s.equals("-")){
            return true;
        }

        if ((s.length() != 2) || (!cas1.contains(casilla[0])) || (!cas2.contains(casilla[1]))) {
            return false;
        }

        return true;
    }

    // Metodo que convierte el caracter de una pieza a su definido en numeros
    private int convPieza(char pieza) throws FenParseException{
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
                throw new FenParseException("Pieza inválida: '" + pieza + "'");
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
