  @Test
  public void test_dirtyPriceFromCleanPrice_cleanPriceFromDirtyPrice() {
    double dirtyPrice = PRICER.dirtyPriceFromCurves(PRODUCT, PROVIDER, REF_DATA);
    LocalDate settlement = DATE_OFFSET.adjust(VAL_DATE, REF_DATA);
    double cleanPrice = PRICER.cleanPriceFromDirtyPrice(PRODUCT, settlement, dirtyPrice);
    double accruedInterest = PRICER.accruedInterest(PRODUCT, settlement);
    assertThat(cleanPrice).isCloseTo(dirtyPrice - accruedInterest / NOTIONAL, offset(NOTIONAL * TOL));
    double dirtyPriceRe = PRICER.dirtyPriceFromCleanPrice(PRODUCT, settlement, cleanPrice);
    assertThat(dirtyPriceRe).isCloseTo(dirtyPrice, offset(TOL));
  }