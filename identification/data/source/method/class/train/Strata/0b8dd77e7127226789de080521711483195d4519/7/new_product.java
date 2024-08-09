public default SwapTrade createTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread) {

    return createTrade(tradeDate, Period.ZERO, tenor, buySell, notionalSpreadLeg, notionalFlatLeg, spread);
  }