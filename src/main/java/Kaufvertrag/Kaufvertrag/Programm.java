package Kaufvertrag.Kaufvertrag;

public class Programm
{
    private static IApplication inputMethod;
    public static void main(String[] args)
    {
        for(String s : args)
        {
            if ("-Console".equals(s))
            {
                inputMethod = inputMethod == null ? new ConsoleApplication() : inputMethod;
            }
            else if ("-GUI".equals(s))
            {
                inputMethod = inputMethod == null ? new WindowApplication() : inputMethod;
            }
        }
        inputMethod.startApplication();
    }

    public static String getPersistenceType()
    {
        return inputMethod.getPersistenceType();
    }
}
