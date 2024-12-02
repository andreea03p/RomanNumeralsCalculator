package operations;

import java.util.ArrayList;

public abstract class Operation
{
    protected String operand1;
    protected String operand2;
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
        ArrayList<RomanNumber> roman = new ArrayList<RomanNumber>();

        for (char i : number.toCharArray())
        {
            roman.add(new RomanNumber(i));
        }

        roman.sort(null);
        number = new String();
        for (RomanNumber i: roman)
        {
            number = number + i.toString();
        }

        return number;
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
