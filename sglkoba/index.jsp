<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>SGL :: Sistema de Gestion Logistico -- KOBA Colombia S.A.S</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="./disenno/templates/Principal/styles.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function sf() {//336CA1
		document.validacionFrm.login.focus();
	}
</script>
</head>
<%String miPuerto = "9999"; //request.getServerPort()+""; %>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" onload="sf()">
	<table width="770" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr>
			<td bgcolor="#FFFFFF"><table width="770" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2">
							<% if (miPuerto != null && !miPuerto.equals("9090")) { %>
								<img src="./disenno/templates/Principal/images/index/logo.gif" width="359"><%} %>
						</td>
					</tr>

					<td width="45%" width="100%" bgcolor="#3E4095" height="20"></td>
					<td width="55%" align="left" bgcolor="#3E4095" class="principal">&nbsp;</td>

					<tr>
						<td colspan="2" width="100%"><% if (miPuerto != null && !miPuerto.equals("9090")) { %><img src="./disenno/templates/Principal/images/index/ktopbar.gif"><%} %></td>
					</tr>

					<tr>
						<td width="140" rowspan="3" valign="top"></td>
						<td colspan="2" align="center"><table width="95%" border="0"
								cellpadding="5" cellspacing="0" class="principal">
								<tr>
									<td class="principal"><font color="#000000">
										<% if (miPuerto != null && !miPuerto.equals("9090")) { %><img src="./disenno/templates/Principal/images/index/bienvenida.png"><%} %>
									</td>
								</tr>
						</td>
					</tr>
					<tr>
						<td width="349" align="center">
							<table width="85%" border="0" cellpadding="3" cellspacing="1"
								bgcolor="#666666">
								<tr>
									<td bgcolor="E3EDFF" align="center">

										<form action="validacionAction.do" name="validacionFrm"
											method="post">
											<table width="290" border="0" cellpadding="2" cellspacing="0"
												class="principal">
												<tr>
													<td colspan="4" valign="top"><img
														src="./disenno/templates/Principal/images/index/spacer.gif"
														width="20" height="4"></td>
												</tr>
												<tr>
													<td width="2%" valign="top">&nbsp;</td>
													<td width="5%" valign="middle"><img
														src="./disenno/templates/Principal/images/index/dots.gif"
														width="10" height="10"></td>
													<td width="23%"><font color="#666666">Login</font></td>
													<td width="70%"><input type="text" name="login"
														style="background-color: ffffff"></td>
												</tr>
												<tr>
													<td colspan="4" valign="middle"><img
														src="./disenno/templates/Principal/images/index/spacer.gif"
														width="20" height="4"></td>
												</tr>
												<tr>
													<td valign="top">&nbsp;</td>
													<td valign="middle"><img
														src="./disenno/templates/Principal/images/index/dots.gif"
														width="10" height="10"></td>
													<td><font color="#666666">Clave</font></td>
													<td><input type="password" name="clave"
														style="background-color: ffffff"></td>
												</tr>
												<tr>
													<td colspan="4" valign="top"><div align="center">
															<input type="submit" name="Submit" value="Ingresar">
														</div></td>
												</tr>
											</table>
										</form>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>

				</table></td>
		</tr>
	</table>
	<div class="subtitulo" align="center">
		Desarrollado por Logical Sistemas S.A.S. <br>Sistemas Logisticos
		Industriales <br>2019
	</div>
</body>
</html>
