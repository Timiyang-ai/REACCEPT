public double annuityCash(ResolvedSwapLeg fixedLeg, double yield) {
    int nbFixedPeriod = fixedLeg.getPaymentPeriods().size();
    PaymentPeriod paymentPeriod = fixedLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(paymentPeriod instanceof RatePaymentPeriod, "payment period should be RatePaymentPeriod");
    RatePaymentPeriod ratePaymentPeriod = (RatePaymentPeriod) paymentPeriod;
    int nbFixedPaymentYear = (int) Math.round(1d /
        ratePaymentPeriod.getDayCount().yearFraction(ratePaymentPeriod.getStartDate(), ratePaymentPeriod.getEndDate()));
    double notional = Math.abs(ratePaymentPeriod.getNotional());
    double annuityCash = notional * annuityCash(nbFixedPaymentYear, nbFixedPeriod, yield);
    return annuityCash;
  }