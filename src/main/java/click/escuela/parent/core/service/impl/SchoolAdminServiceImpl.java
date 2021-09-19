package click.escuela.parent.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.parent.core.conector.SchoolAdminConnector;
import click.escuela.parent.core.dto.StudentShortDTO;

@Service
public class SchoolAdminServiceImpl {

	@Autowired
	private SchoolAdminConnector schoolAdminConnector;
	
	@Autowired
	private GradeServiceImpl gradeServiceImpl;
	
	public List<StudentShortDTO> getStudentsWithGrades(String schoolId, String parentId){
		List<StudentShortDTO> students = schoolAdminConnector.getStudentsByParentId(schoolId, parentId, false);
		if(!students.isEmpty()) {
			students = gradeServiceImpl.getStudentsWithGrades(schoolId, students); 
		}
		return students;
	}
	
	public List<StudentShortDTO> getStudentsWithBills(String schoolId, String parentId){
		List<StudentShortDTO> students = schoolAdminConnector.getStudentsByParentId(schoolId, parentId, true);
		students.stream().forEach(student -> student.setGrades(null));
		return students;
	}

}
