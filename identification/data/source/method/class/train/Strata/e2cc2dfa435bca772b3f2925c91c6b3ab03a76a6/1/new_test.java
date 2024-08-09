  @Test
  public void test_parse_noTenor() {
    assertThat(FloatingRateIndex.parse("GBP-LIBOR")).isEqualTo(IborIndices.GBP_LIBOR_3M);
    assertThat(FloatingRateIndex.parse("GBP-LIBOR-1M")).isEqualTo(IborIndices.GBP_LIBOR_1M);
    assertThat(FloatingRateIndex.parse("GBP-LIBOR-3M")).isEqualTo(IborIndices.GBP_LIBOR_3M);
    assertThat(FloatingRateIndex.parse("GBP-SONIA")).isEqualTo(OvernightIndices.GBP_SONIA);
    assertThat(FloatingRateIndex.parse("GB-RPI")).isEqualTo(PriceIndices.GB_RPI);
    assertThatIllegalArgumentException().isThrownBy(() -> FloatingRateIndex.parse(null));
    assertThatIllegalArgumentException().isThrownBy(() -> FloatingRateIndex.parse("NotAnIndex"));
  }