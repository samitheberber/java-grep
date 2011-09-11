/* Java-library imports */
import java.io.*;
import java.util.Scanner;

/* Own imports */
import structure.RegularExpression;

public class Grep
{

    public static void main(String[] args)
    {

        /* Check if the cli arguments are empty. If they are empty return the usage message and quit. */
        if ( args.length == 0 ) {
            System.out.println("Usage:\n" +
                                    "\tjava -jar [/path/to/]Grep.jar <regexp> -f <textfile>\n" +
                               "or\n" +
                                    "\tcat <textfile> | java -jar [/path/to/]Grep.jar <regexp>\n\n" +
                               "Supported regular expressions:\n" +
                                    "\tAlnum - write any letter to recognize it.\n" +
                                    "\tDot - write . to make it a placeholder for a letter.\n" +
                                    "\tBrackets - write letters inside [] to make them to be recognize on this place.\n" +
                                    "\tStar - write * to make previous rule assigned 0 to n times.\n" +
                                    "\tPlus - write + to make previous rule assigned 1 to n times.\n" +
                                    "\tStart - write ^ to make next rule haveto match on first letter on the row.\n" +
                                    "\tEnd - write $ to make previous rule haveto match on last letter on the row.");
            return;
        }

        try {

            /* Initialize cli arguments */
            String[] arguments = parseArguments(args);
            String filename = arguments[1];

            /* Select regular expression statement */
            String expression = arguments[0];

            /* Create regular expression */
            RegularExpression.setExpression(expression);

            /* Initialize the system reader */
            BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));

            /* Checks if io stream is ready. If it is not, then reads file */
            if (systemReader.ready())
                readFromSystem(systemReader);
            else
                readFromFile(filename);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void readFromSystem(BufferedReader reader)
        throws Exception
    {
        String currentLine = null;
        while(reader.ready()) {
           if (RegularExpression.checking(currentLine = reader.readLine()))
               System.out.println(currentLine);
        }
    }

    private static void readFromFile(String filename)
        throws Exception
    {
        if (filename == null)
            throw new Exception("File not setted.");

        Scanner fileReader = new Scanner(new File(filename));
        String currentLine = null;

        while (fileReader.hasNextLine()) {
            if (RegularExpression.checking(currentLine = fileReader.nextLine()))
                System.out.println(currentLine);
        }
    }

    private static String[] parseArguments(String[] args)
        throws Exception
    {
        /* Initial needed arguments */
        String regExp = null, filename = null;

        /* Analyze given arguments */
        int argLength = args.length;
        if (argLength == 1) {
            regExp = args[0];
        } else if (argLength == 3) {
            regExp = args[0];
            if (args[1].equals("-f"))
                filename = args[2];
            else
                throw new Exception("Invalid arguments");
        } else
            throw new Exception("Invalid arguments");

        /* Check that regexp isn't empty */
        if (regExp.isEmpty())
            throw new Exception("You need to set regular expression");
        /* Create returned arguments */
        String[] arguments = new String[2];
        arguments[0] = regExp;
        arguments[1] = filename;

        /* Return correct arguments */
        return arguments;
    }

}
