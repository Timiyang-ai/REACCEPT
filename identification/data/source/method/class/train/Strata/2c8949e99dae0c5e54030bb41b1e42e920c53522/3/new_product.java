public PointSensitivityBuilder presentValueSensitivityRatesStickyModel(
      ResolvedIborCapFloor capFloor,
      RatesProvider ratesProvider,
      SabrIborCapletFloorletVolatilities volatilities) {

    PointSensitivityBuilder pvSensiCapFloorLeg =
        capFloorLegPricer.presentValueSensitivityRatesStickyModel(capFloor.getCapFloorLeg(), ratesProvider, volatilities);
    if (!capFloor.getPayLeg().isPresent()) {
      return pvSensiCapFloorLeg;
    }
    PointSensitivityBuilder pvSensiPayLeg = getPayLegPricer().presentValueSensitivity(capFloor.getPayLeg().get(), ratesProvider);
    return pvSensiCapFloorLeg.combinedWith(pvSensiPayLeg);
  }