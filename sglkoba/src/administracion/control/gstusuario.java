package administracion.control;

import administracion.form.*;

import administracion.entity.*;
import db.*;
import java.util.Collection;
import java.sql.SQLException;

/***********************************************************************************************************************************************************************************************************************************************************************************************************
 * CLASE GENERADA CON generator V3.0 By pablito****
 **********************************************************************************************************************************************************************************************************************************************************************************************************/

public class gstusuario extends GstTabla {

	public gstusuario() {
		db = new beanConnector();
		this.classEntidad = usuario.class;
	}

	public gstusuario(beanConnector db) {
		this.db = db;
		this.classEntidad = usuario.class;
	}

	public Collection getlistausuario() {

		String consulta = " SELECT * FROM usuario";
		return getLista(consulta);
	}

	public usuario validar(String login, String clave) {
		String cad = "select * from usuario where usulogin='" + login + "' and usuclave='" + clave + "'";
		return (usuario) getEntidad(cad);
	}

	public Collection getlistausuario(String start, String param) {

		start = start == null ? "0" : start;
		param = param == null ? "" : param.toUpperCase();
		String consulta = " SELECT * FROM usuario where " + getTextoBusquedaResumen(param) + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
		return getLista(consulta);
	}

	public int getTotalPaginaslistausuario(String param) {
		param = param == null ? "" : param.toUpperCase();
		String consulta = " SELECT count(1) FROM usuario where  " + getTextoBusquedaResumen(param);
		return getPaginas(consulta);

	}

	public boolean crearusuario(String usunombre, String usulogin, String usuclave, String usumail, String usugrupo, String usucompania, String usucodcliente) throws SQLException {
		String insert = "INSERT INTO usuario (usunombre,usulogin,usuclave,usumail,usugrupo,usucompania,usucodcliente) VALUES (" + (usunombre == null ? "NULL" : "'" + usunombre + "'") + "," + (usulogin == null ? "NULL" : "'" + usulogin + "'") + ","
				+ (usuclave == null ? "NULL" : "'" + usuclave + "'") + "," + (usumail == null ? "NULL" : "'" + usumail + "'") + "," + (usugrupo == null ? "NULL" : "'" + usugrupo + "'") + "," + (usucompania == null ? "NULL" : "'" + usucompania + "'") + ","
				+ (usucodcliente == null ? "NULL" : "'" + usucodcliente + "'") + ")";
		int resp = db.executeUpdate(insert);
		if (resp > 0) {
			crearrol(usulogin);
		}
		return resp == 0 ? false : true;
	}

	public usuario getusuario(String usucodsx) {
		String cad = "SELECT * FROM usuario WHERE usucodsx='" + usucodsx + "'";
		return (usuario) getEntidad(cad);
	}

	public usuario getusuario_by_login(String usulogin) {
		String cad = "SELECT * FROM usuario WHERE usulogin='" + usulogin + "'";
		return (usuario) getEntidad(cad);
	}

	public boolean updateusuario(usuario entity) throws SQLException {
		String cad = " update usuario set  " + " usunombre = " + (entity.getusunombre() == null ? "NULL" : "'" + entity.getusunombre() + "'") + "," + " usulogin = " + (entity.getusulogin() == null ? "NULL" : "'" + entity.getusulogin() + "'") + "," + " usuclave = "
				+ (entity.getusuclave() == null ? "NULL" : "'" + entity.getusuclave() + "'") + "," + " usumail = " + (entity.getusumail() == null ? "NULL" : "'" + entity.getusumail() + "'") + "," + " usugrupo = " + (entity.getusugrupo() == null ? "NULL" : "'" + entity.getusugrupo() + "'") + ","
				+ " usucompania = " + (entity.getusucompania() == null ? "NULL" : "'" + entity.getusucompania() + "'") + "," + " usucodcliente = " + (entity.getusucodcliente() == null ? "NULL" : "'" + entity.getusucodcliente() + "'") + " where usucodsx = " + entity.getusucodsx();
		int resp = db.executeUpdate(cad);
		if (resp > 0) {
			crearrol(entity.getusulogin());
		}
		return resp == 0 ? false : true;
	}

	public boolean updateusuario(usuarioForm entity) throws SQLException {
		String cad = " update usuario set  " + " usunombre = " + (entity.getusunombre() == null ? "NULL" : "'" + entity.getusunombre() + "'") + "," + " usulogin = " + (entity.getusulogin() == null ? "NULL" : "'" + entity.getusulogin() + "'") + "," + " usuclave = "
				+ (entity.getusuclave() == null ? "NULL" : "'" + entity.getusuclave() + "'") + "," + " usumail = " + (entity.getusumail() == null ? "NULL" : "'" + entity.getusumail() + "'") + "," + " usugrupo = " + (entity.getusugrupo() == null ? "NULL" : "'" + entity.getusugrupo() + "'") + ","
				+ " usucompania = " + (entity.getusucompania() == null ? "NULL" : "'" + entity.getusucompania() + "'") + "," + " usucodcliente = " + (entity.getusucodcliente() == null ? "NULL" : "'" + entity.getusucodcliente() + "'") + " where usucodsx = " + entity.getusucodsx();
		int resp = db.executeUpdate(cad);
		if (resp > 0) {
			crearrol(entity.getusulogin());
		}
		return resp == 0 ? false : true;
	}

	public boolean eliminar(String usucodsx) throws SQLException {
		usuario entity = getusuario(usucodsx);
		String elim = " delete from usuario where usucodsx = '" + usucodsx + "'";
		int r = db.executeUpdate(elim);
		if (r > 0) {
			eliminarrol(entity.getusulogin());
		}
		return r == 0 ? false : true;
	}

	public boolean crearrol(String nombre) {
		String cad = "CREATE ROLE " + nombre + " SUPERUSER INHERIT NOCREATEDB NOCREATEROLE";
		try {
			int r = db.executeUpdate(cad);
			return r == 0 ? false : true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean eliminarrol(String nombre) {
		String cad = "DROP ROLE " + nombre;
		try {
			int r = db.executeUpdate(cad);
			return r == 0 ? false : true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean asignarusuario(String nombre) {
		String cad = "SET SESSION ROLE '" + nombre + "'";
		try {
			int r = db.executeUpdate(cad);
			return r == 0 ? false : true;
		} catch (SQLException e) {
			return false;
		}
	}
}
