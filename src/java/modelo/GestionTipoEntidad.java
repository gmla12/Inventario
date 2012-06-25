/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.TipoEntidadOpForm;
import forms.TipoEntidadForm;
import forms.bean.BeanTipoEntidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionTipoEntidad extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaTipoEntidad(TipoEntidadForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

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

            String query = "insert into tipoEntidad     (nombre"
                    + ") "
                    + "values('"
                    + f.getNombre() + "'"
                    + ")";

            System.out.println(query);
            st = cn.createStatement();

            st.execute(query);
            mod = st.getUpdateCount();
            
            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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
    private ArrayList<Object> GR_TIPOENTIDAD;

    public ArrayList<Object> MostrarTipoEntidad(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            GR_TIPOENTIDAD = new ArrayList<Object>();

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

            String query = "SELECT p.idTipoEntidad, p.nombre ";
            query += "FROM tipoEntidad p ";

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_TIPOENTIDAD  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanTipoEntidad bu;
            while (rs.next()) {
                bu = new BeanTipoEntidad();

                bu.setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));
                bu.setNombre(rs.getObject("p.nombre"));

                GR_TIPOENTIDAD.add(bu);


            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_TIPOENTIDAD); // y registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> MostrarTipoEntidadOP(TipoEntidadOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            GR_TIPOENTIDAD = new ArrayList<Object>();

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

            String query = "SELECT p.idTipoEntidad, p.nombre ";
            query += "FROM tipoEntidad p ";
            String query2 = "";
            if (f.getbNombre().isEmpty() != true) {
                query2 = "p.nombre LIKE '%" + f.getbNombre() + "%'";
            }
            if (query2.isEmpty() != true) {
                query += "WHERE " + query2;
            }

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_TIPOENTIDAD  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanTipoEntidad bu;
            while (rs.next()) {

                bu = new BeanTipoEntidad();

                bu.setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));
                bu.setNombre(rs.getObject("p.nombre"));

                GR_TIPOENTIDAD.add(bu);

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_TIPOENTIDAD); // y registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> ModificaTipoEntidad(TipoEntidadForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

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

            String query = "UPDATE tipoEntidad SET nombre = '" + f.getNombre() + "'";
            query += " WHERE idTipoEntidad=" + f.getIdTipoEntidad();


            System.out.println(query);
            st = cn.createStatement();

            st.executeUpdate(query);
            mod = st.getUpdateCount();

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> EliminaTipoEntidad(TipoEntidadForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

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

            String query = "DELETE FROM tipoEntidad ";
            query += "WHERE  idTipoEntidad = " + f.getIdTipoEntidad();


            System.out.println(query);
            st = cn.createStatement();

            st.executeUpdate(query);
            mod = st.getUpdateCount();

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> MostrarTipoEntidadFormulario(String IdTipoEntidad, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

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

            String query = "SELECT p.idTipoEntidad, p.nombre ";
            query += "FROM tipoEntidad p ";
            query += "WHERE  p.idTipoEntidad = " + IdTipoEntidad;


            System.out.println("***********************************************");
            System.out.println("*****       MostrarTipoEntidadFormulario     *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanTipoEntidad bu;
            while (rs.next()) {
                bu = new BeanTipoEntidad();

                setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));
                setNombre(rs.getObject("p.nombre"));

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

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

    public ArrayList<Object> commint(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.commit();
            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

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

    public ArrayList<Object> autoCommint(boolean valor, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.setAutoCommit(valor);
            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> rollback(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.rollback();
            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> ObtenerConexion() {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            ArrayList<Object> resultad = new ArrayList<Object>();
            resultad = (ArrayList) getConection();

            if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                cn = (Connection) resultad.get(1);
                resultado.add(false); //si no hubo un error asigna false
                resultado.add(cn); // y se envia la nueva conexion

            } else { //si hubo error al obtener la conexion retorna el error para visualizar

                resultado.add(true);
                resultado.add(resultad.get(1));
                return resultado;

            }

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }

//    private ArrayList<Object> GR_USUARIOS2;
//
//    public ArrayList<Object> MostrarUsuarios2(String aux, String aux2) {
//        try {
//            GR_USUARIOS2 = new ArrayList<Object>();
//            cn = getConection();
//
//            String query = "SELECT p.idUsuarios, p.tipoDocumentacion, p.documento, p.nombre1, p.nombre2, p.apellido1, ";
//            query += "p.apellido2, p.foto ";
//            query += "FROM usuarios p, cargo c ";
//            query += "WHERE p.id_cargo = c.id_cargo ";
//            if (aux.equals("nombre")) {
//                query += " AND p.nombres = '" + aux2 + "'";
//            } else if (aux.equals("cargo")) {
//                query += " AND p.id_cargo='" + aux2 + "'";
//            }
//
//            System.out.println("***********************************************");
//            System.out.println("*****       Cargando grilla  GR_USUARIOS  *****");
//            System.out.println("***********************************************");
//
//            System.out.println(query);
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            BeanUsuarios bu;
//            while (rs.next()) {
//                bu = new BeanUsuarios();
//
//                bu.setIdUsuarios(rs.getObject("p.idUsuarios"));
//                bu.setTipoDocumentacion(rs.getObject("p.tipoDocumentacion"));
//                bu.setDocumento(rs.getObject("p.documento"));
//                bu.setNombre1(rs.getObject("p.nombre1"));
//                bu.setNombre2(rs.getObject("p.nombre2"));
//                bu.setApellido1(rs.getObject("p.apellido1"));
//                bu.setApellido2(rs.getObject("p.apellido2"));
//                bu.setFoto(rs.getObject("p.foto"));
//
//                GR_USUARIOS2.add(bu);
//
//            }
//
//            st.close();
//            cn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//        return GR_USUARIOS2;
//    }
//}
    private Object idTipoEntidad;
    private Object nombre;

    public Object getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(Object idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }
}
