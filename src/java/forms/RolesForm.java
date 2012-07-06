/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author Mario
 */
public class RolesForm extends ActionForm{
    private int idRoles;
    private String nombre;
    private String op;

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
