package maestro.control;import maestro.form.*;import maestro.entity.*;import db.*;import java.util.Collection;import java.sql.SQLException;/******************************************************** ***** CLASE GENERADA CON generator V3.0 By pablito *******/public class gstsucursal extends GstTabla {	public gstsucursal() {		db = new beanConnector();		this.classEntidad = sucursal.class;	}	public gstsucursal(beanConnector db) {		this.db = db;		this.classEntidad = sucursal.class;	}	public Collection getlistasucursal() {		String consulta = " SELECT * FROM sucursal";		return getLista(consulta);	}	public Collection getlistasucursal(String clicodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM sucursal where succodcliente=" + clicodsx + "  and " + getTextoBusquedaResumen(param) + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}	public int getTotalPaginaslistasucursal(String clicodsx, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM sucursal where succodcliente=" + clicodsx + "  and " + getTextoBusquedaResumen(param) + "  ";		return getPaginas(consulta);	}	public boolean crearsucursal(String succodcliente, String sucnit, String sucnombre, String succiudad, String sucdepartamento, String succontacto, String succargo, String sucemail, String sucdireccion, String suctelefono, String succodigo) throws SQLException {		String insert = "INSERT INTO sucursal (succodcliente,sucnit,sucnombre,succiudad,sucdepartamento,succontacto,succargo,sucemail,sucdireccion,suctelefono, succodigo) VALUES (" + (succodcliente == null ? "NULL" : "'" + succodcliente + "'") + "," + (sucnit == null ? "NULL" : "'" + sucnit + "'")				+ "," + (sucnombre == null ? "NULL" : "'" + sucnombre + "'") + "," + (succiudad == null ? "NULL" : "'" + succiudad + "'") + "," + (sucdepartamento == null ? "NULL" : "'" + sucdepartamento + "'") + "," + (succontacto == null ? "NULL" : "'" + succontacto + "'") + ","				+ (succargo == null ? "NULL" : "'" + succargo + "'") + "," + (sucemail == null ? "NULL" : "'" + sucemail + "'") + "," + (sucdireccion == null ? "NULL" : "'" + sucdireccion + "'") + "," + (suctelefono == null ? "NULL" : "'" + suctelefono + "'") + ","				+ (succodigo == null ? "NULL" : "'" + succodigo + "'")				+ ")";		int resp = db.executeUpdate(insert);		return resp == 0 ? false : true;	}	public sucursal getsucursal(String succodsx) {		String cad = "SELECT * FROM sucursal WHERE succodsx='" + succodsx + "'";		return (sucursal) getEntidad(cad);	}	public sucursal getsuccodcursal(String succodigo) {		String cad = "SELECT * FROM sucursal WHERE succodigo='" + succodigo + "' order by 1 desc limit 1";		return (sucursal) getEntidad(cad);	}	public boolean getclientesucursal(String codcliente, String succodigo) {				String cad = "SELECT * FROM sucursal WHERE succodsx="+succodigo+ " and succodcliente = '" + codcliente + "'" ;		return cad==null ? false:true;  	 }	public int getcountsucursal(String succodigo) {		String cad = "SELECT count(*) FROM sucursal WHERE succodigo='" + succodigo + "'";		return getConteo(cad);	}	public boolean updatesucursal(sucursal entity) throws SQLException {		String cad = " update sucursal set  " + " succodcliente = " + (entity.getsuccodcliente() == null ? "NULL" : "'" + entity.getsuccodcliente() + "'") + "," + " sucnit = " + (entity.getsucnit() == null ? "NULL" : "'" + entity.getsucnit() + "'") + "," + " sucnombre = "				+ (entity.getsucnombre() == null ? "NULL" : "'" + entity.getsucnombre() + "'") + "," + " succiudad = " + (entity.getsucciudad() == null ? "NULL" : "'" + entity.getsucciudad() + "'") + "," + " sucdepartamento = "				+ (entity.getsucdepartamento() == null ? "NULL" : "'" + entity.getsucdepartamento() + "'") + "," + " succontacto = " + (entity.getsuccontacto() == null ? "NULL" : "'" + entity.getsuccontacto() + "'") + "," + " succargo = "				+ (entity.getsuccargo() == null ? "NULL" : "'" + entity.getsuccargo() + "'") + "," + " sucemail = " + (entity.getsucemail() == null ? "NULL" : "'" + entity.getsucemail() + "'") + "," + " sucdireccion = "				+ (entity.getsucdireccion() == null ? "NULL" : "'" + entity.getsucdireccion() + "'") + "," + " suctelefono = " + (entity.getsuctelefono() == null ? "NULL" : "'" + entity.getsuctelefono() + "'") + "," + " succodigo = "				+ (entity.getSuccodigo() == null ? "NULL" : "'" + entity.getSuccodigo() + "'") + " where succodsx = " + entity.getsuccodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean updatesucursal(sucursalForm entity) throws SQLException {		String cad = " update sucursal set  " + " succodcliente = " + (entity.getsuccodcliente() == null ? "NULL" : "'" + entity.getsuccodcliente() + "'") + "," + " sucnit = " + (entity.getsucnit() == null ? "NULL" : "'" + entity.getsucnit() + "'") + "," + " sucnombre = "				+ (entity.getsucnombre() == null ? "NULL" : "'" + entity.getsucnombre() + "'") + "," + " succiudad = " + (entity.getsucciudad() == null ? "NULL" : "'" + entity.getsucciudad() + "'") + "," + " sucdepartamento = "				+ (entity.getsucdepartamento() == null ? "NULL" : "'" + entity.getsucdepartamento() + "'") + "," + " succontacto = " + (entity.getsuccontacto() == null ? "NULL" : "'" + entity.getsuccontacto() + "'") + "," + " succargo = "				+ (entity.getsuccargo() == null ? "NULL" : "'" + entity.getsuccargo() + "'") + "," + " sucemail = " + (entity.getsucemail() == null ? "NULL" : "'" + entity.getsucemail() + "'") + "," + " sucdireccion = "				+ (entity.getsucdireccion() == null ? "NULL" : "'" + entity.getsucdireccion() + "'") + "," + " suctelefono = " + (entity.getsuctelefono() == null ? "NULL" : "'" + entity.getsuctelefono() + "'") + "," + " succodigo = "				+ (entity.getSuccodigo() == null ? "NULL" : "'" + entity.getSuccodigo() + "'") + " where succodsx = " + entity.getsuccodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean eliminar(String succodsx) throws SQLException {		String elim = " delete from sucursal where succodsx = '" + succodsx + "'";		int r = db.executeUpdate(elim);		return r == 0 ? false : true;	}	public Collection getlistasucursalByCliente(String cliente, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " select succodsx, succodcliente, sucnit, sucnombre, succiudad, sucdepartamento, " + " ciunombre as succontacto, depnombre as succargo, sucemail, sucdireccion, suctelefono , '' as resumen , succodigo from sucursal, departamento , ciudad where sucdepartamento = depcodigo "				+ " and succiudad = ciucodigo and  succodcliente = " + cliente + " AND (" + " sucnit like '%" + param + "%' " + " or " + " sucnombre like '%" + param + "%' or sucdireccion like '%" + param + "%'  or succodigo like '%" + param + "%'" + " or "				+ this.getTextoBusquedaResumen("sucursal", param) + " )" +				"  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);				//'succodsx';'succodcliente';'sucnit';'sucnombre';'succiudad';'sucdepartamento';'succontacto';'succargo';'sucemail';'sucdireccion';'suctelefono';'resumen';'succodigo'	}	public int getTotalPaginaslistasucursalByCliente(String cliente, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM sucursal where succodcliente = " + cliente + " AND (" + " sucnit like '%" + param + "%' " + " or " + " sucnombre like '%" + param + "%' )";		return getPaginas(consulta);	}}