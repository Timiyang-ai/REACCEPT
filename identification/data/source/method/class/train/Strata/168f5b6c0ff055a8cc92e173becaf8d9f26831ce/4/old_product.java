public double annuityCashDerivative(ResolvedSwapLeg fixedLeg, double yield) {
    int nbFixedPeriod = fixedLeg.getPaymentPeriods().size();
    PaymentPeriod paymentPeriod = fixedLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(paymentPeriod instanceof RatePaymentPeriod, "payment period should be RatePaymentPeriod");
    RatePaymentPeriod ratePaymentPeriod = (RatePaymentPeriod) paymentPeriod;
    int nbFixedPaymentYear = (int) Math.round(1d /
        ratePaymentPeriod.getDayCount().yearFraction(ratePaymentPeriod.getStartDate(), ratePaymentPeriod.getEndDate()));
    double notional = Math.abs(ratePaymentPeriod.getNotional());
    double fwdOverPeriods = yield / nbFixedPaymentYear;
    int nbFixedPeriodPlus = 1 + nbFixedPeriod;
    double annuityCashDerivative = notional * Math.pow(yield, -2)
        * ((1d + nbFixedPeriodPlus * fwdOverPeriods) * Math.pow(1d + fwdOverPeriods, -nbFixedPeriodPlus) - 1d);
    return annuityCashDerivative;
  }