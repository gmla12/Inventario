/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.EntidadOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionEntidadOp extends Action {

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
    public ActionEntidadOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        EntidadOpForm fo = (EntidadOpForm) form;
        GestionEntidad g = new GestionEntidad();
        GestionTipoDocumento gr = new GestionTipoDocumento();
        GestionPais gP = new GestionPais();
        GestionDepartamento gD = new GestionDepartamento();
        GestionMunicipio gM = new GestionMunicipio();
        GestionTipoEntidad gTE = new GestionTipoEntidad();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpEntidad  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = g.MostrarEntidadFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.MostrarTipoDocumento(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gP.MostrarPais(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            resultado4 = gD.MostrarDepartamento((String) g.getIdPais(), false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gM.MostrarMunicipio((String) g.getIdDepartamento(), (String) g.getIdPais(), false, null);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gTE.MostrarTipoEntidad(false, null);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("getIdEntidad", g.getIdEntidad());
                                        request.setAttribute("getPrimerNombre", g.getPrimerNombre());
                                        request.setAttribute("getSegundoNombre", g.getSegundoNombre());
                                        request.setAttribute("getPrimerApellido", g.getPrimerApellido());
                                        request.setAttribute("getSegundoApellido", g.getSegundoApellido());
                                        request.setAttribute("getIdTipoDocumento", g.getIdTipoDocumento());
                                        request.setAttribute("getIdentificacion", g.getIdentificacion());
                                        request.setAttribute("getRazonSocial", g.getRazonSocial());
                                        request.setAttribute("getIdPais", g.getIdPais());
                                        request.setAttribute("getIdDepartamento", g.getIdDepartamento());
                                        request.setAttribute("getIdMunicipio", g.getIdMunicipio());
                                        request.setAttribute("getDireccion", g.getDireccion());
                                        request.setAttribute("getTelefono", g.getTelefono());
                                        request.setAttribute("getEmail", g.getEmail());
                                        request.setAttribute("getIdTipoEntidad", g.getIdTipoEntidad());

                                        session.setAttribute("CMB_TIPODOCUMENTO", (ArrayList) resultado2.get(1));
                                        session.setAttribute("CMB_PAIS", (ArrayList) resultado3.get(1));
                                        session.setAttribute("CMB_DEPARTAMENTO", (ArrayList) resultado4.get(1));
                                        session.setAttribute("CMB_MUNICIPIO", (ArrayList) resultado5.get(1));
                                        session.setAttribute("CMB_TIPOENTIDAD", (ArrayList) resultado6.get(1));
                                        return mapping.findForward("modificar");

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

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
                    fo.setbIdEntidad((String) session.getAttribute("getbIdEntidad"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                    fo.setbIdTipoDocumento((String) session.getAttribute("getbIdTipoDocumento"));
                    fo.setbIdentificacion((String) session.getAttribute("getbIdentificacion"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = g.MostrarEntidad(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.MostrarTipoDocumento(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        session.setAttribute("getbIdEntidad", fo.getbIdEntidad());
                        session.setAttribute("getbNombre", fo.getbNombre());
                        session.setAttribute("getbIdTipoDocumento", fo.getbIdTipoDocumento());
                        session.setAttribute("getbIdentificacion", fo.getbIdentificacion());

                        session.setAttribute("GR_ENTIDAD", resultado.get(1));

                        session.setAttribute("CMB_TIPODOCUMENTO", (ArrayList) resultado2.get(1));
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

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarTipoDocumento(false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gP.MostrarPais(false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gD.MostrarDepartamento("", false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            resultado4 = gM.MostrarMunicipio("", "", false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gTE.MostrarTipoEntidad(false, null);
                                if ((Boolean) resultado5.get(0) == false) {

                                    request.setAttribute("getIdEntidad", "");
                                    request.setAttribute("getPrimerNombre", "");
                                    request.setAttribute("getSegundoNombre", "");
                                    request.setAttribute("getPrimerApellido", "");
                                    request.setAttribute("getSegundoApellido", "");
                                    request.setAttribute("getIdTipoDocumento", "");
                                    request.setAttribute("getIdentificacion", "");
                                    request.setAttribute("getRazonSocial", "");
                                    request.setAttribute("getIdPais", "");
                                    request.setAttribute("getIdMunicipio", "");
                                    request.setAttribute("getDireccion", "");
                                    request.setAttribute("getTelefono", "");
                                    request.setAttribute("getEmail", "");
                                    request.setAttribute("getIdTipoEntidad", "");

                                    session.setAttribute("CMB_TIPODOCUMENTO", (ArrayList) resultado.get(1));
                                    session.setAttribute("CMB_PAIS", (ArrayList) resultado2.get(1));
                                    session.setAttribute("CMB_DEPARTAMENTO", (ArrayList) resultado3.get(1));
                                    session.setAttribute("CMB_MUNICIPIO", (ArrayList) resultado4.get(1));
                                    session.setAttribute("CMB_TIPOENTIDAD", (ArrayList) resultado5.get(1));
                                    return mapping.findForward("nuevo");

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }


                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }


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

        } else {

            session.setAttribute("getbIdEntidad", "");
            session.setAttribute("getbNombre", "");
            session.setAttribute("getbIdTipoDocumento", "");
            session.setAttribute("getbIdentificacion", "");
            fo.setbNombre("");
            fo.setbIdTipoDocumento("");
            fo.setbIdentificacion("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = g.MostrarEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = gr.MostrarTipoDocumento(false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    session.setAttribute("GR_ENTIDAD", resultado.get(1));

                    session.setAttribute("CMB_TIPODOCUMENTO", (ArrayList) resultado2.get(1));
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
