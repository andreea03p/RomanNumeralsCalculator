package operations_arrays;

public class Division extends Operation
{
    @Override
    public String calculate(String first, String second)
    {
        if(!isValidRomanNumeral(first))
        {
            throw new IllegalArgumentException("Invalid Roman numeral character: " + first);
        }
        else if(!isValidRomanNumeral(second))
        {
            throw new IllegalArgumentException("Invalid Roman numeral character: " + second);
        }

        if (second.isEmpty())
        {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        String quotient = "";
        String remainder = first;

        while (hasQuotient(remainder, second) && !remainder.equals(second))
        {
            // System.out.println("r: " + remainder);
            // System.out.println("q: " + quotient);

            remainder = new Subtraction().calculate(remainder, second);
            quotient = new Addition().calculate(quotient, "I");
        }

        if(remainder.equals(second))
        {
            remainder = new Subtraction().calculate(remainder, second);
            quotient = new Addition().calculate(quotient, "I");
        }

        return (quotient.isEmpty() ? "ZERO" : quotient) + " rest " + (remainder.isEmpty() ? "ZERO" : remainder);
    }

    private boolean hasQuotient(String a, String b)
    {
        String sortedA = Operation.sort(a);
        String sortedB = Operation.sort(b);

        char[] charArrayA = sortedA.toCharArray();
        char[] charArrayB = sortedB.toCharArray();

        int i = 0;
        int j = 0;

        if (a.equals(b)) {
            return true;
        }

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
