public abstract IborFixingDepositTrade createTrade(
      LocalDate tradeDate,
      Period depositPeriod,
      BuySell buySell,
      double notional,
      double fixedRate);