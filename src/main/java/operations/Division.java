package operations;

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
//             System.out.println("r: " + remainder);
//             System.out.println("q: " + quotient);

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
        System.out.println("r: " + a);
        System.out.println("q: " + b);

        a = Operation.replaceSubtractiveSymbols(a);
        b = Operation.replaceSubtractiveSymbols(b);

        String sortedA = Operation.sort(a);
        String sortedB = Operation.sort(b);

        int i = 0;
        int j = 0;

        while (i < sortedA.length() && j < sortedB.length())
        {
            char charA = sortedA.charAt(i);
            char charB = sortedB.charAt(j);

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

        if (j == sortedB.length())
        {
            return true;
        }

        return false;
    }

}
