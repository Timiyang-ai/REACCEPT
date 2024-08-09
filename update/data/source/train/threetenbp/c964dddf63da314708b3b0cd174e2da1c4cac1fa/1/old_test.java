@Test(groups={"tck"})
    public void test_toString_gap() {
        OffsetDateTime odt = OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200);
        ZoneOffsetTransition test = new ZoneOffsetTransition(odt, OFFSET_0300);
        assertEquals(test.toString(), "Transition[Gap at 2010-03-31T01:00+02:00 to +03:00]");
    }