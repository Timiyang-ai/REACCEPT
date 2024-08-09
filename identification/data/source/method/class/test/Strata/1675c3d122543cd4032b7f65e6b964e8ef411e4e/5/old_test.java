  @Test
  public void test_modifiedDurationFromStandardYield() {
    double yield = 0.0175;
    LocalDate standardSettle = SETTLE_OFFSET.adjust(VALUATION, REF_DATA);
    double computed =
        PRICER.modifiedDurationFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield);
    double price = PRICER.dirtyPriceFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield);
    double up = PRICER.dirtyPriceFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield + EPS);
    double dw = PRICER.dirtyPriceFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield - EPS);
    double expected = -0.5 * (up - dw) / price / EPS;
    assertThat(computed).isCloseTo(expected, offset(EPS));

  }