package util;

public class JsonUtil {
	public static final String SUCESS = "SUCESS";
	public static final String FAIL = "FAIL";
	private String message;
	private String status;
	private Object data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}