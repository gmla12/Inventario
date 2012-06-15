<%-- 
    Document   : Entidad
    Created on : 24-abril-2012, 22:04:01
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
        <title>Entidad</title>
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
                document.forms[0].idEntidad.value="";
                document.forms[0].primerNombre.value="";
                document.forms[0].segundoNombre.value="";
                document.forms[0].primerApellido.value="";
                document.forms[0].segundoApellido.value="";
                document.forms[0].idTipoDocumento.value="1";
                document.forms[0].identificacion.value="";
                document.forms[0].razonSocial.value="";
                document.forms[0].idPais.value="";
                document.forms[0].idMunicipio.value="";
                document.forms[0].direccion.value="";
                document.forms[0].telefono.value="";
                document.forms[0].email.value="";
                document.forms[0].idTipoEntidad.value="";
            }

            function guardar(){
                if(document.forms[0].idEntidad.value==""){
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
                document.forms[0].submit();
            }
        </script>
        <script language="javascript" type="text/javascript" src="/Inventario/niceforms_files/niceforms.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">

    </head>
    <body>
        <div >
            <html:form action="/Entidad" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idEntidad" value='<%= String.valueOf(request.getAttribute("getIdEntidad"))%>'> 

                <fieldset>
                    <legend>Ingreso Entidad</legend>

                    <table>
                        <tr>
                            <td class="text">Tipo Documento</td>
                            <td><html:select property="idTipoDocumento"  size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdTipoDocumento"))%>'>
                                <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                    <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Identificacion:</td>
                            <td><html:text property="identificacion" value='<%= String.valueOf(request.getAttribute("getIdentificacion"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">1er Nombre</td>
                            <td><html:text property="primerNombre" value='<%= String.valueOf(request.getAttribute("getPrimerNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">2do Nombre</td>
                            <td><html:text property="segundoNombre" value='<%= String.valueOf(request.getAttribute("getSegundoNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">1er Apellido</td>
                            <td><html:text property="primerApellido" value='<%= String.valueOf(request.getAttribute("getPrimerApellido"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">2do Apellido</td>
                            <td><html:text property="segundoApellido" value='<%= String.valueOf(request.getAttribute("getSegundoApellido"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Razon Social</td>
                            <td><html:text property="razonSocial" value='<%= String.valueOf(request.getAttribute("getRazonSocial"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Pais</td>
                            <td><html:text property="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Municipio</td>
                            <td><html:text property="idMunicipio" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Direccion</td>
                            <td><html:text property="direccion" value='<%= String.valueOf(request.getAttribute("getDireccion"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Telefono</td>
                            <td><html:text property="telefono" value='<%= String.valueOf(request.getAttribute("getTelefono"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">E-mail</td>
                            <td><html:text property="email" value='<%= String.valueOf(request.getAttribute("getEmail"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Tipo de Entidad</td>
                            <td><html:text property="idTipoEntidad" value='<%= String.valueOf(request.getAttribute("getIdTipoEntidad"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdEntidad") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
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
