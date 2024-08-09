  @Test
  public void test_presentValue() {
    CurrencyAmount pvComputed = PRICER.presentValue(BILL, PROVIDER);
    double pvExpected = DSC_FACTORS_ISSUER.discountFactor(MATURITY_DATE) * NOTIONAL.getAmount();
    assertThat(pvComputed.getCurrency()).isEqualTo(EUR);
    assertThat(pvComputed.getAmount()).isCloseTo(pvExpected, offset(TOLERANCE_PV));
  }