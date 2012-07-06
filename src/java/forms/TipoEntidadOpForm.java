/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author mario
 */
public class TipoEntidadOpForm extends ActionForm{
    private String bIdTipoEntidad;
    private String bNombre;
    private String op;
    private int id;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbIdTipoEntidad() {
        return bIdTipoEntidad;
    }

    public void setbIdTipoEntidad(String bIdTipoEntidad) {
        this.bIdTipoEntidad = bIdTipoEntidad;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

}
