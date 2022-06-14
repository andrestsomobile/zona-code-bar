package administracion.action;

import java.io.IOException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import administracion.control.*;
import administracion.entity.*;
import administracion.form.*;
import org.apache.struts.action.*;

public final class parametroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		parametroForm _form = (parametroForm) form;
		gstparametro control = new gstparametro();
		String opcion = request.getParameter("opcion");
		opcion = opcion == null || (opcion != null && opcion.equals("")) ? "crear" : opcion;

		// opcion de setcia:
		//if (opcion.equals("setcia")) {
		//request.setAttribute("cia", _form.getusucompania());
		//return mapping.findForward("parametrizacion_datos");
		//}
		
		// OPCION DE set:
		if (opcion.equals("set")) {
			parametro entity = control.getpk_parametro(request.getParameter("pk_parametro"));
			parametroForm temp = new parametroForm();
			temp.setopcion("update");
			temp.llenar(entity);
			request.setAttribute("parametroForm", temp );
			return mapping.findForward("parametrizacion");
		}

		String destino = "";

		String mensaje = "";
		// opcion de CREAR
		if (opcion.equals("crear")) {
			String pk_parametro = _form.getpk_parametro();
			String nombre = _form.getnombre();
			String descripcion = _form.getdescripcion();
			String valor = _form.getvalor();
			String ubicacion =_form.getubicacion();
			String tipo_elemento = _form.gettipo_elemento();

			try {
				//control.crearusuario(nombre, descripcion, valor);
				control.crearparametro(pk_parametro, nombre, descripcion, valor, ubicacion, tipo_elemento);
				mensaje = "Parametro Creado con Exito";
				destino = "parametro";
			} catch (Exception e) {
				e.printStackTrace();
				mensaje = "No se pudo Crear el Parametro: <br> " + e.getLocalizedMessage();
				destino = "parametrizacion";
			}
		}
		// Opcion de update
		if (opcion.equals("update")) {
			try {
				//control.updatebodega(_form);
				mensaje = "parametro Actualizado con Exito";
				destino = "parametro";
			} catch (Exception e) {
				e.printStackTrace();
				mensaje = "No se pudo Actualizar el parametro: <br> " + e.getLocalizedMessage();
				destino = "parametrizacion";
			}
		}
		// Opcion de eliminar
		if (opcion.equals("delete")) {
			String pk_parametro = request.getParameter("pk_parametro");
			try {
				control.eliminar(pk_parametro);
				mensaje = "usuario Eliminado con Exito";
				destino = "parametro";
			} catch (SQLException e) {
				e.printStackTrace();
				mensaje = "No se pudo Eliminar el parametro: <br> " + e.getLocalizedMessage();
			}
		}
		ActionMessages e = getErrors(request);
		e.add("general", new ActionMessage(mensaje, false));
		addErrors(request, e);
		return mapping.findForward(destino);

	}
}