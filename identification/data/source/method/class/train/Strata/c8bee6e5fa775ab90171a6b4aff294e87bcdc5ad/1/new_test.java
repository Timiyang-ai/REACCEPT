  @Test
  public void test_fxRate_forBase() {
    FxRate test = FxRate.of(GBP, USD, 1.25d);
    assertThat(test.fxRate(GBP, USD)).isEqualTo(1.25d);
    assertThat(test.fxRate(USD, GBP)).isEqualTo(1d / 1.25d);
    assertThatIllegalArgumentException().isThrownBy(() -> test.fxRate(GBP, AUD));
  }