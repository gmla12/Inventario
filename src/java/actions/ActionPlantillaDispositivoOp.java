/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.PlantillaDispositivoOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionPlantillaDispositivo;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionPlantillaDispositivoOp extends Action {

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
    public ActionPlantillaDispositivoOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PlantillaDispositivoOpForm fo = (PlantillaDispositivoOpForm) form;
        GestionPlantillaDispositivo gr = new GestionPlantillaDispositivo();
//        GestionTipoDocumentoAut grAut = new GestionTipoDocumentoAut();
//        GestionTablas grT = new GestionTablas();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpPlantillaDispositivo  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPlantillaDispositivoFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdPlantillaDispositivo", gr.getIdPlantillaDispositivo());
                    request.setAttribute("getNombre", gr.getNombre());
                    request.setAttribute("getDescripcion", gr.getDescripcion());
                    request.setAttribute("getHija", gr.getHija());
                    session.setAttribute("getIdPlantillaDispositivo", gr.getIdPlantillaDispositivo());
                    return mapping.findForward("modificar");

//                    ArrayList<Object> GR_TIPODOCUMENTOAUT = new ArrayList<Object>();
//
//                    ArrayList<Object> resultado2 = new ArrayList<Object>();
//                    resultado2 = grAut.MostrarTipoDocumentoAut(Integer.valueOf(String.valueOf(gr.getIdTipoDocumento())), false, null);
//                    if ((Boolean) resultado2.get(0) == false) {
//
//                        GR_TIPODOCUMENTOAUT = (ArrayList) resultado2.get(1);
//
//                        ArrayList<Object> GR_TABLAS = new ArrayList<Object>();
//
//                        ArrayList<Object> resultado3 = new ArrayList<Object>();
//                        resultado3 = grT.MostrarTablas("Entidad", false, null);
//                        if ((Boolean) resultado3.get(0) == false) {
//
//                            GR_TABLAS = (ArrayList) resultado3.get(1);
//
//                            ArrayList<Object> GR_AUT = new ArrayList<Object>();
//
//                            BeanTablas buTablas;
//                            BeanTipoDocumentoAut buTipoDocumentoAut;
//                            BeanTipoDocumentoAut buTipoDocumentoAut2;
//
//                            for (int i = 0; i < GR_TABLAS.size(); i++) {
//
//                                buTablas = new BeanTablas();
//                                buTablas = (BeanTablas) GR_TABLAS.get(i);
//                                int op = 0;
//
//                                if (GR_TIPODOCUMENTOAUT.size() > 0) {
//                                    for (int y = 0; y < GR_TIPODOCUMENTOAUT.size(); y++) {
//
//                                        buTipoDocumentoAut = new BeanTipoDocumentoAut();
//                                        buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_TIPODOCUMENTOAUT.get(y);
//
//                                        buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
//                                        if (String.valueOf(buTipoDocumentoAut.getCampo()).equals(String.valueOf(buTablas.getField()))) {
//
//                                            buTipoDocumentoAut2.setIdTipoDocumento(buTipoDocumentoAut.getIdTipoDocumento());
//                                            buTipoDocumentoAut2.setCampo(buTipoDocumentoAut.getCampo());
//                                            buTipoDocumentoAut2.setHabilitar(buTipoDocumentoAut.getHabilitar());
//                                            buTipoDocumentoAut2.setObligatorio(buTipoDocumentoAut.getObligatorio());
//
//                                            GR_AUT.add(buTipoDocumentoAut2);
//
//                                            op = 1;
//
//                                        }
//
//                                        if (op == 1) {
//                                            y = GR_TIPODOCUMENTOAUT.size();
//                                        }
//
//                                    }
//                                    if (op == 0) {
//                                        buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
//
//                                        buTipoDocumentoAut2.setIdTipoDocumento(gr.getIdTipoDocumento());
//                                        buTipoDocumentoAut2.setCampo(buTablas.getField());
//                                        buTipoDocumentoAut2.setHabilitar(true);
//                                        buTipoDocumentoAut2.setObligatorio(true);
//
//                                        GR_AUT.add(buTipoDocumentoAut2);
//                                    }
//
//                                } else {
//
//                                    buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
//                                    buTipoDocumentoAut2.setIdTipoDocumento(gr.getIdTipoDocumento());
//                                    buTipoDocumentoAut2.setCampo(buTablas.getField());
//                                    buTipoDocumentoAut2.setHabilitar(true);
//                                    buTipoDocumentoAut2.setObligatorio(true);
//
//                                    GR_AUT.add(buTipoDocumentoAut2);
//
//                                }
//
//                            }
//
//                            session.setAttribute("GR_TIPODOCUMENTOAUT", GR_AUT);
//
//                            return mapping.findForward("modificar");
//
//                        } else {
//
//                            request.setAttribute("error", resultado3.get(1));
//                            return mapping.findForward("error");
//
//                        }
//
//                    } else {
//
//                        request.setAttribute("error", resultado2.get(1));
//                        return mapping.findForward("error");
//
//                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() != null) {
                    session.setAttribute("getbIdPlantillaDispositivo", fo.getbIdPlantillaDispositivo());
                    session.setAttribute("getbNombre", fo.getbNombre());
                    session.setAttribute("getbHija", fo.getbHija());
                } else {
                    fo.setbIdPlantillaDispositivo((String) session.getAttribute("getbIdPlantillaDispositivo"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                    fo.setbHija((String) session.getAttribute("getbHija"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPlantillaDispositivoOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("GR_PLANTILLADISPOSITIVO", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdPlantillaDispositivo", "");
                request.setAttribute("getNombre", "");
                request.setAttribute("getDescipcion", "");
                request.setAttribute("getHija", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPlantillaDispositivo", "");
            session.setAttribute("getbNombre", "");
            session.setAttribute("getbHija", "");
            fo.setbNombre("");
            fo.setbHija("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarPlantillaDispositivoOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                session.setAttribute("GR_PLANTILLADISPOSITIVO", resultado.get(1));
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
