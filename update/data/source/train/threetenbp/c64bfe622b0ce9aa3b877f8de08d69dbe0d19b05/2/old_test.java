@Test(dataProvider="sampleDates", groups={"implementation"})
    public void test_toLocalDate(int year, int month, int day) {
        LocalDate d = LocalDate.of(year, month, day);
        LocalDateTime dt = LocalDateTime.of(d, LocalTime.MIDNIGHT);
        assertSame(dt.toLocalDate(), d);
    }