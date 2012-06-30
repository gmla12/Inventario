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
        <title>Top</title>
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
                background:url("data/iframeno.png");
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
                        html.push('<div class="openout"><a href="' + url + ' "><img src="data/world.png" border="0" alt="Open" title="Remove iFrame" /></a></div><iframe class="iframetab" src="' + url + '">Load Failed?</iframe>');
                        html.push('</div>');
                        $(tab).append(html.join(""));
                        $(tab).find("iframe").height($(window).height()-80);
                    }
                    return false;
                }
                
                var $tab_title_input = $( "#tab_title"),
                $tab_content_input = $( "#tab_content" );
                var tab_counter = 2;
                var reff = "#" + "{href}";
                var labell = "#" + "{label}"
                
                // tabs init with a custom tab template and an "add" callback filling in the content
                var $tabs = $( "#tabs").tabs({
                    tabTemplate: "<li><a class='tabref' href='" + reff + "' rel='Inicio.jsp'>" + labell + "</a><span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
                    add: function( event, ui ) {
                        var tab_content = '<div class="tabIframeWrapper"><div class="openout"><a href="' + 'Inicio.jsp' + ' "><img src="data/world.png" border="0" alt="Open" title="Remove iFrame" /></a></div><iframe class="iframetab" src="' + 'Inicio.jsp' + '">Load Failed?</iframe></div>';
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
                function addTab() {
                    var tab_title = "prueba";
                    $tabs.tabs( "add", "#tabs-" + tab_counter, tab_title );
                    tab_counter++;
                }
                $('#submit').click(function(e) {
                    e.preventDefault();
                    addTab();
                }); 
            });
        </script>

    </head>
    <body style="background-image: url(/Inventario/css/top.png); background-repeat: no-repeat; background-position: center">
        <div class="botonHome">
            <a href="/Inventario/Jsp/Inicio.jsp" target="Body"><img src="/Inventario/img/hom.png" alt="Home Bellavista"/></a>
        </div>
        <div align="center" class="encabe"><h1>Sistema Comunidad Bellavista</h1></div>
        <div class="divSesion">
            <table>
                <tr>
                    <td class="text">Usuario en Sesión :&nbsp;<%= session.getAttribute("usuario")%></td>
                </tr>
            </table>
        </div>
        <div class="divlogo"><img src="/Inventario/img/Bellavista.jpg" height="100" width="75"/></div>
        <div id='cssmenu'>
            <ul>
                <li><a href="javascript:addTab();"><span>Principal</span></a></li>
                <li class='has-sub '><a href='#'><span>Products</span></a>
                    <ul>
                        <li><a id="submit" href='javascript:addTab();'><span>Product 1</span></a></li>
                        <li><a href='javascript:addTab();'><span>Product 2</span></a></li>
                    </ul>
                </li>
                <li><a href='javascript:addTab();'><span>About</span></a></li>
                <li><a href='javascript:addTab();'><span>Contact</span></a></li>
            </ul>
        </div>
        <button id="add_tab">Add Tab</button>

        <div id="tabs">
            <ul>
                <li><a class="tabref" href="#tabs-1" rel="Inicio.jsp">google</a> <span class="ui-icon ui-icon-close">Remove Tab</span></li>
            </ul>
            <div id="tabs-1" class="tabMain">
            </div>
        </div>
    </body>
</html>
