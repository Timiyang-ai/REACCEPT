  @Test
  public void test_isSmdRevoked_null() {
    assertThrows(
        NullPointerException.class,
        () ->
            SignedMarkRevocationList.create(START_OF_TIME, ImmutableMap.of())
                .isSmdRevoked(null, clock.nowUtc()));
  }