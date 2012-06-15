/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.PaisForm;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionPais;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionPais extends Action {

    public ActionPais() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PaisForm fo = (PaisForm) form;
        GestionPais gr = new GestionPais();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaPais(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Pais");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Pais");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaPais(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Pais");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar Pais");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaPais(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Usuarios");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Usuarios");
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
