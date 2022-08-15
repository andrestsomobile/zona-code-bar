package administracion.entity;
/**
 * CLASE GENERADA CON generator V2.0 By pablito******
 */

public class parametro implements java.io.Serializable { 

private String pk_parametro;
private String nombre;
private String descripcion;
private String valor;
private String ubicacion;
private String tipo_elemento;

/**
 * @uml.property  name="resumen"
 */

public parametro(String pk_parametro,String nombre,String descripcion,String valor, String ubicacion, String tipo_elemento)  {
this.pk_parametro  =  pk_parametro;
this.nombre  =  nombre;
this.descripcion  =  descripcion;
this.valor  =  valor;
this.ubicacion = ubicacion;
this.tipo_elemento = tipo_elemento;

}
public String getpk_parametro() {
return pk_parametro;
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

public String getubicacion() {
return ubicacion;
}
public String gettipo_elemento() {
return tipo_elemento;
}
public void setpk_parametro(String new_pk_parametro) {
this.pk_parametro =  new_pk_parametro;
}

public void setnombre(String new_nombre) {
this.nombre =  new_nombre;
}
public void setdescripcion(String new_descripcion) {
this.descripcion =  new_descripcion;
}
public void setvalor(String new_valor) {
this.valor =  new_valor;
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

/**
 * @param resumen
 * @uml.property  name="resumen"
 */

}