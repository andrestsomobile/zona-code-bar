
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		    pageEncoding="ISO-8859-1" import ="administracion.entity.*, nacionalizacion.entity.*, nacionalizacion.control.*, ingreso.control.*, ingreso.entity.*, java.util.*, administracion.control.gstpermiso, pedido.form.pedidoForm,pedido.control.*, maestro.control.*, maestro.entity.* " %>  

<html> 
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %> 
<%@ taglib uri="/WEB-INF/struts-logic.tld"  prefix="logic"%> 

<%!

gstpedido gped = new gstpedido();
gstreferencia_pedido grefp = new gstreferencia_pedido();
gstcompania gcomp= new gstcompania();
gstproducto gprod = new gstproducto();
gstcliente gcli = new gstcliente();
gstsucursal gsuc = new gstsucursal();
gstciudad gciud = new gstciudad();
gstdepartamento gdep = new gstdepartamento();
gstbodega gbod = new gstbodega();
gstentrada gent = new gstentrada();
gstingreso ging = new gstingreso();
gsttrafico gtraf = new gsttrafico();
gsttransportadora gtrans = new gsttransportadora();
gstnacionalizacion gnac = new gstnacionalizacion();
gstnacionalizacion_detalle gnacdet = new gstnacionalizacion_detalle();
gstlote_trafico gl = new gstlote_trafico();

%>

<%
String pedcodsx = request.getParameter("pedcodsx");
String tipo = request.getParameter("tipo"); //puede ser DETALLADO  o RESUMIDO
tipo = tipo==null ? "DETALLADO" : tipo;

pedido.entity.pedido ped = gped.getpedido(pedcodsx);
cliente cli =  gcli.getcliente(ped.getpedcliente());
//sucursal suc = gsuc.getsucursal( ped.getpedsucursal());
sucursal suc = gsuc.getsuccodcursal( ped.getpedsucursal());
if(suc == null) //si no encuentra lo saca por el consecutivo
	suc = gsuc.getsucursal( ped.getpedsucursal());

ciudad ciud = gciud.getciudad( ped.getpedciudad());
departamento dep = gdep.getdepartamento( ped.getpeddepartamento());
transportadora transp = gtrans.gettransportadora(ped.getPedtransportadora());
%>
<body>
<table width="96%" height="500" border="0" cellpadding="0" cellspacing="0" align="center">
<%@include file="documento_entrega_head.jsp"%>
     
	  <table align='center'  width='96%' border='0' cellspacing='0' cellpading='0' class="tabla_resumen" > 
<%@include file="documento_entrega_titulos.jsp"%>      	
        <% 
