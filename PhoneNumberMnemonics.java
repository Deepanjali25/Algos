/* If you open the keypad of your mobile phone, it'll likely look like this:
 ----- ----- -----
|     |     |     |
|  1  |  2  |  3  |
|     | abc | def |
 ----- ----- -----
|     |     |     |
|  4  |  5  |  6  |
| ghi | jkl | mno |
 ----- ----- -----
|     |     |     |
|  7  |  8  |  9  |
| pqrs| tuv |  wxyz|
 ----- ----- -----
      |     |
      |  0  |
      |     |
       -----
Almost every digit is associated with some letters in the alphabet; this allows certain phone numbers to
spell out actual words. For example, the phone number 8464747328 can be written as timisgreat;
similarly, the phone number 2686463 can be written as antoine or as ant6463.
It's important to note that a phone number doesn't represent a single sequence of letters, but rather
multiple combinations of letters. For instance, the digit 2 can represent three different letters (a, b, and c).
A mnemonic is defined as a pattern of letters, ideas, or associations that assist in remembering something.
Companies oftentimes use a mnemonic for their phone number to make it easier to remember.
Given a stringified phone number of any non-zero length, write a function that returns all mnemonics for
this phone number, in any order.
For this problem, a valid mnemonic may only contain letters and the digits 0 and 1 . In other words, if a
digit is able to be represented by a letter, then it must be. Digits 0 and 1 are the only two digits that
don't have letter representations on the keypad. i
Note that you should rely on the keypad illustrated above for digit-letter associations.
Sample Input: phoneNumber = "1905"
Sample Output: ["1w0j", "1w0k", "1w0l", "1x0j", "1x0k", "1x0l", "1y0j", "1y0k", "1y0l", "1z0j", "1z0k", "1z0l",]
// The mnemonics could be ordered differently
#Hint1 - The first thing you'll need to do is create a mapping from digits to letters. You can do this by creating a hash table
mapping all string digits to lists of their respective characters
#Hint2 - This problem can be solved fairly easily using recursion. Try generating all characters for the first digit
in the phone number one at a time, and for each character, recursively performing the same action
on the next digit, and then on the digit after that, and so on and so forth until you've done so for
all digits in the phone number.
#Hint3 - You can recursively generate characters one digit at a time and store the intermediate results in a
array. Once you've reached the last digit in the phone number, you can add the currently generated
mnemonic (stored in the previously mentioned array) to a final array that stores all the results.
Optimal Space & Time Complexity - O(4^n * n) time | O(4^n * n) space - where n is the length of the phone number
*/
package Recursion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberMnemonics {
    public static Map<Character, String[]> DIGIT_LETTERS = new HashMap<Character, String[]>();
    static {
        DIGIT_LETTERS.put('0', new String[] {"0"});
        DIGIT_LETTERS.put('1', new String[] {"1"});
        DIGIT_LETTERS.put('2', new String[] {"a", "b", "c"});
        DIGIT_LETTERS.put('3', new String[] {"d", "e", "f"});
        DIGIT_LETTERS.put('4', new String[] {"g", "h", "i"});
        DIGIT_LETTERS.put('5', new String[] {"j", "k", "l"});
        DIGIT_LETTERS.put('6', new String[] {"m", "n", "o"});
        DIGIT_LETTERS.put('7', new String[] {"p", "q", "r", "s"});
        DIGIT_LETTERS.put('8', new String[] {"t", "u", "v"});
        DIGIT_LETTERS.put('9', new String[] {"w", "x", "y", "z"});
    }
    //O(4^n*n) time | O(4^n*n) space - where n is the length of the phone number
    public ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        String[] currentMnemonic = new String[phoneNumber.length()];
        Arrays.fill(currentMnemonic, "0");
        ArrayList<String> mnemonicsFound = new ArrayList<String>();
        phoneNumberMnemonicsHelper(0, phoneNumber, currentMnemonic, mnemonicsFound);
        return mnemonicsFound;
    }
    public void phoneNumberMnemonicsHelper(int idx, String phoneNumber, String[] currentMnemonic,
                                           ArrayList<String> mnemonicsFound) {
        if (idx == phoneNumber.length()) {
            String mnemonic = String.join("", currentMnemonic);
            mnemonicsFound.add(mnemonic);
        }
        else {
            char digit = phoneNumber.charAt(idx);
            String[] letters = DIGIT_LETTERS.get(digit);
            for (String letter : letters) {
                currentMnemonic[idx] = letter;
                phoneNumberMnemonicsHelper(idx + 1, phoneNumber, currentMnemonic, mnemonicsFound);
            }
        }
    }
}