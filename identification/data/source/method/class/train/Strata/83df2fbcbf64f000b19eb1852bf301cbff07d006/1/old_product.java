@Override
  public double forecastValue(NotionalExchange event, RatesProvider provider) {
    // paymentAmount
    return event.getPaymentAmount().getAmount();
  }