public PointSensitivities presentValueSensitivity(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider) {

    PointSensitivityBuilder pvSensiCms =
        productPricer.presentValueSensitivity(trade.getProduct(), ratesProvider);
    if (!trade.getPremium().isPresent()) {
      return pvSensiCms.build();
    }
    PointSensitivityBuilder pvSensiPremium = paymentPricer.presentValueSensitivity(trade.getPremium().get(), ratesProvider);
    return pvSensiCms.combinedWith(pvSensiPremium).build();
  }