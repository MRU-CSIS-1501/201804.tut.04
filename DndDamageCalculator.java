import java.util.Scanner;
import java.util.Collections;

class DndDamageCalculator {

    private final Scanner kbd = new Scanner(System.in);

    private static final String DEFAULT_CLASS = "Fighter";
    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_STR = 9;
    private static final int DEFAULT_DEX = 9;

    public DndDamageCalculator() { }

    public static void main(final String[] args) {
        new DndDamageCalculator().run();
    }

    public void run() {
        displayWelcome();
        displayBlankLines(1);

        String characterClass = characterClassOrDefaultFromUser();
        int characterLevel = characterLevelOrDefaultFromUser();
        int characterStr = characterStrengthOrDefaultFromUser();
        int characterDex = characterDexterityOrDefaultFromUser();

        displayBlankLines(2);
        displayTableMessage(characterClass, characterLevel, characterStr, characterDex);
        displayBlankLines(1);
        displayTable(characterClass, characterLevel, characterStr, characterDex);
    }


    private void displayWelcome() {
        System.out.println("Welcome to the DnD Damage Calculator.");
    }


    /**
       Prints out a given number of blank lines (newlines) to console.

       Yes, we can use a loop here. I just wanted to try out some
       Java 8 stuff. It's fun, but funky.
     */
    private void displayBlankLines(final int numLines) {
        final java.util.List<String> newLines =
            Collections.nCopies(numLines, String.format("%n"));
        System.out.print(String.join("", newLines));
    }


    /**
       Returns the character class chosen by the user, or the default
       such class if they entered an invalid class.
     */
    private String characterClassOrDefaultFromUser() {
        final String prompt = "What is the character's class " +
            " [(C)leric, (F)ighter, (R)ogue, (W)izard ]? ";

        char response = characterFromUser(prompt);

        if (!isAllowableCharacterClass(response)) {
            displayDefaultClassWarning(response);
        }

        return fullCharacterClass(response);
    }


    /**
       Returns the character level chosen by the user, or the default
       such level if they entered an invalid level.
     */
    private int characterLevelOrDefaultFromUser() {
        final int lowBound = DndRulesHelper.MIN_ALLOWABLE_LEVEL;
        final int highBound = DndRulesHelper.MAX_ALLOWABLE_LEVEL;

        final String msg = "What is the character's level [ %d-%d ]? ";
        final String prompt = String.format(msg, lowBound, highBound);
        final int defaultLevel = DEFAULT_LEVEL;

        return intInRangeOrDefault(prompt, lowBound, highBound, defaultLevel);
    }


    /**
       Returns the character strength chosen by the user, or the default
       such strength if they entered an invalid one.
     */
    private int characterStrengthOrDefaultFromUser() {
        final int lowBound = DndRulesHelper.MIN_ALLOWABLE_STR;
        final int highBound = DndRulesHelper.MAX_ALLOWABLE_STR;

        final String msg = "What is the character's strength [ %d-%d ]? ";
        final String prompt = String.format(msg, lowBound, highBound);
        final int defaultLevel = DEFAULT_STR;

        return intInRangeOrDefault(prompt, lowBound, highBound, defaultLevel);
    }


    /**
       Returns the character dexterity chosen by the user, or the default
       such dexterity if they entered an invalid one.
     */
    private int characterDexterityOrDefaultFromUser() {
        final int lowBound = DndRulesHelper.MIN_ALLOWABLE_DEX;
        final int highBound = DndRulesHelper.MAX_ALLOWABLE_DEX;

        final String msg = "What is the character's dexterity [ %d-%d ]? ";
        final String prompt = String.format(msg, lowBound, highBound);
        final int defaultLevel = DEFAULT_DEX;

        return intInRangeOrDefault(prompt, lowBound, highBound, defaultLevel);
    }

    /**
       Returns an integer chosen by the user, or a default value if the
       chosen integer is not between a provided low and high value, inclusive.

       If the integer is *not* in bounds, a warning message to this effect
       is provided.
     */
    private int intInRangeOrDefault(final String prompt, final int lowVal,
                                    final int highVal, final int defaultVal) {
        int validatedInt = intFromUser(prompt);
        if (!inBounds(validatedInt, lowVal, highVal)) {
            final String warning = "\t%d is not within the acceptable range " +
                "of [%d - %d]. Using default value of %d instead.%n";
            System.out.printf(warning, validatedInt, lowVal, highVal, defaultVal);
            validatedInt = defaultVal;
        }
        return validatedInt;
    }


    /**
       Returns true iff a given value is in [low, high].
     */
    private boolean inBounds(final int val, final int low, final int high) {
        return val >= low && val <= high;
    }


