@Test(groups={"tck"})
    public void test_doAdjustment() {
        assertEquals(DayOfWeek.MONDAY.doWithAdjustment(LocalDate.of(2012, 9, 2)), LocalDate.of(2012, 8, 27));
        assertEquals(DayOfWeek.MONDAY.doWithAdjustment(LocalDate.of(2012, 9, 3)), LocalDate.of(2012, 9, 3));
        assertEquals(DayOfWeek.MONDAY.doWithAdjustment(LocalDate.of(2012, 9, 4)), LocalDate.of(2012, 9, 3));
        assertEquals(DayOfWeek.MONDAY.doWithAdjustment(LocalDate.of(2012, 9, 10)), LocalDate.of(2012, 9, 10));
        assertEquals(DayOfWeek.MONDAY.doWithAdjustment(LocalDate.of(2012, 9, 11)), LocalDate.of(2012, 9, 10));
    }