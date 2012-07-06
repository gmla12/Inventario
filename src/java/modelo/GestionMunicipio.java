/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.MunicipioForm;
import forms.MunicipioOpForm;
import forms.bean.BeanMunicipio;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionMunicipio extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaMunicipio(MunicipioForm f, Boolean transac, Connection tCn) {

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

            psInsertar = cn.prepareStatement("insert into municipios (idMunicipio, idDepartamento, idPais, nombre) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, f.getIdMunicipio());
            psInsertar.setString(2, f.getIdDepartamento());
            psInsertar.setString(3, f.getIdPais());
            psInsertar.setString(4, f.getNombre());
            psInsertar.executeUpdate(); // Se ejecuta la inserción.

            mod = psInsertar.getUpdateCount();

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
    private ArrayList<Object> GR_MUNICIPIO;

    public ArrayList<Object> MostrarMunicipio(String idDepartamento, String idPais, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_MUNICIPIO = new ArrayList<Object>();

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

            psSelectConClave = cn.prepareStatement("SELECT p.idMunicipio, p.idDepartamento, p.idPais, p.nombre FROM municipios p WHERE p.idPais = ? AND p.idDepartamento = ?");
            psSelectConClave.setString(1, idPais);
            psSelectConClave.setString(2, idDepartamento);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanMunicipio bu;
            while (rs.next()) {
                bu = new BeanMunicipio();

                bu.setIdMunicipio(rs.getObject("p.idMunicipio"));
                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setIdPais(rs.getObject("p.idPais"));
                bu.setNombre(rs.getObject("p.nombre"));

                GR_MUNICIPIO.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_MUNICIPIO); // y registros consultados

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

    public ArrayList<Object> MostrarMunicipioOP(MunicipioOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_MUNICIPIO = new ArrayList<Object>();

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

            String query = "SELECT p.idMunicipio, p.idDepartamento, p.idPais, p.nombre, r.nombre, d.nombre ";
            query += "FROM municipios p INNER JOIN paises r ON p.idPais = r.idPais INNER JOIN departamentos d ON p.idDepartamento = d.idDepartamento";
            String query2 = "";
            if (f.getbIdMunicipio().isEmpty() != true) {
                query2 = "p.idMunicipio LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdDepartamento().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.idDepartamento LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdPais().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.idPais LIKE CONCAT('%',?,'%')";
            }
            if (f.getbNombre().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.nombre LIKE CONCAT('%',?,'%')";
            }
            if (query2.isEmpty() != true) {
                query += " WHERE " + query2;
            }
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbIdMunicipio().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbIdMunicipio());
                if (f.getbIdDepartamento().isEmpty() != true) {
                    psSelectConClave.setString(2, f.getbIdDepartamento());
                    if (f.getbIdPais().isEmpty() != true) {
                        psSelectConClave.setString(3, f.getbIdPais());
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(4, f.getbNombre());
                        }
                    } else {
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbNombre());
                        }
                    }
                } else {
                    if (f.getbIdPais().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbIdPais());
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbNombre());
                        }
                    } else {
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbNombre());
                        }
                    }
                }
            } else {
                if (f.getbIdDepartamento().isEmpty() != true) {
                    psSelectConClave.setString(1, f.getbIdDepartamento());
                    if (f.getbIdPais().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbIdPais());
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbNombre());
                        }
                    } else {
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbNombre());
                        }
                    }
                } else {
                    if (f.getbIdPais().isEmpty() != true) {
                        psSelectConClave.setString(1, f.getbIdPais());
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbNombre());
                        }
                    } else {
                        if (f.getbNombre().isEmpty() != true) {
                            psSelectConClave.setString(1, f.getbNombre());
                        }
                    }
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanMunicipio bu;
            while (rs.next()) {

                bu = new BeanMunicipio();

                bu.setIdMunicipio(rs.getObject("p.idMunicipio"));
                bu.setIdPais(rs.getObject("p.idPais"));
                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setNombre(rs.getObject("p.nombre"));
                bu.setNombreDepartamento(rs.getObject("d.nombre"));
                bu.setNombrePais(rs.getObject("r.nombre"));

                GR_MUNICIPIO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_MUNICIPIO); // y registros consultados

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

    public ArrayList<Object> ModificaMunicipio(MunicipioForm f, Boolean transac, Connection tCn) {

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

            String query = "UPDATE municipios SET nombre = ?";
            query += " WHERE idMunicipio = ? AND idDepartamento = ? AND idPais = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setString(1, f.getNombre());
            psUpdate.setString(2, f.getIdMunicipio());
            psUpdate.setString(3, f.getIdDepartamento());
            psUpdate.setString(4, f.getIdPais());
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

    public ArrayList<Object> EliminaMunicipio(MunicipioForm f, Boolean transac, Connection tCn) {

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

            psDelete = cn.prepareStatement("DELETE FROM municipios WHERE  idMunicipio = ? AND idDepartamento = ? AND idPais = ?");
            psDelete.setString(1, f.getIdMunicipio());
            psDelete.setString(2, f.getIdDepartamento());
            psDelete.setString(3, f.getIdPais());
            psDelete.executeUpdate();

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

    public ArrayList<Object> BuscarMunicipio(String idMunicipio, String idDepartamento, String idPais, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        BeanMunicipio bu;
        bu = new BeanMunicipio();
        boolean encontro = false;
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

            psSelectConClave = cn.prepareStatement("SELECT p.idMunicipio, p.idDepartamento, p.idPais FROM municipios p WHERE p.idMunicipio = ? AND p.idDepartamento = ? AND p.idPais = ?");
            psSelectConClave.setString(1, idMunicipio);
            psSelectConClave.setString(2, idDepartamento);
            psSelectConClave.setString(3, idPais);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {
                bu = new BeanMunicipio();

                bu.setIdMunicipio(rs.getObject("p.idMunicipio"));
                bu.setIdDepartamento(rs.getObject("p.idDepartamento"));
                bu.setIdPais(rs.getObject("p.idPais"));
                String p = (String) bu.getIdMunicipio();
                String p2 = (String) bu.getIdDepartamento();
                String p3 = (String) bu.getIdPais();
                if (p.equals(idMunicipio) && p2.equals(idDepartamento) && p3.equals(idPais)) {
                    encontro = true;
                }


            }

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

    public ArrayList<Object> MostrarMunicipioFormulario(String IdMunicipio, String IdDepartamento, String IdPais, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
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

            psSelectConClave = cn.prepareStatement("SELECT p.idMunicipio, p.idDepartamento, p.idPais, p.nombre FROM municipios p WHERE p.idMunicipio = ? AND p.idDepartamento = ? AND p.idPais = ?");
            psSelectConClave.setString(1, IdMunicipio);
            psSelectConClave.setString(2, IdDepartamento);
            psSelectConClave.setString(3, IdPais);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanMunicipio bu;
            while (rs.next()) {
                bu = new BeanMunicipio();

                setIdMunicipio(rs.getObject("p.idMunicipio"));
                setIdDepartamento(rs.getObject("p.idDepartamento"));
                setIdPais(rs.getObject("p.idPais"));
                setNombre(rs.getObject("p.nombre"));

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
    private Object idMunicipio;
    private Object idDepartamento;
    private Object idPais;
    private Object nombre;

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

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
