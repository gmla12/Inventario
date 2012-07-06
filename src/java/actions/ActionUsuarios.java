/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.UsuariosForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionUsuarios;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionUsuarios extends Action {

    public ActionUsuarios() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        UsuariosForm fo = (UsuariosForm) form;
        GestionUsuarios gr = new GestionUsuarios();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdUsuario", fo.getIdUsuario());
            request.setAttribute("getLogin", fo.getLogin());
            request.setAttribute("getPassword", fo.getPassword());
            request.setAttribute("getIdRol", fo.getIdRol());
            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getIdentificacion", fo.getIdentificacion());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaUsuarios(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdUsuario", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Usuarios");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Usuarios");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdUsuario", fo.getIdUsuario());
            request.setAttribute("getLogin", fo.getLogin());
            request.setAttribute("getPassword", fo.getPassword());
            request.setAttribute("getIdRol", fo.getIdRol());
            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getIdentificacion", fo.getIdentificacion());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaUsuarios(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Usuarios");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar Usuarios");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaUsuarios(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {

                    request.setAttribute("getIdUsuario", "");
                    request.setAttribute("getLogin", "");
                    request.setAttribute("getPassword", "");
                    request.setAttribute("getIdRol", "");
                    request.setAttribute("getIdTipoDocumento", "");
                    request.setAttribute("getIdentificacion", "");

                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Usuarios");

                } else {

                    request.setAttribute("getIdUsuario", fo.getIdUsuario());
                    request.setAttribute("getLogin", fo.getLogin());
                    request.setAttribute("getPassword", fo.getPassword());
                    request.setAttribute("getIdRol", fo.getIdRol());
                    request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
                    request.setAttribute("getIdentificacion", fo.getIdentificacion());

                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Usuarios");

                }
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else {

            request.setAttribute("getOp", "buscar");
            return mapping.findForward("atras");

        }

    }
}
