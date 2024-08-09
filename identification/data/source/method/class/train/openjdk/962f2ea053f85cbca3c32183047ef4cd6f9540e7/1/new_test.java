@Test(dataProvider="datesForPeriod")
    public void test_until(HijrahDate h1, HijrahDate h2, ChronoPeriod p) {
        ChronoPeriod period = h1.until(h2);
        assertEquals(period, p);
    }