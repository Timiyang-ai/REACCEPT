@Test(groups="tck")
    public void test_now() {
        assertEquals(LocalDate.from(ISOChronology.INSTANCE.now()), LocalDate.now());
    }