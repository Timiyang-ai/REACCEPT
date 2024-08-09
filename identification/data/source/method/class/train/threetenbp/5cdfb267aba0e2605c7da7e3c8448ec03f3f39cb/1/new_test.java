@Test(groups={"tck"})
    public void test_withYear_BE() {
        ChronoDate base = ThaiBuddhistChronology.INSTANCE.date(2555, 8, 29);
        ChronoDate test = base.withYear(2554);
        assertEquals(test, ThaiBuddhistChronology.INSTANCE.date(2554, 8, 29));
    }