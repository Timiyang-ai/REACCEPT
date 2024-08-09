@Test(groups={"tck"})
    public void test_withMonthOfYear() {
        assertEquals(MonthDay.of(6, 30).withMonthOfYear(1), MonthDay.of(1, 30));
    }