public double pvbp(PricingEnvironment env, SwapLeg fixedLeg) {
    double pvbpFixedLeg = 0.0;
    for (PaymentPeriod period : fixedLeg.expand().getPaymentPeriods()) {
      ArgChecker.isTrue(period instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
      pvbpFixedLeg += pvbpPayment(env, (RatePaymentPeriod) period);
    }
    return pvbpFixedLeg;
  }