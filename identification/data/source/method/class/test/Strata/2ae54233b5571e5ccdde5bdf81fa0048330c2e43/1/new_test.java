  @Test
  public void test_resolve() {
    Swaption base = sut();
    ResolvedSwaption test = base.resolve(REF_DATA);
    assertThat(test.getExpiry()).isEqualTo(ADJUSTMENT.adjust(EXPIRY_DATE, REF_DATA).atTime(EXPIRY_TIME).atZone(ZONE));
    assertThat(test.getLongShort()).isEqualTo(LONG);
    assertThat(test.getSwaptionSettlement()).isEqualTo(PHYSICAL_SETTLE);
    assertThat(test.getUnderlying()).isEqualTo(SWAP.resolve(REF_DATA));
  }