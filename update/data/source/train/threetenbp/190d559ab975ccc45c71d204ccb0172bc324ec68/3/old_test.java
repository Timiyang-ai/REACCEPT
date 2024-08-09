@Test(groups={"tck"})
    public void test_withEra_BE() {
        ChronoLocalDate base = ThaiBuddhistChrono.INSTANCE.date(2555, 8, 29);
        ChronoLocalDate test = base.with(LocalDateTimeField.ERA, ThaiBuddhistChrono.ERA_BE.getValue());
        assertEquals(test, ThaiBuddhistChrono.INSTANCE.date(2555, 8, 29));
    }