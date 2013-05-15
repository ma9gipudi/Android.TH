package tradehero.core.net;

public class RestError {

	public RestError(int _reasonCode,String _message,String _response)
	{
		reasonCode = _reasonCode;
		message = _message;
		response = _response;
	}
	private int reasonCode;
	private String message;
	private String response;
	public int getReasoncode() {
		return reasonCode;
	}
	public void setReasoncode(int reasoncode) {
		this.reasonCode = reasoncode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
