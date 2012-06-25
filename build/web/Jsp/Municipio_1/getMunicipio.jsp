<%-- 
    Document   : getMunicipio
    Created on : 18/06/2012, 19:30:58 PM
    Author     : @gilberth12.
--%>

<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.GestionMunicipio"%>

<%
    String usuario = "";
    HttpSession sesionOk = request.getSession();
    if (sesionOk.getAttribute("usuario") == null) {
%>
<jsp:forward page="/index.jsp">
    <jsp:param name="mensaje" value="Es obligatorio identificarse"/>
</jsp:forward>
<%    } else {
        usuario = String.valueOf(sesionOk.getAttribute("usuario"));
    }
%>
<%
    String op = request.getParameter("idMunicipio");
    boolean op2 = (Boolean) Boolean.valueOf(request.getParameter("lectura"));
    String op3 = request.getParameter("idDepartamento");
    String op4 = request.getParameter("idPais");
    if (op != null) {
        String id = String.valueOf(op);

        GestionMunicipio gr = new GestionMunicipio();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.BuscarMunicipio(id, op3, op4, false, null);
        if ((Boolean) resultado.get(0) == false) {
            if ((Boolean) resultado.get(1) == true) {
                if (op2 == false) {
                    out.print(false);//si existe
                } else {
                    out.print(true);//si existe pero es una modificacion
                }
            } else {
                out.print(true);//no existe
            }
        } else {
            out.print(false);
        }
        out.close();
    }
%>