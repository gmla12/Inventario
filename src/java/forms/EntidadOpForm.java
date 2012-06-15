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
public class EntidadOpForm extends ActionForm{
    private String bIdEntidad;
    private String bNombre;
    private String bIdTipoDocumento;
    private String bIdentificacion;
    private String op;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getbIdEntidad() {
        return bIdEntidad;
    }

    public void setbIdEntidad(String bIdEntidad) {
        this.bIdEntidad = bIdEntidad;
    }

    public String getbIdTipoDocumento() {
        return bIdTipoDocumento;
    }

    public void setbIdTipoDocumento(String bIdTipoDocumento) {
        this.bIdTipoDocumento = bIdTipoDocumento;
    }

    public String getbIdentificacion() {
        return bIdentificacion;
    }

    public void setbIdentificacion(String bIdentificacion) {
        this.bIdentificacion = bIdentificacion;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }


}
