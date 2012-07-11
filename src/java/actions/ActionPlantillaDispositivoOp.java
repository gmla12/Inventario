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
import modelo.GestionCaracteristicaPlantilla;
import modelo.GestionPlantillaDispositivo;
import modelo.GestionPlantillaDispositivoHija;
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
        GestionCaracteristicaPlantilla grCaract = new GestionCaracteristicaPlantilla();
        GestionPlantillaDispositivoHija grHija = new GestionPlantillaDispositivoHija();
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

                ArrayList<Object> resultado;
                resultado = gr.MostrarPlantillaDispositivoFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdPlantillaDispositivo", gr.getIdPlantillaDispositivo());
                    request.setAttribute("getNombre", gr.getNombre());
                    request.setAttribute("getDescripcion", gr.getDescripcion());
                    request.setAttribute("getHija", gr.getHija());
                    session.setAttribute("getIdPlantillaDispositivo", gr.getIdPlantillaDispositivo());

                    ArrayList<Object> GR_CARACTERISTICAPLANTILLA;
                    ArrayList<Object> GR_PlantillaDisponible;
                    ArrayList<Object> GR_PlantillaHija;

                    ArrayList<Object> resultado2;
                    resultado2 = grCaract.MostrarCaracteristicaPlantilla(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        GR_CARACTERISTICAPLANTILLA = (ArrayList) resultado2.get(1);

                        ArrayList<Object> resultado3;
                        resultado3 = gr.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            GR_PlantillaDisponible = (ArrayList) resultado3.get(1);

                            ArrayList<Object> resultado4;
                            resultado4 = grHija.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                GR_PlantillaHija = (ArrayList) resultado4.get(1);

                                session.setAttribute("GR_CARACTERISTICAPLANTILLA", GR_CARACTERISTICAPLANTILLA);
                                session.setAttribute("GR_PlantillaDisponible", GR_PlantillaDisponible);
                                session.setAttribute("GR_PlantillaHija", GR_PlantillaHija);

                                return mapping.findForward("modificar");

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

                if (fo.getbNombre() != null) {
                    session.setAttribute("getbNombre", fo.getbNombre());
                    session.setAttribute("getbHija", fo.getbHija());
                } else {
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                    fo.setbHija((String) session.getAttribute("getbHija"));
                }

                ArrayList<Object> resultado;
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
                request.setAttribute("getDescripcion", "");
                request.setAttribute("getHija", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPlantillaDispositivo", "");
            session.setAttribute("getbNombre", "");
            session.setAttribute("getbHija", "");
            fo.setbNombre("");
            fo.setbHija("");

            ArrayList<Object> resultado;
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
