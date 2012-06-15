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
public class PaisOpForm extends ActionForm{
    private String bIdPais;
    private String bNombre;
    private String op;
    private String id;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getbIdPais() {
        return bIdPais;
    }

    public void setbIdPais(String bIdPais) {
        this.bIdPais = bIdPais;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

}
