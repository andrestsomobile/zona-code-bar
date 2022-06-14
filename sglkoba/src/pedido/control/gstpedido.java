package pedido.control;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

import db.GstTabla;
import db.beanConnector;
import pedido.entity.pedido;
import pedido.form.pedidoForm;

/***********************************************************************************************************************************************************************************************************************************************************************************************************
 * CLASE GENERADA CON generator V3.0 By pablito****
 **********************************************************************************************************************************************************************************************************************************************************************************************************/

public class gstpedido extends GstTabla {

    public gstpedido() {
        db = new beanConnector();
        this.classEntidad = pedido.class;
    }

    public gstpedido(beanConnector db) {
        this.db = db;
        this.classEntidad = pedido.class;
    }

    public Collection getlistapedido() {

        String consulta = " SELECT * FROM pedido";
        return getLista(consulta);
    }

    public Collection getlistapedido(String start, String param) {
        start = start == null ? "0" : start;
        param = param == null ? "" : param;
        String consulta = " SELECT * FROM pedido where " + getTextoBusquedaResumen(param) + " order by pedcodsx desc " + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
        return getLista(consulta);
    }
    //solo compania 1
    public Collection getlistapedido_fecha(String fecha1,String fecha2) {
        
        String consulta = "select pedcodsx,entcodsx as pedcompania,pednumpedido,pedordencompra,pedguia,pedfechasistema,pedfecha,pedfechavenc,pedfechacita,pedhoracita,pedcliente,pedsucursal,peddireccion,pedciudad,peddepartamento,pedobservaciones,pedfactura,pedestado,pedsubtotal,pediva,peddescuento,pedtotal,pedtipo,"+
"pedpicking,pedpesoneto,pedpesobruto,pedhora,pedzona,pedcausal_hit," +
"promodelo as resumen,pedporc_iva,pednotifzfp from referencia_pedido,entrada,producto, pedido ped" +
                "  where entcodsx=refpentrada and refpnumpedido=pedcodsx and refpproducto=procodsx" +
                "   and pedcompania=1 and entsaldonacf>0 and entsaldonac<>entsaldonacf and pedfechasistema  between '"+fecha1+"' and '"+fecha2+"' order by pedfechasistema asc" +
                " ";
        return getLista(consulta);
    }
    
    public String getSaldoNac(String prodcuto) {
        String cad = "select reservadonac from reporte_saldo_reservado where comcodsx= 1 and procodsx = " + prodcuto;
        
        return getCampo(cad);
    }
    
    public int getTotalPaginaslistapedido(String param) {
        param = param == null ? "" : param;
        String consulta = " SELECT count(1) FROM pedido where  " + getTextoBusquedaResumen(param);
        return getPaginas(consulta);

    }

