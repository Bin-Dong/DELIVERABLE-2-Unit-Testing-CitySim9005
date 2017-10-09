import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.*;
import java.util.ArrayList;

public class CitySim9005Test{
	City _c;
	
	@Before
	public void setup(){
		_c = new City();
	}
	
	//Testing to see if there would be an error if we were to start outside of the 4 destination location
	//This test is to check if it is possible to start outside of the city.
	//All valid starting locations are from int 0-3. Thus calling _c.setStartingLocation(5)
	//Should return false
	@Test
	public void testInvalidStartingLocation(){
		for(int i = 0; i<4; i++){
		_c.addToList(Mockito.mock(Location.class));
		}
		boolean result = _c.setStartingLocation(5);
		assertEquals(false,result);
	}
	
	//This test is to see if it allows VALID starting location. 
	//This should return true as we're starting from location 1. 
	//Should return true
	@Test
	public void testValidStartingLocation(){
		for(int i = 0; i<4; i++){
		_c.addToList(Mockito.mock(Location.class));
		}
		boolean result = _c.setStartingLocation(2);
		assertEquals(true,result);
	}
	
	//This test is to check if all the locations CAN be connected at least one way.
	//For example, A's left is B and A's right is also B. 
	//However, B's left is A but B's right is NOT A.
	//This should return true as it is a valid connection
	//Should return true
	@Test
	public void testValidConnection(){
		ArrayList<Location> _location = new ArrayList<Location>();
		
		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocation()).thenReturn(p2);
		Mockito.when(p1.getRightLocation()).thenReturn(p2);
		Mockito.when(p2.getLeftLocation()).thenReturn(p1);
		Mockito.when(p2.getRightLocation()).thenReturn(null);
		
		boolean result = _c.isAllConnected();
		assertEquals(true,result);
	}
	
	//This test is the same as testValidConnection()
	//The only difference here is now B is connected to A both ways just like
	//A is connected to B both ways.
	//Should return true
	@Test
	public void testValidConnect2(){
		ArrayList<Location> _location = new ArrayList<Location>();
		
		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocation()).thenReturn(p2);
		Mockito.when(p1.getRightLocation()).thenReturn(p2);
		Mockito.when(p2.getLeftLocation()).thenReturn(p1);
		Mockito.when(p2.getRightLocation()).thenReturn(p1);
		
		
		boolean result = _c.isAllConnected();
		assertEquals(true,result);
		}
		
	//This test is a continuation of testValidConnect() and testValidConnection2().
	//Instead, we will have A connect to B but 
	//B will not be connected to A at all. 
	//Should return false.
	@Test
	public void invalidConnection(){
		ArrayList<Location> _location = new ArrayList<Location>();
		
		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocation()).thenReturn(p2);
		Mockito.when(p1.getRightLocation()).thenReturn(p2);
		Mockito.when(p2.getLeftLocation()).thenReturn(null);
		Mockito.when(p2.getRightLocation()).thenReturn(null);
		
		boolean result = _c.isAllConnected();
		assertEquals(false,result);
		}
		
	//This test tests 2 things. First is if the starting location is valid. Second is stated below.		
	//Testing the return string when making a goLeft() method call
	//This test is designed to check if we can goLeft on a valid location / way.
	//Should return a valid string that meets the requirements
	@Test
	public void goLeftTest(){

		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocationName()).thenReturn("library");
		Mockito.when(p2.getCurrentLocation()).thenReturn("library");
		Mockito.when(p1.getCurrentLocation()).thenReturn("coffee");
		Mockito.when(p1.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p2.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p1.getRightLocationName()).thenReturn("c");
		Mockito.when(p2.getLeftLocationName()).thenReturn("b");
		Mockito.when(p2.getRightLocationName()).thenReturn("a");
		
		_c.connectCity();
	
		boolean result = _c.setStartingLocation(0);
		assertTrue(result);
		String travel = _c.goLeft();
		String wantedResult = "heading from coffee to library via Laboon St.";
		assertEquals(travel,wantedResult);
	}
	
	//This test tests 2 things. First is if the starting location is valid. Second is stated below.
	//Testing the return string when making a goRight() method call
	//This code is a copy/paste from goLeftTest(). 
	//The two string, wantedResult and Travel, should not be equal as the Right Location
	//Name isnt as specified.
	@Test
	public void goRightTest(){

		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocationName()).thenReturn("library");
		Mockito.when(p1.getCurrentLocation()).thenReturn("coffee");
		Mockito.when(p1.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p1.getRightLocationName()).thenReturn("Cathedral");
		
		Mockito.when(p2.getCurrentLocation()).thenReturn("Library");
		Mockito.when(p2.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p2.getLeftLocationName()).thenReturn("b");
		Mockito.when(p2.getRightLocationName()).thenReturn("a");
		
		_c.connectCity();
	
		boolean result = _c.setStartingLocation(0);
		
		assertTrue(result);
		
		String travel = _c.goRight();
		String wantedResult = "heading from coffee to library via Laboon St.";
		assertFalse(travel.equals(wantedResult));
	}
	
	//This test tests 2 things. First is if the starting location is valid. Second is stated below.
	//Testing the return string when making a goRight() method call. 
	//The two string, wantedResult and Travel, SHOULD BE equal as the Right Location
	//Name IS as specified.	
	@Test
	public void goRightTest2(){

		Location p1 = Mockito.mock(Location.class);
		Location p2 = Mockito.mock(Location.class);
		
		_c.addToList(p1);
		_c.addToList(p2);
		
		Mockito.when(p1.getLeftLocationName()).thenReturn("library");
		Mockito.when(p1.getCurrentLocation()).thenReturn("coffee");
		Mockito.when(p1.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p1.getRightLocationName()).thenReturn("Cathedral");
		
		Mockito.when(p2.getCurrentLocation()).thenReturn("Library");
		Mockito.when(p2.getLeftStreet()).thenReturn("Laboon St.");
		Mockito.when(p2.getLeftLocationName()).thenReturn("b");
		Mockito.when(p2.getRightLocationName()).thenReturn("diner");
		
		_c.connectCity();
	
		boolean result = _c.setStartingLocation(0);
	
		assertTrue(result);
		
		String travel = _c.goRight();
		String wantedResult = "heading from coffee to diner via Laboon St.";
		assertFalse(travel.equals(wantedResult));
	}
	
	
	
	//Testing if we can get the outside city's name, 
	//i.e., Philadelphia or Cleveland
	@Test
	public void testOutsideCityNameReturn(){
		Location p1 = Mockito.mock(Location.class);
		_c.current = p1;
		Mockito.when(p1.getLeftLocationName()).thenReturn("Cleveland");
		String result = _c.getOutsideCityName();
		String wantedResult = "Cleveland";
		assertEquals(result,wantedResult);
	}
	
	//Testing whether it can return currentLocation
	@Test
	public void testCurrentLocationReturn(){
		Location p1 = Mockito.mock(Location.class);
		_c.current = p1;
		Mockito.when(p1.getCurrentLocation()).thenReturn("Library");
		assertEquals(_c.getCurrentLocation(),"Library");
	}
}