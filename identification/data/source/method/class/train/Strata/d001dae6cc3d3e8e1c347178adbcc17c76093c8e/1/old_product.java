public CurrencyAmount netAmount(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider) {

    PaymentPeriod settlement = trade.getSettlement();
    if (settlement instanceof KnownAmountPaymentPeriod) {
      Payment payment = ((KnownAmountPaymentPeriod) settlement).getPayment();
      return payment.getValue();
    } else if (settlement instanceof CapitalIndexedBondPaymentPeriod) {
      CapitalIndexedBondPaymentPeriod casted = (CapitalIndexedBondPaymentPeriod) settlement;
      double netAmount = productPricer.getPeriodPricer().forecastValue(casted, ratesProvider);
      return CurrencyAmount.of(casted.getCurrency(), netAmount);
    }
    throw new UnsupportedOperationException("unsupported settlement type");
  }