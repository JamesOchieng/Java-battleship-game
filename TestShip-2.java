 /**
*Test class used to test the ship class
*
* @auther James Ochieng
* @version 2 2/9/2020
*/

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestShip {

   @Test public void hasSquare() {
      Ship a = new Ship('A',0,Direction.VERTICAL, 5);
      assertTrue(a.hasSquare('A',0));
      assertTrue(a.hasSquare('B',0));
      assertFalse(a.hasSquare('j',0));
      assertTrue(a.hasSquare('E',0));
      assertFalse(a.hasSquare('F',0));
   }
   @Test public void sinkIt() {
      Ship a = new Ship('D',3,Direction.HORIZONTAL, 5);
      assertFalse(a.checkShot('A',0));
      assertTrue(a.checkShot('D',7));
      assertTrue(a.checkShot('D',5));
      assertFalse(a.isSunk());
   
   }  
   @Test(expected = IllegalArgumentException.class)
   public void offgrid02() {
      Ship a = new Ship('F',7,Direction.HORIZONTAL, 5); 
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void invalidConstruct01() {
      Ship a = new Ship('q',11,Direction.VERTICAL, 3); 
   }
   
   @Test public void oppon() {
      Ship a = new Ship('E',2,Direction.HORIZONTAL, 6);
      assertFalse(a.checkShot('J',6));
      assertTrue(a.checkShot('E',6));
      assertFalse(a.checkShot('J',8));
      assertFalse(a.isSunk());
      assertEquals('+',a.getSquare('E',6));
      a.setViewer(Viewer.OPPONENT);   
      assertFalse(a.isSunk()); 
   }

   @Test public void getSquareOpponent() {
      Ship a = new Ship('D',5,Direction.VERTICAL, 5);
      assertFalse(a.checkShot('A',6));
      assertTrue(a.checkShot('D',5));
      assertTrue(a.checkShot('E',5));
      assertTrue(a.checkShot('F',5));
      assertTrue(a.checkShot('G',5));
      assertTrue(a.checkShot('H',5));
      assertEquals('-',a.getSquare('A',6));
      assertEquals('x',a.getSquare('D',5));
      assertEquals('x',a.getSquare('E',5));
      assertEquals('x',a.getSquare('F',5));
      assertEquals('x',a.getSquare('G',5));
      assertEquals('x',a.getSquare('H',5));
      assertTrue(a.isSunk());
   
   }
   @Test public void defaultConstructor01() {
      Ship a = new Ship();
      assertEquals("PLAYER[r=A,c=1(o)],[r=A,c=2(o)]",a.toString());
      assertEquals("  1 2 3 4 5 6 7 8 9 0\nA o o - - - - - - - -\nB - - - - - - - - - -\nC - - - - - - - - - -\nD - - - - - - - - - -\nE - - - - - - - - - -\nF - - - - - - - - - -\nG - - - - - - - - - -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -\n",a.gridView());
   }
   
   @Test public void gridView02() {
      Ship a = new Ship('D',3,Direction.VERTICAL, 3);
      a.setViewer(Viewer.OPPONENT);
      assertEquals("OPPONENT[r=D,c=3(-)],[r=E,c=3(-)],[r=F,c=3(-)]",a.toString());
      assertEquals("  1 2 3 4 5 6 7 8 9 0\nA - - - - - - - - - -\nB - - - - - - - - - -\nC - - - - - - - - - -\nD - - - - - - - - - -\nE - - - - - - - - - -\nF - - - - - - - - - -\nG - - - - - - - - - -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -\n",a.gridView());
      assertTrue(a.checkShot('E',3));
      assertEquals("OPPONENT[r=D,c=3(-)],[r=E,c=3(+)],[r=F,c=3(-)]",a.toString());
      assertEquals("  1 2 3 4 5 6 7 8 9 0\nA - - - - - - - - - -\nB - - - - - - - - - -\nC - - - - - - - - - -\nD - - - - - - - - - -\nE - - + - - - - - - -\nF - - - - - - - - - -\nG - - - - - - - - - -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -\n",a.gridView());
   
   
   }
}
