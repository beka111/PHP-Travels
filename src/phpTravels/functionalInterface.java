package phpTravels;

import java.util.Arrays;
import java.util.List;

public class functionalInterface {
	public static void main(String[]args) {
		List<String> list=Arrays.asList("Hello","World","I","am", "doing","good");
		list.stream().filter(x->!x.equalsIgnoreCase("am")).map(x->x.toLowerCase()).forEach(System.out::println);
		
		System.out.println(list.stream().count());
		
	}

}
