/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.InicioForm;
import forms.bean.BeanUsuarios;
import modelo.GestionUsuarios;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class InicioAction extends Action {

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
    public InicioAction() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        InicioForm fo = (InicioForm) form;
        HttpSession sesionOk = request.getSession();

        System.out.println("Action");

        GestionUsuarios g = new GestionUsuarios();

        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = g.BuscarUsuarios(fo, false, null);
        if ((Boolean) resultado.get(0) == false) {
            
            BeanUsuarios c = (BeanUsuarios) resultado.get(1);

            if (c.getIdRol() == null) {
                fo.setMensaje("<h2>Usuario y Password Incorrecta!</h2>");
                return mapping.findForward("mal");
            } else {
                sesionOk.setAttribute("usuario", c.getLogin());
                sesionOk.setAttribute("nombre", c.getNombre());
                sesionOk.setAttribute("rol", c.getNombreRol());

                return mapping.findForward("ok");
            }
            
        }
        else{
            
            request.setAttribute("error", resultado.get(1));
            return mapping.findForward("error");
            
        }
        
//            if (fo.getUsuario().equals("admin2010") && fo.getPassw().equals("2010")) {
//
//                session.setAttribute("usuario", fo.getUsuario());
//                session.setAttribute("nombre", "administrador");
//
//                return mapping.findForward("ok");
//
//            } else {
//                fo.setMensaje("<h2>Usuario y Password Incorrecta!</h2>");
//                return mapping.findForward("error");
//            }



    }
}
