public PointSensitivityBuilder pvbpSensitivity(SwapLeg fixedLeg, RatesProvider provider) {
    PointSensitivityBuilder builder = PointSensitivityBuilder.none();
    for (PaymentPeriod period : fixedLeg.expand().getPaymentPeriods()) {
      ArgChecker.isTrue(period instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
      builder = builder.combinedWith(pvbpSensitivityPayment((RatePaymentPeriod) period, provider));
    }
    return builder;
  }