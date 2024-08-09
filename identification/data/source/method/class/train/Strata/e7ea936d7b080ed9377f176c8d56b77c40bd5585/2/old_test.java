  @Test
  public void test_trade() {
    CdsIndexIsdaCreditCurveNode node =
        CdsIndexIsdaCreditCurveNode.ofQuotedSpread(TEMPLATE, QUOTE_ID, INDEX_ID, LEGAL_ENTITIES, 0.01);
    double rate = 0.0125;
    double quantity = -1234.56;
    MarketData marketData = ImmutableMarketData.builder(VAL_DATE).addValue(QUOTE_ID, rate).build();
    CdsIndexCalibrationTrade trade = node.trade(quantity, marketData, REF_DATA);
    CdsTrade cdsTrade = TEMPLATE.createTrade(INDEX_ID, VAL_DATE, SELL, -quantity, 0.01, REF_DATA);
    CdsIndex cdsIndex = CdsIndex.of(
        SELL, INDEX_ID, LEGAL_ENTITIES, TEMPLATE.getConvention().getCurrency(), -quantity, date(2015, 6, 20),
        date(2025, 6, 20), Frequency.P3M, TEMPLATE.getConvention().getSettlementDateOffset().getCalendar(), 0.01);
    CdsIndex cdsIndexMod = cdsIndex.toBuilder()
        .paymentSchedule(
            cdsIndex.getPaymentSchedule().toBuilder()
                .rollConvention(RollConventions.DAY_20)
                .startDateBusinessDayAdjustment(cdsIndex.getPaymentSchedule().getBusinessDayAdjustment())
                .build())
        .build();
    CdsIndexTrade expected = CdsIndexTrade.builder()
        .product(cdsIndexMod)
        .info(cdsTrade.getInfo())
        .build();
    assertThat(trade.getUnderlyingTrade()).isEqualTo(expected);
    assertThat(trade.getQuote()).isEqualTo(CdsQuote.of(CdsQuoteConvention.QUOTED_SPREAD, rate));

    CdsIndexIsdaCreditCurveNode node1 = CdsIndexIsdaCreditCurveNode.ofParSpread(TEMPLATE, QUOTE_ID, INDEX_ID, LEGAL_ENTITIES);
    CdsTrade cdsTrade1 = TEMPLATE.createTrade(INDEX_ID, VAL_DATE, SELL, -quantity, rate, REF_DATA);
    CdsIndexCalibrationTrade trade1 = node1.trade(quantity, marketData, REF_DATA);
    CdsIndex cdsIndex1 = CdsIndex.of(
        SELL, INDEX_ID, LEGAL_ENTITIES, TEMPLATE.getConvention().getCurrency(), -quantity, date(2015, 6, 20),
        date(2025, 6, 20), Frequency.P3M, TEMPLATE.getConvention().getSettlementDateOffset().getCalendar(), rate);
    CdsIndex cdsIndexMod1 = cdsIndex1.toBuilder()
        .paymentSchedule(
            cdsIndex.getPaymentSchedule().toBuilder()
                .rollConvention(RollConventions.DAY_20)
                .startDateBusinessDayAdjustment(cdsIndex1.getPaymentSchedule().getBusinessDayAdjustment())
                .build())
        .build();
    CdsIndexTrade expected1 = CdsIndexTrade.builder()
        .product(cdsIndexMod1)
        .info(cdsTrade1.getInfo())
        .build();
    assertThat(trade1.getUnderlyingTrade()).isEqualTo(expected1);
    assertThat(trade1.getQuote()).isEqualTo(CdsQuote.of(CdsQuoteConvention.PAR_SPREAD, rate));
  }