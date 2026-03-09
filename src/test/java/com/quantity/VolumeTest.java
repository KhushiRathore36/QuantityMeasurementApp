package com.quantity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VolumeTest {
	private static final double EPSILON = 0.0001;

    @Test
    public void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
    }

    @Test
    public void testEquality_LitreToGallon() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(litre.equals(gallon));
    }

    @Test
    public void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    public void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                gallon.convertTo(VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(3.78541, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    public void testConversion_MillilitreToGallon() {

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                ml.convertTo(VolumeUnit.GALLON);

        assertEquals(
                new Quantity<>(0.26417, VolumeUnit.GALLON),
                result
        );
    }

    @Test
    public void testAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(ml);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    public void testAddition_GallonPlusLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                gallon.add(litre);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.GALLON),
                result
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(ml, VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(2000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    public void testVolumeVsLength() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    public void testVolumeVsWeight() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }

    @Test
    public void testZeroValue() {

        Quantity<VolumeUnit> a =
                new Quantity<>(0.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        assertTrue(a.equals(b));
    }

    @Test
    public void testNegativeVolume() {

        Quantity<VolumeUnit> a =
                new Quantity<>(-1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

        assertTrue(a.equals(b));
    }

    @Test
    public void testLargeVolume() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.LITRE);

        assertTrue(a.equals(b));
    }

    @Test
    public void testNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }
}
