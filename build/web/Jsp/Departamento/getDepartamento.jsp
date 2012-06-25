<%-- 
    Document   : getDepartamento
    Created on : 24/06/2012, 20:44:58 PM
    Author     : @gilberth12.
--%>

<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.GestionDepartamento"%>

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
    String op = request.getParameter("idDepartamento");
    boolean op2 = (Boolean) Boolean.valueOf(request.getParameter("lectura"));
    String op3 = request.getParameter("idPais");
    if (op != null) {
        String id = String.valueOf(op);

        GestionDepartamento gr = new GestionDepartamento();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.BuscarDepartamento(id, op3, false, null);
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