import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class SentenceMakerLoop
{
   public static void main(String[] args) throws IOException
   {
      File pronouns = new File("pronouns.txt");
      File verbs = new File("verbs.txt");
      File nouns = new File("nouns.txt");     
      Scanner prompt = new Scanner(System.in); 
      int pronounsLength = 0, verbsLength = 0, nounsLength = 0;
      int pronounsChoice, verbsChoice, nounsChoice;
      int i;
      Random rand = new Random();
      String pronoun, verb, noun;
      String response;
      
      
      if (!pronouns.exists() || !verbs.exists() || !nouns.exists())
      {
         System.out.println("ERROR: ONE OF THE FILES HAS BEEN DELETED. PLEASE CHECK TO MAKE SURE THE APPROPIATE FILES ARE IN THE SAME DIRECTORY.");
         System.out.println("Needed Files:\n\tprounouns.txt\n\tverbs.txt\n\tnouns.txt");
         System.exit(0);
      }  
      
      Scanner pronounsScanner = new Scanner(pronouns);
      Scanner verbsScanner = new Scanner(verbs);
      Scanner nounsScanner = new Scanner(nouns);
         
      while (pronounsScanner.hasNext())
      {
         pronounsLength++;
         pronounsScanner.nextLine();
      }
         
      while (verbsScanner.hasNext())
      {
         verbsLength++;
         verbsScanner.nextLine();
      }
         
      while (nounsScanner.hasNext())
      {
         nounsLength++;
         nounsScanner.nextLine();
      }
      
      do
      {
         pronounsScanner = new Scanner(pronouns);
         verbsScanner = new Scanner(verbs);
         nounsScanner = new Scanner(nouns);
         
         pronounsChoice = rand.nextInt(pronounsLength) + 1;
         verbsChoice = rand.nextInt(verbsLength) + 1;
         nounsChoice = rand.nextInt(nounsLength) + 1;
         
         for (i = 1; i < pronounsChoice; i++)
         {
            pronounsScanner.nextLine();
         }
         
         for (i = 1; i <verbsChoice; i++)
         {
            verbsScanner.nextLine();
         }
         
         for (i = 1; i < nounsChoice; i++)
         {
            nounsScanner.nextLine();
         }
         
         pronoun = pronounsScanner.nextLine();
         verb = verbsScanner.nextLine();
         noun = nounsScanner.nextLine();
         
         System.out.println(pronoun + " " + verb + " " + noun + ".");
         
         do
         {
            response = (prompt.nextLine()).toLowerCase() + " ";
         }
         while (response.equals(""));
      }
      while (!(response.charAt(0) == 'n'));
   }
}