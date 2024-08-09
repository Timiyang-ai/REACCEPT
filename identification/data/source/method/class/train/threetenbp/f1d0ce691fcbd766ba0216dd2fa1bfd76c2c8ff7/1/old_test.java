@Test(dataProvider="sampleDates")
    public void test_matchesDate_true(int y, int m, int d) {
        LocalDate a = LocalDate.date(y, m, d);
        LocalDate b = LocalDate.date(y, m, d);
        assertEquals(a.matchesDate(b), true);
    }