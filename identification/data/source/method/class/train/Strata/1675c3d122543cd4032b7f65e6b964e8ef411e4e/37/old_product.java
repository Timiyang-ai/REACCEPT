public CurrencyAmount presentValueFromCleanPriceWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate valuationDate = ratesProvider.getValuationDate();
    ResolvedCapitalIndexedBond bond = trade.getProduct();
    LocalDate standardSettlementDate = bond.calculateSettlementDateFromValuation(valuationDate, refData);
    LocalDate tradeSettlementDate = settlementDate(trade, valuationDate);
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
      pvDiff = -productPricer.presentValueCouponWithZSpread(
          bond,
          ratesProvider,
          discountFactors,
          tradeSettlementDate,
          standardSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    } else {
      pvDiff = productPricer.presentValueCouponWithZSpread(
          bond,
          ratesProvider,
          discountFactors,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    }
    return presentValueFromProductPresentValue(
        trade, ratesProvider, discountingProvider, pvStandard.plus(pvDiff));
  }