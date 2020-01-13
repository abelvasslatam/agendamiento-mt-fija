package tdp.backend.mt.fija.main.restclient.updateCustomer;

import java.util.List;

import lombok.Data;
import tdp.backend.mt.fija.main.restclient.updateCustomer.request.Contacts;

@Data
public class UpdateCustomerRequestFront {
	private String tipoCliente;
	private String psiCode;
	private String email;
	private List<Contacts> contacts;
}
