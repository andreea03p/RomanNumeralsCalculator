package operations;

public class Division extends Operation
{
    public String calculate(String roman1, String roman2)
    {
        String expandedRoman1 = ConvertToI(roman1);
        String expandedRoman2 = ConvertToI(roman2);

        if (expandedRoman2.length() == 0)
        {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        int count = 0;
        while (expandedRoman1.length() >= expandedRoman2.length())
        {
            expandedRoman1 = subtractStrings(expandedRoman1, expandedRoman2);
            count++;
        }

        String result = ConvertFromI(count);

        if (!isValidRomanNumeral(result))
        {
            throw new IllegalArgumentException("Division result is not a valid Roman numeral.");
        }

        if(result.isEmpty())
        {
            return "NONE";
        }

        return result;
    }

    private static String subtractStrings(String dividend, String divisor)
    {
        StringBuilder result = new StringBuilder(dividend);
        for (char c : divisor.toCharArray())
        {
            int index = result.indexOf("I");
            if (index != -1)
            {
                result.deleteCharAt(index);
            }
        }
        return result.toString();
    }

    public static String ConvertToI(String roman)
    {
        String expanded = Addition.replaceSubtractiveSymbols(roman);

        expanded = expanded.replace("M", "CCCCCCCCCC");
        expanded = expanded.replace("D", "CCCCC");
        expanded = expanded.replace("C", "XXXXXXXXXX");
        expanded = expanded.replace("L", "XXXXX");
        expanded = expanded.replace("X", "IIIIIIIIII");
        expanded = expanded.replace("V", "IIIII");

        return expanded;
    }

    public static String ConvertFromI(int count)
    {
        StringBuilder roman = new StringBuilder();
        while (count >= 1000) { roman.append("M"); count -= 1000; }
        while (count >= 500) { roman.append("D"); count -= 500; }
        while (count >= 100) { roman.append("C"); count -= 100; }
        while (count >= 50) { roman.append("L"); count -= 50; }
        while (count >= 10) { roman.append("X"); count -= 10; }
        while (count >= 5) { roman.append("V"); count -= 5; }
        while (count >= 1) { roman.append("I"); count -= 1; }
        return Addition.replaceDuplicates(roman.toString());
    }
}
