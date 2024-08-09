public PointSensitivityBuilder forecastValueSensitivity(
      CapitalIndexedBondPaymentPeriod period,
      RatesProvider ratesProvider) {

    if (period.getPaymentDate().isBefore(ratesProvider.getValuationDate())) {
      return PointSensitivityBuilder.none();
    }
    PointSensitivityBuilder rateSensi = rateComputationFn.rateSensitivity(
        period.getRateComputation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    return rateSensi.multipliedBy(period.getNotional() * period.getRealCoupon());
  }