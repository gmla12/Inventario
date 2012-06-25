<%-- 
    Document   : getGriddahico
    Created on : 4/02/2010, 11:00:58 PM
    Author     : @dahico.
    Blog:[URL="http://dahicotux.wordpress.com"]http://dahicotux.wordpress.com[/URL]
--%>

<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="forms.bean.BeanTipoDocumentoAut"%>
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
    int start = 0;
    int total_pages = 0;
    String op = request.getParameter("op");
    if (op.equals("bus")) {
        ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("GR_TIPODOCUMENTOAUT");
        int intpage = new Integer(request.getParameter("page"));
        int limit = new Integer(request.getParameter("rows"));

        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");

        /*
         * -----------------------------------
         */

        BeanTipoDocumentoAut buTipoDocumentoAut2;

        /*
         * -----------------------------------
         */
        String json = "";

        boolean rc;

        if (sidx == "") {
            sidx = "1";
        }

        if (GR_AUT.size() > 0) {
            double d = Math.ceil((double) (GR_AUT.size()) / (double) (limit));
            total_pages = (int) (d);
        } else {
            total_pages = 0;
        }

        if (intpage > total_pages) {
            intpage = total_pages;
        }

        start = limit * intpage - limit;

        if (start < 0) {
            start = 0;
        }

        //strQuery = "SELECT * FROM tbl_Usuario ORDER BY " + sidx + " " + sord + " LIMIT " + start + " , " + limit;

        /*
         * Acá viene la parte donde creamos la cadena para el JSON manualmente
         */

        response.setContentType("text/x-json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        json = "";
        json = json + "{\n";
        json = json + " \"page\":\"" + intpage + "\",\n";
        json = json + "\"total\":" + total_pages + ",\n";
        json = json + "\"records\":" + GR_AUT.size() + ",\n";
        json = json + "\"rows\": [";
        rc = false;

        int maximo = start + limit;
        if (maximo > GR_AUT.size()){
            maximo = GR_AUT.size();
        }
                
        for (int i = start; i < maximo; i++) {

            if (rc) {
                json = json + ",";
            }

            buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
            buTipoDocumentoAut2 = (BeanTipoDocumentoAut) GR_AUT.get(i);

            json = json + "\n{";
            json = json + "\"id\":\"" + i + "\",";
            json = json + "\"cell\":[" + buTipoDocumentoAut2.getIdTipoDocumento() + "";
            json = json + ",\"" + buTipoDocumentoAut2.getCampo() + "\"";
            json = json + ",\"" + Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getHabilitar())) + "\"";
            json = json + ",\"" + Boolean.valueOf(String.valueOf(buTipoDocumentoAut2.getObligatorio())) + "\"]";
            json = json + "}";

            rc = true;
        }

        json = json + "]\n";

        json = json + "}";

        out.print(json);
        out.close();
    } else {
        int id = Integer.valueOf(request.getParameter("id"));
        String column = String.valueOf(request.getParameter("column"));
        boolean valor = Boolean.valueOf(request.getParameter("valor"));

        ArrayList<Object> GR_AUT = (ArrayList) session.getAttribute("GR_TIPODOCUMENTOAUT");
        BeanTipoDocumentoAut buTipoDocumentoAut2;

        buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
        buTipoDocumentoAut2 = (BeanTipoDocumentoAut) GR_AUT.get(id);

        if (column.equals("habilitar")) {
            buTipoDocumentoAut2.setHabilitar(valor);
        } else {
            buTipoDocumentoAut2.setObligatorio(valor);
        }
        GR_AUT.set(id, buTipoDocumentoAut2);
    }
%>