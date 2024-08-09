@Test(groups={"implementation"})
    public void test_toString_normal() {
        ZoneOffsetInfo test = make(OFFSET_0200, null);
        assertEquals(test.toString(), "OffsetInfo[+02:00]");
    }