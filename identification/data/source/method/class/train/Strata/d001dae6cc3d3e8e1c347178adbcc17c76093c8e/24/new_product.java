public CurrencyAmount currentCash(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider) {

    LocalDate valuationDate = ratesProvider.getValuationDate();
    LocalDate settlementDate = settlementDate(trade, valuationDate);
    CurrencyAmount cashProduct = productPricer.currentCash(trade.getProduct(), ratesProvider, settlementDate);
    if (!trade.getSettlement().isPresent()) {
      return cashProduct;
    }
    BondPaymentPeriod settlePeriod = trade.getSettlement().get().getPayment();
    double cashSettle = settlePeriod.getPaymentDate().isEqual(valuationDate) ? netAmount(trade, ratesProvider).getAmount() : 0d;
    return cashProduct.plus(cashSettle);
  }