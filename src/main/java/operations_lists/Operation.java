package operations_lists;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation
{
    static List<Character> order = new ArrayList<>();

    static {
        order.add('I');
        order.add('V');
        order.add('X');
        order.add('L');
        order.add('C');
        order.add('D');
        order.add('M');
    }

    public Operation() {
    }

    public abstract String calculate(String a, String b);

    public boolean isValidRomanNumeral(String roman)
    {
        return roman.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public static String sort(String number)
    {
        List<Character> charList = new ArrayList<>();
        for (char c : number.toCharArray()) {
            charList.add(c);
        }

        // sort the list based on the roman numeral order
        for (int i = 0; i < charList.size() - 1; i++)
        {
            for (int j = i + 1; j < charList.size(); j++)
            {
                if (getRomanOrderIndex(charList.get(i)) < getRomanOrderIndex(charList.get(j)))
                {
                    char temp = charList.get(i);
                    charList.set(i, charList.get(j));
                    charList.set(j, temp);
                }
            }
        }

        StringBuilder sortedRoman = new StringBuilder();
        for (char c : charList)
        {
            sortedRoman.append(c);
        }

        return sortedRoman.toString();
    }

    public static int getRomanOrderIndex(char c)
    {
        // find the index of the character in the order list
        for (int i = 0; i < order.size(); i++)
        {
            if (order.get(i) == c)
            {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral character: " + c);
    }

    public static String replaceSubtractiveSymbols(String number)
    {
        List<Character> charList = new ArrayList<>();
        for (char c : number.toCharArray())
        {
            charList.add(c);
        }

        replaceSequence(charList, "IV", "IIII");
        replaceSequence(charList, "IX", "VIIII");
        replaceSequence(charList, "XL", "XXXX");
        replaceSequence(charList, "XC", "LXXXX");
        replaceSequence(charList, "CD", "CCCC");
        replaceSequence(charList, "CM", "DCCCC");

        StringBuilder result = new StringBuilder();
        for (char c : charList)
        {
            result.append(c);
        }

        return result.toString();
    }

    private static void replaceSequence(List<Character> charList, String oldSeq, String newSeq)
    {
        int index = 0;
        while ((index = findSequence(charList, oldSeq, index)) != -1)
        {
            for (int i = 0; i < oldSeq.length(); i++)
            {
                charList.remove(index);
            }
            for (int i = newSeq.length() - 1; i >= 0; i--)
            {
                charList.add(index, newSeq.charAt(i));
            }
            index += newSeq.length();
        }
    }

    private static int findSequence(List<Character> charList, String sequence, int startIndex)
    {
        for (int i = startIndex; i <= charList.size() - sequence.length(); i++)
        {
            boolean match = true;
            for (int j = 0; j < sequence.length(); j++)
            {
                if (charList.get(i + j) != sequence.charAt(j))
                {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    public static String replaceDuplicates(String number)
    {
        List<Character> charList = new ArrayList<>();
        for (char c : number.toCharArray())
        {
            charList.add(c);
        }

        replaceSequence(charList, "IIIII", "V");
        replaceSequence(charList, "VV", "X");
        replaceSequence(charList, "VIIII", "IX");
        replaceSequence(charList, "XXXXX", "L");
        replaceSequence(charList, "LL", "C");
        replaceSequence(charList, "LXXXX", "XC");
        replaceSequence(charList, "CCCCC", "D");
        replaceSequence(charList, "DD", "M");
        replaceSequence(charList, "IIII", "IV");
        replaceSequence(charList, "XXXX", "XL");
        replaceSequence(charList, "DCCCC", "CM");
        replaceSequence(charList, "CCCC", "CD");

        StringBuilder result = new StringBuilder();
        for (char c : charList)
        {
            result.append(c);
        }

        return result.toString();
    }
}
