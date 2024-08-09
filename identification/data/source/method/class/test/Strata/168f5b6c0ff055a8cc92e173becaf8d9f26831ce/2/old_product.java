public double annuityCash(SwapLeg fixedLeg, double forward) {
    ExpandedSwapLeg expanded = fixedLeg.expand();
    int nbFixedPeriod =  expanded.getPaymentPeriods().size();
    PaymentPeriod paymentPeriod = expanded.getPaymentPeriods().get(0);
    ArgChecker.isTrue(paymentPeriod instanceof RatePaymentPeriod, "payment period should be RatePaymentPeriod");
    RatePaymentPeriod ratePaymentPeriod = (RatePaymentPeriod) paymentPeriod;
    int nbFixedPaymentYear = (int) Math.round(1d /
        ratePaymentPeriod.getDayCount().yearFraction(ratePaymentPeriod.getStartDate(), ratePaymentPeriod.getEndDate()));
    double notional = Math.abs(ratePaymentPeriod.getNotional());
    double annuityCash = notional * (1d - Math.pow(1d + forward / nbFixedPaymentYear, -nbFixedPeriod)) / forward;
    return annuityCash;
  }