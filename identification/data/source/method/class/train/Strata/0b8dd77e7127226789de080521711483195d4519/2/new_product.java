public default SwapTrade createTrade(
      LocalDate tradeDate,
      Period periodToStart,
      Tenor tenor,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread) {

    LocalDate spotValue = calculateSpotDateFromTradeDate(tradeDate);
    LocalDate startDate = spotValue.plus(periodToStart);
    LocalDate endDate = startDate.plus(tenor.getPeriod());
    return toTrade(tradeDate, startDate, endDate, buySell, notionalSpreadLeg, notionalFlatLeg, spread);
  }