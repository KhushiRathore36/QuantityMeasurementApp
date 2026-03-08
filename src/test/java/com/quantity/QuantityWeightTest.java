package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityWeightTest {
	@Test
    public void testEquality_KgToGram() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_DifferentWeight() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2, WeightUnit.KILOGRAM);

        assertFalse(a.equals(b));
    }

    @Test
    public void testWeightConversion() {

        double result = QuantityWeight.convert(1, WeightUnit.KILOGRAM, WeightUnit.GRAM);

        assertEquals(1000, result);
    }

    @Test
    public void testWeightAddition() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(500, WeightUnit.GRAM);

        QuantityWeight result = a.add(b, WeightUnit.KILOGRAM);

        assertEquals(new QuantityWeight(1.5, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(1, null);
        });
    }
}
