package ingreso.control;import ingreso.form.*;import ingreso.entity.*;import db.*;import java.util.Collection;import java.sql.SQLException;/******************************************************** ***** CLASE GENERADA CON generator V3.0 By pablito *******/public class gstcontenedor_trafico extends GstTabla {	public gstcontenedor_trafico() {		db = new beanConnector();		this.classEntidad = contenedor_trafico.class;	}	public gstcontenedor_trafico(beanConnector db) {		this.db = db;		this.classEntidad = contenedor_trafico.class;	}	public Collection getlistacontenedor_trafico() {		String consulta = " SELECT * FROM contenedor_trafico";		return getLista(consulta);	}	public Collection getlistacontenedor_trafico(String ctrafnumtrafico) {		String consulta = " SELECT * FROM contenedor_trafico where " + "  ctrafnumtrafico=" + ctrafnumtrafico;		return getLista(consulta);	}		public Collection getlistacontenedor_trafico_descargando(String ctrafnumtrafico, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM contenedor_trafico where " + "  ctrafnumtrafico=" + ctrafnumtrafico + " and ctrafestado='DESCARGANDO'";		return getLista(consulta);	}		public int getTotalPaginalistacontenedor_trafico_descargando(String ctrafnumtrafico, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM contenedor_trafico where " + "  ctrafnumtrafico=" + ctrafnumtrafico + " and ctrafestado='DESCARGANDO'";		return getPaginas(consulta);	}	public boolean crearcontenedor_trafico(String ctrafnumtrafico, String ctraftamano, String ctrafnumero, String ctrafplaca, String ctrafconductor, 			String ctrafcedula, String ctrafobservacion, String ctrafestado,String ctrafiniciodescargue) throws SQLException {		String insert = "INSERT INTO contenedor_trafico (" + " ctrafnumtrafico, ctraftamano, ctrafnumero, ctrafplaca, ctrafconductor," 	+ "	ctrafcedula, ctrafobservacion, ctrafestado,ctrafiniciodescargue) VALUES ("				+ (ctrafnumtrafico == null ? "NULL" : "'" + ctrafnumtrafico + "'") 				+ "," + (ctraftamano == null ? "NULL" : "'" + ctraftamano + "'") 				+ "," + (ctrafnumero == null ? "NULL" : "'" + ctrafnumero + "'") 				+ "," + (ctrafplaca == null ? "NULL" : "'" + ctrafplaca + "'") 				+ "," + (ctrafconductor == null ? "NULL" : "'" + ctrafconductor + "'") 				+ "," + (ctrafcedula == null ? "NULL" : "'" + ctrafcedula + "'") 				+ "," + (ctrafobservacion == null ? "NULL" : "'" + ctrafobservacion + "'") 				+ "," + (ctrafestado == null ? "NULL" : "'" + ctrafestado + "'") 				+ "," + (ctrafiniciodescargue == null ? "NULL" : "'" + ctrafiniciodescargue + "'") 				+ ")";		int resp = db.executeUpdate(insert);		return resp == 0 ? false : true;	}	public contenedor_trafico getcontenedor_trafico(String ctrafcodsx) {		String cad = "SELECT * FROM contenedor_trafico WHERE ctrafcodsx='" + ctrafcodsx + "'";		return (contenedor_trafico) getEntidad(cad);	}	public boolean updatecontenedor_trafico(contenedor_trafico entity) throws SQLException {		String cad = " update contenedor_trafico set  " 				+ " ctrafnumtrafico = " + (entity.getctrafnumtrafico() == null ? "NULL" : "'" + entity.getctrafnumtrafico() + "'") 				+ "," + " ctraftamano = " + (entity.getctraftamano() == null ? "NULL" : "'" + entity.getctraftamano() + "'") 				+ "," + " ctrafnumero = " + (entity.getctrafnumero() == null ? "NULL" : "'" + entity.getctrafnumero() + "'") 				+ "," + " ctrafplaca = " + (entity.getctrafplaca() == null ? "NULL" : "'" + entity.getctrafplaca() + "'") 				+ "," + " ctrafconductor = " + (entity.getCtrafconductor() == null ? "NULL" : "'" + entity.getCtrafconductor() + "'") 				+ "," + " ctrafcedula = " + (entity.getCtrafcedula() == null ? "NULL" : "'" + entity.getCtrafcedula() + "'") 				+ "," + " ctrafobservacion = " + (entity.getCtrafobservacion() == null ? "NULL" : "'" + entity.getCtrafobservacion() + "'") 				+ "," + " ctrafestado = " + (entity.getCtrafestado() == null ? "NULL" : "'" + entity.getCtrafestado() + "'") 				+ "," + " ctrafiniciodescargue = " + (entity.getctrafiniciodescargue() == null || (entity.getctrafiniciodescargue() != null && entity.getctrafiniciodescargue().equals("")) ? "NULL" : "'" + entity.getctrafiniciodescargue() + "'")				+ "," + " ctraffindescargue = " + (entity.getCtraffindescargue() == null || (entity.getCtraffindescargue() != null && entity.getCtraffindescargue().equals("")) ? "NULL" : "'" + entity.getCtraffindescargue() + "'")				+ " where ctrafcodsx = " + entity.getctrafcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean updatecontenedor_trafico(contenedor_traficoForm entity) throws SQLException {		String cad = " update contenedor_trafico set  " 				+ " ctrafnumtrafico = " + (entity.getctrafnumtrafico() == null ? "NULL" : "'" + entity.getctrafnumtrafico() + "'") 				+ "," + " ctraftamano = " + (entity.getctraftamano() == null ? "NULL" : "'" + entity.getctraftamano() + "'") 				+ "," + " ctrafnumero = " + (entity.getctrafnumero() == null ? "NULL" : "'" + entity.getctrafnumero() + "'") 				+ "," + " ctrafplaca = " + (entity.getCtrafplaca() == null ? "NULL" : "'" + entity.getCtrafplaca() + "'") 				+ "," + " ctrafconductor = " + (entity.getCtrafconductor() == null ? "NULL" : "'" + entity.getCtrafconductor() + "'") 				+ "," + " ctrafcedula = " + (entity.getCtrafcedula() == null ? "NULL" : "'" + entity.getCtrafcedula() + "'") 				+ "," + " ctrafobservacion = " + (entity.getCtrafobservacion() == null ? "NULL" : "'" + entity.getCtrafobservacion() + "'") 				+ "," + " ctrafestado = " + (entity.getCtrafestado() == null ? "NULL" : "'" + entity.getCtrafestado() + "'") 				+ "," + " ctrafiniciodescargue = " + (entity.getCtrafiniciodescargue() == null || (entity.getCtrafiniciodescargue() != null && entity.getCtrafiniciodescargue().equals("")) ? "NULL" : "'" + entity.getCtrafiniciodescargue() + "'")				+ "," + " ctraffindescargue = " + (entity.getCtraffindescargue() == null || (entity.getCtraffindescargue() != null && entity.getCtraffindescargue().equals("")) ? "NULL" : "'" + entity.getCtraffindescargue() + "'")				+ " where ctrafcodsx = " + entity.getctrafcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean eliminar(String ctrafcodsx) throws SQLException {		String elim = " delete from contenedor_trafico where ctrafcodsx = '" + ctrafcodsx + "'";		int r = db.executeUpdate(elim);		return r == 0 ? false : true;	}		public int get_num_vehiculos(String trafcodsx) throws SQLException {		String cad = "select coalesce(trafmotonave,'0') from trafico where trafcodsx = '" + trafcodsx + "'";		return getConteo(cad);	}		public Collection getlistaVehiculosDescargando() {		String consulta = " select * from contenedor_trafico \r" + 				"where ctrafestado != 'FINALIZADO' \r"				+ "order by 1 desc" ;		return getLista(consulta);	}	}