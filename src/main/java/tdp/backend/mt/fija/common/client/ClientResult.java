package tdp.backend.mt.fija.common.client;

import lombok.Data;
import tdp.backend.mt.fija.main.fija.model.ServiceCallEventsFija;
import tdp.backend.mt.fija.main.mt.model.ServiceCallEventsMt;

@Data
public class ClientResult<T> {

	private T result;
	private boolean success;
	
	private ServiceCallEventsMt sceMt;
	private ServiceCallEventsFija sceFija;
	

	public ClientResult() {
		super();
		this.success = false;
		this.sceMt = new ServiceCallEventsMt();
		this.sceFija = new ServiceCallEventsFija();
	}

}