    public boolean crearpedido(String pedcompania, String pednumpedido, String pedordencompra, String pedguia, String pedfechasistema, String pedfecha, String pedfechavenc, String pedfechacita, String pedhoracita, String pedcliente, String pedsucursal, String peddireccion, String pedciudad,
            String peddepartamento, String pedobservaciones, String pedfactura, String pedestado, String pedsubtotal, String pediva, String peddescuento, String pedtotal, String pedtipo, String pedpicking, String pedhora, String pedzona, String pedcausal_hit, String pedporc_iva, String pedtransportadora,
            String pedfechaalistamiento, String pedfechadespacho, String pedfechaentrega) throws SQLException {
        String insert = "INSERT INTO pedido (pedcompania,pednumpedido,pedordencompra,pedguia,pedfechasistema,pedfecha," 
                + " pedfechavenc,pedfechacita,pedhoracita,pedcliente,pedsucursal,peddireccion,pedciudad,peddepartamento,"
                + " pedobservaciones,pedfactura,pedestado,pedsubtotal,pediva,peddescuento,pedtotal, pedtipo, pedpicking," 
                + " pedhora, pedzona, pedcausal_hit, pedporc_iva, pedtransportadora, pedfechaalistamiento, pedfechadespacho, pedfechaentrega"
                + ") VALUES ("
                + (pedcompania == null ? "NULL" : "'" + pedcompania + "'")
                + ","
                + (pednumpedido == null ? "NULL" : "'" + pednumpedido + "'")
                + ","
                + (pedordencompra == null ? "NULL" : "'" + pedordencompra + "'")
                + ","
                + (pedguia == null ? "NULL" : "'" + pedguia + "'")
                + ","
                + (pedfechasistema == null ? "NULL" : "'" + pedfechasistema + "'")
                + ","
                + (pedfecha == null ? "NULL" : "'" + pedfecha + "'")
                + ","
                + (pedfechavenc == null ? "NULL" : "'" + pedfechavenc + "'")
                + ","
                + (pedfechacita == null || pedfechacita.equals("") ? "NULL" : "'" + pedfechacita + "'")
                + ","
                + (pedhoracita == null ? "NULL" : "'" + pedhoracita + "'")
                + ","
                + (pedcliente == null ? "NULL" : "'" + pedcliente + "'")
                + ","
                + (pedsucursal == null ? "NULL" : "'" + pedsucursal + "'")
                + ","
                + (peddireccion == null ? "NULL" : "'" + peddireccion + "'")
                + ","
                + (pedciudad == null ? "NULL" : "'" + pedciudad + "'")
                + ","
                + (peddepartamento == null ? "NULL" : "'" + peddepartamento + "'")
                + ","
                + (pedobservaciones == null ? "NULL" : "'" + pedobservaciones + "'")
                + ","
                + (pedfactura == null ? "NULL" : "'" + pedfactura + "'")
                + ","
                + (pedestado == null ? "NULL" : "'" + pedestado + "'")
                + ","
                + (pedsubtotal == null ? "NULL" : "'" + pedsubtotal + "'")
                + ","
                + (pediva == null ? "NULL" : "'" + pediva + "'")
                + ","
                + (peddescuento == null ? "NULL" : "'" + peddescuento + "'")
                + ","
                + (pedtotal == null ? "NULL" : "'" + pedtotal + "'")
                + ","
                + (pedtipo == null ? "NULL" : "'" + pedtipo + "'")
                + ","
                + (pedpicking == null ? "NULL" : "'" + (pedpicking.equals("") ? null : pedpicking) + "'")
                + ","
                + (pedhora == null ? "NULL" : "'" + (pedhora.equals("") ? null : pedhora) + "'")
                + ","
                + (pedzona == null ? "NULL" : "'" + (pedzona.equals("") ? null : pedzona) + "'")
                + ","
                + (pedcausal_hit == null ? "NULL" : "'" + (pedcausal_hit.equals("") ? null : pedcausal_hit) + "'")
                + ","
                + (pedporc_iva == null ? "NULL" : "'" + (pedporc_iva) + "'")
                + ","
                + (pedtransportadora == null ? "NULL" : "'" + (pedtransportadora) + "'") 
                + ","
                + (pedfechaalistamiento == null || (pedfechaalistamiento != null && pedfechaalistamiento.equals(""))  ? "NULL" : "'" + (pedfechaalistamiento) + "'") 
                + ","
                + (pedfechadespacho == null || (pedfechadespacho != null && pedfechadespacho.equals(""))? "NULL" : "'" + (pedfechadespacho) + "'") 
                + ","
                + (pedfechaentrega == null || (pedfechaentrega != null && pedfechaentrega.equals(""))? "NULL" : "'" + (pedfechaentrega) + "'") 
                + ")";
        int resp = db.executeUpdate(insert);
        return resp == 0 ? false : true;
    }

    public pedido getpedido(String pedcodsx) {
        String cad = "SELECT * FROM pedido WHERE pedcodsx='" + pedcodsx + "'";
        return (pedido) getEntidad(cad);
    }

