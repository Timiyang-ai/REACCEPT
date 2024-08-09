@Test(groups={"tck"})
    public void test_withSecond_normal() {
        OffsetDateTime base = OffsetDateTime.of(2008, 6, 30, 11, 30, 59, OFFSET_PONE);
        OffsetDateTime test = base.withSecond(15);
        assertEquals(test, OffsetDateTime.of(2008, 6, 30, 11, 30, 15, OFFSET_PONE));
    }