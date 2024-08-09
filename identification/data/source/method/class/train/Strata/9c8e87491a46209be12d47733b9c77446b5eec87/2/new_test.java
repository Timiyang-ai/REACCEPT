  @Test
  public void test_adjustPaymentDate() {
    FxResetNotionalExchange test = FxResetNotionalExchange.of(
        CurrencyAmount.of(USD, 1000d), DATE_2014_06_30, FxIndexObservation.of(GBP_USD_WM, DATE_2014_03_28, REF_DATA));
    FxResetNotionalExchange expected = FxResetNotionalExchange.of(
        CurrencyAmount.of(USD, 1000d), DATE_2014_06_30.plusDays(2), FxIndexObservation.of(GBP_USD_WM, DATE_2014_03_28, REF_DATA));
    assertThat(test.adjustPaymentDate(TemporalAdjusters.ofDateAdjuster(d -> d.plusDays(0)))).isEqualTo(test);
    assertThat(test.adjustPaymentDate(TemporalAdjusters.ofDateAdjuster(d -> d.plusDays(2)))).isEqualTo(expected);
  }