public MultiCurrencyAmount currentCash(ResolvedFxSwap swap, LocalDate valuationDate) {
    MultiCurrencyAmount farPv = fxPricer.currentCash(swap.getFarLeg(), valuationDate);
    MultiCurrencyAmount nearPv = fxPricer.currentCash(swap.getNearLeg(), valuationDate);
    return nearPv.plus(farPv);
  }