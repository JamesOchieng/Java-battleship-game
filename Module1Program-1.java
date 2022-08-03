import java.util.*;
import java.io.*;
  /*
-This program is desighned to read lyrics (lyrcis.txt) with 
 numbers in then, and in retuen it rearranges lines 
 based on those numbers
 
 -@auther James Ochieng
 -@Version 2/17/20
*/ 

public class Module1Program
{
//final int for the max number of lines
   public static final int MAX_LINES = 200;

//Declaring song to hold the lyrics txt
   public static final File song = new File("lyrics.txt");
  
  //Main method calling the initial method  
   public static void main(String[] args)
   {
      lineCount();
   }
   //The method that will run all the code  
   public static void lineCount()
   {
      
      // The counter, used to keep track of number of lines
      int ctr = 0;
      
      //ifand else statment to determine if the file exists
      if(song.exists())
      {
      //try and catch for just incase the file doeas not exist
         try
         {
            //variables that will be used to determine the number of lines 
            FileReader s1 = new FileReader(song);
            LineNumberReader lnr = new LineNumberReader(s1);
           //while for counting the number of lines
            while((lnr.readLine() !=null))
            {
               ctr++;
            }
         }
         
         //try and catch for just incase the file doeas not exist
         catch(Exception e)
         {
            System.out.print(e);
            return;
         }
         //if and else statement to check if the file is empty
         //counter is passed to the fileLength method
         if(ctr > 0)
         {
            fileLength(ctr);
         }
         else if( ctr == 0)
         {
            System.out.println("Empty file");
            return;
         }
      }
      else
      {
         System.out.println("File not found");
      }
   }
   //This method will extract the numbers from the lines 
   //and throw then into an int array, while also throwning
   //the new string into a string array.
   public static void fileLength(int ctr)  
   {
   //if and else statement to check if the number of lines is ober
   // the MAX_LINES
      if(ctr <= MAX_LINES)
      {
      //try and catch for ensuring the scanners will be accepted
         try{
            //Decleard variables for use in the while loops
            Scanner input = new Scanner(song);
            Scanner numbrLine;
            int[] lines = new int[ctr];
            String[] lyrics = new String[MAX_LINES];
            String lns = "";
            int x = 0; 
            int y = 0;
            String words = "";
            String fullword = "";
         
         //while loop to check for has.Next.Line
            while(input.hasNextLine())
            {
               lns = input.nextLine();
               numbrLine = new Scanner(lns);
               //while loop to check for ints in the lines,
               //while sending the ints in the int array
               while(numbrLine.hasNext())
               {
                  try
                  {
                     int linenumb = numbrLine.nextInt();
                     //System.out.print("*" + linenumb + "*");
                     lines[x++] = linenumb;
                     
                  //Non int are added together and send to the string
                  //array
                  }catch(Exception i)
                  {
                     words =numbrLine.next();
                     fullword = fullword + " " + words;
                  }
                  
                  
               }
               //The line variable is reset for the next line//
               lyrics[y] = fullword;
               fullword = "";
               y++;
               
            } 
            //The lines and lyrics variable arrays are sent to the 
            //selection sort method
            selectionSort(lines , lyrics);
            for( int q = 0; q < ctr; q++)
            {
               System.out.println(lyrics[q]);
            }
         
             
         }
         catch(Exception e)
         {
            System.out.print(e);
         }
      }
      else
      {
         System.out.println("The song is too long");
      }
      
   }
   //selesctionsort method, to rearrange the int array
   public static void selectionSort(int[] a , String[] b) {
      for (int i = 0; i < a.length - 1; i++) {
       // find index of smallest element
         int smallest = i;
         for (int j = i + 1; j < a.length; j++) {
            if (a[j] < a[smallest]) {
               smallest = j;
            }
         }
         //Both the string and int array are sent to the swap method
         swap(a, b, i, smallest); // swap smallest to front
      }
   }
   
   //This method rearranges both arrays in parallel
   public static void swap(int[] list, String[] lines,  int i, int j) {
      int temp = list[i];
      String tempStr = lines[i];
      list[i] = list[j];
      lines[i] = lines[j];
      list[j] = temp;
      lines[j] = tempStr;
   }
      
}