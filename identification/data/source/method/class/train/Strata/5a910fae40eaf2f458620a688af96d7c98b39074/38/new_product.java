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
    StandardId legalEntityId = bond.getLegalEntityId();
    Currency currency = bond.getCurrency();
    RepoCurveDiscountFactors repoDiscountFactors =
        discountingProvider.repoCurveDiscountFactors(bond.getSecurityId(), legalEntityId, currency);
    double df = repoDiscountFactors.discountFactor(standardSettlementDate);
    PointSensitivityBuilder dfSensi = repoDiscountFactors.zeroRatePointSensitivity(standardSettlementDate);
    PointSensitivityBuilder pvSensiStandard = forecastValueSensitivityStandardFromCleanPrice(bond, ratesProvider,
        standardSettlementDate, cleanRealPrice).multipliedBy(df).combinedWith(dfSensi.multipliedBy(
            forecastValueStandardFromCleanPrice(bond, ratesProvider, standardSettlementDate, cleanRealPrice)
                .getAmount()));
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueSensitivityFromProductPresentValueSensitivity(
          trade, ratesProvider, discountingProvider, pvSensiStandard).build();
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors issuerDiscountFactors =
        discountingProvider.issuerCurveDiscountFactors(legalEntityId, currency);
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
        trade, ratesProvider, discountingProvider, pvSensiStandard.combinedWith(pvSensiDiff)).build();
  }