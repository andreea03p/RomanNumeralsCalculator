package operations;

public class Multiplication extends Operation
{
    public String calculate(String first, String second)
    {
        String expandedFirst = Addition.replaceSubtractiveSymbols(first);
        String expandedSecond = Addition.replaceSubtractiveSymbols(second);

        String secondLength = convertToI(second);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < secondLength.length(); i++)
        {
            result.append(expandedFirst);
        }

        String sortedResult = Addition.sort(result.toString());
        String combinedResult = Addition.replaceDuplicates(sortedResult);

        if (!isValidRomanNumeral(combinedResult))
        {
            throw new IllegalArgumentException("Multiplication result is not a valid Roman numeral.");
        }

        return combinedResult;
    }

    private String convertToI(String roman)
    {
        String expanded = Operation.replaceSubtractiveSymbols(roman);
        expanded = expanded.replace("M", "CCCCCCCCCC");
        expanded = expanded.replace("D", "CCCCC");
        expanded = expanded.replace("C", "XXXXXXXXXX");
        expanded = expanded.replace("L", "XXXXX");
        expanded = expanded.replace("X", "IIIIIIIIII");
        expanded = expanded.replace("V", "IIIII");
        return expanded;
    }
}
