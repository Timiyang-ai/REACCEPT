public double pvbp(RatesProvider provider, SwapLeg fixedLeg) {
    double pvbpFixedLeg = 0.0;
    for (PaymentPeriod period : fixedLeg.expand().getPaymentPeriods()) {
      ArgChecker.isTrue(period instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
      pvbpFixedLeg += pvbpPayment(provider, (RatePaymentPeriod) period);
    }
    return pvbpFixedLeg;
  }