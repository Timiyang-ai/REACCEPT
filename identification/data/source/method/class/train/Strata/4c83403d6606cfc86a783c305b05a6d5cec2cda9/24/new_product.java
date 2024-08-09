public CurrencyAmount currentCash(ResolvedFxNdf ndf, RatesProvider provider) {
    Currency ccySettle = ndf.getSettlementCurrency();
    if (provider.getValuationDate().isEqual(ndf.getPaymentDate())) {
      Currency ccyOther = ndf.getNonDeliverableCurrency();
      CurrencyAmount notionalSettle = ndf.getSettlementCurrencyNotional();
      double agreedRate = ndf.getAgreedFxRate().fxRate(ccySettle, ccyOther);
      LocalDate fixingDate = ndf.getIndex().calculateFixingFromMaturity(ndf.getPaymentDate());
      double rate = provider.fxIndexRates(ndf.getIndex()).rate(ccySettle, fixingDate);
      return notionalSettle.multipliedBy(1d - agreedRate / rate);
    }
    return CurrencyAmount.zero(ccySettle);
  }