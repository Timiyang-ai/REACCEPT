public double pvbp(SwapLeg fixedLeg, RatesProvider provider) {
    double pvbpFixedLeg = 0.0;
    for (PaymentPeriod period : fixedLeg.expand().getPaymentPeriods()) {
      ArgChecker.isTrue(period instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
      pvbpFixedLeg += pvbpPayment((RatePaymentPeriod) period, provider);
    }
    return pvbpFixedLeg;
  }