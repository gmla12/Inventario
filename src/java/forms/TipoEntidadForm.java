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
    private int idTipoEntidad;
    private String nombre;
    private String op;

    public int getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(int idTipoEntidad) {
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