    public boolean updatepedido(pedido entity) throws SQLException {
        String cad = " update pedido set  " + " pedcompania = " + (entity.getpedcompania() == null ? "NULL" : "'" + entity.getpedcompania() + "'") + "," + " pednumpedido = " + (entity.getpednumpedido() == null ? "NULL" : "'" + entity.getpednumpedido() + "'") + "," + " pedordencompra = "
                + (entity.getpedordencompra() == null ? "NULL" : "'" + entity.getpedordencompra() + "'") + "," + " pedguia = " + (entity.getpedguia() == null ? "NULL" : "'" + entity.getpedguia() + "'") + "," + " pedfechasistema = "
                + (entity.getpedfechasistema() == null ? "NULL" : "'" + entity.getpedfechasistema() + "'") + "," + " pedfecha = " + (entity.getpedfecha() == null ? "NULL" : "'" + entity.getpedfecha() + "'") + "," + " pedfechavenc = "
                + (entity.getpedfechavenc() == null ? "NULL" : "'" + entity.getpedfechavenc() + "'") + "," + " pedfechacita = " + (entity.getpedfechacita() == null || entity.getpedfechacita().equals("") ? "NULL" : "'" + entity.getpedfechacita() + "'") + "," + " pedhoracita = "
                + (entity.getpedhoracita() == null ? "NULL" : "'" + entity.getpedhoracita() + "'") + "," + " pedcliente = " + (entity.getpedcliente() == null ? "NULL" : "'" + entity.getpedcliente() + "'") + "," + " pedsucursal = "
                + (entity.getpedsucursal() == null ? "NULL" : "'" + entity.getpedsucursal() + "'") + "," + " peddireccion = " + (entity.getpeddireccion() == null ? "NULL" : "'" + entity.getpeddireccion() + "'") + "," + " pedciudad = "
                + (entity.getpedciudad() == null ? "NULL" : "'" + entity.getpedciudad() + "'") + "," + " peddepartamento = " + (entity.getpeddepartamento() == null ? "NULL" : "'" + entity.getpeddepartamento() + "'") + "," + " pedobservaciones = "
                + (entity.getpedobservaciones() == null ? "NULL" : "'" + entity.getpedobservaciones() + "'") + "," + " pedfactura = " + (entity.getpedfactura() == null ? "NULL" : "'" + entity.getpedfactura() + "'") + "," + " pedestado = "
                + (entity.getpedestado() == null ? "NULL" : "'" + entity.getpedestado() + "'") + "," + " pedsubtotal = " + (entity.getpedsubtotal() == null ? "NULL" : "'" + entity.getpedsubtotal() + "'") + "," + " pediva = " + (entity.getpediva() == null ? "NULL" : "'" + entity.getpediva() + "'")
                + "," + " peddescuento = " + (entity.getpeddescuento() == null ? "NULL" : "'" + entity.getpeddescuento() + "'") + "," + " pedtotal = " + (entity.getpedtotal() == null ? "NULL" : "'" + entity.getpedtotal() + "'") + "," + " pedtipo = "
                + (entity.getpedtipo() == null ? "NULL" : "'" + entity.getpedtipo() + "'") + "," + " pedpicking= " + (entity.getPedpicking() == null ? "NULL" : "" + entity.getPedpicking() + "") + "," + " pedzona= " + (entity.getPedzona() == null ? "NULL" : "" + entity.getPedzona() + "") + ","
                + " pedcausal_hit= " + (entity.getPedcausal_hit() == null ? "NULL" : "" + entity.getPedcausal_hit() + "") + "," + " pedporc_iva= " + (entity.getPedporc_iva() == null ? "NULL" : "" + entity.getPedporc_iva() + "") 
                + "," + "pedtransportadora = " + (entity.getPedtransportadora() == null ? "NULL" : "'" + (entity.getPedtransportadora()) + "'")
                + "," + "pedfechaalistamiento = " + (entity.getPedfechaalistamiento() == null ? "NULL" : "'" + (entity.getPedfechaalistamiento()) + "'")
                + "," + "pedfechadespacho = " + (entity.getPedfechadespacho() == null ? "NULL" : "'" + (entity.getPedfechadespacho()) + "'")
                + "," + "pedfechaentrega = " + (entity.getPedfechaentrega() == null ? "NULL" : "'" + (entity.getPedfechaentrega()) + "'")
                + " where pedcodsx = " + entity.getpedcodsx();
        int resp = db.executeUpdate(cad);
        return resp == 0 ? false : true;
    }

