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
public class BeanPlantillaDispositivo implements Serializable{

    private Object idPlantillaDispositivo;
    private Object nombre;
    private Object descripcion;
    private Object hija;

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

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getHija() {
        return hija;
    }

    public void setHija(Object hija) {
        this.hija = hija;
    }

}
