package click.escuela.parent.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.parent.core.conector.SchoolAdminConnector;
import click.escuela.parent.core.dto.BillDTO;
import click.escuela.parent.core.dto.GradeDTO;
import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.enumerator.PaymentStatus;
import click.escuela.parent.core.service.impl.GradeServiceImpl;
import click.escuela.parent.core.service.impl.SchoolAdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SchoolAdminServiceTest {

	@Mock
	private SchoolAdminConnector schoolAdminConnector;
	
	@Mock
	private GradeServiceImpl gradeServiceImpl;

	private SchoolAdminServiceImpl schoolAdminServiceImpl = new SchoolAdminServiceImpl();
	private String parentId = UUID.randomUUID().toString();
	private String schoolId = "1234";
	private String studentId = UUID.randomUUID().toString();
	private StudentShortDTO student = new StudentShortDTO();
	private GradeDTO grade;
	private BillDTO bill;
	private List<StudentShortDTO> students = new ArrayList<>();

	@Before
	public void setUp() {
	
		student.setId(studentId);
		student.setName("Tony");
		student.setSurname("Liendro");
		student.setGrades(new ArrayList<>());
		students.add(student);
		grade = GradeDTO.builder().id(UUID.randomUUID().toString()).name("Examen").subject("Matematica")
				.type("HOMEWORK").number(10).build();
		List<GradeDTO> grades = new ArrayList<>();
		grades.add(grade);
		student.setGrades(grades);
		bill =BillDTO.builder().id(UUID.randomUUID().toString()).year(2021).month(6).status(PaymentStatus.PENDING)
				.file("Mayo").amount((double) 12000).build();
		List<BillDTO> bills = new ArrayList<>();
		bills.add(bill);
		student.setBills(bills);
		
		when(schoolAdminConnector.getStudentsByParentId(schoolId, parentId, false)).thenReturn(students);
		when(schoolAdminConnector.getStudentsByParentId(schoolId, parentId, true)).thenReturn(students);
		ReflectionTestUtils.setField(schoolAdminServiceImpl, "schoolAdminConnector", schoolAdminConnector);
		ReflectionTestUtils.setField(schoolAdminServiceImpl, "gradeServiceImpl", gradeServiceImpl);
	}

	@Test
	public void getStudentsWithGrades(){
		schoolAdminServiceImpl.getStudentsWithGrades(schoolId,parentId);
		verify(schoolAdminConnector).getStudentsByParentId(schoolId, parentId, false);
		verify(gradeServiceImpl).getStudentsWithGrades(schoolId, students);
	}
	
	@Test
	public void getStudentsWithGradesEmpty(){
		when(schoolAdminConnector.getStudentsByParentId(schoolId, parentId, false)).thenReturn(new ArrayList<>());

		List<StudentShortDTO> students = schoolAdminServiceImpl.getStudentsWithGrades(schoolId,parentId);
		assertThat(students).isEmpty();
	}
	
	@Test
	public void  getStudentsWithBills(){
		schoolAdminServiceImpl.getStudentsWithBills(schoolId,parentId);
		verify(schoolAdminConnector).getStudentsByParentId(schoolId, parentId, true);
	}
	
}
