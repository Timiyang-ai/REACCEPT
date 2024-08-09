public CurrencyAmount presentValueFromCleanPriceWithZSpread(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    Security<CapitalIndexedBond> security = trade.getSecurity();
    CapitalIndexedBond product = security.getProduct();
    LocalDate standardSettlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    LocalDate tradeSettlementDate = trade.getTradeInfo().getSettlementDate().get();
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    Currency currency = product.getCurrency();
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
    ResolvedCapitalIndexedBond expanded = product.resolve(REF_DATA);
    boolean exCoupon = product.getExCouponPeriod().getDays() != 0;
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = -productPricer.presentValueCouponWithZSpread(
          expanded,
          ratesProvider,
          discountFactors,
          tradeSettlementDate,
          standardSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear,
          exCoupon);
    } else {
      pvDiff = productPricer.presentValueCouponWithZSpread(
          expanded,
          ratesProvider,
          discountFactors,
          standardSettlementDate,
          tradeSettlementDate,
          zSpread,
          compoundedRateType,
          periodsPerYear,
          exCoupon);
    }
    return pvStandard.plus(pvDiff * trade.getQuantity());
  }