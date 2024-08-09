@Test
    public void testCompareTo() throws Exception {
        doTestComparisons(
            HijrahDate.of(1, 1, 1),
            HijrahDate.of(1, 1, 2),
            HijrahDate.of(1, 1, 30),
            HijrahDate.of(1, 2, 1),
            HijrahDate.of(1, 2, 29),
            HijrahDate.of(1, 12, 29),
            HijrahDate.of(2, 1, 1),
            HijrahDate.of(2, 12, 29),
            HijrahDate.of(3, 1, 1),
            HijrahDate.of(3, 12, 29),
            HijrahDate.of(4500, 1, 1),
            HijrahDate.of(4500, 12, 29)
        );
    }