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

   private static int editDistance(String s1, String s2) {  
      int m = s1.length();  
      int n = s2.length();
      int i,j;  
        // for all i, j, dp[i][j] will hold the edit distance between the first  
        // i characters of source string and first j characters of target string  
      int [][] dp = new int[m + 1][n + 1];  
      for ( i = 0; i < m + 1; i++)
         for ( j = 0; j < n + 1; j++)
            dp[i][j] = 0;
        
     // source prefixes can be transformed into empty string by  
     // by deleting all of the characters  
      for ( i = 1; i <= m; i++) {  
         dp[i][0] = i;  
      }  
        // source can be transformed into target hiprefix by inserting  
        // all of the characters in the prefix  
      for ( j = 1; j <= n; j++) {  
         dp[0][j] = j;  
      }  
   
      for ( i = 1; i <= m; i++) {  
         for ( j = 1; j <= n; j++) {  
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {  
               dp[i][j] = dp[i - 1][j - 1]; // no operation required as characters are the same  
            }  
            else {  
               dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],    // substitution  
                            Math.min(dp[i][j - 1],     // insertion  
                                dp[i - 1][j] ) );    // deletion  
            }  
         }  
      }  
      return dp[m][n];  
   }
   



   /** Construct a valid board. **/
   @Test 
   public void constructBoard() {
      Board myBoard ;
        
      // me = new Board();
        // System.out.println(p.gridView());
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      boolean result;
      try {
         myBoard = new Board(ships);
         result = true;
      } catch (Exception e) {
         result = false;
      }
      
      
      assertTrue(result);
   }
   
   /** Construct a valid board. **/
   @Test 
   public void invalidBoard01() {
      Board myBoard ;
        
      // me = new Board();
        // System.out.println(p.gridView());
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      // two ships on top of each other
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',9,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      boolean result;
      try {
         myBoard = new Board(ships);
         result = false; // should throw exception
      } catch (IllegalArgumentException e) {
         // should go here
         result = true;
      }
      assertTrue("Invalid Board check failed",result);
   }

      /************
      * Will allow for newline or no newline at end of string
      ***********/
   @Test 
   public void shipView() {
      Board myBoard ;
        
      // me = new Board();
        // System.out.println(p.gridView());
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      boolean result;
      
      myBoard = new Board(ships);
   
         
      String expected = "  1 2 3 4 5 6 7 8 9 0\nA - - - 0 0 0 0 0 - -\nB - - - - - - - - 2 3\nC - - - - 4 - - - 2 3\nD - - - - 4 - - - 2 3\nE - - - - - - - - - -\nF - - - - - - - - - -\nG - - - - - 1 1 1 1 -\nH - - - - - - - - - -\nI - - - - - - - - - -\nJ - - - - - - - - - -";
      String actual = myBoard.shipView();
      // should be  0, but allow up to two
      int dist = editDistance(expected, actual); 
      
      assertTrue("Output for expected String does not match",dist >= 0 && dist <= 2);
   }
   
   @Test 
   public void getShipN() {
      Board myBoard ;
        
      // me = new Board();
        // System.out.println(p.gridView());
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      Ship a;
      myBoard = new Board(ships);
      
      a = myBoard.getShip(0);
      assertEquals("Ship 0 didn't return",ships[0],a);
      a = myBoard.getShip(1);
      assertEquals("Ship 1 didn't return",ships[1],a);
      a = myBoard.getShip(2);
      assertEquals("Ship 2 didn't return",ships[2],a);
      a = myBoard.getShip(3);
      assertEquals("Ship 3 didn't return",ships[3],a);
      a = myBoard.getShip(4);
      assertEquals("Ship 4 didn't return",ships[4],a);
   
      
   }
   
   @Test 
   public void getShipRowCol() {
      Board myBoard ;
        
      // me = new Board();
        // System.out.println(p.gridView());
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      Ship a;
      myBoard = new Board(ships);
      a = myBoard.getShip('C',9); // ship 2
      
      assertEquals("C,9 should be Ship 2",ships[2],a);
      a = myBoard.getShip('A',7); // ship 0
      assertEquals("A,7 should be Ship 0",ships[0],a);
      a = myBoard.getShip('D',0); // ship 3
      assertEquals("D,0 should be Ship 3",ships[3],a);
   }
   
   @Test 
   public void hasShipRowCol() {
      Board myBoard ;
   
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      myBoard = new Board(ships);
      assertTrue("Should have B,9",myBoard.hasShip('B',9));
      assertTrue("Should have C,9",myBoard.hasShip('C',9));
      assertTrue("Should have D,9",myBoard.hasShip('D',9));
      assertFalse("Should have E,9",myBoard.hasShip('E',9));
      assertFalse("Should have F,9",myBoard.hasShip('F',9));
      
      assertFalse("No ship at A,1",myBoard.hasShip('A',1));
      assertFalse("No ship at J,0",myBoard.hasShip('J',0));
   }
   
   @Test
   public void accessBounds() {
      Board myBoard ;
   
      Ship [] ships = new Ship[5];
      ships[0] = new Ship('A',4,Direction.HORIZONTAL,5);
      ships[1] = new Ship('G',6,Direction.HORIZONTAL,4);
      ships[2] = new Ship('B',9,Direction.VERTICAL,3);
      ships[3] = new Ship('B',0,Direction.VERTICAL,3);
      ships[4] = new Ship('C',5,Direction.VERTICAL,2);
      
      myBoard = new Board(ships);
      // multiple attempts to go out of bounds
      boolean pass = true;
      try {
         myBoard.hasShip('A',10);
         pass = pass && false; // should not make it here
      } catch (IllegalArgumentException e) {
               // still good
      }
      try {
         myBoard.hasShip('K',5);
         pass = pass && false;
      } catch (IllegalArgumentException e) {
               // still good
      }
     
      try {
         myBoard.getShip('A',10);
         pass = pass && false;
      } catch (IllegalArgumentException e) {
               // still good
      }
      try {
         myBoard.getShip('K',5);
         pass = pass && false;
      } catch (IllegalArgumentException e) {
               // still good
      }
      try {
         myBoard.getShip(5);
         pass = pass && false;
      } catch (IllegalArgumentException e) {
               // still good
      }
      try {
         myBoard.getShip(-1);
         pass = pass && false;
      } catch (IllegalArgumentException e) {
               // still good
      }
      
      assertTrue(pass);
   }
   
   /***********
   * Requires manual review
   * and random generator should report usage 
   ******************/
   @Test
   public void randomBoards() {
      Utils u = new Utils();
      Board b;
      b = new Board();
      System.out.println("Random ship placement");
      System.out.println(b.shipView());
      b = new Board();
      System.out.println(b.shipView());
      
      // check that random generator was used.
      System.out.println("Random values generated: " + u.getCount());
      assertTrue("You must use the Utils random. Count is " + u.getCount(),u.getCount() > 20);
   }
   

}