    public boolean updatepedido(pedidoForm entity) throws SQLException {
        String cad = " update pedido set  " + " pedcompania = " + (entity.getpedcompania() == null ? "NULL" : "'" + entity.getpedcompania() + "'") + "," + " pednumpedido = " + (entity.getpednumpedido() == null ? "NULL" : "'" + entity.getpednumpedido() + "'") + "," + " pedordencompra = "
                + (entity.getpedordencompra() == null ? "NULL" : "'" + entity.getpedordencompra() + "'") + "," + " pedguia = " + (entity.getpedguia() == null ? "NULL" : "'" + entity.getpedguia() + "'") + "," + " pedfechasistema = "
                + (entity.getpedfechasistema() == null ? "NULL" : "'" + entity.getpedfechasistema() + "'") + "," + " pedfecha = " + (entity.getpedfecha() == null ? "NULL" : "'" + entity.getpedfecha() + "'") + "," + " pedfechavenc = "
                + (entity.getpedfechavenc() == null ? "NULL" : "'" + entity.getpedfechavenc() + "'") + "," + " pedfechacita = " + (entity.getpedfechacita() == null || entity.getpedfechacita().equals("") ? "NULL" : "'" + entity.getpedfechacita() + "'") + "," + " pedhoracita = "
                + (entity.getpedhoracita() == null ? "NULL" : "'" + entity.getpedhoracita() + "'") + "," + " pedcliente = " + (entity.getpedcliente() == null ? "NULL" : "'" + entity.getpedcliente() + "'") + "," + " pedsucursal = "
                + (entity.getpedsucursal() == null ? "NULL" : "'" + entity.getpedsucursal() + "'") + "," + " peddireccion = " + (entity.getpeddireccion() == null ? "NULL" : "'" + entity.getpeddireccion() + "'") + "," + " pedciudad = "
                + (entity.getpedciudad() == null ? "NULL" : "'" + entity.getpedciudad() + "'") + "," + " peddepartamento = " + (entity.getpeddepartamento() == null ? "NULL" : "'" + entity.getpeddepartamento() + "'") + "," + " pedobservaciones = "
                + (entity.getpedobservaciones() == null ? "NULL" : "'" + entity.getpedobservaciones() + "'") + "," + " pedfactura = " + (entity.getpedfactura() == null ? "NULL" : "'" + entity.getpedfactura() + "'") + "," + " pedestado = "
                + (entity.getpedestado() == null ? "NULL" : "'" + entity.getpedestado() + "'") + "," + " pedsubtotal = " + (entity.getpedsubtotal() == null ? "NULL" : "'" + entity.getpedsubtotal() + "'") + "," + " pediva = " + (entity.getpediva() == null ? "NULL" : "'" + entity.getpediva() + "'")
                + "," + " peddescuento = " + (entity.getpeddescuento() == null ? "NULL" : "'" + entity.getpeddescuento() + "'") + "," + " pedtotal = " + (entity.getpedtotal() == null ? "NULL" : "'" + entity.getpedtotal() + "'") + "," + " pedtipo = "
                + (entity.getPedtipo() == null ? "NULL" : "'" + entity.getPedtipo() + "'") + "," + " pedpicking= " + (entity.getPedpicking() == null ? "NULL" : "" + entity.getPedpicking() + "") + "," + " pedzona= " + (entity.getPedzona() == null ? "NULL" : "" + entity.getPedzona() + "") + ","
                + " pedcausal_hit= " + (entity.getPedcausal_hit() == null ? "NULL" : "" + entity.getPedcausal_hit() + "") + "," + " pedporc_iva= " + (entity.getPedporc_iva() == null ? "NULL" : "" + entity.getPedporc_iva() + "")   
                + ","  + "pedtransportadora = " + (entity.getPedtransportadora() == null ? "NULL" : "'" + (entity.getPedtransportadora()) + "'")
                + "," + "pedfechaalistamiento = " + (entity.getPedfechaalistamiento() == null ? "NULL" : "'" + (entity.getPedfechaalistamiento()) + "'")
                + "," + "pedfechadespacho = " + (entity.getPedfechadespacho() == null ? "NULL" : "'" + (entity.getPedfechadespacho()) + "'")
                + "," + "pedfechaentrega = " + (entity.getPedfechaentrega() == null ? "NULL" : "'" + (entity.getPedfechaentrega()) + "'")
                + " where pedcodsx = " + entity.getpedcodsx();
        int resp = db.executeUpdate(cad);
        return resp == 0 ? false : true;
    }

