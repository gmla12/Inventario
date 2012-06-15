/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.RolesOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionRoles;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionRolesOp extends Action {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ActionRolesOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        RolesOpForm fo = (RolesOpForm) form;
        GestionRoles gr = new GestionRoles();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpRoles  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarRolesFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdRoles", gr.getIdRoles());
                    request.setAttribute("getNombre", gr.getNombre());

                    return mapping.findForward("modificar");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() == null) {
                    fo.setbIdRoles((String) session.getAttribute("getbIdRoles"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarRolesOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("getbIdRoles", fo.getbIdRoles());
                    session.setAttribute("getbNombre", fo.getbNombre());

                    session.setAttribute("GR_ROLES", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdRoles", "");
                request.setAttribute("getNombre", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdRoles", "");
            session.setAttribute("getbNombre", "");
            fo.setbNombre("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarRolesOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                session.setAttribute("GR_ROLES", resultado.get(1));
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
