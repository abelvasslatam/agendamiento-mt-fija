package tdp.backend.mt.fija.main.restclient.updateCustomer.request;

import java.util.List;

import lombok.Data;

@Data
public class Body {
	private String psiCode;
	private String email;
	private List<Contacts> contacts;

}
