public FraTrade toTrade(
      LocalDate tradeDate,
      Period periodToStart,
      Period periodToEnd,
      BuySell buySell,
      double notional,
      double fixedRate) {

    LocalDate spotValue = getSpotDateOffset().adjust(tradeDate);
    LocalDate startDate = spotValue.plus(periodToStart);
    LocalDate endDate = spotValue.plus(periodToEnd);
    return toTrade(tradeDate, startDate, endDate, buySell, notional, fixedRate);
  }