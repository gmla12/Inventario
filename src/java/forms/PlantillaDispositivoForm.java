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
public class PlantillaDispositivoForm extends ActionForm{
    private int idPlantillaDispositivo;
    private String nombre;
    private String descripcion;
    private boolean hija;
    private String op;
    private boolean habilitar;
    private boolean obligatorio;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getHija() {
        return hija;
    }

    public void setHija(boolean hija) {
        this.hija = hija;
    }

    public int getIdPlantillaDispositivo() {
        return idPlantillaDispositivo;
    }

    public void setIdPlantillaDispositivo(int idPlantillaDispositivo) {
        this.idPlantillaDispositivo = idPlantillaDispositivo;
    }
    
}
