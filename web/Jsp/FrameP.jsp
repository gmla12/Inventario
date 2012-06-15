<%-- 
    Document   : FrameP
    Created on : 19-nov-2010, 22:51:43
    Author     : Mario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<frameset rows="100,*" frameborder="0" framespacing="0" border="0" scrolling="no">
    <frame name="Head" src="Jsp/Top.jsp"  scrolling="no">
    <frameset cols="235,*" frameborder="0" framespacing="0" border="0" scrolling="no">
        <frame name="Menu" src="Jsp/Menu.jsp"  scrolling="no">
        <frame name="Body" src="Jsp/Inicio.jsp"  scrolling="no">
    </frameset>
</frameset>