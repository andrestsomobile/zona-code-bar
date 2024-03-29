
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="administracion.control.gstpermiso"%>

<html>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<div class="titulo">Gestion de Datos de una Compa�ia</div>
<br>
<html:form action="companiaAction.do?app=maestro&modulo=compania_datos"
	method="post">
	<html:hidden property="opcion" />
	
	<%
	maestro.form.companiaForm cf =(maestro.form.companiaForm) request.getAttribute("companiaForm");
	
	%>
	<table align="center" border="0" width='90%' cellpadding="3" cellspacing="0" class="tabla_datos"> 
		<tr> 
				<td  colspan="2">Defina los datos de la compania: direccion, nombre, fax ,ciudad, mail,
				 gerente, contacto,cargo y telefono.
				<br><br>
		<tr>
			<td>Codigo del sistema
			<td><html:text property="comcodsx"  readonly="true" styleClass="readonly" />Automatico, generado por la app
		<tr>
			<td>Nombre
			<td><html:text property="comnombre" />* <html:errors
				property="comnombre" />
	<tr>
		<td>Direccion
			<td><html:text property="comdireccion" />* <html:errors property="comdireccion" />
		<tr>
			<td>NIT
			<td><html:text property="comnit" />* <html:errors
				property="comnit" />
				
		<tr>
			<td>Fax
			<td><html:text property="comfax" />
		<tr>
			<td>Ciudad
			<td><html:hidden property="comciudad"  />
			<input name="ciunombre" type="text" readonly="readonly" class="readonly" value="<%= cf!=null && cf.getcomciudad()!=null && !cf.getcomciudad().equals("") ? new maestro.control.gstciudad().getciudad(  cf.getcomciudad()).getciunombre() : ""%>">
					<% 


			String[] parametros = new String[] {   "0", ""};
			String[] parametros_total = new String[] { ""};
			String[] campos_form= new String[] {"comciudad", "ciunombre"};
			String[] campos_pick = new String[] {"ciucodigo", "ciunombre"};
			String[] campos_entity= new String[] {"ciucodigo", "ciunombre" };
			String[] nombres_campos_entity= new String[] { "Codigo",  "Nombre"};
			String formulario = "companiaForm";
			String metodo = "getlistaciudad"; 
			String metodoTotal = "getTotalPaginaslistaciudad";
			%> <%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
			<popup:boton clase="maestro.control.gstciudad" entidad="maestro.entity.ciudad" 
					formulario="<%=formulario %>" metodo="<%=metodo %>"
					metodo_total="<%=metodoTotal %>"
					parametros="<%= parametros %>" campos_form="<%= campos_form %>"
					campos_pick="<%= campos_pick %>"
					parametros_total="<%= parametros_total %>" 
					campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" /> 
			*<html:errors property="comciudad"/>
		<tr>
			<td>Email
			<td><html:text property="comemail" />
		<tr>
			<td>Gerente
			<td><html:text property="comgerente" />
		<tr>
			<td>Contacto
			<td><html:text property="comcontacto" />* <html:errors
				property="comcontacto" />
		<tr>
			<td>Cargo
			<td><html:text property="comcargo" />
		<tr>
			<td>Telefono
			<td><html:text property="comtelefono" />* <html:errors
				property="comtelefono" />
		<tr>
			<td>Tipo
			<td><html:select property="comtipo" >
				<html:option value="N">Normal</html:option>
				<html:option value="P">Patios</html:option>
				<html:option value="E">Empleado</html:option>
				<html:option value="O">Otros</html:option>
			</html:select>
		<tr>
			<td>Descarga Inventario
			<td><html:select property="cominventario" >
				<html:option value="A">Automatico (FIFO)</html:option>
				<html:option value="M">Manual</html:option>ion>
				<html:option value="V">Virtual</html:option>
			</html:select>
		<tr>
			<td>Utiliza Mapa
			<td><html:select property="commapa" >
				<html:option value="S">S</html:option>
				<html:option value="N">N</html:option>
			</html:select>						
		<tr align="center">
			<td colspan="2"><html:submit value="Grabar" disabled='<%= !gstpermiso.getEscritura(session,  "maestro", "compania_datos.jsp") %>' />
			<html:button property="cancelar" onclick="window.location='main.jsp?app=maestro&modulo=compania'" value="Cancelar"/>
	</table>
</html:form>
</html>
