@Test
    public void test_toString_formatter() {
        DateTimeFormatter f = DateTimeFormatters.pattern("M d");
        String t = MonthDay.of(12, 3).toString(f);
        assertEquals(t, "12 3");
    }