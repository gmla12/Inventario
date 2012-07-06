/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.bean.BeanTipoDocumentoAut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionTipoDocumentoAut extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;
    private ArrayList<Object> GR_TIPODOCUMENTOANT;

    public ArrayList<Object> IngresaTipoDocumentoAut(int idTipoDocumento, String campo, Boolean habilitar, Boolean obligatorio, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psInsertar = null;

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

            psInsertar = cn.prepareStatement("insert into tipoDocumentoAut (idTipoDocumento, campoEntidad, habilitar, obligatorio) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setInt(1, idTipoDocumento);
            psInsertar.setString(2, campo);
            psInsertar.setBoolean(3, habilitar);
            psInsertar.setBoolean(4, obligatorio);
            psInsertar.executeUpdate(); // Se ejecuta la inserción.

            mod = psInsertar.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

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

    public ArrayList<Object> MostrarTipoDocumentoAut(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_TIPODOCUMENTOANT = new ArrayList<Object>();

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

            psSelectConClave = cn.prepareStatement("SELECT p.idTipoDocumento, p.campoEntidad, p.habilitar, p.obligatorio FROM tipoDocumentoAut p");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanTipoDocumentoAut bu;
            while (rs.next()) {
                bu = new BeanTipoDocumentoAut();

                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setCampo(rs.getObject("p.campoEntidad"));
                bu.setHabilitar(rs.getObject("p.habilitar"));
                bu.setObligatorio(rs.getObject("p.obligatorio"));

                GR_TIPODOCUMENTOANT.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_TIPODOCUMENTOANT); // y registros consultados

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

    public ArrayList<Object> MostrarTipoDocumentoAut(int idTipoDocumento, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_TIPODOCUMENTOANT = new ArrayList<Object>();

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

            psSelectConClave = cn.prepareStatement("SELECT p.idTipoDocumento, p.campoEntidad, p.habilitar, p.obligatorio FROM tipoDocumentoAut p WHERE p.idTipoDocumento = ?");
            psSelectConClave.setInt(1, idTipoDocumento);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanTipoDocumentoAut bu;
            while (rs.next()) {
                bu = new BeanTipoDocumentoAut();

                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setCampo(rs.getObject("p.campoEntidad"));
                bu.setHabilitar(rs.getObject("p.habilitar"));
                bu.setObligatorio(rs.getObject("p.obligatorio"));

                GR_TIPODOCUMENTOANT.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_TIPODOCUMENTOANT); // y registros consultados

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

    public ArrayList<Object> MostrarTipoDocumentoAnt(int idTipoDocumento, String campo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanTipoDocumentoAut bu = new BeanTipoDocumentoAut();
        PreparedStatement psSelectConClave = null;

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

            psSelectConClave = cn.prepareStatement("SELECT p.idTipoDocumento, p.campoEntidad, p.habilitar, p.obligatorio FROM tipoDocumentoAut p WHERE p.idTipoDocumento = ? AND p.campoEntidad = ?");
            psSelectConClave.setInt(1, idTipoDocumento);
            psSelectConClave.setString(2, campo);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {
                bu = new BeanTipoDocumentoAut();

                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setCampo(rs.getObject("p.campoEntidad"));
                bu.setHabilitar(rs.getObject("p.habilitar"));
                bu.setObligatorio(rs.getObject("p.obligatorio"));

            }

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(bu); // y registro consultado

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

    public ArrayList<Object> ModificaTipoDocumentoAnt(int idTipoDocumento, String campo, Boolean habilitar, Boolean obligatorio, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psUpdate = null;

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

            String query = "UPDATE tipoDocumentoAut SET habilitar = ?";
            query += ", obligatorio = ?";
            query += " WHERE idTipoDocumento = ? AND campoEntidad = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setBoolean(1, habilitar);
            psUpdate.setBoolean(2, obligatorio);
            psUpdate.setInt(3, idTipoDocumento);
            psUpdate.setString(4, campo);
            psUpdate.executeUpdate();

            mod = psUpdate.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); //  y el numero de registros consultados

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

    public ArrayList<Object> EliminaTipoDocumentoAut(int idTipoDocumento, String campo, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psDelete = null;

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

            psDelete = cn.prepareStatement("DELETE FROM tipoDocumentoAut WHERE  idTipoDocumento = ? AND campoEntidad = ?");
            psDelete.setInt(1, idTipoDocumento);
            psDelete.setString(2, campo);
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); //  y el numero de registros consultados

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

    public ArrayList<Object> EliminaTipoDocumentoAut(int idTipoDocumento, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psDelete = null;

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

            psDelete = cn.prepareStatement("DELETE FROM tipoDocumentoAut WHERE  idTipoDocumento = ?");
            psDelete.setInt(1, idTipoDocumento);
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion
                cn.close();
            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); //  y el numero de registros consultados

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

    public ArrayList<Object> commint(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.commit();
            resultado.add(false); //si no hubo un error asigna false

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
//    public void MostrarTipoDocumentoFormulario(String IdTipoDocumento) {
//        try {
//            cn = getConection();
//
//            String query = "SELECT p.idTipoDocumento, p.nombre ";
//            query += "FROM tipoDocumento p ";
//            query += "WHERE  p.idTipoDocumento = " + IdTipoDocumento;
//
//
//            System.out.println("***********************************************");
//            System.out.println("*****       MostrarTipoDocumentoFormulario     *****");
//            System.out.println("***********************************************");
//
//            System.out.println(query);
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            BeanTipoDocumentoAnt bu;
//            while (rs.next()) {
//                bu = new BeanTipoDocumentoAnt();
//
//                setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
//                setNombre(rs.getObject("p.nombre"));
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
//
//    }
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
    private Object idTipoDocumento;
    private Object campo;
    private Object habilitar;
    private Object obligatorio;

    public Object getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Object idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Object getCampo() {
        return campo;
    }

    public void setCampo(Object campo) {
        this.campo = campo;
    }

    public Object getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Object habilitar) {
        this.habilitar = habilitar;
    }

    public Object getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Object obligatorio) {
        this.obligatorio = obligatorio;
    }
}
