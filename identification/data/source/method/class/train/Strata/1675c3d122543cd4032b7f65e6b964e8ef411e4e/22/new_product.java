public CurrencyAmount presentValueFromCleanPrice(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    validate(ratesProvider, discountingProvider);
    ResolvedCapitalIndexedBond bond = trade.getProduct();
    LocalDate standardSettlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    LocalDate tradeSettlementDate = trade.getSettlementDate();
    StandardId legalEntityId = bond.getLegalEntityId();
    Currency currency = bond.getCurrency();
    double df = discountingProvider
        .repoCurveDiscountFactors(bond.getSecurityId(), legalEntityId, currency).discountFactor(standardSettlementDate);
    CurrencyAmount pvStandard = forecastValueStandardFromCleanPrice(
        bond, ratesProvider, standardSettlementDate, cleanRealPrice).multipliedBy(df);
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueFromProductPresentValue(trade, ratesProvider, discountingProvider, pvStandard);
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors discountFactors =
        discountingProvider.issuerCurveDiscountFactors(legalEntityId, currency);
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = -productPricer.presentValueCoupon(
          bond, ratesProvider, discountFactors, tradeSettlementDate, standardSettlementDate);
    } else {
      pvDiff = productPricer.presentValueCoupon(
          bond, ratesProvider, discountFactors, standardSettlementDate, tradeSettlementDate);
    }
    return presentValueFromProductPresentValue(
        trade, ratesProvider, discountingProvider, pvStandard.plus(pvDiff));
  }