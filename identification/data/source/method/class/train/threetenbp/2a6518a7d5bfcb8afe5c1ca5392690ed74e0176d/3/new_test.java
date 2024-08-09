@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullCalendrical() {
        DateTimeFormatters.isoDate().print((TemporalAccessor) null);
    }