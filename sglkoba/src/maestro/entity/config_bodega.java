package maestro.entity;

public class config_bodega implements java.io.Serializable {
	
	private String cbbodega;

	private String cbestante;

	private String cbnivel;

	private String cbposicion;

	private String cbestado;

	private String cbentrada;

	private String cbcategoria;
	
	private String cbproducto;
	
	private String cbfrente_pos;

	public config_bodega() {
		super();
		// TODO Auto-generated constructor stub
	}

	public config_bodega(String cbbodega, String cbestante, String cbnivel, String cbposicion, String cbestado, String cbentrada, String cbcategoria, String cbproducto, String cbfrente_pos) {
		super();
		this.cbbodega = cbbodega;
		this.cbestante = cbestante;
		this.cbnivel = cbnivel;
		this.cbposicion = cbposicion;
		this.cbestado = cbestado;
		this.cbentrada = cbentrada;
		this.cbcategoria = cbcategoria;
		this.cbproducto = cbproducto;
		this.cbfrente_pos = cbfrente_pos;
	}

	public String getCbbodega() {
		return cbbodega;
	}

	public void setCbbodega(String cbbodega) {
		this.cbbodega = cbbodega;
	}

	public String getCbestante() {
		return cbestante;
	}

	public void setCbestante(String cbestante) {
		this.cbestante = cbestante;
	}

	public String getCbnivel() {
		return cbnivel;
	}

	public void setCbnivel(String cbnivel) {
		this.cbnivel = cbnivel;
	}

	public String getCbposicion() {
		return cbposicion;
	}

	public void setCbposicion(String cbposicion) {
		this.cbposicion = cbposicion;
	}

	public String getCbestado() {
		return cbestado;
	}

	public void setCbestado(String cbestado) {
		this.cbestado = cbestado;
	}

	public String getCbentrada() {
		return cbentrada;
	}

	public void setCbentrada(String cbentrada) {
		this.cbentrada = cbentrada;
	}

	public String getCbcategoria() {
		return cbcategoria;
	}

	public void setCbcategoria(String cbcategoria) {
		this.cbcategoria = cbcategoria;
	}

	public String getCbproducto() {
		return cbproducto;
	}

	public void setCbproducto(String cbproducto) {
		this.cbproducto = cbproducto;
	}

	public config_bodega(String cbfrente_pos) {
		super();
		this.cbfrente_pos = cbfrente_pos;
	}	

}
