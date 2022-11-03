package appmovil.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import pedido.entity.pedido;
import registro.control.gstregistro_pedido;
import registro.control.gstregistro_pedido_detalle;
import registro.entity.registro_pedido;
import registro.entity.registro_pedido_detalle;
import util.JsonUtil;

public class UpdateRegistroPedidoMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mensaje = "";
		boolean isValid = true;
		JsonUtil msg = new JsonUtil();
		String idRegistroPedido = request.getParameter("registroPedido");
		String pedido = request.getParameter("pedido");
		String novedad = request.getParameter("novedad");
		String producto = request.getParameter("producto");
		String posicion = request.getParameter("posicion");
		
		if (pedido == null || pedido.isEmpty()) {
			isValid = false;
			mensaje = "Pedido es obligatorio";
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
				
				
				gstregistro_pedido grpedido = new gstregistro_pedido();
				registro_pedido rpedido = grpedido.getregistro_pedido(idRegistroPedido);
				
				
				if(rpedido != null) {
					gstpedido gstpedido = new gstpedido();
					pedido ped = gstpedido.getpedidoByNumPedidoDate(pedido, ingfecha);
					gstregistro_pedido_detalle grdpedido = new gstregistro_pedido_detalle();
					registro_pedido_detalle rdpedido = grdpedido.getregistro_pedido_detalle_numpedido(rpedido.getrepecodsx(), ped.getpedcodsx(),
							producto, posicion);
					rdpedido.setrpnovedad(novedad);
					rdpedido.setrpdeterminado("S");
					rdpedido.setrpdetire(inghora);
					rdpedido.setRpdeproducto(producto);
					rdpedido.setRpdeposicion(posicion);
					boolean edito = grdpedido.updateregistro_pedido_detalle(rdpedido);
					
					if(edito) {
						int countPendiente = grdpedido.getregistro_pedido_detalle_pendiente(rpedido.getrepecodsx());
						
						if(countPendiente == 0) {
							rpedido.setrepehofi(inghora);
							rpedido.setrepeesta("FINALIZADO");
							boolean editoRegistro = grpedido.updateregistro_pedido(rpedido);
							
							if(!editoRegistro) {
								isValid = false;
								mensaje = "El registro del pedido no se completo";
								msg.setMessage(mensaje);
								msg.setStatus(JsonUtil.FAIL);
							} else {
								mensaje = "Registro completado correctamente";
								msg.setMessage(mensaje);
								msg.setStatus(JsonUtil.SUCESS);
							}
						} else {
							mensaje = "Registro finalizado correctamente";
							msg.setMessage(mensaje);
							msg.setStatus(JsonUtil.SUCESS);
						}
					}
				} else {
					isValid = false;
					mensaje = "El registro del pedido es invalido";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
				}
			}

		} catch (Exception e) {
			isValid = false;
			e.printStackTrace();
			mensaje = "No se pudo finalizar el registro del pedido: " + e.getLocalizedMessage();
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}

		JSONObject jsonObject = new JSONObject(msg);
		String myJson = jsonObject.toString();
		response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(myJson);

		return null;

	}
}
