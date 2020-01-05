package tdp.backend.mt.fija.common.client;

public class ClientResult<T> {

	private T result;
	private boolean success;

	public ClientResult() {
		super();
		this.success = false;
	}


	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}