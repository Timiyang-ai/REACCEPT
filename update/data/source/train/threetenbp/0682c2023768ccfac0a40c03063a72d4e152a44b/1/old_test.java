@Test(groups={"tck"})
    public void test_adjustDate() {
        MonthDay test = MonthDay.of(6, 30);
        LocalDate date = LocalDate.of(2007, 1, 1);
        assertEquals(test.adjustDate(date), LocalDate.of(2007, 6, 30));
    }