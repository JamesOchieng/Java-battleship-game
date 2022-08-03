import java.util.*;
import java.io.*;

public class Ship
{
   public int col;
   public int length;
   public char row;
   public static int rownumb;
   public static int colmnumb;
   public static int counter;
   public static boolean hit = false;
   public static boolean sunk = false ;
   private static int grid = 10;
   public static char[][] arr = new char[grid][grid];
   public static String[] gridRow = {"1","2","3","4","5","6","7","8","9","0"};
   public static char[] gridcolmn = {'A','B','C','D','E','F','G','H','I','J'};
   public static Direction newDirection;
   public static Viewer gamer;

   
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
      
      
      for(int x = 0; x < arr.length; x++)
      {
         for(int j = 0; j < arr[x].length; j++)
         {
            arr[x][j] = '-';
         }
      }
      
      setOrientation(newDirection);
      
   
   }
   
   public Ship()
   {
      Ship demoShip = new Ship('A', 1 , Direction.HORIZONTAL, 2);
      demoShip.setViewer(Viewer.PLAYER);
   }
   
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

   public String gridView()
   {
      String grid = "";
      String fullGrid = "";
      String rowHeading = "";
      for(int x = 0; x < arr.length; x++)
      {
         for(int j = 0; j < arr[x].length; j++)
         {
            grid =  grid + arr[x][j] + " ";
         }
         rowHeading =rowHeading + " " + gridRow[x] ;
         fullGrid = fullGrid +  gridcolmn[x] + " " + grid + "\n";
         grid = "";
      }
      String word =" " + rowHeading + "\n" + fullGrid;
      return word;
   }
   
   public void setOrientation(Direction newDirection)
   {
      
      if(newDirection == Direction.HORIZONTAL)
      {
         for(int w =1 ; w <= length ; w++)
         {
            arr[rownumb][colmnumb++] = 'o';
         }
      }
         
      else if(newDirection == Direction.VERTICAL)
      {
         for(int x = 1; x <= length; x++)
         {
            arr[rownumb++][colmnumb] = 'o';
         }
      }
   }
   
   
   
   
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
   
   public char getSquare(char row, int col)
   {
      if(Character.isLowerCase(row) == true ||  row > 'J' || col < 0 || col > 9)
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
      if(arr[getrownumb][getcolmnumb] == 'o' && gamer == Viewer.PLAYER)
      {
         status = 'o';
      }
      if(arr[getrownumb][getcolmnumb] == '-' && gamer == Viewer.OPPONENT)
      {
         status = '-';
      }
      if(arr[getrownumb][getcolmnumb] == 'o' && gamer == Viewer.OPPONENT)
      {
         status = '-';
      }
      if(arr[getrownumb][getcolmnumb] == '+' && gamer == Viewer.OPPONENT)
      {
         status = '+';
      }
      if(arr[getrownumb][getcolmnumb] == '+' && gamer == Viewer.PLAYER)
      {
         status = '+';
      }
      else if(arr[getrownumb][getcolmnumb] == 'x' && gamer == Viewer.OPPONENT && sunk == true)
      {
         status = 'x';
      }
         
      return status;
      
   }
   
   
   public boolean isSunk()
   {
      return sunk;
   }
   
   public boolean checkShot(char shotRow, int shotCol)
   {
      if(Character.isLowerCase(row) == true ||  row > 'J' || col < 0 || col > 9)
      {
         throw new IllegalArgumentException("Invalid coordinates");
      }
      int countshot = 0;
      int checkrownumb = charToNumb(shotRow);
      int checkcolmnumb = getTen(shotCol);
      
      if(arr[checkrownumb][checkcolmnumb] == 'o')
      {
         arr[checkrownumb][checkcolmnumb] = '+';
         hit  = true;
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
         sunk = true;
      }
      else if(arr[checkrownumb][checkcolmnumb] == '-')
      {
         return hit;
      }
      counter++;
      return hit;
   }
   
   public String toString()
   {
      String shipRepre = "";
      if(newDirection == Direction.VERTICAL )
      {
         shipRepre = setToString(Direction.VERTICAL);
      }
      else if(newDirection == Direction.HORIZONTAL)
      {
         shipRepre = setToString(Direction.HORIZONTAL);
      }
      return gamer + shipRepre;
   }
   
   public String setToString(Direction s)
   {  
      
      int setRownumb = charToNumb(row);
      int arrRownumb = charToNumb(row);
      int setColmnumb = colmnumb;
      
      String shipRepre = "";
      String tempRepre = "";
      String viewer = "";
      int n = 1;
      if(s == Direction.VERTICAL)
      {
         while(n <= length)
         {
            tempRepre =tempRepre + "[r=" + gridcolmn[setRownumb++] + ", c="+ gridRow[setColmnumb]+"(" + arr[arrRownumb++][colmnumb]+")" +"]" ;
            n++;
         }
         shipRepre = tempRepre;
         tempRepre = "";
      }
      else if(s == Direction.HORIZONTAL)
      {
         while(n <= length)
         {
            tempRepre =tempRepre + "[r=" + gridcolmn[setRownumb] + ", c="+ gridRow[setColmnumb++]+"(" + arr[arrRownumb][colmnumb++]+")" +"]" ;
            n++;
         }
         shipRepre = tempRepre;
         tempRepre = "";
      } 
      return shipRepre;
   }
   
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
