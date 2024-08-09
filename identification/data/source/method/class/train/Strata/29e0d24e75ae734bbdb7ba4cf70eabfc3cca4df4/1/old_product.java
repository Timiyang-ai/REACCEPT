@Override
  public BlackFxOptionSmileVolatilities volatilities(
      ZonedDateTime valuationDateTime,
      DoubleArray parameters,
      ReferenceData refData) {

    Map<FxOptionVolatilitiesNode, Double> totalMap = IntStream.range(0, nodes.size())
        .boxed()
        .collect(toImmutableMap(nodes::get, parameters::get));
    List<Tenor> tenors = nodes.stream()
        .map(FxOptionVolatilitiesNode::getTenor)
        .distinct()
        .sorted()
        .collect(toImmutableList());
    int nTenors = tenors.size();
    List<Double> deltas = nodes.stream()
        .map(FxOptionVolatilitiesNode::getStrike)
        .distinct()
        .map(Strike::getValue)
        .sorted()
        .collect(toImmutableList());
    int nDeltas = deltas.size() - 1;
    ArgChecker.isTrue(deltas.get(nDeltas) == 0.5, "0 < deltas <= 0.5, and ATM nodes must be involved");

    double[] expiries = new double[nTenors];
    double[] atm = new double[nTenors];
    double[][] rr = new double[nTenors][nDeltas];
    double[][] str = new double[nTenors][nDeltas];
    for (int i = 0; i < nTenors; ++i) {
      parametersForPeriod(valuationDateTime.toLocalDate(), totalMap, deltas, tenors, i, expiries, atm, rr, str, refData);
    }
    InterpolatedStrikeSmileDeltaTermStructure smiles = InterpolatedStrikeSmileDeltaTermStructure.of(
        DoubleArray.copyOf(expiries), DoubleArray.copyOf(deltas.subList(0, nDeltas)),
        DoubleArray.copyOf(atm), DoubleMatrix.copyOf(rr), DoubleMatrix.copyOf(str), dayCount,
        timeInterpolator, timeExtrapolatorLeft, timeExtrapolatorRight,
        strikeInterpolator, strikeExtrapolatorLeft, strikeExtrapolatorRight);

    return BlackFxOptionSmileVolatilities.of(name, currencyPair, valuationDateTime, smiles);
  }