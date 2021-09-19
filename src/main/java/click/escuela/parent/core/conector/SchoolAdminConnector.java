package click.escuela.parent.core.conector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.feign.SchoolAdminController;

@Service
public class SchoolAdminConnector {
	
	@Autowired
	private SchoolAdminController schoolAdminController;
	
	public List<StudentShortDTO> getStudentsByParentId(String schoolId, String parentId,  Boolean fullDetail){
		return schoolAdminController.getStudentsByParentId(schoolId, parentId, fullDetail);
	}
}
