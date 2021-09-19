package click.escuela.parent.core.connector;

import static org.mockito.Mockito.verify;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.parent.core.conector.SchoolAdminConnector;
import click.escuela.parent.core.feign.SchoolAdminController;

@RunWith(MockitoJUnitRunner.class)
public class SchoolAdminConnectorTest {

	@Mock
	private SchoolAdminController schoolAdminController;

	private SchoolAdminConnector schoolAdminConnector = new SchoolAdminConnector();
	private String parentId = UUID.randomUUID().toString();
	private String schoolId = "1234";

	@Before
	public void setUp() {
		ReflectionTestUtils.setField(schoolAdminConnector, "schoolAdminController", schoolAdminController);
	}

	@Test
	public void whenGetCourseStudentsOk(){
		schoolAdminConnector.getStudentsByParentId(schoolId, parentId, false);
		verify(schoolAdminController).getStudentsByParentId(schoolId, parentId, false);
	}

}
