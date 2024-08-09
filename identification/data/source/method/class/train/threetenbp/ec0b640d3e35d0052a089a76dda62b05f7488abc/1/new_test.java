@Test(groups="tck")
    public void test_now() {
        assertEquals(LocalDate.from(ISOChronology.INSTANCE.dateNow()), LocalDate.now());
    }