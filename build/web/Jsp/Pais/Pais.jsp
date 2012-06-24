<%-- 
    Document   : Pais
    Created on : 15-junio-2012, 14:49:01
    Author     : Gilberth
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pais</title>
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <script type="text/javascript" src="/Inventario/Js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.datepicker.js"></script>
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
        <script type="text/javascript">
            $(function() {
                $("#datepicker").datepicker();
            });
            
            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idPais.value="";
                document.forms[0].nombre.value="";
            }

            function guardar(){
                if(document.forms[0].idPais.value==""){
                    document.forms[0].op.value="nuevo";
                }
                else {
                    document.forms[0].op.value="modificar";
                }
                document.forms[0].submit();
            }
            
            function eliminar(){
                document.forms[0].op.value="eliminar";
                document.forms[0].submit();
            }
            
            function atras(){
                document.forms[0].op.value="atras";
                document.forms[0].submit();}
        </script>
        <script language="javascript" type="text/javascript" src="/Inventario/niceforms_files/niceforms.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">

    </head>
    <body>
        <div >
            <html:form action="/Pais" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'> 

                <fieldset>
                    <legend>Pais</legend>

                    <table>
                        <tr>
                            <td class="text">Nombre del Pais</td>
                            <td><html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdPais") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td class="text"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
