public CurrencyAmount currentCash(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      double realCleanPrice) {

    LocalDate valuationDate = ratesProvider.getValuationDate();
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    CapitalIndexedBondPaymentPeriod settle = trade.getSettlement();
    CurrencyAmount cashProduct = productPricer.currentCash(trade.getProduct(), ratesProvider, settlementDate);
    double cashSettle = settle.getPaymentDate().isEqual(valuationDate) ?
        netAmount(trade, ratesProvider, realCleanPrice).getAmount() : 0d;
    return cashProduct.plus(cashSettle);
  }