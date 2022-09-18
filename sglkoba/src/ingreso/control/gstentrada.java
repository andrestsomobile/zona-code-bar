package ingreso.control;import ingreso.entity.entrada;import ingreso.form.entradaForm;import java.sql.ResultSet;import java.sql.SQLException;import java.util.Collection;import java.util.Vector;import maestro.control.gstcompania;import maestro.control.gstproducto;import maestro.control.gsttipoproducto;import util.Listado;import db.GstTabla;import db.beanConnector;/*********************************************************************************************************************************************************************************************************************************************************************************************************** * CLASE GENERADA CON generator V3.0 By pablito**** **********************************************************************************************************************************************************************************************************************************************************************************************************/public class gstentrada extends GstTabla {	public gstentrada() {		db = new beanConnector();		this.classEntidad = entrada.class;	}	public gstentrada(beanConnector db) {		this.db = db;		this.classEntidad = entrada.class;	}	public Collection getlistaentrada() {		String consulta = " SELECT * FROM entrada";		return getLista(consulta);	}	public Collection getlistaentrada(String entcodproducto) {		String consulta = " SELECT * FROM entrada where entcodproducto = " + entcodproducto + "  and (entsaldonac >0 or entsaldosinnac >0 )";		return getLista(consulta);	}	public Collection getListaPosicionesSaldo(String procodsx, String tipo, String bodega) {		String cad = "";		if (tipo.equals("NAC"))			cad = "select entposicion from entrada where entbodega =" + bodega + " and  entcodproducto =" + procodsx + " and entsaldonac >0 group by entposicion";		if (tipo.equals("SNAC"))			cad = "select entposicion from entrada where  entbodega =" + bodega + " and entcodproducto =" + procodsx + " and entsaldosinnac >0 group by entposicion";		if (tipo.equals("TODO"))			cad = "select entposicion from entrada where entbodega =" + bodega + " and entcodproducto =" + procodsx + " and ( entsaldonac + entsaldosinnac) >0  group by entposicion";		Vector resp = new Vector();		try {			ResultSet rs = db.executeQuery(cad);			while (rs.next())				resp.add(rs.getString(1));		} catch (SQLException ex) {		}		return resp;	}	public String getSaldo(String procodsx, String tipo) {		String cad = "";		if (tipo.equals("NAC"))			cad = "select sum(entsaldonac) from entrada where entcodproducto =" + procodsx;		if (tipo.equals("SNAC"))			cad = "select sum(entsaldosinnac) from entrada where entcodproducto =" + procodsx;		if (tipo.equals("TODO"))			cad = "select (sum(entsaldonac) + sum(entsaldosinnac) ) from entrada where entcodproducto =" + procodsx;		String resp = getCampo(cad);		return resp == null ? "0.00" : resp;	}	public String getSaldoFisico(String procodsx, String tipo) {		String cad = "";		if (tipo.equals("NAC"))			cad = "select sum(entsaldonacf) from entrada where entcodproducto =" + procodsx;		if (tipo.equals("SNAC"))			cad = "select sum(entsaldosinnacf) from entrada where entcodproducto =" + procodsx;		if (tipo.equals("TODO"))			cad = "select (sum(entsaldonacf) + sum(entsaldosinnacf) ) from entrada where entcodproducto =" + procodsx;		String resp = getCampo(cad);		return resp == null ? "0.00" : resp;	}	public Collection getlistaentrada(String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM entrada where " + getTextoBusquedaResumen(param) + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}	public int getTotalPaginaslistaentrada(String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM entrada where  " + getTextoBusquedaResumen(param);		return getPaginas(consulta);	}	public Collection getlistaentrada(String entcodingreso, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM entrada where " + " entcodingreso =" + entcodingreso + " and " + getTextoBusquedaResumen(param) + " order by entcodproducto asc, entcodsx asc" + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}	public int getTotalPaginaslistaentrada(String entcodingreso, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM entrada where " + " entcodingreso =" + entcodingreso + " and " + getTextoBusquedaResumen(param);		return getPaginas(consulta);	}	public boolean crearentrada_original(String entcodingreso, String entcodproducto, String entbodega, String entposicion, String entcantidad, String entpesoneto, String entpesonetototal, String entpesobruto, String entpesobrutototal, String entsaldopesoneto, String entsaldopesobruto, String entsaldonac,			String entsaldosinnac, String entvalor, String entvalortotal, String entsaldonacf, String entsaldosinnacf, String entunidad, String entlote) throws SQLException {						gsttipoproducto gtipr = new gsttipoproducto();		gstingreso ging = new gstingreso();		gstcompania gcom = new gstcompania();		gstproducto gpro = new gstproducto();		String codigo = gtipr.gettipoproducto(gpro.getproducto(entcodproducto).getprotipoproducto()).gettiprcodigo();				String mapa = gcom.getcompania(ging.getingreso(entcodingreso).getingcodcia()).getCommapa();				if (mapa.equals("N")) {			entposicion = codigo+entposicion;		}				String insert = "INSERT INTO entrada (entcodingreso,entcodproducto,entbodega, entposicion,entcantidad,entpesoneto,entpesonetototal, " + " entpesobruto, entpesobrutototal, entsaldopesoneto,entsaldopesobruto,entsaldonac,"				+ "entsaldosinnac, entvalor, entvalortotal, entsaldonacf, entsaldosinnacf, entunidad, entlote) VALUES ("				+ (entcodingreso == null ? "NULL" : "'" + entcodingreso + "'")				+ ","				+ (entcodproducto == null ? "NULL" : "'" + entcodproducto + "'")				+ ","				+ (entbodega == null ? "NULL" : "'" + entbodega + "'")				+ ","				+ (entposicion == null ? "NULL" : "'" + entposicion + "'")				+ ","				+ (entcantidad == null ? "NULL" : "'" + entcantidad + "'")				+ ","				+ (entpesoneto == null ? "NULL" : "'" + entpesoneto + "'")				+ ","				+ (entpesonetototal == null ? "NULL" : "'" + entpesonetototal + "'")				+ ","				+ (entpesobruto == null ? "NULL" : "'" + entpesobruto + "'")				+ ","				+ (entpesobrutototal == null ? "NULL" : "'" + entpesobrutototal + "'")				+ ","				+ (entsaldopesoneto == null ? "NULL" : "'" + entsaldopesoneto + "'")				+ ","				+ (entsaldopesobruto == null ? "NULL" : "'" + entsaldopesobruto + "'")				+ ","				+ (entsaldonac == null ? "NULL" : "'" + entsaldonac + "'")				+ ","				+ (entsaldosinnac == null ? "NULL" : "'" + entsaldosinnac + "'")				+ ","				+ (entvalor == null ? "NULL" : "'" + entvalor + "'")				+ ","				+ (entvalortotal == null ? "NULL" : "'" + entvalortotal + "'")				+ ","				+ (entsaldonacf == null ? "NULL" : "'" + entsaldonacf + "'") 				+ "," 				+ (entsaldosinnacf == null ? "NULL" : "'" + entsaldosinnacf + "'") 				+ "," 				+ (entunidad == null ? "NULL" : "'" + entunidad + "'")				+ "," 				+ (entlote == null ? "NULL" : "'" + entlote + "'")				+ ")";				int resp = db.executeUpdate(insert);				return resp == 0 ? false : true;	}	public boolean crearentrada(String entcodingreso, String entcodproducto, String entbodega, String entposicion, String entcantidad, String entpesoneto, String entpesonetototal, String entpesobruto, String entpesobrutototal, String entsaldopesoneto, String entsaldopesobruto, String entsaldonac,			String entsaldosinnac, String entvalor, String entvalortotal, String entsaldonacf, String entsaldosinnacf, String entunidad, String entlote) throws SQLException {						// se reemplaza insert por funcion que divide la cantidad segun las unidades x estiba que se define en el producto.				String insert = "SELECT * FROM movimiento.entrada_x_estiba ("				+ (entcodingreso == null ? "NULL" : "" + entcodingreso + "")				+ ","				+ (entcodproducto == null ? "NULL" : "" + entcodproducto + "")				+ ","				+ (entbodega == null ? "NULL" : "" + entbodega + "") 				+ ","				+ (entposicion == null ? "NULL" : "'" + entposicion + "'") + "::character varying"				+ ","				+ (entcantidad == null ? "NULL" : "" + entcantidad + "")				+ ","				+ (entpesoneto == null ? "NULL" : "" + entpesoneto + "")				+ ","				+ (entpesonetototal == null ? "NULL" : "" + entpesonetototal + "")				+ ","				+ (entpesobruto == null ? "NULL" : "" + entpesobruto + "")				+ ","				+ (entpesobrutototal == null ? "NULL" : "" + entpesobrutototal + "")				+ ","				+ (entsaldopesoneto == null ? "NULL" : "" + entsaldopesoneto + "")				+ ","				+ (entsaldopesobruto == null ? "NULL" : "" + entsaldopesobruto + "")				+ ","				+ (entsaldonac == null ? "0" : "0")				+ ","				+ (entsaldosinnac == null ? "0" : "0")				+ ","				+ (entvalor == null ? "NULL" : "" + entvalor + "")				+ ","				+ (entvalortotal == null ? "NULL" : "" + entvalortotal + "")				+ ","				+ (entsaldonacf == null ? "0" : "0") 				+ "," 				+ (entsaldosinnacf == null ? "0" : "0") 				+ "," 				+ (entunidad == null ? "NULL" : "'" + entunidad + "'") + "::character varying"				+ "," 				+ (entlote == null ? "NULL" : "" + entlote + "")+ "::integer"				+ ")";		getCampo(insert);		return true;	}	public entrada getentrada(String entcodsx) {		String cad = "SELECT * FROM entrada WHERE entcodsx='" + entcodsx + "'";		return (entrada) getEntidad(cad);	}		public entrada getEntradaByUbicacion(String producto, String bodega, String posicion) {		String cad = "select e.* from entrada e, producto p, bodega b "				+ "where e.entcodproducto = p.procodsx "				+ "and p.procodigobarra = '" + producto + "'"				+ "and e.entbodega = b.bodcodsx "				+ "and b.bodnombre = '" + bodega + "'"				+ "and e.entposicion = '" + posicion + "'";		return (entrada) getEntidad(cad);	}	public boolean updateentrada(entrada entity) throws SQLException {		String cad = " update entrada set  " + " entcodingreso = " + (entity.getentcodingreso() == null ? "NULL" : "'" + entity.getentcodingreso() + "'") + "," + " entcodproducto = " + (entity.getentcodproducto() == null ? "NULL" : "'" + entity.getentcodproducto() + "'") + "," + " entbodega= "				+ (entity.getEntbodega() == null ? "NULL" : "'" + entity.getEntbodega() + "'") + "," + " entposicion = " + (entity.getentposicion() == null ? "NULL" : "'" + entity.getentposicion() + "'") + "," + " entcantidad = "				+ (entity.getentcantidad() == null ? "NULL" : "'" + entity.getentcantidad() + "'") + "," + " entpesoneto = " + (entity.getentpesoneto() == null ? "NULL" : "'" + entity.getentpesoneto() + "'") + "," + " entpesonetototal = "				+ (entity.getEntpesonetototal() == null ? "NULL" : "'" + entity.getEntpesonetototal() + "'") + "," + " entpesobruto = " + (entity.getentpesobruto() == null ? "NULL" : "'" + entity.getentpesobruto() + "'") + "," + " entpesobrutototal = "				+ (entity.getEntpesobrutototal() == null ? "NULL" : "'" + entity.getEntpesobrutototal() + "'") + "," + " entsaldopesoneto = " + (entity.getentsaldopesoneto() == null ? "NULL" : "'" + entity.getentsaldopesoneto() + "'") + "," + " entsaldopesobruto = "				+ (entity.getentsaldopesobruto() == null ? "NULL" : "'" + entity.getentsaldopesobruto() + "'") + "," + " entsaldonac = " + (entity.getentsaldonac() == null ? "NULL" : "'" + entity.getentsaldonac() + "'") + "," + " entsaldosinnac = "				+ (entity.getentsaldosinnac() == null ? "NULL" : "'" + entity.getentsaldosinnac() + "'") + "," + " entvalor = " + (entity.getEntvalor() == null ? "NULL" : "'" + entity.getEntvalor() + "'") + "," + " entvalortotal = "				+ (entity.getEntvalortotal() == null ? "NULL" : "'" + entity.getEntvalortotal() + "'") + "," + " entsaldonacf = " + (entity.getEntsaldonacf() == null ? "NULL" : "'" + entity.getEntsaldonacf() + "'") + "," + " entsaldosinnacf = "				+ (entity.getEntsaldosinnacf() == null ? "NULL" : "'" + entity.getEntsaldosinnacf() + "'") + "," + " entunidad= " + (entity.getEntunidad() == null ? "NULL" : "'" + entity.getEntunidad() + "'") 				+ "," + " entlote = " + (entity.getentlote() == null ? "NULL" : "'" + entity.getentlote() + "'") 				+ " where entcodsx = " + entity.getentcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean updateentrada(entradaForm entity) throws SQLException {		String cad = " update entrada set  " + " entcodingreso = " + (entity.getentcodingreso() == null ? "NULL" : "'" + entity.getentcodingreso() + "'") + "," + " entcodproducto = " + (entity.getentcodproducto() == null ? "NULL" : "'" + entity.getentcodproducto() + "'") + "," + " entbodega = "				+ (entity.getEntbodega() == null ? "NULL" : "'" + entity.getEntbodega() + "'") + "," + " entposicion = " + (entity.getentposicion() == null ? "NULL" : "'" + entity.getentposicion() + "'") + "," + " entcantidad = "				+ (entity.getentcantidad() == null ? "NULL" : "'" + entity.getentcantidad() + "'") + "," + " entpesoneto = " + (entity.getentpesoneto() == null ? "NULL" : "'" + entity.getentpesoneto() + "'") + "," + " entpesonetototal = "				+ (entity.getEntpesonetototal() == null ? "NULL" : "'" + entity.getEntpesonetototal() + "'") + "," + " entpesobruto = " + (entity.getentpesobruto() == null ? "NULL" : "'" + entity.getentpesobruto() + "'") + "," + " entpesobrutototal = "				+ (entity.getEntpesobrutototal() == null ? "NULL" : "'" + entity.getEntpesobrutototal() + "'") + "," + " entsaldopesoneto = " + (entity.getentsaldopesoneto() == null ? "NULL" : "'" + entity.getentsaldopesoneto() + "'") + "," + " entsaldopesobruto = "				+ (entity.getentsaldopesobruto() == null ? "NULL" : "'" + entity.getentsaldopesobruto() + "'") + "," + " entsaldonac = " + (entity.getentsaldonac() == null ? "NULL" : "'" + entity.getentsaldonac() + "'") + "," + " entsaldosinnac = "				+ (entity.getentsaldosinnac() == null ? "NULL" : "'" + entity.getentsaldosinnac() + "'") + "," + " entvalor = " + (entity.getEntvalor() == null ? "NULL" : "'" + entity.getEntvalor() + "'") + "," + " entvalortotal = "				+ (entity.getEntvalortotal() == null ? "NULL" : "'" + entity.getEntvalortotal() + "'") + "," + " entsaldonacf = " + (entity.getEntsaldonacf() == null ? "NULL" : "'" + entity.getEntsaldonacf() + "'") + "," + " entsaldosinnacf = "				+ (entity.getEntsaldosinnacf() == null ? "NULL" : "'" + entity.getEntsaldosinnacf() + "'") + "," + " entunidad= " + (entity.getEntunidad() == null ? "NULL" : "'" + entity.getEntunidad() + "'"			    + "," + " entlote = " + (entity.getentlote() == null ? "NULL" : "'" + entity.getentlote() + "'") 				) + " where entcodsx = " + entity.getentcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean eliminar(String entcodsx) throws SQLException {		String elim = " delete from entrada where entcodsx = '" + entcodsx + "'";		int r = db.executeUpdate(elim);		return r == 0 ? false : true;	}	public Collection getlistaentradaByingreso_sinnac(String ingcodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String cad = "select entcodsx,  promodelo as entcodingreso ,prodescripcion as entcodproducto , entbodega , " + " entposicion, entcantidad, entpesoneto, " + " entpesonetototal, entpesobruto, entpesobrutototal, entsaldopesoneto,  "				+ " entsaldopesobruto, entsaldonac, entsaldosinnac, entvalor, entvalortotal " + ", entrada.resumen, entfecha, entsaldonacf, entsaldosinnacf, entunidad, entlote " + " from producto, entrada where entcodproducto = procodsx and entcodingreso = " + ingcodsx				+ " and entsaldosinnac >0 and (promodelo like '%" + param + "%' or prodescripcion like '%" + param + "%')" + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(cad);	}	public int getTotalPaginaslistaentradaByingreso_sinnac(String ingcodsx, String param) {		param = param == null ? "" : param;		String cad = "select count(1)  " + " from producto, entrada where entcodproducto = procodsx and entcodingreso = " + ingcodsx + " and entsaldosinnac >0 and (promodelo like '%" + param + "%' or prodescripcion like '%" + param + "%')";		return getPaginas(cad);	}	public Collection getlistaentradaByingreso_sinnac(String ingcodsx) {		String cad = "select entrada.* " + " from entrada where entcodingreso = " + ingcodsx + " and entsaldosinnac >0 ";		return getLista(cad);	}	public Collection getlistaentradaByingreso_sinnac(String ingcodsx, String procodsx) {		//String cad = "select entrada.* " + " from producto, entrada where entcodproducto = procodsx and entcodingreso = " + ingcodsx + " and entsaldosinnac >0 and entcodproducto = " + procodsx + " order by entlote";		String cad = "select entrada.* " 					+ " from entrada "					+ " inner join producto on procodsx = entcodproducto and entcodproducto = " + procodsx					+ " left join lote_trafico on ltrafcodsx = entlote "					+ " where entcodingreso = " + ingcodsx 					+ " and entsaldosinnac >0 "					+ " order by ltrafvencimiento, entfecha";		return getLista(cad);	}	public Collection getlistaentradaByingreso_tipo(String tipo, String ingcodcia, String ingcodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String cad = " select entcodsx, promodelo as entcodingreso, prodescripcion as entcodproducto, bodnombre as entbodega, " + " entposicion,procodsx as  entcantidad, entpesoneto, entpesonetototal,  " + " entpesobruto, entpesobrutototal, entsaldopesoneto, entsaldopesobruto, 0 as entsaldonac, "				+ (tipo.equals("NACIONALIZADO") ? " entsaldonac" : "entsaldosinnac ") + " , '','','','','','','',''   " + " from ingreso, entrada, producto, bodega where ingcodsx = entcodingreso and entcodproducto = procodsx " + " and entbodega=bodcodsx and ingcodcia =" + ingcodcia + " and ingcodsx="				+ ingcodsx + "  and " + (tipo.equals("NACIONALIZADO") ? " entsaldonac >0 " : " entsaldosinnac >0") + "and " + getTextoBusquedaResumen("entrada", param) + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(cad);	}	public int getTotalPaginaslistaentradaByingreso_tipo(String tipo, String ingcodcia, String ingcodsx, String param) {		param = param == null ? "" : param;		String cad = " select count(1) " + " from ingreso, entrada, producto where ingcodsx = entcodingreso and entcodproducto = procodsx " + " and ingcodcia =" + ingcodcia + " and ingcodsx=" + ingcodsx + "  and " + (tipo.equals("NACIONALIZADO") ? " entsaldonac >0 " : " entsaldosinnac >0")				+ " and " + getTextoBusquedaResumen("entrada", param);		return getPaginas(cad);	}		public Collection getlistaentradaByTipo(String tipo, String ingcodcia, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String cad = " select entcodsx, promodelo as entcodingreso, prodescripcion as entcodproducto, bodnombre as entbodega, " 				+ " entposicion,procodsx as  entcantidad, entcodingreso as entpesoneto, entpesonetototal,  " + " entpesobruto, entpesobrutototal, entsaldopesoneto, entsaldopesobruto, 0 as entsaldonac, "				+ (tipo.equals("NACIONALIZADO") ? " entsaldonac" : "entsaldosinnac ") + " , '','','','','','','',''   " 				+ " from ingreso, entrada, producto, bodega where ingcodsx = entcodingreso and entcodproducto = procodsx " 				+ " and entbodega=bodcodsx and ingcodcia =" + ingcodcia 				+ " and " + (tipo.equals("NACIONALIZADO") ? " entsaldonac >0 " : " entsaldosinnac >0") + " and "				+ getTextoBusquedaResumen("entrada", param) 				+ " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(cad);	}	public int getTotalPaginaslistaentradaByTipo(String tipo, String ingcodcia, String param) {		param = param == null ? "" : param;		String cad = " select count(1) " + " from ingreso, entrada, producto where ingcodsx = entcodingreso and entcodproducto = procodsx " 		+ " and ingcodcia =" + ingcodcia + " and " + (tipo.equals("NACIONALIZADO") ? " entsaldonac >0 " : " entsaldosinnac >0")				+ " and " + getTextoBusquedaResumen("entrada", param);		return getPaginas(cad);	}		public entrada getentrada(String entcodingreso, String entcodproducto, String entbodega, String entposicion) {		String cad = "SELECT * FROM entrada WHERE entcodingreso=" + entcodingreso + " and entcodproducto =" + entcodproducto + " and entbodega=" + entbodega + " and entposicion= '" + entposicion + "'";		return (entrada) getEntidad(cad);	}	public Collection getlistaentradaFIFO(String codproducto, String tipo) {		String consulta = " SELECT * FROM lista_entradas_fifo(" + codproducto + ", '" + tipo + "')";		return getLista(consulta);	}		public Collection getlistaentradaManualold(String entcodsx) {		String consulta = " SELECT * FROM entrada WHERE entcodsx=" + entcodsx;		return getLista(consulta);	}		public Collection getlistaentradaManual(String entcodsx, String tipo) {		String consulta = " SELECT * FROM lista_entradas_manual(" + entcodsx + ", '" + tipo + "')";		return getLista(consulta);	}	public Collection getlistaentradaByingreso(String ingcodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String cad = " select entcodsx, entcodingreso, promodelo || '-' || prodescripcion , bodnombre, entposicion, " + " entcantidad, entpesoneto, entpesonetototal, " + " entpesobruto, entpesobrutototal, entsaldopesoneto, entsaldopesobruto, entsaldonac, entsaldosinnac,"				+ " entvalor, entvalortotal, entrada.resumen, entfecha , entsaldonacf, entsaldosinnacf , entunidad , entlote " + " from entrada, producto, bodega " + " where entcodproducto = procodsx and bodcodsx = entbodega " + " and entcodingreso=" + ingcodsx + " and "				+ getTextoBusquedaResumen("entrada", param) + " order by promodelo asc " + " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(cad);	}	public int getTotalPaginaslistaentradaByingreso(String ingcodsx, String param) {		param = param == null ? "" : param;		String cad = " select count(1) " + " from entrada where entcodingreso =" + ingcodsx + " and " + getTextoBusquedaResumen("entrada", param);		return getPaginas(cad);	}	public boolean tienePedidos(String entcodsx) {		String cad = "select count(1) from referencia_pedido where refpentrada = " + entcodsx;		int x = getConteo(cad);		return x != 0 ? true : false;	}	public boolean tienePackings(String entcodsx) {		String cad = "select count(1) from packing_detalle_posicion where packpentrada=" + entcodsx;		int x = getConteo(cad);		return x != 0 ? true : false;	}	public Collection original_getlistaproductoConSaldoByIngreso(String codcia, String ingreso, String tiposaldo, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param.toUpperCase();		String consulta = "select procodsx, promodelo, prodescripcion, " + " sum(" + (tiposaldo.equals("NAC") ? "entsaldonac" : "entsaldosinnac") + ") , " + ingreso + " , '','' " + " from producto, entrada" + " where entcodproducto = procodsx and entcodingreso= " + ingreso + "  and "				+ " procodigocia = " + codcia + " and (prodescripcion like '%" + param + "%' or promodelo like '%" + param + "%')" + " group by " + " procodsx, procodigocia, prodescripcion, procategoria, promodelo, prosubpartida, proean, procodbarra, progarantia,"				+ " prokiloreal, provolumen, prokilovolumen, prouniempaque, prokvuniempaque, prouniempaque, prounimasterpac," + " prokvmasterpack  " + " HAVING  sum(" + (tiposaldo.equals("NAC") ? "entsaldonac" : "entsaldosinnac") + ") >0 " + " limit " + this.maxfilas + " offset  "				+ (Integer.parseInt(start) * this.maxfilas);		this.classEntidad = Listado.class;		Collection lista = getLista(consulta);		this.classEntidad = entrada.class;		return lista;	}		public Collection getlistaproductoConSaldoByIngreso(String codcia, String ingreso, String tiposaldo, String naccodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param.toUpperCase();		String consulta = "select procodsx, promodelo, prodescripcion, " + " sum(" + (tiposaldo.equals("NAC") ? "entsaldonac" : "entsaldosinnac") + ") , " + ingreso + " ,  sum(nadcantidad),'' " 		+ " from  entrada " 		+ " inner join producto on procodsx = entcodproducto and  procodigocia = " + codcia + " and (prodescripcion like '%" + param + "%' or promodelo like '%" + param + "%')"		+ " left join nacionalizacion_detalle on nadentrada  = entcodsx and nadcodnac = " + naccodsx  		+ " where entcodingreso= " + ingreso 		+ " group by procodsx, promodelo, prodescripcion "		+ " HAVING  sum(" + (tiposaldo.equals("NAC") ? "entsaldonac" : "entsaldosinnac") + ") >0 " + " limit " + this.maxfilas + " offset  "		+ (Integer.parseInt(start) * this.maxfilas);		this.classEntidad = Listado.class;		Collection lista = getLista(consulta);		this.classEntidad = entrada.class;		return lista;	}	public int getTotalPaginaslistaproductoConSaldoByIngreso(String codcia, String ingreso, String tiposaldo, String param) {		param = param == null ? "" : param.toUpperCase();		String consulta = "select count( distinct entcodproducto)  from producto, entrada " + " where entcodproducto = procodsx and entcodingreso=" + ingreso + " and " + " procodigocia = " + codcia + " and (prodescripcion like '%" + param + "%' or promodelo like '%" + param + "%') " + " and  "				+ (tiposaldo.equals("NAC") ? "entsaldonac" : "entsaldosinnac") + " >0 ";		return getPaginas(consulta);	}		public Collection getlistaentradaByingresoConSaldoNac(String ingcodsx) {		String cad = " select entcodsx, entcodingreso, promodelo || '-' || prodescripcion , bodnombre, entposicion, " + " entcantidad, entpesoneto, entpesonetototal, " + " entpesobruto, entpesobrutototal, entsaldopesoneto, entsaldopesobruto, entsaldonac, entsaldosinnac,"				+ " entvalor, entvalortotal, entrada.resumen, entfecha , entsaldonacf, entsaldosinnacf , entunidad, entlote " + " from entrada, producto, bodega " + " where entcodproducto = procodsx and bodcodsx = entbodega " + " and entcodingreso=" + ingcodsx + " and entsaldonac > 0 "				+ " order by promodelo asc ";		return getLista(cad);	}	public String getSumaAveriada(String entcodsx) {		String cad = "select coalesce(sum(avecant_pleg_nac + avecant_pleg_nnac + avecant_prod_nac + avecant_prod_nnac),0) as suma from averia where aveentrada = '" + entcodsx + "'";		return getCampo(cad);	}		public String getRotuloEstiba(String ingcodsx,String entcodsx,String procodsx) {		String cad = "select obtiene_rotulo_estiba(" + ingcodsx + "," + entcodsx + "," + procodsx + ") as rotulo";		return getCampo(cad);	}		public Collection getlistaentradaByProducto(String procodsx, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String cad = " select entcodsx, entcodingreso, promodelo || '-' || prodescripcion , bodnombre, entposicion, " + " entcantidad, entpesoneto, entpesonetototal, " + " entpesobruto, entpesobrutototal, entsaldopesoneto, entsaldopesobruto, entsaldonac, entsaldosinnac,"				+ " entvalor, entvalortotal, entrada.resumen, entfecha , entsaldonacf, entsaldosinnacf , entunidad , entlote " + " from entrada, producto, bodega " + " where entcodproducto = procodsx and bodcodsx = entbodega " + " and entcodproducto=" + procodsx + " and "				+ getTextoBusquedaResumen("entrada", param) + " order by promodelo asc " + " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(cad);	}		public int getTotalPaginaslistaentradaByProducto(String procodsx, String param) {		param = param == null ? "" : param;		String cad = " select count(1) " + " from entrada where entcodproducto =" + procodsx + " and " + getTextoBusquedaResumen("entrada", param);		return getPaginas(cad);	}		public boolean updatePosicion(String entcodsx, String entposicion, String entbodega) throws SQLException {		String cad = " update entrada set  " 				+ " entposicion = " + (entposicion == null ? "NULL" : "'" + entposicion + "'") 				+ " , entbodega = " + (entbodega == null ? "NULL" : "'" + entbodega + "'") 				+ " where entcodsx = " + entcodsx;		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}		public Collection getlistaentrada_sinpos(String entcodingreso, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM entrada where " + " entcodingreso =" + entcodingreso						+ " and (entposicion is null or entposicion='') "						+ " and " + getTextoBusquedaResumen(param) 						+ " order by entcodproducto asc, entcodsx asc" 						+ " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}	public int getTotalPaginaslistaentrada_sinpos(String entcodingreso, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM entrada where " + " entcodingreso =" + entcodingreso 				+ " and (entposicion is null or entposicion='') "				+ " and " + getTextoBusquedaResumen(param);		return getPaginas(consulta);	}		public Collection getlistaentrada_cambiolote(String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT entrada.* FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " where entsaldonac+entsaldosinnac > 0" 						+ " and (entposicion like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')"						+ " order by entposicion" 						+ " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}		public int getTotallistaentrada_cambiolote(String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " where entsaldonac+entsaldosinnac > 0" 						+ " and (entposicion like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')";		return getPaginas(consulta);	}		public Collection getlistaentrada_posicion_movil(String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT entrada.* FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " inner join trafico on trafcodsx = ltrafnumtrafico "				        + " where ( (entsaldonac > 0 and entsaldonac = prounimasterpac) or (entsaldosinnac > 0 and entsaldosinnac = prounimasterpac) )" 						+ " and (entposicion like '%" + param + "%' or trafnumdta like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')"						+ " order by entposicion" 						+ " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}		public int getTotallistaentrada_posicion_movil(String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " inner join trafico on trafcodsx = ltrafnumtrafico "				        + " where ( (entsaldonac > 0 and entsaldonac = prounimasterpac) or (entsaldosinnac > 0 and entsaldosinnac = prounimasterpac) )" 				        + " and (entposicion like '%" + param + "%' or trafnumdta like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')"				        ;		return getPaginas(consulta);	}		public Collection getlistaentrada_reubicacion_movil(String start, String param, String tipo) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		tipo = tipo == null ? "NACIONALIZADO" : tipo;		String saldo = tipo != null && tipo.equals("NACIONALIZADO") ? "entsaldonac" : "entsaldosinnac";		String consulta = " SELECT entrada.* FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " inner join trafico on trafcodsx = ltrafnumtrafico "				        + " where " + saldo + " > 0" 				        //+ " and " + saldo + " != prounimasterpac"						+ " and (entposicion like '%" + param + "%' or trafnumdta like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')"						+ " order by entposicion" 						+ " limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}		public int getTotallistaentrada_reubicacion_movil(String param, String tipo) {		param = param == null ? "" : param;		tipo = tipo == null ? "NACIONALIZADO" : tipo;		String saldo = tipo != null && tipo.equals("NACIONALIZADO") ? "entsaldonac" : "entsaldosinnac";		String consulta = " SELECT count(1) FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " inner join trafico on trafcodsx = ltrafnumtrafico "				        + " where " + saldo + " > 0" 				        //+ " and " + saldo + " != prounimasterpac"				        + " and (entposicion like '%" + param + "%' or trafnumdta like '%" + param + "%' or ltraflote like '%" + param + "%' or promodelo like '%" + param + "%')"				        ;		return getPaginas(consulta);	}			public Collection getIdentificacionEstibas (String ingcodsx) {		String consulta = " SELECT "				        + " promodelo "				        + " , prodescripcion "				        + " , ltraflote "				        + " , to_char(ingfecha,'yyyy-mm-dd') as ingfecha"				        + " , coalesce(to_char(ltrafvencimiento,'yyyy-mm-dd'),'SIN DEFINIR') as ltrafvencimiento"				        + " , round(prounimasterpac) as prounimasterpac "				        + " , trafnumdta "				        + " , entcodsx "				        + " FROM entrada "				        + " inner join producto on procodsx = entcodproducto "				        + " inner join ingreso on ingcodsx = entcodingreso "				        + " inner join lote_trafico on ltrafcodsx = entlote "				        + " inner join trafico on trafcodsx = ltrafnumtrafico "				        + " where entcodingreso = " + ingcodsx 				        + " order by promodelo";		return getListaDeListas(consulta);	}		public Collection getIdentificacionEstibas_x_lote (String lctrafcodsx) {		String consulta = " SELECT \r" + 				"promodelo  , prodescripcion  , ltraflote  \r" + 				", to_char(ctrafiniciodescargue,'yyyy-mm-dd') as ctrafiniciodescargue \r" + 				", coalesce(to_char(ltrafvencimiento,'yyyy-mm-dd'),'SIN DEFINIR') as ltrafvencimiento \r" + 				", round(prounimasterpac) as prounimasterpac  \r" + 				", trafnumdta  \r" + 				", lctrafcodsx  \r" + 				", ceil(lctrafcantidad / prounimasterpac) as etk\r" + 				"FROM lote_contenedor_trafico  \r" + 				"INNER JOIN contenedor_trafico ON ctrafcodsx = lctrafcontenedor\r" + 				"INNER JOIN lote_trafico ON ltrafcodsx = lctraflote\r" + 				"inner join trafico on trafcodsx = ltrafnumtrafico  \r" + 				"inner join producto on procodsx = ltrafcodproducto\r" + 				"WHERE lctrafcodsx = " + lctrafcodsx;		return getListaDeListas(consulta);	}		public Collection getlistaentrada_Bodega(String codproducto, String tipo, String bodega) {		String consulta = " SELECT * FROM lista_entradas_bodega(" + codproducto + ", '" + tipo + "', " + bodega + ")";		return getLista(consulta);	}		public String getCantIngresadaTrafico(String trafcodsx,String entcodproducto) {		String consulta = "select sum(entcantidad) \r" + 				"from entrada\r" + 				"inner join ingreso on ingcodsx = entcodingreso and ingtrafico = " + trafcodsx + "\r" + 				"where entcodproducto = " +  entcodproducto + "\r";		return getCampo(consulta);	}	}