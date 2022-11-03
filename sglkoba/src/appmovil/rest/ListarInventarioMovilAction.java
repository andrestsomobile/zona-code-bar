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
import maestro.control.gstproducto;
import util.JsonUtil;

public class ListarInventarioMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GstInventario control = new GstInventario();
		String mensaje = "";
		JsonUtil msg = new JsonUtil();
		
		try {			
			Collection traficoListDB = control.getAll();
			InventarioResponse temp = null;
			List<InventarioResponse> traficoList = new ArrayList<InventarioResponse>();
			for(Object obj: traficoListDB) {
				Inventario t = (Inventario) obj;
				temp = new InventarioResponse(t.getInvcodsx(), t.getInvcodsx() + " " + t.getInvfecha());
				traficoList.add(temp);
			}
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.SUCESS);
			msg.setData(traficoList);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "No se pudo listar el inventario: <br> " + e.getLocalizedMessage();
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
