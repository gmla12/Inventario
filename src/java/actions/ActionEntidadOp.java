/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.EntidadOpForm;
import forms.bean.BeanTablas;
import forms.bean.BeanTipoDocumento;
import forms.bean.BeanTipoDocumentoAut;
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
        GestionTipoDocumentoAut grAut = new GestionTipoDocumentoAut();
        GestionTablas grT = new GestionTablas();
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

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
                                        resultado7 = grAut.MostrarTipoDocumentoAut(false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            ArrayList<Object> GR_TIPODOCUMENTOAUT = (ArrayList) resultado7.get(1);
                                            ArrayList<Object> GR_TABLAS = new ArrayList<Object>();
                                            ArrayList<Object> GR_TIPODOCUMENTO = new ArrayList<Object>();

                                            ArrayList<Object> resultado8 = new ArrayList<Object>();
                                            resultado8 = grT.MostrarTablas("Entidad", false, null);
                                            if ((Boolean) resultado8.get(0) == false) {

                                                GR_TIPODOCUMENTO = (ArrayList) resultado2.get(1);
                                                GR_TABLAS = (ArrayList) resultado8.get(1);
                                                ArrayList<Object> GR_AUT = new ArrayList<Object>();

                                                BeanTablas buTablas;
                                                BeanTipoDocumento buTipoDocumento;
                                                BeanTipoDocumentoAut buTipoDocumentoAut;
                                                BeanTipoDocumentoAut buTipoDocumentoAut2;

                                                for (int z = 0; z < GR_TIPODOCUMENTO.size(); z++) {

                                                    buTipoDocumento = new BeanTipoDocumento();
                                                    buTipoDocumento = (BeanTipoDocumento) GR_TIPODOCUMENTO.get(z);

                                                    for (int i = 0; i < GR_TABLAS.size(); i++) {

                                                        buTablas = new BeanTablas();
                                                        buTablas = (BeanTablas) GR_TABLAS.get(i);
                                                        int op = 0;

                                                        if (GR_TIPODOCUMENTOAUT.size() > 0) {
                                                            for (int y = 0; y < GR_TIPODOCUMENTOAUT.size(); y++) {

                                                                buTipoDocumentoAut = new BeanTipoDocumentoAut();
                                                                buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_TIPODOCUMENTOAUT.get(y);

                                                                buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                                                                if (String.valueOf(buTipoDocumentoAut.getCampo()).equals(String.valueOf(buTablas.getField())) && String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(buTipoDocumento.getIdTipoDocumento()))) {

                                                                    buTipoDocumentoAut2.setIdTipoDocumento(buTipoDocumentoAut.getIdTipoDocumento());
                                                                    buTipoDocumentoAut2.setCampo(buTipoDocumentoAut.getCampo());
                                                                    buTipoDocumentoAut2.setHabilitar(buTipoDocumentoAut.getHabilitar());
                                                                    buTipoDocumentoAut2.setObligatorio(buTipoDocumentoAut.getObligatorio());

                                                                    GR_AUT.add(buTipoDocumentoAut2);

                                                                    op = 1;

                                                                }

                                                                if (op == 1) {
                                                                    y = GR_TIPODOCUMENTOAUT.size();
                                                                }

                                                            }
                                                            if (op == 0) {
                                                                buTipoDocumentoAut2 = new BeanTipoDocumentoAut();

                                                                buTipoDocumentoAut2.setIdTipoDocumento(buTipoDocumento.getIdTipoDocumento());
                                                                buTipoDocumentoAut2.setCampo(buTablas.getField());
                                                                buTipoDocumentoAut2.setHabilitar(true);
                                                                buTipoDocumentoAut2.setObligatorio(true);

                                                                GR_AUT.add(buTipoDocumentoAut2);
                                                            }

                                                        } else {

                                                            buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                                                            buTipoDocumentoAut2.setIdTipoDocumento(gr.getIdTipoDocumento());
                                                            buTipoDocumentoAut2.setCampo(buTablas.getField());
                                                            buTipoDocumentoAut2.setHabilitar(true);
                                                            buTipoDocumentoAut2.setObligatorio(true);

                                                            GR_AUT.add(buTipoDocumentoAut2);

                                                        }

                                                    }

                                                }
                                                for (int y = 0; y < GR_AUT.size(); y++) {

                                                    buTipoDocumentoAut = new BeanTipoDocumentoAut();
                                                    buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_AUT.get(y);

                                                    if (String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(g.getIdTipoDocumento()))) {

                                                        request.setAttribute("Hab" + (String) buTipoDocumentoAut.getCampo(), buTipoDocumentoAut.getHabilitar());

                                                    }

                                                }

                                                request.setAttribute("getIdEntidad", g.getIdEntidad());
                                                request.setAttribute("getIdTipoDocumento", g.getIdTipoDocumento());
                                                request.setAttribute("getIdentificacion", g.getIdentificacion());
                                                Object o;
                                                if (g.getPrimerNombre()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getPrimerNombre();
                                                }
                                                request.setAttribute("getPrimerNombre", o);
                                                if (g.getSegundoNombre()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getSegundoNombre();
                                                }                                                
                                                request.setAttribute("getSegundoNombre", o);
                                                if (g.getPrimerApellido()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getPrimerApellido();
                                                } 
                                                request.setAttribute("getPrimerApellido", o);
                                                if (g.getSegundoApellido()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getSegundoApellido();
                                                } 
                                                request.setAttribute("getSegundoApellido", o);
                                                if (g.getRazonSocial()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getRazonSocial();
                                                } 
                                                request.setAttribute("getRazonSocial", o);
                                                if (g.getIdPais()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getIdPais();
                                                } 
                                                request.setAttribute("getIdPais", o);
                                                if (g.getIdDepartamento()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getIdDepartamento();
                                                } 
                                                request.setAttribute("getIdDepartamento", o);
                                                if (g.getIdMunicipio()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getIdMunicipio();
                                                } 
                                                request.setAttribute("getIdMunicipio", o);
                                                if (g.getDireccion()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getDireccion();
                                                } 
                                                request.setAttribute("getDireccion", o);
                                                if (g.getTelefono()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getTelefono();
                                                } 
                                                request.setAttribute("getTelefono", o);
                                                if (g.getEmail()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getEmail();
                                                } 
                                                request.setAttribute("getEmail", o);
                                                if (g.getIdTipoEntidad()==null){
                                                    o = "";
                                                }else{
                                                    o = g.getIdTipoEntidad();
                                                } 
                                                request.setAttribute("getIdTipoEntidad", o);

                                                session.setAttribute("CMB_TIPODOCUMENTO", (ArrayList) resultado2.get(1));
                                                session.setAttribute("CMB_PAIS", (ArrayList) resultado3.get(1));
                                                session.setAttribute("CMB_DEPARTAMENTO", (ArrayList) resultado4.get(1));
                                                session.setAttribute("CMB_MUNICIPIO", (ArrayList) resultado5.get(1));
                                                session.setAttribute("CMB_TIPOENTIDAD", (ArrayList) resultado6.get(1));
                                                session.setAttribute("CMB_TIPODOCUMENTOAUT", (ArrayList) resultado7.get(1));
                                                session.setAttribute("CMB_TIPODOCUMENTOAUT", GR_AUT);
                                                return mapping.findForward("modificar");

                                            } else {

                                                request.setAttribute("error", resultado8.get(1));
                                                return mapping.findForward("error");

                                            }

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

                                    ArrayList<Object> resultado7 = new ArrayList<Object>();
                                    resultado7 = grAut.MostrarTipoDocumentoAut(false, null);
                                    if ((Boolean) resultado7.get(0) == false) {

                                        ArrayList<Object> GR_TIPODOCUMENTOAUT = (ArrayList) resultado7.get(1);
                                        ArrayList<Object> GR_TABLAS = new ArrayList<Object>();
                                        ArrayList<Object> GR_TIPODOCUMENTO = new ArrayList<Object>();

                                        ArrayList<Object> resultado8 = new ArrayList<Object>();
                                        resultado8 = grT.MostrarTablas("Entidad", false, null);
                                        if ((Boolean) resultado8.get(0) == false) {

                                            GR_TIPODOCUMENTO = (ArrayList) resultado.get(1);
                                            GR_TABLAS = (ArrayList) resultado8.get(1);
                                            ArrayList<Object> GR_AUT = new ArrayList<Object>();

                                            BeanTablas buTablas;
                                            BeanTipoDocumento buTipoDocumento;
                                            BeanTipoDocumentoAut buTipoDocumentoAut;
                                            BeanTipoDocumentoAut buTipoDocumentoAut2;

                                            for (int z = 0; z < GR_TIPODOCUMENTO.size(); z++) {

                                                buTipoDocumento = new BeanTipoDocumento();
                                                buTipoDocumento = (BeanTipoDocumento) GR_TIPODOCUMENTO.get(z);

                                                for (int i = 0; i < GR_TABLAS.size(); i++) {

                                                    buTablas = new BeanTablas();
                                                    buTablas = (BeanTablas) GR_TABLAS.get(i);
                                                    int op = 0;

                                                    if (GR_TIPODOCUMENTOAUT.size() > 0) {
                                                        for (int y = 0; y < GR_TIPODOCUMENTOAUT.size(); y++) {

                                                            buTipoDocumentoAut = new BeanTipoDocumentoAut();
                                                            buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_TIPODOCUMENTOAUT.get(y);

                                                            buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                                                            if (String.valueOf(buTipoDocumentoAut.getCampo()).equals(String.valueOf(buTablas.getField())) && String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(buTipoDocumento.getIdTipoDocumento()))) {

                                                                buTipoDocumentoAut2.setIdTipoDocumento(buTipoDocumentoAut.getIdTipoDocumento());
                                                                buTipoDocumentoAut2.setCampo(buTipoDocumentoAut.getCampo());
                                                                buTipoDocumentoAut2.setHabilitar(buTipoDocumentoAut.getHabilitar());
                                                                buTipoDocumentoAut2.setObligatorio(buTipoDocumentoAut.getObligatorio());

                                                                GR_AUT.add(buTipoDocumentoAut2);

                                                                op = 1;

                                                            }

                                                            if (op == 1) {
                                                                y = GR_TIPODOCUMENTOAUT.size();
                                                            }

                                                        }
                                                        if (op == 0) {
                                                            buTipoDocumentoAut2 = new BeanTipoDocumentoAut();

                                                            buTipoDocumentoAut2.setIdTipoDocumento(buTipoDocumento.getIdTipoDocumento());
                                                            buTipoDocumentoAut2.setCampo(buTablas.getField());
                                                            buTipoDocumentoAut2.setHabilitar(true);
                                                            buTipoDocumentoAut2.setObligatorio(true);

                                                            GR_AUT.add(buTipoDocumentoAut2);
                                                        }

                                                    } else {

                                                        buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                                                        buTipoDocumentoAut2.setIdTipoDocumento(gr.getIdTipoDocumento());
                                                        buTipoDocumentoAut2.setCampo(buTablas.getField());
                                                        buTipoDocumentoAut2.setHabilitar(true);
                                                        buTipoDocumentoAut2.setObligatorio(true);

                                                        GR_AUT.add(buTipoDocumentoAut2);

                                                    }

                                                }

                                            }
                                            buTipoDocumentoAut2 = (BeanTipoDocumentoAut) GR_AUT.get(0);
                                            for (int y = 0; y < GR_AUT.size(); y++) {

                                                buTipoDocumentoAut = new BeanTipoDocumentoAut();
                                                buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_AUT.get(y);

                                                if (String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(buTipoDocumentoAut2.getIdTipoDocumento()))) {

                                                    request.setAttribute("Hab" + (String) buTipoDocumentoAut.getCampo(), true);

                                                }

                                            }

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
                                            session.setAttribute("CMB_TIPODOCUMENTOAUT", GR_AUT);
                                            return mapping.findForward("nuevo");

                                        } else {

                                            request.setAttribute("error", resultado8.get(1));
                                            return mapping.findForward("error");

                                        }

                                    } else {

                                        request.setAttribute("error", resultado7.get(1));
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
