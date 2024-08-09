public PointSensitivities priceSensitivity(ResolvedDsf future, RatesProvider ratesProvider) {
    ResolvedSwap swap = future.getUnderlyingSwap();
    Currency currency = future.getCurrency();
    double pvSwap = swapPricer.presentValue(swap, currency, ratesProvider).getAmount();
    double dfInv = 1d / ratesProvider.discountFactor(currency, future.getDeliveryDate());
    PointSensitivityBuilder sensiSwapPv = swapPricer.presentValueSensitivity(swap, ratesProvider).multipliedBy(dfInv);
    PointSensitivityBuilder sensiDf = ratesProvider.discountFactors(currency)
        .zeroRatePointSensitivity(future.getDeliveryDate()).multipliedBy(-pvSwap * dfInv * dfInv);
    return sensiSwapPv.combinedWith(sensiDf).build();
  }