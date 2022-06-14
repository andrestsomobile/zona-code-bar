package reportes.util;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import db.GstTabla;
import db.beanConnector;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import reportes.form.reportesForm;

public class informe_ingresos extends Command {

	public reporte rep = new reporte();
	
	public void execute(String ruta, HttpServletRequest request, reportesForm repfor) {

		exportar(request, repfor);
	}

	private void exportar(HttpServletRequest request, reportesForm repfor) {
		String ruta = request.getRealPath("") + File.separator + "uploads" + File.separator;
		String nomarch = "Informe_Ingresos" + ".xls";
		File archivo = new File(ruta, nomarch);
		WritableFont letra = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
		WritableCellFormat format = new WritableCellFormat(letra);
		String vectorTit[] = {
				"#IMPO","PROVEEDOR","MATERIAL","PRODUCTO","UMP X ESTIBAS","UMP","PESO X UMP","PUERTO LLEGADA","CONTENEDOR","TIPO","UMP ESPERADAS ","UMP RECIBIDAS","DIF.RECIBO "
				,"ESTIBAS RECIBIDAS","LOTE","F. VENCIMIENTO","AVERIAS RECIBIDAS","AVERIAS RECUPERADAS ","AVERIAS TOTALES X UMP","COSTO X UND DDP","VALOR AVERIA A DDP","ESTADO DE RECLAMACION"
				,"VALOR PESO AVERIA RECLAMACION","TIPO NOVEDAD","OBSERVACIONES AVERIAS","VALOR TOTAL RECLAMACION","ESTIBAS","PESO NETO","PESO TOTAL + TARA","TARA","TOLERANCIA PESO","PESO SIN TARA"
				,"DIF. NETO-SIN TARA","DIFERENCIA","ITR","VALOR FOB","TRANSPORTADOR","PLACA","NOMBRE DEL CONDUCTOR","CEDULA","NUMERO DE CONTENEDOR","PRECINTO","NOVEDADES SELLOS","FECHA LEVANTE"
				,"SEMANA DE ENTREGA","FECHA ENTREGA PLANILLAS APROX","ETA ESPERADACD","CITA DESCARGUE","ETA REAL CEDI","HORA INGRESO","HORA SALIDA","LUGAR DE DEVOLUCION","STATUS","RECIBE LOGISTICA"
				,"RESPONSABLE RECLAMACION","ESTADO COSTEO","NOVEDADES COSTEO","AJUSTE INVENTARIO"
				};
		int renglon = 0;
		int col = 0;
		try {
			WritableWorkbook book = Workbook.createWorkbook(archivo);
			WritableSheet hoja = book.createSheet("Informe_Ingresos", 0);
			hoja.addCell(new Label(0,renglon++,"INFORME DE INGRESOS", format));
			hoja.addCell(new Label(0,renglon,"Fechas Seleccionadas:", format));
			hoja.addCell(new Label(1,renglon++,repfor.getFecha_ini() + " a " + repfor.getFecha_fin(), format));

			for(int i = 0; i < vectorTit.length; i++){
				hoja.addCell(new Label(col++, renglon, vectorTit[i], format));
			}
			Collection registros = rep.informe_ingresos(repfor.getFecha_ini(), repfor.getFecha_fin());
			Iterator it = registros.iterator();			
			while (it.hasNext()) {
				renglon++;
				col = 0;
				Collection datos = (Collection) it.next();
				Iterator data = datos.iterator();
				while (data.hasNext()) {
					String val =  (String) data.next();
					hoja.addCell(new Label(col++, renglon, val, format));
				}
			}
	
			book.write();
			book.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}
	
	
	public class reporte extends GstTabla {
		
		public reporte() {
			db = new beanConnector();
		}


		
		public Collection informe_ingresos (String fecha_ini,String fecha_fin)  {
			
			if(fecha_ini!=null && fecha_ini.indexOf(" ")==-1) fecha_ini += " 00:00:00";
			if(fecha_fin!=null && fecha_fin.indexOf(" ")==-1) fecha_fin+= " 23:59:59";
			String consulta = "select\r\n" + 
					"trafnumdta as \"#IMPO\"\r\n" + 
					", ltrafproveedor as \"PROVEEDOR\"\r\n" + 
					", promodelo as \"MATERIAL\"\r\n" + 
					", prodescripcion as \"PRODUCTO\"\r\n" + 
					", prouniempaque as \"UMP X ESTIBAS\"\r\n" + 
					", prounimasterpac as \"UMP\"\r\n" + 
					", prokiloreal as \"PESO X UMP\"\r\n" + 
					", '' as \"PUERTO LLEGADA\"\r\n" + 
					", lctrafcontenedor as \"CONTENEDOR\"\r\n" + 
					", ctraftamano as \"TIPO\"\r\n" + 
					", (select sum(reftcantidad) from referencia_trafico where reftnumfacturatrafico = trafcodsx and reftproducto = procodsx) as \"UMP ESPERADAS \"\r\n" + 
					", lctrafcantidad as \"UMP RECIBIDAS\"\r\n" + 
					", 0 as \"DIF.RECIBO \"\r\n" + 
					", lctrafcantidad as \"ESTIBAS RECIBIDAS\"\r\n" + 
					", ltraflote as \"LOTE\"\r\n" + 
					", ltrafvencimiento as \"F. VENCIMIENTO\"\r\n" + 
					", 0 as \"AVERIAS RECIBIDAS\"\r\n" + 
					", 0 as \"AVERIAS RECUPERADAS \"\r\n" + 
					", 0 as \"AVERIAS TOTALES X UMP\"\r\n" + 
					", 0 as \"COSTO X UND DDP\"\r\n" + 
					", 0 as \"VALOR AVERIA A DDP\"\r\n" + 
					", '' as \"ESTADO DE RECLAMACION\"\r\n" + 
					", 0 as \"VALOR PESO AVERIA RECLAMACION\"\r\n" + 
					", '' as \"TIPO NOVEDAD\"\r\n" + 
					", '' as \"OBSERVACIONES AVERIAS\"\r\n" + 
					", 0 as \"VALOR TOTAL RECLAMACION\"\r\n" + 
					", lctrafcantidad/prouniempaque as \"ESTIBAS\"\r\n" + 
					", lctrafcantidad*prokiloreal as \"PESO NETO\"\r\n" + 
					", lctrafcantidad*prokiloreal as \"PESO TOTAL + TARA\"\r\n" + 
					", 0 as \"TARA\"\r\n" + 
					", 0 as \"TOLERANCIA PESO\"\r\n" + 
					", 0 as \"PESO SIN TARA\"\r\n" + 
					", 0 as \"DIF. NETO-SIN TARA\"\r\n" + 
					", 0 as \"DIFERENCIA\"\r\n" + 
					", 0 as \"ITR\"\r\n" + 
					", lctrafcantidad*(select reftpesoneto from referencia_trafico where reftnumfacturatrafico = trafcodsx and reftproducto = procodsx LIMIT 1) as \"VALOR FOB\"\r\n" + 
					", transpnombre as \"TRANSPORTADOR\"\r\n" + 
					", ctrafplaca as \"PLACA\"\r\n" + 
					", ctrafconductor as \"NOMBRE DEL CONDUCTOR\"\r\n" + 
					", ctrafcedula as \"CEDULA\"\r\n" + 
					", ctrafnumero as \"NUMERO DE CONTENEDOR\"\r\n" + 
					", '' as \"PRECINTO\"\r\n" + 
					", '' as \"NOVEDADES SELLOS\"\r\n" + 
					", '' as \"FECHA LEVANTE\"\r\n" + 
					", '' as \"SEMANA DE ENTREGA\"\r\n" + 
					", '' as \"FECHA ENTREGA PLANILLAS APROX\"\r\n" + 
					", '' as \"ETA ESPERADACD\"\r\n" + 
					", '' as \"CITA DESCARGUE\"\r\n" + 
					", '' as \"ETA REAL CEDI\"\r\n" + 
					", ctrafiniciodescargue as \"HORA INGRESO\"\r\n" + 
					", ctraffindescargue as \"HORA SALIDA\"\r\n" + 
					", '' as \"LUGAR DE DEVOLUCION\"\r\n" + 
					", trafestado as \"STATUS\"\r\n" + 
					", '' as \"RECIBE LOGISTICA\"\r\n" + 
					", '' as \"RESPONSABLE RECLAMACION\"\r\n" + 
					", '' as \"ESTADO COSTEO\"\r\n" + 
					", '' as \"NOVEDADES COSTEO\"\r\n" + 
					", '' as \"AJUSTE INVENTARIO\"\r\n" + 
					"\r\n" + 
					"from trafico\r\n" + 
					"left join lote_trafico on ltrafnumtrafico = trafcodsx\r\n" + 
					"left join producto on procodsx = ltrafcodproducto\r\n" + 
					"left join lote_contenedor_trafico on lctraflote = ltrafcodsx\r\n" + 
					"left join contenedor_trafico on ctrafcodsx = lctrafcontenedor\r\n" + 
					"left join transportadora on transpcodsx = traftransportadora\r\n" + 
					"where traffechacreacion between '" + fecha_ini + "' and '" + fecha_fin + "'\r\n" + 
					"order by 1,3 ";
			return getListaDeListas(consulta);
		}
		
	}
		
}
