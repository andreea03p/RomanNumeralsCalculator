package operations;

public class Subtraction extends Operation
{
    @Override
    public String calculate(String first, String second)
    {
        // Replace subtractive symbols for both Roman numerals
        String expandedRoman1 = Operation.replaceSubtractiveSymbols(first);
        String expandedRoman2 = Operation.replaceSubtractiveSymbols(second);

        // Check if second number is larger than the first
        if (!isSubtractionPossible(expandedRoman1, expandedRoman2))
        {
            throw new IllegalArgumentException("Subtraction not possible: " + first + " < " + second);
        }

        StringBuilder remaining1 = new StringBuilder(expandedRoman1);

        // Subtract characters of expandedRoman2 from expandedRoman1
        for (char c : expandedRoman2.toCharArray())
        {
            while (true)
            {
                int index = remaining1.indexOf(String.valueOf(c));
                if (index != -1)
                {
                    remaining1.deleteCharAt(index);
                    break;
                }
                else
                {
                    expandRoman1(remaining1);
                }
            }
        }

        String simplifiedResult = Operation.replaceDuplicates(remaining1.toString());

        // If result is empty, return "NONE" for 0
        if (simplifiedResult.isEmpty())
        {
            return "NONE";
        }

        // Validate the resulting Roman numeral
        if (!isValidRomanNumeral(simplifiedResult))
        {
            throw new IllegalArgumentException("Invalid subtraction result.");
        }

        return simplifiedResult;
    }

    /**
     * Expand the given Roman numeral to allow further subtraction.
     * @param roman1 The Roman numeral to expand.
     */
    private void expandRoman1(StringBuilder roman1)
    {
        for (int i = roman1.length() - 1; i >= 0; i--)
        {
            char c = roman1.charAt(i);
            switch (c)
            {
                case 'M':
                    roman1.replace(i, i + 1, "DD");
                    return;
                case 'D':
                    roman1.replace(i, i + 1, "CCCCC");
                    return;
                case 'C':
                    roman1.replace(i, i + 1, "LL");
                    return;
                case 'L':
                    roman1.replace(i, i + 1, "XXXXX");
                    return;
                case 'X':
                    roman1.replace(i, i + 1, "VV");
                    return;
                case 'V':
                    roman1.replace(i, i + 1, "IIIII");
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * Compare two Roman numerals to determine if a >= b.
     * @param a The first Roman numeral.
     * @param b The second Roman numeral.
     * @return True if a >= b, false otherwise.
     */
    private boolean isSubtractionPossible(String a, String b)
    {
        String sortedA = Operation.sort(a);
        String sortedB = Operation.sort(b);

        String romanOrder = "IVXLCDM";

        int i = 0;
        int j = 0;

        while (i < sortedA.length() && j < sortedB.length())
        {
            char charA = sortedA.charAt(i);
            char charB = sortedB.charAt(j);

            int indexA = romanOrder.indexOf(charA);
            int indexB = romanOrder.indexOf(charB);

            if (indexA > indexB)
            {
                return true;
            }
            else if (indexA < indexB)
            {
                return false;
            }

            i++;
            j++;
        }

        if (j == sortedB.length())
        {
            return true;
        }

        return false;
    }

}
