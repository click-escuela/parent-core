package click.escuela.parent.core.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import click.escuela.parent.core.dto.StudentShortDTO;

@FeignClient(name = "grades")
public interface GradeController {

	// GradeController
	@PutMapping(value = "/school/{schoolId}/grade/students")
	public List<StudentShortDTO> getStudentsWithGrades(@PathVariable("schoolId") String schoolId,
			@RequestBody @Validated List<StudentShortDTO> students);
}
