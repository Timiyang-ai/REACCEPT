public CurrencyAmount presentValueFromCleanPriceWithZSpread(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double cleanPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    ResolvedFixedCouponBond product = trade.getProduct();
    LocalDate standardSettlementDate = standardSettlementDate(product, provider, refData);
    LocalDate tradeSettlementDate = settlementDate(trade, provider.getValuationDate());
    Currency currency = product.getCurrency();
    RepoCurveDiscountFactors repoDf = DiscountingFixedCouponBondProductPricer.repoCurveDf(product, provider);
    double df = repoDf.discountFactor(standardSettlementDate);
    double pvStandard =
        (cleanPrice * product.getNotional() + productPricer.accruedInterest(product, standardSettlementDate)) * df;
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard));
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors issuerDf = DiscountingFixedCouponBondProductPricer.issuerCurveDf(product, provider);
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = productPricer.presentValueCouponWithZSpread(
          product,
          issuerDf,
          tradeSettlementDate,
          standardSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    } else {
      pvDiff = -productPricer.presentValueCouponWithZSpread(
          product,
          issuerDf,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    }
    return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard + pvDiff));
  }