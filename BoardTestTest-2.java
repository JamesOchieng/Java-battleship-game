/**
*Test class used to test the Board class
*
*@auther James Ochieng
*
*@version 4/8/2020
*/

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BoardTestTest 
{
/*
 Checks if the getShip method works properly*/

   @Test public void getShip()
   {
      Board newBoard;
      Ship[] ships = new Ship[5];
      Ship goal;
      
      ships[0] = new Ship('C',6,Direction.VERTICAL,5);
      ships[1] = new Ship('F',1,Direction.VERTICAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('J',8,Direction.HORIZONTAL,3);
      ships[4] = new Ship('E',8,Direction.HORIZONTAL,2);
      
      newBoard = new Board(ships);
      goal = newBoard.getShip(0);
      assertEquals("Ship 0 has no return",ships[0],goal);
      goal = newBoard.getShip(1);
      assertEquals("Ship 1 has no return",ships[1],goal);
      goal = newBoard.getShip(2);
      assertEquals("Ship 2 has no return",ships[2],goal);
   }
   
   /*
   Determines if the hasShip method works properly
   */
   @Test public void conatinsShip()
   {
      Board newBoard;
      Ship[] ships = new Ship[5];
      Ship goal;
      
      ships[0] = new Ship('C',6,Direction.VERTICAL,5);
      ships[1] = new Ship('F',1,Direction.VERTICAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('J',8,Direction.HORIZONTAL,3);
      ships[4] = new Ship('E',8,Direction.HORIZONTAL,2);
   
      newBoard = new Board(ships);
      assertTrue("Contains value D,6", newBoard.hasShip('D',6));
      assertTrue("Contains value J,9", newBoard.hasShip('J',9));
      assertTrue("Contains value E,8", newBoard.hasShip('E',8));
   
      assertFalse("Contains value G,3", newBoard.hasShip('G',3));
      assertFalse("Contains value D,4", newBoard.hasShip('D',4));
      assertFalse("Contains value I,7", newBoard.hasShip('I',7));
   }
   
   /*
   Checks to see if we can add the ships on the board,
   and will catch any invalid ships.
   */
   @Test public void invalidBoard()
   {
      Board newBoard;
      Ship[] ships = new Ship[5];
      Ship goal;
      
      ships[0] = new Ship('C',6,Direction.VERTICAL,5);
      ships[1] = new Ship('F',1,Direction.VERTICAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('J',8,Direction.HORIZONTAL,3);
      ships[4] = new Ship('E',8,Direction.HORIZONTAL,2);
   
      boolean conc;
      try{
         newBoard = new Board(ships);
         conc = false;
      }catch(IllegalArgumentException e)
      {
         conc = true;
      }
      assertFalse("Ship is off grid", conc);
   }
   /*
   Determines if the hasShip method works properly,
   it will check for invalid ships
   */
   @Test public void lookShip()
   {
      Board newBoard;
      Ship[] ships = new Ship[5];
      Ship goal;
      
      ships[0] = new Ship('C',6,Direction.VERTICAL,5);
      ships[1] = new Ship('F',1,Direction.VERTICAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('J',8,Direction.HORIZONTAL,3);
      ships[4] = new Ship('E',8,Direction.HORIZONTAL,2);
   
      newBoard = new Board(ships);
      try{
         newBoard.hasShip('A', 5);
      }catch(IllegalArgumentException e)
      {
         System.out.println("No ship found");
      }
      try{
         newBoard.hasShip('B', 0);
      }catch(IllegalArgumentException e)
      {
         System.out.println("No ship found");
      }
      try{
         newBoard.hasShip('E', 8);
      }catch(IllegalArgumentException e)
      {
         System.out.println("No ship found");
      }
   
   }
   /*
   Determines if the shipView method works, and compares 
   it to a string
   */
   
   @Test public void shipView()
   {
      Board newBoard;
      Ship[] ships = new Ship[5];
      Ship goal;
      
      ships[0] = new Ship('C',6,Direction.VERTICAL,5);
      ships[1] = new Ship('F',1,Direction.VERTICAL,4);
      ships[2] = new Ship('B',0,Direction.VERTICAL,3);
      ships[3] = new Ship('J',8,Direction.HORIZONTAL,3);
      ships[4] = new Ship('E',8,Direction.HORIZONTAL,2);
   
      newBoard = new Board(ships);
      assertEquals("  1 2 3 4 5 6 7 8 9 0\nA - - - - - - - - - -\nB - - - - - - - - - 2\nC - - - - - 0 - - - 2\nD - - - - - 0 - - - 2\nE - - - - - 0 - 4 4 -\nF 1 - - - - 0 - - - -\nG 1 - - - - 0 - - - -\nH 1 - - - - - - - - -\nI 1 - - - - - - - - -\nJ - - - - - - - 3 3 3\n",newBoard.shipView());
   
   }
   @Test public void randomBoard()
   {
      Board rand;
      rand = new Board();
      System.out.println(rand.shipView());
   }
   
}
