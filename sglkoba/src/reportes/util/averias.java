package reportes.util;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import reportes.form.reportesForm;

public class averias extends Command {


	public void execute(String ruta, HttpServletRequest request,
			reportesForm repfor) {
		String cad_params = getCadParams( repfor);
		HashMap parametros = new HashMap();
		
		parametros.put("cad_params", cad_params);
		
		//ahora saco el reporte:
		
		
		File reporte = new File(  ruta + "reporte_averias.jasper");
				  
		
		String sql = getQuery(repfor, "reporte_averias"); 	
		
		parametros.put("sql", sql);
		
		request.setAttribute("parametros", parametros);
		request.setAttribute("reporte", reporte);


	}

}
