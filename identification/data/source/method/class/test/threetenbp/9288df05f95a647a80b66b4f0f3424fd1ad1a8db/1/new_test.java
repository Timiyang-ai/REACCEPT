@Test(expectedExceptions=NullPointerException.class, groups={"tck"})
    public void test_print_nullCalendrical() {
        DateTimeFormatters.isoDate().print((TemporalAccessor) null);
    }