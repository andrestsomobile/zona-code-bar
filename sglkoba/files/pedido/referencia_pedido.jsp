	 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
 pageEncoding="ISO-8859-1" import=" administracion.control.*,pedido.control.*,pedido.entity.*,java.util.*, pedido.form.*"%> 
 <html> 

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld"  prefix="html"%>
<%@ taglib uri="/WEB-INF/hiddenpaneltag.tld" prefix="h" %>
<%   

 gstreferencia_pedido greferencia_pedido = new gstreferencia_pedido(); 
 gstpedido gped = new gstpedido(); 
 maestro.control.gstbodega gbod = new maestro.control.gstbodega();
 String pedcodsx = request.getParameter("pedcodsx");
 pedido ped = gped.getpedido(pedcodsx);
 greferencia_pedido.setMaxfilas(15);
  
 maestro.control.gstcompania gcomp = new maestro.control.gstcompania();
 maestro.entity.compania comp = gcomp.getcompania(ped.getpedcompania());
 String cominventario = comp.getCominventario();  

 String param = request.getParameter("param"); 
 String pag = request.getParameter("pag");   

 Collection lista = greferencia_pedido.getlistareferencia_pedido(pedcodsx, pag, param);  
 request.setAttribute("lista", lista); 
 
 boolean tienePicking = ped.getPedpicking()==null || ( ped.getPedpicking()!=null && ped.getPedpicking().equals("") );
  %> 
  
<script type="text/javascript">

function modificarReferencia(pedcodsx,codsx) {
	var cantidad = document.getElementById("cantidad_"+codsx);
	var valorunit = document.getElementById("valorunit_"+codsx);
	var valorfacunit = document.getElementById("valorfacunit_"+codsx);
	if(confirm('Se eliminara la referencia para respladar con los nuevos datos, esta seguro de realizar estos cambios?')) 
		window.location = "referencia_pedidoAction.do?opcion=modificar&pedcodsx="+pedcodsx+"&codsx="+codsx+"&cantidad="+cantidad.value+"&valorunit="+valorunit.value+"&valorfacunit="+valorfacunit.value;
}
</script>
  
 <div class="titulo"> Gestion de Referencias x Pedido </div> 
 <BR><br>
 <html:form method="post"  action="referencia_pedidoAction.do?opcion=agregar_total" onsubmit="if(this.promodelo.value='') return false; else return true;">
  Adicionar Referencia:
