  @Test
  public void test_ofForwardPoints() {
    double nearRate = 1.6;
    double fwdPoint = 0.1;
    FxSwap test =
        FxSwap.ofForwardPoints(GBP_P1000, FxRate.of(GBP, USD, nearRate), fwdPoint, DATE_2011_11_21, DATE_2011_12_21);
    FxSingle nearLegExp = FxSingle.of(GBP_P1000, CurrencyAmount.of(USD, -1000.0 * nearRate), DATE_2011_11_21);
    FxSingle farLegExp = FxSingle.of(GBP_M1000, CurrencyAmount.of(USD, 1000.0 * (nearRate + fwdPoint)), DATE_2011_12_21);
    assertThat(test.getNearLeg()).isEqualTo(nearLegExp);
    assertThat(test.getFarLeg()).isEqualTo(farLegExp);
  }