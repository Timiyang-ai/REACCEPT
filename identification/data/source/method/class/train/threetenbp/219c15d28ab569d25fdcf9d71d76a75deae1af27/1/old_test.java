@Test(groups={"tck"})
    public void test_from_Accessor() {
        assertEquals(LocalDateTime.from(LocalDateTime.of(2007, 7, 15, 17, 30)), LocalDateTime.of(2007, 7, 15, 17, 30));
        assertEquals(LocalDateTime.from(OffsetDateTime.of(2007, 7, 15, 17, 30, ZoneOffset.ofHours(2))), LocalDateTime.of(2007, 7, 15, 17, 30));
    }