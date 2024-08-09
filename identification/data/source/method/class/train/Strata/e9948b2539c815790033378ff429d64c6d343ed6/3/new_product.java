public ExplainMap explainPresentValue(
      ResolvedCmsLeg cmsLeg,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities volatilities) {

    ExplainMapBuilder builder = ExplainMap.builder();
    builder.put(ExplainKey.ENTRY_TYPE, "CmsLeg");
    builder.put(ExplainKey.PAY_RECEIVE, cmsLeg.getPayReceive());
    builder.put(ExplainKey.PAYMENT_CURRENCY, cmsLeg.getCurrency());
    builder.put(ExplainKey.START_DATE, cmsLeg.getStartDate());
    builder.put(ExplainKey.END_DATE, cmsLeg.getEndDate());
    builder.put(ExplainKey.INDEX, cmsLeg.getIndex());
    for (CmsPeriod period : cmsLeg.getCmsPeriods()) {
      builder.addListEntry(
          ExplainKey.PAYMENT_PERIODS, child -> cmsPeriodPricer.explainPresentValue(period, provider, volatilities, child));
    }
    builder.put(ExplainKey.PRESENT_VALUE, presentValue(cmsLeg, provider, volatilities));
    return builder.build();
  }