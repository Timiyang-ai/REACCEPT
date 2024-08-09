public IborFutureTrade toTrade(
      LocalDate tradeDate,
      Period minimumPeriod,
      int sequenceNumber,
      long quantity,
      double notional,
      double price) {

    LocalDate referenceDate = referenceDate(tradeDate, minimumPeriod, sequenceNumber);
    double accrualFactor = index.getTenor().get(ChronoUnit.MONTHS) / 12.0;
    LocalDate lastTradeDate = index.calculateFixingFromEffective(referenceDate);
    IborFuture underlying = IborFuture.builder()
        .index(index)
        .accrualFactor(accrualFactor)
        .lastTradeDate(lastTradeDate)
        .notional(notional).build();
    YearMonth m = YearMonth.from(lastTradeDate);
    Security<IborFuture> security = UnitSecurity.builder(underlying)
        .standardId(StandardId.of("OG-Ticker", "IborFuture-" + index.getName() + "-" + m.toString()))
        .build();
    SecurityLink<IborFuture> securityLink = SecurityLink.resolved(security);
    TradeInfo info = TradeInfo.builder().tradeDate(tradeDate).build();
    return IborFutureTrade.builder()
        .quantity(quantity).initialPrice(price).securityLink(securityLink).tradeInfo(info).build();
  }