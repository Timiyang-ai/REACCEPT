@Test(groups={"tck"})
    public void test_with() {
        TimeAdjuster timeAdjuster = new TimeAdjuster() {
            public LocalTime adjustTime(LocalTime time) {
                return LocalTime.of(23, 5);
            }
        };
        assertEquals(TEST_12_30_40_987654321.with(timeAdjuster), LocalTime.of(23, 5));
    }