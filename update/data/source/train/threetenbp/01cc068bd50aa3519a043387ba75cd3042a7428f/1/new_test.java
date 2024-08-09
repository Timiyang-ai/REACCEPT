@Test(groups={"tck"})
    public void test_with_MonthOfYear() {
        assertEquals(MonthDay.of(6, 30).with(Month.JANUARY), MonthDay.of(1, 30));
    }