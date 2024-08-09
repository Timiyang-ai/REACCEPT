  @Test
  public void test_presentValue_zero() {
    CurrencyAmount pv = PRICER.presentValue(COUPON, RATES_PROVIDER, VOLATILITIES);
    CurrencyAmount pvCaplet = PRICER.presentValue(CAPLET_ZERO, RATES_PROVIDER, VOLATILITIES);
    CurrencyAmount pvFloorlet = PRICER.presentValue(FLOORLET_ZERO, RATES_PROVIDER, VOLATILITIES);
    assertThat(pv.getAmount()).isCloseTo(pvCaplet.getAmount(), offset(NOTIONAL * TOL));
    assertThat(pvFloorlet.getAmount()).isCloseTo(0d, offset(2.0d * NOTIONAL * TOL));
    CurrencyAmount pvShift = PRICER.presentValue(COUPON, RATES_PROVIDER, VOLATILITIES_SHIFT);
    CurrencyAmount pvCapletShift = PRICER.presentValue(CAPLET_SHIFT, RATES_PROVIDER, VOLATILITIES_SHIFT);
    CurrencyAmount pvFloorletShift = PRICER.presentValue(FLOORLET_SHIFT, RATES_PROVIDER, VOLATILITIES_SHIFT);
    double dfPayment = RATES_PROVIDER.discountFactor(EUR, PAYMENT);
    assertThat(pvShift.getAmount()).isCloseTo(pvCapletShift.getAmount() - SHIFT * dfPayment * NOTIONAL * ACC_FACTOR, offset(NOTIONAL * TOL));
    assertThat(pvFloorletShift.getAmount()).isCloseTo(0d, offset(2.0d * NOTIONAL * TOL));
  }