@Test
    public void test_toEpochSecond_afterEpoch() {
        for (int i = 0; i < 100000; i++) {
            OffsetDateTime a = OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC).plusSeconds(i);
            assertEquals(a.toEpochSecond(), i);
        }
    }