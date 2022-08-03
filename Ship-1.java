import java.util.*;
import java.io.*;
/**
*A class desighned to play the battleship game, there will be a player and an opponent. 
*The player can see their board and all the hits, while the Opponent can look but will
*be able to see on;y the hits in the board. This class allows the user to call special
*mutatorsby calling the methods
*
* @author James Ochieng
* @version 2 3/9/2020
*
*/
public class Ship
{
   public int col;
   public static int length;
   public char row;
   public static int rownumb;
   public static int colmnumb;
   public int counter=0;
   public static boolean hit = false;
   public static boolean sunk = false ;
   private static int grid = 10;
   public static char[][] arr = new char[grid][grid];
   public static String [] gridRow = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
   public static char[] gridcolmn = {'A','B','C','D','E','F','G','H','I','J'};
   public static Direction newDirection;
   public static Viewer gamer;

   /**
   *This constructor will be used to create a battleship depending on what 
   *the user wants.The user will pass a row, colmn and length.
   *
   * @throws  IllegalArgumentException if ship is off grid, or if the cordinates entered
   *are not valid.
   * @param the method recieves a row and colmn frm the user.
   *
   */
   public Ship(char shiprow, int shipcol, Direction d, int shiplength)
   {
      if(Character.isLowerCase(shiprow) == true)
      {
         throw new IllegalArgumentException("Ship is off grid");
      }
   
      if(shipcol < 0 || shipcol > 9 || shiprow > 'J' || shiplength < 1 || shiplength > 9)
      {
         throw new IllegalArgumentException("Ship is off grid");
      }
      row = shiprow;
      rownumb = charToNumb(row);
      colmnumb = getTen(shipcol);
      this.newDirection = d;
      this.length = shiplength;
      gamer = Viewer.PLAYER;
     
     
      for(int x = 0; x < arr.length; x++)
      {
         for(int j = 0; j < arr[x].length; j++)
         {
            arr[x][j] = '-';
         }
      }
     
      setOrientation(newDirection);
     
   
   }
   /**
   *This method will create a generic battleship, the values are already
   *included.
   *
   */
   public Ship()
   {
      Ship demoShip = new Ship('A', 1 , Direction.HORIZONTAL, 2);
      demoShip.setViewer(Viewer.PLAYER);
   }
   
   /**
   *A method that translate the row charcter into an int.
   *
   * @param The method recieves a character.
   * @return The method returns the integer corresponding to that char.
   */
   public int charToNumb(char row)
   {
      int rownumbr = 0;
      char lettr = Character.toUpperCase(row);
      if(lettr == 'A')
      {
         rownumbr = 0;
      }
      if(lettr == 'B')
      {
         rownumbr = 1;
      }
      if(lettr == 'C')
      {
         rownumbr = 2;
      }
      if(lettr == 'D')
      {
         rownumbr = 3;
      }
      if(lettr == 'E')
      {
         rownumbr = 4;
      }
      if(lettr == 'F')
      {
         rownumbr = 5;
      }
      if(lettr == 'G')
      {
         rownumbr = 6;
      }
      if(lettr == 'H')
      {
         rownumbr = 7;
      }
      if(lettr == 'I')
      {
         rownumbr = 8;
      }
      else if(lettr == 'J')
      {
         rownumbr = 9;
      }
      return rownumbr;
   }
   
   /**
   *The method sets the orientation for the ship.
   *
   * @throw IllegalArgumentException if the direction causes the ship to go off grid.
   * @param The method recieves a direction from the user input.
   */
   
   public void setOrientation(Direction newDirection)
   {
      if((colmnumb + length) >= arr.length-1 && newDirection == Direction.HORIZONTAL)
      {
         throw new IllegalArgumentException("Ship is off grid");
      }
      else if((rownumb + length) >= arr.length-1 && newDirection == Direction.VERTICAL)
      {
         throw new IllegalArgumentException("Ship is off grid");
      }
      
      int getrownumb = rownumb;
      int getcolmnumb = colmnumb;
      
      if(newDirection == Direction.HORIZONTAL)
      {
         for(int w =1 ; w <= length ; w++)
         {
            arr[getrownumb][getcolmnumb++] = 'o';
         }
      }
         
      else if(newDirection == Direction.VERTICAL)
      {
         for(int x = 1; x <= length; x++)
         {
            arr[getrownumb++][getcolmnumb] = 'o';
         }
      }
      
   }
   
