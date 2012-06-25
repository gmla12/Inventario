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
    private Object idDepartamento;
    private Object idPais;
    private Object nombre;
    private Object nombreDepartamento;
    private Object nombrePais;

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    
    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
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
    
    public Object getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(Object nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    public Object getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(Object nombrePais) {
        this.nombrePais = nombrePais;
    }

}
