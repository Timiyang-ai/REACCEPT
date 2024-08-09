public MultiCurrencyAmount currencyExposure(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider discountingProvider,
      BondFutureVolatilities volatilities,
      double lastOptionSettlementPrice) {

    double price = price(trade, discountingProvider, volatilities);
    return currencyExposure(trade, discountingProvider.getValuationDate(), price, lastOptionSettlementPrice);
  }