    public boolean eliminar(String pedcodsx) throws SQLException {
        String elim = " delete from pedido where pedcodsx = '" + pedcodsx + "'";
        int r = db.executeUpdate(elim);
        return r == 0 ? false : true;
    }

    public pedido getpedido(String pedcompania, String pednumpedido) {
        String cad = "SELECT * FROM pedido WHERE pedcompania = " + pedcompania + " and pednumpedido = '" + pednumpedido + "' ";
        return (pedido) getEntidad(cad);
    }
    public int getPedidoCount(String pedcompania, String pednumpedido) {
        String cad = "SELECT count(*) FROM pedido WHERE pedcompania = " + pedcompania + " and pednumpedido = '" + pednumpedido + "' ";
        return getConteo(cad);
    }
    public int getPedidoCountEstado(String pedcompania, String pednumpedido, String pedestado) {
        String cad = "SELECT count(*) FROM pedido WHERE pedcompania = " + pedcompania + " and pednumpedido = '" + pednumpedido + "' and pedestado = '" + pedestado + "'";
        return getConteo(cad);
    }
    public int getPedidoRef(String promodelo, String refpnumpedido) {
        String cad = "select count(*) from producto,referencia_pedido where refpproducto=procodsx and promodelo ='" + promodelo + "' and refpnumpedido=" + refpnumpedido + "";
        //String cad = "SELECT count(*) FROM pedido WHERE pedcompania = " + pedcompania + " and pednumpedido = '" + pednumpedido + "' ";
        return getConteo(cad);
    }
    public int getPedidoRefPromodelo(String promodelo) {
        String cad = "select count(*) from producto where promodelo ='" + promodelo + "'";
        //String cad = "SELECT count(*) FROM pedido WHERE pedcompania = " + pedcompania + " and pednumpedido = '" + pednumpedido + "' ";
        return getConteo(cad);
    }
    
    public int getPedidoRefPromodeloCia(String cia, String promodelo) {
        String cad = "select count(*) from producto where procodigocia = " + cia + " and promodelo ='" + promodelo + "'";
        return getConteo(cad);
    }

    public pedido getpedidoByPicking(String pedpicking) {
        String cad = "SELECT * FROM pedido WHERE pedpicking='" + pedpicking + "'";
        return (pedido) getEntidad(cad);
    }
    
    public pedido getpedidoByNumPedidoDate(String numPedido, String date) {
        //String cad = "SELECT * FROM pedido WHERE pednumpedido='" + numPedido + "and pedfechasistema='"+date+ "'";
    	String cad = "SELECT * FROM pedido WHERE pednumpedido='" + numPedido + "'";
        return (pedido) getEntidad(cad);
    }
    
 

