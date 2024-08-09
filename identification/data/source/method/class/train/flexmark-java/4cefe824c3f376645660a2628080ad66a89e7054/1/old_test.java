    @Test
    public void test_aggrLength() {
        loopSizes((b, i) -> {
            int[] aggrSegData1 = { i, -1, -2, -4 };
            int[] aggrSegData2 = { -1, -2, i, -3 };
            assertEquals("i: " + i, (int) i, aggrLength(0, aggrSegData1));
            assertEquals("i: " + i, (int) i, aggrLength(1, aggrSegData2));
        });
    }