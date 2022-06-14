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
gstcliente gcli = new gstcliente();

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
				<td colspan="2"> Digite los datos de un usuario, debe ingresar el nombre, login, clave, mail (no obligatorio) , 
				grupo al que pertenece  y compania  a la que pertenece. 
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
				<td><html:select property="ubicacion"> <html:errors property="ubicacion"/> </html:select>
				
			<tr> 
				
				<td>Tipo de elemento
				<td><html:select property="tipo_elemento" > <html:errors property="Tipo_elemento"/> </html:select>
			
			<tr>
			
			
				<tr align="center"> 
				<td colspan="2"><html:submit  value="Grabar" /> 
						 <html:button property="cancelar" onclick="window.location='main.jsp?app=administracion&modulo=parametrizacion'" value="Cancelar"/>
		</table> 		</html:form> 		
		
		
		</html> 