public CurrencyAmount currentCash(ResolvedFixedCouponBondTrade trade, LocalDate valuationDate) {
    Payment upfront = trade.getPayment();
    Currency currency = upfront.getCurrency(); // assumes single currency is involved in trade
    CurrencyAmount currentCash = CurrencyAmount.zero(currency);
    if (upfront.getDate().equals(valuationDate)) {
      currentCash = currentCash.plus(CurrencyAmount.of(currency, upfront.getAmount()));
    }
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    ResolvedFixedCouponBond product = trade.getProduct();
    if (!settlementDate.isAfter(valuationDate)) {
      double cashCoupon = product.getExCouponPeriod().getDays() != 0 ? 0d : currentCashCouponPayment(product, valuationDate);
      Payment payment = product.getNominalPayment();
      double cashNominal = payment.getDate().isEqual(valuationDate) ? payment.getAmount() : 0d;
      currentCash = currentCash.plus(CurrencyAmount.of(currency, (cashCoupon + cashNominal) * trade.getQuantity()));
    }
    return currentCash;
  }