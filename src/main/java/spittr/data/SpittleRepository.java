package spittr.data;

import java.util.List;
import spittr.Spittle;

public interface SpittleRepository {
	Spittle findOne(long id);
	List<Spittle> findSpittles(long max, int count);
}
