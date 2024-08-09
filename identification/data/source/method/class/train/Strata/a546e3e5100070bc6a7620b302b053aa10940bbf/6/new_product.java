public default FraTrade createTrade(
      LocalDate tradeDate,
      Period periodToStart,
      Period periodToEnd,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    LocalDate spotValue = calculateSpotDateFromTradeDate(tradeDate, refData);
    LocalDate startDate = spotValue.plus(periodToStart);
    LocalDate endDate = spotValue.plus(periodToEnd);
    return toTrade(tradeDate, startDate, endDate, buySell, notional, fixedRate);
  }