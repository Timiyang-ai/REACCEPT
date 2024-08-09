public MultiCurrencyAmount currencyExposure(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider ratesProvider,
      BondFutureVolatilities volatilities,
      double lastOptionSettlementPrice) {

    double price = price(trade, ratesProvider, volatilities);
    return currencyExposure(trade, ratesProvider.getValuationDate(), price, lastOptionSettlementPrice);
  }