public CurrencyAmount netAmount(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      double realCleanPrice) {

    CapitalIndexedBondPaymentPeriod settlement = trade.getSettlement();
    LocalDate paymentDate = settlement.getPaymentDate();
    double notional = trade.getProduct().getNotional();
    double netAmountRealByUnit =
        realCleanPrice + productPricer.accruedInterest(trade.getProduct(), paymentDate) / notional;
    double netAmount = productPricer.getPeriodPricer().forecastValue(settlement, ratesProvider);
    return CurrencyAmount.of(settlement.getCurrency(), netAmount * netAmountRealByUnit);
  }