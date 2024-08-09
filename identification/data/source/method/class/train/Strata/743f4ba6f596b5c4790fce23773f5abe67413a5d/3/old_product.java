public CurrencyValuesArray presentValue(
      ResolvedSwaptionTrade trade,
      RatesMarketDataLookup ratesLookup,
      SwaptionMarketDataLookup swaptionLookup,
      ScenarioMarketData marketData) {

    return calc.presentValue(
        trade,
        ratesLookup.marketDataView(marketData),
        swaptionLookup.marketDataView(marketData));
  }