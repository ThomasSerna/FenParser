/*
*
* Clase principal
*
*/

import gui.VentanaPrincipal;
import parse.FenParser;
import javax.swing.SwingUtilities;

public class FenParserMain {

    public static void main(String[] args) {
        // Iniciar la GUI en el hilo gráfico de Swing (buena práctica)
        SwingUtilities.invokeLater(() -> { //lambda para crear un entorno de ejecución con esa libreria
            VentanaPrincipal ventana = new VentanaPrincipal(); //crear ventana principal
            ventana.setVisible(true); //hacerla visible
        });

        /*
        FenParser prueba = new FenParser();
        prueba.testParse("r1bqkbnr/pppp1Bpp/2n5/4p3/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 0 3");
        */

    }

}
