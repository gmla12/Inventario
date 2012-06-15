/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.EntidadOpForm;
import forms.EntidadForm;
import forms.bean.BeanEntidad;
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
public class GestionEntidad extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaEntidad(EntidadForm f, Boolean transac, Connection tCn) {

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

            String query = "insert into entidad     (primerNombre, segundoNombre, primerApellido, segundoApellido, idTipoDocumento, identificacion, razonSocial"
                    + ") "
                    + "values('"
                    + f.getPrimerNombre() + "' ,'"
                    + f.getSegundoNombre() + "' ,'"
                    + f.getPrimerApellido() + "' ,'"
                    + f.getSegundoApellido() + "' ,"
                    + f.getIdTipoDocumento() + " , "
                    + f.getIdentificacion() + ", '"
                    + f.getRazonSocial() + "', "
                    + f.getIdPais() + ", "
                    + f.getIdMunicipio() + ", '"
                    + f.getDireccion() + "', '"
                    + f.getTelefono() + "', '"
                    + f.getEmail() + "', "
                    + f.getIdTipoEntidad()
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
    private ArrayList<Object> GR_ENTIDAD;

    public ArrayList<Object> BuscarEntidad(EntidadForm fo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        
        BeanEntidad bu;
        bu = new BeanEntidad();

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

            String query = "SELECT p.idEntidad, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.idTipoDocumento, p.identificacion, p.razonSocial, p.idPais, p.idMunicipio, p.direccion, p.telefono, p.email, p.idTipoEntidad ";
            query += "FROM entidad p ";
            query += "WHERE ";
            query += "p.identificacion = " + fo.getIdentificacion();

            System.out.println("***********************************************");
            System.out.println("*****       Buscar Entidad  *****");
            System.out.println("***********************************************");

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setPrimerNombre(rs.getObject("p.primerNombre"));
                bu.setSegundoNombre(rs.getObject("p.segundoNombre"));
                bu.setPrimerApellido(rs.getObject("p.primerApellido"));
                bu.setSegundoApellido(rs.getObject("p.segundoApellido"));
                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setRazonSocial(rs.getObject("p.razonSocial"));
                bu.setIdPais(rs.getObject("p.idPais"));
                bu.setIdMunicipio(rs.getObject("p.idMunicipio"));
                bu.setTelefono(rs.getObject("p.telefono"));
                bu.setEmail(rs.getObject("p.email"));
                bu.setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));

            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(bu); // y registros consultados

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

    public ArrayList<Object> BuscarEntidad(String idTipoDocumento, String identificacion, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanEntidad bu;
        bu = new BeanEntidad();
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

            String query = "SELECT p.idTipoDocumento, p.identificacion ";
            query += "FROM entidad p ";
            query += "WHERE ";
            query += "p.idTipoDocumento = " + idTipoDocumento + " AND p.identificacion = " + identificacion;

            System.out.println("***********************************************");
            System.out.println("*****       Buscar Entidad  *****");
            System.out.println("***********************************************");

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));
                
                String p =(String) bu.getIdTipoDocumento().toString();
                String p2 =(String) bu.getIdentificacion().toString();
                if ((p.equals(idTipoDocumento)) && (p2.equals(identificacion))){
                    encontro = true;
                }
            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(encontro); // y registros consultados

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

    public ArrayList<Object> MostrarEntidad(EntidadOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        
        try {
            
            GR_ENTIDAD = new ArrayList<Object>();
            
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

            String query = "SELECT p.idEntidad, IF(primernombre <> NULL AND primerapellido <> NULL, razonSocial, CONCAT(IF(primernombre <> NULL,'',CONCAT(primernombre,' ')), IF(segundonombre <> NULL,'',CONCAT(segundonombre,' ')), IF(primerapellido <> NULL,'',CONCAT(primerapellido,' ')), IF(segundoapellido <> NULL,'',CONCAT(segundoapellido,' ')))) as nombre, p.idTipoDocumento, p.identificacion, r.nombre ";
            query += "FROM entidad p INNER JOIN tipoDocumento r ON p.idTipoDocumento = r.idTipoDocumento ";
            String query2 = "";
            if (f.getbNombre().isEmpty() != true) {
                query2 = "nombre LIKE '%" + f.getbNombre() + "%'";
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
            System.out.println("*****       Cargando grilla  GR_ENTIDAD  *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanEntidad bu;
            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setNombre(rs.getObject("nombre"));
                bu.setNombreTipoDoc(rs.getObject("r.nombre"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));

                GR_ENTIDAD.add(bu);


            }

            st.close();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_ENTIDAD); // y registros consultados

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

    public ArrayList<Object> ModificaEntidad(EntidadForm f, Boolean transac, Connection tCn) {

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

            String query = "UPDATE entidad SET primerNombre = '" + f.getPrimerNombre();
            query += "', SegundoNombre ='" + f.getSegundoNombre();
            query += "', PrimerApellido ='" + f.getPrimerApellido();
            query += "', SegundoApellido ='" + f.getSegundoApellido();
            query += "', idTipoDocumento =" + f.getIdTipoDocumento();
            query += ", identificacion=" + f.getIdentificacion();
            query += ", razonSocial='" + f.getRazonSocial();
            query += "', idPais=" + f.getIdPais();
            query += ", idMunicipio=" + f.getIdMunicipio();
            query += ", direccion='" + f.getDireccion();
            query += "', telefono='" + f.getTelefono();
            query += "', email='" + f.getEmail();
            query += "', idTipoEntidad=" + f.getIdTipoEntidad();
            query += " WHERE idEntidad=" + f.getIdEntidad();


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

    public ArrayList<Object> EliminaEntidad(EntidadForm f, Boolean transac, Connection tCn) {

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

            String query = "DELETE FROM entidad ";
            query += "WHERE  idEntidad = " + f.getIdEntidad();


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

    public ArrayList<Object> MostrarEntidadFormulario(String IdEntidad, Boolean transac, Connection tCn) {

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

            String query = "SELECT p.idEntidad, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.idTipoDocumento, p.identificacion, p.razonSocial, p.idPais, p.idMunicipio, p.direccion, p.telefono, p.email, p.idTipoEntidad ";
            query += "FROM entidad p ";
            query += "WHERE  p.idEntidad = " + IdEntidad;


            System.out.println("***********************************************");
            System.out.println("*****       MostrarEntidadFormulario     *****");
            System.out.println("***********************************************");

            System.out.println(query);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            BeanEntidad bu;
            while (rs.next()) {
                bu = new BeanEntidad();

                setIdEntidad(rs.getObject("p.idEntidad"));
                setPrimerNombre(rs.getObject("p.primerNombre"));
                setSegundoNombre(rs.getObject("p.segundoNombre"));
                setPrimerApellido(rs.getObject("p.primerApellido"));
                setSegundoApellido(rs.getObject("p.segundoApellido"));
                setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                setIdentificacion(rs.getObject("p.identificacion"));
                setRazonSocial(rs.getObject("p.razonSocial"));
                setIdPais(rs.getObject("p.idPais"));
                setIdMunicipio(rs.getObject("p.idMunicipio"));
                setDireccion(rs.getObject("p.direccion"));
                setTelefono(rs.getObject("p.telefono"));
                setEmail(rs.getObject("p.email"));
                setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));

            }

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
    private Object idEntidad;
    private Object primerNombre;
    private Object segundoNombre;
    private Object primerApellido;
    private Object segundoApellido;
    private Object idTipoDocumento;
    private Object identificacion;
    private Object razonSocial;
    private Object idPais;
    private Object idMunicipio;
    private Object direccion;
    private Object telefono;
    private Object email;
    private Object idTipoEntidad;

    public Object getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Object idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Object getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Object identificacion) {
        this.identificacion = identificacion;
    }

    public Object getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(Object primerApellido) {
        this.primerApellido = primerApellido;
    }

    public Object getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(Object primerNombre) {
        this.primerNombre = primerNombre;
    }

    public Object getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(Object segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Object getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(Object segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Object getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Object idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Object getDireccion() {
        return direccion;
    }

    public void setDireccion(Object direccion) {
        this.direccion = direccion;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Object getIdPais() {
        return idPais;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

    public Object getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(Object idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public Object getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(Object razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

}
