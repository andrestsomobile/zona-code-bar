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
import util.JsonUtil;

public class FinalizarIngresoMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		gstingreso control = new gstingreso();
		String mensaje = "";
		JsonUtil msg = new JsonUtil();
		String trafico = request.getParameter("trafico");
		boolean isValid = true;

		if (trafico == null || trafico.isEmpty()) {
			isValid = false;
			mensaje = "Trafico es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
		}

		gsttrafico gusu = new gsttrafico();
		trafico traf = gusu.gettrafico(trafico);

		try {
			if (isValid) {

				// cuando finalize un ingreso,
				if (traf.getTrafestado().equals("TRAMITE")) {
					mensaje = "NO PUEDE FINALIZAR EL INGRESO HASTA NO FINALIZAR EL TRAFICO ASOCIADO NUMERO :" + traf.getTrafcodsx();
					control.updateIngresoMovil(traf.getTrafcodsx(), "FINALIZADO");
					traf.setTrafestado("FINALIZADO");
					gusu.updatetrafico(traf);
					mensaje = "ingreso Actualizado con Exito";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.SUCESS);
				} else {
					mensaje = "No se pudo finalizar el ingreso: El trafico no esta en TRAMITE ";
					msg.setMessage(mensaje);
					msg.setStatus(JsonUtil.FAIL);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No se pudo finalizar el ingreso: " + e.getLocalizedMessage();
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
