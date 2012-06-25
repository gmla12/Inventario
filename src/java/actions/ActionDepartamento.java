/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.DepartamentoForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionDepartamento;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionDepartamento extends Action {

    public ActionDepartamento() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        DepartamentoForm fo = (DepartamentoForm) form;
        GestionDepartamento gr = new GestionDepartamento();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaDepartamento(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Departamento");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Departamento");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaDepartamento(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Departamento");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar Departamento");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdDepartamento", "");
            request.setAttribute("getIdPais", "");
            request.setAttribute("getNombre", "");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaDepartamento(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Departamento");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Departamento");
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