   /**
   *This method assighns the Viewer as an opponent or a user.
   *
   * @param It recieves the Viewer from the user input.
   */
   
   
   public void setViewer(Viewer w)
   {
      if(w == Viewer.PLAYER)
      {
         gamer = w;
      }
      else if(w == Viewer.OPPONENT)
      {
         gamer = w;
      }
   }
   
   /**
   *This method grabs the value of the square, the values are altered 
   *depending on who the viewer is.
   *
   * @throw  IllegalArgumentException if the inputs are not valid.
   * @param The method recieves a row and colmn frm the user.
   *
   *
   */
   
   public char getSquare(char row, int col)
   {
      if(Character.isLowerCase(row) == true ||  row > 'J' || col < 0 || col > 10)
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
   
      int getrownumb = charToNumb(row);
      int getcolmnumb = getTen(col);
      char status = ' ';
      if(arr[getrownumb][getcolmnumb] == '-' && gamer == Viewer.PLAYER)
      {
         status = '-';
      }
      else if(arr[getrownumb][getcolmnumb] == 'o' && gamer == Viewer.PLAYER)
      {
         status = 'o';
      }
      else if(arr[getrownumb][getcolmnumb] == '-' && gamer == Viewer.OPPONENT)
      {
         status = '-';
      }
      else if(arr[getrownumb][getcolmnumb] == 'o' && gamer == Viewer.OPPONENT)
      {
         status = '-';
      }
      else if(arr[getrownumb][getcolmnumb] == '+' && gamer == Viewer.OPPONENT)
      {
         status = '+';
      }
      else if(arr[getrownumb][getcolmnumb] == '+' && gamer == Viewer.PLAYER)
      {
         status = '+';
      }
      else if(arr[getrownumb][getcolmnumb] == 'x' && gamer == Viewer.OPPONENT && sunk == true)
      {
         status = 'x';
      }
      else if(arr[getrownumb][getcolmnumb] == 'x' && gamer == Viewer.PLAYER && sunk == true)
      {
         status = 'x';
      }
         
      return status;
     
   }
   
   /**
   *This method keeps track of whether the ship has sunk.
   *
   * @return A boolean will be returned.
   */
   
   public boolean isSunk()
   {
      return sunk;
   }
   
   /**
   *This method will be used to fire at the battleship, it wull also keep 
   *count the number of times the ship has been hit.
   *
   * @throw IllegalArgumentException if the user input are not valid
   * @param It is recieving the user input as colmn and row.
   * @return It will return a boolean hit.
   */
   
   public boolean checkShot(char shotRow, int shotCol)
   {
      if(Character.isLowerCase(row) == true ||  row > 'J' || col < 0 || col > 9)
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
      int checkrownumb = charToNumb(shotRow);
      int checkcolmnumb = getTen(shotCol);
     
      if(arr[checkrownumb][checkcolmnumb] == 'o')
      {
         arr[checkrownumb][checkcolmnumb] = '+';
         hit  = true;
         sunk = false;
         counter++;
      
      }
      if(counter == length  )
      {
         if(newDirection == Direction.HORIZONTAL)
         {
            for(int x =1 ; x <= length ; x++)
            {
               arr[checkrownumb][checkcolmnumb--] = 'x';
            }
         }
         
         else if(newDirection == Direction.VERTICAL)
         {
            for(int x = 1; x <= length; x++)
            {
               arr[checkrownumb--][checkcolmnumb] = 'x';
            }
         }
         counter = 0;
         sunk = true;
      }
      else if(arr[checkrownumb][checkcolmnumb] == '-')
      {
         hit = false;
      }
      return hit;
   }
   
   /**
   *This method produces the grid of the battleship and its current status.
   *
   * @return return string of the grid.
   */
   
