<%-- 
    Document   : getUsuario
    Created on : 31/05/2012, 9:59:58 PM
    Author     : @gilberth12.
--%>

<%@page import="modelo.GestionEntidad"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.GestionUsuarios"%>

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
    String op = request.getParameter("login");
    String op2 = request.getParameter("idUsuario");
    if (op != null && op2 != null) {
        String id = String.valueOf(op);

        GestionUsuarios gr = new GestionUsuarios();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.BuscarUsuarios(id, false, null);
        if ((Boolean) resultado.get(0) == false) {
            if ((Boolean) resultado.get(1) == true) {
                if (op2.contentEquals("")) {
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
    } else {
        op = request.getParameter("identificacion");
        op2 = request.getParameter("idTipoDocumento");
        if (op != null && op2 != null) {
            int id = Integer.parseInt(op);
            int idt = Integer.parseInt(op2);

            GestionEntidad gr = new GestionEntidad();
            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.BuscarEntidad(idt, id, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Boolean) resultado.get(1) == true) {
                    out.print(true);//si existe
                } else {
                    out.print(false);//no existe
                }
            } else {
                out.print(true);
            }
            out.close();
        }
    }
%>