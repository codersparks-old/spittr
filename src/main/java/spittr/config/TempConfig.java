package spittr.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

@Configuration
public class TempConfig {
	
	@Bean
	public SpittleRepository spittleRepository() {
		return new SpittleRepository() {
			
			@Override
			public List<Spittle> findSpittles(long max, int count) {
				
				List<Spittle> spittles = generateSpittleList(count);
				
				return spittles;
			}

			private List<Spittle> generateSpittleList(int count) {
				List<Spittle> spittles = new ArrayList<Spittle>();
				
				for(int i = 0; i < count; i++) {
					spittles.add(new Spittle("Spittle " + i, new Date()));
				}
				return spittles;
			}
			
			@Override
			public Spittle findOne(long id) {
				
				if(id == 54321) {
					return new Spittle("Random spittle with id 54321", new Date());
				}
				
				if(id == 1) {
					return new Spittle("Hello World from spittle!!!!", Date.from(Instant.MIN));
				}
				
				// Otherwise there is no Spittle
				return null;
			}

			@Override
			public List<Spittle> findRecentSpittles() {
				// TODO Auto-generated method stub
				return generateSpittleList(10);
			}

			@Override
			public void save(Spittle spittle) {
				// Nothing yet
				
			}
		};
	}
	
	
	@Bean
	public SpitterRepository spitterRepository() {
		
		return new SpitterRepository() {
			
			@Override
			public Spitter save(Spitter s) {
				
				double tempId = Math.floor(Math.random() * 1000);
				
				s.setId((long)tempId);
				
				return s;
			}
			
			@Override
			public Spitter findByUsername(String username) {
				
				if(username.equals("jbauer")) {
					return new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
				}
				return null;
			}
		};
	}
	

}
