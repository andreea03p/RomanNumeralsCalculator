package operations_lists;

public class Multiplication extends Operation
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
        String result = "";

        while (!second.equals("I"))
        {
            //System.out.println(operand2);
            result = Operation.sort(result.concat(operand1));

            second = new Subtraction().calculate(second, "I");
        }

        result = Operation.sort(result.concat(operand1));
        result = Operation.replaceDuplicates(result);

        if (!isValidRomanNumeral(result))
        {
            throw new IllegalArgumentException("Invalid multiplication result. (overflow)");
        }

        return result;
    }

}
