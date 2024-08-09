  @Test
  public void test_dirtyPriceFromCurvesWithZSpread_continuous() {
    double computed = PRICER.dirtyPriceFromCurvesWithZSpread(
        PRODUCT, PROVIDER, REF_DATA, Z_SPREAD, CONTINUOUS, 0);
    CurrencyAmount pv = PRICER.presentValueWithZSpread(PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    LocalDate settlement = DATE_OFFSET.adjust(VAL_DATE, REF_DATA);
    double df = DSC_FACTORS_REPO.discountFactor(settlement);
    assertThat(computed).isEqualTo(pv.getAmount() / df / NOTIONAL);
  }