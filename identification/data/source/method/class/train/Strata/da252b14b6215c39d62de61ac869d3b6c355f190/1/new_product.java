public PointSensitivityBuilder pvbpSensitivity(ResolvedSwapLeg fixedLeg, RatesProvider provider) {
    PointSensitivityBuilder builder = PointSensitivityBuilder.none();
    for (PaymentPeriod period : fixedLeg.getPaymentPeriods()) {
      builder = builder.combinedWith(paymentPeriodPricer.pvbpSensitivity(period, provider));
    }
    return builder;
  }