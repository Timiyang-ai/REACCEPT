@Override
  public MultiCurrencyAmount currencyExposure(NotionalExchange event, RatesProvider provider) {
    return paymentPricer.currencyExposure(event.getPayment(), provider);
  }