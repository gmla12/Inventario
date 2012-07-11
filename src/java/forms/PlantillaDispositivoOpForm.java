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
    private int bIdPlantillaDispositivo;
    private String bNombre;
    private String bHija;
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

    public int getbIdPlantillaDispositivo() {
        return bIdPlantillaDispositivo;
    }

    public void setbIdPlantillaDispositivo(int bIdPlantillaDispositivo) {
        this.bIdPlantillaDispositivo = bIdPlantillaDispositivo;
    }

}
