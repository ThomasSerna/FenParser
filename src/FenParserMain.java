/*
*
* Clase principal
*
*/

import parse.FenParser;

public class FenParserMain {

    private static void testParse(String prueba){

        // Instanciar FenParser para pruebas internas de funcionamiento
        FenParser toParce = new FenParser();
        toParce.parser(prueba);

        // Ciclo para revisar arreglo que define el tablero
        for (int[] i : toParce.getTablero()){
            for (int k : i) {
                System.out.print(k);
                System.out.print(" ");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println("Color activo: " + toParce.getColorActivo());
        System.out.println("Disponibilidad enroque: " + toParce.getDisponibilidadEnroque());
        System.out.println("Casilla captura al paso: " + toParce.getCasillaCapturaAlPaso());
        System.out.println("Reloj medias jugadas: " + toParce.getRelojMediasJugadas());
        System.out.println("Número jugada completa: " + toParce.getNumeroJugadaCompleta());

    }


    public static void main(String[] args) {

        // Metodo de prueba para clase parse.FenParser
        testParse("r1bqkbnr/pppp1Bpp/2n5/4p3/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 0 3");

        /*
                                 --- Casos de prueba ---

        testParse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        testParse("rnbqk1nr/pppp1ppp/8/4p3/1b2P3/3P4/PPP2PPP/RNBQKBNR w KQkq - 1 3");
        testParse("rnbqkbnr/pp1ppppp/2p5/8/3PP3/8/PPP2PPP/RNBQKBNR b KQkq - 0 2");
        testParse("rnbqkbnr/pp1p1ppp/8/2pPp3/8/8/PPP1PPPP/RNBQKBNR w KQkq e6 0 3");
        testParse("8/8/5k2/8/8/8/1K6/6R1 w - - 0 45");

        */


    }

}
