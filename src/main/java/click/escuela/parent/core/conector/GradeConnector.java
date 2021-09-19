package click.escuela.parent.core.conector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.feign.GradeController;

@Service
public class GradeConnector {

	@Autowired
	private GradeController gradeController;

	public List<StudentShortDTO> getStudentsWithGrades(String schoolId,List<StudentShortDTO> students) {
		return gradeController.getStudentsWithGrades(schoolId, students);
	}
}
