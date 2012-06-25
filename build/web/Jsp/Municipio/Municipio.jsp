<%-- 
    Document   : Municipio
    Created on : 18-junio-2012, 17:08:01
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
        <title>Municipio</title>
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <script type="text/javascript" src="/Inventario/Js/ui/ui.core.js"></script>
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="/Inventario/Js/jquery.validate.js"></script>
        <script src="/Inventario/Js/i18n/messages_es.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
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
            $(function () { 
                //guardar
                $('#submit').click(function(e) {
                    e.preventDefault();
                    if(document.forms[0].idPais.readOnly==false){
                        document.forms[0].op.value="nuevo";
                    }
                    else {
                        document.forms[0].op.value="modificar";
                    }
                    $("#forma").submit(); 
                }); 
                $("#forma").validate({
                    event: "blur",
                    rules : {
                        idMunicipio : {
                            required : true,
                            minlength : 3,
                            maxlength : 3,
                            remote: { 
                                url: "Jsp/Municipio/getMunicipio.jsp", //valida si existe el idMunicipio
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idMunicipio.disabled },
                                    idPais: function() { return $("#idPais").val() },
                                    idDepartamento: function() { return $("#idDepartamento").val() } 
                                } 
                            }
                        },
                        idDepartamento : {
                            required : true,
                            remote: { 
                                url: "Jsp/Municipio/getMunicipio.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idPais.readOnly },
                                    idMunicipio: function() { return $("#idMunicipio").val() },
                                    idPais: function() { return $("#idPais").val() }
                                } 
                            }
                        },
                        idPais : {
                            required : true,
                            remote: { 
                                url: "Jsp/Municipio/getMunicipio.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idPais.readOnly },
                                    idMunicipio: function() { return $("#idMunicipio").val() },
                                    idDepartamento: function() { return $("#idDepartamento").val() } 
                                } 
                            }
                        },
                        nombre : {
                            required : true,
                            minlength : 3,
                            maxlength : 45
                        }
                    },
                    messages: {
                        idMunicipio: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        },
                        idDepartamento: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        },
                        idPais: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        }
                    },
                    debug: false,
                    errorElement: "label",
                    submitHandler: function(form){
                        form.submit();
                        //alert('El formulario ha sido validado correctamente!');
                    }
                });
            });

            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idMunicipio.value="";
                document.forms[0].nombre.value="";
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

        <style>
            .error-message, label.error {
                color: #ff0000;
                margin:0;
                display: inline;
                font-size: 1em !important;
                font-weight:lighter;
            }
        </style>
    </head>
    <body>
        <div >
            <html:form action="/Municipio" method="post" styleId="forma">

                <input type="hidden" name="op" value=""> 

                <fieldset>
                    <legend>Ingreso Municipio</legend>

                    <table>
                        <tr>
                            <td class="text">ID</td>
                            <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                            <td><html:text property="idMunicipio" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'></html:text></td>
                            <% } else {%> 
                            <td><html:text property="idMunicipio" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'></html:text></td>
                            <% }%> 
                        </tr>
                        <tr>
                            <td class="text">Departamento</td>
                            <% if (request.getAttribute("getIdDepartamento") != "") {%> 
                                <td><html:select property="idDepartamento"  size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'>
                                    <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                        <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                            <% } else {%> 
                                <td><html:select property="idDepartamento"  size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'>
                                    <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                        <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                            <% }%> 
                        </tr>
                        <tr>
                            <td class="text">Pais</td>
                            <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                                <td><html:select property="idPais"  size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                                    <c:forEach items="${CMB_PAIS}" var="cat">
                                        <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                            <% } else {%> 
                                <td><html:select property="idPais"  size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                                    <c:forEach items="${CMB_PAIS}" var="cat">
                                        <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                            <% }%> 
                        </tr>
                        <tr>
                            <td class="text">Nombre del Municipio</td>
                            <td><html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdMunicipio") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td class="text" colspan="3"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
