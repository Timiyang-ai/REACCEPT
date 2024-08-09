public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider) {

    PointSensitivityBuilder pvSensiCms =
        productPricer.presentValueSensitivity(trade.getProduct(), ratesProvider);
    if (!trade.getPremium().isPresent()) {
      return pvSensiCms;
    }
    PointSensitivityBuilder pvSensiPremium = paymentPricer.presentValueSensitivity(trade.getPremium().get(), ratesProvider);
    return pvSensiCms.combinedWith(pvSensiPremium);
  }