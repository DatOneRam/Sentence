//Sentence Class
//Started By Ramsey Alsheikh
//Released Under The FreeBSD Copyright
//OBJECTIVE: To make a class that provides useful utilities towards sentences, including random sentence generation

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Sentence
{
    //Files with lists of words
    private File singularPronouns;
    private File nouns;
    private File singularVerbs;
    private File pluralVerbs;

    //defined constructor
    //sets up the files with filenames given by program
    public Sentence(String n, String v, String h, String a)
    {
        singularPronouns = new File(n);
        singularVerbs= new File(v);
        pluralVerbs = new File(h);
        nouns = new File(a);
    }

    //default constructor
    //sets up the files with default filenames
    public Sentence()
    {
        singularPronouns = new File("singularPronouns.txt");
        singularVerbs= new File("singularVerbs.txt");
        pluralVerbs = new File("pluralVerbs.txt");
        nouns = new File("nouns.txt");
    }

    //method to make a random, simple sentence
    public String generateSimpleSentence() throws IOException
    {
        String article1 = "blank", start = "blank", verb = "blank", noun = "blank", article2= "blank";
        Random rand = new Random();

        //randomly set the flags
        int startPronoun = rand.nextInt(2);
        int startNoun = rand.nextInt(2);
        int startProperNoun = rand.nextInt(2);
        int singularStart = rand.nextInt(2);

        //if the sentence is to start with a sngular word, make the verb plural
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

    //method to pluralize a file given
    public void pluralize(File f) throws IOException
    {
        PrintWriter pw = new PrintWriter("plural.txt");
        Scanner read = new Scanner(f);
        String current;
        String[] ending;

        //if the file doesn't exist, end their program for their mediocracy
        if (!f.exists())
        {
            System.out.println("ERROR! FILE DOESN'T EXIST AT pluralize()");
            System.exit(0);
        }

        //while there are lines to read, read them, make them plural, and put them in a new file
        while (read.hasNext())
        {
            current = read.nextLine();
            ending = getPluralEnding(current);
            pw.println(putEnding(current, ending));
        }

        read.close();
        pw.close();
    }

    //method to tell if a word endsin a vowel
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

    //method to get the letter a digits from the last letter in String b
    //EXAMPLE USE
    //String hoo = "owlowl";
    //char ch = letterFromLast(hoo, 3);
    //char now holds 'l'
    public char letterFromLast(String b, int a)
    {
        return b.charAt(b.length() - (a+1));
    }

    //method to add an ending to a word
    public String putEnding(String a, String[] aa)
    { 
        //uncomment for debugging if needed
        //System.out.println("Word:" + a + ", Ending: " + aa[0] + ", Type: " + aa[1]);
        
        //if you are just adding the ending, just add it to the end of the word
        if (aa[1].equals("add"))
        {
            return a + aa[0];
        }
        //if the ending takes place of the last letter, do that
        else if (aa[1].equals("replace1"))
        {
            String aaa = a.substring(0, a.length() - 1) + aa[0];
            return aaa;
        }
        //otherwise, the ending takes place of the last two letters
        else
        {
            String aaa = a.substring(0, a.length() - 2) + aa[0];
            return aaa;
        }
    }

    //method to get the apprpopiate ending for the plural of a word
    public String[] getPluralEnding(String a)
    { 
        a = a.toLowerCase();
        String[] aa = new String[2];

        //if statements reflect the ruls of grammers regardign plural endings...
        //NOTE: THIS METHOD IS IN NO MEANS PERFECT AND IS STILL A WORK IN PROGRESS
        //ANY HELP IS APPRECIATED!
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

    //method to check if a letter is a consonant
    public boolean isConsonant(char a)
    {
        if (!(a == 'a' || a == 'e' || a == 'i' || a =='o' || a == 'u'))
            return true;
        else
            return false;
    }
}