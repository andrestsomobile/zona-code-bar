<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" %>
	<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %> 

<script type="text/javascript">
function validar(){
var mens ='';
//valido cia inicial
	if( document.reportesForm.cia_ini.value=='') mens +='-Debe Escribir la Cia inicial\n';
	if(isNaN ( document.reportesForm.cia_ini.value) )mens += '-La Cia Inicial debe ser el codigo numerico\n';
//valido cia final
	if( document.reportesForm.cia_fin.value=='') mens +='-Debe Escribir la Cia Final\n';
	if(isNaN ( document.reportesForm.cia_fin.value) )mens += '-La Cia Final debe ser el codigo numerico\n'; 
//valido fechas:	
	if( document.reportesForm.fecha.value=='') mens +='-Debe Escribir la Fecha\n';
//respuesta
	if(mens!='') {
		alert(mens);
		return false;
	}else 
		return true;
}
</script>	

<div class="TITULO"> Reportes de Inventario  <br>Inventario de Unidades Resumido</div>
 <br>
<html:form action="reportesAction.do?tipo=inventarios&reporte=inventario_unidades_resumen" method="post"  onsubmit="return validar()"> 
<table >
<%@include file="companias.jsp"%> 
<%@include file="fecha.jsp"%>  
<%@include file="tipo_reporte.jsp"%>  

<tr> <td colspan="8" align="center"><html:submit value="Ver Reporte" />
</table>
</html:form>