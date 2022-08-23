package administracion.form;
   import javax.servlet.http.HttpServletRequest; 
   import org.apache.struts.action.ActionErrors; 
   import org.apache.struts.action.ActionForm;
   import org.apache.struts.action.ActionMapping;
   import administracion.control.*;
   import administracion.entity.*;
  import org.apache.struts.action.ActionMessage; 
 /********************************************************
*****CLASE GENERADA CON generator V3.0 By pablito*******/

public class parametroForm  extends ActionForm { 


private String pk_parametro;
private String nombre;
private String descripcion;
private String valor;
private String ubicacion;
private String tipo_elemento;
 private String opcion; 
 
 public void setopcion(String newopcion) { this.opcion = newopcion; }
 public String getopcion() { return this.opcion; }
 
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
 public void reset(ActionMapping mapping, HttpServletRequest request) {  	} 

 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) { 
ActionErrors errors = new ActionErrors(); 
 gstparametro gparametro= new gstparametro(); 
 System.out.println(opcion);
 opcion = opcion==null || opcion.equals("") ? "crear" : opcion; 
 if(opcion.equals("set") || opcion.equals("delete")   ) return null; 
 
 if(opcion.equals("crear")   ) { 
	 
	 parametro parame = gparametro.getpk_parametro(pk_parametro);
	 if(parame!=null) {
		 	errors.add("general", new ActionMessage("El parametro <b>" + pk_parametro + "</b> ya existe, no pueden existir 2 parametros iguales en la aplicacion", false));
		 	return errors;
	 }
} 

 
//valido campos requeridos:
   
   if(nombre.equals("")) errors.add("nombre", new ActionMessage("El Nombre no puede ser vacio", false) ); 
   if(descripcion.equals("")) errors.add("descripcion", new ActionMessage("La descripcion no puede ser vacio", false) );  
   if(valor.equals("")) errors.add("valor", new ActionMessage("El valor no puede ser vacio", false) );  
   if(ubicacion.equals("")) errors.add("ubicacion", new ActionMessage("La ubicacion no puede ser vacio", false) );
   if(tipo_elemento.equals("")) errors.add("tipo_elemento", new ActionMessage("El tipo de elemento no puede ser vacio", false) ); 
   
   
   
   if(!errors.isEmpty()) errors.add("general", new ActionMessage("No se pudo Registrar Los datos, revise los mensajes", false));
   
   return errors;
 } 

 public void llenar (parametro entity ) { 
 

this.pk_parametro =  entity.getpk_parametro();
this.nombre =  entity.getnombre(); 
this.descripcion =  entity.getdescripcion(); 
this.valor =  entity.getvalor();  
this.ubicacion =  entity.getubicacion(); 
this.tipo_elemento = entity.gettipo_elemento();

 }
}