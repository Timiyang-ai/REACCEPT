public CurrencyAmount presentValue(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ExpandedSwaption expanded = swaption.expand();
    validate(expanded, ratesProvider, hwProvider);
    ExpandedSwap swap = expanded.getUnderlying();
    LocalDate expiryDate = expanded.getExpiryDate();
    if (expiryDate.isBefore(ratesProvider.getValuationDate())) { // Option has expired already
      return CurrencyAmount.of(swap.getLegs().get(0).getCurrency(), 0d);
    }
    ExpandedSwapLeg cashFlowEquiv = CashFlowEquivalentCalculator.cashFlowEquivalentSwap(swap, ratesProvider);
    int nPayments = cashFlowEquiv.getPaymentEvents().size();
    double[] alpha = new double[nPayments];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      PaymentEvent payment = cashFlowEquiv.getPaymentEvents().get(loopcf);
      LocalDate maturityDate = payment.getPaymentDate();
      alpha[loopcf] = hwProvider.alpha(ratesProvider.getValuationDate(), expiryDate, expiryDate, maturityDate);
      discountedCashFlow[loopcf] = paymentEventPricer.presentValue(payment, ratesProvider);
    }
    double omega = (swap.getLegs(SwapLegType.FIXED).get(0).getPayReceive().isPay() ? -1d : 1d);
    double kappa = computeKappa(hwProvider, discountedCashFlow, alpha, omega);
    double pv = 0.0;
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      pv += discountedCashFlow[loopcf] * NORMAL.getCDF(omega * (kappa + alpha[loopcf]));
    }
    return CurrencyAmount.of(cashFlowEquiv.getCurrency(), pv * (expanded.getLongShort().isLong() ? 1d : -1d));
  }