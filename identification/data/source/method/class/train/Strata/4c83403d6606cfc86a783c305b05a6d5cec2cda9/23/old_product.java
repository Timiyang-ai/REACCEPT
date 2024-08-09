public MultiCurrencyAmount presentValue(FxSwapProduct product, RatesProvider provider) {
    ExpandedFxSwap fx = product.expand();
    MultiCurrencyAmount farPv = fxPricer.presentValue(fx.getFarLeg(), provider);
    MultiCurrencyAmount nearPv = fxPricer.presentValue(fx.getNearLeg(), provider);
    return nearPv.plus(farPv);
  }