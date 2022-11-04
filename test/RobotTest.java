import org.junit.*;
import static org.junit.Assert.*;

import factory.Robot;
import factory.util.*;
import jdk.jfr.Timestamp;

public class RobotTest {
  
    @Test
    public void robotCarryNoBoxWhenCreated() {
	    Robot robbie = new Robot();
	    assertFalse(robbie.carryingBox());
    }

    @Test
    public void robotCanTakeBoxIfNotCarrying() {
        Robot robbie = new Robot() ;
        Box someBox = new Box(10) ;
        assertFalse(robbie.carryingBox());
        robbie.take(someBox) ;
    }

    @Test
    public void robotCannotTakeBoxIfAlreadyCarrying() {
        Box box10 = new Box(10) ;
        Box box20 = new Box(20) ;
        Robot robbie = new Robot();
        robbie.take(box10) ;
        assertTrue(robbie.carryingBox()) ;
        robbie.take(box20) ;
        assertSame(box10,robbie.getCarriedBox()) ;
    }

    @Test
    public void robotCarriesABoxAndPutsItOnTheConveyer() {
        Robot robbie = new Robot() ;
        ConveyerBelt conveyer = new ConveyerBelt(40) ;
        Box box10 = new Box(10) ;
        robbie.take(box10) ;
        assertTrue(robbie.carryingBox());
        assertTrue(robbie.putOn(conveyer)) ;

    }

    @Test
    public void robotCannotPutABoxBecauseDoNotCarryABox() {
        Robot robbie = new Robot() ;
        ConveyerBelt conveyer = new ConveyerBelt(40) ;
        Box box10 = new Box(10) ;
        assertFalse(robbie.putOn(conveyer)) ;
    }

    @Test
    public void robotCannotPutABoxOnTheConveyerBecauseTheBoxIsTooHeavy() {
        Robot robbie = new Robot() ;
        ConveyerBelt conveyer = new ConveyerBelt(40) ;
        Box box50 = new Box(50) ;
        robbie.take(box50) ;
        assertFalse(robbie.putOn(conveyer)) ;
    }

    @Test
    public void robotCannotPutABoxOnTheConveyerBecauseTheConveyerIsFull() {
        Robot robbie = new Robot() ;
        ConveyerBelt conveyer = new ConveyerBelt(400) ;
        Box box10 = new Box(10) ;
        Box box20 = new Box(20) ;
        Box box30 = new Box(30) ;
        robbie.take(box10) ;
        assertTrue(robbie.putOn(conveyer)) ;
        robbie.take(box20) ;
        assertTrue(robbie.putOn(conveyer)) ;
        robbie.take(box30) ;
        assertFalse(robbie.putOn(conveyer)) ;
    }
    
}
