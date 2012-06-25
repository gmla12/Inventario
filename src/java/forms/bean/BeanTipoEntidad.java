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
public class BeanTipoEntidad implements Serializable{

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
