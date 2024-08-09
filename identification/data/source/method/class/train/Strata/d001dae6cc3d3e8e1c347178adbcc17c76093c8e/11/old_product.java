public CurrencyAmount currentCash(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LocalDate settlementDate) {

    LocalDate valuationDate = ratesProvider.getValuationDate();
    Currency currency = product.getCurrency();
    CurrencyAmount currentCash = CurrencyAmount.zero(currency);
    if (settlementDate.isBefore(valuationDate)) {
      ExpandedCapitalIndexedBond expanded = product.expand();
      double cashCoupon = product.getExCouponPeriod().getDays() != 0 ? 0d :
          currentCashPayment(expanded, ratesProvider, valuationDate);
      CapitalIndexedBondPaymentPeriod nominal = expanded.getNominalPayment();
      double cashNominal = nominal.getPaymentDate().isEqual(valuationDate) ?
          periodPricer.forecastValue(nominal, ratesProvider) : 0d;
      currentCash = currentCash.plus(CurrencyAmount.of(currency, cashCoupon + cashNominal));
    }
    return currentCash;
  }