CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, discountingProvider);
    double pvNominal = periodPricer.presentValueWithZSpread(
        bond.getNominalPayment(), ratesProvider, issuerDf, zSpread, compoundedRateType, periodsPerYear);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValueWithZSpread(
            period, ratesProvider, issuerDf, zSpread, compoundedRateType, periodsPerYear);
      }
    }
    return CurrencyAmount.of(bond.getCurrency(), pvCoupon + pvNominal);
  }