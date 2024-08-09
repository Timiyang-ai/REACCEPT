CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    IssuerCurveDiscountFactors issuerDiscountFactors = issuerDiscountFactorsProvider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    double pvNominal = periodPricer.presentValueWithZSpread(
        bond.getNominalPayment(), ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValueWithZSpread(
            period, ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
      }
    }
    return CurrencyAmount.of(bond.getCurrency(), pvCoupon + pvNominal);
  }