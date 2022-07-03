package appmovil.rest;


public class RegistroPedidoResponseDTO {
	private appmovil.rest.referencia_pedido refPedido;
	private String idRegistroPedido;
	public appmovil.rest.referencia_pedido getRefPedido() {
		return refPedido;
	}
	public void setRefPedido(appmovil.rest.referencia_pedido refPedido) {
		this.refPedido = refPedido;
	}
	public String getIdRegistroPedido() {
		return idRegistroPedido;
	}
	public void setIdRegistroPedido(String idRegistroPedido) {
		this.idRegistroPedido = idRegistroPedido;
	}
	
	
}
