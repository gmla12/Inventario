/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.UsuariosOpForm;
import forms.UsuariosForm;
import forms.bean.BeanUsuarios;
import forms.InicioForm;
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
public class GestionUsuarios extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaUsuarios(UsuariosForm f, Boolean transac, Connection tCn) {

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

            String query = "insert into usuario     (login, password, idRol, idTipoDocumento, identificacion"
                    + ") "
                    + "values('"
                    + f.getLogin() + "' ,"
                    + "AES_ENCRYPT('" + f.getPassword() + "','mundoodnum') ,"
                    + f.getIdRol() + " , "
                    + f.getIdTipoDocumento() + " , "
                    + f.getIdentificacion()
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
    private ArrayList<Object> GR_USUARIO;
    private String descCargo = "";
    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    public ArrayList<Object> BuscarUsuarios(InicioForm fo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanUsuarios bu;
        bu = new BeanUsuarios();

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

            String query = "SELECT p.idUsuario, p.idRol, p.idTipoDocumento, p.identificacion ";
            query += "FROM usuario p ";
            query += "WHERE ";
            query += "p.login = '" + fo.getUsuario() + "' ";
            query += "AND p.password = AES_ENCRYPT('" + fo.getPassw() + "', 'mundoodnum')";

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_USUARIOS  *****");
            System.out.println("***********************************************");

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                bu = new BeanUsuarios();

                bu.setIdUsuario(rs.getObject("p.idUsuario"));
                bu.setIdRol(rs.getObject("p.idRol"));
                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(bu); // y registros consultados

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

    public ArrayList<Object> BuscarUsuarios(String login, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanUsuarios bu;
        bu = new BeanUsuarios();
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

            String query = "SELECT p.login ";
            query += "FROM usuario p ";
            query += "WHERE ";
            query += "p.login = '" + login + "' ";

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_USUARIOS  *****");
            System.out.println("***********************************************");

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                bu = new BeanUsuarios();

                bu.setLogin(rs.getObject("p.login"));
                String p =(String) bu.getLogin();
                if (p.equals(login)){
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

    public ArrayList<Object> MostrarUsuarios(UsuariosOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {
            GR_USUARIO = new ArrayList<Object>();

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

            String query = "SELECT p.idUsuario, p.login, p.idRol, p.idTipoDocumento, p.identificacion, r.nombre, t.nombre ";
            query += "FROM usuario p INNER JOIN roles r ON p.idRol = r.idRoles INNER JOIN tipoDocumento t ON p.idTipoDocumento = t.idTipoDocumento";
            String query2 = "";
            if (f.getbLogin().isEmpty() != true) {
                query2 = "p.login LIKE '%" + f.getbLogin() + "%'";
            }
            if (f.getbIdRol().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND p.idRol LIKE '%" + f.getbIdRol() + "%'";
                } else {
                    query2 = "p.idRol LIKE '%" + f.getbIdRol() + "%'";
                }
            }
            if (f.getbIdTipoDocumento().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND p.idTipoDocumento LIKE '%" + f.getbIdTipoDocumento() + "%'";
                } else {
                    query2 = "p.idTipoDocumento LIKE '%" + f.getbIdTipoDocumento() + "%'";
                }
            }
            if (f.getbIdentificacion().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND p.identificacion LIKE '%" + f.getbIdentificacion() + "%'";
                } else {
                    query2 = "p.identificacion LIKE '%" + f.getbIdentificacion() + "%'";
                }
            }
            if (query2.isEmpty() != true) {
                query += "WHERE " + query2;
            }

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_USUARIOS  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanUsuarios bu;
            while (rs.next()) {
                bu = new BeanUsuarios();

                bu.setIdUsuario(rs.getObject("p.idUsuario"));
                bu.setLogin(rs.getObject("p.login"));
                bu.setIdRol(rs.getObject("p.idRol"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));
                bu.setNombre(rs.getObject("r.nombre"));
                bu.setNombreDocumento(rs.getObject("t.nombre"));

                GR_USUARIO.add(bu);


            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_USUARIO); // y registros consultados

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

    public ArrayList<Object> ModificaUsuarios(UsuariosForm f, Boolean transac, Connection tCn) {

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

            String query = "UPDATE usuario SET login = '" + f.getLogin() + "'";
            if (f.getActPassword() != null) {
                if (f.getActPassword().equals("on")) {
                    query += "', password= AES_ENCRYPT('" + f.getPassword() + "', 'mundoodnum') ";
                }
            }
            query += ", idRol =" + f.getIdRol();
            query += ", idTipoDocumento =" + f.getIdTipoDocumento();
            query += ", identificacion=" + f.getIdentificacion();
            query += " WHERE idUsuario=" + f.getIdUsuario();


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

    public ArrayList<Object> EliminaUsuarios(UsuariosForm f, Boolean transac, Connection tCn) {

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

            String query = "DELETE FROM usuario ";
            query += "WHERE  idUsuario = " + f.getIdUsuario();


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

    public ArrayList<Object> MostrarUsuarioFormulario(String IdUsuario, Boolean transac, Connection tCn) {

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

            String query = "SELECT p.idUsuario, p.login, AES_DECRYPT(p.password,'mundoodnum') password, p.idRol, p.idTipoDocumento, p.identificacion ";
            query += "FROM usuario p ";
            query += "WHERE  p.idUsuario = " + IdUsuario;


            System.out.println("***********************************************");
            System.out.println("*****       MostrarUsuarioFormulario     *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanUsuarios bu;
            while (rs.next()) {
                bu = new BeanUsuarios();

                setIdUsuario(rs.getObject("p.idUsuario"));
                setLogin(rs.getObject("p.login"));
                setPassword(rs.getObject("password"));
                setIdRol(rs.getObject("p.idRol"));
                setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                setIdentificacion(rs.getObject("p.identificacion"));

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false

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

    public ArrayList<Object> commint(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.commit();
            resultado.add(false); //si no hubo un error asigna false

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

    public ArrayList<Object> autoCommint(boolean valor, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.setAutoCommit(valor);
            resultado.add(false); //si no hubo un error asigna false

        } catch (Exception e) {

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

        } catch (Exception e) {

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
    private Object idUsuario;
    private Object login;
    private Object password;
    private Object idTipoDocumento;
    private Object identificacion;
    private Object idRol;

    /**
     * @return the idUsuario
     */
    public Object getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuarios to set
     */
    public void setIdUsuario(Object idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
        this.login = login;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getIdRol() {
        return idRol;
    }

    public void setIdRol(Object idRol) {
        this.idRol = idRol;
    }

    public Object getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Object idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
    
    public Object getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Object identificacion) {
        this.identificacion = identificacion;
    }
}
