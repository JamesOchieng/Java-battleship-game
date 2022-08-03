/**
*This battle ship program is desighned to allow
*the user to place all their ships on one grid.
*If there is no user input, the program will randomly
*place the ships on the grid.
*
*@auther James OChieng
*
*@Version 3/21/2020
*/

import java.util.*; 

public class Board
{

   private Ship[] ships = new Ship[5];
   private Ship[] demoships = new Ship[5];
   private char[][] grid = new char[Utils.NUM_ROWS][Utils.NUM_COLS];

   private int convertCol(int n) {
      if (n == 0)
         return 9;
      else
         return n -1;
   }
         
   private int convertRow(char c) {
      return c - 'A';
   }
/**
*This Board constructor will place random ships on the board
*
*@throws IllegalArgumentExcpetion if the random ships over lap or the ship is off grid
*/
   public Board()
   {
      int bt = 0;
      Utils util = new Utils();
      demoships[bt] = null;
      int length = 5;
      int lengths = 0; 
      
      for (int i=0; i < Utils.NUM_COLS; i++)
         for (int j=0; j < Utils.NUM_ROWS; j++)
            this.grid[i][j] = '-';
   
      for(bt =0; bt < 5; bt++)
      {
         demoships[bt] = null;
      
         while(demoships[bt] == null  )
         {
            Direction d =/* Direction.HORIZONTA*/ util.nextDirection();
            char row =/* 'A' */ util.nextRow();
            int col = util.nextCol();
            
            {
               try
               {
                  if(length == 3 || length == 2)
                  {
                     lengths = 3;
                     demoships[bt] = new Ship(row, col, d, lengths);
                  }
                  else if(length == 1)
                  {
                     lengths = 2;
                     demoships[bt] = new Ship(row, col, d, lengths);
                  }
                  else if(length == 5 || length == 4 )
                  {
                     if(length == 5)
                     {
                        lengths = 5;
                     }
                     else if(length == 4)
                     {
                        lengths = 4;
                     }
                     demoships[bt] = new Ship(row, col, d, lengths);
                  }
                  
                  for (int i=0; i < Utils.NUM_ROWS; i++)     
                  {
                     char row2 = (char)((int)'A' + i);
                  
                     for (int j = 0; j < Utils.NUM_COLS; j++)  
                     {
                        int intR = convertRow(row2);
                        int intC = convertCol(j);
                        if(demoships[bt].hasSquare(row2,j) && grid[intR][intC] != '-')
                        {
                           demoships[bt] = null;
                           throw new IllegalArgumentException("Invalid Coordinates");
                        
                        }
                        
                        else if(demoships[bt].hasSquare(row2, j) && grid[intR][intC] == '-')
                        {
                        
                           grid[intR][intC] = (char)((int)'0' + bt);
                        }
                        
                     }
                  
                  }
               
               
                  length--;
               
               }
               catch(IllegalArgumentException e)
               {
               
               }
            }
         }
      }
      cleanGrid(demoships);
   }
   
   /**
   *This method recreates the grid and removes any half finished ships.
   *
   *@param Ship[] is the rendoms ships
   *
   */
   public void cleanGrid(Ship[] demoships)
   {
      for (int i=0; i < Utils.NUM_COLS; i++)
         for (int j=0; j < Utils.NUM_ROWS; j++)
            this.grid[i][j] = '-';
      
      for(int x = 0;  x < demoships.length; x++)
      {
         ships[x] = demoships[x];
      }
      
      for(int k = 0; k < 5; k++)
      {
         for (int i=0; i < Utils.NUM_ROWS; i++)     
         {
            char row2 = (char)((int)'A' + i);
         
            for (int j = 0; j < Utils.NUM_COLS; j++)  
            {
               int intR = convertRow(row2);
               int intC = convertCol(j);
               
               if(ships[k].hasSquare(row2, j) && grid[intR][intC] == '-')
               {
               
                  grid[intR][intC] = (char)((int)'0' + k);
               }
            }
         
         }
         
      }
   
   }
   

