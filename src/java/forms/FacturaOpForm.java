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
public class FacturaOpForm extends ActionForm{
    private int bIdFactura;
    private String bIdEntidad;
    private String bNumFactura;
    private String bFecha;
    private String op;
    private int id;

    public String getbFecha() {
        return bFecha;
    }

    public void setbFecha(String bFecha) {
        this.bFecha = bFecha;
    }

    public String getbIdEntidad() {
        return bIdEntidad;
    }

    public void setbIdEntidad(String bIdEntidad) {
        this.bIdEntidad = bIdEntidad;
    }

    public int getbIdFactura() {
        return bIdFactura;
    }

    public void setbIdFactura(int bIdFactura) {
        this.bIdFactura = bIdFactura;
    }

    public String getbNumFactura() {
        return bNumFactura;
    }

    public void setbNumFactura(String bNumFactura) {
        this.bNumFactura = bNumFactura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

}
