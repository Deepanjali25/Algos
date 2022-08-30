/* Write a SuffixTrie class for a Suffix-Trie-like data structure. The class should have a root property
set to be the root node of the trie and should support:
-Creating the trie from a string; this will be done by calling the populateSuffixTrieFrom method upon class
instantiation, which should populate the root of the class.
-Searching for strings in the trie.
Note that every string added to the trie should end with the special endSymbol character: "*".
Sample input:
string = "babc"
Sample output:
The structure below is the root of the trie.
{
 "c": {"*": true},
 "b": {
   "c": {"*": true},
   "a": {"b": {"c": {"*": true}}},
   },
   "a": {"b": {"c": {"*": true}}},
}
Sample input(for searching in the suffix trie above)
string = "abc"
Sample output(for searching in the suffix trie above)
true
Hint1 Building a suffix-trie-like data structure consists of essentially storing every suffix of a given string
in a trie. To do so, iterate through the input string one character at a time and insert every substring starting
at each character and ending at the end of the string into the trie.
Hint 2 To insert a string into the trie, start by adding the first character of the string into the root node of
the trie and mapping it to an empty hash table if it isn't already there. Then, iterate through the rest of the
string inserting each of the remaining characters into the previous character's corresponding node (or hash table)
in the trie, making sure to add an endSymbol "*" at the end.
Hint3 Searching the trie for a specific string should follow a nearly identical logic to the one used to add a
string in the trie.
Optimal Space & Time Complexity -
Creation: O(n^2) time | O(n^2) space - where n is the length of the input string.
Searching: O(m) time | O(1) space - where m is the length of the input string.*/
package Tries;
import java.util.HashMap;
import java.util.Map;
public class SuffixTrieConstruction {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }
    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';
        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }
        //O(n^2) time | O(n^2) space
        public void populateSuffixTrieFrom(String str) {
            for (int i = 0; i < str.length(); i++) {
                insertSubstringStartingAt(i, str);
            }
        }
        public void insertSubstringStartingAt(int i, String str) {
            TrieNode node = root;
            for (int j = i; j < str.length(); j++) {
                char letter = str.charAt(j);
                if (!node.children.containsKey(letter)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                }
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
        }
        // O(m) time | O(1) space
        public boolean contains(String str) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); i++) {
                char letter = str.charAt(i);
                if (!node.children.containsKey(letter)) {
                    return false;
                }
                node = node.children.get(letter);
            }
            return node.children.containsKey(endSymbol);
        }
    }
}
