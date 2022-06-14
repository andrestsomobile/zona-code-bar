package appmovil.rest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.json.JSONObject;

import administracion.control.gstempresa;
import administracion.control.gstgrupo;
import administracion.control.gstparametro;
import administracion.entity.empresa;
import administracion.entity.grupo;
import administracion.entity.usuario;
import administracion.form.usuarioForm;
import auditoria.control.gstauditoria_conexiones;
import ingreso.control.gsttrafico;
import ingreso.entity.trafico;
import util.JsonUtil;
import util.Propiedades;

public class TraficoMovilAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


		JsonUtil msg = new JsonUtil();
		
		try {
			gsttrafico gusu = new gsttrafico();
			Collection traficoListDB = gusu.getlistatraficoActivo();
			trafico temp = null;
			List<trafico> traficoList = new ArrayList<trafico>();
			for(Object obj: traficoListDB) {
				trafico t = (trafico) obj;
				temp = new trafico(t.gettrafcodsx(), t.getTrafpedido());
				traficoList.add(temp);
			}
			msg.setData(traficoList);
			msg.setStatus(JsonUtil.SUCESS);
		} catch(Exception e) {
			msg.setMessage(e.getMessage());
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