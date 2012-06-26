<%-- 
    Document   : Entidad
    Created on : 24-abril-2012, 22:04:01
    Author     : Gilberth
--%>

<%@page import="forms.bean.BeanTipoDocumentoAut"%>
<%@page import="java.util.ArrayList"%>
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
            
            $(function() {
                matrixx = Array();
            <%

                ArrayList<Object> resultado = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
                for (int i = 0; i < resultado.size(); i++) {
                    out.print("matrixx[" + i + "] = Array('");
                    BeanTipoDocumentoAut buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                    buTipoDocumentoAut2 = (BeanTipoDocumentoAut) resultado.get(i);
                    out.print(buTipoDocumentoAut2.getIdTipoDocumento() + "', '");
                    out.print(buTipoDocumentoAut2.getCampo() + "', '");
                    out.print(buTipoDocumentoAut2.getHabilitar() + "');");
                }

            %>
                    
                    //guardar
                    $('#submit').click(function(e) {
                        e.preventDefault();
                        if(document.forms[0].idEntidad.value==""){
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
                            primerNombre : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            segundoNombre : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            primerApellido : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            segundoApellido : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            idTipoDocumento : {
                                required : true,
                                remote: { 
                                    url: "Jsp/Entidad/getEntidad.jsp", //valida si existe el identificacion
                                    type: "post", 
                                    data: { 
                                        lectura: function() { return document.forms[0].identificacion.readOnly },
                                        identificacion: function() { return $("#identificacion").val() } 
                                    } 
                                }
                            },
                            identificacion : {
                                required : true,
                                minlength : 7,
                                maxlength : 15,
                                number : true,
                                remote: { 
                                    url: "Jsp/Entidad/getEntidad.jsp", //valida si existe el identificacion
                                    type: "post", 
                                    data: { 
                                        lectura: function() { return document.forms[0].identificacion.readOnly },
                                        idTipoDocumento: function() { return $("#idTipoDocumento").val() }
                                    } 
                                }
                            },
                            razonSocial : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            idPais : {
                                required : true
                            },
                            idDepartamento : {
                                required : true
                            },
                            idMunicipio : {
                                required : true
                            },
                            Direccion : {
                                required : true,
                                minlength : 3,
                                maxlength : 45
                            },
                            telefono : {
                                required : true,
                                minlength : 7,
                                maxlength : 15,
                                number : true
                            },
                            email : {
                                required : true,
                                minlength : 8,
                                maxlength : 45,
                                email : true
                            },
                            idTipoEntidad : {
                                required : true
                            }
                        },
                        messages: {
                            identificacion: {
                                remote: "La Identificacion ya existe con el Tipo de Documento escogido"
                            },
                            idTipoDocumento: {
                                remote: "La Identificacion ya existe con el Tipo de Documento escogido"
                            }
                        },
                        debug: false,
                        errorElement: "label",
                        submitHandler: function(form){
                            form.submit();
                            //alert('El formulario ha sido validado correctamente!');
                        }
                    });
 
                    $("#idPais").change(function(){
                        $.post("Jsp/Comun/getDepartamento.jsp",{ id:$(this).val() },function(data){$("#idDepartamento").html(data);})
                    });
                    $("#idDepartamento").change(function(){
                        $.post("Jsp/Comun/getMunicipio.jsp",{ id:$(this).val(), idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})
                    });
                    $("#idTipoDocumento").change(function(){
                        for (i=0;i<matrixx.length;i++){
                            if(matrixx[i][0] == $(this).val()){
                                switch (matrixx[i][1]){
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "segundoNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#segundoNombre").attr('disabled', true);
                                        }else{
                                            $("#segundoNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerApellido" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerApellido").attr('disabled', true);
                                        }else{
                                            $("#primerApellido").attr('disabled', false);
                                        }
                                    break;
                                    case "segundoApellido" :
                                        if(matrixx[i][2] == "false"){
                                            $("#segundoApellido").attr('disabled', true);
                                        }else{
                                            $("#segundoApellido").attr('disabled', false);
                                        }
                                    break;
                                    case "razonSocial" :
                                        if(matrixx[i][2] == "false"){
                                            $("#razonSocial").attr('disabled', true);
                                        }else{
                                            $("#razonSocial").attr('disabled', false);
                                        }
                                    break;
                                    case "idPais" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idPais").attr('disabled', true);
                                        }else{
                                            $("#idPais").attr('disabled', false);
                                        }
                                    break;
                                    case "idDepartamento" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idDepartamento").attr('disabled', true);
                                        }else{
                                            $("#idDepartamento").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                    break;
                                }

                            }
                        }
                    });
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
                    document.forms[0].idDepartamento.value="";
                    document.forms[0].idMunicipio.value="";
                    document.forms[0].direccion.value="";
                    document.forms[0].telefono.value="";
                    document.forms[0].email.value="";
                    document.forms[0].idTipoEntidad.value="";
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

        <style type="text/css">
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
            <html:form action="/Entidad" styleId="forma" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idEntidad" value='<%= String.valueOf(request.getAttribute("getIdEntidad"))%>'> 

                <fieldset>
                    <legend>Ingreso Entidad</legend>

                    <table>
                        <tr>
                            <td class="text">Tipo Documento</td>
                            <% if (request.getAttribute("getIdentificacion") != "") {%> 
                            <td><html:select property="idTipoDocumento" styleId="idTipoDocumento" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdTipoDocumento"))%>'>
                                    <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                        <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% } else {%> 
                            <td><html:select property="idTipoDocumento" styleId="idTipoDocumento" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdTipoDocumento"))%>'>
                                    <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                        <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% }%> 
                        </tr>
                        <tr>
                            <td class="text">Identificacion</td>
                            <% if (request.getAttribute("getIdentificacion") != "") {%> 
                            <td><html:text property="identificacion" styleId="identificacion" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdentificacion"))%>'></html:text></td>
                            <% } else {%> 
                            <td><html:text property="identificacion" styleId="identificacion" value='<%= String.valueOf(request.getAttribute("getIdentificacion"))%>'></html:text></td>
                            <% }%> 
                        </tr>
                        <tr>
                            <td class="text">1er Nombre</td>
                            <td><html:text property="primerNombre" styleId="primerNombre" value='<%= String.valueOf(request.getAttribute("getPrimerNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">2do Nombre</td>
                            <td><html:text property="segundoNombre" styleId="segundoNombre" value='<%= String.valueOf(request.getAttribute("getSegundoNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">1er Apellido</td>
                            <td><html:text property="primerApellido" styleId="primerApellido" value='<%= String.valueOf(request.getAttribute("getPrimerApellido"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">2do Apellido</td>
                            <td><html:text property="segundoApellido" styleId="segundoApellido" value='<%= String.valueOf(request.getAttribute("getSegundoApellido"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Razon Social</td>
                            <td><html:text property="razonSocial" styleId="razonSocial" value='<%= String.valueOf(request.getAttribute("getRazonSocial"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Pais</td>
                            <td><html:select property="idPais" styleId="idPais" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                                    <c:forEach items="${CMB_PAIS}" var="cat">
                                        <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Departamento</td>
                            <td><html:select property="idDepartamento" styleId="idDepartamento" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'>
                                    <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                        <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Municipio</td>
                            <td><html:select property="idMunicipio" styleId="idMunicipio" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'>
                                    <c:forEach items="${CMB_MUNICIPIO}" var="cat">
                                        <html:option value="${cat.idMunicipio}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Direccion</td>
                            <td><html:text property="direccion" styleId="direccion" value='<%= String.valueOf(request.getAttribute("getDireccion"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Telefono</td>
                            <td><html:text property="telefono" styleId="telefono" value='<%= String.valueOf(request.getAttribute("getTelefono"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">E-mail</td>
                            <td><html:text property="email" styleId="email" value='<%= String.valueOf(request.getAttribute("getEmail"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Tipo de Entidad</td>
                            <td><html:select property="idTipoEntidad" styleId="idTipoEntidad" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdTipoEntidad"))%>'>
                                    <c:forEach items="${CMB_TIPOENTIDAD}" var="cat">
                                        <html:option value="${cat.idTipoEntidad}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdEntidad") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
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
