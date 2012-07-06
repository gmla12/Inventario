<%-- 
    Document   : getDepartamento
    Created on : 25/06/2012, 10:28:58 AM
    Author     : @gilberth12.
--%>

<%@page import="forms.bean.BeanMunicipio"%>
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
    String op = request.getParameter("id");
    String op2 = request.getParameter("idPais");
    if (op != null) {
        String id = String.valueOf(op);
        String json = "";

        GestionMunicipio gr = new GestionMunicipio();
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado = gr.MostrarMunicipio(id, op2, false, null);
        if ((Boolean) resultado.get(0) == false) {

            ArrayList<Object> GR_AUT = (ArrayList) resultado.get(1);
            session.setAttribute("CMB_MUNICIPIO", (ArrayList) resultado.get(1));
            BeanMunicipio buMunicipio2;
            json = "";

            for (int i = 0; i < GR_AUT.size(); i++) {

                buMunicipio2 = new BeanMunicipio();
                buMunicipio2 = (BeanMunicipio) GR_AUT.get(i);

                json = "<option value = " + buMunicipio2.getIdMunicipio() + ">" + buMunicipio2.getNombre() + "</option>";
                out.print(json);

            }

            out.close();
        } else {
            out.print(false);
        }
        out.close();
    }
%>