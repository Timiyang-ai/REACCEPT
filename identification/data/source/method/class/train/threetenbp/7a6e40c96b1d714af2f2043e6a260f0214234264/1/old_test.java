@Test
    public void test_atMonthDay() {
        Year test = Year.of(2008);
        assertEquals(test.atMonthDay(MonthDay.of(6, 30)), LocalDate.of(2008, 6, 30));
    }