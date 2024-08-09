public PointSensitivities presentValueSensitivity(ResolvedDsfTrade trade, RatesProvider ratesProvider) {
    ResolvedDsf product = trade.getProduct();
    PointSensitivities priceSensi = productPricer.priceSensitivity(product, ratesProvider);
    PointSensitivities marginIndexSensi = productPricer.marginIndexSensitivity(product, priceSensi);
    return marginIndexSensi.multipliedBy(trade.getQuantity());
  }