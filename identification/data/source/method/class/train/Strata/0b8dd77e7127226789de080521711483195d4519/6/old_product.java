public abstract SwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread);