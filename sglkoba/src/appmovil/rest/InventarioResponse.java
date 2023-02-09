package appmovil.rest;

public class InventarioResponse {
	private String inventoryId;
	private String name;
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InventarioResponse(String inventoryId, String name) {
		super();
		this.inventoryId = inventoryId;
		this.name = name;
	}
	
	
	
}
