/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.FacturaForm;
import forms.FacturaOpForm;
import forms.bean.BeanFactura;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionFactura extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaFactura(FacturaForm f, Boolean transac, Connection tCn) {

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

            psInsertar = cn.prepareStatement("insert into Factura (idFactura, idEntidad, numFactura, fecha, total) values (null,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setInt(1, f.getIdEntidad());
            psInsertar.setString(2, f.getNumFactura());
            psInsertar.setDate(3, new java.sql.Date(f.getFecha().getTime()));
            psInsertar.setLong(4, f.getTotal());
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
            resultado.add(claveGenerada); // clave Generada
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
    private ArrayList<Object> GR_FACTURA;

    public ArrayList<Object> MostrarFactura(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_FACTURA = new ArrayList<Object>();

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

            psSelectConClave = cn.prepareStatement("SELECT p.idFactura, p.idEntidad, p.numFactura, p.fecha, p.total FROM factura p");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanFactura bu;
            while (rs.next()) {
                bu = new BeanFactura();

                bu.setIdFactura(rs.getObject("p.idFactura"));
                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setNumFactura(rs.getObject("p.numFactura"));
                bu.setFecha(rs.getObject("p.fecha"));
                bu.setTotal(rs.getObject("p.total"));

                GR_FACTURA.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_FACTURA); // y registros consultados

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

    public ArrayList<Object> MostrarFacturaOP(FacturaOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_FACTURA = new ArrayList<Object>();

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

            String query = "SELECT p.idFactura, p.idEntidad, p.numFactura, DATE_FORMAT(p.fecha,'%d/%m/%Y') as fecha, p.total, r.idEntidad, IF(r.primernombre <> NULL AND r.primerapellido <> NULL, r.razonSocial, CONCAT(IF(r.primernombre <> NULL,'',CONCAT(r.primernombre,' ')), IF(r.segundonombre <> NULL,'',CONCAT(r.segundonombre,' ')), IF(r.primerapellido <> NULL,'',CONCAT(r.primerapellido,' ')), IF(r.segundoapellido <> NULL,'',CONCAT(r.segundoapellido,' ')))) as nombreEntidad ";
            query += "FROM factura p INNER JOIN entidad r ON p.idEntidad = r.idEntidad";
            String query2 = "";
            if (f.getbIdEntidad().isEmpty() != true) {
                query2 = "p.idEntidad = ?";
            }
            if (f.getbNumFactura().isEmpty() != true) {
                if (query2.equals("") != true) {
                    query2 = query2 + " AND ";
                }
                query2 = query2 + "p.numFactura LIKE CONCAT('%',?,'%')";
            }
            if (f.getbFecha().isEmpty() != true) {
                if (query2.equals("") != true) {
                    query2 = query2 + " AND ";
                }
                query2 = query2 + "DATE_FORMAT(fecha,'%d/%m/%Y') = ?";
            }
            if (query2.isEmpty() != true) {
                query += " WHERE " + query2;
            }
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbIdEntidad().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbIdEntidad());
                if (f.getbNumFactura().isEmpty() != true) {
                    psSelectConClave.setString(2, f.getbNumFactura());
                    if (f.getbFecha().isEmpty() != true) {
                        psSelectConClave.setString(3, f.getbFecha());
                    }
                } else {
                    if (f.getbFecha().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbFecha());
                    }
                }
            } else {
                if (f.getbNumFactura().isEmpty() != true) {
                    psSelectConClave.setString(1, f.getbNumFactura());
                    if (f.getbFecha().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbFecha());
                    }
                }else{
                    if (f.getbFecha().isEmpty() != true) {
                        psSelectConClave.setString(1, f.getbFecha());
                    }
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanFactura bu;
            while (rs.next()) {
                bu = new BeanFactura();

                bu.setIdFactura(rs.getObject("p.idFactura"));
                bu.setIdEntidad(rs.getObject("p.idEntidad"));
                bu.setNombreEntidad(rs.getObject("nombreEntidad"));
                bu.setNumFactura(rs.getObject("p.numFactura"));
                bu.setFecha(rs.getObject("fecha"));
                bu.setTotal(rs.getObject("p.total"));

                GR_FACTURA.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_FACTURA); // y registros consultados

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

//    public ArrayList<Object> ModificaFactura(FacturaForm f, Boolean transac, Connection tCn) {
//
//        int mod = -99;
//        ArrayList<Object> resultado = new ArrayList<Object>();
//        PreparedStatement psUpdate = null;
//
//        try {
//
//            if (transac == false) { //si no es una transaccion busca una nueva conexion
//
//                ArrayList<Object> resultad;
//                resultad = (ArrayList) getConection();
//
//                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion
//
//                    cn = (Connection) resultad.get(1);
//
//                } else { //si hubo error al obtener la conexion retorna el error para visualizar
//
//                    resultado.add(true);
//                    resultado.add(resultad.get(1));
//                    return resultado;
//
//                }
//
//            } else { //si es una transaccion asigna la conexion utilizada
//
//                cn = tCn;
//
//            }
//
//            String query = "UPDATE factura SET idEntidad = ?";
//            query += ", descripcion = ?";
//            query += ", serHija = ?";
//            query += " WHERE idPlantillaDispositivo = ?";
//            psUpdate = cn.prepareStatement(query);
//            psUpdate.setString(1, f.getNombre());
//            psUpdate.setString(2, f.getDescripcion());
//            psUpdate.setBoolean(3, f.getHija());
//            psUpdate.setInt(4, f.getIdPlantillaDispositivo());
//            psUpdate.executeUpdate();
//
//            mod = psUpdate.getUpdateCount();
//
//            if (transac == false) { // si no es una transaccion cierra la conexion
//
//                cn.close();
//
//            }
//
//            resultado.add(false); //si no hubo un error asigna false
//            resultado.add(mod); // y el numero de registros consultados
//
//        } catch (Exception e) {
//
//            resultado.add(true); //si hubo error asigna true
//            resultado.add(e); //y asigna el error para retornar y visualizar
//
//            if (cn != null) {
//                cn.rollback();
//                cn.close();
//            }
//
//        } finally {
//
//            return resultado;
//
//        }
//
//    }

    public ArrayList<Object> EliminaFactura(FacturaForm f, Boolean transac, Connection tCn) {

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

            psDelete = cn.prepareStatement("DELETE FROM factura WHERE  idFactura = ?");
            psDelete.setInt(1, f.getIdFactura());
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

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

    public ArrayList<Object> MostrarFacturaFormulario(int IdFactura, Boolean transac, Connection tCn) {

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

            psSelectConClave = cn.prepareStatement("SELECT p.idFactura, p.idEntidad, p.numFactura, DATE_FORMAT(p.fecha,'%d/%m/%Y') as fecha, p.total FROM factura p WHERE p.idFactura = ?");
            psSelectConClave.setInt(1, IdFactura);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setIdFactura(rs.getObject("p.idFactura"));
                setIdEntidad(rs.getObject("p.idEntidad"));
                setNumFactura(rs.getObject("p.numFactura"));
                setFecha(rs.getObject("fecha"));
                setTotal(rs.getObject("p.total"));

            }

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
//}
    private Object idFactura;
    private Object idEntidad;
    private Object numFactura;
    private Object fecha;
    private Object total;

    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    public Object getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Object idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Object getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Object idFactura) {
        this.idFactura = idFactura;
    }

    public Object getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(Object numFactura) {
        this.numFactura = numFactura;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

}
