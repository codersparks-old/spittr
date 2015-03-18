package spittr.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class HomeControllerTest {

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
	public void testHome() {
		HomeController controller = new HomeController();
		
		assertEquals("home", controller.home(null));
	}
	
	@Test
	public void testRootPage() {
		try {
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/")).andExpect(view().name("home"));
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHomePage() {
		try {
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
