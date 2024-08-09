public PointSensitivityBuilder presentValueSensitivity(ResolvedSwap swap, Currency currency, RatesProvider provider) {
    PointSensitivityBuilder builder = PointSensitivityBuilder.none();
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      PointSensitivityBuilder ls = legPricer.presentValueSensitivity(leg, provider);
      PointSensitivityBuilder lsConverted =
          ls.withCurrency(currency).multipliedBy(provider.fxRate(leg.getCurrency(), currency));
      builder = builder.combinedWith(lsConverted);
    }
    return builder;
  }