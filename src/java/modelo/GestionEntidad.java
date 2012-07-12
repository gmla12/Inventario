/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.EntidadForm;
import forms.EntidadOpForm;
import forms.bean.BeanEntidad;
import java.sql.*;
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

        int mod;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psInsertar;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            psInsertar = cn.prepareStatement("insert into entidad (idEntidad, primerNombre, segundoNombre, primerApellido, segundoApellido, idTipoDocumento, identificacion, razonSocial, idPais, idDepartamento, idMunicipio, direccion, telefono, email, idTipoEntidad) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, f.getPrimerNombre());
            psInsertar.setString(2, f.getSegundoNombre());
            psInsertar.setString(3, f.getPrimerApellido());
            psInsertar.setString(4, f.getSegundoApellido());
            psInsertar.setInt(5, f.getIdTipoDocumento());
            psInsertar.setInt(6, f.getIdentificacion());
            psInsertar.setString(7, f.getRazonSocial());
            psInsertar.setString(8, f.getIdPais());
            psInsertar.setString(9, f.getIdDepartamento());
            psInsertar.setString(10, f.getIdMunicipio());
            psInsertar.setString(11, f.getDireccion());
            psInsertar.setString(12, f.getTelefono());
            psInsertar.setString(13, f.getEmail());
            psInsertar.setInt(14, f.getIdTipoEntidad());
            psInsertar.executeUpdate(); // Se ejecuta la inserción.

            // Se obtiene la clave generada
            int claveGenerada = -1;
            ResultSet rs = psInsertar.getGeneratedKeys();
            while (rs.next()) {
                claveGenerada = rs.getInt(1);
            }
            mod = psInsertar.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(claveGenerada); // clave generada
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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
    private ArrayList<Object> GR_ENTIDAD;

    public ArrayList<Object> BuscarEntidad(EntidadForm fo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanEntidad bu;
        bu = new BeanEntidad();
        PreparedStatement psSelectConClave;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            System.out.println("***********************************************");
            System.out.println("*****       Buscar Entidad  *****");
            System.out.println("***********************************************");

            psSelectConClave = cn.prepareStatement("SELECT p.idEntidad, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.idTipoDocumento, p.identificacion, p.razonSocial, p.idPais, p.idDepartamento, p.idMunicipio, p.direccion, p.telefono, p.email, p.idTipoEntidad FROM entidad p WHERE p.identificacion=?");
            psSelectConClave.setInt(1, fo.getIdentificacion());
            ResultSet rs = psSelectConClave.executeQuery();

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
                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setTelefono(rs.getObject("p.telefono"));
                bu.setEmail(rs.getObject("p.email"));
                bu.setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(bu); // y registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> BuscarEntidad(int idTipoDocumento, int identificacion, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanEntidad bu;
        boolean encontro = false;
        PreparedStatement psSelectConClave;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            System.out.println("***********************************************");
            System.out.println("*****       Buscar Entidad  *****");
            System.out.println("***********************************************");

            psSelectConClave = cn.prepareStatement("SELECT p.idTipoDocumento, p.identificacion FROM entidad p WHERE p.idTipoDocumento=? AND p.identificacion=?");
            psSelectConClave.setInt(1, idTipoDocumento);
            psSelectConClave.setInt(2, identificacion);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdTipoDocumento(rs.getObject("p.idTipoDocumento"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));

                int p = Integer.parseInt(bu.getIdTipoDocumento().toString());
                int p2 = Integer.parseInt(bu.getIdentificacion().toString());
                if (p == idTipoDocumento && p2 == identificacion) {
                    encontro = true;
                }
            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(encontro); // y registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> MostrarEntidad(EntidadOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_ENTIDAD = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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
                query2 += "(p.primernombre LIKE CONCAT('%',?,'%')";
                query2 += " OR p.segundonombre LIKE CONCAT('%',?,'%')";
                query2 += " OR p.primerapellido LIKE CONCAT('%',?,'%')";
                query2 += " OR p.segundonombre LIKE CONCAT('%',?,'%')";
                query2 += " OR p.razonsocial LIKE CONCAT('%',?,'%'))";
            }
            if (f.getbIdTipoDocumento().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.idTipoDocumento LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdentificacion().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.identificacion LIKE CONCAT('%',?,'%')";
            }
            if (query2.isEmpty() != true) {
                query += " WHERE " + query2;
            }

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_ENTIDAD  *****");
            System.out.println("***********************************************");
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbNombre().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbNombre());
                psSelectConClave.setString(2, f.getbNombre());
                psSelectConClave.setString(3, f.getbNombre());
                psSelectConClave.setString(4, f.getbNombre());
                psSelectConClave.setString(5, f.getbNombre());
                if (f.getbIdTipoDocumento().isEmpty() != true) {
                    psSelectConClave.setString(6, f.getbIdTipoDocumento());
                    if (f.getbIdentificacion().isEmpty() != true) {
                        psSelectConClave.setString(7, f.getbIdentificacion());
                    }
                } else {
                    if (f.getbIdentificacion().isEmpty() != true) {
                        psSelectConClave.setString(6, f.getbIdentificacion());
                    }
                }
            } else {
                if (f.getbIdTipoDocumento().isEmpty() != true) {
                    psSelectConClave.setString(1, f.getbIdTipoDocumento());
                    if (f.getbIdentificacion().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbIdentificacion());
                    }
                } else {
                    if (f.getbIdentificacion().isEmpty() != true) {
                        psSelectConClave.setString(1, f.getbIdentificacion());
                    }
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanEntidad bu;
            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setNombre(rs.getObject("nombre"));
                bu.setNombreTipoDoc(rs.getObject("r.nombre"));
                bu.setIdentificacion(rs.getObject("p.identificacion"));

                GR_ENTIDAD.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_ENTIDAD); // y registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> MostrarEntidad(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_ENTIDAD = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            String query = "SELECT p.idEntidad, IF(primernombre <> NULL AND primerapellido <> NULL, razonSocial, CONCAT(IF(primernombre <> NULL,'',CONCAT(primernombre,' ')), IF(segundonombre <> NULL,'',CONCAT(segundonombre,' ')), IF(primerapellido <> NULL,'',CONCAT(primerapellido,' ')), IF(segundoapellido <> NULL,'',CONCAT(segundoapellido,' ')))) as nombre ";
            query += "FROM entidad p";

            System.out.println("***********************************************");
            System.out.println("*****       Cargando grilla  GR_ENTIDAD  *****");
            System.out.println("***********************************************");
            psSelectConClave = cn.prepareStatement(query);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanEntidad bu;
            while (rs.next()) {
                bu = new BeanEntidad();

                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setNombre(rs.getObject("nombre"));

                GR_ENTIDAD.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_ENTIDAD); // y registros consultados

        } catch (SQLException e) {

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
    
    public ArrayList<Object> ModificaEntidad(EntidadForm f, Boolean transac, Connection tCn) {

        int mod;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psUpdate;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            String query = "UPDATE entidad SET primerNombre = ?";
            query += ", SegundoNombre =?";
            query += ", PrimerApellido =?";
            query += ", SegundoApellido =?";
            query += ", idTipoDocumento =?";
            query += ", identificacion=?";
            query += ", razonSocial=?";
            query += ", idPais=?";
            query += ", idDepartamento=?";
            query += ", idMunicipio=?";
            query += ", direccion=?";
            query += ", telefono=?";
            query += ", email=?";
            query += ", idTipoEntidad=?";
            query += " WHERE idEntidad=?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setString(1, f.getPrimerNombre());
            psUpdate.setString(2, f.getSegundoNombre());
            psUpdate.setString(3, f.getPrimerApellido());
            psUpdate.setString(4, f.getSegundoApellido());
            psUpdate.setInt(5, f.getIdTipoDocumento());
            psUpdate.setInt(6, f.getIdentificacion());
            psUpdate.setString(7, f.getRazonSocial());
            psUpdate.setString(8, f.getIdPais());
            psUpdate.setString(9, f.getIdDepartamento());
            psUpdate.setString(10, f.getIdMunicipio());
            psUpdate.setString(11, f.getDireccion());
            psUpdate.setString(12, f.getTelefono());
            psUpdate.setString(13, f.getEmail());
            psUpdate.setInt(14, f.getIdTipoEntidad());
            psUpdate.setInt(15, f.getIdEntidad());
            psUpdate.executeUpdate();

            mod = psUpdate.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> EliminaEntidad(EntidadForm f, Boolean transac, Connection tCn) {

        int mod;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psDelete;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            psDelete = cn.prepareStatement("DELETE FROM entidad WHERE  idEntidad =?");
            psDelete.setInt(1, f.getIdEntidad());
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (SQLException e) {

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

    public ArrayList<Object> MostrarEntidadFormulario(int IdEntidad, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
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

            System.out.println("***********************************************");
            System.out.println("*****       MostrarEntidadFormulario     *****");
            System.out.println("***********************************************");

            psSelectConClave = cn.prepareStatement("SELECT p.idEntidad, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.idTipoDocumento, p.identificacion, p.razonSocial, p.idPais, p.idDepartamento, p.idMunicipio, p.direccion, p.telefono, p.email, p.idTipoEntidad FROM entidad p WHERE  p.idEntidad =?");
            psSelectConClave.setInt(1, IdEntidad);
            ResultSet rs = psSelectConClave.executeQuery();

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
                setIdDepartamento(rs.getObject("p.idDepartamento"));
                setIdMunicipio(rs.getObject("p.idMunicipio"));
                setDireccion(rs.getObject("p.direccion"));
                setTelefono(rs.getObject("p.telefono"));
                setEmail(rs.getObject("p.email"));
                setIdTipoEntidad(rs.getObject("p.idTipoEntidad"));

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

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

        } catch (SQLException e) {

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

            ArrayList<Object> resultad;
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
    private Object idDepartamento;
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

    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
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
