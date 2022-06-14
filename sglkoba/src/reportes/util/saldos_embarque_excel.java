package reportes.util;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maestro.control.gstcompania;
import maestro.entity.compania;
import reportes.form.reportesForm;
import db.GstTabla;
import db.beanConnector;

public class saldos_embarque_excel extends Command {

	public reporte rep = new reporte();
	
	public void execute(String ruta, HttpServletRequest request, reportesForm repfor) {

		exportar(request, repfor);
	}

	private void exportar(HttpServletRequest request, reportesForm repfor) {
		String ruta = request.getRealPath("") + File.separator + "uploads" + File.separator;
		String nomarch = "SaldosEmbarque" + ".xls";
		File archivo = new File(ruta, nomarch);
		gstcompania gstcom = new gstcompania();
		compania com = gstcom.getcompania(repfor.getCompania());		
		WritableFont letraT = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat formatT = new WritableCellFormat(letraT);
		WritableFont letra = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.NO_BOLD);
		WritableCellFormat format = new WritableCellFormat(letra);
		NumberFormat numero = new jxl.write.NumberFormat ("#,##0");
		WritableCellFormat formatonumero = new jxl.write.WritableCellFormat (numero);  
		
		Collection registros = rep.encabezado(repfor.getCompania(), repfor.getFecha_ini(), repfor.getFecha_fin(), repfor.getEmbarque());
		Iterator it = registros.iterator();
		
		int renglon = 0;
		int col = 0;
		
