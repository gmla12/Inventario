<%-- 
    Document   : getDepartamento
    Created on : 25/06/2012, 10:28:58 AM
    Author     : @gilberth12.
--%>

<%@page import="forms.bean.BeanDepartamento"%>
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
    String op = request.getParameter("id");
    if (op != null) {
        String id = String.valueOf(op);
        String json = "";

        GestionDepartamento gr = new GestionDepartamento();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.MostrarDepartamento(id, false, null);
        if ((Boolean) resultado.get(0) == false) {

            ArrayList<Object> GR_AUT = (ArrayList) resultado.get(1);
            BeanDepartamento buDepartamento2;
            json = "";
            json = "<option value = ''>[Todos]</option>";
            out.print(json);

            for (int i = 0; i < GR_AUT.size(); i++) {

                buDepartamento2 = new BeanDepartamento();
                buDepartamento2 = (BeanDepartamento) GR_AUT.get(i);

                json = "<option value = " + buDepartamento2.getIdDepartamento() + ">" + buDepartamento2.getNombre() + "</option>";
                out.print(json);

            }

            out.close();
        } else {
            out.print(false);
        }
        out.close();
    }
%>