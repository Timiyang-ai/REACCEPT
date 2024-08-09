public DoubleArray presentValueSensitivityHullWhiteParameter(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    validate(swaption, ratesProvider, hwProvider);
    ResolvedSwap swap = swaption.getUnderlying();
    LocalDate expiryDate = swaption.getExpiryDate();
    if (expiryDate.isBefore(ratesProvider.getValuationDate())) { // Option has expired already
      return DoubleArray.EMPTY;
    }
    ResolvedSwapLeg cashFlowEquiv = CashFlowEquivalentCalculator.cashFlowEquivalentSwap(swap, ratesProvider);
    int nPayments = cashFlowEquiv.getPaymentEvents().size();
    double[] alpha = new double[nPayments];
    double[][] alphaAdjoint = new double[nPayments][];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      PaymentEvent payment = cashFlowEquiv.getPaymentEvents().get(loopcf);
      ValueDerivatives valueDeriv = hwProvider.alphaAdjoint(
          ratesProvider.getValuationDate(), expiryDate, expiryDate, payment.getPaymentDate());
      alpha[loopcf] = valueDeriv.getValue();
      alphaAdjoint[loopcf] = valueDeriv.getDerivatives().toArray();
      discountedCashFlow[loopcf] = paymentEventPricer.presentValue(payment, ratesProvider);
    }
    double omega = (swap.getLegs(SwapLegType.FIXED).get(0).getPayReceive().isPay() ? -1d : 1d);
    double kappa = computeKappa(hwProvider, discountedCashFlow, alpha, omega);
    int nParams = alphaAdjoint[0].length;
    if (Math.abs(kappa) > 1d / SMALL) { // decays exponentially
      return DoubleArray.filled(nParams);
    }
    double[] pvSensi = new double[nParams];
    double sign = (swaption.getLongShort().isLong() ? 1d : -1d);
    for (int i = 0; i < nParams; ++i) {
      for (int loopcf = 0; loopcf < nPayments; loopcf++) {
        pvSensi[i] += sign * discountedCashFlow[loopcf] * NORMAL.getPDF(omega * (kappa + alpha[loopcf])) *
            omega * alphaAdjoint[loopcf][i];
      }
    }
    return DoubleArray.ofUnsafe(pvSensi);
  }