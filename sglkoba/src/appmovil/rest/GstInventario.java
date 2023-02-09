package appmovil.rest;

import db.*;
import java.util.Collection;
import java.util.List;
import java.sql.SQLException;

public class GstInventario extends GstTabla{

		public GstInventario() {
			db = new beanConnector();
			this.classEntidad = Inventario.class;
		}

		public GstInventario(beanConnector db) {
			this.db = db;
			this.classEntidad = Inventario.class;
		}

		public Collection getAll() {
			String consulta = "SELECT * FROM inventario";
			return getLista(consulta);
		}
		
		public Inventario getByReferenceAndGroup(String referencia, int grupoId) {
			String consulta = " SELECT * FROM inventario where invreferencia = '" + referencia + "' and invgrupoid=" + grupoId;
			return (Inventario)getEntidad(consulta);
		}

		public boolean crear(String conteo, String terminal, String usuario, String bodega, String posicion, String referencia, 
				String producto, String fecha, int grupoId, String codigoInventario) throws SQLException {
			String insert = "INSERT INTO inventario( "
					+ "	invcodsx, invconteo, invterminal, invusuario, invbodega, invposicion, invreferencia, invproducto, invfecha, invgrupoid, invinventariopadre) "
					+ "	VALUES (" + getId() + ", " + conteo + ", '" + terminal + "', '" + usuario + "', " + bodega + ", '" + posicion + 
					"', '" + referencia + "', " + producto + ", '" + fecha + "'" + ", " + grupoId + "," + codigoInventario + ")";
			
			
				int resp = db.executeUpdate(insert);
				System.out.println(insert);
			
			return resp == 0 ? false : true;
		}
		
		public boolean update(String id, String conteo, String terminal, String usuario, 
				String bodega, String posicion, String referencia, String producto, String fecha, int grupoId) throws SQLException {
			String cad = " UPDATE inventario "
					+ "	SET invconteo=" + conteo + ", invterminal='" + terminal + "', "
					+ "	invusuario='" + usuario +"', invbodega="+ bodega +", invposicion='"+ posicion + "'" +
					", invreferencia= '" + referencia + "'" +
					", invgrupoid= " + grupoId +
					", invproducto= " + producto
					+ "	WHERE invcodsx = " + id;
			System.out.println(cad);
			int resp = db.executeUpdate(cad);
			return resp == 0 ? false : true;
		}
		
		public int getId() {
			String cad = "select max(invcodsx)+1 invcodsx from inventario ";
			Inventario i = (Inventario)getEntidad(cad);

			if(i == null) {
				return 1;
			} else {
				if(i.getInvcodsx() == null) {
					return 1;
				}
				return Integer.parseInt(i.getInvcodsx());
			}
		}
		
		public int getGrupoId() {
			String cad = "select max(invgrupoid)+1 invgrupoid from inventario ";
			Inventario i = (Inventario)getEntidad(cad);

			if(i == null) {
				return 1;
			} else {
				if(i.getInvcodsx() == null) {
					return 1;
				}
				return Integer.parseInt(i.getInvcodsx());
			}
		}
		

	}
