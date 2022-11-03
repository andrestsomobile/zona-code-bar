package pedido.control;import pedido.form.*;import pedido.entity.*;import db.*;import ingreso.entity.referencia_trafico;import java.util.Collection;import java.sql.SQLException;/******************************************************** ***** CLASE GENERADA CON generator V3.0 By pablito *******/public class gstreferencia_pedido extends GstTabla {	public gstreferencia_pedido() {		db = new beanConnector();		this.classEntidad = referencia_pedido.class;	}	public gstreferencia_pedido(beanConnector db) {		this.db = db;		this.classEntidad = referencia_pedido.class;	}	public Collection getlistareferencia_pedido() {		String consulta = " SELECT * FROM referencia_pedido";		return getLista(consulta);	}	public Collection getlistareferencia_pedido(String refpnumpedido, String start, String param) {		start = start == null ? "0" : start;		param = param == null ? "" : param;		String consulta = " SELECT * FROM referencia_pedido where refpnumpedido=" + refpnumpedido + " and " + getTextoBusquedaResumen(param) + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);		return getLista(consulta);	}	public int getTotalPaginaslistareferencia_pedido(String refpnumpedido, String param) {		param = param == null ? "" : param;		String consulta = " SELECT count(1) FROM referencia_pedido where refpnumpedido=" + refpnumpedido + " and " + getTextoBusquedaResumen(param);		return getPaginas(consulta);	}		public Collection getReferenciaByPedido(String registroPedido) {		String consulta = "select rp.* from registro_pedido_detalle rpd, movimiento.referencia_pedido rp "				+ "where rp.refpnumpedido = rpd.rpdenumpedido "				+ "and rpd.rpderegped = " + registroPedido;		return getLista(consulta);	}		public Collection getReferenciaByNumPedido(String numPedido) {		String consulta = 				"select rp.refpcodsx, rp.refpnumpedido, b.bodnombre as refpbodega, rp.refpposicion,"				+ "				rp.refpcantidad, rp.refpvalorunit, rp.refpvalortotal,"				+ "				rp.refpsaldo,"				+ "				pd.procodigobarra refpproducto,"				+ "				pd.prodescripcion resumen,"				+ "				c.comnombre refpentrada, p.pedestado refpnacdetalle "				+ "				from movimiento.referencia_pedido rp, pedido p, "				+ "				producto pd, bodega b, entrada e, ingreso i, trafico t,"				+ "				compania c "				+ "				where rp.refpnumpedido = p.pedcodsx  "				+ "				and rp.refpproducto = pd.procodsx "				+ "				and b.bodcodsx = rp.refpbodega   "				+ "				and rp.refpentrada = e.entcodsx "				+ "				and e.entcodingreso = i.ingcodsx "				+ " 				and t.trafcodsx = i.ingtrafico "				+ "				and t.trafcompania = c.comcodsx "				+ "and p.pednumpedido = '" + numPedido + "' ";						return getLista(consulta);	}		public referencia_pedido getreferencia_pedidoUqKey(String refpnumpedido, String reftproducto) {		String cad = "SELECT * FROM referencia_pedido WHERE refpnumpedido='" + refpnumpedido + "' AND refpproducto = '" + reftproducto + "'";		return (referencia_pedido) getEntidad(cad);	}	public boolean crearreferencia_pedido(String refpnumpedido, String refpproducto, String refpbodega, String refpposicion, String refpcantidad, String refpvalorunit, String refpvalortotal, String refpentrada, String refpnacdetalle, String refpsaldo, String refppesoneto, String refppesobruto,			String refppesonetototal, String refppesobrutototal, String refpvalorfacunit, String refpvalorfactotal) throws SQLException {		String insert = "INSERT INTO referencia_pedido (refpnumpedido,refpproducto, refpbodega, refpposicion,refpcantidad,refpvalorunit,refpvalortotal, " + "refpentrada, refpnacdetalle, refpsaldo, refppesoneto, refppesobruto, refppesonetototal, refppesobrutototal, refpvalorfacunit, refpvalorfactotal) VALUES ("				+ (refpnumpedido == null ? "NULL" : "'" + refpnumpedido + "'") + "," + (refpproducto == null ? "NULL" : "'" + refpproducto + "'") + "," + (refpbodega == null ? "NULL" : "'" + refpbodega + "'") + "," + (refpposicion == null ? "NULL" : "'" + refpposicion + "'") + ","				+ (refpcantidad == null ? "NULL" : "'" + refpcantidad + "'") + "," + (refpvalorunit == null ? "NULL" : "'" + refpvalorunit + "'") + "," + (refpvalortotal == null ? "NULL" : "'" + refpvalortotal + "'") + "," + (refpentrada == null ? "NULL" : "'" + refpentrada + "'") + ","				+ (refpnacdetalle == null ? "NULL" : "'" + refpnacdetalle + "'") + "," + (refpsaldo == null ? "NULL" : "'" + refpsaldo + "'") + "," + (refppesoneto == null ? "NULL" : "'" + refppesoneto + "'") + "," + (refppesobruto == null ? "NULL" : "'" + refppesobruto + "'") + ","				+ (refppesonetototal == null ? "NULL" : "'" + refppesonetototal + "'") + "," + (refppesobrutototal == null ? "NULL" : "'" + refppesobrutototal + "'") + ","				+ (refpvalorfacunit == null ? "NULL" : "'" + refpvalorfacunit + "'") + "," + (refpvalorfactotal == null ? "NULL" : "'" + refpvalorfactotal + "'") + ")";		System.out.println("//////////////////////////" + insert);		int resp = db.executeUpdate(insert);		return resp == 0 ? false : true;	}	public referencia_pedido getreferencia_pedido(String refpcodsx) {		String cad = "SELECT * FROM referencia_pedido WHERE refpcodsx='" + refpcodsx + "'";		return (referencia_pedido) getEntidad(cad);	}	public boolean updatereferencia_pedido(referencia_pedido entity) throws SQLException {		String cad = " update referencia_pedido set  " + " refpnumpedido = " + (entity.getrefpnumpedido() == null ? "NULL" : "'" + entity.getrefpnumpedido() + "'") + "," + " refpproducto = " + (entity.getrefpproducto() == null ? "NULL" : "'" + entity.getrefpproducto() + "'") + "," + " refpbodega = "				+ (entity.getRefpbodega() == null ? "NULL" : "'" + entity.getRefpbodega() + "'") + "," + " refpposicion = " + (entity.getrefpposicion() == null ? "NULL" : "'" + entity.getrefpposicion() + "'") + "," + " refpcantidad = "				+ (entity.getrefpcantidad() == null ? "NULL" : "'" + entity.getrefpcantidad() + "'") + "," + " refpvalorunit = " + (entity.getrefpvalorunit() == null ? "NULL" : "'" + entity.getrefpvalorunit() + "'") + "," + " refpvalortotal = "				+ (entity.getrefpvalortotal() == null ? "NULL" : "'" + entity.getrefpvalortotal() + "'") + "," + " refpentrada = " + (entity.getRefpentrada() == null ? "NULL" : "'" + entity.getRefpentrada() + "'") + "," + " refpnacdetalle = "				+ (entity.getRefpnacdetalle() == null ? "NULL" : "'" + entity.getRefpnacdetalle() + "'") + "," + " refpsaldo = " + (entity.getRefpsaldo() == null ? "NULL" : "'" + entity.getRefpsaldo() + "'") + "," + " refppesoneto= "				+ (entity.getRefppesoneto() == null ? "NULL" : "'" + entity.getRefppesoneto() + "'") + "," + " refppesobruto = " + (entity.getRefppesobruto() == null ? "NULL" : "'" + entity.getRefppesobruto() + "'") + "," + " refppesonetototal = "				+ (entity.getRefppesonetototal() == null ? "NULL" : "'" + entity.getRefppesonetototal() + "'") + "," + " refppesobrutototal = " + (entity.getRefppesobrutototal() == null ? "NULL" : "'" + entity.getRefppesobrutototal() + "'") + ", " + " refpvalorfacunit = "				+ (entity.getRefpvalorfacunit() == null ? "NULL" : "'" + entity.getRefpvalorfacunit() + "'") + "," + " refpvalorfactotal = " + (entity.getRefpvalorfactotal() == null ? "NULL" : "'" + entity.getRefpvalorfactotal() + "'") + " where refpcodsx = " + entity.getrefpcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean updatereferencia_pedido(referencia_pedidoForm entity) throws SQLException {		String cad = " update referencia_pedido set  " + " refpnumpedido = " + (entity.getrefpnumpedido() == null ? "NULL" : "'" + entity.getrefpnumpedido() + "'") + "," + " refpproducto = " + (entity.getrefpproducto() == null ? "NULL" : "'" + entity.getrefpproducto() + "'") + "," + " refpbodega = "				+ (entity.getRefpbodega() == null ? "NULL" : "'" + entity.getRefpbodega() + "'") + "," + " refpposicion = " + (entity.getrefpposicion() == null ? "NULL" : "'" + entity.getrefpposicion() + "'") + "," + " refpcantidad = "				+ (entity.getrefpcantidad() == null ? "NULL" : "'" + entity.getrefpcantidad() + "'") + "," + " refpvalorunit = " + (entity.getrefpvalorunit() == null ? "NULL" : "'" + entity.getrefpvalorunit() + "'") + "," + " refpvalortotal = "				+ (entity.getrefpvalortotal() == null ? "NULL" : "'" + entity.getrefpvalortotal() + "'") + "," + " refpentrada = " + (entity.getRefpentrada() == null ? "NULL" : "'" + entity.getRefpentrada() + "'") + "," + " refpnacdetalle = "				+ (entity.getRefpnacdetalle() == null ? "NULL" : "'" + entity.getRefpnacdetalle() + "'") + "," + " refpsaldo = " + (entity.getRefpsaldo() == null ? "NULL" : "'" + entity.getRefpsaldo() + "'") + "," + " refppesoneto= "				+ (entity.getRefppesoneto() == null ? "NULL" : "'" + entity.getRefppesoneto() + "'") + "," + " refppesobruto = " + (entity.getRefppesobruto() == null ? "NULL" : "'" + entity.getRefppesobruto() + "'") + "," + " refppesonetototal = "				+ (entity.getRefppesonetototal() == null ? "NULL" : "'" + entity.getRefppesonetototal() + "'") + "," + " refppesobrutototal = " + (entity.getRefppesobrutototal() == null ? "NULL" : "'" + entity.getRefppesobrutototal() + "'") + ", " + " refpvalorfacunit = "				+ (entity.getRefpvalorfacunit() == null ? "NULL" : "'" + entity.getRefpvalorfacunit() + "'") + "," + " refpvalorfactotal = " + (entity.getRefpvalorfactotal() == null ? "NULL" : "'" + entity.getRefpvalorfactotal() + "'") + " where refpcodsx = " + entity.getrefpcodsx();		int resp = db.executeUpdate(cad);		return resp == 0 ? false : true;	}	public boolean eliminarReferenciasPedido(String refpnumpedido) throws SQLException {		String elim = " delete from referencia_pedido where refpnumpedido= " + refpnumpedido + " ";		int r = db.executeUpdate(elim);		return r == 0 ? false : true;	}	public boolean eliminar(String refpcodsx) throws SQLException {		String elim = " delete from referencia_pedido where refpcodsx= " + refpcodsx + " ";		int r = db.executeUpdate(elim);		return r == 0 ? false : true;	}	public Collection getlistareferencia_pedido(String refpnumpedido) {		String consulta = " SELECT * FROM referencia_pedido where refpnumpedido=" + refpnumpedido;		return getLista(consulta);	}	public Collection getProductosPedido(String refpnumpedido) {		String consulta = " select refpproducto, sum(refpcantidad) as cant, sum(refppesonetototal) as peso, " + "sum(refppesobrutototal) as bruto from " + " referencia_pedido, entrada where refpnumpedido=" + refpnumpedido + "  and entcodsx = refpentrada " + " group by refpproducto ";		return getListaDeListas(consulta);	}	public Collection getProductosPedido_SortByModelo(String refpnumpedido) {		String consulta = " select refpproducto, promodelo, sum(refpcantidad) as cant, sum(refppesonetototal) as peso, " + "sum(refppesobrutototal) as bruto " 				+ "from referencia_pedido " + "inner join entrada on entcodsx = refpentrada " 				+ "inner join producto on procodsx = refpproducto "				+ "where refpnumpedido = " + refpnumpedido + "  " + "group by refpproducto, promodelo " + "order by promodelo";		return getListaDeListas(consulta);	}	public Collection getlistareferencia_pedido(String refpnumpedido, String refpproducto) {		String consulta = " SELECT * FROM referencia_pedido where refpnumpedido=" + refpnumpedido 				+ " and refpproducto=" + refpproducto + " order by refpposicion desc ";		return getLista(consulta);	}	public Collection getProductosPedidoDisponible(String refpnumpedido) {		String consulta = " select refpproducto, sum(refpsaldo) from " + " referencia_pedido where refpnumpedido=" + refpnumpedido + "  group by refpproducto  having sum(refpcantidad) >0";		return getListaDeListas(consulta);	}	public Collection getlistareferencia_pedido_disponible(String refpnumpedido, String refpproducto) {		String consulta = " SELECT * FROM referencia_pedido where refpnumpedido=" + refpnumpedido + " and refpproducto=" + refpproducto + "  and refpsaldo >0";		return getLista(consulta);	}	public String getDisponible(String refpnumpedido, String refpproducto) {		String consulta = " select sum(refpsaldo) from " + " referencia_pedido where refpnumpedido=" + refpnumpedido + "  and refpproducto=" + refpproducto;		return getCampo(consulta);	}	public Collection getlistareferencia_pedido_disponible_agregar(String refpnumpedido, String start, String param) {		String consulta = " SELECT '' as refpcodsx, refpnumpedido, refpproducto, promodelo, prodescripcion, '' as refpcantidad, " + "	'' as refpvalorunit, '' as refpvalortotal, '' as refpentrada, '' as refpnacdetalle, sum(refpsaldo), "				+ "0 as refppesoneto, 0 as refppesobruto, 0 as refppesonetototal, 0 as refppesobrutototal,'' as resumen, 0 as refpvalorfacunit, 0 as refpvalorfactotal " + " FROM referencia_pedido, producto where procodsx = refpproducto and refpnumpedido= " + refpnumpedido + "   and refpsaldo >0  " + " and "				+ getTextoBusquedaResumen("referencia_pedido", param) + " group by refpnumpedido, refpproducto, promodelo, prodescripcion";		return getLista(consulta);	}	public int getTotalPaginaslistareferencia_pedido_disponible_agregar(String refpnumpedido, String param) {		param = param == null ? "" : param;		String consulta = " SELECT  count(distinct refpproducto)  FROM referencia_pedido, producto where refpnumpedido=" + refpnumpedido + "   and refpsaldo >0 " + " and refpproducto = procodsx and " + getTextoBusquedaResumen("referencia_pedido", param);		return getPaginas(consulta);	}		public Collection getlistadeclaracion_pedido(String refpnumpedido) {		String consulta = " select distinct naccodsx, nacdeclaracion "				+ " from pedido "				+ " inner join referencia_pedido on refpnumpedido = pedcodsx "				+ " inner join nacionalizacion_detalle on nadcodsx = refpnacdetalle "				+ " inner join nacionalizacion on naccodsx = nadcodnac "				+ " where pedcodsx = " + refpnumpedido				+ " order by 2";		return getListaDeListas(consulta);	}	public Collection getlistareferencia_pedido_ruta_logica(String refpnumpedido) {		String consulta = " SELECT refpcodsx, refpnumpedido, refpproducto, refpbodega, refpposicion, " + 				"       refpcantidad, refpvalorunit, refpvalortotal, refpentrada, refpnacdetalle, " + 				"       refpsaldo, refppesoneto, refppesobruto, refppesonetototal, refppesobrutototal, " + 				"       resumen, refpvalorfacunit, refpvalorfactotal FROM referencia_pedido "				+ " where refpnumpedido=" + refpnumpedido 				+ " order by \r" + 				"  (select bodega from movimiento.posicion_ruta_logica(refpbodega,refpposicion)) asc \r" + 				", (select estante from movimiento.posicion_ruta_logica(refpbodega,refpposicion)) desc NULLS LAST \r" + 				", (select nivel from movimiento.posicion_ruta_logica(refpbodega,refpposicion)) desc \r" + 				", (select posicion from movimiento.posicion_ruta_logica(refpbodega,refpposicion)) desc \r" + 				", (select frente_pos from movimiento.posicion_ruta_logica(refpbodega,refpposicion)) asc";		return getLista(consulta);	}}