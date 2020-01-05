package tdp.backend.mt.fija.common.client;

public class ClientConfig {
	private String url;
	private String apiId;
	private String apiSecret;
	private String operation;
	private String destination;
	private String authorization;
	
	
	public ClientConfig(){
		super();
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
}
