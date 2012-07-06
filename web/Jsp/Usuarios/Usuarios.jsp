<%-- 
    Document   : MIngresoUsuarios
    Created on : 19-nov-2010, 22:21:01
    Author     : Mario
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
        <title>Usuario</title>
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <script type="text/javascript" src="/Inventario/Js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.datepicker.js"></script>
        <script type="text/javascript" src="/Inventario/Js/jquery.validate.js"></script>
        <script src="/Inventario/Js/i18n/messages_es.js" type="text/javascript"></script>
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
                    if(document.forms[0].idUsuario.value==""){
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
                        login : {
                            required : true,
                            minlength : 6,
                            remote: { 
                                url: "Jsp/Usuarios/getUsuario.jsp", //valida si existe el login
                                type: "post", 
                                data: { 
                                    idUsuario: function() { return $("#idUsuario").val() } 
                                } 
                            }
                        },
                        idTipoDocumento : {
                            required : true
                        },
                        identificacion : {
                            required : true,
                            number: true,
                            remote: { 
                                url: "Jsp/Usuarios/getUsuario.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    idTipoDocumento: function() { return $("#idTipoDocumento").val() } 
                                } 
                            }
                        }
                    },
                    messages: {
                        login: {
                            remote: "El nombre de usuario ya existe"
                        },
                        identificacion: {
                            remote: "La identificacion no existe"
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

        <script type="text/javascript">
            $(function() {
                $("#datepicker").datepicker();
            });
            
            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idUsuario.value="";
                document.forms[0].login.value="";
                document.forms[0].password.value="";
                document.forms[0].idRol.value="1";
                document.forms[0].idTipoDocumento.value="1";
                document.forms[0].identificacion.value="";
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
            <html:form action="/Usuarios" method="post" styleId="forma">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idUsuario" id="idUsuario" value='<%= String.valueOf(request.getAttribute("getIdUsuario"))%>'> 

                <fieldset>
                    <legend>Usuarios</legend>

                    <table>
                        <tr>
                            <td class="text">Nombre de Usuario</td>
                            <td>*<html:text property="login" styleId="login" value='<%= String.valueOf(request.getAttribute("getLogin"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Contraseña</td>
                            <td><html:text property="password" styleId="password" value='<%= String.valueOf(request.getAttribute("getPassword"))%>'></html:text></td>
                            <%
                                if (request.getAttribute("getIdUsuario") != "") {
                            %>
                            <td class="text">Actualizar</td>
                            <td><html:checkbox property="actPassword" value="on"></html:checkbox></td>
                            <%  }
                            %>
                        </tr>
                        <tr>
                            <td class="text">Role</td>
                            <td>*<html:select property="idRol" styleId="idRol" size="1" style="width:140px;" value='<%= String.valueOf(request.getAttribute("getIdRol"))%>'>
                                    <c:forEach items="${CMB_ROLES}" var="cat">
                                        <html:option value="${cat.idRoles}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Tipo de Documento</td>
                            <td>*<html:select property="idTipoDocumento" styleId="idTipoDocumento" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdTipoDocumento"))%>'>
                                    <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                        <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Identificacion:</td>
                            <td>*<html:text property="identificacion" styleId="identificacion" value='<%= String.valueOf(request.getAttribute("getIdentificacion"))%>'></html:text></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" href="javascript:;" id="submit">Guardar</a> <% if (request.getAttribute("getIdUsuario") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td colspan="3" class="text"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