   public String gridView()
   {
      String grid = "";
      String fullGrid = "";
      String rowHeading = "";
      rowHeading = rowHeading + " ";
      for(int x = 0; x < gridRow.length; x++)
      {
         rowHeading = rowHeading + " ";
         rowHeading = rowHeading + gridRow[x];
      }
      rowHeading = rowHeading + "\n";
     
      for(int z = 0; z < arr.length; z++)
      {
         rowHeading = rowHeading + gridcolmn[z];
         for(int j = 0; j < arr[0].length; j++)
         {            
            rowHeading = rowHeading + " ";
            if(gamer == Viewer.OPPONENT && arr[z][j] == 'o')
            {
               rowHeading = rowHeading + '-';
            }
            else
            {
               rowHeading = rowHeading + arr[z][j];
            } 
            
         }
         rowHeading = rowHeading + "\n"; 
      }
      return rowHeading;
   }
   
   /**
   *This method creates a list of the position of the battleship, and the current Viewer.
   *
   * @return Returns a string of the battleship location. 
   */
   public String toString()
   {
      String shipRepre = "";
      if(newDirection == Direction.VERTICAL )
      {
         shipRepre = setToString();
      }
      else if(newDirection == Direction.HORIZONTAL)
      {
         shipRepre = setToString();
      }
      return gamer + shipRepre;
   }
   
   /**
   *This method is called upon by toString method to find the ship's position.
   *
   * @return It will return a string of the ship position.
   *
   */
   
   public String setToString()
   {  
     
      int setRownumb = charToNumb(row);
      char arrRownumb = row;
      int setColmnumb = colmnumb ;
      int arrColmnumb = colmnumb + 1;
   
     
      String shipRepre = "";
      String tempRepre = "";
      String viewer = "";
      int n = 1;
      if(newDirection == Direction.VERTICAL)
      {
         while(n <= length)
         {
            tempRepre =tempRepre + "[r=" + gridcolmn[setRownumb++] + ",c="+ gridRow[setColmnumb]+"(" + getSquare(arrRownumb,arrColmnumb) +")" +"]" ;
            n++;
            String sep = "";
            for(int i = 1 ; i <= 2; i++) {
               tempRepre = tempRepre + sep;
               sep = ",";
            }
            arrRownumb++;
         }
         shipRepre = tempRepre.replaceAll(",$","");
         tempRepre = "";
      }
      else if(newDirection == Direction.HORIZONTAL)
      {
         while(n <= length)
         {
            tempRepre =tempRepre + "[r=" + gridcolmn[setRownumb] + ",c="+ gridRow[setColmnumb++]+"(" + getSquare(arrRownumb,arrColmnumb) +")" +"]";
            String sep = "";
            n++;
            for(int i =1 ; i <= 2; i++) {
               tempRepre = tempRepre + sep;
               sep = ",";
            }
            arrColmnumb++;
         }
         shipRepre = tempRepre.replaceAll(",$","");
         tempRepre = "";
      }
      return shipRepre;
   }
   
   /**
   *This method check's to see the current value of a square, and if
   *it's being occupied by a ship.
   *
   * @param It will recieve row and colmn from the user.
   * @throw  IllegalArgumentException if the values are valid. 
   * @return It will return a boolean if a ship is on thee sqaure.
   */
   
   public boolean hasSquare(char hasRow, int hasCol)
   {
      if(Character.isLowerCase(row) == true ||  row > 'J' || col < 0 || col > 9)
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
     
      int hasRownumb = charToNumb(hasRow);
      int hasColmnumb = getTen(hasCol);
      boolean square;
      if( arr[hasRownumb][hasColmnumb] == 'o'|| arr[hasRownumb][hasColmnumb] == '+' || arr[hasRownumb][hasColmnumb] == 'x')
      {
         square = true;
      }
      else
      {
         square = false;
      }
      return square ;
   }
   
   /**
   *This method is used to check if the user colmn input is 10, and if it is
   *the value gets switched to 0.
   *
   * @param It will recieve an int from the user
   * @return It will return the new int. If the int is not changed, the it will 
   *return the current int minus one.
   */
   public int getTen(int notzero)
   {
      int newnum;
      if( notzero == 0)
      {
         newnum = 10;
      }
      else
      {
         newnum = notzero;
      }
      return newnum - 1;
   }
}