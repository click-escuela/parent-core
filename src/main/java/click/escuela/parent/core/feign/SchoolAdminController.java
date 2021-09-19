package click.escuela.parent.core.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import click.escuela.parent.core.dto.StudentShortDTO;

@FeignClient(name = "school-admin")
public interface SchoolAdminController {

	@GetMapping(value = "/school/{schoolId}/student/parent/{parentId}")
	public List<StudentShortDTO> getStudentsByParentId(@PathVariable("schoolId") String schoolId,
			@PathVariable("parentId") String parentId, @RequestParam("fullDetail") Boolean fullDetail);

}
