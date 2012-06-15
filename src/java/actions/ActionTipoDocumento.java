/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.TipoDocumentoForm;
import forms.bean.BeanTipoDocumentoAut;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionTipoDocumento;
import modelo.GestionTipoDocumentoAut;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionTipoDocumento extends Action {

    public ActionTipoDocumento() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        TipoDocumentoForm fo = (TipoDocumentoForm) form;
        GestionTipoDocumento gr = new GestionTipoDocumento();
        GestionTipoDocumentoAut grAut = new GestionTipoDocumentoAut();
        HttpSession session = request.getSession();

        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaTipoDocumento(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Tipo Documento");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Tipo Documento");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getNombre", fo.getNombre());
            ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("GR_TIPODOCUMENTOAUT");
            BeanTipoDocumentoAut buTipoDocumentoAut1;
            BeanTipoDocumentoAut buTipoDocumentoAut2;

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = (ArrayList) grAut.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                Connection cn = (Connection) resultado.get(1);

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = (ArrayList) grAut.autoCommint(false, cn);
                if ((Boolean) resultado2.get(0) == false) {

                    for (int i = 0; i < GR_AUT.size(); i++) {

                        buTipoDocumentoAut1 = new BeanTipoDocumentoAut();
                        buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                        buTipoDocumentoAut2 = (BeanTipoDocumentoAut) GR_AUT.get(i);

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = (ArrayList) grAut.MostrarTipoDocumentoAnt(Integer.valueOf(fo.getIdTipoDocumento()), String.valueOf(buTipoDocumentoAut2.getCampo()), true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            buTipoDocumentoAut1 = (BeanTipoDocumentoAut) resultado3.get(1);

                            if (String.valueOf(buTipoDocumentoAut1.getCampo()).equals(String.valueOf(buTipoDocumentoAut2.getCampo()))) {

                                ArrayList<Object> resultado4 = new ArrayList<Object>();
                                resultado4 = (ArrayList) grAut.ModificaTipoDocumentoAnt(Integer.valueOf(fo.getIdTipoDocumento()), String.valueOf(buTipoDocumentoAut2.getCampo()), Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getHabilitar())), Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getObligatorio())), true, cn);
                                if ((Boolean) resultado4.get(0) == true) {

                                    request.setAttribute("error", resultado4.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                ArrayList<Object> resultado4 = new ArrayList<Object>();
                                resultado4 = (ArrayList) grAut.IngresaTipoDocumentoAut(Integer.valueOf(fo.getIdTipoDocumento()), String.valueOf(buTipoDocumentoAut2.getCampo()), Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getHabilitar())), Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getObligatorio())), true, cn);
                                if ((Boolean) resultado4.get(0) == true) {

                                    request.setAttribute("error", resultado4.get(1));
                                    return mapping.findForward("error");

                                }

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    }

                    ArrayList<Object> resultado5 = new ArrayList<Object>();
                    resultado5 = (ArrayList) gr.ModificaTipoDocumento(fo, true, cn);
                    if ((Boolean) resultado5.get(0) == false) {

                        ArrayList<Object> resultado6 = new ArrayList<Object>();
                        resultado6 = (ArrayList) grAut.commint(cn);
                        if ((Boolean) resultado6.get(0) == false) {

                            ArrayList<Object> resultado7 = new ArrayList<Object>();
                            resultado7 = (ArrayList) grAut.autoCommint(true, cn);
                            if ((Boolean) resultado7.get(0) == false) {

                                request.setAttribute("respuesta", "Registro modificado correctamente.");
                                System.out.println("Action Modicar Tipo Documento");

                                return mapping.findForward("ok");

                            } else {

                                request.setAttribute("error", resultado7.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado6.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado5.get(1));
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

        } else if (fo.getOp().equals("eliminar")) {

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = (ArrayList) grAut.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                Connection cn = (Connection) resultado.get(1);

                ArrayList<Object> resultado2 = new ArrayList<Object>();
                resultado2 = (ArrayList) grAut.autoCommint(false, cn);
                if ((Boolean) resultado2.get(0) == false) {

                    ArrayList<Object> resultado3 = new ArrayList<Object>();
                    resultado3 = (ArrayList) gr.EliminaTipoDocumento(fo, true, cn);

                    if ((Boolean) resultado3.get(0) == false) {

                        ArrayList<Object> resultado4 = new ArrayList<Object>();
                        resultado4 = (ArrayList) grAut.EliminaTipoDocumentoAut(Integer.valueOf(fo.getIdTipoDocumento()), true, cn);

                        if ((Boolean) resultado4.get(0) == false) {

                            ArrayList<Object> resultado5 = new ArrayList<Object>();
                            resultado5 = (ArrayList) grAut.commint(cn);

                            if ((Boolean) resultado5.get(0) == false) {

                                ArrayList<Object> resultado6 = new ArrayList<Object>();
                                resultado6 = (ArrayList) grAut.autoCommint(true, cn);

                                if ((Boolean) resultado6.get(0) == false) {

                                    request.setAttribute("getIdTipoDocumento", "");
                                    request.setAttribute("getNombre", "");
                                    session.setAttribute("GR_TIPODOCUMENTOAUT", null);

                                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                    System.out.println("Action Eliminar Tipo Documento");

                                    return mapping.findForward("ok");

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

        } else {

            request.setAttribute("getOp", "buscar");
            return mapping.findForward("atras");

        }

    }
}