<table align='center'  width='98%' border='0' cellspacing='0' cellpading='0' >  
	<% if(cominventario!=null && cominventario.equals("M")) {%>
	<tr> 
    	<td align='left'>Ingreso  : 
    	<html:text property="ingreso" readonly="true" styleClass="readonly"  /> 
	  	<%
		String fechaingreso = ""; 
		//if(pedcodsx==null || ( pedcodsx!=null && pedcodsx.equals("")  )) {				 
      	
			//String cia = naccodsx==null ?  "'+ nacionalizacionForm.nacempresa.options[nacionalizacionForm.nacempresa.selectedIndex].value  + '" : "'+ nacionalizacionForm.nacempresa.value   + '";
			
			String[] parametros = new String[] {  comp.getcomcodsx(), ped.getpedtipo(), "0", ""};
			String[] parametros_total = new String[] {comp.getcomcodsx(), ped.getpedtipo(),""};
			String[] campos_form= new String[] {"ingreso", "fechaingreso"};
			String[] campos_pick = new String[] {"ingcodsx", "ingfecha"};
			String[] campos_entity= new String[] {"ingcodsx", "ingfecha", "Ingcausal",  "Ingobservacion" , "Ingestado", "Ingconsec" , "Ingconseczfp"};
			String[] nombres_campos_entity= new String[] {"Consecutivo", "Fecha","Embarque" ,  "Documento Transporte", "Documento Transito", "Consec ZF" , "Numero FM (aprobacion) ZF"};
			String formulario = "referencia_pedidoForm";
			String metodo = "getlistaingreso_manual"; 
			String metodoTotal = "getTotalPaginaslistaingreso_manual";
		%> 
			<%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
			<popup:boton clase="ingreso.control.gstingreso" entidad="ingreso.entity.ingreso" 
				formulario="<%=formulario %>" metodo="<%=metodo %>"
				metodo_total="<%=metodoTotal %>"
				parametros="<%= parametros %>" campos_form="<%= campos_form %>"
				campos_pick="<%= campos_pick %>"
				parametros_total="<%= parametros_total %>" 
				campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" /> 
      	<% //} %> 
    	<td align='right'>Fecha Ingreso 
    	<input type="text" name="fechaingreso" value='<%= fechaingreso %>' readonly="true" class="readonly"  />
		
	<% } %> 	
	<tr> 
 		<td align='left'>   
 		<%if( gstpermiso.getEscritura(session, "pedido", "referencia_pedido.jsp") && tienePicking==true ){ %> 
			<%--
 			<a href='main.jsp?app=pedido&modulo=referencia_pedido_datos&pedcodsx=<%= pedcodsx %>'> Agregar Referencia x Posicion </a> 
  			
 		<td align='left'>  
		--%>

			<input type="hidden" name="pedcodsx"  value="<%= pedcodsx%>">
			<html:hidden property="refpentrada" />
			<html:hidden property="procodsx" />Producto:
			<input type="text" name="promodelo" readonly="true" class="readonly" >
 		<%
				String cia = ped.getpedcompania();
				String ing = ""; 

				String[] parametros = new String[] {  cia, ped.getpedtipo(), "0", ""};
				String[] parametros_total = new String[] {cia, ped.getpedtipo(), ""};
				String[] campos_form= new String[] {"procodsx", "promodelo"};
				String[] campos_pick = new String[] {"Uno", "Dos"};
				String[] campos_entity= new String[] {"Uno", "Dos",  "Tres" , "Cuatro", "Cinco", "Seis"};
				String[] nombres_campos_entity= new String[] {"Consecutivo", "Material",  "Descripcion", "Saldo ("  +  ped.getpedtipo() + ")", "UMP", "UMP x Estiba" };
				String formulario = "referencia_pedidoForm";
				String metodo = "getlistaproductoConSaldo";  
				String metodoTotal = "getTotalPaginaslistaproductoConSaldo";
				if(cominventario!=null && cominventario.equals("M")) {

					ing = "'+ referencia_pedidoForm.ingreso.value +'";

					parametros = new String[] {  ing, ped.getpedtipo(), "0", ""};
					parametros_total = new String[] {ing, ped.getpedtipo(), ""};
					campos_form= new String[] {"procodsx", "promodelo", "refpentrada"};
					campos_pick = new String[] {"Uno", "Dos", "Siete"};
					campos_entity= new String[] {"Uno", "Dos",  "Tres" , "Cuatro", "Cinco", "Seis", "Siete"};
					nombres_campos_entity= new String[] {"Consecutivo", "Material",  "Descripcion", "Bodega:Posicion", "Saldo ("  +  ped.getpedtipo() + ")", "Unidades","Entrada" };
					metodo = "getlistaproductoConSaldoManual";  
					metodoTotal = "getTotalPaginaslistaproductoConSaldoManual";
                } 
		%>
				
		 <%@ taglib uri="/WEB-INF/popup.tld"  prefix="popup"%> 
			<popup:boton clase="maestro.control.gstproducto" entidad="util.Listado" 
						formulario="<%=formulario %>" metodo="<%=metodo %>"
						metodo_total="<%=metodoTotal %>"
						parametros="<%= parametros %>" campos_form="<%= campos_form %>"
						campos_pick="<%= campos_pick %>"
						parametros_total="<%= parametros_total %>" 
						campos_entity="<%= campos_entity %>" nombres_campos_entity="<%= nombres_campos_entity %>" />
			<html:errors property="procodsx"/>
			<br>
			Cantidad:<html:text property="cantidad" size="5"  /> 	<html:errors property="cantidad"/>
			<html:select property="tipocantidad">
				<html:option value="UMP"> UMP</html:option> 
      			<html:option value="UNIDAD"> UNIDAD</html:option> 
      		</html:select>
			<html:hidden property="valorunit" value="1"/> 	<html:errors property="valorunit"/>
			<html:hidden property="valorfacunit" value="1"/> 	<html:errors property="valorfacunit"/>
			<% if (ped.getpedestado().equals("TRAMITE") ) { %>
			<input type="submit" value="Ok">
 			<%  }  %>
 		<% } %>
 
 </html:form>
 
 <br>
 	<td align='right'> 	<form method='post' action='main.jsp?app=pedido&modulo=referencia_pedido&pedcodsx=<%= pedcodsx %>'> 
 <input type='text' name='param' value='<%=param==null ? "" : param %>'> 
 <input type='submit' name='buscar' value='Buscar'> 
 </form> 
 </table> 
