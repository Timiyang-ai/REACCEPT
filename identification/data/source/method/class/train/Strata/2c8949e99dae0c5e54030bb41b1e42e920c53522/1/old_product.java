public PointSensitivities presentValueSensitivityRatesStickyModel(
      ResolvedIborCapFloorTrade trade,
      RatesProvider ratesProvider,
      SabrIborCapletFloorletVolatilities volatilities) {

    PointSensitivityBuilder pvSensiProduct =
        productPricer.presentValueSensitivityRatesStickyModel(trade.getProduct(), ratesProvider, volatilities);
    if (!trade.getPremium().isPresent()) {
      return pvSensiProduct.build();
    }
    PointSensitivityBuilder pvSensiPremium =
        paymentPricer.presentValueSensitivity(trade.getPremium().get(), ratesProvider);
    return pvSensiProduct.combinedWith(pvSensiPremium).build();
  }