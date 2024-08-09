  @Test
  public void test_presentValue() {
    CurrencyAmount computed = PRICER.presentValue(PRODUCT, PROVIDER);
    CurrencyAmount expected = PRICER_NOMINAL.presentValue(PRODUCT.getNominalPayment(), DSC_FACTORS_ISSUER);
    int size = PRODUCT.getPeriodicPayments().size();
    double pvCupon = 0d;
    for (int i = 2; i < size; ++i) {
      FixedCouponBondPaymentPeriod payment = PRODUCT.getPeriodicPayments().get(i);
      pvCupon += PRICER_COUPON.presentValue(payment, IssuerCurveDiscountFactors.of(DSC_FACTORS_ISSUER, GROUP_ISSUER));
    }
    expected = expected.plus(pvCupon);
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected.getAmount(), offset(NOTIONAL * TOL));
  }