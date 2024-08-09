public PointSensitivityBuilder presentValueSensitivityRates(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    validate(swaption, ratesProvider, hwProvider);
    ResolvedSwap swap = swaption.getUnderlying();
    LocalDate expiryDate = swaption.getExpiryDate();
    if (expiryDate.isBefore(ratesProvider.getValuationDate())) { // Option has expired already
      return PointSensitivityBuilder.none();
    }
    ImmutableMap<NotionalExchange, PointSensitivityBuilder> cashFlowEquivSensi =
        CashFlowEquivalentCalculator.cashFlowEquivalentAndSensitivitySwap(swap, ratesProvider);
    ImmutableList<NotionalExchange> list = cashFlowEquivSensi.keySet().asList();
    ImmutableList<PointSensitivityBuilder> listSensi = cashFlowEquivSensi.values().asList();
    int nPayments = list.size();
    double[] alpha = new double[nPayments];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      NotionalExchange payment = list.get(loopcf);
      alpha[loopcf] = hwProvider.alpha(ratesProvider.getValuationDate(), expiryDate, expiryDate, payment.getPaymentDate());
      discountedCashFlow[loopcf] = paymentEventPricer.presentValue(payment, ratesProvider);
    }
    double omega = (swap.getLegs(SwapLegType.FIXED).get(0).getPayReceive().isPay() ? -1d : 1d);
    double kappa = computeKappa(hwProvider, discountedCashFlow, alpha, omega);
    PointSensitivityBuilder point = PointSensitivityBuilder.none();
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      NotionalExchange payment = list.get(loopcf);
      double cdf = NORMAL.getCDF(omega * (kappa + alpha[loopcf]));
      point = point.combinedWith(paymentEventPricer.presentValueSensitivity(payment, ratesProvider).multipliedBy(cdf));
      if (!listSensi.get(loopcf).equals(PointSensitivityBuilder.none())) {
        point = point.combinedWith(listSensi.get(loopcf)
            .multipliedBy(cdf * ratesProvider.discountFactor(payment.getCurrency(), payment.getPaymentDate())));
      }
    }
    return swaption.getLongShort().isLong() ? point : point.multipliedBy(-1d);
  }