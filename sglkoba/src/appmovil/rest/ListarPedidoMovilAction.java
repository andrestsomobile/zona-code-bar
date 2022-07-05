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
import util.JsonUtil;

public class ListarPedidoMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mensaje = "";
		boolean isValid = true;
		JsonUtil msg = new JsonUtil();
		String pedido = request.getParameter("pedido");
		String usuario = request.getParameter("usuario");
		String idPedido = "";
		
		if (pedido == null || pedido.isEmpty()) {
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
				gstusuario gusu = new gstusuario();
				usuario user = gusu.getusuario_by_login(usuario);
				String cedula = user.getusucedula();
				
				gstempleado gemp = new gstempleado();
				empleado emp = gemp.getempleado_by_ukey(cedula);
				
				gstreferencia_pedido gstrpedido = new gstreferencia_pedido();
				Collection listPed = gstrpedido.getReferenciaByNumPedido(pedido);
				List<RegistroPedidoResponseDTO> responseDTO = new ArrayList<RegistroPedidoResponseDTO>();
				for(Object obj: listPed) {
					referencia_pedido r = (referencia_pedido) obj;
					RegistroPedidoResponseDTO responsePedido = new RegistroPedidoResponseDTO();
					responsePedido.setRefPedido(r);
					responseDTO.add(responsePedido);
				}
				
				msg.setMessage("Pedido listado exitosamente");
				msg.setData(responseDTO);
				msg.setStatus(JsonUtil.SUCESS);
			}

		} catch (Exception e) {
			isValid = false;
			e.printStackTrace();
			mensaje = "No se pudo listar pedido: " + e.getLocalizedMessage();
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