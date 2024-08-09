CurrencyAmount presentValue(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, discountingProvider);
    double pvNominal = periodPricer.presentValue(bond.getNominalPayment(), ratesProvider, issuerDf);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValue(period, ratesProvider, issuerDf);
      }
    }
    return CurrencyAmount.of(bond.getCurrency(), pvCoupon + pvNominal);
  }