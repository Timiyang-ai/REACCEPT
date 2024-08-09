  @Test
  public void test_resolve() {
    FxVanillaOption base = sut();
    ResolvedFxVanillaOption expected = ResolvedFxVanillaOption.builder()
        .longShort(LONG)
        .expiry(EXPIRY_DATE.atTime(EXPIRY_TIME).atZone(EXPIRY_ZONE))
        .underlying(FX.resolve(REF_DATA))
        .build();
    assertThat(base.resolve(REF_DATA)).isEqualTo(expected);
  }