public double pvbp(ResolvedSwapLeg leg, RatesProvider provider) {
    double pvbpLeg = 0d;
    for (PaymentPeriod period : leg.getPaymentPeriods()) {
      pvbpLeg += paymentPeriodPricer.pvbp(period, provider);
    }
    return pvbpLeg;
  }