  /**
  *This constructor recieves a ship array fromthe user and places 
  *the ships on the grid 
  *
  *@param Ship[] is the ships array from the user 
  *
  *
  */ 
   public Board(Ship[] newships)
   {
      for (int i=0; i < Utils.NUM_COLS; i++)
         for (int j=0; j < Utils.NUM_ROWS; j++)
            this.grid[i][j] = '-';
      
      for(int x = 0;  x < newships.length; x++)
      {
         ships[x] = newships[x];
      }
      
      for(int k = 0; k < 5; k++)
      {
         for (int i=0; i < Utils.NUM_ROWS; i++)     
         {
            char row2 = (char)((int)'A' + i);
         
            for (int j = 0; j < Utils.NUM_COLS; j++)  
            {
               int intR = convertRow(row2);
               int intC = convertCol(j);
               if(ships[k].hasSquare(row2, j) && grid[intR][intC] != '-')
               {
                  throw new IllegalArgumentException("Ships overlap");
               }
               
               if(ships[k].hasSquare(row2, j) && grid[intR][intC] == '-')
               {
               
                  grid[intR][intC] = (char)((int)'0' + k);
               }
            }
         
         }
         
      }
   }
/**
*This method will  create a string represenation of the grid 
*
*@return String that represents the grid
*
*/

   public String shipView()
   {
      int [] gridRow = { 1,2,3,4,5,6,7,8,9,0,};
      char[] gridcolmn = {'A','B','C','D','E','F','G','H','I','J'};
      String fullGrid = "";
      String rowHeading = "";
      rowHeading = rowHeading + " ";
      
            
      for(int x = 0; x < gridRow.length; x++)
      {
         rowHeading = rowHeading + " ";
         rowHeading = rowHeading + gridRow[x];
      }
      rowHeading = rowHeading + "\n";
     
      for(int z = 0; z < grid.length; z++)
      {
         rowHeading = rowHeading + gridcolmn[z];
         for(int j = 0; j < grid[0].length; j++)
         {        
            rowHeading = rowHeading + " ";
            rowHeading = rowHeading + grid[z][j] ;
         }
         rowHeading = rowHeading + "\n"; 
      }
      return rowHeading;
   }


/**
*This method will return the ship based on the asked index 
*
*@param int will be used to find the right ship
*
*@return Ship is the ship based on the index
*/
   
   public Ship getShip(int n) {
      if(n > 4 || n < 0)
      {
         throw new IllegalArgumentException( "Invalid value");
      }
      return ships[n];
   }
  
  /**
  *This method checks whether the accpeted values equal to a ship
  *
  *@param row the row of grid
  *
  *@param col the col of grid
  *
  *@return boolean if there is a ship on the grid
  */ 
   
   public boolean hasShip(char row, int col)
   {     
       
      if (col >= 10 || col < 0 || row > 'J' || Character.isLowerCase(row))
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
      int intR = convertRow(row);
      int intC = convertCol(col);
    
      if(grid[intR][intC] == '0' ||grid[intR][intC] == '1' ||grid[intR][intC] == '2' ||grid[intR][intC] == '3' ||grid[intR][intC] == '4')
      {
         return true;
      }
      
      else
         return false;
   }
   
   /**
   *This method reruen the asked ship based on the cordinates 
   *
   *@param row the row of grid
   *
   *@param col the col of grid
   *
   *@return Ship based on the required cordinates
   *
   */
   public Ship getShip(char row, int col)
   {
      int intR = convertRow(row);
      int intC = convertCol(col);
      
      if ((intC > 9 || intC < 0) || (intR > 9 || intR < 0) || Character.isLowerCase(row))
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
      
      if(grid[intR][intC] == '-')
      {
         throw new IllegalArgumentException("No ship there");
      }
      char t = (char)grid[intR][intC];
      int x = Integer.parseInt(String.valueOf(t));
      return ships[x];
      
     
   
      
   }
   
}