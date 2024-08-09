@Test(groups={"tck"})
    public void test_withSecondOfMinute_normal() {
        OffsetDateTime base = OffsetDateTime.of(2008, 6, 30, 11, 30, 59, OFFSET_PONE);
        OffsetDateTime test = base.withSecondOfMinute(15);
        assertEquals(test, OffsetDateTime.of(2008, 6, 30, 11, 30, 15, OFFSET_PONE));
    }