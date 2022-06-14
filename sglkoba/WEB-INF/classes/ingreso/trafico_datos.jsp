
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="administracion.control.gstpermiso, util.*,ingreso.control.*, maestro.control.*, java.util.*"%>

<html>
<%@ taglib uri="/WEB-INF/hiddenpaneltag.tld" prefix="h"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% ingreso.form.traficoForm traf_form =(ingreso.form.traficoForm)request.getAttribute("traficoForm"); %>
<div class="titulo">Gestion de Datos de un Trafico</div>
<br>
<html:form action="traficoAction.do?app=ingreso&modulo=trafico_datos"
	method="post">
	<html:hidden property="opcion" />


<table align="center" border="0" width='90%' cellpadding="3" cellspacing="0" class="tabla_datos"> 
		<tr>
			<td>Codigo
			<td><html:text property="trafcodsx" size="8" readonly="true"
				styleClass="readonly" />
			<td>Compania
			<td><%
    Collection lista = new gstcompania().getlistacompania();
    request.setAttribute("lista", lista);
    %> <html:select property="trafcompania">
				<html:options collection="lista" property="comcodsx"
					labelProperty="comnombre" />
			</html:select>
			<td>Fecha Creacion
			<td><html:text property="traffechacreacion" size="15" readonly="true"
				value="<%= Fecha.getFecha() %>" styleClass="readonly" />
		<tr>
		    <td>Recibo de Mercanc�a
			<td><html:text property="trafpedido" /> <html:errors property="trafpedido" />
			<td># BL
			<td><html:text property="trafdocumento" /> <html:errors
				property="trafdocumento" />
			<td># Importaci�n
			<td><html:text property="trafnumdta" />  <html:errors
				property="trafnumdta" />
		<tr>
			<td>Procedencia
			<td><html:text property="trafprocedencia" /><html:errors
				property="trafprocedencia" />
			<td>Fecha Arribo Pto
			<td colspan="3"><html:text property="traffechaarribopuerto" size="15"
				styleClass="readonly" readonly="true" /> <html:errors property="traffechaarribopuerto" /> <a
				href="javascript:show_calendar('traficoForm.traffechaarribopuerto');">
			<img src="./disenno/images/calendar.gif" class="lupita"> </a>
			<html:hidden property="trafembarque" value="0"/>
    
	<tr>
			
	    <td>Origen 
	    <td ><html:text property="traforigen" /> <html:errors
					property="traforigen" />
	    <td>TRM
				<td colspan="3"><html:text property="traftrm" /> 
				<html:errors property="traftrm" />
		<tr>
			<td>FOB
			<td><html:text property="traffob" readonly="true"
				styleClass="readonly" /> <html:errors property="traffob" />
			<td>Fletes / Seguros
			<td><html:text property="traffletesseguro" />

			<td>CIF
			<td><html:text property="trafcif" readonly="true"
				styleClass="readonly" /> <html:errors property="trafcif" />
		<tr>
			
			
		<tr>
			<td>Sobordo No
			<td><html:text property="trafnumsobordo" /> <html:errors property="trafnumsobordo" />
			<td>Fecha Sobordo
			<td><html:text property="traffechasobordo" size="15" readonly="true"
				styleClass="readonly" /> <html:errors property="traffechasobordo" /> <a
				href="javascript:show_calendar('traficoForm.traffechasobordo');"> <img
				src="./disenno/images/calendar.gif" class="lupita"> </a>
			<td>Fecha Descargue
			<td><html:text property="traffechadescargue" size="15"
				readonly="true" styleClass="readonly" /> <html:errors property="traffechadescargue" />
				<a
				href="javascript:show_calendar('traficoForm.traffechadescargue');">
			<img src="./disenno/images/calendar.gif" class="lupita"> </a>
		
			<html:hidden property="traftransportadora" />
	
		<tr>
		
		    <td>Estado
		    <td >
		     <%if(traf_form==null) { %>
		     <html:text property="trafestado" value="TRAMITE" readonly="true" styleClass="readonly"/>
		     
		       <% } else if(traf_form.getTrafestado().equals("TRAMITE")) { %>
			<html:select property="trafestado"  > 
		    	<html:option value="TRAMITE">TRAMITE</html:option>
		    	<html:option value="FINALIZADO">FINALIZADO</html:option>
		    </html:select>
		         <% } else {%>
		  <html:text property="trafestado" value="FINALIZADO" readonly="true" styleClass="readonly"/>
		  <%
		  	//coloco el enlace con permiso de administracion para que lo active:
			if(gstpermiso.getAdministracion(session, "ingreso", "trafico_datos.jsp") ) { 
		  %>
		  
		  <% } } %>
		  <html:errors property="trafestado" />
		
			
			<td>Peso Bruto
			<td><html:text property="trafpesobruto" readonly="true" size="10"
				styleClass="readonly" /> KGM<html:errors property="trafpesobruto" />
				
			<td>Peso Neto
			<td><html:text property="trafpesoneto" readonly="true" size="10"
				styleClass="readonly" />KGM <html:errors property="trafpesoneto" />
		<tr>
			<td>Observaciones
			<td><html:textarea property="trafobservaciones" />
			<td colspan="2"># Veh�culos que entrega la mercancia
			<td colspan="2"><html:text property="trafmotonave" /> <html:errors property="trafmotonave" />
			
			
			
		<tr align="center">
			<td colspan="6">
			<% if( gstpermiso.getEscritura(session,  "ingreso", "trafico_datos.jsp")) {
				
				if( traf_form==null ||  traf_form!=null && traf_form.getTrafestado().equals("TRAMITE")) {
					%>
			<html:submit value="Grabar"  /> 
			<%} else if( traf_form!=null && traf_form.getTrafestado().equals("FINALIZADO")) { %>
			<a href="traficoAction.do?opcion=activar&codsx=<%=traf_form.gettrafcodsx() %>">ACTIVAR TRAFICO </a>
			
			<% } } %> &nbsp;
			
			
			  <tr align="center"> 
    <td colspan="6">     
    <% if(traf_form!=null ) {  %>
    	<a href="main.jsp?app=ingreso&modulo=subirAdjuntoTrafico&clave=<%=traf_form.gettrafcodsx() %>">Cargar Documentos Trafico </a>
    <% } %>  
</table>

</html:form> 		</html> 