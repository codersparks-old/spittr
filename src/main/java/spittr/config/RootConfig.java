package spittr.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Configuration
@ComponentScan(basePackages={"spittr"}, excludeFilters={
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
})
public class RootConfig {
	
//	@Bean
//	public SpittleRepository spittleRepository() {
//		return new SpittleRepository() {
//			
//			@Override
//			public List<Spittle> findSpittles(long max, int count) {
//				
//				List<Spittle> spittles = new ArrayList<Spittle>();
//				
//				for(int i = 0; i < count; i++) {
//					
//					spittles.add(new Spittle("Spittle " + i, new Date()));
//				}
//				
//				return spittles;
//			}
//			
//		};
//	}

}
