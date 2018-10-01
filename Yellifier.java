import java.util.Scanner;

class Yellifier {
  
  private Scanner keyboard = new Scanner(System.in);
  private static final String EXCLAIM_MARKS = "!!!";
  
  public static void main(final String[] args) {
    new Yellifier().run();
  }
  
  public void run() {
    
    String response = thingToYellify();
    
    String yellifiedResponse = yellify(response);
    
    System.out.println(yellifiedResponse);
   
  }
  
  private String thingToYellify() {
    System.out.print("> ");
    return keyboard.nextLine();
  }
  
  private String yellify(String s) {
    String result = s;
    result = result.toUpperCase();
    result = result.trim();
    result = EXCLAIM_MARKS + result + EXCLAIM_MARKS;
    return result;
  }
  
}