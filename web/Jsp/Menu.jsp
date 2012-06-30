<%-- 
    Document   : Menu
    Created on : 19-nov-2010, 22:54:16
    Author     : Mario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <script type="text/javascript" src="../Js/ui/jquery-1.3.1.min.js"></script>
        <script type="text/javascript" src="../Js/ui/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="../Js/MenuDes.js"></script>
        <link href="/Inventario/css/estiloMenuDes.css" rel="stylesheet" type="text/css">
        <link href="/Inventario/css/menu.css" rel="stylesheet" type="text/css">
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
            function salir(){
                window.parent.location.href = '/Inventario/Jsp/CerrarSesion.jsp';
            }
        </script>

    </head>
    <body bgcolor="#EFFBFB">
        <div id="navsite">
            <ul>
                <li><center><a href="#" style=" font-size: 18px;"><strong>Menu</strong></a></center></li>
                <!--    <li style="text-align: left"><a href="/ZFsoft/Jsp/Inicio.jsp" style=" font-size: 14px;"><strong>Home</strong></a></li> -->
            </ul>
        </div>
        <div id="navsite">
            <ul>
                <li><a href="#" onclick="return kadabra('menulink1');" style=" font-size: 16px; ">&nbsp;<strong>Maestros</strong></a></li>
            </ul>
        </div>
        <div id="navsite2">
            <div id="menulink1">
                <div id="navsite">
                    <ul>
                        <li><a href="#" onclick="return kadabra('menulink3');" style=" font-size: 16px; ">&nbsp;<strong>Entidades</strong></a></li>
                    </ul>
                </div>
                <div id="navsite">
                    <ul id="menulink3">
                        <li><a href="/Inventario/OpEntidad.do" target="Body">&nbsp;&nbsp;Entidad</a></li>
                        <li><a href="/Inventario/OpTipoEntidad.do" target="Body">&nbsp;&nbsp;Tipo de Entidad</a></li>
                    </ul>
                </div>
                <div id="navsite">
                    <ul>
                        <li><a href="#" onclick="return kadabra('menulink4');" style=" font-size: 16px; ">&nbsp;<strong>Plantillas</strong></a></li>
                    </ul>
                </div>
                <div id="navsite">
                    <ul id="menulink4">
                        <li><a href="/Inventario/OpPlantillaDispositivo.do" target="Body">&nbsp;&nbsp;Plantilla Dispositivo</a></li>
                    </ul>
                </div>
                <ul >
                    <li><a href="/Inventario/OpTipoDocumento.do" target="Body">&nbsp;&nbsp;Tipo de Documentos</a></li>
                    <li><a href="/Inventario/OpPais.do" target="Body">&nbsp;&nbsp;Paises</a></li>
                    <li><a href="/Inventario/OpDepartamento.do" target="Body">&nbsp;&nbsp;Departamentos</a></li>
                    <li><a href="/Inventario/OpMunicipio.do" target="Body">&nbsp;&nbsp;Municipios</a></li>
                </ul>
            </div> 
        </div>
        <div id="navsite">
            <ul>
                <li><a href="#" onclick="return kadabra('menulink2');" style=" font-size: 16px; ">&nbsp;<strong>Seguridad</strong></a></li>
            </ul>
        </div>
        <div id="navsite2">
            <ul id="menulink2">
                <li><a href="/Inventario/OpUsuarios.do" target="Body">&nbsp;&nbsp;Usuarios</a></li>
                <li><a href="/Inventario/OpRoles.do" target="Body">&nbsp;&nbsp;Roles</a></li>
            </ul>
        </div>
        <div id="navsite">
            <ul>
                <li><a href="#" onclick="javascript:salir();"  style=" font-size: 16px; ">&nbsp;<strong>Cerrar Sessión</strong></a></li>
            </ul>
            <div class="block png"></div>
        </div>

    </body> 
</html>
