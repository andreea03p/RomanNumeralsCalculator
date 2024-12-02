package operations;

public class Multiply extends Operation
{
    public String calculate(String first, String second)
    {
        String expandedFirst = Addition.replaceSubtractiveSymbols(first);
        String expandedSecond = Addition.replaceSubtractiveSymbols(second);

        int repeatCount = countRomanCharacters(expandedSecond);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < repeatCount; i++)
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

    public static int countRomanCharacters(String roman)
    {
        int count = 0;
        for (char c : roman.toCharArray())
        {
            switch (c)
            {
                case 'I':
                    count += 1;
                    break;
                case 'V':
                    count += 5;
                    break;
                case 'X':
                    count += 10;
                    break;
                case 'L':
                    count += 50;
                    break;
                case 'C':
                    count += 100;
                    break;
                case 'D':
                    count += 500;
                    break;
                case 'M':
                    count += 1000;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Roman numeral character.");
            }
        }
        return count;
    }
}
