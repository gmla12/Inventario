<%-- 
    Document   : Top
    Created on : 19-nov-2010, 22:53:22
    Author     : Mario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top</title>
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <style>
            .botonHome{
                position:absolute;
                top:5px;
                left:10px;
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
    <body style="background-image: url(/Inventario/css/top.png); background-repeat: no-repeat; background-position: center">
        <div class="botonHome">
            <a href="/Inventario/Jsp/Inicio.jsp" target="Body"><img src="/Inventario/img/hom.png" alt="Home Bellavista"/></a>
        </div>
        <div align="center" class="encabe"><h1>Sistema Comunidad Bellavista</h1></div>
        <div class="divSesion">
            <table>
                <tr>
                    <td class="text">Usuario en Sesión :&nbsp;<%= session.getAttribute("usuario")%></td>
                </tr>
            </table>
        </div>
        <div class="divlogo"><img src="/Inventario/img/Bellavista.jpg" height="100" width="75"/></div>

    </body>
</html>
