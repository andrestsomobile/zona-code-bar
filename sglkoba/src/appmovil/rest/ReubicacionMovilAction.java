package appmovil.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import administracion.control.gstusuario;
import administracion.entity.usuario;
import db.beanConnector;
import ingreso.control.gstentrada;
import ingreso.control.gstingreso;
import ingreso.control.gstreubic_nacdetalle;
import ingreso.control.gstreubicacion;
import ingreso.control.gsttrafico;
import ingreso.entity.entrada;
import ingreso.entity.reubicacion;
import ingreso.entity.trafico;
import ingreso.form.reubicacionForm;
import maestro.control.gstempleado;
import maestro.control.gstproducto;
import maestro.control.gsttipoproducto;
import maestro.entity.empleado;
import maestro.entity.producto;
import maestro.entity.tipoproducto;
import nacionalizacion.control.gstnacionalizacion_detalle;
import nacionalizacion.entity.nacionalizacion_detalle;
import pedido.control.gstpedido;
import pedido.control.gstreferencia_pedido;
import pedido.entity.pedido;
import pedido.entity.referencia_pedido;
import registro.control.gstregistro_pedido;
import registro.control.gstregistro_pedido_detalle;
import util.JsonUtil;
import util.Math;

public class ReubicacionMovilAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mensaje = "";
		boolean isValid = true;
		JsonUtil msg = new JsonUtil();
		// de la entrada fin saco es la bodega y posicion de destino
		String reingreso = request.getParameter("ingreso");
		String bodegafin = request.getParameter("bodegaFin");
		String posicionfin = request.getParameter("posicionFin");

		String recantidad = request.getParameter("cantidad");
		
		if (bodegafin == null || bodegafin.isEmpty()) {
			isValid = false;
			mensaje = "Bodega Fin es obligatorio";
			msg.setMessage(mensaje);
			msg.setStatus(JsonUtil.FAIL);
		}
		
		
		Date date = new Date();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		String ingfecha = fecha.format(date);
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String inghora = hora.format(date);
		String recodcia = "";
		
		String retipo = "NACIONALIZADO";
		String reentradaor = inghora;
		String reentradafin = inghora;
		
		String reestado = "FINALIZADO"; // una reubicacion se crea finalizada y se puede activar, pero validando que no este
		// respaldando ninguna salida
		String refecha = ingfecha;
		String retipomov = "FISICO";

		boolean resp = true;
		beanConnector db = new beanConnector(false);
		gstreubicacion control = new gstreubicacion(db);
		gstentrada gent = new gstentrada(db);
		gstnacionalizacion_detalle gnacdet = new gstnacionalizacion_detalle(db);
		gstreubic_nacdetalle grnac = new gstreubic_nacdetalle(db);
		
		try {

			// primero saco la entrada original y sus datos
			entrada entor = gent.getentrada(reentradaor);
			reubicacionForm rf = new reubicacionForm();
			gstproducto gpro = new gstproducto();
			gsttipoproducto gtp = new gsttipoproducto();
			producto pro = gpro.getproducto(entor.getentcodproducto());
			tipoproducto tipopro = gtp.gettipoproducto(pro.getprotipoproducto());
			//String miposicionfin = tipopro.gettiprcodigo() + posicionfin;
			if (!bodegafin.equals(entor.getEntbodega()) || !posicionfin.equals(entor.getentposicion())) {

				BigDecimal pesoneto_or = new BigDecimal(entor.getentpesoneto());
				BigDecimal pesobruto_or = new BigDecimal(entor.getentpesobruto());
				BigDecimal saldopesoneto_or = new BigDecimal(entor.getentsaldopesoneto());
				BigDecimal saldopesobruto_or = new BigDecimal(entor.getentsaldopesobruto());
				BigDecimal saldonac_or = new BigDecimal(entor.getentsaldonac());
				BigDecimal saldosinnac_or = new BigDecimal(entor.getentsaldosinnac());

				BigDecimal valor = new BigDecimal(entor.getEntvalor());
				BigDecimal valortotal = valor.multiply(new BigDecimal(recantidad));
				BigDecimal recant = new BigDecimal(recantidad);
				// primero creo la entrada de destino:

				BigDecimal pesonetototal = pesoneto_or.multiply(recant);
				BigDecimal pesobrutototal = pesobruto_or.multiply(recant);
				BigDecimal saldopesoneto_dest = pesonetototal;
				BigDecimal saldopesobruto_dest = pesobrutototal;
				BigDecimal saldonac_dest = BigDecimal.ZERO;
				BigDecimal saldosinnac_dest = BigDecimal.ZERO;
				if (retipo.equals("NACIONALIZADO"))
					saldonac_dest = recant;
				else
					saldosinnac_dest = recant;

				// miro si la entrada existe o no para crearla o actualizarla

				entrada existe = gent.getentrada(reingreso, entor.getentcodproducto(), bodegafin, posicionfin);
				if (existe != null) {
					// le sumo al saldo nac / nnac y los saldos en peso:
					existe.setentsaldonac(Math.sumar_bigdecimal(saldonac_dest.toPlainString(), existe.getentsaldonac()) + "");
					existe.setentsaldosinnac(Math.sumar_bigdecimal(saldosinnac_dest.toPlainString(), existe.getentsaldosinnac()) + "");
					existe.setentsaldopesoneto(Math.sumar_bigdecimal(pesonetototal.toPlainString(), existe.getentsaldopesoneto()) + "");
					existe.setentsaldopesobruto(Math.sumar_bigdecimal(pesobrutototal.toPlainString(), existe.getentsaldopesobruto()) + "");
					// como existe le sumo tambien el saldo fisico
					existe.setEntsaldonacf(Math.sumar_bigdecimal(saldonac_dest.toPlainString(), existe.getEntsaldonacf()) + "");
					existe.setEntsaldosinnacf(Math.sumar_bigdecimal(saldosinnac_dest.toPlainString(), existe.getEntsaldosinnacf()) + "");
					// tambien se le suma la cantidad original ya que se debe de mover
					existe.setentcantidad(Math.sumar(recant.toPlainString(), existe.getentcantidad()));
					gent.updateentrada(existe);
				} else {
					// no existe, la creo se crea con todo igual al a entrada original menos los saldos en peso, del sx y fisicos:
					resp &= gent.crearentrada_original(reingreso, entor.getentcodproducto(), bodegafin, posicionfin, recantidad, entor.getentpesoneto(), pesonetototal + "", entor.getentpesobruto(), pesobrutototal + "", saldopesoneto_dest + "", saldopesobruto_dest + "", saldonac_dest + "",
							saldosinnac_dest + "", valor.toPlainString(), valortotal.toPlainString(), saldonac_dest + "", saldosinnac_dest + "", entor.getEntunidad(), entor.getentlote());

				}

				entrada entfin = gent.getentrada(reingreso, entor.getentcodproducto(), bodegafin, posicionfin);

				reentradafin = entfin != null ? entfin.getentcodsx() : null;

				// ahora resto saldos y pesos a la entrada original...
				saldopesoneto_or = saldopesoneto_or.subtract(saldopesoneto_dest);
				saldopesobruto_or = saldopesobruto_or.subtract(saldopesobruto_dest);
				saldonac_or = saldonac_or.subtract(saldonac_dest);
				saldosinnac_or = saldosinnac_or.subtract(saldosinnac_dest);

				// resto tambien al saldo fisico de la entrada original
				String saldo_fisico_nac_or = util.Math.restar_bigdecimal(entor.getEntsaldonacf(), saldonac_dest.toPlainString()).toPlainString();
				String saldo_fisico_nnac_or = util.Math.restar_bigdecimal(entor.getEntsaldosinnacf(), saldosinnac_dest.toPlainString()).toPlainString();

				entor.setentsaldopesoneto(saldopesoneto_or + "");
				entor.setentsaldopesobruto(saldopesobruto_or + "");
				entor.setentsaldonac(saldonac_or + "");
				entor.setentsaldosinnac(saldosinnac_or + "");
				entor.setEntsaldonacf(saldo_fisico_nac_or);
				entor.setEntsaldosinnacf(saldo_fisico_nnac_or);
				// resto a la entrada original la cantidad de la reubicacion para mover siempre la cantidad tambien
				entor.setentcantidad(Math.restar_bigdecimal(entor.getentcantidad(), recant.toPlainString()).toPlainString());
				// LOS PESOS TOTALES DE LA ENTRAA ORIGINAL NO SE RESTAN, SOLO LOS SALDOS!!!(en pesos y cant)

				resp &= gent.updateentrada(entor);

				// ahora si creo la reubicacion...

				resp &= control.crearreubicacion(recodcia, reingreso, retipo, reentradaor, reentradafin, recantidad, reestado, refecha, retipomov);

				reubicacion reubic = control.getreubicacion(recodcia, refecha);
				resp &= reubicarNacDetalles(reubic.getrecodsx(), entor.getentcodsx(), entfin.getentcodsx(), recantidad, gnacdet, grnac);

				if (resp)
					mensaje = "Reubicacion Creada con Exito";
				else
					mensaje = "No se pudo crear la reubicacion ";
						
				rf.llenar(control.getreubicacion(recodcia, refecha));
				rf.setopcion("update");

			} else {
				// rf.setreingreso(reingreso);
				mensaje = "Bodega, Posicion origen y destino deben ser diferentes";
				resp = false;
			}

			rf.setPosicionfin(posicionfin);

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "No se pudo Crear el reubicacion: <br> " + e.getLocalizedMessage();
			resp = false;
		}
		if (resp)
			db.commit();
		else
			db.rollback();
		
		msg.setMessage(mensaje);
		msg.setStatus(resp?JsonUtil.SUCESS:JsonUtil.FAIL);
		JSONObject jsonObject = new JSONObject(msg);
		String myJson = jsonObject.toString();
		response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(myJson);

		return null;

	}
	
	/**
	 * metodo llamado cuando la entrada que se va a mover, tiene saldo nacionalizado y tiene nacionalizacion_detalle asociadas, en este caso, hay que crear la nueva tabla reubicacion_nacdetalle en la que se registrara las nacdetalles que se restan, y a donde se suman.
	 * 
	 * @param entcodsx
	 * @return
	 */
	private boolean reubicarNacDetalles(String renreubicacion, String entor, String entfin, String cantidad, gstnacionalizacion_detalle gnacdet, gstreubic_nacdetalle grnac) {
		boolean resp = true;

		Collection nacdets = gnacdet.getlistanacionalizacion_detalleByEntrada(entor);
		// si no hay nacionalizaciones, no hace anda
		if (nacdets.isEmpty())
			return true;

		Iterator itenacd = nacdets.iterator();
		// por cada nacdetalle original que tengo, voy restando la cantidad y creando la nacdetalle fin

		BigDecimal faltante = new BigDecimal(cantidad);
		while (itenacd.hasNext() && faltante.compareTo(BigDecimal.ZERO) > 0) {

			nacionalizacion_detalle nacdetor = (nacionalizacion_detalle) itenacd.next();

			BigDecimal nadsaldo = new BigDecimal(nacdetor.getNadsaldo());

			String cant = "";
			String pesoneto = nacdetor.getNadpesoneto();
			String pesonetototal = nacdetor.getNadpesonetototal();
			String pesobruto = nacdetor.getNadpesobruto();
			String pesobrutototal = nacdetor.getNadpesobrutototal();
			String fob = nacdetor.getNadfob();

			String nadfobtotal = "0";
			if (faltante.compareTo(nadsaldo) > 0) {
				// si el faltante es mayor, cojo todo el saldo de la nacionalizacion
				cant = nadsaldo.toPlainString();
				nacdetor.setNadsaldo("0");
				nacdetor.setnadcantidad("0");

			} else {
				cant = faltante.toPlainString();
				nacdetor.setNadsaldo(Math.restar_bigdecimal(nadsaldo.toPlainString(), faltante.toPlainString()).toPlainString());
				nacdetor.setnadcantidad(Math.restar_bigdecimal(nacdetor.getnadcantidad(), faltante.toPlainString()).toPlainString());
			}

			try {
				// actualizo la original que ya le reste el saldo y reste las cantidades originales tambiem
				resp &= gnacdet.updatenacionalizacion_detalle(nacdetor);

				// miro si ya he nacionalizado algo de esa posicion:
				nacionalizacion_detalle nacdet = gnacdet.getnacionalizacion_detalle(entfin, nacdetor.getnadcodnac());

				if (nacdet == null) {
					// ahora creo la nacdetalle final
					resp &= gnacdet.crearnacionalizacion_detalle(nacdetor.getnadcodnac(), entfin, cant, cant, pesoneto, pesonetototal, pesobruto, pesobrutototal, fob, nadfobtotal);
				} else {
					// actualizo sumandole lo de la reubicacion:
					nacdet.setnadcantidad(util.Math.sumar(nacdet.getnadcantidad(), cant));
					nacdet.setNadsaldo(util.Math.sumar(nacdet.getNadsaldo(), cant));
					resp &= gnacdet.updatenacionalizacion_detalle(nacdet);
				}
				nacionalizacion_detalle nacdetfin = gnacdet.getnacionalizacion_detalle(entfin, nacdetor.getnadcodnac());

				// creo la relacion reuic_nacet para asi identificar en la reubicacion, lo que se movio con saldo
				// nac producto de nacionalizaciones se mueva bien
				resp &= grnac.crearreubic_nacdetalle(renreubicacion, nacdetor.getnadcodsx(), nacdetfin.getnadcodsx(), cant);
			} catch (SQLException ex) {

				ex.printStackTrace();
				resp = false;
				break;

			}

		}

		return resp;
	}
}