<table align='center'  width='98%' border='0' cellspacing='0' cellpading='0' class="tabla_listas"> 
 	<tr>  
 <th> Material (*)
 <th>Descripcion (*)
 <th>UMP
 <th>Bodega
 <th>Posicion
 <th>Cantidad x UMP
 <th>Unidades

 <th> Opcion <logic:iterate id="temp" name="lista" type="referencia_pedido">
 <tr align='center'>  
 <% 
 maestro.entity.producto prod = new maestro.control.gstproducto().getproducto( temp.getrefpproducto() );
 %>
 <td>  <%= prod.getpromodelo()  %>
 <td>  <%= prod.getprodescripcion()%> 
 <td>  <%= prod.getProump()%>  
 <td><%= gbod.getbodega( temp.getRefpbodega()).getbodnombre() %>
 <td> <%= temp.getrefpposicion() %>
 <td><input type="text" id="cantidad_<%=temp.getrefpcodsx()%>" size="8" value="<%=temp.getrefpcantidad()%>">
 <td><%= util.Math.multiplicar(prod.getprouniempaque(),temp.getrefpcantidad()) %>

 <td>
 <%if(gstpermiso.getEscritura(session, "pedido", "referencia_pedido.jsp") && tienePicking==true ){ %>   
 <%-- 
 <a href='referencia_pedidoAction.do?opcion=set&pedcodsx=<%= pedcodsx %>&codsx=<%= temp.getrefpcodsx()%>'> <img src="./disenno/images/update.gif" border="0" ></a>| 
--%> 
 <%if(gstpermiso.getEscritura(session, "pedido", "referencia_pedido.jsp")  &&  ped.getpedestado().equals("TRAMITE") ) { %> 
		<a href="javascript:validarDelete('referencia_pedidoAction.do?opcion=delete&codsx=<%= temp.getrefpcodsx()  %>&pedcodsx=<%= pedcodsx %>')">
		<img src="./disenno/images/substract.gif" border="0" ></a> |
		<a href="javascript:modificarReferencia('<%=pedcodsx%>','<%=temp.getrefpcodsx()%>')"><img src="./disenno/images/update.gif" border="0" ></a>   
 <% } %> 
 <%} %>
&nbsp; </logic:iterate>
  </table> 
	 <% 	 String url = "main.jsp?app=pedido&modulo=referencia_pedido&pedcodsx="+pedcodsx + "&param=" + (param==null?"":param); 
 int paginas = greferencia_pedido.getTotalPaginaslistareferencia_pedido(pedcodsx,  param); 
 %> 
 <%@ taglib uri="/WEB-INF/Paginador.tld" prefix="p" %> 
 <p:paginar  actual="<%= pag %>"   total="<%= (paginas) %>" url="<%= url %>"   /> 
 <br>Los campos con (*) son de busqueda

 	
