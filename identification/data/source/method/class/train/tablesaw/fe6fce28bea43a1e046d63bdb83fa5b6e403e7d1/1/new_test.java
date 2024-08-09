@Test
    public void testDifference() {
        FloatColumn floats = new FloatColumn("floats", 100);
        FloatColumn otherFloats = new FloatColumn("otherFloats", 100);
        for (int i = 0; i < 100; i++) {
            floats.append(RandomUtils.nextFloat(0, 10_000));
            otherFloats.append(floats.get(i) - 1.0f);
        }
        FloatColumn diff = floats.subtract(otherFloats);
        for (int i = 0; i < floats.size(); i++) {
            assertEquals(floats.get(i), otherFloats.get(i) + 1.0, 0.01);
        }
    }