    public Collection getEmbarquePorPedido(String pedcodsx, String procodsx) {
        String cad = "select trafembarque, trafdocumento, nacdeclaracion, sum(refpcantidad) from referencia_pedido, entrada, nacionalizacion_detalle, nacionalizacion, ingreso,  trafico "
                + " where refpentrada = entcodsx and nadcodsx = refpnacdetalle and naccodsx = nadcodnac and entcodingreso =  ingcodsx and ingtrafico = trafcodsx " + " and refpproducto=" + procodsx + " and refpnumpedido = " + pedcodsx + " group by trafembarque, trafdocumento, nacdeclaracion";

        return getListaDeListas(cad);
    }

    public Collection getlistapedido(String pedcompania, String start, String param) {
        start = start == null ? "0" : start;
        param = param == null ? "" : param;
        String consulta = " SELECT * FROM pedido where " + " pedcompania= " + pedcompania + " and " + getTextoBusquedaResumen(param) + " order by pedcodsx desc" + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
        return getLista(consulta);
    }

    public int getTotalPaginaslistapedido(String pedcompania, String param) {
        param = param == null ? "" : param;
        String consulta = " SELECT count(1) FROM pedido where " + " pedcompania= " + pedcompania + " and " + getTextoBusquedaResumen(param);
        return getPaginas(consulta);

    }

    public Collection xgetlistapedido_despachar(String pedcompania, String despcodsx, String start, String param) {
        start = start == null ? "0" : start;
        param = param == null ? "" : param;
        /*
         * String consulta = " select pedido.* from pedido where pedcompania="+ pedcompania + " and pedestado='FINALIZADO' and pedcodsx in (select distinct refpnumpedido from referencia_pedido where "+ " refpnumpedido = pedcodsx group by refpproducto, refpnumpedido having sum(refpsaldo)>0 ) "+ " and " +
         * getTextoBusquedaResumen(param)+ " and pedcodsx not in (select despedpedido from despacho_pedido where despeddespacho=" + despcodsx + ") " + " order by pedcodsx desc " + " limit " + this.maxfilas + " offset " + (Integer.parseInt(start) * this.maxfilas);
         */
        // consulta original
        String consulta = " select * from pedido where pedcodsx in  " + " (select distinct pedcodsx from pedido, referencia_pedido where pedcompania=" + pedcompania + " and pedestado='FINALIZADO' and pedfechasistema between now()-interval '7 days' and now() and pedcodsx = refpnumpedido " + " and  " + getTextoBusquedaResumen("pedido", param)
                + "  and pedcodsx not in (select despedpedido from despacho_pedido) " + " group by pedcodsx having sum(refpsaldo)>0  ) order by pedcodsx desc " + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
        return getLista(consulta);
    
        //consulta a probar
        /*String consulta = "SELECT *"+
       "FROM pedido p"+
       "JOIN referencia_pedido rp ON (p.pedcodsx=rp.pedcodsx)"+
       "LEFT JOIN despacho_pedido dp ON (p.pedcodsx=dp.pedcodsx)"+
       "WHERE dp.pedcodsx IS NULL AND"+
       "pedcompania=" + pedcompania + " and pedestado='FINALIZADO' and pedcodsx = refpnumpedido " + " and " + getTextoBusquedaResumen("pedido", param)+""+
       "group by pedcodsx having sum(refpsaldo)>0  ) order by p.pedcodsx desc " + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
        return getLista(consulta);
        */
        
    }
    
