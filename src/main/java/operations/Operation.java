package operations;

public abstract class Operation
{
    static char[] order = {'I','V','X','L','C','D','M'};

    public Operation() {
    }

    public abstract String calculate(String a, String b);

    public static String replaceSubtractiveSymbols(String number)
    {
        number = number.replaceAll("IV", "IIII");
        number = number.replaceAll("IX", "VIIII");
        number = number.replaceAll("XL", "XXXX");
        number = number.replaceAll("XC", "LXXXX");
        number = number.replaceAll("CD", "CCCC");
        number = number.replaceAll("CM", "DCCCC");
        return number;
    }

    public boolean isValidRomanNumeral(String roman)
    {
        return roman.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public static String sort(String number)
    {
        char[] charArray = number.toCharArray();

        // sort the character array based on the roman numeral order
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

        StringBuilder sortedRoman = new StringBuilder();
        for (char c : charArray)
        {
            sortedRoman.append(c);
        }

        return sortedRoman.toString();
    }

    public static int getRomanOrderIndex(char c)
    {
        // find the index of the character in the order array
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
        number = number.replaceAll("IIIII", "V");
        number = number.replaceAll("VV", "X");
        number = number.replaceAll("VIIII", "IX");
        number = number.replaceAll("XXXXX", "L");
        number = number.replaceAll("LL", "C");
        number = number.replaceAll("LXXXX", "XC");
        number = number.replaceAll("CCCCC", "D");
        number = number.replaceAll("DD", "M");
        number = number.replaceAll("IIII", "IV");
        number = number.replaceAll("XXXX", "XL");
        number = number.replaceAll("DCCCC", "CM");
        number = number.replaceAll("CCCC", "CD");

        return number;
    }
}
