<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="administracion.control.*,administracion.entity.*"%>
    
<%
String menuizq = (String)request.getAttribute("menuizq");
if(menuizq==null) {
%> 

<% 
gstpermiso gper = new gstpermiso();
String grupo = ((usuario)session.getAttribute("usuario")).getusugrupo();
%>    
<!-- <table width="120" border="0" cellspacing="3" cellpadding="3"  class="menuizquierdo"> -->

<% if( gper.verIzquierdo(grupo, "movil", "inventario.jsp") ) { %>
<!-- <tr >  -->
<!-- 	<td>&nbsp;</td> -->
<!-- 		<td><a href="main.jsp?app=movil&modulo=inventario">  :: Inventario </a>  -->
<% } %>

<% if( gper.verIzquierdo(grupo, "movil", "lecturaQR.jsp") ) { %>
<!-- 	<tr >  -->
<!-- 	<td>&nbsp;</td> -->
<!-- 		<td><a href="main.jsp?app=movil&modulo=movil">  :: Consulta por QR </a> 	 -->
<% }%>
   
<!-- </table> -->
<% } %>
