/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.DepartamentoOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionDepartamento;
import modelo.GestionPais;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionDepartamentoOp extends Action {

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
    public ActionDepartamentoOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        DepartamentoOpForm fo = (DepartamentoOpForm) form;
        GestionDepartamento gr = new GestionDepartamento();
        GestionPais gp = new GestionPais();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpDepartamento  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarDepartamentoFormulario(fo.getId(), fo.getId2(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gp.MostrarPais(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        request.setAttribute("getIdDepartamento", gr.getIdDepartamento());
                        request.setAttribute("getIdPais", gr.getIdPais());
                        request.setAttribute("getNombre", gr.getNombre());

                        session.setAttribute("CMB_PAIS", resultado2.get(1));
                        return mapping.findForward("modificar");

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() == null) {
                    fo.setbIdDepartamento((String) session.getAttribute("getbIdDepartamento"));
                    fo.setbIdPais((String) session.getAttribute("getbIdPais"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarDepartamentoOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gp.MostrarPais(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        session.setAttribute("getbIdDepartamento", fo.getbIdDepartamento());
                        session.setAttribute("getbIdPais", fo.getbIdPais());
                        session.setAttribute("getbNombre", fo.getbNombre());

                        session.setAttribute("GR_DEPARTAMENTO", resultado.get(1));
                        session.setAttribute("CMB_PAIS", resultado2.get(1));
                        return mapping.findForward("ok");

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdDepartamento", "");
                request.setAttribute("getIdPais", "");
                request.setAttribute("getNombre", "");

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = gp.MostrarPais(false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    session.setAttribute("CMB_PAIS", resultado2.get(1));
                    return mapping.findForward("nuevo");

                } else {

                    request.setAttribute("error", resultado2.get(1));
                    return mapping.findForward("error");

                }

            }

        } else {

            session.setAttribute("getbIdDepartamento", "");
            session.setAttribute("getbIdPais", "");
            session.setAttribute("getbNombre", "");
            fo.setbIdDepartamento("");
            fo.setbIdPais("");
            fo.setbNombre("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarDepartamentoOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = gp.MostrarPais(false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    session.setAttribute("GR_DEPARTAMENTO", resultado.get(1));
                    session.setAttribute("CMB_PAIS", resultado2.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado2.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
