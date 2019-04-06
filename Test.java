import java.io.File;
import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException
    {
        Sentence s = new Sentence();
        s.pluralize(new File("singularPronouns.txt"));
        //System.out.println(s.generateSimpleSentence());
    }
}