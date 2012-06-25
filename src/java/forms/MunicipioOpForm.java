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
public class MunicipioOpForm extends ActionForm{
    private String bIdMunicipio;
    private String bIdDepartamento;
    private String bIdPais;
    private String bNombre;
    private String op;
    private String id;
    private String id2;
    private String id3;

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
    
    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getbIdMunicipio() {
        return bIdMunicipio;
    }

    public void setbIdMunicipio(String bIdMunicipio) {
        this.bIdMunicipio = bIdMunicipio;
    }

    public String getbIdDepartamento() {
        return bIdDepartamento;
    }

    public void setbIdDepartamento(String bIdDepartamento) {
        this.bIdDepartamento = bIdDepartamento;
    }

    public String getbIdPais() {
        return bIdPais;
    }

    public void setbIdPais(String bIdPais) {
        this.bIdPais = bIdPais;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

}
