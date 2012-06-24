<%-- 
    Document   : MCnsUsuarios
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
        <script language="javascript" type="text/javascript" src="/Inventario/niceforms_files/niceforms.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
        <script type="text/javascript" src="/Inventario/Js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/ui.datepicker.js"></script>
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
        <script language="javascript">
            var xhr;
            function busqueda(ev){
                if((ev.keyCode>=48 && ev.keyCode<=57) ||
                    (ev.keyCode>=65 && ev.keyCode<=90)){
                    if(window.ActiveXObject){
                        xhr=new ActiveXObject("Microsoft.XMLHttp");
                    }
                    else if((window.XMLHttpRequest) || (typeof XMLHttpRequest)!=undefined){
                        xhr=new XMLHttpRequest();
                    }
                    else{
                        alert("Su navegador no tiene soporte para AJAX");
                        return;
                    }
                    localizaPalabra();
                }
            }
            function localizaPalabra(){
                //recupera el texto introducido en el campo
                //de texto y lo pasa como par�metro en la URL
                //al servlet
                var caja=document.getElementById("texto");
                var texto=caja.value;
                xhr.open("GET","/Inventario/Generador?texto="+texto,true);
                xhr.onreadystatechange=procesadatos;
                xhr.send(null);
            }
            function procesadatos(){
                if(xhr.readyState==4){
                    var resp=xhr.responseText;
                    var caja=document.getElementById("texto");
                    //si se ha recibido una palabra de respuesta
                    //se introduce en el control y se seleccionan los
                    //caracteres a�n no tecleados
                    if(resp!=""){
                        var inicioSel=caja.value.length;
                        caja.value=resp;
                        caja.selectionStart=inicioSel;
                        caja.selectionEnd=caja.value.length;
                    }
                }
            }
        </script>

        <script type="text/javascript">
            $(function() {
                $("#datepicker").datepicker();
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
                        <td><a class="boton" href="javascript:nuevo()">Nuevo Usuario</a></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Usuarios</legend>
                <table>
                    <tr>
                        <th bgcolor='#BDBDBD' height="25" width="40">ID</th>
                        <th bgcolor='#BDBDBD' height="25" width="130">Login</th>
                        <th bgcolor='#BDBDBD' height="25" width="130">Rol</th>
                        <th bgcolor='#BDBDBD' height="25" width="130">Tipo de Doc</th>
                        <th bgcolor='#BDBDBD' height="25" width="130">Identificacion</th>
                        <th bgcolor='#BDBDBD' height="25">Editar</th>
                    </tr>
                    <%int color = 1;%>
                    <c:forEach items="${GR_USUARIO}" var="cat">
                        <%  if (color == 1) {%>
                        <tr>
                            <td width="40" align="center" height="25" bgcolor="#D8D8D8"><c:out value="${cat.idUsuario}"/></td>
                            <td width="130" align="center"  height="25" bgcolor="#D8D8D8"><c:out value="${cat.login}"/></td>
                            <td width="130" align="center"  height="25" bgcolor="#D8D8D8"><c:out value="${cat.nombre}"/></td>
                            <td width="130" align="center"  height="25" bgcolor="#D8D8D8"><c:out value="${cat.nombreDocumento}"/></td>
                            <td width="130" align="center"  height="25" bgcolor="#D8D8D8"><c:out value="${cat.identificacion}"/></td>
                            <td width="60" align="center" height="25" bgcolor="#D8D8D8"><a href="javascript:modifica(${cat.idUsuario})">Modificar</a></td>
                        </tr>
                        <% color = 0;%>
                        <% } else {%>
                        <tr>
                            <td width="40" align="center" height="25"><c:out value="${cat.idUsuario}"/></td>
                            <td width="130" align="center"  height="25"><c:out value="${cat.login}"/></td>
                            <td width="130" align="center"  height="25"><c:out value="${cat.nombre}"/></td>
                            <td width="130" align="center"  height="25"><c:out value="${cat.nombreDocumento}"/></td>
                            <td width="130" align="center"  height="25"><c:out value="${cat.identificacion}"/></td>
                            <td width="60" align="center" height="25"><a href="javascript:modifica(${cat.idUsuario})">Modificar</a></td>
                        </tr>
                        <% color = 1;%>
                        <%}%>
                    </c:forEach>
                </table>
            </fieldset>
        </html:form>
    </body>
</html:html>
