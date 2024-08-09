  @Test
  public void test_convertedTo_rateProvider_noConversionSize1() {
    FxRateProvider provider = (ccy1, ccy2) -> {
      throw new IllegalArgumentException();
    };
    MultiCurrencyAmount test = MultiCurrencyAmount.of(CA2);
    assertThat(test.convertedTo(CCY2, provider)).isEqualTo(CA2);
  }