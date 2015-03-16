package spittr.web;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldShowRegistration() {
		try {

			SpitterRepository mockRepository = mock(SpitterRepository.class);
			
			SpitterController controller = new SpitterController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).build();

			mockMvc.perform(get("/spitter/register")).andExpect(
					view().name("registerForm"));
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void shouldProcessRegistration() {
		try {

			SpitterRepository mockRepository = mock(SpitterRepository.class);
			Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			when(mockRepository.save(unsaved)).thenReturn(saved);
			
			SpitterController controller = new SpitterController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(post("/spitter/register")
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")
				.param("email", "jbauer@ctu.gov"))
				.andExpect(redirectedUrl("/spitter/jbauer"));
			
			verify(mockRepository, atLeastOnce()).save(unsaved);
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void processRegistrationWithErrors() {
		try {

			SpitterRepository mockRepository = mock(SpitterRepository.class);
			Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			when(mockRepository.save(unsaved)).thenReturn(saved);
			
			SpitterController controller = new SpitterController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(post("/spitter/register")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")
				.param("email", "jbauer@ctu.gov"))
				.andExpect(view().name("registerForm"));
			
			
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void processRegistrationWithErrorsMissingEmail() {
		try {

			SpitterRepository mockRepository = mock(SpitterRepository.class);
			Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
			when(mockRepository.save(unsaved)).thenReturn(saved);
			
			SpitterController controller = new SpitterController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(post("/spitter/register")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")
				.param("firstName", "Jack"))
				.andExpect(view().name("registerForm"));
			
			
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
