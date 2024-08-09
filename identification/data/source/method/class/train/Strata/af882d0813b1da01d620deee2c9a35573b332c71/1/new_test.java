  @Test
  public void test_resolve() {
    FxSingle fwd = sut();
    ResolvedFxSingle test = fwd.resolve(REF_DATA);
    assertThat(test.getBaseCurrencyPayment()).isEqualTo(Payment.of(GBP_P1000, DATE_2015_06_30));
    assertThat(test.getCounterCurrencyPayment()).isEqualTo(Payment.of(USD_M1600, DATE_2015_06_30));
    assertThat(test.getPaymentDate()).isEqualTo(DATE_2015_06_30);
  }