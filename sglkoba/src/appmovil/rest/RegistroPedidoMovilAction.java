package appmovil.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import administracion.control.gstusuario;
import administracion.entity.usuario;
import ingreso.control.gstingreso;
import ingreso.control.gsttrafico;
import ingreso.entity.trafico;
import maestro.control.gstempleado;
import maestro.entity.empleado;
import pedido.control.gstpedido;
import pedido.control.gstreferencia_pedido;
import pedido.entity.pedido;
import pedido.entity.referencia_pedido;
import registro.control.gstregistro_pedido;
import registro.control.gstregistro_pedido_detalle;
import registro.entity.registro_pedido;
import util.JsonUtil;

public class RegistroPedidoMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mensaje = "";
		boolean isValid = true;
		JsonUtil msg = new JsonUtil();
		String pedidos = request.getParameter("pedidos");
		String usuario = request.getParameter("usuario");
		String idPedido = "";

		if (pedidos == null || pedidos.isEmpty()) {
			isValid = false;
			mensaje = "Pedido es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}

		if (usuario == null || usuario.isEmpty()) {
			isValid = false;
			mensaje = "Usuario es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}

		try {
			if (isValid) {
				Date date = new Date();
				SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
				String ingfecha = fecha.format(date);
				SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
				String inghora = hora.format(date);
				String[] rcPedido = pedidos.split(",");
				gstusuario gusu = new gstusuario();
				usuario user = gusu.getusuario_by_login(usuario);

				if (user == null) {
					isValid = false;
					mensaje = "No existe el usuario con login " + usuario;
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
					sendMessage(msg, response);

					return null;
				}
				String cedula = user.getusucedula();

				gstempleado gemp = new gstempleado();
				empleado emp = gemp.getempleado_by_ukey(cedula);

				if (emp == null) {
					isValid = false;
					mensaje = "No existe el empleado con login " + usuario;
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
					sendMessage(msg, response);
					return null;
				}

				gstregistro_pedido grpedido = new gstregistro_pedido();
				idPedido = grpedido.Getrepecodsx();

				for (String p : rcPedido) {

					registro_pedido rp = grpedido.getRegistroPedidoByPedido(p);

					if (rp == null) {
						boolean creoPedido = grpedido.crearregistro_pedido(idPedido, ingfecha, emp.getempcodsx(), inghora, inghora, "0", "TRAMITE");

						if (!creoPedido) {
							isValid = false;
							mensaje = "No se pudo crear el registro del pedido ";
							msg.setMessage(mensaje);
							msg.setStatus(JsonUtil.FAIL);
							idPedido = "0";
						} else {
							gstreferencia_pedido gstrpedido = new gstreferencia_pedido();
							Collection listPed = gstrpedido.getReferenciaByNumPedido(p);

							gstregistro_pedido_detalle grdpedido = new gstregistro_pedido_detalle();

							if (listPed == null || listPed.isEmpty()) {
								isValid = false;
								mensaje = "Pedido no existe, verifique que el nro del pedido exista ";
								msg.setMessage(mensaje);
								msg.setStatus(JsonUtil.FAIL);
							} else {
								for (Object obj : listPed) {
									referencia_pedido r = (referencia_pedido) obj;
									grdpedido.crearregistro_pedido_detalle(grdpedido.Getrpdecodsx(), idPedido, r.getRefpnumpedido(), inghora, "N", r.getRefpproducto(), r.getrefpposicion());
								}
							}
						}
					} else {
						idPedido = rp.getrepecodsx();
					}

					
				}
			}
		} catch (SQLException e) {
			isValid = false;
			e.printStackTrace();
			mensaje = "No se pudo crear el registro del pedido: " + e.getLocalizedMessage();
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}

		if (isValid) {
			RegistroPedidoResponseDTO responsePedido = new RegistroPedidoResponseDTO();
			responsePedido.setIdRegistroPedido(idPedido);
			msg.setMessage("Pedido registrado exitosamente");
			msg.setData(responsePedido);
			msg.setStatus(JsonUtil.SUCESS);
		}

		JSONObject jsonObject = new JSONObject(msg);
		String myJson = jsonObject.toString();
		response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(myJson);

		return null;

	}

	public Object sendMessage(JsonUtil msg, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject(msg);
		String myJson = jsonObject.toString();
		response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(myJson);

		return null;
	}
}
