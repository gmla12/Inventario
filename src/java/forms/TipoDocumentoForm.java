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
public class TipoDocumentoForm extends ActionForm{
    private int idTipoDocumento;
    private String nombre;
    private String op;
    private boolean habilitar;
    private boolean obligatorio;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }
}
