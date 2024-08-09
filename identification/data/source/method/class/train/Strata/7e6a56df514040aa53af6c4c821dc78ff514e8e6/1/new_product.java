public default CreditRatesMarketData marketDataView(MarketData marketData) {
    return DefaultCreditRatesMarketData.of(this, marketData);
  }