package ingreso.entity;/** * CLASE GENERADA CON generator V2.0 By pablito****** */public class ingreso implements java.io.Serializable {	private String ingcodsx;	private String ingcodcia;	private String ingfecha;	private String ingtipo;	private String ingtrafico;	private String ingobservacion;	private String ingestado;	private String ingcausal;	private String ingcli_devolucion;	private String ingpesoneto;	private String ingpesobruto;	private String ingfob;	private String ingcantidad;	private String ingciudad_dev;	private String resumen;	private String ingconsec;	private String ingconseczfp;	private String ingplanilla;		private String ingusuario;		private String ingtipoasignacion;	public ingreso(String ingcodsx, String ingcodcia, String ingfecha, String ingtipo, String ingtrafico, String ingobservacion, String ingestado, String ingcausal, String ingcli_devolucion, String ingpesoneto, String ingpesobruto, String ingfob, String ingcantidad, String ingciudad_dev,			String resumen, String ingconsec, String ingconseczfp, String ingplanilla, String ingusuario, String ingtipoasignacion ) {		this.ingcodsx = ingcodsx;		this.ingcodcia = ingcodcia;		this.ingfecha = ingfecha;		this.ingtipo = ingtipo;		this.ingtrafico = ingtrafico;		this.ingobservacion = ingobservacion;		this.ingestado = ingestado;		this.ingcausal = ingcausal;		this.ingcli_devolucion = ingcli_devolucion;		this.ingpesobruto = ingpesobruto;		this.ingpesoneto = ingpesoneto;		this.ingfob = ingfob;		this.ingcantidad = ingcantidad;		this.ingciudad_dev = ingciudad_dev;		this.resumen = resumen;		this.ingconsec = ingconsec;		this.ingconseczfp = ingconseczfp;		this.ingplanilla = ingplanilla;		this.ingusuario = ingusuario;		this.ingtipoasignacion = ingtipoasignacion;	}	public String getIngusuario() {		return ingusuario;	}	public void setIngusuario(String ingusuario) {		this.ingusuario = ingusuario;	}	public String getingcodsx() {		return ingcodsx;	}	public String getingcodcia() {		return ingcodcia;	}	public String getingfecha() {		return ingfecha;	}	public String getingtipo() {		return ingtipo;	}	public String getingtrafico() {		return ingtrafico;	}	public void setingcodsx(String new_ingcodsx) {		this.ingcodsx = new_ingcodsx;	}	public void setingcodcia(String new_ingcodcia) {		this.ingcodcia = new_ingcodcia;	}	public void setingfecha(String new_ingfecha) {		this.ingfecha = new_ingfecha;	}	public void setingtipo(String new_ingtipo) {		this.ingtipo = new_ingtipo;	}	public void setingtrafico(String new_ingtrafico) {		this.ingtrafico = new_ingtrafico;	}	/**	 * @return	 * @uml.property name="ingestado"	 */	public String getIngestado() {		return ingestado;	}	/**	 * @param ingestado	 * @uml.property name="ingestado"	 */	public void setIngestado(String ingestado) {		this.ingestado = ingestado;	}	/**	 * @return	 * @uml.property name="ingobservacion"	 */	public String getIngobservacion() {		return ingobservacion;	}	/**	 * @param ingobservacion	 * @uml.property name="ingobservacion"	 */	public void setIngobservacion(String ingobservacion) {		this.ingobservacion = ingobservacion;	}	/**	 * @return	 * @uml.property name="ingcausal"	 */	public String getIngcausal() {		return ingcausal;	}	/**	 * @param ingcausal	 * @uml.property name="ingcausal"	 */	public void setIngcausal(String ingcausal) {		this.ingcausal = ingcausal;	}	/**	 * @return	 * @uml.property name="ingcli_devolucion"	 */	public String getIngcli_devolucion() {		return ingcli_devolucion;	}	/**	 * @param ingcli_devolucion	 * @uml.property name="ingcli_devolucion"	 */	public void setIngcli_devolucion(String ingcli_devolucion) {		this.ingcli_devolucion = ingcli_devolucion;	}	/**	 * @return	 * @uml.property name="ingfob"	 */	public String getIngfob() {		return ingfob;	}	/**	 * @param ingfob	 * @uml.property name="ingfob"	 */	public void setIngfob(String ingfob) {		this.ingfob = ingfob;	}	/**	 * @return	 * @uml.property name="ingpesobruto"	 */	public String getIngpesobruto() {		return ingpesobruto;	}	/**	 * @param ingpesobruto	 * @uml.property name="ingpesobruto"	 */	public void setIngpesobruto(String ingpesobruto) {		this.ingpesobruto = ingpesobruto;	}	/**	 * @return	 * @uml.property name="ingpesoneto"	 */	public String getIngpesoneto() {		return ingpesoneto;	}	/**	 * @param ingpesoneto	 * @uml.property name="ingpesoneto"	 */	public void setIngpesoneto(String ingpesoneto) {		this.ingpesoneto = ingpesoneto;	}	/**	 * @return	 * @uml.property name="ingciudad_dev"	 */	public String getIngciudad_dev() {		return ingciudad_dev;	}	/**	 * @param ingciudad_dev	 * @uml.property name="ingciudad_dev"	 */	public void setIngciudad_dev(String ingciudad_dev) {		this.ingciudad_dev = ingciudad_dev;	}	/**	 * @return	 * @uml.property name="ingcantidad"	 */	public String getIngcantidad() {		return ingcantidad;	}	/**	 * @param ingcantidad	 * @uml.property name="ingcantidad"	 */	public void setIngcantidad(String ingcantidad) {		this.ingcantidad = ingcantidad;	}	/**	 * @return	 * @uml.property name="resumen"	 */	public String getResumen() {		return resumen;	}	/**	 * @param resumen	 * @uml.property name="resumen"	 */	public void setResumen(String resumen) {		this.resumen = resumen;	}	public String getIngconsec() {		return ingconsec;	}	public void setIngconsec(String ingconsec) {		this.ingconsec = ingconsec;	}	public String getIngconseczfp() {		return ingconseczfp;	}	public void setIngconseczfp(String ingconseczfp) {		this.ingconseczfp = ingconseczfp;	}	public String getIngplanilla() {		return ingplanilla;	}	public void setIngplanilla(String ingplanilla) {		this.ingplanilla = ingplanilla;	}		public String getIngtipoasignacion() {		return ingtipoasignacion;	}	public void setIngtipoasignacion(String ingtipoasignacion) {		this.ingtipoasignacion = ingtipoasignacion;	}	}