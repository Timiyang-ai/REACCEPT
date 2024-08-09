@Test(groups={"tck"})
    public void test_atZone_resolver() {
        LocalDateTime t = LocalDateTime.of(2008, 6, 30, 11, 30);
        assertEquals(t.atZone(ZONE_PARIS, ZoneResolvers.postTransition()),
                ZonedDateTime.of(LocalDateTime.of(2008, 6, 30, 11, 30), ZONE_PARIS));
    }