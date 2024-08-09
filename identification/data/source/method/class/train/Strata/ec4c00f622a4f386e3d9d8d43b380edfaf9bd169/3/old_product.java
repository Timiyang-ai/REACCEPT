public abstract FraTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      LocalDate paymentDate,
      BuySell buySell,
      double notional,
      double fixedRate);