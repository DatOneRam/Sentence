import java.util.Scanner;

import java.util.Random;

import java.io.File;

import java.io.IOException;

import java.io.PrintWriter;



public class Sentence
{
    private File singularPronouns;
    private File nouns;
    private File singularVerbs;
    private File pluralVerbs;

    //constructor
    //sets up the files with filenames
    public Sentence(String n, String v, String h, String a)
    {
        singularPronouns = new File(n);
        singularVerbs= new File(v);
        pluralVerbs = new File(h);
        nouns = new File(a);
    }

    public Sentence()
    {
        singularPronouns = new File("singularPronouns.txt");
        singularVerbs= new File("singularVerbs.txt");
        pluralVerbs = new File("pluralVerbs.txt");
        nouns = new File("nouns.txt");
    }

    public String generateSimpleSentence() throws IOException
    {
        String article1 = "blank", start = "blank", verb = "blank", noun = "blank", article2= "blank";
        Random rand = new Random();

        int startPronoun = rand.nextInt(2);
        int startNoun = rand.nextInt(2);
        int startProperNoun = rand.nextInt(2);
        int singularStart = rand.nextInt(2);

        if (singularStart == 1 && startPronoun == 1)
        {
            start = getRandWord(singularPronouns);
            verb = getRandWord(pluralVerbs);
        }
        return (start + " " + verb + " " + noun + ".");
    }

    //method to get a random line from a given file
    //takes in one File
    //returns a String (a random line from the file)
    public String getRandWord(File f) throws IOException
    {
      int length = 0;
      int choice;
      int i;
      Scanner prompt = new Scanner(f);
      Random rand = new Random();
      String word;

      //if the file doesn't exist, return an error message
      if (!f.exists())
      {
         return "ERROR: FILE NOT FOUND AT getRandWord()";
      }  
      
      //get how many lines are in the file, assign it to length
      while (prompt.hasNext())
      {
         length++;
         prompt.nextLine();
      }

     //reset the Scanner, since the read position is at the bottom of the file
     prompt = new Scanner(f);
       
     //get a random line number in the file
     choice = rand.nextInt(length) + 1;
           
     //get the read positon to just before the line we want
     for (i = 1; i < choice; i++)
     {
         prompt.nextLine();
     }

     //read in the line we want and close the Scanner
     word = prompt.nextLine();
     prompt.close();

     //return result
     return word;
    }

    public void pluralize(File f) throws IOException
    {
        PrintWriter pw = new PrintWriter("plural.txt");
        Scanner read = new Scanner(f);
        String current;
        String[] ending;

        if (!f.exists())
        {
            System.out.println("ERROR! FILE DOESN'T EXIST AT pluralize()");
            System.exit(0);
        }

        while (read.hasNext())
        {
            current = read.nextLine();
            ending = getEnding(current);
            pw.println(putEnding(current, ending));
        }

        read.close();
        pw.close();
    }

    public boolean endsInVowel(String current)
    {
        if (current.charAt(current.length() - 1) == 'a' || current.charAt(current.length() - 1) == 'e' || current.charAt(current.length() - 1) == 'i' || current.charAt(current.length() - 1) == 'o' || current.charAt(current.length() - 1) == 'u')
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public char letterFromLast(String b, int a)
    {
        return b.charAt(b.length() - (a+1));
    }

    public String putEnding(String a, String[] aa)
    { 
        System.out.println("Word:" + a + ", Ending: " + aa[0] + ", Type: " + aa[1]);
        if (aa[1].equals("add"))
        {
            return a + aa[0];
        }
        else if (aa[1].equals("replace1"))
        {
            String aaa = a.substring(0, a.length() - 1) + aa[0];
            return aaa;
        }
        else
        {
            String aaa = a.substring(0, a.length() - 2) + aa[0];
            return aaa;
        }
    }

    public String[] getEnding(String a)
    { 
        a = a.toLowerCase();
        String[] aa = new String[2];
        if (letterFromLast(a, 0) == 'x' || letterFromLast(a, 0) == 's' || letterFromLast(a, 0) == 'z' || (letterFromLast(a, 0) == 'h' && (letterFromLast(a, 1) == 'c' || letterFromLast(a, 1) == 's')))
        {
            aa[0] = "es";
            aa[1] = "add";
        }
        else if (isConsonant(letterFromLast(a, 1)) && letterFromLast(a, 0) == 'y')
        {
            aa[0] = "ies";
            aa[1] = "replace1";
        }
        else if (isConsonant(letterFromLast(a, 1)) &&  letterFromLast(a, 0) == 'o')
        {
            aa[0] = "es";
            aa[1] = "add";
        }
        else if (letterFromLast(a, 0) == 's' && letterFromLast(a, 1) == 'u')
        {
            aa[0] = "i";
            aa[1] = "replace2";
        }
        else if (letterFromLast(a, 0) == 's' && letterFromLast(a, 1) == 'i')
        {
            aa[0] = "es";
            aa[1] = "replace2";
        }
        else
        {
            aa[0] = "s";
            aa[1] = "add";
        }

        return aa;
    } 

    public boolean isConsonant(char a)
    {
        if (!(a == 'a' || a == 'e' || a == 'i' || a =='o' || a == 'u'))
            return true;
        else
            return false;
    }
}