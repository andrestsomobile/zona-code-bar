
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="administracion.control.gstpermiso, maestro.control.*,maestro.entity.*,java.util.*"%>
<html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%gstsucursal gsucursal = new gstsucursal();
gstciudad gciu = new gstciudad();
gstdepartamento gdep = new gstdepartamento();

			gsucursal.setMaxfilas(10);

			String param = request.getParameter("param");
			String pag = request.getParameter("pag");
			String cliente = request.getAttribute("cliente") == null ? request
					.getParameter("succodcliente") : (String) request
					.getAttribute("cliente");
			Collection lista = gsucursal.getlistasucursal( cliente,pag, param ); 
			request.setAttribute("lista", lista); 
			gstcliente gcliente = new gstcliente();
			cliente entcliente;
			String clienteSuc;
			%>
<div class="titulo">Gestion de Sucursales</div>
<div align="right" > <a href="main.jsp?app=maestro&modulo=print_<%= request.getParameter("modulo") %>"> Imprimir Listado Completo </a> </div>

<div class="titulo">Cliente <%=gcliente.getcliente(cliente).getclinombre() %></div>


<br>
<table align='center'  width='90%' border='0' cellspacing='0' cellpading='0' > 
	<Tr>
		<td align='left'>
		<% if(gstpermiso.getEscritura(session, "maestro", "sucursal.jsp")) {%>
		<a href='main.jsp?app=maestro&modulo=sucursal_datos&succodcliente=<%=cliente %>'>Nueva Sucursal </a>
		<%} %>
		<td align='right'>
		<form method='post'
			action='main.jsp?app=maestro&modulo=sucursal&succodcliente=<%=cliente %>'><input
			type='text' name='param' value='<%=param==null ? "" : param %>'> <input
			type='submit' name='buscar' value='Buscar'></form>
</table>
<table align='center'  width='90%' border='0' cellspacing='0' cellpading='0' class="tabla_listas"> 
	<tr>
		<th>Cod Sistema (*)
		<th>Codigo
		<th>Nit (*)
		<th>Nombre (*)
		<th>Ciudad
		<th>Departamento
		<th>Direccion
		<th>Telefono
		<th>Opcion <logic:iterate id="temp" name="lista"
			type="maestro.entity.sucursal">
			<tr align='center'>
				<td><%=temp.getsuccodsx()%>
				<td><%=temp.getSuccodigo()%>
				<%
				entcliente = gcliente.getcliente(temp.getsuccodcliente());
				clienteSuc = entcliente != null ? entcliente.getclinombre():"";
				%>
				
				<td><%=temp.getsucnit()%>
				<td><%=temp.getsucnombre()%>
				<td><%= gciu.getciudad(temp.getsucciudad()).getciunombre()%>
				<td><%= gdep.getdepartamento(temp.getsucdepartamento()).getdepnombre()%>
				<td><%=temp.getsucdireccion()%>
				<td><%=temp.getsuctelefono()%>
				<td><a
					href='sucursalAction.do?opcion=set&codsx=<%= temp.getsuccodsx()%>&succodcliente=<%=cliente %>'><img src="./disenno/images/update.gif" title="Actualizar" ></a>&nbsp;|&nbsp;
				<% if(gstpermiso.getEscritura(session, "maestro", "sucursal.jsp")) {%>
				<a href="javascript:validarDelete('sucursalAction.do?opcion=delete&codsx=<%= temp.getsuccodsx()  %>&cliente=<%=cliente %>&succodcliente=<%= cliente  %>')"><img src="./disenno/images/substract.gif" title="Eliminar" ></a>
				<%} %>
		</logic:iterate>
</table>
<%String url = "main.jsp?app=maestro&modulo=sucursal&param="
					+ (param == null ? "" : param) + "&succodcliente="+ cliente;
			int paginas = gsucursal.getTotalPaginaslistasucursal(cliente, param);

			%>
<%@ taglib uri="/WEB-INF/Paginador.tld" prefix="p"%>
<p:paginar actual="<%= pag %>" total="<%= (paginas) %>" url="<%= url %>" />
<br>Los campos con (*) son de busqueda