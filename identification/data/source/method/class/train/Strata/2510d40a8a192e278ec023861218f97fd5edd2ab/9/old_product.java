public PointSensitivityBuilder presentValueSensitivity(SwapProduct product, Currency currency, RatesProvider provider) {
    PointSensitivityBuilder builder = PointSensitivityBuilder.none();
    for (ExpandedSwapLeg leg : product.expand().getLegs()) {
      PointSensitivityBuilder ls = legPricer.presentValueSensitivity(leg, provider);
      PointSensitivityBuilder lsConverted =
          ls.withCurrency(currency).multipliedBy(provider.fxRate(leg.getCurrency(), currency));
      builder = builder.combinedWith(lsConverted);
    }
    return builder;
  }