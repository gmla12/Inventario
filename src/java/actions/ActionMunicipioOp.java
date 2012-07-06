/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.MunicipioOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionDepartamento;
import modelo.GestionMunicipio;
import modelo.GestionPais;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionMunicipioOp extends Action {

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
    public ActionMunicipioOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        MunicipioOpForm fo = (MunicipioOpForm) form;
        GestionMunicipio gr = new GestionMunicipio();
        GestionDepartamento gd = new GestionDepartamento();
        GestionPais gp = new GestionPais();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpMunicipio  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarMunicipioFormulario(fo.getId(), fo.getId2(), fo.getId3(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gd.MostrarDepartamento((String) gr.getIdPais(), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gp.MostrarPais(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            request.setAttribute("getIdMunicipio", gr.getIdMunicipio());
                            request.setAttribute("getIdDepartamento", gr.getIdDepartamento());
                            request.setAttribute("getIdPais", gr.getIdPais());
                            request.setAttribute("getNombre", gr.getNombre());

                            session.setAttribute("CMB_DEPARTAMENTO", resultado2.get(1));
                            session.setAttribute("CMB_PAIS", resultado3.get(1));
                            return mapping.findForward("modificar");

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

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
                    fo.setbIdMunicipio((String) session.getAttribute("getbIdMunicipio"));
                    fo.setbIdDepartamento((String) session.getAttribute("getbIdDepartamento"));
                    fo.setbIdPais((String) session.getAttribute("getbIdPais"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarMunicipioOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gd.MostrarDepartamento(fo.getbIdPais(), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gp.MostrarPais(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            session.setAttribute("getbIdMunicipio", fo.getbIdMunicipio());
                            session.setAttribute("getbIdDepartamento", fo.getbIdDepartamento());
                            session.setAttribute("getbIdPais", fo.getbIdPais());
                            session.setAttribute("getbNombre", fo.getbNombre());

                            session.setAttribute("GR_MUNICIPIO", resultado.get(1));
                            session.setAttribute("CMB_DEPARTAMENTO", resultado2.get(1));
                            session.setAttribute("CMB_PAIS", resultado3.get(1));
                            return mapping.findForward("ok");

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                session.setAttribute("getIdMunicipio", "");
                session.setAttribute("getIdDepartamento", "");
                session.setAttribute("getIdPais", "");
                session.setAttribute("getNombre", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdMunicipio", "");
            session.setAttribute("getbIdDepartamento", "");
            session.setAttribute("getbIdPais", "");
            session.setAttribute("getbNombre", "");
            fo.setbIdMunicipio("");
            fo.setbIdDepartamento("");
            fo.setbIdPais("");
            fo.setbNombre("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarMunicipioOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = gd.MostrarDepartamento("", false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    ArrayList<Object> resultado3 = new ArrayList<Object>();
                    resultado3 = gp.MostrarPais(false, null);
                    if ((Boolean) resultado3.get(0) == false) {

                        session.setAttribute("GR_MUNICIPIO", resultado.get(1));
                        session.setAttribute("CMB_DEPARTAMENTO", resultado2.get(1));
                        session.setAttribute("CMB_PAIS", resultado3.get(1));
                        return mapping.findForward("ok");

                    } else {

                        request.setAttribute("error", resultado3.get(1));
                        return mapping.findForward("error");

                    }

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
