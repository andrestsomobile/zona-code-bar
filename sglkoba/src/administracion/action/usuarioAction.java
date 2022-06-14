package administracion.action;import java.io.IOException;import javax.servlet.ServletException;import java.sql.SQLException;import org.apache.struts.action.ActionMessage;import org.apache.struts.action.ActionMessages;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import administracion.control.*;import administracion.entity.*;import administracion.form.*;import org.apache.struts.action.*;public final class usuarioAction extends Action {	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		usuarioForm _form = (usuarioForm) form;		gstusuario control = new gstusuario();		String opcion = request.getParameter("opcion");		opcion = opcion == null || (opcion != null && opcion.equals("")) ? "crear" : opcion;		// opcion de setcia:		if (opcion.equals("setcia")) {			request.setAttribute("cia", _form.getusucompania());			return mapping.findForward("usuario_datos");		}		// OPCION DE set:		if (opcion.equals("set")) {			usuario entity = control.getusuario(request.getParameter("codsx"));			usuarioForm temp = new usuarioForm();			temp.setopcion("update");			temp.llenar(entity);			request.setAttribute("usuarioForm", temp);			return mapping.findForward("usuario_datos");		}		String destino = "";		String mensaje = "";		// opcion de CREAR		if (opcion.equals("crear")) {			String usunombre = _form.getusunombre();			String usulogin = _form.getusulogin();			String usuclave = _form.getusuclave();			String usumail = _form.getusumail();			String usugrupo = _form.getusugrupo();			String usucompania = _form.getusucompania();			String usucodcliente = _form.getusucodcliente();			try {				control.crearusuario(usunombre, usulogin, usuclave, usumail, usugrupo, usucompania, usucodcliente);				mensaje = "usuario Creado con Exito";				destino = "usuario";			} catch (SQLException e) {				e.printStackTrace();				mensaje = "No se pudo Crear el usuario: <br> " + e.getLocalizedMessage();				destino = "usuario_datos";			}		}		// Opcion de update		if (opcion.equals("update")) {			try {				control.updateusuario(_form);				mensaje = "usuario Actualizado con Exito";				destino = "usuario";			} catch (SQLException e) {				e.printStackTrace();				mensaje = "No se pudo Actualizar el usuario: <br> " + e.getLocalizedMessage();				destino = "usuario_datos";			}		}		// Opcion de eliminar		if (opcion.equals("delete")) {			String codsx = request.getParameter("codsx");			try {				control.eliminar(codsx);				mensaje = "usuario Eliminado con Exito";				destino = "usuario";			} catch (SQLException e) {				e.printStackTrace();				mensaje = "No se pudo Eliminar el usuario: <br> " + e.getLocalizedMessage();			}		}		ActionMessages e = getErrors(request);		e.add("general", new ActionMessage(mensaje, false));		addErrors(request, e);		return mapping.findForward(destino);	}}