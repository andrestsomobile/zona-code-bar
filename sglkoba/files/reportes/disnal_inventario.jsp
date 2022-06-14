<%@ page contentType="text/html; charset=iso-8859-1"
	language="java"  
	import="java.util.*,reportes.util.*,reportes.form.*"
%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %> 

<% 
disnal_inventario informe = new disnal_inventario();
disnal_inventario.reporte reporte = informe.new reporte ();

String fecha_ini = request.getParameter("fecha_ini");
String fecha_fin = request.getParameter("fecha_fin");

Collection data = null;
Iterator filasIT = null;
Iterator columnasIT = null;


String vectorTit[] = {
		"Tipo Mov",
		"Compa�ia",
		"Material",
		"Fecha Ingreso",
		"Cantidad Ingreso",	
		"Sucursal",
		"Fecha Despacho",
		"Cantidad Salida", 
		"Saldo"
		};
	 

String titulos = "";

if (fecha_ini != null && fecha_fin  != null){
	
	for(int i = 0; i < vectorTit.length; i++){
		titulos += "<td class=\"titulostabla\">"+vectorTit[i]+"</td>";
	}

	data = reporte.disnal_inventario(fecha_ini,fecha_fin);

	
}
%>

<!DOCTYPE HTML>
<html>
	<head>

		<style>
	 	.botonexcel{
			padding:5px 5px 5px 10px;
			background-color:#DDDDDD;
			border:1px solid #444444;
		}
		
		.botonexcel a{
				text-decoration:none;
				color:green;
		}
		
		#noreg{
			color:blue;
			font-weight:bold;
		}
		
		#tablaRep{
			border:1px solid black;
		}
		
		#tablaRep .titulostabla{
	 		background-color:gray;
	 		color:white;
	 		font-weight:bold;
	 	}
		
		#tablaRep td{
			border:1px dashed #E1E1E1;
			padding:5px;
		}
	 	
	 </style>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<script type="text/javascript">
		 function validar(){
			 var mens ='';
			 //valido fechas:	
			 	if( document.reportesForm.fecha_ini.value=='') mens +='-Debe Escribir la Fecha Inicial\n';
			 	if( document.reportesForm.fecha_fin.value=='') mens +='-Debe Escribir la Fecha Final\n';
			 //respuesta
			 	if(mens!='') {
			 		alert(mens);
			 		return false;
			 	}else 
			 		document.reportesForm.pagina.value = 'main.jsp?app=reportes&modulo=disnal_inventario&tipo=inventarios&fecha_ini='+document.reportesForm.fecha_ini.value+'&fecha_fin='+document.reportesForm.fecha_fin.value;	
			 		return true;
			 }
		</script>	
	
		<script type="text/javascript">
			function fnExcelReport(idtable,nameFile)
			{
			  var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
			  var textRange; var j=0;
			  tab = document.getElementById(idtable); // id of table
			
			
			  for(j = 0 ; j < tab.rows.length ; j++)
			  {
			    tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
			    //tab_text=tab_text+"</tr>";
			  }
			
			  tab_text=tab_text+"</table>";
			  tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
			  tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
			  tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params
			
			  var ua = window.navigator.userAgent;
			  var msie = ua.indexOf("MSIE ");
			
			/*  if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
			  {
			    txtArea1.document.open("txt/html","replace");
			    txtArea1.document.write(tab_text);
			    txtArea1.document.close();
			    txtArea1.focus();
			    sa=txtArea1.document.execCommand("SaveAs",true,nameFile+".xls");
			  }
			  else  */               //other browser not tested on IE 11
			  sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
			
			
			  return (sa);
			}
		</script>
		
	</head>
			

	<body>
		<header>
			<br />
			<div class="subtitulo">INVENTARIO DISTRIBUCION NACIONAL</div>
		</header>
		
		<br />
		<%
		if(fecha_ini == null || fecha_fin == null) {		
		%>
		<html:form action="reportesAction.do?tipo=inventarios&reporte=disnal_inventario&tipo_reporte=EXCELGENERADO" method="post" onsubmit="return validar()">
			<input type="hidden" name="pagina" value="" />
			<input type="hidden" name="app" value="reportes"/>
			<input type="hidden" name="modulo" value="disnal_inventario"/>
			<input type="hidden" name="tipo" value="inventarios"/>

			<table >
				<%@include file="fechas.jsp"%> 
				<tr> <td colspan="8" align="center"><html:submit value="Generar" /> </td></tr>
			</table >
			<br />	
		</html:form>	

		<%}else
	
		
		if(fecha_ini != null || fecha_fin != null){

	 		Iterator filas = data.iterator();
	 		Iterator columnas;
	 		Collection infoPorReg;
		
		%>
		<%
		String ruta = "uploads/";
		String nomarch = "DisNal_Inventario.xls";
		%>
		 <table border="1" cellpadding="0" cellspacing="0">
		   <caption> Ultimo Archivo Generado </caption>
		    <tr>
		      <th> Archivo 
		      <th> Opcion
		    </tr>
		    <tr>
		      <td nowrap="nowrap"><%=nomarch %>
		      <td nowrap="nowrap"><a href="<%=ruta+nomarch%>"> Descargar </a>           
		    </tr>
		  
		 </table>
		<br>
		
	 	<form id="reporte">
	 	    <% if (!data.isEmpty()){ %>
	 	   
	 	    <table id="tablaRep">
				<%=titulos%>
		 		<% while(filas.hasNext()){
		 			infoPorReg = (Collection)filas.next();
		 			columnas = infoPorReg.iterator();
		 		%>
		 		<tr>
		 			<%while(columnas.hasNext()){
		 				%>
		 				<td>
		 					<%=columnas.next()%>
		 				</td>
		 				<%
		 			}
		 			//fin columnas
		 			%>
		 		</tr>
		 		<%}
		 		//fin filas
		 		%>
		 	</table>
		 	<%}else{
		 		%>
		 		<label style="font-size:10pt;color:#CA3415;">NO HAY DATOS PARA MOSTRAR</label>		 		
		 		
		 	<%} %>
	 	</form>
	<%} %>
	</body>
</html>
	


