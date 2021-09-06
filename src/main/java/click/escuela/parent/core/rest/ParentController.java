package click.escuela.parent.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.service.impl.SchoolAdminServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/school/{schoolId}/parent")
public class ParentController {
	
	@Autowired
	private SchoolAdminServiceImpl schoolAdminServiceImpl;
 
	@Operation(summary = "Get courses with grades", responses = {
			@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentShortDTO.class))) })
	@GetMapping(value = "/{parentId}/students", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<StudentShortDTO>> getCoursesWithGrades(@PathVariable("schoolId") String schoolId,
			@PathVariable("parentId") String parentId){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(schoolAdminServiceImpl.getStudentsWithGrades(schoolId, parentId));
	}
}
