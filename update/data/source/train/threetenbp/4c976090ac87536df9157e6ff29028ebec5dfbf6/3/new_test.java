@Test(groups={"tck"}, dataProvider="plusTime")
    public void test_plusSeconds(ZonedDateTime base, long amount, ZonedDateTime expected) {
        assertEquals(base.plusSeconds(amount * 3600), expected);
    }