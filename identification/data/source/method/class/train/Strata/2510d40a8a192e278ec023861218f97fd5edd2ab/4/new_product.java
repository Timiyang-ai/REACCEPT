public double pvbp(SwapLeg leg, RatesProvider provider) {
    double pvbpLeg = 0d;
    for (PaymentPeriod period : leg.expand().getPaymentPeriods()) {
      pvbpLeg += paymentPeriodPricer.pvbp(period, provider);
    }
    return pvbpLeg;
  }