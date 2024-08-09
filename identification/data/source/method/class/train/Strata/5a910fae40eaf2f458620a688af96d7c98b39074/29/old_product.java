public PointSensitivityBuilder presentValueSensitivityFromCleanPriceWithZSpread(
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
    LocalDate standardSettlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    LocalDate tradeSettlementDate = trade.getTradeInfo().getSettlementDate().get();
    StandardId legalEntityId = bond.getLegalEntityId();
    Currency currency = bond.getCurrency();
    RepoCurveDiscountFactors repoDiscountFactors =
        issuerDiscountFactorsProvider.repoCurveDiscountFactors(bond.getSecurityId(), legalEntityId, currency);
    double df = repoDiscountFactors.discountFactor(standardSettlementDate);
    PointSensitivityBuilder dfSensi = repoDiscountFactors.zeroRatePointSensitivity(standardSettlementDate);
    PointSensitivityBuilder pvSensiStandard = forecastValueSensitivityStandardFromCleanPrice(bond, ratesProvider,
        standardSettlementDate, cleanRealPrice).multipliedBy(df).combinedWith(dfSensi.multipliedBy(
        forecastValueStandardFromCleanPrice(bond, ratesProvider, standardSettlementDate, cleanRealPrice)
                .getAmount()));
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueSensitivityFromProductPresentValueSensitivity(
          trade, ratesProvider, issuerDiscountFactorsProvider, pvSensiStandard);
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors issuerDiscountFactors =
        issuerDiscountFactorsProvider.issuerCurveDiscountFactors(legalEntityId, currency);
    PointSensitivityBuilder pvSensiDiff = PointSensitivityBuilder.none();
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvSensiDiff = pvSensiDiff.combinedWith(productPricer.presentValueSensitivityCouponWithZSpread(
          bond,
          ratesProvider,
          issuerDiscountFactors,
          tradeSettlementDate,
          standardSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear)
          .multipliedBy(-1d));
    } else {
      pvSensiDiff = pvSensiDiff.combinedWith(productPricer.presentValueSensitivityCouponWithZSpread(
          bond,
          ratesProvider,
          issuerDiscountFactors,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear));
    }
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, issuerDiscountFactorsProvider, pvSensiStandard.combinedWith(pvSensiDiff));
  }