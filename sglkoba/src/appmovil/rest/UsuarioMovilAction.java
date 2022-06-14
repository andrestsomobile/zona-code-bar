package appmovil.rest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import administracion.control.gstusuario;
import administracion.entity.empresa;
import administracion.entity.grupo;
import administracion.entity.usuario;
import administracion.form.usuarioForm;
import auditoria.control.gstauditoria_conexiones;
import util.JsonUtil;
import util.Propiedades;

public class UsuarioMovilAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		String compania = request.getParameter("compania");
		String modo = request.getParameter("modo");
		JsonUtil msg = new JsonUtil();

		gstusuario gusu = new gstusuario();
		usuario usu = gusu.validar(login, clave);
		// db.
		String destino = "";
		if (usu == null)
			if (modo != null && modo.equalsIgnoreCase("movil")) {
				destino = "index_movil";
				msg.setMessage("Usuario/Clave invalida " + login + " pas " + clave);
				msg.setStatus(JsonUtil.FAIL);
			} else {
				destino = "index";
				msg.setMessage("Usuario/Clave invalida " + login + " pas " + clave);
				msg.setStatus(JsonUtil.FAIL);
			}
		else {

			HttpSession sesion = request.getSession(true);
			gstgrupo ggrupo = new gstgrupo();
			grupo gru = ggrupo.getgrupo(usu.getusugrupo());
			gstempresa gemp = new gstempresa();
			empresa emp = gemp.getempresa("1");
			sesion.setAttribute("usuario", usu);
			sesion.setAttribute("grupo", gru);
			sesion.setAttribute("empresa", emp);
			gusu.asignarusuario(login); // le define a la bd quien es el usuario efectivo
			if (modo != null && modo.equalsIgnoreCase("movil")) {
				destino = "main_movil";
			} else {
				destino = "main";
			}
			msg.setData(usu.getusunombre());
			msg.setStatus(JsonUtil.SUCESS);

			// destino = "main";
			/*
			 * String soName = System.getProperty("os.name"); if(soName.equals("Windows XP")){//verifico si lo estoy ejecutando en pc personal sesion.setAttribute("ruta_upload", "C://TMP/"); }else{ sesion.setAttribute("ruta_upload", "/home/ricardo/uploads/"); }
			 */
			String ruta = request.getRealPath("") + File.separator + Propiedades.getProperties("ruta_upload") + File.separator;
			sesion.setAttribute("ruta_upload", ruta);
			System.out.println("ruta_upload:" + ruta);
			// setDataBase();

			// registro en la auditoria que se logeo:
			gstauditoria_conexiones gaudi = new gstauditoria_conexiones();
			try {
				gaudi.crearauditoria_conexiones(login, usu.getusunombre(), util.Fecha.getFecha(), "Ingreso", "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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