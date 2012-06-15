/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.UsuariosOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionUsuarios;
import modelo.GestionRoles;
import modelo.GestionTipoDocumento;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionUsuariosOp extends Action {

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
    public ActionUsuariosOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        UsuariosOpForm fo = (UsuariosOpForm) form;
        GestionUsuarios g = new GestionUsuarios();
        GestionRoles gr = new GestionRoles();
        GestionTipoDocumento gt = new GestionTipoDocumento();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpUsuarios  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = g.MostrarUsuarioFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.MostrarRoles(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gt.MostrarTipoDocumento(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            request.setAttribute("getIdUsuario", g.getIdUsuario());
                            request.setAttribute("getLogin", g.getLogin());
                            request.setAttribute("getPassword", g.getPassword());
                            request.setAttribute("getIdRol", g.getIdRol());
                            request.setAttribute("getIdTipoDocumento", g.getIdTipoDocumento());
                            request.setAttribute("getIdentificacion", g.getIdentificacion());

                            session.setAttribute("CMB_ROLES", resultado2.get(1));
                            session.setAttribute("CMB_TIPODOCUMENTO", resultado3.get(1));

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

                if (fo.getbLogin() == null) {
                    fo.setbIdUsuario((String) session.getAttribute("getbIdUsuario"));
                    fo.setbLogin((String) session.getAttribute("getbLogin"));
                    fo.setbPassword((String) session.getAttribute("getbPassword"));
                    fo.setbIdRol((String) session.getAttribute("getbIdRol"));
                    fo.setbIdTipoDocumento((String) session.getAttribute("getbIdTipoDocumento"));
                    fo.setbIdentificacion((String) session.getAttribute("getbIdentificacion"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = g.MostrarUsuarios(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.MostrarRoles(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gt.MostrarTipoDocumento(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            session.setAttribute("getbIdUsuario", fo.getbIdUsuario());
                            session.setAttribute("getbLogin", fo.getbLogin());
                            session.setAttribute("getbPassword", fo.getbPassword());
                            session.setAttribute("getbIdRol", fo.getbIdRol());
                            session.setAttribute("getbIdTipoDocumento", fo.getbIdTipoDocumento());
                            session.setAttribute("getbIdentificacion", fo.getbIdentificacion());

                            session.setAttribute("GR_USUARIO", resultado.get(1));
                            session.setAttribute("CMB_ROLES", resultado2.get(1));
                            session.setAttribute("CMB_TIPODOCUMENTO", resultado3.get(1));
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

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarRoles(false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado3 = new ArrayList<Object>();
                    resultado3 = gt.MostrarTipoDocumento(false, null);
                    if ((Boolean) resultado3.get(0) == false) {

                        request.setAttribute("getIdUsuario", "");
                        request.setAttribute("getLogin", "");
                        request.setAttribute("getPassword", "");
                        request.setAttribute("getIdRol", "");
                        request.setAttribute("getIdTipoDocumento", "");
                        request.setAttribute("getIdentificacion", "");

                        session.setAttribute("CMB_ROLES", resultado.get(1));
                        session.setAttribute("CMB_TIPODOCUMENTO", resultado3.get(1));
                        return mapping.findForward("nuevo");

                    } else {

                        request.setAttribute("error", resultado3.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            }

        } else {

            session.setAttribute("getbIdUsuario", "");
            session.setAttribute("getbLogin", "");
            session.setAttribute("getbPassword", "");
            session.setAttribute("getbIdRol", "");
            session.setAttribute("getbIdTipoDocumento", "");
            session.setAttribute("getbIdentificacion", "");
            fo.setbLogin("");
            fo.setbPassword("");
            fo.setbIdRol("");
            fo.setbIdTipoDocumento("");
            fo.setbIdentificacion("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = g.MostrarUsuarios(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = gr.MostrarRoles(false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    ArrayList<Object> resultado3 = new ArrayList<Object>();
                    resultado3 = gt.MostrarTipoDocumento(false, null);
                    if ((Boolean) resultado3.get(0) == false) {

                        session.setAttribute("GR_USUARIO", resultado.get(1));
                        session.setAttribute("CMB_ROLES", resultado2.get(1));
                        session.setAttribute("CMB_TIPODOCUMENTO", resultado3.get(1));
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
