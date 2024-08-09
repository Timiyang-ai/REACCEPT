public CurrencyAmount presentValueFromCleanPrice(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    validate(ratesProvider, discountingProvider);
    LocalDate valuationDate = ratesProvider.getValuationDate();
    ResolvedCapitalIndexedBond bond = trade.getProduct();
    LocalDate standardSettlementDate = bond.calculateSettlementDateFromValuation(valuationDate, refData);
    LocalDate tradeSettlementDate = settlementDate(trade, valuationDate);
    RepoCurveDiscountFactors repoDf = DiscountingCapitalIndexedBondProductPricer.repoCurveDf(bond, discountingProvider);
    double df = repoDf.discountFactor(standardSettlementDate);
    CurrencyAmount pvStandard = forecastValueStandardFromCleanPrice(
        bond, ratesProvider, standardSettlementDate, cleanRealPrice).multipliedBy(df);
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueFromProductPresentValue(trade, ratesProvider, discountingProvider, pvStandard);
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors issuerDf = DiscountingCapitalIndexedBondProductPricer.issuerCurveDf(bond, discountingProvider);
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = -productPricer.presentValueCoupon(bond, ratesProvider, issuerDf, tradeSettlementDate, standardSettlementDate);
    } else {
      pvDiff = productPricer.presentValueCoupon(bond, ratesProvider, issuerDf, standardSettlementDate, tradeSettlementDate);
    }
    return presentValueFromProductPresentValue(
        trade, ratesProvider, discountingProvider, pvStandard.plus(pvDiff));
  }