@Test
    public void test_range() {
        // eras may be added after release
        for (JapaneseEra era : JapaneseEra.values()) {
            assertEquals(era.range(ERA).getMinimum(), -999);
            assertEquals(era.range(ERA).getLargestMinimum(), -999);
            assertEquals(era.range(ERA).getSmallestMaximum(), era.range(ERA).getMaximum());
            assertEquals(era.range(ERA).getMaximum() >= 2, true);
        }
    }