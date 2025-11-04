// VentanaPrincipal.java
package gui;
import javax.swing.*; //paquete principal de las interfaces graficas (el asterisco importa todas las clases dentro del paquete)
import java.awt.*;  //

/**
 * Ventana principal de la aplicación FEN.
 * Contiene el panel del tablero y el panel de controles.
 */
public class VentanaPrincipal extends JFrame { //Jframe es la ventana base, tiene boton de minimizar, cerrar y barrra de titulo

    private PanelTablero panelTablero;
    private PanelControl panelControles;

    public VentanaPrincipal() {
        setTitle("Visor de Notación FEN - Proyecto Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //“Cuando el usuario cierre esta ventana, termina todo el programa.”
        setSize(850, 600);
        setLocationRelativeTo(null); // Centrar la ventana

        setLayout(new BorderLayout(10, 10));

        // Crear y agregar paneles
        panelTablero = new PanelTablero(); // Asigna al campo de la clase
        panelControles = new PanelControl(panelTablero); // Asigna al campo de la clase

        add(panelTablero, BorderLayout.CENTER);
        add(panelControles, BorderLayout.EAST);
    }
}
