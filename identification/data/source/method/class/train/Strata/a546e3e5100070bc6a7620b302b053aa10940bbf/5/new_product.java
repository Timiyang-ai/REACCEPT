private ResolvedSwap createUnderlyingSwap(LocalDate fixingDate, ReferenceData refData) {
    FixedIborSwapConvention conv = index.getTemplate().getConvention();
    LocalDate effectiveDate = conv.calculateSpotDateFromTradeDate(fixingDate, refData);
    LocalDate maturityDate = effectiveDate.plus(index.getTemplate().getTenor());
    Swap swap = conv.toTrade(fixingDate, effectiveDate, maturityDate, BuySell.BUY, 1d, 1d).getProduct();
    return swap.resolve(refData);
  }