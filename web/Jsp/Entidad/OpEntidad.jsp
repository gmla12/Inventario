<%-- 
    Document   : OpEntidad
    Created on : 24-abril-2012, 21:57:34
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
        <script type="text/javascript" src="/Inventario/Js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.datepicker.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="/Inventario/css/ui.jqgrid.css" />
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="/Inventario/Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <title>Opiones Usuarios</title>
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
        <jsp:forward page="/OpEntidad.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%
        }
        %>

        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'Jsp/Entidad/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Nombre', 'Tipo de Documento', 'Identificacion', 'Editar'],
                    colModel:[
                        {name:'idPais',index:'idPais', width:15, sortable:false},
                        {name:'nombre',index:'nombre', width:90, sortable:false},
                        {name:'nombreTipoDoc',index:'nombreTipoDoc', width:70, sortable:false},
                        {name:'identificacion',index:'identificacion', width:40, sortable:false},
                        {name:'editar',index:'editar', width:40, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 590,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Entidades"
                }); 
                jQuery("#list4").jqGrid('navGrid',"#prowed1",{edit:false,add:false,del:false,search:false});
            }); 
            
            function buscar(){
                document.forms[0].op.value="buscar";
                document.forms[0].id.value="";
                document.forms[0].submit();
            }
            
            function modifica(id){
                document.forms[0].op.value="modificar";
                document.forms[0].id.value=id;
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
        <html:form action="/OpEntidad.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <fieldset>
                <legend>Consulta de Entidad</legend>
                <table>
                    <tr>
                        <td>Nombre<input type="text" name="bNombre" value="<%= session.getAttribute("getbNombre")%>"/> </td>
                        <td>Tipo de Documento<html:select property="bIdTipoDocumento"  size="1" style="width:150px;" value='<%= String.valueOf(session.getAttribute("getbIdTipoDocumento"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                        <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select>
                        </td>
                        <td>Identificacion<input type="text" name="bIdentificacion" size="15" value="<%= session.getAttribute("getbIdentificacion")%>"/> </td>
                        <td>
                        </td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Entidad</legend>
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
