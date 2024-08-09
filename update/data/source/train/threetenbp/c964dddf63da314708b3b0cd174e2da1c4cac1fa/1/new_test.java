@Test(groups={"tck"})
    public void test_toString_gap() {
        LocalDateTime ldt = LocalDateTime.of(2010, 3, 31, 1, 0);
        ZoneOffsetTransition test = new ZoneOffsetTransition(ldt, OFFSET_0200, OFFSET_0300);
        assertEquals(test.toString(), "Transition[Gap at 2010-03-31T01:00+02:00 to +03:00]");
    }