package appmovil.rest;

public class Inventario {
	private String invcodsx;
	private String invfecha;
	private String invconteo;
	private String invterminal;
	private String invusuario;
	private String invbodega;
	private String invposicion;
	private String invreferencia;
	private String invproducto;
	private String invgrupoid;
	
	
	public Inventario(String invcodsx, String invfecha, String invconteo, String invterminal, String invusuario, String invbodega, 
			String invposicion, String invreferencia, String invproducto, String invgrupoid) {
		this.invcodsx = invcodsx;
		this.invfecha = invfecha;
		this.invconteo = invconteo;
		this.invterminal = invterminal;
		this.invusuario = invusuario;
		this.invbodega = invbodega;
		this.invposicion = invposicion;
		this.invreferencia = invreferencia;
		this.invproducto = invproducto;
		this.invgrupoid = invgrupoid;
	}
	
	public Inventario(String invcodsx) {
		this.invcodsx = invcodsx;
	}
	
	
	
	public String getInvgrupoid() {
		return invgrupoid;
	}



	public void setInvgrupoid(String invgrupoid) {
		this.invgrupoid = invgrupoid;
	}



	public String getInvproducto() {
		return invproducto;
	}



	public void setInvproducto(String invproducto) {
		this.invproducto = invproducto;
	}



	public String getInvcodsx() {
		return invcodsx;
	}
	public void setInvcodsx(String invcodsx) {
		this.invcodsx = invcodsx;
	}
	public String getInvfecha() {
		return invfecha;
	}
	public void setInvfecha(String invfecha) {
		this.invfecha = invfecha;
	}
	public String getInvconteo() {
		return invconteo;
	}
	public void setInvconteo(String invconteo) {
		this.invconteo = invconteo;
	}
	public String getInvterminal() {
		return invterminal;
	}
	public void setInvterminal(String invterminal) {
		this.invterminal = invterminal;
	}
	public String getInvusuario() {
		return invusuario;
	}
	public void setInvusuario(String invusuario) {
		this.invusuario = invusuario;
	}
	public String getInvbodega() {
		return invbodega;
	}
	public void setInvbodega(String invbodega) {
		this.invbodega = invbodega;
	}
	public String getInvposicion() {
		return invposicion;
	}
	public void setInvposicion(String invposicion) {
		this.invposicion = invposicion;
	}
	public String getInvreferencia() {
		return invreferencia;
	}
	public void setInvreferencia(String invreferencia) {
		this.invreferencia = invreferencia;
	}
	
	
}
