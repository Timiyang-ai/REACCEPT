public ColumnHeader toHeader() {
    if (measure.isCurrencyConvertible()) {
      ReportingCurrency reportingCurrency = getReportingCurrency();
      if (reportingCurrency.isSpecific()) {
        return ColumnHeader.of(name, measure, reportingCurrency.getCurrency());
      }
    }
    return ColumnHeader.of(name, measure);
  }