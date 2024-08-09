  @Test
  public void priceFromCurves() {
    LocalDate settlementDate = VAL_DATE.plusDays(1);
    double priceComputed = PRICER.priceFromCurves(BILL, PROVIDER, settlementDate);
    double dfMaturity = DSC_FACTORS_ISSUER.discountFactor(MATURITY_DATE);
    double dfSettle = DSC_FACTORS_REPO.discountFactor(settlementDate);
    double priceExpected = dfMaturity / dfSettle;
    assertThat(priceComputed).isCloseTo(priceExpected, offset(TOLERANCE_PRICE));
  }