/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tallerconectar;

import Vista.Principal;
import co.edu.unicartagena.persistencia.BD;

/**
 *
 * @author Estudiante
 */
public class TallerConectar {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        
        // Probamos la conexi�n a la base de datos
        new Principal().setVisible(true);
    }
}