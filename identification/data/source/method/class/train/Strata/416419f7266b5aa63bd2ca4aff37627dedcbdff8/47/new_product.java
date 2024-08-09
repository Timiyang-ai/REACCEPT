public CurrencyAmount currentCash(ResolvedFixedCouponBondTrade trade, LocalDate valuationDate) {
    Payment upfrontPayment = upfrontPayment(trade);
    Currency currency = upfrontPayment.getCurrency(); // assumes single currency is involved in trade
    CurrencyAmount currentCash = CurrencyAmount.zero(currency);
    if (upfrontPayment.getDate().equals(valuationDate)) {
      currentCash = currentCash.plus(upfrontPayment.getValue());
    }
    if (trade.getSettlement().isPresent()) {
      LocalDate settlementDate = trade.getSettlement().get().getSettlementDate();
      ResolvedFixedCouponBond product = trade.getProduct();
      if (!settlementDate.isAfter(valuationDate)) {
        double cashCoupon = product.hasExCouponPeriod() ? 0d : currentCashCouponPayment(product, valuationDate);
        Payment payment = product.getNominalPayment();
        double cashNominal = payment.getDate().isEqual(valuationDate) ? payment.getAmount() : 0d;
        currentCash = currentCash.plus(CurrencyAmount.of(currency, (cashCoupon + cashNominal) * trade.getQuantity()));
      }
    }
    return currentCash;
  }