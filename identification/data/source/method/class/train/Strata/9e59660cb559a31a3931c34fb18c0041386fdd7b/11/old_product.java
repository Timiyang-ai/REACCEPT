public CurrencyAmount presentValue(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ExpandedSwaption expanded = swaption.expand();
    validate(expanded, ratesProvider, hwProvider);
    ExpandedSwap swap = expanded.getUnderlying();
    ExpandedSwapLeg cashFlowEquiv = CashFlowEquivalentCalculator.cashFlowEquivalent(swap);
    LocalDate expiryDate = expanded.getExpiryDate();
    if (expiryDate.isBefore(ratesProvider.getValuationDate())) { // Option has expired already
      return CurrencyAmount.of(cashFlowEquiv.getCurrency(), 0d);
    }
    int nPayments = cashFlowEquiv.getPaymentPeriods().size();
    double[] alpha = new double[nPayments];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      PaymentPeriod payment = cashFlowEquiv.getPaymentPeriods().get(loopcf);
      LocalDate maturityDate = cashFlowEquivalentMaturityDate(payment);
      alpha[loopcf] = hwProvider.alpha(ratesProvider.getValuationDate(), expiryDate, expiryDate, maturityDate);
      discountedCashFlow[loopcf] = paymentPeriodPricer.presentValueCashFlowEquivalent(payment, ratesProvider);
    }
    double omega = (swap.getLegs(SwapLegType.FIXED).get(0).getPayReceive().isPay() ? -1d : 1d);
    double kappa = computeKappa(hwProvider, discountedCashFlow, alpha, omega);
    double pv = 0.0;
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      pv += discountedCashFlow[loopcf] * NORMAL.getCDF(omega * (kappa + alpha[loopcf]));
    }
    return CurrencyAmount.of(cashFlowEquiv.getCurrency(), pv * (expanded.getLongShort().isLong() ? 1d : -1d));
  }