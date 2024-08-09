  @Test
  public void test_price() {
    double computed = PRICER.price(FUTURE, PROVIDER);
    double pvSwap = PRICER.getSwapPricer().presentValue(RSWAP, PROVIDER).getAmount(USD).getAmount();
    double yc = ACT_ACT_ISDA.relativeYearFraction(VAL_DATE, DELIVERY);
    double df = Math.exp(-USD_DSC.yValue(yc) * yc);
    double expected = 1d + pvSwap / df;
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }