

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,pedido.control.*,pedido.entity.*,pedido.form.*"%>

<html>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%gstdespacho gdesp = new gstdespacho();
			gstpedido gped = new gstpedido();
			gstdespacho_pedido gdesp_ped = new gstdespacho_pedido();
			gstreferencia_despacho gref_desp = new gstreferencia_despacho();

			maestro.control.gstcompania gcomp = new maestro.control.gstcompania();
			maestro.control.gsttransportadora gtransp = new maestro.control.gsttransportadora();
			maestro.control.gstproducto gprod = new maestro.control.gstproducto();
			String despcodsx = request.getParameter("despcodsx");

			despacho desp = gdesp.getdespacho(despcodsx);

			administracion.entity.empresa emp = new administracion.control.gstempresa()
					.getempresa("4");
%>

<table align="center" border="0">
	<tr>
		<td><img src="./disenno/images/logo.gif" >
		<td align="center"><b> PLANILLA DE DESPACHOS <br>
		<%=emp.getempnombre()%> <br>
		<%=emp.getempdireccion()%><br>
		NIT <%=emp.getempnit()%> </b>
		<td><%=desp.getdespfecha()%>
</table>

<br>
<%
String tipo = request.getParameter("tipo");
			tipo = tipo == null ? "" : tipo;

			%>
<div align="right">
<form
	action="main.jsp?app=pedido&modulo=planilla_despacho&despcodsx=<%= despcodsx %>"
	method="post"><select name="tipo"
	onchange="document.forms[0].submit();">
	<option value="DETALLADO"
		<%= tipo.equals("") || tipo.equals("DETALLADO")? "selected" :"" %>>DETALLADO (PEDIDOS)</option>
	<option value="RESUMIDO" <%= tipo.equals("RESUMIDO")? "selected" :"" %>>RESUMIDO (POR GUIA)</option>
	<option value="RESUMIDOCLIENTE" <%= tipo.equals("RESUMIDOCLIENTE")? "selected" :"" %>>RESUMIDO (POR CLIENTE)</option>
</select></form>
</div>
<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">

	<tr>
		<td>Consecutivo
		<td><%=desp.getdespcodsx()%>
		<td>Empresa
		<td><%=gcomp.getcompania(desp.getdespempresa()).getcomnombre()%>
	<tr>
		<td>Fecha
		<td><%=desp.getdespfecha()%>
		<td>&nbsp;
		<td>&nbsp;
	<tr>
		<td>Empresa Transp
		<td><%=gtransp.gettransportadora(desp.getdesptransportadora())
							.gettranspnombre()%>
		<td>Placa Veh&iacute;culo
		<td><%=desp.getdespplaca() + " Tipo Veh: "%><%=desp.getDesptipoveh()!= null?desp.getDesptipoveh():""%>&nbsp;
	<tr>
		<td>C&eacute;dula
		<td><%=desp.getdespcedcond()%>
		<td>Nombre Cond
		<td><%=desp.getdespnombrecond()%>
	<tr>
		<td>Tipo Despacho
		<td><%=desp.getDesptipo()%>
		<td>Celular Cond
		<td><%=desp.getDespcelcond()%>&nbsp;
	<tr>
		<td>Observaci&oacute;n
		<td><%=desp.getdespobservacion()%> &nbsp;
		<td>&nbsp;
		<td>&nbsp;
