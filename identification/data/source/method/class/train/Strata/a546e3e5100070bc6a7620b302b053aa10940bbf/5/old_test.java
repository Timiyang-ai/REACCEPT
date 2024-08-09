  private ResolvedSwap createUnderlyingSwap(LocalDate fixingDate) {
    FixedIborSwapConvention conv = INDEX.getTemplate().getConvention();
    LocalDate effectiveDate = conv.calculateSpotDateFromTradeDate(fixingDate, REF_DATA);
    LocalDate maturityDate = effectiveDate.plus(INDEX.getTemplate().getTenor());
    Swap swap = conv.toTrade(fixingDate, effectiveDate, maturityDate, BuySell.BUY, 1d, 1d).getProduct();
    return swap.resolve(REF_DATA);
  }