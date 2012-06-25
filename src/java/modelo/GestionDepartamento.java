/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.DepartamentoForm;
import forms.DepartamentoOpForm;
import forms.bean.BeanDepartamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionDepartamento extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaDepartamento(DepartamentoForm f, Boolean transac, Connection tCn) {

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

            String query = "insert into departamentos     (idDepartamento, idPais, nombre"
                    + ") "
                    + "values('"
                    + f.getIdDepartamento() + "', '"
                    + f.getIdPais() + "', '"
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
    private ArrayList<Object> GR_DEPARTAMENTO;

    public ArrayList<Object> MostrarDepartamento(String id, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            GR_DEPARTAMENTO = new ArrayList<Object>();

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

            String query = "SELECT p.idDepartamento, p.idPais, p.nombre ";
            query += "FROM departamentos p WHERE p.idPais = '" + id + "'";

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_DEPARTAMENTO  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanDepartamento bu;
            while (rs.next()) {
                bu = new BeanDepartamento();

                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setIdPais(rs.getObject("p.idPais"));
                bu.setNombre(rs.getObject("p.nombre"));

                GR_DEPARTAMENTO.add(bu);


            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_DEPARTAMENTO); // y registros consultados

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

    public ArrayList<Object> MostrarDepartamentoOP(DepartamentoOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            GR_DEPARTAMENTO = new ArrayList<Object>();

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

            String query = "SELECT p.idDepartamento, p.idPais, p.nombre, r.nombre ";
            query += "FROM departamentos p INNER JOIN paises r ON p.idPais = r.idPais";
            String query2 = "";
            if (f.getbIdDepartamento().isEmpty() != true) {
                query2 = "p.idDepartamento LIKE '%" + f.getbIdDepartamento() + "%'";
            }
            if (f.getbIdPais().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND p.idPais LIKE '%" + f.getbIdPais() + "%'";
                } else {
                    query2 = "p.idPais LIKE '%" + f.getbIdPais() + "%'";
                }
            }
            if (f.getbNombre().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND p.nombre LIKE '%" + f.getbNombre() + "%'";
                } else {
                    query2 = "p.nombre LIKE '%" + f.getbNombre() + "%'";
                }
            }
            if (query2.isEmpty() != true) {
                query += " WHERE " + query2;
            }

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_DEPARTAMENTO  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanDepartamento bu;
            while (rs.next()) {

                bu = new BeanDepartamento();

                bu.setIdPais(rs.getObject("p.idPais"));
                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setNombre(rs.getObject("p.nombre"));
                bu.setNombrePais(rs.getObject("r.nombre"));

                GR_DEPARTAMENTO.add(bu);

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_DEPARTAMENTO); // y registros consultados

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

    public ArrayList<Object> ModificaDepartamento(DepartamentoForm f, Boolean transac, Connection tCn) {

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

            String query = "UPDATE departamentos SET nombre = '" + f.getNombre() + "'";
            query += " WHERE idDepartamento = '" + f.getIdDepartamento() + "' AND idPais = '" + f.getIdPais() + "'";


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

    public ArrayList<Object> EliminaDepartamento(DepartamentoForm f, Boolean transac, Connection tCn) {

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

            String query = "DELETE FROM departamentos ";
            query += "WHERE idDepartamento = '" + f.getIdDepartamento() + "' AND idPais = '" + f.getIdPais() + "'";


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

    public ArrayList<Object> BuscarDepartamento(String idDepartamento, String idPais, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanDepartamento bu;
        bu = new BeanDepartamento();
        boolean encontro = false;

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

            String query = "SELECT p.idDepartamento, p.idPais ";
            query += "FROM departamentos p ";
            query += "WHERE ";
            query += "p.idDepartamento = '" + idDepartamento + "' ";
            query += "AND p.idPais = '" + idPais + "' ";

            System.out.println("***********************************************");
            System.out.println("*****       Buscar idDepartamento  *****");
            System.out.println("***********************************************");

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                bu = new BeanDepartamento();

                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setIdPais(rs.getObject("p.idPais"));
                String p =(String) bu.getIdDepartamento();
                String p2 =(String) bu.getIdPais();
                if (p.equals(idDepartamento) && p2.equals(idPais)){
                    encontro = true;
                }
                

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(encontro); // y registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }
    
    public ArrayList<Object> MostrarDepartamentoFormulario(String IdDepartamento, String IdPais, Boolean transac, Connection tCn) {

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

            String query = "SELECT p.idDepartamento, p.idPais, p.nombre ";
            query += "FROM departamentos p ";
            query += "WHERE p.idDepartamento = '" + IdDepartamento + "' AND p.idPais = '" + IdPais + "'";


            System.out.println("***********************************************");
            System.out.println("*****       MostrarDepartamentoFormulario     *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanDepartamento bu;
            while (rs.next()) {
                bu = new BeanDepartamento();

                setIdDepartamento(rs.getObject("p.idDepartamento"));
                setIdPais(rs.getObject("p.idPais"));
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

    private Object idDepartamento;
    private Object idPais;
    private Object nombre;

    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Object getIdPais() {
        return idPais;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }
}