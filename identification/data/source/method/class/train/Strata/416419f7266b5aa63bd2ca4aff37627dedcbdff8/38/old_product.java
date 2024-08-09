public CurrencyAmount currentCash(FixedCouponBondTrade trade, LocalDate valuationDate) {
    Payment upfront = trade.getPayment();
    Currency currency = upfront.getCurrency(); // assumes single currency is involved in trade
    CurrencyAmount currentCash = CurrencyAmount.zero(currency);
    if (upfront.getDate().equals(valuationDate)) {
      currentCash = currentCash.plus(CurrencyAmount.of(currency, upfront.getAmount()));
    }
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    FixedCouponBond product = trade.getProduct();
    if (!settlementDate.isAfter(valuationDate)) {
      ExpandedFixedCouponBond expanded = product.expand();
      double cashCoupon = product.getExCouponPeriod().getDays() != 0 ? 0d : currentCashCouponPayment(expanded, valuationDate);
      Payment payment = expanded.getNominalPayment();
      double cashNominal = payment.getDate().isEqual(valuationDate) ? payment.getAmount() : 0d;
      currentCash = currentCash.plus(CurrencyAmount.of(currency, (cashCoupon + cashNominal) * trade.getQuantity()));
    }
    return currentCash;
  }