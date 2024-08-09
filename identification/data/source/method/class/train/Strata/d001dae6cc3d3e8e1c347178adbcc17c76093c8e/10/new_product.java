public CurrencyAmount netAmount(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider) {

    if (!trade.getSettlement().isPresent()) {
      // position has no settlement, thus it has no value
      return CurrencyAmount.zero(trade.getProduct().getCurrency());
    }
    BondPaymentPeriod settlePeriod = trade.getSettlement().get().getPayment();
    if (settlePeriod instanceof KnownAmountBondPaymentPeriod) {
      Payment payment = ((KnownAmountBondPaymentPeriod) settlePeriod).getPayment();
      return payment.getValue();
    } else if (settlePeriod instanceof CapitalIndexedBondPaymentPeriod) {
      CapitalIndexedBondPaymentPeriod casted = (CapitalIndexedBondPaymentPeriod) settlePeriod;
      double netAmount = productPricer.getPeriodPricer().forecastValue(casted, ratesProvider);
      return CurrencyAmount.of(casted.getCurrency(), netAmount);
    }
    throw new UnsupportedOperationException("unsupported settlement type");
  }