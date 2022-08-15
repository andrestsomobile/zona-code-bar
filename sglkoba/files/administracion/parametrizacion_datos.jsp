<%@page import="administracion.form.parametroForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		    pageEncoding="ISO-8859-1" import="administracion.control.*,maestro.control.*" %>  

		<html> 
		<script type="text/javascript">
		//function setcia() {
				//if(document.parametroForm.tipo_elemento.options[parametroForm.tipo_elemento.selectedIndex].value=='3'
					//	||			
						//document.parametroForm.tipo_elemento.options[parametroForm.tipo_elemento.selectedIndex].value=='4'	
				//) {
				
				
				//if(document.parametroForm.tipo_elemento.options[parametroForm.tipo_elemento.selectedIndex].value=='3') {
				//	document.getElementById('cliente').style.display='block';
				//	}
				//	else {
				//	document.getElementById('cliente').style.display='none';
				//	}
				//	document.getElementById('cia').style.display='block';
				
				//}else {
				//  document.getElementById('cia').style.display='none';
				 // document.getElementById('cliente').style.display='none';
				 // document.usuarioForm.usucompania.value='0';
				//}
		//	}
			
		</script>
		
				
		
<%
administracion.form.parametroForm parametroform = (administracion.form.parametroForm)request.getAttribute("parametroForm");
gstparametro gpar = new gstparametro();

gstparametro ggr = new gstparametro();
%>		
		<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %> 
 <%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%> 

		<div class="titulo"> Gestion de Datos de un parametro </div> 
		<br> 
		<html:form action="parametroAction.do?app=administracion&modulo=parametrizacion_datos" method="post" > 
		<html:hidden property="opcion" styleId="opcion"/> 
		<table align="center" border="0" width='90%' cellpadding="3" cellspacing="0" class="tabla_datos"> 
			<tr> 
				<td colspan="2"> Digite los registros de un parametro, debe ingresar el nombre, descripcion (no obligatorio), valor, ubicacion (no obligatorio) y tipo de elemento(no obligatorio)
			<tr> 
				<td>Codigo del parametro
				<td><html:text property="pk_parametro"  readonly="true" styleClass="readonly"  /> (Asignado por el Sx - No Editable)
			<tr> 
				<td>Nombre
				<td><html:text property="nombre"  />* <html:errors property="nombre" />
			<tr> 
				<td>Descripcion
				<td><html:text property="descripcion"  />* <html:errors property="descripcion"/>
			<tr> 
			
				<td>Valor
				<td><html:text property="valor"  />* <html:errors property="valor"/>
			<tr> 
				
				<td>Ubicacion
				<td><html:text property="ubicacion"> <html:errors property="ubicacion"/> </html:text>
				
			<tr> 
				
				<td>Tipo de elemento
				<td><html:text property="tipo_elemento" > <html:errors property="tipo_elemento"/> </html:text>
			
			<tr>
			
			
				<tr align="center"> 
				<td colspan="2"><html:submit  value="Grabar" /> 
						 <html:button property="cancelar" onclick="window.location='main.jsp?app=administracion&modulo=parametrizacion'" value="Cancelar"/>
		</table> 		</html:form> 		
		
		
		</html> 