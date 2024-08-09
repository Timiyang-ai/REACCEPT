public PointSensitivities presentValueSensitivityFromCleanPriceWithZSpread(
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
    RepoCurveDiscountFactors repoDf = DiscountingCapitalIndexedBondProductPricer.repoCurveDf(bond, discountingProvider);
    double df = repoDf.discountFactor(standardSettlementDate);
    PointSensitivityBuilder dfSensi = repoDf.zeroRatePointSensitivity(standardSettlementDate);
    PointSensitivityBuilder pvSensiStandard = forecastValueSensitivityStandardFromCleanPrice(bond, ratesProvider,
        standardSettlementDate, cleanRealPrice).multipliedBy(df).combinedWith(dfSensi.multipliedBy(
            forecastValueStandardFromCleanPrice(bond, ratesProvider, standardSettlementDate, cleanRealPrice)
                .getAmount()));
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueSensitivityFromProductPresentValueSensitivity(
          trade, ratesProvider, discountingProvider, pvSensiStandard).build();
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors issuerDf = DiscountingCapitalIndexedBondProductPricer.issuerCurveDf(bond, discountingProvider);
    PointSensitivityBuilder pvSensiDiff = PointSensitivityBuilder.none();
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvSensiDiff = pvSensiDiff.combinedWith(productPricer.presentValueSensitivityCouponWithZSpread(
          bond,
          ratesProvider,
          issuerDf,
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
          issuerDf,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear));
    }
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, discountingProvider, pvSensiStandard.combinedWith(pvSensiDiff)).build();
  }