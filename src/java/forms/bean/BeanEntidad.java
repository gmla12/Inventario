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
public class BeanEntidad implements Serializable{

    private Object idEntidad;
    private Object primerNombre;
    private Object segundoNombre;
    private Object primerApellido;
    private Object segundoApellido;
    private Object idTipoDocumento;
    private Object identificacion;
    private Object razonSocial;
    private Object idPais;
    private Object idDepartamento;
    private Object idMunicipio;
    private Object direccion;
    private Object telefono;
    private Object email;
    private Object idTipoEntidad;
    private Object nombreTipoDoc;
    private Object nombre;

    public Object getDireccion() {
        return direccion;
    }

    public Object getEmail() {
        return email;
    }

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public Object getIdPais() {
        return idPais;
    }

    public Object getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public Object getRazonSocial() {
        return razonSocial;
    }

    public Object getTelefono() {
        return telefono;
    }

    public Object getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Object idEntidad) {
        this.idEntidad = idEntidad;
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

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(Object primerApellido) {
        this.primerApellido = primerApellido;
    }

    public Object getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(Object primerNombre) {
        this.primerNombre = primerNombre;
    }

    public Object getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(Object segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Object getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(Object segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setDireccion(Object direccion) {
        this.direccion = direccion;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

    public void setIdTipoEntidad(Object idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public void setRazonSocial(Object razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

    public Object getNombreTipoDoc() {
        return nombreTipoDoc;
    }

    public void setNombreTipoDoc(Object nombreTipoDoc) {
        this.nombreTipoDoc = nombreTipoDoc;
    }

    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

}