    public int xgetTotalPaginaslistapedido_despachar(String pedcompania, String despcodsx, String param) {
        param = param == null ? "" : param;
        /*
         * String consulta = " select count(1) from pedido where pedcompania="+ pedcompania + " and pedestado='FINALIZADO' and pedcodsx in (select distinct refpnumpedido from referencia_pedido where "+ " refpnumpedido = pedcodsx group by refpproducto, refpnumpedido having sum(refpsaldo)>0 ) "+ " and " +
         * getTextoBusquedaResumen(param) + " and pedcodsx not in (select despedpedido from despacho_pedido) ";
         */
        String consulta = " select count(1) from pedido where pedcodsx in  " + " (select distinct pedcodsx from pedido, referencia_pedido where pedcompania=" + pedcompania + " and pedestado='FINALIZADO' and pedfechasistema between now()-interval '7 days' and now() and pedcodsx = refpnumpedido " + " and  " + getTextoBusquedaResumen("pedido", param)
                + " and pedcodsx not in (select despedpedido from despacho_pedido) )";
        return getPaginas(consulta);

    }
    
    public Collection getlistapedido_despachar(String pedcompania, String despcodsx, String pedtransportadora, String start, String param) {
        start = start == null ? "0" : start;
        param = param == null ? "" : param;

        String consulta = " select * from pedido where pedcodsx in  " + " (select distinct pedcodsx from pedido, referencia_pedido where pedcompania=" + pedcompania + " and pedtransportadora=" + pedtransportadora + " and pedestado='FINALIZADO' and pedfechasistema between now()-interval '7 days' and now() and pedcodsx = refpnumpedido " + " and  " + getTextoBusquedaResumen("pedido", param)
                + "  and NOT EXISTS ( select * from despacho_pedido where despedpedido = pedcodsx  ) " + " group by pedcodsx having sum(refpsaldo)>0  ) order by pedcodsx " + "  limit " + this.maxfilas + " offset  " + (Integer.parseInt(start) * this.maxfilas);
        return getLista(consulta);
    
        
    }

    public int getTotalPaginaslistapedido_despachar(String pedcompania, String despcodsx, String pedtransportadora, String param) {
        param = param == null ? "" : param;
        String consulta = " select count(1) from pedido where pedcodsx in  " + " (select distinct pedcodsx from pedido, referencia_pedido where pedcompania=" + pedcompania + " and pedtransportadora=" + pedtransportadora + " and pedestado='FINALIZADO' and pedfechasistema between now()-interval '7 days' and now() and pedcodsx = refpnumpedido " + " and  " + getTextoBusquedaResumen("pedido", param)
                + " and NOT EXISTS ( select * from despacho_pedido where despedpedido = pedcodsx  ) )";
        return getPaginas(consulta);

    }

    public Collection getlistapedidoByIngreso(String ingcodsx) {

        String consulta = "select pedido.* from pedido, referencia_pedido, ingreso , entrada where " + " pedcodsx = refpnumpedido and refpentrada = entcodsx and entcodingreso = ingcodsx and ingcodsx = " + ingcodsx;
        return getLista(consulta);
    }

    public Collection getlistapedidoByNacionalizacion(String naccodsx) {

        String consulta = "select pedido.* from pedido, referencia_pedido, nacionalizacion_detalle where " + " pedcodsx = refpnumpedido and refpnacdetalle =nadcodsx and nadcodnac = " + naccodsx;
        return getLista(consulta);
    }

    public Collection getlistapedidoByGuia(String pedcompania, String pedguia) {
        String cad = "select * from pedido where pedcompania=" + pedcompania + " and pedguia='" + pedguia + "'";
        return getLista(cad);
    }
    
    public Collection getlistapedidoByGuiaByDespacho(String pedcompania, String pedguia, String despcodsx) {
        String cad = "select pedido.* from pedido, despacho_pedido where pedcodsx = despedpedido and pedcompania=" + pedcompania + " and pedguia='" + pedguia + "' and despeddespacho=" + despcodsx;
        return getLista(cad);
    }
    
