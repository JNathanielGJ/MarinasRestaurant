package marinasrestaurant;

import Formulario.Usuarios;

/**
 *
 * @author TDFM
 */
public class MarinasRestaurant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        }); 
    }
}
