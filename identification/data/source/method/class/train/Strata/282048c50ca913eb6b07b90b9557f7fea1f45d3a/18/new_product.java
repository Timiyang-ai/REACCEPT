public MultiCurrencyAmount currentCash(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount ccCms = productPricer.currentCash(trade.getProduct(), ratesProvider, swaptionVolatilities);
    if (!trade.getPremium().isPresent()) {
      return ccCms;
    }
    Payment premium = trade.getPremium().get();
    if (premium.getDate().equals(ratesProvider.getValuationDate())) {
      ccCms = ccCms.plus(premium.getCurrency(), premium.getAmount());
    }
    return ccCms;
  }