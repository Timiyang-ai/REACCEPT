public SwapTrade createTrade(LocalDate tradeDate,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread,
      ReferenceData refData) {

    return convention.createTrade(
        tradeDate, periodToStart, tenor, buySell, notionalSpreadLeg, notionalFlatLeg, spread, refData);
  }