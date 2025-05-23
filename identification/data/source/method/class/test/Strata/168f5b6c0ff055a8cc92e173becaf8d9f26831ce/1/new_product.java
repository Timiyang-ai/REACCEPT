public ValueDerivatives annuityCashDerivative(ResolvedSwapLeg fixedLeg, double yield) {
    int nbFixedPeriod = fixedLeg.getPaymentPeriods().size();
    SwapPaymentPeriod paymentPeriod = fixedLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(paymentPeriod instanceof RatePaymentPeriod, "payment period should be RatePaymentPeriod");
    RatePaymentPeriod ratePaymentPeriod = (RatePaymentPeriod) paymentPeriod;
    int nbFixedPaymentYear = (int) Math.round(1d /
        ratePaymentPeriod.getDayCount().yearFraction(ratePaymentPeriod.getStartDate(), ratePaymentPeriod.getEndDate()));
    double notional = Math.abs(ratePaymentPeriod.getNotional());
    ValueDerivatives annuityUnit = annuityCash1(nbFixedPaymentYear, nbFixedPeriod, yield);
    return ValueDerivatives.of(annuityUnit.getValue() * notional, annuityUnit.getDerivatives().multipliedBy(notional));
  }