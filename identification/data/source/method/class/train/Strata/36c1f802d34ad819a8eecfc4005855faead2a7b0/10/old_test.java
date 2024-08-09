  @Test
  public void test_presentValueWithZSpread_continuous() {
    CurrencyAmount computed = PRICER.presentValueWithZSpread(PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    CurrencyAmount expected = PRICER_NOMINAL.presentValueWithSpread(
        PRODUCT.getNominalPayment(), DSC_FACTORS_ISSUER, Z_SPREAD, CONTINUOUS, 0);
    int size = PRODUCT.getPeriodicPayments().size();
    double pvcCupon = 0d;
    for (int i = 2; i < size; ++i) {
      FixedCouponBondPaymentPeriod payment = PRODUCT.getPeriodicPayments().get(i);
      pvcCupon += PRICER_COUPON.presentValueWithSpread(payment, 
          IssuerCurveDiscountFactors.of(DSC_FACTORS_ISSUER, GROUP_ISSUER), Z_SPREAD, CONTINUOUS, 0);
    }
    expected = expected.plus(pvcCupon);
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected.getAmount(), offset(NOTIONAL * TOL));
  }