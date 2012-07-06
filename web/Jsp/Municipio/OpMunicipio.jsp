<%-- 
    Document   : OpMunicipio
    Created on : 18-junio-2012, 17:02:34
    Author     : Gilberth
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
        <link rel="stylesheet" type="text/css" media="screen" href="/Inventario/css/ui.jqgrid.css" />
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="/Inventario/Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <title>Opciones Municipios</title>
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
            if (request.getAttribute("getOp") == "buscar") {
        %>
        <jsp:forward page="/OpMunicipio.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%            }
        %>
        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'Jsp/Municipio/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Departamento', 'Pais', 'Nombre', 'Editar'],
                    colModel:[
                        {name:'idMunicipio',index:'idMunicipio', width:20, sortable:false},
                        {name:'nombreDepartamento',index:'nombreDepartamento', width:90, sortable:false},
                        {name:'nombrePais',index:'nombrePais', width:90, sortable:false},
                        {name:'nombre',index:'nombre', width:160, sortable:false},
                        {name:'editar',index:'editar', width:50, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 600,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Municipios"
                }); 
                jQuery("#list4").jqGrid('navGrid',"#prowed1",{edit:false,add:false,del:false,search:false});
                $("#bIdPais").change(function(){
                    $.post("Jsp/Comun/getDepartamentoOp.jsp",{ id:$(this).val() },function(data){$("#bIdDepartamento").html(data);})
                });
            }); 

            function buscar(){
                document.forms[0].op.value="buscar";
                document.forms[0].id.value="";
                document.forms[0].submit();
            }
            
            function modifica(id, id2, id3){
                document.forms[0].op.value="modificar";
                document.forms[0].id.value=id;
                document.forms[0].id2.value=id2;
                document.forms[0].id3.value=id3;
                document.forms[0].submit();
            }

            function nuevo(){
                document.forms[0].op.value="nuevo";
                document.forms[0].id.value="";
                document.forms[0].submit();
            }
        </script>

    </head>
    <body  bgcolor="#EFFBFB">
        <html:form action="/OpMunicipio.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <input type="hidden" name="id2" value=""> 
            <input type="hidden" name="id3" value=""> 
            <fieldset>
                <legend>Consulta de Municipios</legend>
                <table>
                    <tr>
                        <td>ID<input size="5" type="text" name="bIdMunicipio" value="<%= session.getAttribute("getbIdMunicipio")%>"/> </td>
                        <td>Nombre<input type="text" name="bNombre" value="<%= session.getAttribute("getbNombre")%>"/> </td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                    <tr>
                        <td>Pais<html:select property="bIdPais" styleId="bIdPais" size="1" style="width:240px;" value='<%= String.valueOf(session.getAttribute("getbIdPais"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_PAIS}" var="cat">
                                    <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                        </html:select></td>
                        <td>Departamento<html:select property="bIdDepartamento" styleId="bIdDepartamento" size="1" style="width:240px;" value='<%= String.valueOf(session.getAttribute("getbIdDepartamento"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                    <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                        </html:select></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Muncipios</legend>
                <table>
                    <tr>
                        <td><table id="list4"></table></td>
                        <td><div id="prowed1"></div></td>
                    </tr>
                </table>
            </fieldset>
        </html:form>
    </body>
</html:html>
