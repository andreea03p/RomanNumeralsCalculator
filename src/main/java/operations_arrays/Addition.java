package operations_arrays;

public class Addition extends Operation
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
        String operand1 = Operation.replaceSubtractiveSymbols(first);
        String operand2 = Operation.replaceSubtractiveSymbols(second);
        String result = Operation.sort(operand1.concat(operand2));

        result = Operation.replaceDuplicates(result);

        if (!isValidRomanNumeral(result))
        {
            throw new IllegalArgumentException("Invalid addition result. (overflow)");
        }

        return Operation.replaceDuplicates(result);
    }
}
