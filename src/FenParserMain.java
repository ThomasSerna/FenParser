/*
*
* Clase principal
*
*/

import parse.FenParseException;
import parse.FenParser;

public class FenParserMain {

    private static void testParse(String prueba){

        // Instanciar FenParser para pruebas internas de funcionamiento
        FenParser toParse = new FenParser();
        try {
            toParse.parser(prueba);
        } catch (FenParseException e) {
            System.out.println("Error: " + e);
        }
        // Ciclo para revisar arreglo que define el tablero
        for (int[] i : toParse.getTablero()){
            for (int k : i) {
                System.out.print(k);
                System.out.print(" ");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println("Color activo: " + toParse.getColorActivo());
        System.out.println("Disponibilidad enroque: " + toParse.getDisponibilidadEnroque());
        System.out.println("Casilla captura al paso: " + toParse.getCasillaCapturaAlPaso());
        System.out.println("Reloj medias jugadas: " + toParse.getRelojMediasJugadas());
        System.out.println("Número jugada completa: " + toParse.getNumeroJugadaCompleta());

    }


    public static void main(String[] args) {

        // Metodo de prueba para clase parse.FenParser
        testParse("r1bqkbnr/pppp1Bpp/2n5/4p3/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 0 3");

    }

}
