<%-- 
    Document   : Inicio
    Created on : 19-nov-2010, 22:13:12
    Author     : Mario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <style>
            .ses{
                position:absolute;
                top:100px;
                left:300px;
            }
        </style>
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
    </head>
    <body bgcolor=" #EFFBFB">
        <div class="ses">
            <table>
                <tr>
                    <td><img src="/Inventario/img/Bellavista.jpg" width="300" height="300"/> </td>
                </tr>
                <tr>
                    <td class="text" align="center">Bienvenido :&nbsp;<%=  session.getAttribute("nombre")%></td>
                </tr>
            </table>
        </div>
    </body>
</html>
