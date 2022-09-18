package appmovil.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import ingreso.control.gstingreso;
import ingreso.control.gsttrafico;
import ingreso.entity.trafico;
import util.JsonUtil;

public class IngresoMovilAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		gstingreso control = new gstingreso();
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mensaje = "";
		JsonUtil msg = new JsonUtil();
		String trafico = request.getParameter("trafico");
		String codigoBarra = request.getParameter("codigoBarra");
		String ubicacion = request.getParameter("ubicacion");
		String cantidad = request.getParameter("cantidad");
		String usuario = request.getParameter("usuario");
		String bodega = "";
		boolean isValid = true;
		
		if(ubicacion != null && !ubicacion.isEmpty()) {
			String[] strUbicacion = ubicacion.split(" ");
			bodega = strUbicacion[0];
			ubicacion = strUbicacion[1];
		} else {
			isValid = false;
			mensaje = "La ubicacion es obligatoria";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}
		
		if(codigoBarra == null || codigoBarra.isEmpty()) {
			isValid = false;
			mensaje = "Producto es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}	
		
		if(cantidad == null || cantidad.isEmpty()) {
			isValid = false;
			mensaje = "Cantidad es obligatoria";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}	
		
		if (trafico == null || trafico.isEmpty()) {
			isValid = false;
			mensaje = "Trafico es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}
		
		gsttrafico gusu = new gsttrafico();
		trafico traf = gusu.gettrafico(trafico);
		String ingcodcia = traf.gettrafcompania();
		String ingfecha = s.format(date);
		String ingtipo = "TRAFICO";
		String ingtrafico = trafico;
		String ingobservacion = "";
		String ingestado = "TRAMITE";
		String ingcausal = "";
		String ingclidevolucion = "";
		String ingciudad_dev = "";
		String ingconsec = null;
		String ingconseczfp =null;
		String ingplanilla = "";
		String ingtipoasignacion = "AUTOMATICO";
		
		try {
			if(isValid) {
				if(traf.getTrafestado().equals(ingestado)) {
					control.crearingreso(ingcodcia, ingfecha, ingtipo, ingtrafico == null || (ingtrafico != null && ingtrafico.equals("")) ? null : ingtrafico,

					ingobservacion, ingestado, ingtipo.equals("DEVOLUCION") ? ingcausal : null, ingclidevolucion == null ? null : ingclidevolucion.equals("") ? null : ingclidevolucion,

					"0", "0", "0", "0", ingciudad_dev == null ? null : ingciudad_dev.equals("") ? null : ingciudad_dev, ingconsec, ingconseczfp, ingplanilla, ingtipoasignacion, usuario);
					mensaje = "ingreso Creado con Exito";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.SUCESS);
				} else {
					mensaje = "No se pudo Crear el ingreso: El trafico no esta en TRAMITE ";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
				}
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No se pudo Crear el ingreso: " + e.getLocalizedMessage();
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
