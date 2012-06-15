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
public class BeanTipoDocumentoAut implements Serializable{

    private Object idTipoDocumento;
    private Object campo;
    private Object habilitar;
    private Object obligatorio;

    public Object getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Object idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Object getCampo() {
        return campo;
    }

    public void setCampo(Object campo) {
        this.campo = campo;
    }

    public Object getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Object habilitar) {
        this.habilitar = habilitar;
    }

    public Object getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Object obligatorio) {
        this.obligatorio = obligatorio;
    }

}
