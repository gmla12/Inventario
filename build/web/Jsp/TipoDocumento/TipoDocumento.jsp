<%-- 
    Document   : TipoDocumento
    Created on : 25-abril-2012, 08:39:01
    Author     : Gilberth
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Documento</title>
        <link type="text/css" href="/Inventario/css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="/Inventario/css/comun.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" media="screen" href="/Inventario/css/ui.jqgrid.css" />
        <script src="/Inventario/Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="/Inventario/Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="/Inventario/Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="/Inventario/Js/jquery.validate.js"></script>
        <script src="/Inventario/Js/i18n/messages_es.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="all" href="/Inventario/niceforms_files/niceforms-default.css">
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
            $(function(){ 
                $('#submit').click(function(e) {
                    e.preventDefault();
                    if(document.forms[0].idTipoDocumento.value==""){
                        document.forms[0].op.value="nuevo";
                    }
                    else {
                        document.forms[0].op.value="modificar";
                    }
                    $("#forma").submit(); 
                }); 
                $("#forma").validate({
                    event: "blur",
                    rules : {
                        nombre : {
                            required : true,
                            minlength : 3,
                            maxlength : 45
                        }
                    },
                    debug: false,
                    errorElement: "label",
                    submitHandler: function(form){
                        form.submit();
                        //alert('El formulario ha sido validado correctamente!');
                    }
                });
                var grid = $("#list4"),
                getColumnIndexByName = function(columnName1, columnName2) {
                    var cm = grid.jqGrid('getGridParam','colModel'),i=0,l=cm.length;
                    for (; i<l; i++) {
                        if (cm[i].name===columnName1 || cm[i].name===columnName2) {
                            return i; // return the index
                        }
                    }
                    return -1;
                },
                disableIfChecked = function(elem, id, column){
                    var checke = "";
                    id=id;
                    if ($(elem.target).is(':checked')) {
                        checke="true";
                    }else{
                        checke="false";
                    }
                    $.ajax({
                        type: "POST",
                        url: "Jsp/TipoDocumento/getGriddahico.jsp",
                        data: "op=act&id="+id+"&column="+column+"&valor="+checke
                    });
                };
                jQuery("#list4").jqGrid({
                    url:'Jsp/TipoDocumento/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    height:180,
                    colNames:['IdIipoDocumento', 'Campo', 'Habilitar', 'Obligatorio'],
                    colModel:[
                        {name:'idTipoDocumento',index:'idTipoDocumento', width:1},
                        {name:'campo',index:'campo', width:160},
                        {name:'habilitar',index:'habilitar', width:80, editable:true, align: "center",edittype:"checkbox", formatter:'checkbox', editoptions: { value: "true:false" }, formatoptions: {disabled : false}},
                        {name:'obligatorio',index:'obligatorio', width:80, editable:true, align: "center",edittype:"checkbox", formatter:'checkbox', editoptions: { value:"true:false" }, formatoptions: {disabled : false}}		
                    ],
                    pager: '#prowed1',
                    editurl: "Jsp/TipoDocumento/getGriddahico.jsp",
                    caption: "Lista de Campos",
                    loadComplete: function() {
                        //Para la columna habilitar
                        var h=getColumnIndexByName('habilitar'), rows = this.rows, i, c = rows.length;
                        for (i = 1; i < c; i += 1) {
                            $(rows[i].cells[h]).click(function (e) {
                                var id = $(e.target).closest('tr')[0].id;
                                disableIfChecked(e, id, "habilitar");
                            });
                        }
                        //Para la columna obligatorio
                        var o=getColumnIndexByName('obligatorio');
                        for (i = 1; i < c; i += 1) {
                            $(rows[i].cells[o]).click(function (e) {
                                var id = $(e.target).closest('tr')[0].id;
                                disableIfChecked(e, id, "obligatorio");
                            });
                        }
                    }
                }); 
                jQuery("#list4").jqGrid('navGrid',"#prowed1",{edit:true,add:false,del:false});
                //Ocultar Columna idTipoDocumento
                jQuery("#list4").setGridParam().hideCol("idTipoDocumento").trigger("reloadGrid");
                function checkboxFormatter(cellvalue, options, rowObject) {
                    cellvalue = cellvalue + "";
                    cellvalue = cellvalue.toLowerCase();
                    var bchk = cellvalue.search(/(false|0|no|off|n)/i) < 0 ? " checked=" +checked : "";
                    return "<input type='checkbox' onclick=" + disableIfChecked(this, '" + options.rowId + "');" " + bchk + " value='" + cellvalue + "' offval='no' />";
                }
            }); 

            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idTipoDocumento.value="";
                document.forms[0].nombre.value="";
            }
            
            function eliminar(){
                document.forms[0].op.value="eliminar";
                document.forms[0].submit();
            }
            
            function atras(){
                document.forms[0].op.value="atras";
                document.forms[0].submit();
            }
            
        </script>

        <style type="text/css">
            .error-message, label.error {
                color: #ff0000;
                margin:0;
                display: inline;
                font-size: 1em !important;
                font-weight:lighter;
            }
        </style>
    </head>
    <body>
        <div >
            <html:form action="/TipoDocumento" method="post" styleId="forma">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idTipoDocumento" value='<%= String.valueOf(request.getAttribute("getIdTipoDocumento"))%>'> 

                <fieldset>
                    <legend>Ingreso Tipo Documento</legend>

                    <table>
                        <tr>
                            <td colspan="2" class="text">Nombre del Tipo Documento</td>
                            <td><html:text property="nombre" styleId="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text></td>
                        </tr>
                        <%
                            if (request.getAttribute("getIdTipoDocumento") != "") {
                        %>
                        <tr>
                            <td colspan="3" class="text">Por favor establesca cuales campos son obligatorios y habilitados.</td>
                        </tr>
                        <tr>
                            <td colspan="3"><table id="list4"></table></td>
                            <td colspan="3"><div id="prowed1"></div></td>
                        </tr>
                        <%  }
                        %>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdTipoDocumento") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td class="text"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
