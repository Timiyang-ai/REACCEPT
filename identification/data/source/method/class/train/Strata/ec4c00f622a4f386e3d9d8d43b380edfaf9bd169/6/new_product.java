@Override
  public default SwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate) {

    return SingleCurrencySwapConvention.super.toTrade(tradeDate, startDate, endDate, buySell, notional, fixedRate);
  }