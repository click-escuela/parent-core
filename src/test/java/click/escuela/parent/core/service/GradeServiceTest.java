package click.escuela.parent.core.service;

import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.parent.core.conector.GradeConnector;
import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.service.impl.GradeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

	@Mock
	private GradeConnector gradeConnector;

	private GradeServiceImpl gradeService = new GradeServiceImpl();
	private String studentId;
	private String schoolId;
	private StudentShortDTO student = new StudentShortDTO();
	private List<StudentShortDTO> students = new ArrayList<>();
	
	@Before
	public void setUp() {

		studentId = UUID.randomUUID().toString();
		schoolId = "1234";
		student.setId(studentId);
		student.setName("Tony");
		student.setSurname("Liendro");
		student.setGrades(new ArrayList<>());
		students.add(student);

		ReflectionTestUtils.setField(gradeService, "gradeConnector", gradeConnector);

	}

	@Test
	public void whenGetStudentsWithGradesIsOk() {
		gradeService.getStudentsWithGrades(schoolId, students);
		verify(gradeConnector).getStudentsWithGrades(schoolId, students);
	}
}