    /**
       Prompts for and gets a character from the user via the
       keyboard. If more that one character if entered, only
       the first is returned.
     */
    private char characterFromUser(final String prompt) {
        System.out.print(prompt);

        return kbd.nextLine().charAt(0);
    }


    /**
       Returns the full character class name associatied with a
       given abbreviated form. The case of the abbreviated form
       doesn't matter.

       If the abbreviated form is not recognized, whatever the
       current DEFAULT_CLASS is will be returned instead.
     */
    private String fullCharacterClass(final char abbrevCharClass) {

        String fullClass = "Invalid Character Class";
        char upCasedAbbrevCharClass = Character.toUpperCase(abbrevCharClass);

        switch (upCasedAbbrevCharClass) {
            case 'C':
                fullClass = DndRulesHelper.CLERIC;
                break;

            case 'R':
                fullClass = DndRulesHelper.ROGUE;
                break;

            case 'W':
                fullClass = DndRulesHelper.WIZARD;
                break;

            case 'F':
                fullClass = DndRulesHelper.FIGHTER;
                break;

            default:
                fullClass = DEFAULT_CLASS;
                break;
        }

        return fullClass;
    }


    /**
       Displays a warning that a given abbreviated character class has
       not been recognized and that a default class will be used because
       of that.
     */
    private void displayDefaultClassWarning(final char invalidResponse) {
        final String msg = "\t'" + invalidResponse + "' is not a valid " +
            " character class. Using the default of " + DEFAULT_CLASS +
            " instead.";
        System.out.println(msg);
    }


    /**
       TODO:

       Returns whether a provided abbreviated character class is a
       recognized one. Case insensitive.

       For this app, the only allowable abbreviations are C,F,R, and W.
     */
    private boolean isAllowableCharacterClass(final char abbrevCharClass) {
        return true;
    }


    /**
       Prompts for, obtains via keyboard, and returns an integer.

       No error checking is performed - if it's not an int, goes boom.
     */
    private int intFromUser(final String prompt) {
        System.out.print(prompt);
        return kbd.nextInt();
    }


    /**
       TODO:

       Displays a message about the class, level, strength, and dexterity of
       a character.

       The level part of the message must use the proper ordinal ('3rd' instead
       of 3, for example) word.
     */
    private void displayTableMessage(final String characterClass,
                                     final int characterLevel,
                                     final int characterStr,
                                     final int characterDex) {
        System.out.println("Here is the table for a 1st level Fighter (prof +2) with 18 STR(+4) and 17 DEX(+3):");

    }

    /**
       TODO:

       Returns the ordinal (1st instead of 1, 5th instead of 5) for a given
       number.

       We only care about the numbers from 1 to 20. If it's a number above that,
       just assume it ends with "th".
     */
    private String ordinalFromNumber(final int num) {
        return "1st";
    }


    /**
       Displays the tohit/damage table for four different weapons: dagger,
       sickle, maul, and rapier.
     */
    private void displayTable(final String characterClass, final int characterLevel,
                              final int characterStr, final int characterDex) {

        displayHeader();
        displayOneRow("Dagger",
                      characterClass, characterLevel, characterStr, characterDex);
        displayOneRow("Sickle",
                      characterClass, characterLevel, characterStr, characterDex);
        displayOneRow("Maul",
                      characterClass, characterLevel, characterStr, characterDex);
        displayOneRow("Rapier",
                      characterClass, characterLevel, characterStr, characterDex);
    }


    /**
       Displays the header for the weapon tohit/damage table.
     */
    private void displayHeader() {
        System.out.println("Weapon     | Attack Roll Bonus | Damage (Type) | Notes");
        System.out.println("-----------+-------------------+---------------+----------");
    }


    /**
       TODO:

       Displays a single row in the tohit/damage table.

       Calculations of the attack roll bonus and damage is based on the character
       using the weapon. Details of these calculations can be found in the freely
       available basic rules (http://dnd.wizards.com/articles/features/basicrules)
       but here's the gist of it:

       Let's deal with non-finesse weapons first. For these, we calculate the
       bonus by adding the strength ability modifier to the proficiency
       bonus. The damage is calculated by taking the basic damage for that
       weapon and adding the strength ability modifier to it.

       With finesse weapons, the calculations are the same - but if the dexterity
       ability modifier is greater than the strength modifier, you use the
       dex modifier. (The idea here is that for certain weapons, agility is
       more important than brute strength.)

       The Notes column will display the category of weapon (simple or martial),
       and if the character is proficient in that weapon, there should be an
       * next to the category.
     */
    private void displayOneRow(final String weaponName, final String characterClass,
                               final int characterLevel, final int characterStr,
                               final int characterDex) {

        final String msg = "%-11s| %-+18d| %-14s| %s%s%n";
        System.out.printf(msg, "Finger", 4, "1-3 (P)", "Simple", "*");
    }
}