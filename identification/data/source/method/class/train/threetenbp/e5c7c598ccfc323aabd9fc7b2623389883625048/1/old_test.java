@Test(enabled=false)
    public void test_toString() {
        Instant t = Instant.instant(0L, 567);
        assertEquals(t.toString(), "1970-01-01T00:00:00.000000567Z");
    }