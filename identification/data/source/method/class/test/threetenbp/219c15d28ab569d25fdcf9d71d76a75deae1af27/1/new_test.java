@Test(groups={"tck"})
    public void test_atZone_Offset() {
        LocalDateTime t = LocalDateTime.of(2008, 6, 30, 11, 30);
        assertEquals(t.atZone(OFFSET_PTWO), ZonedDateTime.of(LocalDateTime.of(2008, 6, 30, 11, 30), OFFSET_PTWO));
    }