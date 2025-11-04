
package gui;
import javax.swing.SwingUtilities; //--> contiene métodos estáticos relacionados con la interfaz gráfica

/**
 * Clase principal del programa. Inicia la interfaz gráfica.
 */

public class AplicacionFen {
    public static void main(String[] args) {
        // Iniciar la GUI en el hilo gráfico de Swing (buena práctica)
        SwingUtilities.invokeLater(() -> { //lambda para crear un entorno de ejecución con esa libreria
            VentanaPrincipal ventana = new VentanaPrincipal(); //crear ventana principal
            ventana.setVisible(true); //hacerla visible
        });
    }
}
