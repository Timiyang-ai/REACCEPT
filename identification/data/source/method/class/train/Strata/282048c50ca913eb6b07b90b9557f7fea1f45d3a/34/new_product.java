public MultiCurrencyAmount currencyExposure(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount ceCms = productPricer.currencyExposure(trade.getProduct(), ratesProvider, swaptionVolatilities);
    if (!trade.getPremium().isPresent()) {
      return ceCms;
    }
    CurrencyAmount pvPremium = paymentPricer.presentValue(trade.getPremium().get(), ratesProvider);
    return ceCms.plus(pvPremium);
  }