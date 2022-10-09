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
import maestro.entity.producto;
import util.JsonUtil;

public class CrearInventarioMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GstInventario control = new GstInventario();
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String codigoInventario = request.getParameter("codigoInventario");
		String conteo = request.getParameter("conteo");
		String terminal = request.getParameter("terminal");
		String usuario = request.getParameter("usuario");
		String ubicacion = request.getParameter("bodega");
		String referencia = request.getParameter("referencia");
		String ingfecha = s.format(date);
		String bodega = "";
		boolean isValid = true;
		String mensaje = "";
		JsonUtil msg = new JsonUtil();
		
		if (referencia == null || referencia.isEmpty()) {
			isValid = false;
			mensaje = "Referencia es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}
		
		if(ubicacion == null || ubicacion.isEmpty()) {
			isValid = false;
			mensaje = "Ubicacion es obligatoria";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}
		
		if (conteo == null || conteo.isEmpty()) {
			isValid = false;
			mensaje = "Conteo es obligatoria";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}
		
		if (terminal == null || terminal.isEmpty()) {
			isValid = false;
			mensaje = "Terminal es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}
		
		try {
			
			if(isValid) {
				
				String[] rcReferencia = referencia.split(",");
				String[] rcUbicacion = ubicacion.split(",");
				int i = 0;
				String location = "";
				int grupoId = control.getGrupoId();
				for(String ref: rcReferencia) {
					String[] strUbicacion = rcUbicacion[i].split(" ");
					
					if(strUbicacion.length < 0) {
						isValid = false;
						mensaje = "La ubicacion no es correcta, debe ser BODEGA POSICION";
						msg.setMessage(mensaje);
						msg.setStatus(JsonUtil.FAIL);
					} else {
						bodega = strUbicacion[0];
						location = strUbicacion[1];
					}
					
					if(isValid) {
						gstbodega gsbodega = new gstbodega();
						maestro.entity.bodega bod = gsbodega.getbodega_by_ukey(bodega);
						
						if(bod == null) {
							isValid = false;
							mensaje = "No existe la bodega " + bodega;
							msg.setMessage(mensaje);
							msg.setStatus(JsonUtil.FAIL);
						}
						
						gstproducto gstproducto = new gstproducto();
						producto p = gstproducto.getProductoByCodBarra(ref);
						
						if(p == null) {
							isValid = false;
							mensaje = "No existe el producto " + referencia;
							msg.setMessage(mensaje);
							msg.setStatus(JsonUtil.FAIL);
						}
						
						if(isValid) {
							boolean isSucessfull = false;
							if(codigoInventario == null || codigoInventario.isEmpty()) {
								isSucessfull = control.crear(conteo, terminal, usuario, bod.getbodcodsx(), location, ref, 
										p.getprocodsx(), ingfecha, grupoId);
							} else {
								isSucessfull = control.update(codigoInventario, conteo, terminal, usuario, bod.getbodcodsx(), location, ref, 
										p.getprocodsx(), ingfecha, grupoId);
							}
							
							if(isSucessfull) {
								mensaje = "Inventario guardado exitosamente";
								msg.setMessage(mensaje);
								msg.setStatus(JsonUtil.SUCESS);
							} else {
								mensaje = "No se pudo guardar el inventario ";
								msg.setMessage(mensaje);
								msg.setStatus(JsonUtil.FAIL);
							}
						}
					}
					i ++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No se pudo guardar el inventario: <br> " + e.getLocalizedMessage();
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
