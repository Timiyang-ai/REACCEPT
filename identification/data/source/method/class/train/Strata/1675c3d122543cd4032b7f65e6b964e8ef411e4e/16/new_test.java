  @Test
  public void test_convexityFromStandardYield() {
    double yield = 0.0175;
    LocalDate standardSettle = SETTLE_OFFSET.adjust(VALUATION, REF_DATA);
    double computed = PRICER.convexityFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield);
    double md = PRICER.modifiedDurationFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield);
    double up = PRICER.modifiedDurationFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield + EPS);
    double dw = PRICER.modifiedDurationFromStandardYield(PRODUCT_EX_COUPON, RATES_PROVIDER, standardSettle, yield - EPS);
    double expected = -0.5 * (up - dw) / EPS + md * md;
    assertThat(computed).isCloseTo(expected, offset(EPS));
    double computed1 = PRICER.convexityFromStandardYield(PRODUCT, RATES_PROVIDER, VALUATION, yield);
    double md1 = PRICER.modifiedDurationFromStandardYield(PRODUCT, RATES_PROVIDER, VALUATION, yield);
    double up1 = PRICER.modifiedDurationFromStandardYield(PRODUCT, RATES_PROVIDER, VALUATION, yield + EPS);
    double dw1 = PRICER.modifiedDurationFromStandardYield(PRODUCT, RATES_PROVIDER, VALUATION, yield - EPS);
    double expected1 = -0.5 * (up1 - dw1) / EPS + md1 * md1;
    assertThat(computed1).isCloseTo(expected1, offset(EPS));
  }