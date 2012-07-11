/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.mysql.jdbc.Connection;
import forms.PlantillaDispositivoForm;
import forms.bean.BeanCaracteristicaPlantilla;
import forms.bean.BeanPlantillaDispositivoHija;
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
 * @author Mario
 */
public class ActionPlantillaDispositivo extends Action {

    public ActionPlantillaDispositivo() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PlantillaDispositivoForm fo = (PlantillaDispositivoForm) form;
        GestionPlantillaDispositivo gr = new GestionPlantillaDispositivo();
        GestionCaracteristicaPlantilla grCaract = new GestionCaracteristicaPlantilla();
        GestionPlantillaDispositivoHija grHija = new GestionPlantillaDispositivoHija();
        HttpSession session = request.getSession();

        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdPlantillaDispositivo", fo.getIdPlantillaDispositivo());
            request.setAttribute("getNombre", fo.getNombre());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getHija", fo.getHija());
            session.setAttribute("GR_Eliminados", null);
            session.setAttribute("GR_EliminadosHija", null);
            session.setAttribute("GR_CARACTERISTICAPLANTILLA", null);
            session.setAttribute("GR_PlantillaDisponible", null);
            session.setAttribute("GR_PlantillaHija", null);

            ArrayList<Object> resultado;
            resultado = gr.IngresaPlantillaDispositivo(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdPlantillaDispositivo", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Plantilla Dispositivo");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Plantilla Dispositivo");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdPlantillaDispositivo", fo.getIdPlantillaDispositivo());
            request.setAttribute("getNombre", fo.getNombre());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getHija", fo.getHija());
            ArrayList<Object> GR_CARACTERISTICAPLANTILLA = (ArrayList) session.getAttribute("GR_CARACTERISTICAPLANTILLA");
            ArrayList<Object> GR_Eliminados = (ArrayList) session.getAttribute("GR_Eliminados");
            ArrayList<Object> GR_PlantillaHija = (ArrayList) session.getAttribute("GR_PlantillaHija");
            ArrayList<Object> GR_EliminadosHija = (ArrayList) session.getAttribute("GR_EliminadosHija");

            ArrayList<Object> resultado;
            resultado = (ArrayList) grCaract.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                Connection cn = (Connection) resultado.get(1);

                ArrayList<Object> resultado2;
                resultado2 = (ArrayList) grCaract.autoCommint(false, cn);
                if ((Boolean) resultado2.get(0) == false) {

                    //Graba si hay caracteristicas nuevas o modificadas
                    if (GR_CARACTERISTICAPLANTILLA != null) {

                        BeanCaracteristicaPlantilla buCaracteristicaPlantilla1;
                        BeanCaracteristicaPlantilla buCaracteristicaPlantilla2;

                        for (int i = 0; i < GR_CARACTERISTICAPLANTILLA.size(); i++) {

                            buCaracteristicaPlantilla2 = (BeanCaracteristicaPlantilla) GR_CARACTERISTICAPLANTILLA.get(i);

                            if (buCaracteristicaPlantilla2.getIdCaracteristicaPlantilla() == null) {
                                ArrayList<Object> resultado3;
                                resultado3 = (ArrayList) grCaract.IngresaCaracteristicaPlantilla(fo.getIdPlantillaDispositivo(), String.valueOf(buCaracteristicaPlantilla2.getNombre()), Boolean.parseBoolean(String.valueOf(buCaracteristicaPlantilla2.getObligatorio())), Boolean.parseBoolean(String.valueOf(buCaracteristicaPlantilla2.getHabilitar())), true, cn);
                                if ((Boolean) resultado3.get(0) == true) {

                                    request.setAttribute("error", resultado3.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {


                                ArrayList<Object> resultado4;
                                resultado4 = (ArrayList) grCaract.MostrarCaracteristicaPlantilla(Integer.parseInt(String.valueOf(buCaracteristicaPlantilla2.getIdCaracteristicaPlantilla())), fo.getIdPlantillaDispositivo(), true, cn);
                                if ((Boolean) resultado4.get(0) == false) {

                                    buCaracteristicaPlantilla1 = (BeanCaracteristicaPlantilla) resultado4.get(1);

                                    if (buCaracteristicaPlantilla1.getIdCaracteristicaPlantilla() == buCaracteristicaPlantilla2.getIdCaracteristicaPlantilla()) {

                                        ArrayList<Object> resultado5;
                                        resultado5 = (ArrayList) grCaract.ModificaCaracteristicaPlantilla(Integer.parseInt(String.valueOf(buCaracteristicaPlantilla2.getIdCaracteristicaPlantilla())), fo.getIdPlantillaDispositivo(), String.valueOf(buCaracteristicaPlantilla2.getNombre()), Boolean.parseBoolean(String.valueOf(buCaracteristicaPlantilla2.getObligatorio())), Boolean.parseBoolean(String.valueOf(buCaracteristicaPlantilla2.getHabilitar())), true, cn);
                                        if ((Boolean) resultado5.get(0) == true) {

                                            request.setAttribute("error", resultado5.get(1));
                                            return mapping.findForward("error");

                                        }

                                    }

                                } else {

                                    request.setAttribute("error", resultado4.get(1));
                                    return mapping.findForward("error");

                                }

                            }

                        }

                        // Elimina si fue eliminada alguna caracteristica
                        if (GR_Eliminados != null) {

                            for (int i = 0; i < GR_Eliminados.size(); i++) {

                                buCaracteristicaPlantilla1 = (BeanCaracteristicaPlantilla) GR_Eliminados.get(i);

                                ArrayList<Object> resultado6;
                                resultado6 = (ArrayList) grCaract.EliminaCaracteristicaPlantilla(Integer.parseInt(String.valueOf(buCaracteristicaPlantilla1.getIdCaracteristicaPlantilla())), fo.getIdPlantillaDispositivo(), true, cn);
                                if ((Boolean) resultado6.get(0) == true) {

                                    request.setAttribute("error", resultado6.get(1));
                                    return mapping.findForward("error");

                                }


                            }
                        }

                    }

                    //Se graba si hay plantillas hijas nuevas
                    if (GR_PlantillaHija != null) {

                        BeanPlantillaDispositivoHija buPlantillaDispositivoHija1;

                        for (int i = 0; i < GR_PlantillaHija.size(); i++) {

                            buPlantillaDispositivoHija1 = (BeanPlantillaDispositivoHija) GR_PlantillaHija.get(i);

                            if (buPlantillaDispositivoHija1.getIdPlantillaDispositivo() == null) {
                                ArrayList<Object> resultado3;
                                resultado3 = (ArrayList) grHija.IngresaPlantillaDispositivoHija(fo.getIdPlantillaDispositivo(), Integer.parseInt(String.valueOf(buPlantillaDispositivoHija1.getIdPlantillaDispositivoHija())), true, cn);
                                if ((Boolean) resultado3.get(0) == true) {

                                    request.setAttribute("error", resultado3.get(1));
                                    return mapping.findForward("error");

                                }

                            }

                        }

                        // Elimina si fue eliminada alguna caracteristica
                        if (GR_EliminadosHija != null) {

                            for (int i = 0; i < GR_EliminadosHija.size(); i++) {

                                buPlantillaDispositivoHija1 = (BeanPlantillaDispositivoHija) GR_EliminadosHija.get(i);

                                ArrayList<Object> resultado6;
                                resultado6 = (ArrayList) grHija.EliminaPlantillaDispositivoHija(fo.getIdPlantillaDispositivo(), Integer.parseInt(String.valueOf(buPlantillaDispositivoHija1.getIdPlantillaDispositivoHija())), true, cn);
                                if ((Boolean) resultado6.get(0) == true) {

                                    request.setAttribute("error", resultado6.get(1));
                                    return mapping.findForward("error");

                                }


                            }
                        }

                    }

                    ArrayList<Object> resultado7;
                    resultado7 = (ArrayList) gr.ModificaPlantillaDispositivo(fo, true, cn);
                    if ((Boolean) resultado7.get(0) == false) {

                        ArrayList<Object> resultado8;
                        resultado8 = (ArrayList) grCaract.commint(cn);
                        if ((Boolean) resultado8.get(0) == false) {

                            ArrayList<Object> resultado9;
                            resultado9 = (ArrayList) grCaract.autoCommint(true, cn);
                            if ((Boolean) resultado9.get(0) == false) {

                                ArrayList<Object> resultado10;
                                resultado10 = grCaract.MostrarCaracteristicaPlantilla(fo.getIdPlantillaDispositivo(), false, null);
                                if ((Boolean) resultado10.get(0) == false) {

                                    GR_CARACTERISTICAPLANTILLA = (ArrayList) resultado10.get(1);
                                    session.setAttribute("GR_CARACTERISTICAPLANTILLA", GR_CARACTERISTICAPLANTILLA);
                                    session.setAttribute("GR_Eliminados", null);
                                    session.setAttribute("GR_EliminadosHija", null);

                                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                                    System.out.println("Action Modicar Plantilla Dispositivo");

                                    return mapping.findForward("ok");

                                } else {

                                    request.setAttribute("error", resultado10.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado9.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado8.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado7.get(1));
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

            ArrayList<Object> resultado;
            resultado = (ArrayList) grCaract.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                Connection cn = (Connection) resultado.get(1);

                ArrayList<Object> resultado2;
                resultado2 = (ArrayList) grCaract.autoCommint(false, cn);
                if ((Boolean) resultado2.get(0) == false) {

                    ArrayList<Object> resultado3;
                    resultado3 = (ArrayList) gr.EliminaPlantillaDispositivo(fo, true, cn);

                    if ((Boolean) resultado3.get(0) == false) {

                        ArrayList<Object> resultado4;
                        resultado4 = (ArrayList) grCaract.EliminaCaracteristicaPlantilla(Integer.valueOf(fo.getIdPlantillaDispositivo()), true, cn);

                        if ((Boolean) resultado4.get(0) == false) {

                            ArrayList<Object> resultado5;
                            resultado5 = (ArrayList) grHija.EliminaPlantillaDispositivoHija(Integer.valueOf(fo.getIdPlantillaDispositivo()), true, cn);

                            if ((Boolean) resultado5.get(0) == false) {

                                ArrayList<Object> resultado6;
                                resultado6 = (ArrayList) grCaract.commint(cn);

                                if ((Boolean) resultado6.get(0) == false) {

                                    ArrayList<Object> resultado7;
                                    resultado7 = (ArrayList) grCaract.autoCommint(true, cn);

                                    if ((Boolean) resultado7.get(0) == false) {

                                        request.setAttribute("getIdPlantillaDispositivo", "");
                                        request.setAttribute("getNombre", "");
                                        request.setAttribute("getDescripcion", "");
                                        request.setAttribute("getHija", "");
                                        session.setAttribute("GR_CARACTERISTICAPLANTILLA", null);
                                        session.setAttribute("GR_Eliminados", null);
                                        session.setAttribute("GR_EliminadosHija", null);

                                        request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                        System.out.println("Action Eliminar Plantilla Dispositivio");

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
