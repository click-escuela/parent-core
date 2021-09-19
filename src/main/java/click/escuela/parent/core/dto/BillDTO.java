package click.escuela.parent.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import click.escuela.parent.core.enumerator.PaymentStatus;
import lombok.Builder;

@Builder
public class BillDTO {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "month")
	private Integer month;
	
	@JsonProperty(value = "year")
	private Integer year;
	
	@JsonProperty(value = "amount")
	private Double amount;

	@JsonProperty(value = "file")
	private String file;
	
	@JsonProperty(value = "status")
	private PaymentStatus status;
	
}
