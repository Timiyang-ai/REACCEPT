@Test
    public void testCompareTo() throws Exception {
        doTestComparisons(
            HijrahDate.hijrahDate(1, 1, 1),
            HijrahDate.hijrahDate(1, 1, 2),
            HijrahDate.hijrahDate(1, 1, 30),
            HijrahDate.hijrahDate(1, 2, 1),
            HijrahDate.hijrahDate(1, 2, 29),
            HijrahDate.hijrahDate(1, 12, 29),
            HijrahDate.hijrahDate(2, 1, 1),
            HijrahDate.hijrahDate(2, 12, 29),
            HijrahDate.hijrahDate(3, 1, 1),
            HijrahDate.hijrahDate(3, 12, 29),
            HijrahDate.hijrahDate(4500, 1, 1),
            HijrahDate.hijrahDate(4500, 12, 29)
        );
    }