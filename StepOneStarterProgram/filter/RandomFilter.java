package filter;

import java.util.Random;

public class RandomFilter implements Filter {
	@Override
	public boolean satisfies(String id) {
		Random rand = new Random();
		return rand.nextBoolean();
	}

}
