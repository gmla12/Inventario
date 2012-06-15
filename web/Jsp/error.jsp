<%-- 
    Document   : enContruccion
    Created on : 25-05-2010, 06:56:38 PM
    Author     : Luis Urrutia Diaz
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ha ocurrido un error</title>
        <style>

            .const{
                position:absolute;
                top: 80px;
                left: 300px;
            }
        </style>
    </head>
    <body bgcolor = "#EFFBFB">
        
        <h1>Ha ocurrido un error...</h1>
        <div class="const"><p>Por favor contacte al programador y enviele el siguiente codigo:</p>
        <p><%= String.valueOf(request.getAttribute("error"))%></p></div> 

    </body>
</html>
