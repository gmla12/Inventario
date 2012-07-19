/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanFactura implements Serializable{

    private Object idFactura;
    private Object idEntidad;
    private Object nombreEntidad;
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

    public Object getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(Object nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

}
