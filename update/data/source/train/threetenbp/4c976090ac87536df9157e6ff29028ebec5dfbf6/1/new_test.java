@Test(groups={"tck"}, dataProvider="plusTime")
    public void test_plusNanos(ZonedDateTime base, long amount, ZonedDateTime expected) {
        assertEquals(base.plusNanos(amount * 3600_000_000_000L), expected);
    }