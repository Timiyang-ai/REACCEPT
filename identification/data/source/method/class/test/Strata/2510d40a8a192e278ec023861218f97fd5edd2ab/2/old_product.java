static double pvbp(PricingEnvironment env, SwapLeg fixedLeg) {
    // TODO: make public and non-static
    double pvbpFixedLeg = 0.0;
    for (PaymentPeriod p : fixedLeg.expand().getPaymentPeriods()) {
      RatePaymentPeriod rp = (RatePaymentPeriod) p;
      pvbpFixedLeg += pvbpPayment(env, rp);
    }
    return pvbpFixedLeg;
  }