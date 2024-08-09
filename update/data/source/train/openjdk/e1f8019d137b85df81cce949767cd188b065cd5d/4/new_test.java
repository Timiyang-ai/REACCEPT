@Test(dataProvider="plusDays")
    public void test_minus_TemporalAmount_Period_days(ZonedDateTime base, int amount, ZonedDateTime expected) {
        assertEquals(base.minus(Period.ofDays(-amount)), expected);
    }