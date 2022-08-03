import java.util.*;
import java.io.*;

public class Module1Program
{
   public static final int MAX_LINES = 200;
   int numb = 0;
   public static final File song = new File("lyrics.txt");
   
   public static void main(String[] args)
   {
      lineCount();
   }
     
   public static void lineCount()
   {
      
      
      int ctr = 0;
      if(song.exists())
      {
         try
         {
            
            FileReader s1 = new FileReader(song);
            LineNumberReader lnr = new LineNumberReader(s1);
           
            while((lnr.readLine() !=null))
            {
               ctr++;
            }
         }
         
         catch(Exception e)
         {
            System.out.print(e);
            return;
         }
         
         if(ctr > 0)
         {
            fileLength(ctr);
         }
         else if( ctr == 0)
         {
            System.out.println("Empty file");
            return;
         }
         
         //System.out.println(ctr);
      }
      else
      {
         System.out.println("File not found");
      }
   }
   
   public static void fileLength(int ctr)  
   {
      if(ctr <= 200)
      {
         try{
            
            Scanner input = new Scanner(song);
         
            int[] lines = new int[ctr];
            String[] lyrics = new String[80];
            String lns = "";
            int x = 0; 
            int y = 0;
            String words = "";
         
            while(input.hasNextLine())
            {
               lns = input.nextLine();
               //System.out.println(lns);
            
               for(String word : lns.split(" "))
               {
                  //System.out.println(word);
                  
                  try
                  {
                     int numbr = Integer.parseInt(word);
                     lines[y++] = numbr ;
                  }
                  catch(Exception e)
                  {
                     //word = word + " ";
                     lyrics[x++] = word;
                  
                  }
               
               //System.out.println(words);
               }
            } 
            
            
            System.out.println(Arrays.toString(lyrics));
            //System.out.println(Arrays.toString(lines));
            selectionSort(lines , lyrics);
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
   
   public static void selectionSort(int[] a, String[] lyrics )
   {
   //System.out.println(Arrays.toString(a));
      for (int i = 0; i < a.length - 1; i++)
      {
       // find index of smallest element
         int smallest = i;
         for (int j = i + 1; j < a.length; j++)
         {
            if (a[j] < a[smallest]) 
            {
               smallest = j;
            }
         }
         int small = a[smallest];
         a[smallest] = a[i];
         a[i] = small;
         swap(a, lyrics, small, smallest);
      }
      System.out.println(Arrays.toString(a));
      for(int x = 0; x < a.length; x++){  
         System.out.print(a[x]+" ");  
      }
      
            
   }
   
   public static void swap(int[] list, String[] lines,  int i, int j) {
      int temp = list[i];
      String tempStr = lines[i];
      list[i] = list[j];
      lines[i] = lines[j];
      list[j] = temp;
      lines[j] = tempStr;
      for(int x = 0; x < lines.length; x++){  
         System.out.print(lines[x]+" ");  
      }
   
   }

      
}