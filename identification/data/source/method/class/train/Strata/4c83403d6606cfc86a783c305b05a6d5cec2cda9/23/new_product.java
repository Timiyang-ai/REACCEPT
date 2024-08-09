public MultiCurrencyAmount presentValue(ResolvedFxSwap swap, RatesProvider provider) {
    MultiCurrencyAmount farPv = fxPricer.presentValue(swap.getFarLeg(), provider);
    MultiCurrencyAmount nearPv = fxPricer.presentValue(swap.getNearLeg(), provider);
    return nearPv.plus(farPv);
  }