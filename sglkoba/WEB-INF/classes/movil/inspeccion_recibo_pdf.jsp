<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,ingreso.control.*,ingreso.entity.*,ingreso.form.*, maestro.control.*, maestro.entity.*,administracion.entity.*,util.Fecha"%>

<html>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<script type="text/javascript">

function generarpdf(lctrafcodsx,trafcodsx) { 
	window.location = "traficoAction.do?opcion=generaPdfInspeccionRecibo&lctrafcodsx="+lctrafcodsx+"&trafcodsx="+trafcodsx;
}

</script>

<%
gsttrafico gtraf = new gsttrafico();
gstcontenedor_trafico gcont = new gstcontenedor_trafico();
gstlote_trafico glt = new gstlote_trafico();
gstlote_contenedor_trafico glct = new gstlote_contenedor_trafico();


gstcompania gcomp = new maestro.control.gstcompania();
gsttransportadora gtransp = new maestro.control.gsttransportadora();
gstproducto gprod = new maestro.control.gstproducto();

String trafcodsx = request.getParameter("trafcodsx");
String lctrafcodsx = request.getParameter("lctrafcodsx");

empresa emp = new administracion.control.gstempresa().getempresa("4");

trafico traf = gtraf.gettrafico(trafcodsx);
lote_contenedor_trafico lct = glct.getlote_contenedor_trafico(lctrafcodsx);
lote_trafico lt = glt.getlote_trafico(lct.getlctraflote());
contenedor_trafico ct = gcont.getcontenedor_trafico(lct.getlctrafcontenedor());
producto pro = gprod.getproducto(lt.getltrafcodproducto());
%>

<table align="center" border="0">
	<tr>
		<td><img src="./disenno/images/logo.gif" width="120" height="120">
		<td align="center"><b> INSPECCION DE RECIBO <br>
		<%=emp.getempnombre()%> <br>
		<%=emp.getempdireccion()%><br>
		NIT <%=emp.getempnit()%> </b>
		<td><%=Fecha.getFechaSinHora()%>
</table>

<br>

<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">

	<tr>
		<td>Muelle
		<td colspan="3"><%="#"%><input type="text" >

	<tr>
		<td>Fecha
		<td><%=ct.getctrafiniciodescargue() != null ? ct.getctrafiniciodescargue():""%>&nbsp;
		<td>No. Precinto
		<td>&nbsp;
	<tr>
		<td>Hora Inicio
		<td><%=ct.getctrafiniciodescargue() != null ? ct.getctrafiniciodescargue():""%>&nbsp;
		<td>Tipo Contenedor
		<td><%=ct.getctraftamano()%>&nbsp;
	<tr>
		<td>Hora Final
		<td><%=ct.getCtraffindescargue() != null ? ct.getCtraffindescargue():""%>&nbsp;
		<td>No. Contenedor
		<td><%=ct.getctrafnumero()%>&nbsp;
	<tr>
		<td>Importaci&oacute;n
		<td colspan="3"><%=traf.gettrafnumdta()%>&nbsp;
	<tr>
		<td>1. Identificaci&oacute;n del Veh&iacute;culo
		<td colspan="3">
	<tr>
		<td>Nombre Conductor
		<td><%=ct.getCtrafconductor()%> &nbsp;
		<td>Cedula Conductor
		<td><%=ct.getCtrafcedula()%> &nbsp;
	<tr>
		<td>Placa Veh&iacute;culo
		<td colspan="3"><%=ct.getctrafplaca()%> &nbsp;
	<tr>
		<td>2. Mercanc&iacute;a a Recibir
		<td colspan="3">&nbsp;		
	<tr>
		<td>Producto
		<td><%=pro != null ? pro.getpromodelo(): ""%>&nbsp;
		<td>Descripci&oacute;n
		<td><%=pro != null ? pro.getprodescripcion() : ""%>&nbsp;		
	<tr>
		<td>Vencimiento
		<td colspan="3"><%=lt.getLtrafvencimiento()%>&nbsp;
	<tr>
		<td>Lote
		<td colspan="3"><%=lt.getLtraflote()%>&nbsp;		
	<tr>
		<td>Estibas
		<td><input type="text" >
		<td>Cajas
		<td><%=lct.getlctrafcantidad()%>&nbsp;	
	<tr>
		<td>Saldo
		<td colspan="3"><input type="text" >
	<tr>
		<td>Novedades A(O) S(O) F(O) D(O)
		<td colspan="3"><input type="text" >		
	<tr>
		<td>Recuparadas
		<td colspan="3"><input type="text" >	
	<tr>
		<td>TOTAL UMP
		<td colspan="3"><%=lct.getlctrafcantidad()%>&nbsp;	
	<tr>
		<td>Estibas Requeridas
		<td colspan="3"><input type="text" >	
	<tr>
		<td>Peso Estiba Vacia	
		<td colspan="3"><input type="text" >					
	<tr>
		<td>Peso Total Estibas Vacias
		<td colspan="3"><input type="text" >	
	<tr>
		<td>Peso estiba Paletizada
		<td colspan="3"><input type="text" >					
	<tr>
		<td>Peso por UMP
		<td colspan="3"><input type="text" >	
	<tr>
		<td>PESO NETO PRODUCTO
		<td colspan="3"><input type="text" >					
	<tr>
		<td>OBSERVACIONES
		<td colspan="3"><input type="text" >			
</table>

<br>

<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">

	<tr>
		<td colspan="3">3. Condiciones sanitarias del Veh&iacute;culo
	<tr>
		<td>PUNTOS A EVALUAR
		<td>CALIFICACION
		<td>OBSERVACIONES
	<tr>
		<td>El personal de descargue cuenta con ARL vigente
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El personal de descargue cuenta con Carn� de manipulaci�n de ...
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El personal de descargue usa los elementos de proteccio�n personal
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El veh�culo cuenta con certificado de fumigaci�n no menor a...
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El personal de descargue cuenta con Carn� de manipulaci�n de alimentos vigente
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El piso, techo y paredes del veh�culo se encuentran limpias y en...
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>El veh�culo contiene materiales extra�os o sustnacias qu�micas que...
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>Si el veh�culo tiene thermoking registra la temperatura de llegada
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>Estado General del producto
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>Revisiones aleatorias efectuadas
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>*UMP Recibidas
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>*UMP Revisadas
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>*Tabla Nutricional
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>*Informaci�n Importaci�n CINC
		<td><input type="text" >
		<td><input type="text" >
	<tr>
		<td>Calificaci�n/cumplimiento: SI Cumple; NO Cumple
		<td><input type="text" >
		<td><input type="text" >
	
</table>		
<br>
<br>
<br>
<table align="center" border="0" width="80%">
	<tr>
		<td align="center"><input type="text" ><br>----------------------------<BR>
		RECIBIDO POR</td>
		<td align="center"><input type="text" ><br>----------------------------<BR>
		Conductor&nbsp;<%=ct.getCtrafconductor()%>
	</tr>
</table>
<div align="center">
<input type="button" value="Generar PDF" onclick="generarpdf(<%=lctrafcodsx %>,<%=trafcodsx %>)" style="color: blue" >
</div>

</html>

