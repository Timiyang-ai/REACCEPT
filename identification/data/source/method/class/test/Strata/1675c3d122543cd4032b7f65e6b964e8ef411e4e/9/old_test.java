  @Test
  public void test_dirtyNominalPriceFromCurvesWithZSpread() {
    double computed = PRICER.dirtyNominalPriceFromCurvesWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, Z_SPREAD, CONTINUOUS, 0);
    LocalDate settlement = SETTLE_OFFSET.adjust(VALUATION, REF_DATA);
    double df =
        ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD).discountFactor(settlement);
    double expected = PRICER.presentValueWithZSpread(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, settlement,
        Z_SPREAD, CONTINUOUS, 0).getAmount() / NOTIONAL / df;
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }