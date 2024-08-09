public CdsIndexCalibrationTrade trade(double quantity, MarketData marketData, ReferenceData refData) {
    BuySell buySell = quantity > 0 ? BuySell.BUY : BuySell.SELL;
    LocalDate valuationDate = marketData.getValuationDate();
    double quoteValue = marketData.getValue(observableId);
    CdsQuote quote = CdsQuote.of(quoteConvention, quoteValue);
    double notional = Math.abs(quantity);
    CdsTrade cdsTrade = null;
    if (quoteConvention.equals(CdsQuoteConvention.PAR_SPREAD)) {
      cdsTrade = template.createTrade(cdsIndexId, valuationDate, buySell, notional, quoteValue, refData);
    } else {
      double coupon = getFixedRate().getAsDouble(); // always success
      cdsTrade = template.createTrade(cdsIndexId, valuationDate, buySell, notional, coupon, refData);
    }
    Cds cdsProduct = cdsTrade.getProduct();
    CdsIndexTrade cdsIndex = CdsIndexTrade.builder()
        .info(cdsTrade.getInfo())
        .product(CdsIndex.builder()
            .buySell(cdsProduct.getBuySell())
            .currency(cdsProduct.getCurrency())
            .notional(cdsProduct.getNotional())
            .cdsIndexId(cdsIndexId)
            .referenceEntityIds(referenceEntityIds)
            .dayCount(cdsProduct.getDayCount())
            .accrualSchedule(cdsProduct.getAccrualSchedule())
            .fixedRate(cdsProduct.getFixedRate())
            .paymentOnDefault(cdsProduct.getPaymentOnDefault())
            .protectionStart(cdsProduct.getProtectionStart())
            .settlementDateOffset(cdsProduct.getSettlementDateOffset())
            .stepinDateOffset(cdsProduct.getSettlementDateOffset())
            .build())
        .build();
    return CdsIndexCalibrationTrade.of(cdsIndex, quote);
  }