		try {
			WritableWorkbook book = Workbook.createWorkbook(archivo);
			WritableSheet hoja = book.createSheet("SaldosEmbarque", 0); 
			//Merge col[0-11]
		    hoja.mergeCells(0, renglon, 11, renglon);
			hoja.addCell(new Label(col,renglon++,"Saldos por Embarque/Documento", formatT));
			hoja.mergeCells(0, renglon, 11, renglon);
			hoja.addCell(new Label(col,renglon++,"Compa�ia: " + com.getcomnombre(), formatT));
			hoja.mergeCells(0, renglon, 11, renglon);
			hoja.addCell(new Label(col,renglon++,"Fechas desde: " + repfor.getFecha_ini() + " hasta: " + repfor.getFecha_fin(), formatT));
			hoja.mergeCells(0, renglon, 11, renglon);
			hoja.addCell(new Label(col,renglon++,"Embarque: " + repfor.getEmbarque(), formatT));
			hoja.addCell(new Label(col++, renglon, "Ingreso", formatT));
			hoja.addCell(new Label(col++, renglon, "Embarque", formatT));
			hoja.addCell(new Label(col++, renglon, "Documento", formatT));
			hoja.addCell(new Label(col++, renglon, "Transito", formatT));
			hoja.addCell(new Label(col++, renglon, "Fecha", formatT));
			while (it.hasNext()) {
				renglon++;
				col = 0;
				Collection datos = (Collection) it.next();
				Iterator data = datos.iterator();
				String ingreso =  (String) data.next();
				String embarque =  (String) data.next();
				String documento =  (String) data.next();
				String transito =  (String) data.next();
				String fecha =  (String) data.next();
				
				hoja.addCell(new Label(col++, renglon, ingreso, format));
				hoja.addCell(new Label(col++, renglon, embarque, format));
				hoja.addCell(new Label(col++, renglon, documento, format));
				hoja.addCell(new Label(col++, renglon, transito, format));
				hoja.addCell(new Label(col++, renglon, fecha, format));
				
				renglon = renglon + 2;
				Collection detalles = rep.detalle(ingreso);
				Iterator itdet = detalles.iterator();
				
				if (itdet.hasNext()) {
					col = 0;
					hoja.addCell(new Label(col++, renglon, "Modelo", formatT));
					hoja.addCell(new Label(col++, renglon, "Descripcion", formatT));
					hoja.addCell(new Label(col++, renglon, "Cantidad Ingreso", formatT));
					hoja.addCell(new Label(col++, renglon, "Cantidad Averiada", formatT));
					hoja.addCell(new Label(col++, renglon, "Peso Neto", formatT));
					hoja.addCell(new Label(col++, renglon, "Peso Bruto", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo Peso N", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo Peso B", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo NAC", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo NNAC", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo AVE", formatT));
					hoja.addCell(new Label(col++, renglon, "Saldo TOTAL", formatT));
					renglon++;
					int reg_ini = renglon + 1;
					while (itdet.hasNext()) {
						col = 0;
						Collection datosdet = (Collection) itdet.next();
						Iterator datadet = datosdet.iterator();
						String modelo =  (String) datadet.next();
						String descripcion =  (String) datadet.next();
						String cantingreso =  (String) datadet.next();
						String cantave =  (String) datadet.next();
						String pesoneto =  (String) datadet.next();
						String pesobruto =  (String) datadet.next();
						String saldopesoN =  (String) datadet.next();
						String saldopesoB =  (String) datadet.next();
						String saldoNAC =  (String) datadet.next();
						String saldoNNAC =  (String) datadet.next();
						String saldoAVE =  (String) datadet.next();
						String saldoTOT =  (String) datadet.next();
						
						hoja.addCell(new Label(col++, renglon, modelo, format));
						hoja.addCell(new Label(col++, renglon, descripcion, format));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(cantingreso) ,formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(cantave), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(pesoneto), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(pesobruto), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldopesoN), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldopesoB), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldoNAC), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldoNNAC), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldoAVE), formatonumero));
						hoja.addCell(new Number(col++, renglon, Double.parseDouble(saldoTOT), formatonumero));
						renglon++;
					}
					col = 0;
					//Merge col[0-11]
				    hoja.mergeCells(0, renglon, 1, renglon);
					hoja.addCell(new Label(col,renglon,"TOTALES", formatT));
					col = 2;
					
					Formula fC = new Formula(col++, renglon, "SUMA(C" + reg_ini + ":C" + renglon + ")",formatonumero );
					hoja.addCell(fC);
					Formula fD = new Formula(col++, renglon, "SUMA(D" + reg_ini + ":D" + renglon + ")",formatonumero );
					hoja.addCell(fD);
					Formula fE = new Formula(col++, renglon, "SUMA(E" + reg_ini + ":E" + renglon + ")",formatonumero );
					hoja.addCell(fE);
					Formula fF = new Formula(col++, renglon, "SUMA(F" + reg_ini + ":F" + renglon + ")",formatonumero );
					hoja.addCell(fF);
					Formula fG = new Formula(col++, renglon, "SUMA(G" + reg_ini + ":G" + renglon + ")",formatonumero );
					hoja.addCell(fG);
					Formula fH = new Formula(col++, renglon, "SUMA(H" + reg_ini + ":H" + renglon + ")",formatonumero );
					hoja.addCell(fH);
					Formula fI = new Formula(col++, renglon, "SUMA(I" + reg_ini + ":I" + renglon + ")",formatonumero );
					hoja.addCell(fI);
					Formula fJ = new Formula(col++, renglon, "SUMA(J" + reg_ini + ":J" + renglon + ")",formatonumero );
					hoja.addCell(fJ);
					Formula fK = new Formula(col++, renglon, "SUMA(K" + reg_ini + ":K" + renglon + ")",formatonumero );
					hoja.addCell(fK);
					Formula fL = new Formula(col++, renglon, "SUMA(L" + reg_ini + ":L" + renglon + ")",formatonumero );
					hoja.addCell(fL);
				}
				renglon++;
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

		public Collection encabezado (String codcia, String fi, String ff, String emb)  {
			String consulta = "select ingcodsx,trafembarque,trafdocumento,trafnumdta,ingfecha from reporte_saldos_embarque where comcodsx = "+ codcia +
					" and ingfecha between '"+ fi +"' and '" +ff +"' " +
					" and (trafembarque like '%" + emb +"%' " +
					" or trafdocumento like '%" +emb +"%')";
			return getListaDeListas(consulta);
		}	
		
		public Collection detalle (String ing)  {
			String consulta = "select " +
					"promodelo, prodescripcion, " + 
					"sum(entcantidad) as cantproducto, " + 
					"coalesce(sum(avecant_pleg_nac + avecant_pleg_nnac + avecant_prod_nac + avecant_prod_nnac),0) as total_averiado, " +
					"sum(entpesonetototal) as pesonetoprod, " +
					"sum(entpesobrutototal) as pesobrutoprod, " +
					"sum(entsaldopesoneto) as saldopesoneto, " +
					"sum(entsaldopesobruto) as saldopesobruto, " +
					"sum(entsaldonacf) as saldonac, " + 
					"sum(entsaldosinnacf) as saldosinnac, " + 
					"coalesce(sum(avesaldo_pleg_nac + avesaldo_pleg_nnac + avesaldo_prod_nac + avesaldo_prod_nnac),0) as saldo_averiado, " +
					"( sum(entsaldonacf) + sum(entsaldosinnacf) + coalesce(sum(avesaldo_pleg_nac + avesaldo_pleg_nnac + avesaldo_prod_nac + avesaldo_prod_nnac),0)) as saldototal " +
					"FROM  entrada " +
					"inner join producto on procodsx = entcodproducto " +
					"left join averia on aveentrada = entcodsx " +
					"where entcodingreso = " + ing + " " +
					"group by entcodsx,entcodproducto, promodelo, prodescripcion " +
					"order by  promodelo asc" ;
			return getListaDeListas(consulta);
		}
		
	}
		
}
