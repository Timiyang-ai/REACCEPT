public CurrencyAmount presentValueFromCleanPrice(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double cleanPrice) {

    ResolvedFixedCouponBond product = trade.getProduct();
    LocalDate standardSettlementDate = standardSettlementDate(product, provider, refData);
    LocalDate tradeSettlementDate = settlementDate(trade, provider.getValuationDate());
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
      pvDiff = productPricer.presentValueCoupon(product, discountFactors, tradeSettlementDate, standardSettlementDate);
    } else {
      pvDiff = -productPricer.presentValueCoupon(product, discountFactors, standardSettlementDate, tradeSettlementDate);
    }
    return presentValueFromProductPresentValue(trade, provider, CurrencyAmount.of(currency, pvStandard + pvDiff));
  }