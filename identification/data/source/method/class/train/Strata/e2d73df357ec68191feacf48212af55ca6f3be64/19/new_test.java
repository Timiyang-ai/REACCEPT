  @Test
  public void test_priceWithZSpread_continuous() {
    double computed = FUTURE_PRICER.priceWithZSpread(FUTURE_PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    double dirtyPrice = BOND_PRICER.dirtyPriceFromCurvesWithZSpread(
        BOND,
        PROVIDER,
        Z_SPREAD,
        CONTINUOUS,
        0,
        FUTURE_PRODUCT.getLastDeliveryDate());
    double expected = BOND_PRICER.cleanPriceFromDirtyPrice(
        BOND,
        FUTURE_PRODUCT.getLastDeliveryDate(), dirtyPrice) / CONVERSION_FACTOR[0];
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }