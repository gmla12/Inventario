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
public class BeanPlantillaDispositivoHija implements Serializable{

    private Object idPlantillaDispositivo;
    private Object idPlantillaDispositivoHija;
    private Object nombre;

    public Object getIdPlantillaDispositivo() {
        return idPlantillaDispositivo;
    }

    public void setIdPlantillaDispositivo(Object idPlantillaDispositivo) {
        this.idPlantillaDispositivo = idPlantillaDispositivo;
    }

    public Object getIdPlantillaDispositivoHija() {
        return idPlantillaDispositivoHija;
    }

    public void setIdPlantillaDispositivoHija(Object idPlantillaDispositivoHija) {
        this.idPlantillaDispositivoHija = idPlantillaDispositivoHija;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
