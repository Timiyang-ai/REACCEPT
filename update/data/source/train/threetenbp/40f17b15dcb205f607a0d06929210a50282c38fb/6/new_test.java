@Test(groups={"tck"})
    public void test_plus_Duration() {
        Duration dur = Duration.ofSeconds(62, 3);
        OffsetDateTime t = TEST_2008_6_30_11_30_59_000000500.plus(dur);
        assertEquals(t, OffsetDateTime.of(LocalDate.of(2008, 6, 30), LocalTime.of(11, 32, 1, 503), OFFSET_PONE));
    }