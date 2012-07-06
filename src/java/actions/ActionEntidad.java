/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.EntidadForm;
import forms.bean.BeanTipoDocumentoAut;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionEntidad;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionEntidad extends Action {

    public ActionEntidad() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        EntidadForm fo = (EntidadForm) form;
        GestionEntidad gr = new GestionEntidad();
        HttpSession session = request.getSession();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdEntidad", fo.getIdEntidad());
            request.setAttribute("getPrimerNombre", fo.getPrimerNombre());
            request.setAttribute("getSegundoNombre", fo.getSegundoNombre());
            request.setAttribute("getPrimerApellido", fo.getPrimerApellido());
            request.setAttribute("getSegundoApellido", fo.getSegundoApellido());
            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getIdentificacion", fo.getIdentificacion());
            request.setAttribute("getRazonSocial", fo.getRazonSocial());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
            request.setAttribute("getDireccion", fo.getDireccion());
            request.setAttribute("getTelefono", fo.getTelefono());
            request.setAttribute("getEmail", fo.getEmail());
            request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());
            ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
            BeanTipoDocumentoAut buTipoDocumentoAut;
            for (int y = 0; y < GR_AUT.size(); y++) {

                buTipoDocumentoAut = new BeanTipoDocumentoAut();
                buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_AUT.get(y);

                if (String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(fo.getIdTipoDocumento()))) {

                    request.setAttribute("Hab" + (String) buTipoDocumentoAut.getCampo(), buTipoDocumentoAut.getHabilitar());

                }

            }
            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdEntidad", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Entidad");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Entidad");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdEntidad", fo.getIdEntidad());
            request.setAttribute("getPrimerNombre", fo.getPrimerNombre());
            request.setAttribute("getSegundoNombre", fo.getSegundoNombre());
            request.setAttribute("getPrimerApellido", fo.getPrimerApellido());
            request.setAttribute("getSegundoApellido", fo.getSegundoApellido());
            request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
            request.setAttribute("getIdentificacion", fo.getIdentificacion());
            request.setAttribute("getRazonSocial", fo.getRazonSocial());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
            request.setAttribute("getDireccion", fo.getDireccion());
            request.setAttribute("getTelefono", fo.getTelefono());
            request.setAttribute("getEmail", fo.getEmail());
            request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());

            ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
            BeanTipoDocumentoAut buTipoDocumentoAut;
            for (int y = 0; y < GR_AUT.size(); y++) {

                buTipoDocumentoAut = new BeanTipoDocumentoAut();
                buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_AUT.get(y);

                if (String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(fo.getIdTipoDocumento()))) {

                    request.setAttribute("Hab" + (String) buTipoDocumentoAut.getCampo(), buTipoDocumentoAut.getHabilitar());

                }

            }
            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modificar Entidad");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modificar Entidad");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaEntidad(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
                    BeanTipoDocumentoAut buTipoDocumentoAut;
                    BeanTipoDocumentoAut buTipoDocumentoAut2;
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
                    request.setAttribute("getIdDepartamento", "");
                    request.setAttribute("getIdMunicipio", "");
                    request.setAttribute("getDireccion", "");
                    request.setAttribute("getTelefono", "");
                    request.setAttribute("getEmail", "");
                    request.setAttribute("getIdTipoEntidad", "");
                    session.setAttribute("CMB_DEPARTAMENTO", "");
                    session.setAttribute("CMB_MUNICIPIO", "");

                    request.setAttribute("respuesta", "Registro elimidado correctamente.");
                    System.out.println("Action Eliminar Entidad");

                } else {

                    request.setAttribute("getIdEntidad", fo.getIdEntidad());
                    request.setAttribute("getPrimerNombre", fo.getPrimerNombre());
                    request.setAttribute("getSegundoNombre", fo.getSegundoNombre());
                    request.setAttribute("getPrimerApellido", fo.getPrimerApellido());
                    request.setAttribute("getSegundoApellido", fo.getSegundoApellido());
                    request.setAttribute("getIdTipoDocumento", fo.getIdTipoDocumento());
                    request.setAttribute("getIdentificacion", fo.getIdentificacion());
                    request.setAttribute("getRazonSocial", fo.getRazonSocial());
                    request.setAttribute("getIdPais", fo.getIdPais());
                    request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
                    request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
                    request.setAttribute("getDireccion", fo.getDireccion());
                    request.setAttribute("getTelefono", fo.getTelefono());
                    request.setAttribute("getEmail", fo.getEmail());
                    request.setAttribute("getIdTipoEntidad", fo.getIdTipoEntidad());
                    ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
                    BeanTipoDocumentoAut buTipoDocumentoAut;
                    for (int y = 0; y < GR_AUT.size(); y++) {

                        buTipoDocumentoAut = new BeanTipoDocumentoAut();
                        buTipoDocumentoAut = (BeanTipoDocumentoAut) GR_AUT.get(y);

                        if (String.valueOf(buTipoDocumentoAut.getIdTipoDocumento()).equals(String.valueOf(fo.getIdTipoDocumento()))) {

                            request.setAttribute("Hab" + (String) buTipoDocumentoAut.getCampo(), buTipoDocumentoAut.getHabilitar());

                        }

                    }
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Entidad");

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
