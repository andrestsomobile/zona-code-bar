
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		    pageEncoding="ISO-8859-1" import="administracion.control.*,maestro.control.*" %>  
		<html> 
		
				
<%
administracion.form.parametroForm parametroform = (administracion.form.parametroForm)request.getAttribute("parametroForm");
gstcliente gcli = new gstcliente();

gstgrupo ggr = new gstgrupo();
%>		
		<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %> 
 <%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%> 

		<div class="titulo"> Gestion de Parametros </div> 
		<br> 
		<html:form action="bodegaAction.do?app=administracion&modulo=parametrizacion_bodega" method="post" > 
		<html:hidden property="opcion" styleId="opcion"/> 
		<table align="center" border="0" width='90%' cellpadding="3" cellspacing="0" class="tabla_parametro"> 
			<tr> 
				<td colspan="2"> Digite los datos de un usuario, debe ingresar el nombre, login, clave, mail (no obligatorio) , 
				grupo al que pertenece  y compania  a la que pertenece. 
			<tr> 
				<td>Codigo del sistema
				<td><html:text property="pk_parametro"  readonly="true" styleClass="readonly"  /> (Asignado por el Sx - No Editable)
			<tr> 
				<td>Nombre
				<td><html:text property="nombre"  />* <html:errors property="nombre" />
			<tr> 
				<td>Descripcion
				<td><html:text property="descripcion"  />* <html:errors property="descripcion"/>
			<tr> 
				<td>Valor
				<td><html:password property="valor"  />* <html:errors property="valor"/>
			
			<tr> 
				
				<% if(true) { 
			request.setAttribute("lista", ggr.getlistagrupo());
			%>
				<html:select property="usugrupo" onchange="setcia()">
					<html:options collection="lista"  
					property="gcodsx" labelProperty="gnombre"   />
				</html:select>
			<% } else  { %>	 
				<html:hidden property="usugrupo" value="<%= usuform.getusugrupo() %>"/>
				<input type="text" value="<%= ggr.getgrupo(usuform.getusugrupo() ).getgnombre()%>"  readonly="true" class="readonly">
			<% } %>
			<tr> 
				<td>Compañia
				<td>
				<% if(usuform==null  || ( usuform!=null && !usuform.getusugrupo().equals("3")) ) {  %>
				<span id="cia" style="display:none"  >
					<% 
						gstcompania gcia = new gstcompania();
						request.setAttribute("listaCompania", gcia.getlistacompania());
					%>	
					
					<html:select styleId="usucompania" property="usucompania"  >
					<html:options collection="listaCompania"  
					property="comcodsx" labelProperty="comnombre"   />
				</html:select>
				</span>
			<span id="cliente" style="display:none"  >
		  	<br> 
				Cliente:  	<html:text readonly="true" styleClass="readonly" property="usucodcliente" />		
							<%
				String[] parametros = new String[] {"'+ document.usuarioForm.usucompania.options[usuarioForm.usucompania.selectedIndex].value + '", "0", ""};
				String[] parametros_total = new String[] {"'+  document.usuarioForm.usucompania.options[usuarioForm.usucompania.selectedIndex].value + '", ""};
				String[] campos_form= new String[] {"usucodcliente"};
				String[] campos_pick = new String[] {"clicodsx"};
				String[] campos_entity= new String[] {"clicodsx", "clinombre", "clinit", "clidireccion"};
				String[] nombres_campos_entity= new String[] {"Consec. Cliente", "Nombre", "NIT", "Direccion"};
				%>
				 <%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
				<popup:boton clase="maestro.control.gstcliente" entidad="maestro.entity.cliente" 
						formulario="usuarioForm" metodo="getlistaclienteByEmpresa"
						metodo_total="getTotalPaginaslistaclienteByEmpresa"
						parametros="<%= parametros %>" campos_form="<%= campos_form %>"
						campos_pick="<%= campos_pick %>"
						parametros_total="<%= parametros_total %>" 
						campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" />
			
			</span>		
			<% } else  {  %> 
			<html:hidden property="usucodcliente" />		
			<% maestro.entity.cliente cli = gcli.getcliente( usuform.getusucodcliente());  %>
			<input type="text" value="<%= cli==null ? "" :  cli.getclinombre()%>" readonly="true" class="readonly">
			<% } %>
			*<html:errors property="usucodcliente"/>	
			<tr align="center"> 
				<td colspan="2"><html:submit  value="Grabar" disabled='<%= !gstpermiso.getEscritura(session,  "administracion", "usuario_datos.jsp") %>' /> 
						 <html:button property="cancelar" onclick="window.location='main.jsp?app=administracion&modulo=usuario'" value="Cancelar"/>
		</table> 		</html:form> 		
		
		
		</html> 
		
