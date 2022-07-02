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

import ingreso.control.gstingreso;
import ingreso.control.gsttrafico;
import ingreso.entity.ingreso;
import ingreso.entity.trafico;
import ingreso.form.ingresoForm;
import maestro.control.gstbodega;
import util.JsonUtil;

public class EntradaMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		gstingreso control = new gstingreso();
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String trafico = request.getParameter("trafico");
		String codigoBarra = request.getParameter("codigoBarra");
		String ubicacion = request.getParameter("ubicacion");
		String cantidad = request.getParameter("cantidad");
		String usuario = request.getParameter("usuario");
		String bodega = "";
		boolean isValid = true;
		String mensaje = "";
		JsonUtil msg = new JsonUtil();
		
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
		
		if (codigoBarra == null || codigoBarra.isEmpty()) {
			isValid = false;
			mensaje = "Producto es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}
		
		if (cantidad == null || cantidad.isEmpty()) {
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
			
			ingreso ing = control.getIngresoByTrafico(ingcodcia, trafico, ingtipo);
			// ahora agrego las referencias usando la informacion de lotes y referencia_factura
			if (ing != null && ing.getingtipo().equalsIgnoreCase("TRAFICO")) {
				mensaje = "Entrada creada exitosamente";
				gstbodega gsbodega = new gstbodega();
				maestro.entity.bodega bod = gsbodega.getbodega_by_ukey(bodega);
				
				if(bod == null) {
					isValid = false;
					mensaje = "No existe la bodega " + bodega;
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
				}
				
				if(isValid)
					control.crearEntradasTraficoMovil(codigoBarra,ing.getingcodsx(), ing.getingtrafico(), bod.getbodcodsx(), ubicacion,cantidad);
			} else {
				if(traf.getTrafestado().equals(ingestado)) {
					control.crearingreso(ingcodcia, ingfecha, ingtipo, ingtrafico == null || (ingtrafico != null && ingtrafico.equals("")) ? null : ingtrafico,

					ingobservacion, ingestado, ingtipo.equals("DEVOLUCION") ? ingcausal : null, ingclidevolucion == null ? null : ingclidevolucion.equals("") ? null : ingclidevolucion,

					"0", "0", "0", "0", ingciudad_dev == null ? null : ingciudad_dev.equals("") ? null : ingciudad_dev, ingconsec, ingconseczfp, ingplanilla, ingtipoasignacion, usuario);
					
					ing = control.getIngresoByTrafico(ingcodcia, trafico, ingtipo);
					// ahora agrego las referencias usando la informacion de lotes y referencia_factura
					if (ing != null && ing.getingtipo().equalsIgnoreCase("TRAFICO")) {
						control.crearEntradasTraficoMovil(codigoBarra,ing.getingcodsx(), ing.getingtrafico(), bodega, ubicacion,cantidad);
					}
					
					mensaje = "Entrada creada exitosamente";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.SUCESS);
				} else {
					mensaje = "No se pudo Crear el ingreso: El trafico no esta en TRAMITE ";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
				}
			}

			if(isValid) {
				msg.setMessage(mensaje);
				msg.setStatus(JsonUtil.SUCESS);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No se pudo crear la entrada: <br> " + e.getLocalizedMessage();
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
