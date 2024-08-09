@Test(groups={"tck"}, dataProvider="plusTime")
    public void test_plusMinutes(ZonedDateTime base, long amount, ZonedDateTime expected) {
        assertEquals(base.plusMinutes(amount * 60), expected);
    }