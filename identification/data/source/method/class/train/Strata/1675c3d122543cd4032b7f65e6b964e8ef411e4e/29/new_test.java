  @Test
  public void zSpreadFromCurvesAndCleanPrice_us() {
    LocalDate standardSettle = PRODUCT_US.getSettlementDateOffset().adjust(VAL_DATE, REF_DATA);
    double dirtyNominalPrice = PRICER.dirtyNominalPriceFromCurvesWithZSpread(
        PRODUCT_US, RATES_PROVS_US, ISSUER_PROVS_US, REF_DATA, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    double cleanRealPrice = PRICER.realPriceFromNominalPrice(PRODUCT_US, RATES_PROVS_US, standardSettle,
        PRICER.cleanNominalPriceFromDirtyNominalPrice(PRODUCT_US, RATES_PROVS_US, standardSettle, dirtyNominalPrice));
    double computed = PRICER.zSpreadFromCurvesAndCleanPrice(
        PRODUCT_US, RATES_PROVS_US, ISSUER_PROVS_US, REF_DATA, cleanRealPrice, PERIODIC, PERIOD_PER_YEAR);
    assertThat(computed).isCloseTo(Z_SPREAD, offset(TOL));
  }