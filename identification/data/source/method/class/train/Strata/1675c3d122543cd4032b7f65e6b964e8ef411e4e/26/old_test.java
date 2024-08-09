  @Test
  public void test_dirtyNominalPriceFromCurves() {
    double computed = PRICER.dirtyNominalPriceFromCurves(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA);
    LocalDate settlement = SETTLE_OFFSET.adjust(VALUATION, REF_DATA);
    double df =
        ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD).discountFactor(settlement);
    double expected =
        PRICER.presentValue(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, settlement).getAmount() / NOTIONAL / df;
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }