package Kaufvertrag.Kaufvertrag;

public class Programm
{
  private static IApplication inputMethod;

  public static void main(String[] args)
  {
    for (String s : args)
    {
      if ("-Console".equals(s))
      {
        inputMethod = inputMethod == null ? new ConsoleApplication() : inputMethod;
      } else if ("-GUI".equals(s))
      {
        inputMethod = inputMethod == null ? new WindowApplication() : inputMethod;
      }
    }
    if (inputMethod == null)
    {
      System.out.println("The input method is not Specified.\nPlease start the program with '-Console' or '-GUI' to specify the input method.\n");
      return;
    }
    inputMethod.startApplication();
  }

  public static IApplication getInputMethod()
  {
    return inputMethod;
  }
}
