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
public class PlantillaDispositivoOpForm extends ActionForm{
    private String bIdPlantillaDispositivo;
    private String bNombre;
    private String bHija;
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

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

    public String getbHija() {
        return bHija;
    }

    public void setbHija(String bHija) {
        this.bHija = bHija;
    }

    public String getbIdPlantillaDispositivo() {
        return bIdPlantillaDispositivo;
    }

    public void setbIdPlantillaDispositivo(String bIdPlantillaDispositivo) {
        this.bIdPlantillaDispositivo = bIdPlantillaDispositivo;
    }

}
