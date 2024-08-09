@Test(groups={"tck"})
    public void test_with_Month() {
        YearMonth test = YearMonth.of(2008, 6);
        assertEquals(test.with(Month.JANUARY), YearMonth.of(2008, 1));
    }