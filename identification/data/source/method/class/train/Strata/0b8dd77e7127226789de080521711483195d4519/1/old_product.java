public abstract IborFixingDepositTrade toTrade(
      LocalDate tradeDate,
      Period depositPeriod,
      BuySell buySell,
      double notional,
      double fixedRate);