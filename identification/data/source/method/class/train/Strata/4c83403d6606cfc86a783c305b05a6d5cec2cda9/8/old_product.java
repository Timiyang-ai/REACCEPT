public MultiCurrencyAmount currentCash(FxSwapProduct product, LocalDate valuationDate) {
    ExpandedFxSwap fx = product.expand();
    MultiCurrencyAmount farPv = fxPricer.currentCash(fx.getFarLeg(), valuationDate);
    MultiCurrencyAmount nearPv = fxPricer.currentCash(fx.getNearLeg(), valuationDate);
    return nearPv.plus(farPv);
  }