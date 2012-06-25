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
public class TipoEntidadForm extends ActionForm{
    private String idTipoEntidad;
    private String nombre;
    private String op;

    public String getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(String idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
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
