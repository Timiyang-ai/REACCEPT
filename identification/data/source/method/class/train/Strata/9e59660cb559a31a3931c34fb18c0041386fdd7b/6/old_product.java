public DoubleArray presentValueSensitivityHullWhiteParameter(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ExpandedSwaption expanded = swaption.expand();
    validate(expanded, ratesProvider, hwProvider);
    ExpandedSwap swap = expanded.getUnderlying();
    ExpandedSwapLeg cashFlowEquiv = CashFlowEquivalentCalculator.cashFlowEquivalent(swap);
    LocalDate expiryDate = expanded.getExpiryDate();
    if (expiryDate.isBefore(ratesProvider.getValuationDate())) { // Option has expired already
      return DoubleArray.EMPTY;
    }
    int nPayments = cashFlowEquiv.getPaymentPeriods().size();
    double[] alpha = new double[nPayments];
    double[][] alphaAdjoint = new double[nPayments][];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      PaymentPeriod payment = cashFlowEquiv.getPaymentPeriods().get(loopcf);
      LocalDate maturityDate = cashFlowEquivalentMaturityDate(payment);
      ValueDerivatives valueDeriv = hwProvider.alphaAdjoint(
          ratesProvider.getValuationDate(), expiryDate, expiryDate, maturityDate);
      alpha[loopcf] = valueDeriv.getValue();
      alphaAdjoint[loopcf] = valueDeriv.getDerivatives().toArray();
      discountedCashFlow[loopcf] = paymentPeriodPricer.presentValueCashFlowEquivalent(payment, ratesProvider);
    }
    double omega = (swap.getLegs(SwapLegType.FIXED).get(0).getPayReceive().isPay() ? -1d : 1d);
    double kappa = computeKappa(hwProvider, discountedCashFlow, alpha, omega);
    int nParams = alphaAdjoint[0].length;
    if (Math.abs(kappa) > 1d / SMALL) { // decays exponentially
      return DoubleArray.filled(nParams);
    }
    double[] pvSensi = new double[nParams];
    double sign = (expanded.getLongShort().isLong() ? 1d : -1d);
    for (int i = 0; i < nParams; ++i) {
      for (int loopcf = 0; loopcf < nPayments; loopcf++) {
        pvSensi[i] += sign * discountedCashFlow[loopcf] * NORMAL.getPDF(omega * (kappa + alpha[loopcf])) *
            omega * alphaAdjoint[loopcf][i];
      }
    }
    return DoubleArray.copyOf(pvSensi);
  }