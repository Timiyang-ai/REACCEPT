public SwapTrade createTrade(LocalDate tradeDate,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread) {

    return convention.createTrade(tradeDate, periodToStart, tenor, buySell, notionalSpreadLeg, notionalFlatLeg, spread);
  }