</table>
<br>
<%
if (tipo.equals("") || tipo.equals("DETALLADO")) {
%>

<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">
	<tr>
		<th>Gu&iacute;a
		<th>Factura
		<th>Orden Compra
		<th>Codigo Cliente
		<th>Cliente
		<th>Direcci&oacute;n
		<th>Ciudad
		<th>Departamento
		<th>Valor Declarado
		<th>Valor Facturado
		<th>Peso
		<th>Bultos
		<th>Kilo Volumen <%
Collection pedidos = gdesp.getListaPlanillaDespacho(despcodsx);
	
				//if(pedidos.size()==0) pedidos = gdesp.getListaPlanillaDespachonew(despcodsx);
				Iterator ite = pedidos.iterator();

				%> <%
				java.math.BigDecimal vtotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal vtotalfac = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ptotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal btotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ktotal = java.math.BigDecimal.ZERO;
				while (ite.hasNext()) {
					Collection datos = (Collection) ite.next();
					Iterator data = datos.iterator();
					String guia = (String) data.next();
					String factura = (String) data.next();
					String ordenc = (String) data.next();
					String sucur = (String) data.next();
					String dir = (String) data.next();
					String ciud = (String) data.next();
					String dep = (String) data.next();
					String val = (String) data.next();
					String peso = (String) data.next();
					String bultos = (String) data.next();
					String kilovol = (String) data.next();
					String succodigo = (String) data.next();
					String valfac = (String) data.next();
					kilovol = gdesp.getKiloVolumenDespachoPedido(despcodsx,kilovol);
					vtotal = util.Math.sumar_bigdecimal(vtotal.toPlainString(),val);
					vtotalfac = util.Math.sumar_bigdecimal(vtotalfac.toPlainString(),valfac);
					ptotal = util.Math.sumar_bigdecimal(ptotal.toPlainString(),peso);
					btotal = util.Math.sumar_bigdecimal(btotal.toPlainString(),bultos);
					ktotal = util.Math.sumar_bigdecimal(ktotal.toPlainString(),kilovol);

					%>
	<tr>
		<td><%=guia%>
		<td><%=factura%>
		<td><%=ordenc%>
		<td><%=succodigo%>
		<td><%=sucur%>
		<td><%=dir%>
		<td><%=ciud%>
		<td><%=dep%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(val)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(valfac)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(peso)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(bultos)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(kilovol)%> <%}

				%>
	<tr>
		<td colspan="8">Total General
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotalfac.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ptotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(btotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ktotal.toPlainString())%>
</table>

<%
} else if (tipo.equals("RESUMIDO")) {
				//	 ACUMULADO POR GUIA

				%>

<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">
	<tr>
		<th>Gu&iacute;a
		<th>Factura (s)
		<th>Pedido (s)
		<th>Seriales
		<th>Orden Compra (s)
		<th>Codigo Cliente
		<th>Cliente
		<th>Direcci&oacute;n
		<th>Ciudad
		<th>Departamento
		<th>Valor Declarado
		<th>Valor Facturado
		<th>Peso
		<th>Bultos
		<th>Cantidad
		<th>Kilo Volumen 
<%
        Collection pedidos = gdesp.getListaPlanillaDespachoPorGuia(despcodsx);
		out.println("Salida");
		       //if(pedidos.size()==0) pedidos = gdesp.getListaPlanillaDespachonew(despcodsx);
				Iterator ite = pedidos.iterator();

				%> <%
				java.math.BigDecimal vtotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal vtotalfac = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ptotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal btotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ktotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal canttotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal cant_ref = java.math.BigDecimal.ZERO;
				while (ite.hasNext()) {
					Collection datos = (Collection) ite.next();
					Iterator data = datos.iterator();
					String guia = (String) data.next();
					Collection fs = gped.getlistapedidoByGuiaByDespacho(desp.getdespempresa(), guia, despcodsx);
					String seriales = "";
					String factura = "";
					String pe = "";
					String orden = "";
					Iterator itefs = fs.iterator();
					int i = 0;
					cant_ref = java.math.BigDecimal.ZERO;
					while (itefs.hasNext()) {
						pedido pedi = (pedido) itefs.next();
						factura += (i > 0 ? ", " : "") + pedi.getpedfactura();
						pe += (i > 0 ? ", " : "") + pedi.getpednumpedido();
						orden += (i > 0 ? ", " : "") + pedi.getpedordencompra();
						i++;
						despacho_pedido dp = gdesp_ped.getdespacho_pedido(desp.getdespcodsx(),pedi.getpedcodsx());
						Collection rds = gref_desp.getlistareferencia_despacho(dp.getdespedcodsx());
						Iterator itrd = rds.iterator();
						
						while (itrd.hasNext()) {
							referencia_despacho rd = (referencia_despacho) itrd.next();
							cant_ref = util.Math.sumar_bigdecimal(cant_ref.toPlainString(),rd.getRefdcant());
							Collection lcs = gref_desp.getListaConsolidadoSeriales(rd.getrefdcodsx());
							Iterator itlcs = lcs.iterator();
							while (itlcs.hasNext()) {
								Collection datosseriales = (Collection) itlcs.next();
								Iterator itdatosseriales = datosseriales.iterator();
								seriales += (String) itdatosseriales.next();
							}
						}
					}

					//		String ordenc = (String)data.next();
					String sucur = (String) data.next();
					String dir = (String) data.next();
					String ciud = (String) data.next();
					String dep = (String) data.next();
					String val = (String) data.next();
					String peso = (String) data.next();
					String bultos = (String) data.next();
					String kilovol = (String) data.next();
					String succodigo = (String) data.next();
					String valfac = (String) data.next();
					kilovol = gdesp.getKiloVolumenDespachoPedidoGuia(despcodsx,kilovol);
					vtotal = util.Math.sumar_bigdecimal(vtotal.toPlainString(),val);
					vtotalfac = util.Math.sumar_bigdecimal(vtotalfac.toPlainString(),valfac);
					ptotal = util.Math.sumar_bigdecimal(ptotal.toPlainString(),peso);
					btotal = util.Math.sumar_bigdecimal(btotal.toPlainString(),bultos);
					ktotal = util.Math.sumar_bigdecimal(ktotal.toPlainString(),kilovol);
					canttotal = util.Math.sumar_bigdecimal(canttotal.toPlainString(),cant_ref.toPlainString());
					%>
	<tr>
		<td><%=guia%>
		<td><%=factura%>
		<td><%=pe%>
		<td><%=seriales%>&nbsp;
		<td><%=orden%>
		<td><%=succodigo%>
		<td><%=sucur%>
		<td><%=dir%>
		<td><%=ciud%>
		<td><%=dep%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(val)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(valfac)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(peso)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(bultos)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(cant_ref.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(kilovol)%> 
		
		<%}%>
	<tr>
		<td colspan="10">Total General
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotalfac.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ptotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(btotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(canttotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ktotal.toPlainString())%>
</table>
<%
} else if (tipo.equals("RESUMIDOCLIENTE")) {
				//	 ACUMULADO POR CLIENTE

				%>

<table align="center" border="0" width='100%' cellpadding="3"
	cellspacing="0" class="tabla_resumen">
	<tr>
		<th>Codigo Cliente
		<th>Cliente
		<th>Factura (s)
		<th>Pedido (s)
		<th>Seriales
		<th>Orden Compra (s)	
		<th>Direcci&oacute;n
		<th>Ciudad
		<th>Departamento
		<th>Valor Declarado
		<th>Valor Facturado
		<th>Peso
		<th>Bultos
		<th>Cantidad
		<th>Kilo Volumen 
<%
        Collection pedidos = gdesp.getListaPlanillaDespachoPorCliente(despcodsx);
		out.println("Salida");
		       //if(pedidos.size()==0) pedidos = gdesp.getListaPlanillaDespachonew(despcodsx);
				Iterator ite = pedidos.iterator();

				%> <%
				java.math.BigDecimal vtotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal vtotalfac = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ptotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal btotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal ktotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal canttotal = java.math.BigDecimal.ZERO;
				java.math.BigDecimal cant_ref = java.math.BigDecimal.ZERO;
				while (ite.hasNext()) {
					Collection datos = (Collection) ite.next();
					Iterator data = datos.iterator();
					String succodigo = (String) data.next();
					Collection fs = gped.getlistapedidoByClienteByDespacho(desp.getdespempresa(), succodigo, despcodsx);
					String seriales = "";
					String factura = "";
					String pe = "";
					String orden = "";
					Iterator itefs = fs.iterator();
					int i = 0;
					cant_ref = java.math.BigDecimal.ZERO;
					while (itefs.hasNext()) {
						pedido pedi = (pedido) itefs.next();
						factura += (i > 0 ? ", " : "") + pedi.getpedfactura();
						pe += (i > 0 ? ", " : "") + pedi.getpednumpedido();
						orden += (i > 0 ? ", " : "") + pedi.getpedordencompra();
						i++;
						despacho_pedido dp = gdesp_ped.getdespacho_pedido(desp.getdespcodsx(),pedi.getpedcodsx());
						Collection rds = gref_desp.getlistareferencia_despacho(dp.getdespedcodsx());
						Iterator itrd = rds.iterator();
						
						while (itrd.hasNext()) {
							referencia_despacho rd = (referencia_despacho) itrd.next();
							cant_ref = util.Math.sumar_bigdecimal(cant_ref.toPlainString(),rd.getRefdcant());
							Collection lcs = gref_desp.getListaConsolidadoSeriales(rd.getrefdcodsx());
							Iterator itlcs = lcs.iterator();
							while (itlcs.hasNext()) {
								Collection datosseriales = (Collection) itlcs.next();
								Iterator itdatosseriales = datosseriales.iterator();
								seriales += (String) itdatosseriales.next();
							}
						}
					}

					//String ordenc = (String)data.next();
					//String guia = (String) data.next(); 
					String sucur = (String) data.next();
					String dir = (String) data.next();
					String ciud = (String) data.next();
					String dep = (String) data.next();
					String val = (String) data.next();
					String peso = (String) data.next();
					String bultos = (String) data.next();
					String kilovol = (String) data.next();
					String valfac = (String) data.next();
					kilovol = gdesp.getKiloVolumenDespachoPedidoGCliente(despcodsx,kilovol);
					vtotal = util.Math.sumar_bigdecimal(vtotal.toPlainString(),val);
					vtotalfac = util.Math.sumar_bigdecimal(vtotalfac.toPlainString(),valfac);
					ptotal = util.Math.sumar_bigdecimal(ptotal.toPlainString(),peso);
					btotal = util.Math.sumar_bigdecimal(btotal.toPlainString(),bultos);
					ktotal = util.Math.sumar_bigdecimal(ktotal.toPlainString(),kilovol);
					canttotal = util.Math.sumar_bigdecimal(canttotal.toPlainString(),cant_ref.toPlainString());
					%>
	<tr>
		<td><%=succodigo%>
		<td><%=sucur%>
		<td><%=factura%>
		<td><%=pe%>
		<td><%=seriales%>&nbsp;
		<td><%=orden%>
		<td><%=dir%>
		<td><%=ciud%>
		<td><%=dep%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(val)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(val)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(peso)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(bultos)%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(cant_ref.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(kilovol)%> 
		
		<%}%>
	<tr>
		<td colspan="9">Total General
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(vtotalfac.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ptotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(btotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(canttotal.toPlainString())%>
		<td align="right"><%=util.JhFormat.getFormatedNumber(ktotal.toPlainString())%>
</table>


<%}%>
<br>


<table align="center" border="0" width="80%">
	<tr>
		<td align="center">----------------------------<BR>
		FIRMA ENTREGADO
		<td align="center">----------------------------<BR>
		FIRMA RECIBIDO</td>
	</tr>
</table>

</html>
