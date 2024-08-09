public CurrencyAmount presentValue(ResolvedFxNdf ndf, RatesProvider provider) {
    Currency ccySettle = ndf.getSettlementCurrency();
    if (provider.getValuationDate().isAfter(ndf.getPaymentDate())) {
      return CurrencyAmount.zero(ccySettle);
    }
    Currency ccyOther = ndf.getNonDeliverableCurrency();
    CurrencyAmount notionalSettle = ndf.getSettlementCurrencyNotional();
    double agreedRate = ndf.getAgreedFxRate().fxRate(ccySettle, ccyOther);
    double forwardRate = provider.fxIndexRates(ndf.getIndex()).rate(ndf.getObservation(), ccySettle);
    double dfSettle = provider.discountFactor(ccySettle, ndf.getPaymentDate());
    return notionalSettle.multipliedBy(dfSettle * (1d - agreedRate / forwardRate));
  }