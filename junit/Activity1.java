


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Activity1 {
	static ArrayList<String> list;

	@BeforeClass
	public static void setUp(){
		list = new ArrayList<String>();
		list.add("abc");
		list.add("xyz");
	}
	@Test
	public void insertTest() {
		assertEquals(2, list.size(), "Wrong size");
		list.add(2, "Test1");
		assertEquals(3, list.size(), "Wrong size");
		assertEquals("abc", list.get(0), "Wrong element");
		assertEquals("Test1", list.get(2), "Wrong element");
		assertEquals("xyz", list.get(1), "Wrong element");	
	}
	@Test

	public void replaceTest() {
		list.set(1, "Test1");
		assertEquals(2, list.size(), "Wrong size");
		assertEquals("abc", list.get(0), "Wrong element");
		assertEquals("Test1", list.get(1), "Wrong element");
	}
}