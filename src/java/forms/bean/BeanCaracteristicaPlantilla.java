/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanCaracteristicaPlantilla implements Serializable{

    private Object idCaracteristicaPlantilla;
    private Object idPlantillaDispositivo;
    private Object nombre;
    private Object obligatorio;
    private Object habilitar;

    public Object getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Object habilitar) {
        this.habilitar = habilitar;
    }

    public Object getIdCaracteristicaPlantilla() {
        return idCaracteristicaPlantilla;
    }

    public void setIdCaracteristicaPlantilla(Object idCaracteristicaPlantilla) {
        this.idCaracteristicaPlantilla = idCaracteristicaPlantilla;
    }

    public Object getIdPlantillaDispositivo() {
        return idPlantillaDispositivo;
    }

    public void setIdPlantillaDispositivo(Object idPlantillaDispositivo) {
        this.idPlantillaDispositivo = idPlantillaDispositivo;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Object obligatorio) {
        this.obligatorio = obligatorio;
    }

}
