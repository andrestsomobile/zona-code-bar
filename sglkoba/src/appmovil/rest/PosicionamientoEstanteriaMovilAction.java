package appmovil.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import maestro.control.gstbodega;
import maestro.entity.bodega;
import util.JsonUtil;

public class PosicionamientoEstanteriaMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mensaje = "";
		boolean isValid = true;
		JsonUtil msg = new JsonUtil();
		
		try {
			gstbodega gbodega = new gstbodega();
			Collection posicionamientoList = gbodega.getPosicionamientoEstanteria();
			PosicionamientoEstanteriaDTO temp = null;
			List<PosicionamientoEstanteriaDTO> estanteriaList = new ArrayList<PosicionamientoEstanteriaDTO>();
			for(Object obj: posicionamientoList) {
				bodega t = (bodega) obj;
				temp = new PosicionamientoEstanteriaDTO();
				temp.setBodega(t.getbodcodsx());
				temp.setPosicion(t.getbodnombre());
				temp.setCantidad(t.getboddesc());
				estanteriaList.add(temp);
			}
			
			
			msg.setData(estanteriaList);
			
			if(estanteriaList != null && !estanteriaList.isEmpty()) {
				msg.setMessage("Estanteria listada exitosamente");
				msg.setStatus(JsonUtil.SUCESS);
			} else {
				msg.setStatus(JsonUtil.FAIL);
				msg.setMessage("No se encontro estanteria");
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
