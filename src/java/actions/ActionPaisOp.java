/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.PaisOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionPais;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionPaisOp extends Action {

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
    public ActionPaisOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PaisOpForm fo = (PaisOpForm) form;
        GestionPais gr = new GestionPais();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpPais  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPaisFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdPais", gr.getIdPais());
                    request.setAttribute("getNombre", gr.getNombre());

                    return mapping.findForward("modificar");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() == null) {
                    fo.setbIdPais((String) session.getAttribute("getbIdPais"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPaisOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("getbIdPais", fo.getbIdPais());
                    session.setAttribute("getbNombre", fo.getbNombre());

                    session.setAttribute("GR_PAIS", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdPais", "");
                request.setAttribute("getNombre", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPais", "");
            session.setAttribute("getbNombre", "");
            fo.setbIdPais("");
            fo.setbNombre("");
            
            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarPaisOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                session.setAttribute("GR_PAIS", resultado.get(1));
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
