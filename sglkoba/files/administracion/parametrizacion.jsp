
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
 pageEncoding="ISO-8859-1" import="administracion.control.*,administracion.entity.*,java.util.*,maestro.control.*, maestro.entity.*"%> 
 <html> 

 <%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%> 

 <%@ taglib uri="/WEB-INF/struts-html.tld"  prefix="html"%>

 <%   

 gstparametro gparametro = new gstparametro(); 
  gparametro.setMaxfilas(10);  


 String param = request.getParameter("param"); 
  String pag = request.getParameter("pag");   

 Collection lista = gparametro.getlistaparametro(pag, param); 
 request.setAttribute("lista", lista); 
  %> 
 <div class="titulo"> Gestion de Parametros </div> 
 <br> 
 <table width='90%' align='center' border='0' cellpadding="0" cellspacing="0"> 
 <tr>
 	<td colspan="2" class="fuente">
 	<br>
 	Realice operaciones de Busqueda, Crear, Actualizar o Eliminar usuarios de la aplicacion existentes.
 	<br><br>
 	<Tr> 
 	<td align='left'>
 	<% if(gstpermiso.getEscritura(session, "administracion", "parametrizacion.jsp")) {%>
 	<a href='main.jsp?app=administracion&modulo=parametrizacion_datos'> Nuevo Parametro </a> 
 	<%} %>
 	<td align='right'> 	<form method='post' action='main.jsp?app=administracion&modulo=parametro'> 
 <input type='text' name='param' value='<%=param==null ? "" : param %>'> 
 <input type='submit' name='buscar' value='Buscar'> 
 </form> 
 </table> 
 <table align='center'  width='90%' border='0' cellspacing='0' cellpading='0' class="tabla_listas"> 
 	<tr>  <th> Parametro id (*)
 <th> Nombre (*)
 <th> Valor (*)
 <th> Descripcion
 <th> Ubicacion (*)
 <th> Tipo de Elemento(*) 		
 <logic:iterate id="temp" name="lista" type="administracion.entity.parametro"  >
 <tr align='center'>  
 <td>  <%= temp.getpk_parametro() %>
 <td>  <%= temp.getnombre() %>
 <td>  <%= temp.getvalor() %>
 <td>  <%= temp.getdescripcion() %>
 <td>  <%= temp.getubicacion() %>
 <td>  <%= temp.gettipo_elemento() %>
 
 <td> <a href="parametroAction.do?opcion=set&pk_parametro=<%= temp.getpk_parametro()%>"><img src="./disenno/images/update.gif"></a>&nbsp;| &nbsp;
<% if(gstpermiso.getEscritura(session, "administracion", "parametrizacion.jsp")) {%>
<a href="javascript:validarDelete('parametroAction.do?opcion=delete&pk_parametro=<%= temp.getpk_parametro()  %>')">
<img src="./disenno/images/substract.gif"></a>
<%} %>
 </logic:iterate>
  </table> 
	 <% 	 String url = "main.jsp?app=administracion&modulo=parametro&param=" + (param==null?"":param); 
 int paginas = gparametro.getTotalPaginaslistaparametro(param); 
 %> 
 <%@ taglib uri="/WEB-INF/Paginador.tld" prefix="p" %> 
 <p:paginar  actual="<%= pag %>"   total="<%= (paginas) %>" url="<%= url %>"   /> 
 <br>
 Los Campos con * son los Campos de Busqueda
