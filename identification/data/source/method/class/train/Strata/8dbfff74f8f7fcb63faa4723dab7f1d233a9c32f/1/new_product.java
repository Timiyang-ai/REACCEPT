public ColumnHeader toHeader() {
    if (measure.isCurrencyConvertible()) {
      ReportingCurrency reportingCurrency = getReportingCurrency().orElse(ReportingCurrency.NATURAL);
      if (reportingCurrency.isSpecific()) {
        return ColumnHeader.of(name, measure, reportingCurrency.getCurrency());
      }
    }
    return ColumnHeader.of(name, measure);
  }