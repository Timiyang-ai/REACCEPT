  @Test
  public void test_price() {
    double computed = FUTURE_PRICER.price(FUTURE_PRODUCT, PROVIDER);
    double dirtyPrice = BOND_PRICER.dirtyPriceFromCurves(
        BOND,
        PROVIDER,
        FUTURE_PRODUCT.getLastDeliveryDate());
    double expected = BOND_PRICER.cleanPriceFromDirtyPrice(
        BOND,
        FUTURE_PRODUCT.getLastDeliveryDate(), dirtyPrice) / CONVERSION_FACTOR[0];
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }