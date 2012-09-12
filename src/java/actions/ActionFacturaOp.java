/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.FacturaOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionEntidad;
import modelo.GestionFactura;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionFacturaOp extends Action {

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
    public ActionFacturaOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        FacturaOpForm fo = (FacturaOpForm) form;
        GestionFactura gr = new GestionFactura();
        GestionEntidad grEntidad = new GestionEntidad();
//        GestionPlantillaDispositivoHija grHija = new GestionPlantillaDispositivoHija();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpFactura  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado;
                resultado = gr.MostrarFacturaFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdFactura", gr.getIdFactura());
                    request.setAttribute("getIdEntidad", gr.getIdEntidad());
                    request.setAttribute("getNumFactura", gr.getNumFactura());
                    request.setAttribute("getFecha", gr.getFecha());
                    request.setAttribute("getTotal", gr.getTotal());
                    session.setAttribute("getIdFactura", gr.getIdFactura());

//                    ArrayList<Object> GR_CARACTERISTICAPLANTILLA;
//                    ArrayList<Object> GR_PlantillaDisponible;
//                    ArrayList<Object> GR_PlantillaHija;
//
//                    ArrayList<Object> resultado2;
//                    resultado2 = grCaract.MostrarCaracteristicaPlantilla(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                    if ((Boolean) resultado2.get(0) == false) {
//
//                        GR_CARACTERISTICAPLANTILLA = (ArrayList) resultado2.get(1);
//
//                        ArrayList<Object> resultado3;
//                        resultado3 = gr.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                        if ((Boolean) resultado3.get(0) == false) {
//
//                            GR_PlantillaDisponible = (ArrayList) resultado3.get(1);
//
//                            ArrayList<Object> resultado4;
//                            resultado4 = grHija.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                            if ((Boolean) resultado4.get(0) == false) {
//
//                                GR_PlantillaHija = (ArrayList) resultado4.get(1);
//
//                                session.setAttribute("GR_CARACTERISTICAPLANTILLA", GR_CARACTERISTICAPLANTILLA);
//                                session.setAttribute("GR_PlantillaDisponible", GR_PlantillaDisponible);
//                                session.setAttribute("GR_PlantillaHija", GR_PlantillaHija);

                    return mapping.findForward("modificar");

//                            } else {
//
//                                request.setAttribute("error", resultado4.get(1));
//                                return mapping.findForward("error");
//
//                            }
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

                if (fo.getbIdEntidad() != null) {
                    session.setAttribute("getbIdEntidad", fo.getbIdEntidad());
                    session.setAttribute("getbNumFactura", fo.getbNumFactura());
                    session.setAttribute("getbFecha", fo.getbFecha());
                } else {
                    fo.setbIdEntidad((String) session.getAttribute("getbIdEntidad"));
                    fo.setbNumFactura((String) session.getAttribute("getbNumFactura"));
                    fo.setbFecha((String) session.getAttribute("getbFecha"));
                }

                ArrayList<Object> resultado;
                resultado = gr.MostrarFacturaOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("GR_FACTURA", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdFactura", "");
                request.setAttribute("getIdEntidad", "");
                request.setAttribute("getNumFactura", "");
                request.setAttribute("getFecha", "");
                request.setAttribute("getTotal", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdFactura", "");
            session.setAttribute("getbIdEntidad", "");
            session.setAttribute("getbNumFactura", "");
            session.setAttribute("getbFecha", "");
            fo.setbIdEntidad("");
            fo.setbNumFactura("");
            fo.setbFecha("");

            ArrayList<Object> resultado;
            resultado = gr.MostrarFacturaOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado1;
                resultado1 = grEntidad.MostrarEntidad(false, null);
                if ((Boolean) resultado1.get(0) == false) {

                    session.setAttribute("CMB_ENTIDAD", resultado1.get(1));
                    session.setAttribute("GR_FACTURA", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
