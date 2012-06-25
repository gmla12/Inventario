<%-- 
    Document   : OpUsuarios
    Created on : 22-abril-2012, 17:10:34
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
        <jsp:forward page="/OpUsuarios.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%
        }
        %>
        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'Jsp/Usuarios/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Login', 'Rol', 'Tipo de Doc', 'Identificacion', 'Editar'],
                    colModel:[
                        {name:'idUsuario',index:'idUsuario', width:50, sortable:false},
                        {name:'login',index:'login', width:100, sortable:false},
                        {name:'nombre',index:'nombre', width:100, sortable:false},
                        {name:'nombreDocumento',index:'nombreDocumento', width:180, sortable:false},
                        {name:'identificacion',index:'identificacion', width:140, sortable:false},
                        {name:'editar',index:'editar', width:110, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 580,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Usuarios"
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
        <html:form action="/OpUsuarios.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <fieldset>
                <legend>Consulta de Usuarios</legend>
                <table>
                    <tr>
                        <td>Login<input type="text" name="bLogin" value="<%= session.getAttribute("getbLogin")%>"/> </td>
                        <td>Tipo de Documento<html:select property="bIdTipoDocumento"  size="1" style="width:180px;" value='<%= String.valueOf(session.getAttribute("getbIdTipoDocumento"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_TIPODOCUMENTO}" var="cat">
                                        <html:option value="${cat.idTipoDocumento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select>
                        </td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                    </tr>
                    <tr>
                        <td>Rol<html:select property="bIdRol"  size="1" style="width:140px;" value='<%= String.valueOf(session.getAttribute("getbIdRol"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_ROLES}" var="cat">
                                        <html:option value="${cat.idRoles}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select>
                        </td>
                        <td>Identificacion<input type="text" name="bIdentificacion" value="<%= session.getAttribute("getbIdentificacion")%>"/> </td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Usuarios</legend>
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
