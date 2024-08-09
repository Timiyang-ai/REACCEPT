@Test(dataProvider="sampleDates", groups={"tck"})
    public void test_get_dates(int y, int m, int d) {
        LocalDateTime a = LocalDateTime.of(y, m, d, 12, 30);
        assertEquals(a.getYear(), y);
        assertEquals(a.getMonthOfYear(), Month.of(m));
        assertEquals(a.getDayOfMonth(), d);
    }