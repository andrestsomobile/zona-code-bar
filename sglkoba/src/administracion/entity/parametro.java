package administracion.entity;
/**
 * CLASE GENERADA CON generator V2.0 By pablito******
 */

public class parametro implements java.io.Serializable { 

private String nombre;
private String descripcion;
private String valor;
private String pk_parametro;
private String ubicacion;
private String tipo_elemento;

/**
 * @uml.property  name="resumen"
 */
private String resumen;
public parametro(String nombre,String descripcion,String valor,String pk_parametro, String resumen, String ubicacion, String tipo_elemento)  {

this.nombre  =  nombre;
this.descripcion  =  descripcion;
this.valor  =  valor;
this.pk_parametro  =  pk_parametro;
this.resumen = resumen;
this.ubicacion = ubicacion;
this.tipo_elemento = tipo_elemento;
}

public String getnombre() {
return nombre;
}
public String getdescripcion() {
return descripcion;
}
public String getvalor() {
return valor;
}
public String getpk_parametro() {
return pk_parametro;
}
public String getubicacion() {
return ubicacion;
}
public String gettipo_elemento() {
return tipo_elemento;
}

public void setusunombre(String new_nombre) {
this.nombre =  new_nombre;
}
public void setusulogin(String new_descripcion) {
this.descripcion =  new_descripcion;
}
public void setusuclave(String new_valor) {
this.valor =  new_valor;
}
public void setusumail(String new_pk_parametro) {
this.pk_parametro =  new_pk_parametro;
}
public void setubicacion(String new_ubicacion) {
this.ubicacion =  new_ubicacion;
}
public void settipo_elemento(String new_tipo_elemento) {
this.tipo_elemento =  new_tipo_elemento;
}

/**
 * @return
 * @uml.property  name="resumen"
 */
public String getResumen() {
	return resumen;
}
/**
 * @param resumen
 * @uml.property  name="resumen"
 */
public void setResumen(String resumen) {
	this.resumen = resumen;
}
}