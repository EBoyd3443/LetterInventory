/**
 * SDEV 301
 *
 * @author Eric Boyd
 * @version 1.0
 */

package inventory;

/**
 * This class represents an inventory of the 26 letters in the English alphabet.
 * A LetterInventory object keeps track of how many a’s, how many b’s, etc.
 * are contained in the inventory. This object stores the counts of the letters
 * in an integer array with a maximum count of Short.MAX_VALUE for each letter
 * For example:
 * the letter inventory for the string “WashingtonState” corresponds to
 * [aaeghinnosstttw] --> String representation of the inventory
 * [2,0,0,0,1,0,1,1,1,0,0,0,0,2,1,0,0,0,2,3,0,0,1,0,0,0] --> inventory count array
 * [a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z] --> corresponding letters
 * The case of the letter is ignored, so ‘s’ and ‘S’ are exactly the same.
 *
 */

public class LetterInventory extends Throwable  {

  private short[] inventory; // inventory is null here
  public static final byte ALPHABET_SIZE = 26;
  private static final int UNICODE_OF_a = (int)'a';
  private static final int UNICODE_OF_z = (int)'z';
  private static final int CAPITAL_TO_LOWER_OFFSET = 32;

  /**
   * Constructs an integer array for the size of the alphabet.
   * All letter counts are initialized to zero.
   */
  public LetterInventory()
  {
    inventory = new short[ALPHABET_SIZE];
  }
  /**
   * Constructs an integer array for the size of the alphabet.
   * Each element in the array should hold a 16-bit integer
   * and adds each character in the text to the inventory
   * @param text
   */
  public LetterInventory(String text)
  {
    //inventory = new short[ALPHABET_SIZE];
    this();

    for(int i = 0; i < text.length(); i++)
    {
      add(text.charAt(i));
    }
  }

    /**
   * Identifies the index for the given character within the inventory array , throws an
   * IIegalArgumentException if the character is not in the a-z or A-Z range.
   * For example: if the given character is 'c' or 'C', then the index returned is 2
   *              if the given character is '?', then an IllegalArgumentException is thrown
   *
   * @param c a-z or A-Z character
   * @return index of the character
   */
  public int getIndex(char c) throws IllegalArgumentException
  {
    int unicodeValue = (int) c;

    if(unicodeValue < UNICODE_OF_a)
    {
      unicodeValue += CAPITAL_TO_LOWER_OFFSET;
    }
    if(unicodeValue > UNICODE_OF_z || unicodeValue < UNICODE_OF_a)
    {
      throw new IllegalArgumentException("Not a valid Letter");
    }
    return unicodeValue - UNICODE_OF_a;
  }

  /**
   * Increases the count for the given character in the inventory
   * @param c a-z or A-Z otherwise an IllegalArgumentException is thrown
   */
  public void add(char c)
  {
      inventory[getIndex(c)] ++;
  }

  /**
   * Decreases the count for the given character in the inventory
   * @param c a-z or A-Z otherwise an IllegalArgumentException is thrown
   */
  public void subtract(char c)
  {
    inventory[getIndex(c)] --;
  }

  /**
   * Returns the count for the given character in the inventory
   * @param c a-z or A-Z otherwise an IllegalArgumentException is thrown
   */
  public int get(char c)
  {
    return inventory[getIndex(c)];
  }

  /**
   * Sets the count for the given character in the inventory
   * @param c a-z or A-Z otherwise an IllegalArgumentException is thrown
   * @param count the number of occurrences of the character c; if count < 0
   *              IllegalArgumentException is thrown
   */
  public void set(char c, short count) throws IllegalArgumentException
  {
    if(count < 0)
    {
      throw new IllegalArgumentException("Cannot set to Negative");
    }
    inventory[getIndex(c)] = count;
  }

  /**
   * Determines if a character's count is in the inventory
   * @param c a-z or A-Z otherwise an IllegalArgumentException is thrown
   * @return true if character is in inventory, false otherwise
   */
  public boolean contains(char c) {
    boolean containsLetter = false;
    if(inventory[getIndex(c)] > 0)
    {
      containsLetter = true;
    }
    return containsLetter;
  }

  /**
   * Return the total count of all letters in the inventory
   * @return total count
   */
  public int size()
  {
    int count = 0;
    for(int i = 0; i < inventory.length; i++)
    {
      count += inventory[i];
    }
    return count;
  }

  /**
   * Determine if the inventory has zero counts for all letters
   * @return true, if empty, false otherwise
   */
  public boolean isEmpty() {
    boolean noLetters = true;
    for(int i = 0; i < inventory.length; i++)
    {
      if(inventory[i] > 0)
      {
        noLetters = false;
      }
    }
    return noLetters;
  }

  /**
   * Returns a String representation of the inventory with the letters all in
   * lowercase
   * surrounded by square brackets in alphabetical order. The number of
   * occurrences of
   * each letter matches its count in the inventory.
   * For example, an inventory of 4 a’s, 1 b, 1 k and 1 m would be represented as
   * “[aaaabkm]”.
   *
   * @return a bracketed string representation of the letters contained in the
   *         inventory
   */
  public String toString() {
    // If you are concatenating many strings together, StringBuilder class
    // is often more efficient
    StringBuilder toReturn = new StringBuilder("[");

    // for every count in the letters inventory
    for (int i = 0; i < inventory.length; i++) {
      // add each character to the string count times
      for (int count = 0; count < inventory[i]; count++) {
        // ascii math performed here
        // Example:
        // 'a' + 0 = 'a'
        // 'a' + 1 = 'b'
        // 'a' + 2 = 'c'
        // 'a' + 25 = 'z'
        toReturn.append((char) ('a' + i));
      }
    }
    return toReturn.append("]").toString();
  }
}
