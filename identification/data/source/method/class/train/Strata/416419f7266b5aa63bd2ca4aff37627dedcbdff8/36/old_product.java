public BondFutureOptionSensitivity presentValueSensitivityBlackVolatility(
      BondFutureOptionTrade futureOptionTrade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider,
      double futurePrice) {

    BondFutureOption product = futureOptionTrade.getProduct();
    BondFutureOptionSensitivity priceSensitivity =
        futureOptionPricer.priceSensitivityBlackVolatility(product, ratesProvider, volatilityProvider, futurePrice);
    double factor = futureOptionPricer.marginIndex(product, 1) * futureOptionTrade.getQuantity();
    return priceSensitivity.withSensitivity(priceSensitivity.getSensitivity() * factor);
  }