public abstract IborFutureTrade toTrade(
      LocalDate tradeDate,
      Period minimumPeriod,
      int sequenceNumber,
      long quantity,
      double notional,
      double price);