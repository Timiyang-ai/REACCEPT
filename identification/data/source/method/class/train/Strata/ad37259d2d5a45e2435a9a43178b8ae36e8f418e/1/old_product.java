public CurrencyAmount presentValue(ResolvedBillTrade trade, LegalEntityDiscountingProvider provider) {
    if (provider.getValuationDate().isAfter(trade.getProduct().getNotional().getDate())) {
      return CurrencyAmount.of(trade.getProduct().getCurrency(), 0.0d);
    }
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), provider)
        .multipliedBy(trade.getQuantity());
    if (trade.getSettlement().isPresent()) {
      DiscountFactors discountFactorsRepo = provider.repoCurveDiscountFactors(
          trade.getProduct().getSecurityId(), trade.getProduct().getLegalEntityId(), trade.getProduct().getCurrency())
          .getDiscountFactors();
      CurrencyAmount pvSettle = paymentPricer.presentValue(trade.getSettlement().get(), discountFactorsRepo);
      return pvProduct.plus(pvSettle);
    }
    return pvProduct;
  }