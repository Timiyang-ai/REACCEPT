public CurrencyAmount presentValueFromCleanPriceWithZSpread(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double cleanPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    ResolvedFixedCouponBond product = trade.getProduct();
    LocalDate standardSettlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    LocalDate tradeSettlementDate = trade.getInfo().getSettlementDate().get();
    StandardId legalEntityId = product.getLegalEntityId();
    Currency currency = product.getCurrency();
    double df = provider.repoCurveDiscountFactors(
        product.getSecurityId(), legalEntityId, currency).discountFactor(standardSettlementDate);
    double pvStandard =
        (cleanPrice * product.getNotional() + productPricer.accruedInterest(product, standardSettlementDate)) * df;
    if (standardSettlementDate.isEqual(tradeSettlementDate)) {
      return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard));
    }
    // check coupon payment between two settlement dates
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(legalEntityId, currency);
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = productPricer.presentValueCouponWithZSpread(
          product,
          discountFactors,
          tradeSettlementDate,
          standardSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    } else {
      pvDiff = -productPricer.presentValueCouponWithZSpread(
          product,
          discountFactors,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear);
    }
    return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard + pvDiff));
  }