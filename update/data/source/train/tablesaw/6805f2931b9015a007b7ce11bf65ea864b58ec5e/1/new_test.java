@Test
    public void testDifference() {
        DoubleColumn doubles = new DoubleColumn("doubles", 100);
        DoubleColumn otherDoubles = new DoubleColumn("otherDoubles", 100);
        for (int i = 0; i < 100; i++) {
            doubles.append(RandomUtils.nextDouble(0, 10_000));
            otherDoubles.append(doubles.get(i) - 1.0f);
        }
        for (int i = 0; i < doubles.size(); i++) {
            assertEquals(doubles.get(i), otherDoubles.get(i) + 1.0, 0.01);
        }
    }