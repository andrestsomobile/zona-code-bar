<html>
	<head>

        <title>SGL :: Sistema de Gestion Logistico -- KOBA Colombia S.A.S</title>
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css"> 
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="./disenno/templates/Movil/styles.css" rel="stylesheet" type="text/css">

        <script src="./jQuery/jQuery-2.1.4.min.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
        
		<script> 
		$(function(){
		  $("#header").load("./disenno/templates/Movil/header.jsp"); 
		  $("#footer").load("./disenno/templates/Movil/footer.html"); 
		});
		</script> 
		
	</head>
	
	<body>
		<div id="header"></div>
		
		<!--Remaining section-->
		<div class="container">
	
			<div class="titulo"> Modulo de Reubicaciones Movil (Offline) </div>
			<br>
			<br>

			<table width="90%" border="0" align="center" cellpadding="3" cellspacing="0" class="tabla_listas">
			  <tr> 
			    <td colspan="4"><br>
			      Con este m&oacute;dulo puede consultar informaci&oacute;n de inventario<br>
			      <br>
			    </td>
			  </tr>
			  <tr> 
			    <th width="33%">Inventario</th>
			    <th width="33%">Reubicar por Cantidad</th>
			  </tr>
			  
			  <tr valign="top"> 
			    <td>Captura la informaci&oacute;n para realizar cambios de Posici&oacute;n al Inventario <br>
			      <br>
			     
			      <a href="inventario.jsp">&gt;&gt; Ingresar</a><br>
			      <br>
			      
			    </td>
			    <td>Captura de informaci&oacute;n para realizar Reubicaciones por Cantidad
			      <br><br>
			     
			      <a href="reubicacion.jsp">
			      &gt;&gt; Ingresar</a>
			      
			      </td>
			  </tr>
			</table>		
		
		<!--End Remaining section-->	
		</div>
			
		<div id="footer"></div>
	</body>
</html>

