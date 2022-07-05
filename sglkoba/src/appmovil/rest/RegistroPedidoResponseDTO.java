package appmovil.rest;

import pedido.entity.referencia_pedido;

public class RegistroPedidoResponseDTO {
	private referencia_pedido refPedido;
	private String idRegistroPedido;
	public referencia_pedido getRefPedido() {
		return refPedido;
	}
	public void setRefPedido(referencia_pedido refPedido) {
		this.refPedido = refPedido;
	}
	public String getIdRegistroPedido() {
		return idRegistroPedido;
	}
	public void setIdRegistroPedido(String idRegistroPedido) {
		this.idRegistroPedido = idRegistroPedido;
	}
	
	
}
