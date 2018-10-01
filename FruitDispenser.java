import java.util.Scanner;

class FruitDispenser {
  
  private Scanner keyboard = new Scanner(System.in);
  
  public static void main(final String[] args) {
    new FruitDispenser().run();
  }
  
  public void run() {
    
    int numberPiecesFruit = integerFromUser("How many pieces of fruit are there? ");
    int numberTeamMembers = integerFromUser("How many team members are there other than you? ");
  
    int numberFruitPerMember = numberFruitPerMember(numberPiecesFruit, numberTeamMembers);
    int numberFruitRemaining = numberFruitRemaining(numberPiecesFruit, numberTeamMembers);
    
    System.out.println();
    
    displayFruitGiven(numberFruitPerMember);
    displayFruitRemaining(numberFruitRemaining);
   
  }
  
  private int integerFromUser(String prompt) {
    System.out.print(prompt);
    return keyboard.nextInt();
  }
  
  private int numberFruitPerMember(int totalFruit, int numMembers) {
    return totalFruit / numMembers;
  }
  
  private int numberFruitRemaining(int numberPiecesFruit, int numberTeamMembers) {
    return numberPiecesFruit % numberTeamMembers;
  }
  
  private void displayFruitGiven(int numFruit) {
    System.out.printf("Each group member gets %d pieces of fruit.%n", numFruit);
  }
  
  private void displayFruitRemaining(int numFruit) {
    System.out.printf("There are %d pieces of fruit remaining.%n", numFruit);
  }
  
}