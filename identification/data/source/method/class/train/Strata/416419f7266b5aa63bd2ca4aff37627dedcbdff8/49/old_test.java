  @Test
  public void test_dirtyPriceFromCurves() {
    double computed = PRICER.dirtyPriceFromCurves(PRODUCT, PROVIDER, REF_DATA);
    CurrencyAmount pv = PRICER.presentValue(PRODUCT, PROVIDER);
    LocalDate settlement = DATE_OFFSET.adjust(VAL_DATE, REF_DATA);
    double df = DSC_FACTORS_REPO.discountFactor(settlement);
    assertThat(computed).isEqualTo(pv.getAmount() / df / NOTIONAL);
  }