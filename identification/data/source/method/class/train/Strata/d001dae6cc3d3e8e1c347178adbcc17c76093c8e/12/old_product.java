public CurrencyAmount currentCash(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider) {

    LocalDate valuationDate = ratesProvider.getValuationDate();
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PaymentPeriod settle = trade.getSettlement();
    CurrencyAmount cashProduct = productPricer.currentCash(trade.getProduct(), ratesProvider, settlementDate);
    double cashSettle =
        settle.getPaymentDate().isEqual(valuationDate) ? netAmount(trade, ratesProvider).getAmount() : 0d;
    return cashProduct.plus(cashSettle);
  }