    public Collection getlistapedidoByClienteByDespacho(String pedcompania, String succodigo, String despcodsx) {
        String cad = "select pedido.* from pedido, despacho_pedido where pedcodsx = despedpedido and pedcompania=" + pedcompania + " and pedsucursal ='" + succodigo + "' and despeddespacho=" + despcodsx;
        return getLista(cad);
    }

    public String getVolumenPedidoMasterPacks(String pedcodsx) {
        String cad = "select sum(volumen_referencia_pedido) from volumen_referencia_pedido(" + pedcodsx + ")";
        return getCampo(cad);
    }

    public String getMasterPacksPedido(String pedcodsx) {
        String cad = "select sum(total_masterpac_pedido) from total_masterpac_pedido( " + pedcodsx + ")";
        return getCampo(cad);
    }

    public BigDecimal getSaldoReservadoPedido(String procodsx, String tipo) {
        String consulta = "select sum(refpsaldo) from pedido, referencia_pedido where  pedtipo='" + tipo + "' and " + " pedcodsx  = refpnumpedido and refpproducto=" + procodsx;
        String resp = getCampo(consulta);
        return resp == null || resp.equals("") ? BigDecimal.ZERO : new BigDecimal(resp);

    }

    public Collection getlistapedidoFinalizado(String fecha_ini, String fecha_fin) {

        String consulta = " SELECT * FROM pedido where pedfechasistema >='" + fecha_ini + "' and pedfechasistema <='" + fecha_fin + "' and pedestado='FINALIZADO' ";
        return getLista(consulta);
    }
    public String getPedidoCod(String promodelo) {
        String cad = "select procodsx from producto where promodelo ='" + promodelo + "'";
        return getCampo(cad);
    }
    
    
    public String getPedidoCodCia(String cia, String promodelo) {
        String cad = "select procodsx from producto  where procodigocia = " + cia + " and promodelo ='" + promodelo + "'";
        return getCampo(cad);
    }
    
	public Collection reporte_pedido_excel(String codcia, String fechainicial, String fechafinal) {
		String consulta = "select pedfechasistema, pednumpedido, pedfactura, promodelo, refpcantidad, refpvalortotal as declaradoxreferencia, pedsubtotal as valordeclarado, clinombre, ciunombre " +
				"from pedido, cliente, referencia_pedido, producto, ciudad " +
				"where pedcompania='" + codcia + "' and refpnumpedido=pedcodsx and refpproducto=procodsx and ciucodigo=pedciudad and " +
				"pedcliente=clicodsx and pedfechasistema between '" + fechainicial + "' and '" + fechafinal + "' " +
				"order by pedfechasistema, pednumpedido, pedfechasistema";
		return getListaDeListas(consulta);
	}

	public boolean crea_auditoria (String codsx, String accion, String usuario, String antes, String despues)  throws SQLException {
		String cad = "INSERT INTO auditoriapedido(aupedcodsx, aupedaccion, aupedfecha, aupedusuario, aupedpedcodsx, aupedantes, aupeddespues) VALUES ( "
				+ "DEFAULT, "
				+ "'" + accion + "', "
				+ "CURRENT_TIMESTAMP, "
				+ "'" + usuario + "', "
				+ "'" + codsx + "', "
				+ "'" + antes + "', "
				+ "'" + despues + "') ";
		int resp = db.executeUpdate(cad);
		return resp == 0 ? false : true;
	}
	
	public String getNuevoEstado(String pedcodsx) {
		String cad = "select case when pedestado = 'FINALIZADO' and despestado = 'FINALIZADO' then 'DESPACHADO' else pedestado end as nuevoestado\r\n" + 
				"from pedido\r\n" + 
				"left join despacho_pedido on despedpedido = pedcodsx\r\n" + 
				"left join despacho on despcodsx = despeddespacho\r\n" + 
				"where pedcodsx = " + pedcodsx + "\r\n" + 
				"limit 1";
		return getCampo(cad);
	}
}