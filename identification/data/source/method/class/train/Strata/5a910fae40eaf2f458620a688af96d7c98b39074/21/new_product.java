public Payment upfrontPayment(ResolvedFixedCouponBondTrade trade) {
    ResolvedFixedCouponBond product = trade.getProduct();
    // payment is based on the dirty price
    LocalDate settlementDate = trade.getSettlementDate();
    double cleanPrice = trade.getPrice();
    double dirtyPrice = productPricer.dirtyPriceFromCleanPrice(product, settlementDate, cleanPrice);
    // calculate payment
    Currency currency = product.getCurrency();
    double quantity = trade.getQuantity();
    double notional = product.getNotional();
    return Payment.of(CurrencyAmount.of(currency, -quantity * notional * dirtyPrice), settlementDate);
  }