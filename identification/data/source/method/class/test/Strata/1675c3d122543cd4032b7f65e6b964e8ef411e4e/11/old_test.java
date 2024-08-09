  @Test
  public void test_realYieldFromCurves_us() {
    LocalDate standardSettle = PRODUCT_US.getSettlementDateOffset().adjust(VAL_DATE, REF_DATA);
    double computed = PRICER.realYieldFromCurves(PRODUCT_US, RATES_PROVS_US, ISSUER_PROVS_US, REF_DATA);
    double dirtyNominalPrice = PRICER.dirtyNominalPriceFromCurves(
        PRODUCT_US, RATES_PROVS_US, ISSUER_PROVS_US, REF_DATA);
    double dirtyRealPrice =
        PRICER.realPriceFromNominalPrice(PRODUCT_US, RATES_PROVS_US, standardSettle, dirtyNominalPrice);
    double expected = PRICER.realYieldFromDirtyPrice(PRODUCT_US, RATES_PROVS_US, standardSettle, dirtyRealPrice);
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }