import junit.framework.*;

public class TestMath extends TestCase { 
    
  public void testAdd() {
  	int num1 = 3;
  	int num2 = 2;
  	int total = 5;
  	int sum = Math.add(num1, num2);
  	assertEquals(sum, total);
  }
  
  public void testMulitply() {
  	int num1 = 3; 
  	int num2 = 7; 
  	int total = 21;
  	int sum = Math.multiply(num1, num2);
  	assertEquals("Problem with multiply", sum, total);
  }
}