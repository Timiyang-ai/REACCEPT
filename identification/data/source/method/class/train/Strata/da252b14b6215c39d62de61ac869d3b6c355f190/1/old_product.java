public PointSensitivityBuilder pvbpSensitivity(SwapLeg fixedLeg, RatesProvider provider) {
    PointSensitivityBuilder builder = PointSensitivityBuilder.none();
    for (PaymentPeriod period : fixedLeg.expand().getPaymentPeriods()) {
      builder = builder.combinedWith(paymentPeriodPricer.pvbpSensitivity(period, provider));
    }
    return builder;
  }