package spittr.web;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {

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
	public void testShouldShowRecentSpittles() {
		try {
			
			List<Spittle> expectedSpittles = createSpittleList(20);

			// We are going to mock the repository interface that would be used
			// to get our spittles
			SpittleRepository mockRepository = mock(SpittleRepository.class);

			// When the method is called with the parameters then we return the
			// list of spittles
			when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(
					expectedSpittles);

			SpittleController controller = new SpittleController(mockRepository);

			MockMvc mockMvc = standaloneSetup(controller).setSingleView(
					new InternalResourceView("/WEB-INF/views/spittles.jsp"))
					.build();

			mockMvc.perform(get("/spittles"))
					.andExpect(view().name("spittles"))
					.andExpect(model().attributeExists("spittleList"))
					.andExpect(
							model().attribute("spittleList",
									hasItems(expectedSpittles.toArray())));

		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void shouldShowPagedSpittles() {
		try {

			List<Spittle> expectedSpittles = createSpittleList(50);
			SpittleRepository mockRepository = mock(SpittleRepository.class);
			when(mockRepository.findSpittles(238900, 50)).thenReturn(
					expectedSpittles);

			SpittleController controller = new SpittleController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).setSingleView(
					new InternalResourceView("/WEB-INF/views/spittles.jsp"))
					.build();
			mockMvc.perform(get("/spittles?max=238900&count=50"))
				.andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));

		} catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSpittle() {
		try{
			Spittle expectedSpittle = new Spittle("Hello", new Date());
			SpittleRepository mockRepository = mock(SpittleRepository.class);
			when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
			
			SpittleController controller = new SpittleController(mockRepository);
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/spittles/12345"))
				.andExpect(view().name("spittle"))
				.andExpect(model().attributeExists("spittle"))
				.andExpect(model().attribute("spittle", expectedSpittle));
		}catch (Exception e) {
			fail("Exception caught: " + e.getClass().getCanonicalName() + ": "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}

		return spittles;
	}

}
