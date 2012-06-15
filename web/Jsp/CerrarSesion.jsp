<%-- 
    Document   : cerrar
    Created on : 24/04/2012, 02:54:11 PM
    Author     : Usuario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
        <%@ page session="true" %>
        <%
            HttpSession sesionOk = request.getSession();
            sesionOk.invalidate();
        %>
        <jsp:forward page="/index.jsp"/>
    </head>
    <body>
        <h1><c:out value="Hasta Luego!, por favor vuelva."/></h1>
    </body>
</html>

