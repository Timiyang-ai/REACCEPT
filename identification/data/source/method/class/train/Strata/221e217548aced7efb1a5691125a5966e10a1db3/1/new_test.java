  @Test
  public void test_findIndex() {
    assertThat(LoaderUtils.findIndex("GBP-LIBOR-3M")).isEqualTo(IborIndices.GBP_LIBOR_3M);
    assertThat(LoaderUtils.findIndex("GBP-SONIA")).isEqualTo(OvernightIndices.GBP_SONIA);
    assertThat(LoaderUtils.findIndex("GB-RPI")).isEqualTo(PriceIndices.GB_RPI);
    assertThat(LoaderUtils.findIndex("GBP/USD-WM")).isEqualTo(FxIndices.GBP_USD_WM);
    assertThatIllegalArgumentException().isThrownBy(() -> LoaderUtils.findIndex("Rubbish"));
  }