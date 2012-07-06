/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.TipoEntidadForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionTipoEntidad;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionTipoEntidad extends Action {

    public ActionTipoEntidad() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        TipoEntidadForm fo = (TipoEntidadForm) form;
        GestionTipoEntidad gr = new GestionTipoEntidad();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaTipoEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdTipoEntidad", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso TipoEntidad");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso TipoEntidad");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaTipoEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar TipoEntidad");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar TipoEntidad");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaTipoEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar TipoEntidad");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar TipoEntidad");
                }
                return mapping.findForward("ok");
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
