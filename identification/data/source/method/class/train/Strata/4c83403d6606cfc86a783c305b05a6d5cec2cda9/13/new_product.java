public MultiCurrencyAmount currencyExposure(ResolvedFxNdf ndf, RatesProvider provider) {
    if (provider.getValuationDate().isAfter(ndf.getPaymentDate())) {
      return MultiCurrencyAmount.empty();
    }
    Currency ccySettle = ndf.getSettlementCurrency();
    CurrencyAmount notionalSettle = ndf.getSettlementCurrencyNotional();
    double dfSettle = provider.discountFactor(ccySettle, ndf.getPaymentDate());
    Currency ccyOther = ndf.getNonDeliverableCurrency();
    double agreedRate = ndf.getAgreedFxRate().fxRate(ccySettle, ccyOther);
    double dfOther = provider.discountFactor(ccyOther, ndf.getPaymentDate());
    return MultiCurrencyAmount.of(notionalSettle.multipliedBy(dfSettle))
        .plus(CurrencyAmount.of(ccyOther, -notionalSettle.getAmount() * agreedRate * dfOther));
  }