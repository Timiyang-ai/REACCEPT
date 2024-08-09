public CurrencyAmount presentValueFromCleanPrice(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double cleanPrice) {

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
      pvDiff = productPricer.presentValueCoupon(product, issuerDf, tradeSettlementDate, standardSettlementDate);
    } else {
      pvDiff = -productPricer.presentValueCoupon(product, issuerDf, standardSettlementDate, tradeSettlementDate);
    }
    return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard + pvDiff));
  }