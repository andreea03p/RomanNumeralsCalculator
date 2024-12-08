package operations_lists;

import static org.junit.Assert.*;

import operations_arrays.Addition;
import operations_arrays.Division;
import operations_arrays.Multiplication;
import operations_arrays.Subtraction;
import org.junit.Test;

public class MainTest {
    operations_lists.Addition calcAddition = new operations_lists.Addition();
    operations_lists.Subtraction calcSubtraction = new operations_lists.Subtraction();
    operations_lists.Multiplication calcMultiply = new operations_lists.Multiplication();
    operations_lists.Division calcDivision = new operations_lists.Division();

    // ---------------ADDITION--------------
    @Test
    public void additionSmallNumbers() {
        assertEquals("II", calcAddition.calculate("I", "I"));
    }

    @Test
    public void additionBoundaryNumbers() {
        assertEquals("VI", calcAddition.calculate("IV", "II"));
        assertEquals("V", calcAddition.calculate("II", "III"));
        assertEquals("VI", calcAddition.calculate("III", "III"));
        assertEquals("X", calcAddition.calculate("IX", "I"));
        assertEquals("XIV", calcAddition.calculate("IX", "V"));
    }

    @Test
    public void additionLargeNumbers() {
        assertEquals("D", calcAddition.calculate("CCL", "CCL"));
        assertEquals("MM", calcAddition.calculate("M", "M"));
    }

    @Test
    public void additionDifferentBases() {
        assertEquals("CCIX", calcAddition.calculate("C", "CIX"));
        assertEquals("CXXXVI", calcAddition.calculate("XCV", "XLI"));
        assertEquals("C", calcAddition.calculate("L", "L"));
    }

    @Test
    public void additionInvalidInput1() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calcAddition.calculate("A", "MM"));
        assertEquals("Invalid Roman numeral character: A", exception.getMessage());
    }

    @Test
    public void additionInvalidInput2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calcAddition.calculate("MMMM", "MM"));
        assertEquals("Invalid Roman numeral character: MMMM", exception.getMessage());
    }

    @Test
    public void additionOverflow() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calcAddition.calculate("II", "MMMCMXCIX"));
        assertEquals("Invalid addition result. (overflow)", exception.getMessage());
    }



    // -------------SUBTRACTION---------------
    @Test
    public void subtractionSimpleCases() {
        assertEquals("IV", calcSubtraction.calculate("V", "I")); // Basic case
        assertEquals("X", calcSubtraction.calculate("XX", "X"));
    }

    @Test
    public void subtractionZeroResult() {
        assertEquals("NONE", calcSubtraction.calculate("X", "X")); // Exact match
    }

    @Test
    public void subtractionInvalidCases() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calcSubtraction.calculate("IIII", "V"));
        assertEquals("Invalid Roman numeral character: IIII", exception.getMessage());
    }

    @Test
    public void subtractionUnderflow()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calcSubtraction.calculate("I", "V"));
        assertEquals("Subtraction not possible: I < V", exception.getMessage());
    }

    @Test
    public void subtractionLargeNumbers() {
        assertEquals("MM", calcSubtraction.calculate("MMM", "M")); // Large values
        assertEquals("CXL", calcSubtraction.calculate("CXC", "L"));
    }

    @Test
    public void subtractionEdgeCases() {
        assertEquals("IX", calcSubtraction.calculate("X", "I")); // Sequential edge
        assertEquals("V", calcSubtraction.calculate("X", "V")); // Exact boundary match
        assertEquals("NONE", calcSubtraction.calculate("V", "V")); // Exact match
    }



    // -----------MULTIPLICATION--------------
    @Test
    public void multiplicationSmallNumbers() {
        assertEquals("I", calcMultiply.calculate("I", "I"));
        assertEquals("VI", calcMultiply.calculate("II", "III"));
    }

    @Test
    public void multiplicationLargeNumbers() {
        assertEquals("MM", calcMultiply.calculate("M", "II"));
        assertEquals("M", calcMultiply.calculate("C", "X"));
        assertEquals("DCCXIV", calcMultiply.calculate("XIV", "LI"));
    }

    @Test
    public void multiplicationOverflowTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calcMultiply.calculate("MMM", "C")
        );
        assertEquals("Invalid multiplication result. (overflow)", exception.getMessage());
    }



    // -------------DIVISION---------------
    @Test
    public void divisionSimpleCases() {
        assertEquals("I rest NONE", calcDivision.calculate("I", "I"));
        assertEquals("V rest NONE", calcDivision.calculate("X", "II"));
        assertEquals("CII rest NONE", calcDivision.calculate("DCCXIV", "VII"));
    }

    @Test
    public void divisionSpecialCases() {
        assertEquals("ZERO rest XLIV", calcDivision.calculate("XLIV", "MMMDCCCLXXXVIII"));
        assertEquals("XXXIX rest LII", calcDivision.calculate("MMDLXXXVII", "LXV"));
    }

    @Test
    public void divisionZeroDivision() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calcDivision.calculate("X", "")
        );
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

}

