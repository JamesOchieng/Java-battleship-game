/**
*Test class used to test the Board class
*
*@auther James Ochieng
*
*@version 3/22/2020
*/

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BoardTestTest {

   @Test public void UserInput()
   {
      Ship[] ships = new Ship[5];
      ships[0] = new Ship('A',3,Direction.HORIZONTAL,5);
      ships[1] = new Ship('J',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('D',0,Direction.VERTICAL,3);
      ships[3] = new Ship('F',2,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      Board me = new Board(ships);
   
   }

   @Test public void randomConstructor01()
   {
      Ship[] ships = new Ship[5];
      ships[0] = new Ship('A',0,Direction.VERTICAL,5);
      ships[1] = new Ship('J',1,Direction.HORIZONTAL,4);
      ships[2] = new Ship('D',5,Direction.VERTICAL,3);
      ships[3] = new Ship('F',2,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.HORIZONTAL,2);
      Board rand1 = new Board(ships);
      boolean a;
      boolean b;
      boolean c;
      assertFalse( a = rand1.hasShip('C',1));
      assertTrue( b = rand1.hasShip('C',0));
      assertTrue( c = rand1.hasShip('C',5));
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void throwsException()
   {
      Ship[] ships = new Ship[5];
      ships[0] = new Ship('A',0,Direction.VERTICAL,5);
      ships[1] = new Ship('J',1,Direction.HORIZONTAL,4);
      ships[2] = new Ship('D',5,Direction.VERTICAL,3);
      ships[3] = new Ship('F',2,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.HORIZONTAL,2);
      Board rand1 = new Board(ships);
   
      boolean a = rand1.hasShip('C',11);
   
   }
   
   @Test public void gridView02() {
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',6,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('B',2,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
   
      Board me = new Board(ships);
   
      assertEquals("  1 2 3 4 5 6 7 8 9 0\nA - - - - - 0 0 0 0 0\nB - 3 - - - - - - - 2\nC - 3 - - 4 - - - - 2\nD - 3 - - 4 - - - - 2\nE - - - - - - - - - -\nF - - - - - - - - - -\nG - - - - - 1 1 1 1 -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -\n",me.shipView());
   
   }
}
