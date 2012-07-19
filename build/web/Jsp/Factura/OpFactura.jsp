<%-- 
    Document   : OpFactura
    Created on : 12-julio-2012, 14:32:34
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
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet"/>
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
        <link rel="stylesheet" type="text/css" media="screen" href="/Inventario/css/ui.jqgrid.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="/Inventario/css/themes/ui-lightness/jquery.ui.datepicker.css"/>
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="/Inventario/Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.ui.datepicker.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.ui.core.js" type="text/javascript"></script>
        <script src="/Inventario/Js/i18n/jquery.ui.datepicker-es.js" type="text/javascript"></script>
        <title>Opciones Factura</title>
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
        <jsp:forward page="/OpFactura.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%            }
        %>

        <script type="text/javascript">
            $(function(){ 
                $("#bFecha").datepicker({
                    showOtherMonths: true,
                    selectOtherMonths: true,
                    changeYear: true,
                    changeMonth: true
                });
                jQuery("#list4").jqGrid({
                    url:'Jsp/Factura/getGriddahicoOp.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Entidad', 'No. Factura', 'Fecha', 'Total', 'Editar'],
                    colModel:[
                        {name:'idFactura',index:'idFactura', width:50, sortable:false},
                        {name:'nombreEntidad',index:'nombreEntidad', width:360, sortable:false},
                        {name:'numFactura',index:'numFactura', width:160, sortable:false},
                        {name:'fecha',index:'fecha', width:160, sortable:false},
                        {name:'total',index:'total', width:160, sortable:false},
                        {name:'editar',index:'editar', width:110, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 550,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Facturas"
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
        <html:form action="/OpFactura.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <fieldset>
                <legend>Consulta de Facturas</legend>
                <table>
                    <tr>
                        <td>Entidad<html:select property="bIdEntidad" size="1" style="width:300px;" value='<%= String.valueOf(request.getAttribute("getbIdEntidad"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_ENTIDAD}" var="cat">
                                    <html:option value="${cat.idEntidad}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select></td>
                        <td>No. Factura<input size="15" type="text" name="bNumFactura" value="<%= session.getAttribute("getbNumFactura")%>"></td>
                        <td>Fecha<input size="10" type="text" name="bFecha" id="bFecha" value="<%= session.getAttribute("getbFecha")%>"></td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Facturas</legend>
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
