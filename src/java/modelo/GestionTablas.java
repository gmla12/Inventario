/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.bean.BeanTablas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionTablas extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;
    private ArrayList<Object> GR_TABLAS;

    public ArrayList<Object> MostrarTablas(String tabla, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        
        try {
            GR_TABLAS = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            String query = "SHOW COLUMNS FROM " + tabla;

            System.out.println("***********************************************");
            System.out.println("*****       Buscar Columnas " + tabla + "  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanTablas bu;
            while (rs.next()) {
                bu = new BeanTablas();

                bu.setField(rs.getObject("field"));

                GR_TABLAS.add(bu);

            }

            st.close();
            
            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_TABLAS); //  y registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null){
                cn.rollback();
                cn.close();
            }
            
        } finally {

            return resultado;

        }

    }
    private Object field;

    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }
}
