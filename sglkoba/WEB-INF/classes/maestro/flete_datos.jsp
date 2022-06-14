<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="administracion.control.gstpermiso, maestro.control.*, maestro.form.*"%>

<html>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<% 
fleteForm flete_form = (fleteForm)request.getAttribute("fleteForm");
%>
<div class="titulo">Gestion de Datos de un Flete</div>
<br>
<html:form action="fleteAction.do?app=maestro&modulo=flete_datos"
	method="post">
	<html:hidden property="opcion" />
	<table align="center" border="0" width='90%' cellpadding="3" cellspacing="0" class="tabla_datos"> 
		<tr>
			<td>Codigo del sistema
			<td><html:text property="flecodsx" readonly="true" styleClass="readonly" />
		<tr>
		<%
			gsttransportadora gtrans = new gsttransportadora();
			request.setAttribute("lista", gtrans.getlistatransportadora());
		%>	
			<td>Transportadora
			<td><html:select property="fletransportadora">
					<html:options collection="lista"  
					property="transpcodsx" labelProperty="transpnombre"   />
				</html:select>* <html:errors
				property="fletransportadora" />
				
			
		<tr>
		
		<%
			gstcliente gcliente = new gstcliente();
			request.setAttribute("lista", gcliente.getlistacliente());
		%>
			<td>Origen Cliente
			<td><html:text property="flecliente" readonly="true" styleClass="readonly"></html:text>
			    <html:text property="nomcliente" readonly="true" styleClass="readonly" /> 
				<html:errors property="flecliente" />
			
				<% 


			String[] parametros = new String[] {   "0", ""};
			String[] parametros_total = new String[] { ""};
			String[] campos_form= new String[] {"flecliente"};
			String[] campos_pick = new String[] {"clicodsx"};
			String[] campos_entity= new String[] {"clicodsx", "clinombre" };
			String[] nombres_campos_entity= new String[] { "Codigo",  "Nombre"};
			String formulario = "fleteForm";
			String metodo = "getlistacliente"; 
			String metodoTotal = "getTotalPaginaslistacliente";
			%> <%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
			<popup:boton clase="maestro.control.gstcliente" entidad="maestro.entity.cliente" 
					formulario="<%=formulario %>" metodo="<%=metodo %>"
					metodo_total="<%=metodoTotal %>"
					parametros="<%= parametros %>" campos_form="<%= campos_form %>"
					campos_pick="<%= campos_pick %>"
					parametros_total="<%= parametros_total %>" 
					campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" /> 
			
		
	    <tr>
		    <td>Destino Sucursal 
		    <td><html:hidden property="flesucursal" />
		        <html:text  property="nomsucursal" readonly="true" styleClass="readonly" />
		        *<html:errors property="flesucursal"  /> 
		              <% 
						String cliente = "'  +  fleteForm.flecliente.value    +  '";
						parametros = new String[] {  cliente, "0", ""};
						parametros_total = new String[] {cliente, ""};
						campos_form= new String[] {"flesucursal", "nomsucursal"};
						campos_pick = new String[] {"succodsx",  "sucnombre" };
						campos_entity= new String[] {"succodsx", "sucnombre" };
						nombres_campos_entity= new String[] {"Codigo", "Nombre"};
						formulario = "fleteForm";
						metodo = "getlistasucursalByCliente"; 
						metodoTotal = "getTotalPaginaslistasucursalByCliente";
						%> <%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
						<popup:boton clase="maestro.control.gstsucursal" entidad="maestro.entity.sucursal" 
								formulario="<%=formulario %>" metodo="<%=metodo %>"
								metodo_total="<%=metodoTotal %>"
								parametros="<%= parametros %>" campos_form="<%= campos_form %>"
								campos_pick="<%= campos_pick %>"
								parametros_total="<%= parametros_total %>" 
								campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" /> 
								
		<tr>
			<td>Tipo de Vehiculo
			<td>
			 <% 
			 	if(flete_form != null) { %>  
			 	<html:text property="fletipoveh" readonly="true" styleClass="readonly" />* <html:errors property="fletipoveh" />
			 <%} else { %>
		       <html:select property="fletipoveh" >
		        <html:option value="TURBO">TURBO</html:option>
		        <html:option value="SENCILLO">SENCILLO</html:option> 
		        <html:option value="MINIMULA">MINIMULA</html:option>
		        <html:option value="MULA">MULA</html:option> 
		        <html:option value="VEHICULO PROPIO">VEHICULO PROPIO</html:option> 
		       </html:select> 
			<% } %>
		<tr>
			<td>Costo
			<td><html:text property="flevalor" />* <html:errors property="flevalor" />
				
				
		<tr align="center">
			<td colspan="2"><html:submit value="Grabar" disabled='<%= !gstpermiso.getEscritura(session,  "maestro", "flete_datos.jsp") %>' />
						<html:button property="cancelar" onclick="window.location='main.jsp?app=maestro&modulo=flete'" value="Cancelar"/>
	</table>
</html:form>
</html>
