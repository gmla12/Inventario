/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class ConeccionMySql {

    public ArrayList<Object> getConection() {

        ArrayList<Object> resultado = new ArrayList<Object>();
        Connection cn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "imporexport11");
            resultado.add(false); //si ocurrio una excepcion
            resultado.add(cn); //si no hubo excepcion adiciona la conexion para enviarla como resultado

        } catch (Exception e) {

            resultado.add(true); //si ocurrio una excepcion
            resultado.add(e); //si hubo excepcion adiciona el error para enviarlo y mostrarlo

        } finally {

            return resultado; //se retorna sin importar si hay excepcion

        }
    }
}
