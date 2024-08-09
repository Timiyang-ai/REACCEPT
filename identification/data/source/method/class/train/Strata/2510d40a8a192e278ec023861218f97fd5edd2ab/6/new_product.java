public double pvbp(SwapLeg leg, RatesProvider provider) {
    double pvbpFixedLeg = 0.0;
    for (PaymentPeriod period : leg.expand().getPaymentPeriods()) {
      ArgChecker.isTrue(period instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
      pvbpFixedLeg += pvbpPayment((RatePaymentPeriod) period, provider);
    }
    return pvbpFixedLeg;
  }