//  Hasta aqui es el encabezado

        Collection productos = grefp.getProductosPedido_SortByModelo( pedcodsx);
        Iterator ite = productos.iterator();
        gstproducto_cliente gprocc = new gstproducto_cliente();
        String pesoneto_total ="0";
        String pesobruto_total ="0";
        String total_cantidad = "0";
        String total_mpack = "0";
        int pagina = 1;
        int lineas = 3;
        int lineasDet = 18;
        int lineasResu = 27;
        Double lineaadicional = 0.0;
        while(ite.hasNext()) {
        	Collection temp = (Collection) ite.next();
        	Iterator ite_temp= temp.iterator();
        	String refpproducto = (String)ite_temp.next();
        	String modeloproducto = (String)ite_temp.next();
        	String cantidad = (String)ite_temp.next();
        	String pesoneto_unit = (String)ite_temp.next();
        	String pesobruto_unit = (String)ite_temp.next();
        
        	producto prod = gprod.getproducto( refpproducto);
        	String unid_mpack = prod.getprounimasterpac();
        	String mpack = util.Math.dividir( cantidad, unid_mpack);
        
        
        	String pesoneto_ref = util.Math.multiplicar( prod.getprokilovolumen() , cantidad );
        	String pesobruto_ref = pesobruto_unit; //util.Math.multiplicar( pesobruto_unit, cantidad );
        	System.out.println("------"+pesoneto_unit);
        	pesoneto_total = util.Math.sumar( pesoneto_total, pesoneto_ref);
        	pesobruto_total = util.Math.sumar( pesobruto_total, pesobruto_ref);
        	total_cantidad = util.Math.sumar( total_cantidad, cantidad);
        	total_mpack = util.Math.sumar( total_mpack, mpack);
			producto_cliente procc = gprocc.getproducto_cliente( cli.getclicodsx(), prod.getprocodsx());
        
        %>
                
        	<tr> 
          		<td nowrap="nowrap"><font size=2><%= prod.getpromodelo()  %></font></td>
          		<td><font size=2><%= prod.getprodescripcion() %></font></td>
<%--           		<td align="center"><%= procc==null ?"N": procc.getprocean() %></td> --%>
          		<%//<font  size="1">%>
          		
          		 
<%--           		<td align="center"><%= procc==null ?"N": procc.getproctag() %></td>  --%>
<%--           		<td align="center"><%= procc==null ?"N": procc.getprocgarantia() %></td> --%>
<%--           		<td><font  size="1"><%= procc==null ?"N": procc.getprocnumcodigobarra()!= null ?procc.getprocnumcodigobarra():"S"%> --%>
          			<%  %>
          		<% //</font>%>
				</font> 
          		</td>
          		<td align="right"><font size=2><%=util.JhFormat.getFormatedNumber(cantidad) %></font></td>
          		<td align="right"><font size=2><%=util.JhFormat.getFormatedNumber( mpack )%></font></td>
          		<td align="right"><%= util.JhFormat.getFormatedNumber(pesoneto_ref) %></td>
          		<td align="right"><%= util.JhFormat.getFormatedNumber(pesobruto_ref )%></td>
          		<%
          		if(tipo.equals("DETALLADO")) {
          		                  		gstproducto gprod = new gstproducto();
          		                  		Collection detalles = grefp.getlistareferencia_pedido(pedcodsx, refpproducto);
          		                  		Iterator ite_det = detalles.iterator();
          						int totalDet = detalles.size();
          						int contadorDet = 0;

          		              			while(ite_det.hasNext()) {
          		            	  			pedido.entity.referencia_pedido refp = (pedido.entity.referencia_pedido) ite_det.next();
          		            	  			parametro bod =gbod.getbodega( refp.getRefpbodega());
          		            	  			entrada ent = gent.getentrada( refp.getRefpentrada());
          		            	  			ingreso ing = ging.getingreso( ent.getentcodingreso());
          		            	  			lote_trafico lt = null;
          		            	  			if (ent.getentlote() != null) {
          		            	  				lt = gl.getlote_trafico(ent.getentlote());
          		            	  			}
          		            	 
          		            	  			String embarque= "";
          		            	  			String BL= "&nbsp;";
          		            	  			String declaracion ="&nbsp;";
          		            	  			if(ing.getingtipo().equals("TRAFICO")) {
          		            	  				trafico traf = gtraf.gettrafico( ing.getingtrafico());
          		            	  				embarque = traf.gettrafembarque();
          		            	  				BL= traf.gettrafdocumento();
          		            	  			}else {
          		            		  			embarque = "Ingreso de tipo: " + ing.getingtipo();
          		            	  			}
          		            	  			if(refp.getRefpnacdetalle()!=null && !refp.getRefpnacdetalle().equals(""))  {
          		            		  			nacionalizacion_detalle nacdet = gnacdet.getnacionalizacion_detalle(refp.getRefpnacdetalle() );
          		            		 			nacionalizacion nac = gnac.getnacionalizacion( nacdet.getnadcodnac());
          		            		  			declaracion = nac.getnacdeclaracion();
          		            	  			}
          							if (contadorDet > 0) {
          								lineaadicional += 0.75;
          		%>
							<tr>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
<!-- 						   		<td>&nbsp;</td> -->
<!-- 								<td>&nbsp;</td> -->
<!-- 						   		<td>&nbsp;</td> -->
<!-- 						   		<td>&nbsp;</td> -->
             				<%}%> 
                				<td><%= bod.getbodnombre() %></td>
                				<td nowrap="nowrap"><%= refp.getrefpposicion() %></td>
                				<td><%= util.JhFormat.getFormatedNumber( refp.getrefpcantidad()) %></td>
                				<td align="right"><%= lt != null ? lt.getLtrafvencimiento(): "SIN DEFINIR" %></td>
                				<td nowrap="nowrap"><%= lt != null ? lt.getLtraflote(): "SIN DEFINIR"  %></td>
                				<td nowrap="nowrap"><%= BL %></td>
                				<td nowrap="nowrap"><%= declaracion %></td>
						<%
							
								
							if (contadorDet == 0) {
								lineas++;
							}
							contadorDet++;
						%>
              				</tr>
							<%//}
						    if (lineas + lineaadicional.intValue() >= lineasDet) {
						    	lineas = 0;
						    	pagina++;
						    	lineaadicional = 0.0;
						    %>
						    </table>
						    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
						    <tr><td>&nbsp;</td></tr>
						    <tr><td>&nbsp;</td></tr>
						    <tr><td>&nbsp;</td></tr>
						    <tr><td>&nbsp;</td></tr>
						    <tr>
						    <td><b>Pedido No: </b><font size=5><%= ped.getpednumpedido() %></font></td>
          					<td align="right">Pagina: <%=pagina %></td>
          					</tr>
          					
          					</table>
						    <table align='center'  width='100%' border='0' cellspacing='0' cellpading='0' class="tabla_resumen" > 
						    
						    <%@include file="documento_entrega_titulos.jsp"%> 
						    <% 	
						    }
						   }  
						} else {
            	  //es RESUMIDO:
   		 				Collection embarques = gped.getEmbarquePorPedido(pedcodsx, refpproducto);
						Iterator ite_emb = embarques.iterator();
						int totalRes = embarques.size();
						int contadorRes = 0;
            			while(ite_emb.hasNext()) {
							Collection datos = (Collection)ite_emb.next(); 
							Iterator ite_datos = datos.iterator();
							if (contadorRes > 0) {
								lineaadicional += 0.75;
								
								
						%>
							<tr>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
						   		<td>&nbsp;</td>
<!-- 						   		<td>&nbsp;</td>	 -->
<!-- 								<td>&nbsp;</td> -->
<!-- 						   		<td>&nbsp;</td> -->
<!-- 						   		<td>&nbsp;</td>	 -->
              				<%}%> 
					
							
								<td nowrap="nowrap"> <%= ite_datos.next() %>
								<td nowrap="nowrap"><%= ite_datos.next() %>
								<td nowrap="nowrap"><%= ite_datos.next() %>
								<td><%= util.JhFormat.getFormatedNumber((String) ite_datos.next() )%>
						<%
								if (contadorRes == 0) {
									lineas++;
								}
								contadorRes++;
						%>
              				</tr>
						<% 
							if (lineas + lineaadicional.intValue() >= lineasResu) {
					    		lineas = 0;
					    		pagina++;
					    		lineaadicional = 0.0;
					    %>
					    		</table>
					    	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					    	<tr><td>&nbsp;</td></tr>
					    	<tr><td>&nbsp;</td></tr>
					    	<tr><td>&nbsp;</td></tr>
					    	<tr><td>&nbsp;</td></tr>
					    	<tr>
					    		<td><b>Pedido No: </b><font size=5><%= ped.getpednumpedido() %></font></td>
      							<td align="right">Pagina: <%=pagina %></td>
      						</tr>
      					
      						</table>
					    	<table align='center'  width='100%' border='0' cellspacing='0' cellpading='0' class="tabla_resumen" > 
					    
					    	<%@include file="documento_entrega_titulos.jsp"%> 
					    <% 	
					    	}
						} 
						if (contadorRes == 0) {
							lineas ++;
						%>	
							<td>&nbsp;</td>
						   	<td>&nbsp;</td>
						   	<td>&nbsp;</td>
						   	<td>&nbsp;</td>
						</tr>
						<%}%>		              
              		<% } 
				} %>
        <tr> 
          <td><b>TOTAL</b></td>
          <td>&nbsp;</td>
