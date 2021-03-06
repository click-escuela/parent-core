package click.escuela.parent.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class GradeDTO {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "subject")
	private String subject;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "number")
	private Integer number;
	
}
