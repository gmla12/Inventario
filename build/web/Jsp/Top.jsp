<%-- 
    Document   : Top
    Created on : 19-nov-2010, 22:53:22
    Author     : Mario
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Inventario</title>
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/menu.css" rel="stylesheet" />
        <link rel="stylesheet" href="/Inventario/css/themes/base/jquery.ui.all.css">
        <script type="text/javascript" src="/Inventario/Js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.button.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.tabs.js"></script>
        <script type="text/javascript" src="/Inventario/Js/ui/jquery.ui.dialog.js"></script>
        <style type="text/css">
            .botonHome{
                position:absolute;
                top:5px;
                left:10px;
            }
            #dialog label, #dialog input { display:block; }
            #dialog label { margin-top: 0.5em; }
            #dialog input, #dialog textarea { width: 95%; }
            #tabs { margin-top: 1em; }
            #tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
            #add_tab { cursor: pointer; }
            html {
                font-size:10px;
            }

            .iframetab {
                width:100%;
                height:auto;
                border:0px;
                margin:0px;
                position:relative;
                top:-13px;
            }

            .ui-tabs-panel {
                padding:5px !important;
            }

            .openout {
                float:right;
                position:relative;
                top:-28px;
                left:-5px;
            }

            .openventana { 
                width: 16px; 
                height: 16px; 
                background-image: url(css/themes/ui-lightness/images/ui-icons_222222_256x240.png)/*{iconsContent}*/; 
                background-position: -16px -33px; 
            }
        </style>
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
            $(document).ready(function() {
                var $tabs = $('#tabs').tabs();

                //get selected tab
                function getSelectedTabIndex() {
                    return $tabs.tabs('option', 'selected');
                }

                //get tab contents
                beginTab = $("#tabs ul li:eq(" + getSelectedTabIndex() + ")").find("a");

                loadTabFrame($(beginTab).attr("href"),$(beginTab).attr("rel"));

                $("a.tabref").click(function() {
                    loadTabFrame($(this).attr("href"),$(this).attr("rel"));
                });

                //tab switching function
                function loadTabFrame(tab, url) {
                    if ($(tab).find("iframe").length == 0) {
                        var html = [];
                        html.push('<div class="tabIframeWrapper">');
                        html.push('<div class="openout"><img class="openventana" border="0" alt="Abrir en ventana" title="Abrir en ventana" /></div><iframe class="iframetab" src="' + url + '">Load Failed?</iframe>');
                        html.push('</div>');
                        $(tab).append(html.join(""));
                        $(tab).find("iframe").height($(window).height()-80-111);
                    }
                    return false;
                }
                
                var tab_counter = 2;
                var reff = "#" + "{href}";
                var labell = "#" + "{label}"
                var urll = "";
                
                // tabs init with a custom tab template and an "add" callback filling in the content
                var $tabs = $( "#tabs").tabs({
                    tabTemplate: "<li><a class='tabref' href='" + reff + "' rel='" + urll + "'>" + labell + "</a><span class='ui-icon ui-icon-close'>Abrir en ventana</span></li>",
                    add: function( event, ui ) {
                        var alto = $(window).height()-80-111;
                        var tab_content = '<div class="tabIframeWrapper"><div class="openout"><a href="' + urll + ' " target="_blank"><img class="openventana" border="0" alt="Abrir en ventana" title="Abrir en ventana" /></a></div><iframe class="iframetab" style="height:' + alto + 'px;" src="' + urll + '">Load Failed?</iframe></div>';
                        $( ui.panel ).append(tab_content);
                    }
                });
                
                // close icon: removing the tab on click
                // note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
                $( "#tabs span.ui-icon-close" ).live( "click", function() {
                    var index = $( "li", $tabs ).index( $( this ).parent() );
                    $tabs.tabs( "remove", index );
                });
                
                // actual addTab function: adds new tab using the title input from the form above
                function addTab(titulo, url) {
                    urll = url;
                    $tabs.tabs( "add", "#tabs-" + tab_counter, titulo );
                    $tabs.tabs( "select", "#tabs-" + tab_counter);
                    tab_counter++;
                }
                
                $('#principal').click(function(e) {
                    e.preventDefault();
                    addTab("Principal", "Jsp/Inicio.jsp");
                }); 
                
                $('#entidad').click(function(e) {
                    e.preventDefault();
                    addTab("Entidad", "OpEntidad.do");
                }); 
                
                $('#tipoEntidad').click(function(e) {
                    e.preventDefault();
                    addTab("Tipo de Entidad", "OpTipoEntidad.do");
                }); 
                
                $('#tipoDocumento').click(function(e) {
                    e.preventDefault();
                    addTab("Tipo de Documento", "OpTipoDocumento.do");
                }); 
                
                $('#pais').click(function(e) {
                    e.preventDefault();
                    addTab("Paises", "OpPais.do");
                }); 

                $('#departamento').click(function(e) {
                    e.preventDefault();
                    addTab("Departamentos", "OpDepartamento.do");
                }); 

                $('#municipio').click(function(e) {
                    e.preventDefault();
                    addTab("Municipios", "OpMunicipio.do");
                }); 

                $('#plantillaDispositivo').click(function(e) {
                    e.preventDefault();
                    addTab("Plantilla de Dispositivo", "OpPlantillaDispositivo.do");
                }); 

                $('#usuario').click(function(e) {
                    e.preventDefault();
                    addTab("Usuarios", "OpUsuarios.do");
                }); 

                $('#rol').click(function(e) {
                    e.preventDefault();
                    addTab("Roles", "OpRoles.do");
                }); 

                $('#factura').click(function(e) {
                    e.preventDefault();
                    addTab("Factura", "OpFactura.do");
                }); 

                $('#cerrar').click(function(e) {
                    e.preventDefault();
                    window.parent.location.href = '/Inventario/Jsp/CerrarSesion.jsp';
                }); 
            });
        </script>

    </head>
    <body>
        <div class="encabe">Sistema de Inventario</div>
        <div class="divSesion">
            <table>
                <tr>
                    <td style="font-size: 12px; font-family: Arial, Helvetica, sans-serif;"><b>Usuario:</b>&nbsp;<%= session.getAttribute("nombre")%></td>
                </tr>
                <tr>
                    <td style="font-size: 12px; font-family: Arial, Helvetica, sans-serif;"><b>Rol:</b>&nbsp;<%= session.getAttribute("rol")%></td>
                </tr>
            </table>
        </div>
        <div id='cssmenu'>
            <ul>
                <li><a href="#" id="principal"><span>Principal</span></a></li>
                <li class='has-sub'><a href='#'><span>Maestros</span></a>
                    <ul>
                        <li class='has-sub2'><a href='#'><span>Entidades</span></a>
                            <ul id="li1">
                                <li><a id="entidad" href='#'><span>Entidad</span></a></li>
                                <li><a id="tipoEntidad" href='#'><span>Tipos de Entidades</span></a></li>
                            </ul>
                        </li>
                        <li><a id="tipoDocumento" href='#'><span>Tipos de Documento</span></a></li>
                        <li><a id="pais" href='#'><span>Paises</span></a></li>
                        <li><a id="departamento" href='#'><span>Departamentos</span></a></li>
                        <li><a id="municipio" href='#'><span>Municipios</span></a></li>
                        <li class='has-sub2'><a href='#'><span>Plantillas</span></a>
                            <ul>
                                <li><a id="plantillaDispositivo" href='#'><span>Plantillas de Dispositivos</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class='has-sub'><a href='#'><span>Operaciones</span></a>
                    <ul>
                        <li><a id="factura" href='#'><span>Factura</span></a></li>
                    </ul>
                </li>
                <li class='has-sub'><a href='#'><span>Seguridad</span></a>
                    <ul>
                        <li><a id="usuario" href='#'><span>Usuarios</span></a></li>
                        <li><a id="rol" href='#'><span>Roles</span></a></li>
                    </ul>
                </li>
                <li><a id="cerrar" href='#'><span>Cerrar Sesion</span></a></li>
            </ul>
        </div>
        <div id="tabs">
            <ul>
                <li><a class="tabref" href="#tabs-1" rel="Jsp/Inicio.jsp">Principal</a></li>
            </ul>
            <div id="tabs-1" class="tabMain">
            </div>
        </div>
    </body>
</html>
