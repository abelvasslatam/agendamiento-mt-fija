package tdp.backend.mt.fija.main.restclient.updateCustomer;

import tdp.backend.mt.fija.common.client.AbstractClient;
import tdp.backend.mt.fija.common.client.ClientConfig;
import tdp.backend.mt.fija.common.util.UtilMethods;
import tdp.backend.mt.fija.main.restclient.updateCustomer.request.UpdateCustomerRequest;
import tdp.backend.mt.fija.main.restclient.updateCustomer.response.UpdateCustomerResponse;

public class UpdateCustomerClient 
	extends AbstractClient<UpdateCustomerRequest, UpdateCustomerResponse>{
	
	private static final String urlendpoint = "provision/";
	private static final String urlmethod = "update-customer";

	public UpdateCustomerClient(ClientConfig config) {
		super(config);
		this.config = UtilMethods.buildConfigScheduleUpdateCustomer(urlendpoint, urlmethod);
	}

	@Override
	protected String getServiceCode() {
		return "SCHED_UPDATE";
	}

}
