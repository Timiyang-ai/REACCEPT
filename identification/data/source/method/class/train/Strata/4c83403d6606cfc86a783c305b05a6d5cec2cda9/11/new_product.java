public PointSensitivities presentValueSensitivity(ResolvedFxNdf ndf, RatesProvider provider) {
    if (provider.getValuationDate().isAfter(ndf.getPaymentDate())) {
      return PointSensitivities.empty();
    }
    Currency ccySettle = ndf.getSettlementCurrency();
    Currency ccyOther = ndf.getNonDeliverableCurrency();
    double notionalSettle = ndf.getSettlementNotional();
    double agreedRate = ndf.getAgreedFxRate().fxRate(ccySettle, ccyOther);
    double forwardRate = provider.fxIndexRates(ndf.getIndex()).rate(ndf.getObservation(), ccySettle);
    double dfSettle = provider.discountFactor(ccySettle, ndf.getPaymentDate());
    double ratio = agreedRate / forwardRate;
    double dscBar = (1d - ratio) * notionalSettle;
    PointSensitivityBuilder sensiDsc =
        provider.discountFactors(ccySettle).zeroRatePointSensitivity(ndf.getPaymentDate()).multipliedBy(dscBar);
    double forwardRateBar = dfSettle * notionalSettle * ratio / forwardRate;
    PointSensitivityBuilder sensiFx = provider.fxIndexRates(ndf.getIndex())
        .ratePointSensitivity(ndf.getObservation(), ccySettle).withCurrency(ccySettle).multipliedBy(forwardRateBar);
    return sensiDsc.combinedWith(sensiFx).build();
  }