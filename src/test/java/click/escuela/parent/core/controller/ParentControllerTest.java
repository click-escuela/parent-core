package click.escuela.parent.core.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import click.escuela.parent.core.dto.StudentShortDTO;
import click.escuela.parent.core.rest.ParentController;
import click.escuela.parent.core.service.impl.SchoolAdminServiceImpl;

@EnableWebMvc
@RunWith(MockitoJUnitRunner.class)
public class ParentControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ParentController teacherController;

	@Mock
	private SchoolAdminServiceImpl schoolAdminServiceImpl;

	private ObjectMapper mapper;
	private String schoolId = "1234";
	private String parentId = UUID.randomUUID().toString();
	private String studentId = UUID.randomUUID().toString();
	private final static String URL = "/school/{schoolId}/parent/";
	private List<StudentShortDTO> students = new ArrayList<>();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
		mapper = new ObjectMapper().findAndRegisterModules().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		ReflectionTestUtils.setField(teacherController, "schoolAdminServiceImpl", schoolAdminServiceImpl);
		
		StudentShortDTO student = new StudentShortDTO();
		student.setId(studentId);
		student.setName("Anotnio");
		student.setSurname("Liendro");
		students.add(student);
	
		Mockito.when(schoolAdminServiceImpl.getStudentsWithGrades(schoolId, parentId))
				.thenReturn(students);
	}

	@Test
	public void getStudentsWithGradesTests() throws JsonProcessingException, Exception {
		assertThat(mapper.readValue(result(get(URL + "{parentId}/students", schoolId, parentId)),
				new TypeReference<List<StudentShortDTO>>() {}).get(0).getId()).contains(studentId);

		Mockito.when(schoolAdminServiceImpl.getStudentsWithGrades(schoolId, parentId))
		.thenReturn(new ArrayList<>());
		assertThat(mapper.readValue(result(get(URL + "{parentId}/students", schoolId, parentId)),
				new TypeReference<List<StudentShortDTO>>() {})).isEmpty();
	}

	private String result(MockHttpServletRequestBuilder requestBuilder) throws JsonProcessingException, Exception {
		return mockMvc.perform(requestBuilder.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
				.getContentAsString();
	}
}
