public SwapTrade toTrade(
      StandardId id,
      LocalDate tradeDate,
      Period periodToStart,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate) {

    LocalDate spotValue = getSpotDateOffset().adjust(tradeDate);
    LocalDate startDate = spotValue.plus(periodToStart);
    LocalDate endDate = startDate.plus(tenor.getPeriod());
    return toTrade(id, tradeDate, startDate, endDate, buySell, notional, fixedRate);
  }