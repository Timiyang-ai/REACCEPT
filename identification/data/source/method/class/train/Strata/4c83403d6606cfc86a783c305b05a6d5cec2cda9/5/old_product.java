public PointSensitivities presentValueSensitivity(FxSingleProduct product, RatesProvider provider) {
    ExpandedFxSingle fx = product.expand();
    if (provider.getValuationDate().isAfter(fx.getPaymentDate())) {
      return PointSensitivities.empty();
    }
    PointSensitivityBuilder pvcs1 = paymentPricer.presentValueSensitivity(fx.getBaseCurrencyPayment(), provider);
    PointSensitivityBuilder pvcs2 = paymentPricer.presentValueSensitivity(fx.getCounterCurrencyPayment(), provider);
    return pvcs1.combinedWith(pvcs2).build();
  }