<%-- 
    Document   : getDepartamento
    Created on : 24/06/2012, 20:44:58 PM
    Author     : @gilberth12.
--%>

<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.GestionEntidad"%>

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
    String op = request.getParameter("identificacion");
    boolean op2 = (Boolean) Boolean.valueOf(request.getParameter("lectura"));
    String op3 = request.getParameter("idTipoDocumento");
    if (op != null && op.equals("") == false) {
        int id = Integer.parseInt(op);
        int id2 = Integer.parseInt(op3);

        GestionEntidad gr = new GestionEntidad();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.BuscarEntidad(id2, id, false, null);
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