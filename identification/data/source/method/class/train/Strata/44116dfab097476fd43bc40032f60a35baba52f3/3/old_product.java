public abstract SwapTrade toTrade(
      TradeInfo tradeInfo,
      LocalDate startDate,
      LocalDate endDate,
      Period lag,
      BuySell buySell,
      double notional,
      double fixedRate);