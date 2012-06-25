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
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
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
                        idPais : {
                            required : true,
                            minlength : 3,
                            maxlength : 3,
                            remote: { 
                                url: "Jsp/Pais/getPais.jsp", //valida si existe el idPais
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idPais.readOnly } 
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
                        idPais: {
                            remote: "El ID ya existe"
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
                document.forms[0].idPais.value="";
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
            <html:form action="/Pais" method="post" styleId="forma">

                <input type="hidden" name="op" value=""> 

                <fieldset>
                    <legend>Pais</legend>

                    <table>
                        <tr>
                            <td class="text">ID</td>
                            <% if (request.getAttribute("getIdPais") != "") {%> 
                                <td><html:text property="idPais" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'></html:text></td>
                            <% } else {%> 
                                <td><html:text property="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'></html:text></td>
                            <% }%> 
                        </tr>
                        <tr>
                            <td class="text">Nombre</td>
                            <td><html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdPais") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
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
