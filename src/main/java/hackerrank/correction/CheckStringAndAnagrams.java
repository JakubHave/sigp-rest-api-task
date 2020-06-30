package hackerrank.correction;

import java.util.*;

public class CheckStringAndAnagrams {

    /**
     * This Method checks the parity of parenthesis, braces and brackets in the string.
     *
     * @param text a string to check
     * @return boolean value, true if string has valid parity, otherwise false
     */
    public static boolean checkString(String text) {
        /**
        * nothing to do
        */
        if(text.length()==0) return true;

        char[] chars = text.toCharArray();
        /**
        * this is LIFO stack for adding opening chars: '(', '{', '['
        */
        ArrayDeque<Character> specialChars = new ArrayDeque<>();
        for (char ch : chars) {
            if (ch == '(' || ch == '{' || ch == '[') {
                specialChars.push(ch);
            }
            if (ch == ')' || ch == '}' || ch == ']') {
                /**
                * get/remove the head from the stack
                */
                Character head = specialChars.poll();
                /**
                * if the head of the stack with opening chars '(', '{', '[' does not match to the closing char,
                * return false immediately
                */
                if(head == null || head != getOpeningPair(ch)){
                    return false;
                }
            }
        }
        /**
        * if the parity of opening and closing chars is valid, the stack with opening chars is empty -> return true,
        * otherwise return false
        */
        return specialChars.isEmpty();
    }

    private static char getOpeningPair(char ch){
        switch (ch){
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                throw new RuntimeException("No matching opening char!");
        }
    }


    /**
     * This Method filters (keeps only unique anagrams) and sorts a list of strings.
     *
     * @param text a list of strings to filter and sort
     * @return filtered and sorted list of unique anagrams
     */
    public static List<String> funWithAnagrams(List<String> text) {
        List<String> result = new ArrayList<>();
        /**
        * this set will contain unique anagrams with alphabetically sorted chars
        */
        Set<String> uniqueAnags = new HashSet<>();
        String str;
        for(int i = 0; i < text.size(); i++){
            str = text.get(i);
            /**
            * if the set does not contain the string converted to "base" anagram
            * we will add it to the result, otherwise skip this string as it is already there
            */
            if(uniqueAnags.add(toBaseAnagram(str))){
                result.add(str);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
    * Returns "base" anagram out of string by alphabetical sorting of its chars
    */
    private static String toBaseAnagram(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
