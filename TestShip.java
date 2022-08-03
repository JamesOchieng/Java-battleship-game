import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestShip {
/**
*uug
*/
   @Test public void hasSquare()
   {
      Ship a = new Ship('D',4 ,Direction.VERTICAL,  3);
      a.setViewer(Viewer.OPPONENT);
   
      assertFalse(a.checkShot('A',0));
      assertTrue(a.checkShot('D',4));
      assertTrue(a.checkShot('E',4));
      assertTrue(a.checkShot('F',4));
      assertTrue(a.isSunk());
   
      
   
   }
   @Test public void isSunk()
   {
      Ship a = new Ship('A',4 ,Direction.VERTICAL,  6);
      a.setViewer(Viewer.OPPONENT);
      assertTrue(a.checkShot('A',4));
      assertTrue(a.checkShot('D',4));
      assertTrue(a.checkShot('E',4));
      assertTrue(a.checkShot('F',4));
      assertFalse(a.isSunk());
   
   }
   
   @Test public void getSquare()
   {
      Ship a = new Ship('I', 4, Direction.HORIZONTAL, 5);
      a.setViewer(Viewer.OPPONENT);
      a.checkShot('I',6);
      assertEquals('-', a.getSquare('I',2));
      assertEquals('-', a.getSquare('I',5));
      assertEquals('+', a.getSquare('I',6));
      assertEquals('-', a.getSquare('I',7));
      assertEquals('-', a.getSquare('I',8));
   }
   
   /*@Test public void gridView()
   {
      Ship a = new Ship('C', 4, Direction.VERTICAL, 3);
      assertEquals(" 1 2 3 4 5 6 7 8 9 0\nA - - - - - - - - - -\nB - - - - - - - - - -\nC - - - o - - - - - -\nD - - - o - - - - - -\nE - - - o - - - - - -\nF - - - - - - - - - -\nG - - - - - - - - - -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -\n",a.gridView());
   
   }*/
}
