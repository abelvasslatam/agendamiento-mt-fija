package tdp.backend.mt.fija.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class Response<E> {
	@JsonInclude(Include.NON_NULL)
	private String responseCode;
	@JsonInclude(Include.NON_NULL)
	private String responseMessage;
	@JsonInclude(Include.NON_NULL)
	private E responseData;

}
