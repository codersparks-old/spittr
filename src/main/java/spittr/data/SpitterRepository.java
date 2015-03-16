package spittr.data;

import spittr.Spitter;

public interface SpitterRepository {

	public Spitter save(Spitter s);
	public Spitter findByUsername(String username);
}