<!--           <td>&nbsp;</td> -->
<!--           <td>&nbsp;</td> -->
<!--           <td>&nbsp;</td> -->
<!--           <td>&nbsp;</td> -->
          <th align="right"><%=  util.JhFormat.getFormatedNumber(total_cantidad ) %></th>
          <th align="right"><%=  util.JhFormat.getFormatedNumber(total_mpack ) %></th>
          
          <th align="right"><%=  util.JhFormat.getFormatedNumber(pesoneto_total ) %></th>
          <th align="right"><%= util.JhFormat.getFormatedNumber( pesobruto_total )%></th>
          
		  <%if(tipo.equals("DETALLADO")) {%>
		  	<td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
            <td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
            <td>&nbsp;</td>
          <%} else {%>
		  	<td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
            <td>&nbsp;</td>
		<%} %>
        </tr>
      </table>
      
          
	<% 
        int totallineas = 0;
		if(tipo.equals("DETALLADO")) {
			totallineas = lineasDet;
        } else { totallineas = lineasResu; }
		if (lineas + lineaadicional.intValue() + 7 >= totallineas) {
			
    %>
		   	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr>
		    		<td><b>Pedido No: </b><font size=5><%= ped.getpednumpedido() %></font></td>
					<td align="right">Pagina: <%=pagina %></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
		    	<tr><td>&nbsp;</td></tr>
    					
			</table>
		<%}%>
          
      <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
      	<tr><td>&nbsp;</td></tr>
      	<tr><td>
      		<% usuario usu = (usuario)session.getAttribute("usuario");
			%><%=usu.getusunombre()%></td></tr>
      	<tr> 
          <td>_________________________</td><td>_________________________</td>
      
      	</tr>
      	<tr> 
          <td>Elaborado por</td><td>Alistado por</td>
         
      	</tr>
	  	<tr><td>&nbsp;</td></tr>
	  	<tr>
		  <td>_________________________</td><td>_________________________</td>
          	  <td>_________________________</td>
	  	</tr>
	  	<tr>
          <td>Despachado por</td><td>Transportadora </td>			
          <td>Revisado por</td>
	  	</tr>
      </table>

      NOTAS: 
       En caso de presentarse cualquier novedad con la documentacion y / o mercancia de entrega, favor comunicarse con <%=gcomp.getcompania(cli.getclicodcompania()).getcomnombre()%>
      al  <%=gcomp.getcompania(cli.getclicodcompania()).getcomtelefono()%>. CEL <%=gcomp.getcompania(cli.getclicodcompania()).getcomfax()%>
       

</table>
</body>
