  @Test
  public void yieldFromCurves() {
    LocalDate settlementDate = VAL_DATE.plusDays(1);
    double yieldComputed = PRICER.yieldFromCurves(BILL, PROVIDER, settlementDate);
    double dfMaturity = DSC_FACTORS_ISSUER.discountFactor(MATURITY_DATE);
    double dfSettle = DSC_FACTORS_REPO.discountFactor(settlementDate);
    double priceExpected = dfMaturity / dfSettle;
    double yieldExpected = BILL.yieldFromPrice(priceExpected, settlementDate);
    assertThat(yieldComputed).isCloseTo(yieldExpected, offset(TOLERANCE_PRICE));
  }