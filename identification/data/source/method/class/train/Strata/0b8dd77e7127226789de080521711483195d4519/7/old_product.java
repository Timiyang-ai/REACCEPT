public default SwapTrade toTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread) {

    return toTrade(tradeDate, Period.ZERO, tenor, buySell, notionalSpreadLeg, notionalFlatLeg, spread);
  }