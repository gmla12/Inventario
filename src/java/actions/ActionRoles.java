/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.RolesForm;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionRoles;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionRoles extends Action {

    public ActionRoles() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        RolesForm fo = (RolesForm) form;
        GestionRoles gr = new GestionRoles();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdRoles", fo.getIdRoles());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaRoles(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdRoles", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Roles");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Roles");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdRoles", fo.getIdRoles());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaRoles(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Roles");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar Roles");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdRoles", fo.getIdRoles());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaRoles(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Roles");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Roles");
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
