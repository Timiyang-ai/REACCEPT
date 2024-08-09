public CurrencyAmount currentCash(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LocalDate settlementDate) {

    LocalDate valuationDate = ratesProvider.getValuationDate();
    Currency currency = bond.getCurrency();
    CurrencyAmount currentCash = CurrencyAmount.zero(currency);
    if (settlementDate.isBefore(valuationDate)) {
      double cashCoupon = bond.hasExCouponPeriod() ? 0d :
          currentCashPayment(bond, ratesProvider, valuationDate);
      CapitalIndexedBondPaymentPeriod nominal = bond.getNominalPayment();
      double cashNominal = nominal.getPaymentDate().isEqual(valuationDate) ?
          periodPricer.forecastValue(nominal, ratesProvider) : 0d;
      currentCash = currentCash.plus(CurrencyAmount.of(currency, cashCoupon + cashNominal));
    }
    return currentCash;
  }