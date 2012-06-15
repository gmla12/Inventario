<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>
    <head>
        <style>
            fieldset {
                border: thick solid #6666FF;
            }
            legend {
                color: #FFF;
                background: #6666FF;
                font-size: 1.5em;
                padding: 5px;

            }

            example three
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
        <script type="text/javascript">
            function breakout()
            {
                if (window.top != window.self) 
                {
                    window.top.location="<%=request.getContextPath()%>/index.jsp"
                }
            }
        </script>
    </head>
    <body onload="javascript:breakout()" style="background-color: white">

        <div >


            <html:form action="/Login" method="post">
                <fieldset  style="width: 440px; float: left; background-color: #dfdfdf ">
                    <legend>Acceso al Sistema</legend>

                    <table width="310px">

                        <tr>
                            <td>Usuario</td>
                            <td><html:text property="usuario"></html:text></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><html:password property="passw"></html:password></td>
                            </tr>
                            <tr>
                                <td><html:submit property="sobInicio" value="Validar"></html:submit></td>
                            </tr>
                        </table>

                    </fieldset>
                    <h4 style="color:white"><bean:write name="LoginForm" property="mensaje" filter="false"/></h4>
            </html:form>
        </div>

    </body>
</html:html>