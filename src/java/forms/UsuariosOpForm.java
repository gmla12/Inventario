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
public class UsuariosOpForm extends ActionForm{
    private String bIdUsuario;
    private String bLogin;
    private String bPassword;
    private String bIdRol;
    private String bIdTipoDocumento;
    private String bIdentificacion;
    private String op;
    private int id;

    public String getbIdRol() {
        return bIdRol;
    }

    public void setbIdRol(String bIdRol) {
        this.bIdRol = bIdRol;
    }

    public String getbIdUsuario() {
        return bIdUsuario;
    }

    public void setbIdUsuario(String bIdUsuario) {
        this.bIdUsuario = bIdUsuario;
    }

    public String getbIdentificacion() {
        return bIdentificacion;
    }

    public void setbIdentificacion(String bIdentificacion) {
        this.bIdentificacion = bIdentificacion;
    }
    
    public String getbIdTipoDocumento() {
        return bIdTipoDocumento;
    }

    public void setbIdTipoDocumento(String bIdTipoDocumento) {
        this.bIdTipoDocumento = bIdTipoDocumento;
    }

    public String getbLogin() {
        return bLogin;
    }

    public void setbLogin(String bLogin) {
        this.bLogin = bLogin;
    }

    public String getbPassword() {
        return bPassword;
    }

    public void setbPassword(String bPassword) {
        this.bPassword = bPassword;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
