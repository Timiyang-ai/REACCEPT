public Payment upfrontPayment(ResolvedFixedCouponBondTrade trade) {
    ResolvedFixedCouponBond product = trade.getProduct();
    Currency currency = product.getCurrency();
    if (!trade.getSettlement().isPresent()) {
      return Payment.of(CurrencyAmount.zero(currency), product.getStartDate());  // date doesn't matter as it is zero
    }
    // payment is based on the dirty price
    ResolvedFixedCouponBondSettlement settlement = trade.getSettlement().get();
    LocalDate settlementDate = settlement.getSettlementDate();
    double cleanPrice = settlement.getPrice();
    double dirtyPrice = productPricer.dirtyPriceFromCleanPrice(product, settlementDate, cleanPrice);
    // calculate payment
    double quantity = trade.getQuantity();
    double notional = product.getNotional();
    return Payment.of(CurrencyAmount.of(currency, -quantity * notional * dirtyPrice), settlementDate);
  }