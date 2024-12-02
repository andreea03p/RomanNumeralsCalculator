package operations;

public class Addition extends Operation
{
    @Override
    public String calculate(String first, String second)
    {
        String one = Operation.replaceSubtractiveSymbols(first);
        String two = Operation.replaceSubtractiveSymbols(second);
        String number = Operation.sort(one.concat(two));

        return Operation.replaceDuplicates(number);
    }
}
