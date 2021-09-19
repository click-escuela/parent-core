package click.escuela.parent.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.parent.core.conector.GradeConnector;
import click.escuela.parent.core.dto.StudentShortDTO;

@Service
public class GradeServiceImpl {

	@Autowired
	private GradeConnector gradeConnector;
	
	public List<StudentShortDTO> getStudentsWithGrades(String schoolId,List<StudentShortDTO> students) {
		return gradeConnector.getStudentsWithGrades(schoolId, students);
	}
}
