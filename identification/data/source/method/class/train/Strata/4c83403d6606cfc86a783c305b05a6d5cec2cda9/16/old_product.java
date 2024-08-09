public PointSensitivities presentValueSensitivity(FxNdfProduct product, RatesProvider provider) {
    ExpandedFxNdf ndf = product.expand();
    if (provider.getValuationDate().isAfter(ndf.getPaymentDate())) {
      return PointSensitivities.empty();
    }
    Currency ccySettle = ndf.getSettlementCurrency();
    Currency ccyOther = ndf.getNonDeliverableCurrency();
    double notionalSettle = ndf.getSettlementNotional();
    double agreedRate = ndf.getAgreedFxRate().fxRate(ccySettle, ccyOther);
    LocalDate fixingDate = ndf.getIndex().calculateFixingFromMaturity(ndf.getPaymentDate());
    double forwardRate = provider.fxIndexRates(ndf.getIndex()).rate(ccySettle, fixingDate);
    double dfSettle = provider.discountFactor(ccySettle, ndf.getPaymentDate());
    double ratio = agreedRate / forwardRate;
    double dscBar = (1d - ratio) * notionalSettle;
    PointSensitivityBuilder sensiDsc =
        provider.discountFactors(ccySettle).zeroRatePointSensitivity(ndf.getPaymentDate()).multipliedBy(dscBar);
    double forwardRateBar = dfSettle * notionalSettle * ratio / forwardRate;
    PointSensitivityBuilder sensiFx = provider.fxIndexRates(ndf.getIndex())
        .ratePointSensitivity(ccySettle, fixingDate).withCurrency(ccySettle).multipliedBy(forwardRateBar);
    return sensiDsc.combinedWith(sensiFx).build();
  }