public CurrencyAmount presentValueFromCleanPriceWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    ResolvedCapitalIndexedBond bond = trade.getProduct();
    LocalDate standardSettlementDate = bond.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), refData);
    LocalDate tradeSettlementDate = trade.getTradeInfo().getSettlementDate().get();
    StandardId securityId = trade.getSecurityStandardId();
    StandardId legalEntityId = bond.getLegalEntityId();
    Currency currency = bond.getCurrency();
    double df = issuerDiscountFactorsProvider
        .repoCurveDiscountFactors(securityId, legalEntityId, currency).discountFactor(standardSettlementDate);
    CurrencyAmount pvStandard =
        netAmountStandard(trade, ratesProvider, standardSettlementDate, cleanRealPrice).multipliedBy(df);
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return pvStandard;
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors discountFactors =
        issuerDiscountFactorsProvider.issuerCurveDiscountFactors(legalEntityId, currency);
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
    return pvStandard.plus(pvDiff * trade.getQuantity());
  }