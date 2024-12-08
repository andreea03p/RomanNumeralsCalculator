package operations_arrays;

public class Subtraction extends Operation
{
    @Override
    public String calculate(String first, String second)
    {
        if (!isValidRomanNumeral(first))
        {
            throw new IllegalArgumentException("Invalid Roman numeral character: " + first);
        }
        else if (!isValidRomanNumeral(second))
        {
            throw new IllegalArgumentException("Invalid Roman numeral character: " + second);
        }

        String operand1 = Operation.replaceSubtractiveSymbols(first);
        String operand2 = Operation.replaceSubtractiveSymbols(second);

        if (!isSubtractionPossible(operand1, operand2))
        {
            throw new IllegalArgumentException("Subtraction not possible: " + first + " < " + second);
        }

        StringBuilder remaining1 = new StringBuilder(operand1);

        for (char c : operand2.toCharArray())
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

        if (simplifiedResult.isEmpty())
        {
            return "NONE"; // equivalent for zero
        }

        if (!isValidRomanNumeral(simplifiedResult))
        {
            throw new IllegalArgumentException("Invalid subtraction result.");
        }

        return simplifiedResult;
    }

    // Fix: Modify using StringBuilder instead of System.arraycopy for the correct expansion.
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

    // Fix: Correctly use char[] and length property for array size
    private boolean isSubtractionPossible(String a, String b)
    {
        String sortedA = Operation.sort(a);
        String sortedB = Operation.sort(b);

        char[] charArrayA = sortedA.toCharArray();
        char[] charArrayB = sortedB.toCharArray();

        int i = 0;
        int j = 0;

        while (i < charArrayA.length && j < charArrayB.length)
        {
            char charA = charArrayA[i];
            char charB = charArrayB[j];

            int indexA = getRomanOrderIndex(charA);
            int indexB = getRomanOrderIndex(charB);

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

        return j == charArrayB.length;
    }
}
