
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="administracion.control.gstpermiso, ingreso.control.*,ingreso.entity.*,java.util.*"%>
<html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%
	gstlote_trafico glote_trafico = new gstlote_trafico();
	gstcontenedor_trafico gcontenedor_trafico = new gstcontenedor_trafico();
	String trafcodsx = request.getAttribute("trafcodsx") == null ? request.getParameter("trafcodsx")
			: (String) request.getAttribute("trafcodsx");
	Collection lista = glote_trafico.getlistalote_trafico(trafcodsx);
	request.setAttribute("lista", lista);
%>
<div class="titulo">Lotes del Trafico</div>
<br>
<%@ include file="trafico_head.jsp"%>

<table align='center' width='90%' border='0' cellspacing='0'
	cellpading='0'>
	<Tr>
		<td align='left'>
			<%
				if (gstpermiso.getEscritura(session, "ingreso", "lote_trafico.jsp")
						&& traf.getTrafestado().equals("TRAMITE")) {
			%>
			<a
			href='main.jsp?app=ingreso&modulo=lote_trafico_datos&trafcodsx=<%=trafcodsx%>'>
				Nuevo Lote </a> <%
 	}
 %>
		
		<td align='right'>
</table>
<table align='center' width='90%' border='0' cellspacing='0'
	cellpading='0' class="tabla_listas">
	<tr>
		<th>Lote
		<th>Proveedor
		<th>Material
		<th>Fecha Inicio
		<th>Fecha Vencimiento
		<th>Opcion <logic:iterate id="temp" name="lista" type="lote_trafico">
		        
				<tr align='center'>
					<td><%=temp.getLtraflote()%>
					<td><%=temp.getLtrafproveedor()%>
					<td><%=temp.getLtrafproducto()%>
					<td><%=temp.getLtrafelaboracion()%>
					<td><%=temp.getLtrafvencimiento()%>
					<td>
					    <% if (gstpermiso.getEscritura(session, "ingreso", "lote_trafico.jsp")) { %>
						<a href='lote_traficoAction.do?opcion=set&codsx=<%=temp.getLtrafcodsx()%>'><img src="./disenno/images/update.gif" title="Actualizar"></a>
						<% } %>
						&nbsp;
						<% if (gstpermiso.getEscritura(session, "ingreso", "lote_trafico.jsp") && traf.getTrafestado().equals("TRAMITE")) { %>
						| &nbsp; <a href="javascript:validarDelete('lote_traficoAction.do?opcion=delete&codsx=<%=temp.getLtrafcodsx()%>')"><img src="./disenno/images/substract.gif" title="Eliminar"></a> 
						<% } %>
						&nbsp;
			</logic:iterate>
</table>

<br>
<table align="center" width="80%">
	<tr>
		<td width="50%" align="left"><a
			href="traficoAction.do?opcion=set&codsx=<%=trafcodsx%>">
				Volver al Trafico </a>
		
</table>