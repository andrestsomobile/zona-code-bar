<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" 
    import="administracion.control.*, administracion.entity.*, maestro.control.*, maestro.entity.*, util.Fecha, registro.control.*, registro.entity.*, java.util.*"%> 

<html>
<%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld"  prefix="html"%>

<head>
    <script src="files/movil/jquery.min.js"></script>
	<link href="files/movil/dist/attention.css" rel="stylesheet">
	<script src="files/movil/dist/attention.js"></script>
	<style>
		.myButton {
			box-shadow: 0px 0px 0px 2px #9fb4f2;
			background:linear-gradient(to bottom, #7892c2 5%, #476e9e 100%);
			background-color:#7892c2;
			border-radius:10px;
			border:1px solid #4e6096;
			display:inline-block;
			cursor:pointer;
			color:#ffffff;
			font-family:Arial;
			font-size:18px;
			padding:15px 76px;
			text-decoration:none;
			text-shadow:0px 1px 0px #2a59d1;
		}
		.myButton:hover {
			background:linear-gradient(to bottom, #476e9e 5%, #7892c2 100%);
			background-color:#476e9e;
		}
		.myButton:active {
			position:relative;
			top:1px;
		}	
		.myButton:disabled {
			background:linear-gradient(to bottom, #cccccc 5%, #aba9a9 100%);
			background-color:#cccccc;
		}
	</style>
	
	<script type="text/javascript">
		
	function irOpcion(valor_permiso, modulo, accion) {
		if (valor_permiso) {
			var realempleado = document.getElementById("empcodsx");
			if (modulo=='registroFotografico') {
				location.href = "main_movil.jsp?app=movil&modulo=registroFotografico"
			} else if (modulo=='inspeccion_recibo') {
						location.href = "main_movil.jsp?app=movil&modulo=inspeccion_recibo"
			} else{
				location.href = "registro_almacenamientoAction.do?opcion=crear&accion=" + accion + "&ruta_accion=" + modulo + "&realempleado=" + realempleado.value;
			}
		} else {
			alert("No tienes permiso para acceder a esta opcion");
		}
	}
	
	function reanudarAccion(realcodsx) {
		if (confirm("Esta seguro de Reanudar la accion?")) {
			location.href = "registro_almacenamientoAction.do?opcion=set&realcodsx=" + realcodsx;
		} 
	}
	
	function cerrarAccion(realcodsx) {
		if (confirm("Esta seguro de Terminar la accion?")) {
			location.href = "registro_almacenamientoAction.do?opcion=cerrar&realcodsx=" + realcodsx;
		} 
	}
	
	function deleteAccion(realcodsx) {
		if (confirm("Esta seguro de Eliminar la accion?")) {
			location.href = "registro_almacenamientoAction.do?opcion=delete&realcodsx=" + realcodsx;
		} 
	}
	
	</script>		
</head>
<%  
  gstpermiso gper = new gstpermiso();
  gstempleado gempleado = new gstempleado();
  gstregistro_almacenamiento greg = new gstregistro_almacenamiento();
  String mensaje = ""; 
  usuario usu = (usuario)session.getAttribute("usuario");
  String grupo = usu.getusugrupo();
  String cedula = request.getParameter("cedula");
  empleado _empleado = gempleado.getempleado_by_ukey(cedula);
  
  if (cedula != null && _empleado == null) {
	  mensaje = "C&eacute;dula " + cedula + " no esta registrada como empleado en el sistema.";
  }
  Collection lista_pendientes = null;
  if (_empleado != null) {
	  lista_pendientes = greg.getlistaregistro_almacenamiento_pendientes(_empleado.getempcodsx());
  }
  request.setAttribute("lista_pendientes", lista_pendientes); 
  String deshabilitado;
  String soloLectura;
  if (_empleado != null) {
	  deshabilitado = "";
	  soloLectura = "readonly";
  } else {
	  deshabilitado = "disabled"; 
	  soloLectura = "";
  }

%>

<div class="titulo"> Modulo de Reubicaciones Movil </div>
<br>
<br> 
		 
<form action=""></form>
<h3>Bienvenido Usuario: <%=usu != null ? usu.getusunombre():""%> </h3>

<form id="myForm" name="myForm" method='post' action='main_movil.jsp?app=movil&modulo=default'> 	 	
	<table width="auto" border="0" align="center" cellpadding="3" cellspacing="0" >
	    
		<tr>
			<td>C&eacute;dula: </td>
			<td>
				<input type="text" name="cedula" id="cedula" size="30" <%=soloLectura%> style='background-color: white;color:black' value="<%=_empleado != null ? _empleado.getempcedula() : "" %>" />
				<input type="hidden" name="empcodsx" id="empcodsx" value="<%=_empleado != null ? _empleado.getempcodsx() : "" %>" >
			</td>
			<td><input type='submit' name='Iniciar' value='Iniciar' class="myButton" style="font-size:12px;padding:5px 38px;"></td>
		</tr>
		<tr>
			<td>Empleado: </td>
			<td colspan="2"><input type="text" id="nombre" size="50" readonly styleClass="readonly" style='background-color: white;color:black' value="<%=_empleado != null ? _empleado.getempnombre() : mensaje %>" /></td>
		</tr>
	</table>
	
	
	
	<% if (lista_pendientes != null && !lista_pendientes.isEmpty()) { %>
	<h4>Tienes acciones pendientes de cerrar</h4>
	<table align='center'  width='99%' border='1' cellspacing='0' cellpading='0' class=""> 
 	<tr>  
	 <th> Registro</th>
	 <th> Accion</th>
	 <th> Estado</th>
	 <th> Fecha</th>
	 <th> Hora Inicio </th>
	 <th> Reanudar </th>
	 <th> Terminar </th>
	 <th> Eliminar </th>
	</tr>
	
	<logic:iterate id="temp" name="lista_pendientes" type="registro_almacenamiento" >
    <tr align='center'>
		<td> <%= temp.getRealcodsx() %> </td>
		<td> <%= temp.getRealaccion() %> </td>
		<td> <%= temp.getRealestado() %> </td> 
		<td> <%= temp.getRealfecha() %> </td> 
		<td> <%= temp.getRealhoin() %> </td>
		<td> <a href="javascript:reanudarAccion(<%= temp.getRealcodsx()%>)" title="Reanudar Accion"><img src="./disenno/images/comenzar.png" border="0" width="20" heigth="20" ></a></td>
		<td> <a href="javascript:cerrarAccion(<%= temp.getRealcodsx()%>)" title="Terminar Accion"><img src="./disenno/images/ok.png" border="0" width="20" heigth="20" ></a></td>
		<td> <a href="javascript:deleteAccion(<%= temp.getRealcodsx()%>)" title="Eliminar Accion"><img src="./disenno/images/substract.gif" border="0" width="20" heigth="20" ></a> </td> 
	</tr>
    </logic:iterate>
    </table>	 
	<% } //else { %>
	<h4>Que tarea vas a realizar ?</h4>
	<table width="90%" border="0" align="center" cellpadding="3" cellspacing="0" >
	
		<tr>
			<td><button id="btnInventario" type="button" class="myButton" <%= deshabilitado%>
			        onclick='irOpcion(<%= gper.verIzquierdo(grupo, "movil", "inventario.jsp")%>, "inventario", "INVENTARIO MANUAL")'>Consultar Inventario</button></td>
			<td><button id="btnReubicar" type="button" class="myButton" <%= deshabilitado%>
			        onclick='irOpcion(<%=gper.verIzquierdo(grupo, "movil", "reubicacion.jsp")%>,"reubicacion", "REUBICACION MANUAL")'>Reubicar Posiciones</button></td>
		</tr>
		<tr>
			<td><button id="btnAlmacenarQR" type="button" class="myButton" <%= deshabilitado%>
			        onclick='irOpcion(<%=gper.verIzquierdo(grupo, "movil", "descargueqr.jsp")%>,"descargue_qr", "ALMACENAMIENTO X QR")'>Almacenamiento x QR</button></td>
			<td><button id="btnAlmacenarManual" type="button" class="myButton" <%= deshabilitado%>				
					onclick='irOpcion(<%=gper.verIzquierdo(grupo, "movil", "descargueqr.jsp")%>,"descargue", "ALMACENAMIENTO MANUAL")'>Almacenamiento Manual</button></td>
		</tr>
		
		<tr>
			<td><button id="btnRegistroFotografico" type="button" class="myButton" 
			        onclick='irOpcion(<%=gper.verIzquierdo(grupo, "movil", "registroFotografico.jsp")%>,"registroFotografico", "registroFotografico")'>Registro Fotografico</button></td>
			<td><button id="btnAlmacenarManual" type="button" class="myButton" 			
					onclick='irOpcion(<%=gper.verIzquierdo(grupo, "movil", "inspeccion_recibo.jsp")%>,"inspeccion_recibo", "inspeccion_recibo")'>Inspeccion Recibo</button></td>
		</tr>
		

	</table>
<% //} %>
</form> 
	
</html>