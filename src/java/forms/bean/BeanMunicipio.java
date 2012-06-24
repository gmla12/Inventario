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
public class BeanMunicipio implements Serializable{

    private Object idMunicipio;
    private Object idPais;
    private Object nombre;

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    
    public Object getIdPais() {
        return idPais;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
