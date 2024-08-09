public CurrencyAmount presentValueFromCleanPrice(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    Security<CapitalIndexedBond> security = trade.getSecurity();
    CapitalIndexedBond product = security.getProduct();
    LocalDate standardSettlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate());
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
    ExpandedCapitalIndexedBond expanded = product.expand();
    boolean exCoupon = product.getExCouponPeriod().getDays() != 0;
    double pvDiff = 0d;
    if (standardSettlementDate.isAfter(tradeSettlementDate)) {
      pvDiff = -productPricer.presentValueCoupon(
          expanded, ratesProvider, discountFactors, tradeSettlementDate, standardSettlementDate, exCoupon);
    } else {
      pvDiff = productPricer.presentValueCoupon(
          expanded, ratesProvider, discountFactors, standardSettlementDate, tradeSettlementDate, exCoupon);
    }
    return pvStandard.plus(pvDiff * trade.getQuantity());
  }