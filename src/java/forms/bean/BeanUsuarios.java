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
public class BeanUsuarios implements Serializable{

    private Object idUsuario;
    private Object login;
    private Object idTipoDocumento;
    private Object identificacion;
    private Object idRol;
    private Object nombre;
    private Object nombreDocumento;
    private Object nombreRol;

    public Object getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(Object nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }
    
    public Object getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(Object nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
        this.login = login;
    }

    public Object getIdRol() {
        return idRol;
    }

    public void setIdRol(Object idRol) {
        this.idRol = idRol;
    }

    public Object getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Object idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Object getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Object idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
    
    public Object getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Object identificacion) {
        this.identificacion = identificacion;
    }

}
