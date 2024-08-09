@Override
  public BlackFxOptionSmileVolatilities volatilities(
      ZonedDateTime valuationDateTime,
      DoubleArray parameters,
      ReferenceData refData) {

    ImmutableListMultimap.Builder<Tenor, Pair<FxOptionVolatilitiesNode, Double>> builder = ImmutableListMultimap.builder();
    for (Tenor tenor : nodesByTenor.keys()) {
      ImmutableList<Pair<FxOptionVolatilitiesNode, Double>> nodesAndQuotes = nodesByTenor.get(tenor).stream()
          .map(n -> Pair.of(n, parameters.get(nodes.indexOf(n))))
          .collect(toImmutableList());
      builder.putAll(tenor, nodesAndQuotes);
    }
    ImmutableListMultimap<Tenor, Pair<FxOptionVolatilitiesNode, Double>> nodesAndQuotesByTenor = builder.build();

    List<Tenor> tenors = new ArrayList<>(nodesByTenor.keys());
    Collections.sort(tenors);
    int nTenors = tenors.size();
    int nDeltas = deltas.size();

    double[] expiries = new double[nTenors];
    double[] atm = new double[nTenors];
    double[][] rr = new double[nTenors][nDeltas];
    double[][] str = new double[nTenors][nDeltas];
    for (int i = 0; i < nTenors; ++i) {
      parametersForPeriod(
          valuationDateTime, nodesAndQuotesByTenor.get(tenors.get(i)), i, expiries, atm, rr, str, refData);
    }
    InterpolatedStrikeSmileDeltaTermStructure smiles = InterpolatedStrikeSmileDeltaTermStructure.of(
        DoubleArray.copyOf(expiries), DoubleArray.copyOf(deltas.subList(0, nDeltas)),
        DoubleArray.copyOf(atm), DoubleMatrix.copyOf(rr), DoubleMatrix.copyOf(str), dayCount,
        timeInterpolator, timeExtrapolatorLeft, timeExtrapolatorRight,
        strikeInterpolator, strikeExtrapolatorLeft, strikeExtrapolatorRight);

    return BlackFxOptionSmileVolatilities.of(name, currencyPair, valuationDateTime, smiles);
  }