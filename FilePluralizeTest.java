import java.io.File;
import java.io.IOException;

public class FilePluralizeTest
{
    public static void main(String[] args) throws IOException
    {
        Sentence s = new Sentence();
        s.pluralize(new File("singularVerbs.txt"));
    }
}