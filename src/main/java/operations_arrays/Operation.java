package operations_arrays;

public abstract class Operation
{
    static char[] order = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    public Operation() {
    }

    public abstract String calculate(String a, String b);

    public static String replaceSubtractiveSymbols(String number)
    {
        char[] charArray = number.toCharArray();

        charArray = replaceSequence(charArray, "IV", "IIII");
        charArray = replaceSequence(charArray, "IX", "VIIII");
        charArray = replaceSequence(charArray, "XL", "XXXX");
        charArray = replaceSequence(charArray, "XC", "LXXXX");
        charArray = replaceSequence(charArray, "CD", "CCCC");
        charArray = replaceSequence(charArray, "CM", "DCCCC");

        return new String(charArray);
    }

    private static char[] replaceSequence(char[] charArray, String oldSeq, String newSeq)
    {
        String strArray = new String(charArray);
        strArray = strArray.replaceAll(oldSeq, newSeq);
        return strArray.toCharArray();
    }

    public boolean isValidRomanNumeral(String roman)
    {
        return roman.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public static String sort(String number)
    {
        char[] charArray = number.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++)
        {
            for (int j = i + 1; j < charArray.length; j++)
            {
                if (getRomanOrderIndex(charArray[i]) < getRomanOrderIndex(charArray[j]))
                {
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }

        return new String(charArray);
    }

    public static int getRomanOrderIndex(char c)
    {
        for (int i = 0; i < order.length; i++)
        {
            if (order[i] == c)
            {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral character: " + c);
    }

    public static String replaceDuplicates(String number)
    {
        char[] charArray = number.toCharArray();

        charArray = replaceSequence(charArray, "IIIII", "V");
        charArray = replaceSequence(charArray, "VV", "X");
        charArray = replaceSequence(charArray, "VIIII", "IX");
        charArray = replaceSequence(charArray, "XXXXX", "L");
        charArray = replaceSequence(charArray, "LL", "C");
        charArray = replaceSequence(charArray, "LXXXX", "XC");
        charArray = replaceSequence(charArray, "CCCCC", "D");
        charArray = replaceSequence(charArray, "DD", "M");
        charArray = replaceSequence(charArray, "IIII", "IV");
        charArray = replaceSequence(charArray, "XXXX", "XL");
        charArray = replaceSequence(charArray, "DCCCC", "CM");
        charArray = replaceSequence(charArray, "CCCC", "CD");

        return new String(charArray);
    }
}
