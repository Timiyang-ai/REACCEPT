public abstract IborFutureTrade createTrade(
      LocalDate tradeDate,
      Period minimumPeriod,
      int sequenceNumber,
      long quantity,
      double notional,
      double price);