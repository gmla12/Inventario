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
public class BeanRoles implements Serializable{

    private Object idRoles;
    private Object nombre;

    public Object getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Object idRoles) {
        this.idRoles = idRoles;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
