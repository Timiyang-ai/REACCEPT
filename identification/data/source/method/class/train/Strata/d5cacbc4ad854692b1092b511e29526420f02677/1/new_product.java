public PointSensitivityBuilder presentValueSensitivityModelParamsVolatility(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilitySmileFxProvider volatilityProvider) {

    validate(ratesProvider, volatilityProvider);
    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return PointSensitivityBuilder.none();
    }
    ResolvedFxSingle underlyingFx = option.getUnderlying();
    Currency ccyCounter = option.getCounterCurrency();
    double df = ratesProvider.discountFactor(ccyCounter, underlyingFx.getPaymentDate());
    FxRate forward = fxPricer.forwardFxRate(underlyingFx, ratesProvider);
    CurrencyPair currencyPair = underlyingFx.getCurrencyPair();
    double forwardRate = forward.fxRate(currencyPair);
    double strikeRate = option.getStrike();
    SmileDeltaParameters smileAtTime = volatilityProvider.getSmile().smileForTime(timeToExpiry);
    double[] strikes = smileAtTime.getStrike(forwardRate).toArray();
    double[] vols = smileAtTime.getVolatility().toArray();
    double volAtm = vols[1];
    double[] x = vannaVolgaWeights(forwardRate, strikeRate, timeToExpiry, volAtm, strikes);
    double vegaAtm = BlackFormulaRepository.vega(forwardRate, strikeRate, timeToExpiry, volAtm);
    double signedNotional = signedNotional(option);
    PointSensitivityBuilder sensiSmile = PointSensitivityBuilder.none();
    for (int i = 0; i < 3; i += 2) {
      double vegaFwdAtm = BlackFormulaRepository.vega(forwardRate, strikes[i], timeToExpiry, volAtm);
      vegaAtm -= x[i] * vegaFwdAtm;
      double vegaFwdSmile = BlackFormulaRepository.vega(forwardRate, strikes[i], timeToExpiry, vols[i]);
      sensiSmile = sensiSmile.combinedWith(
          FxOptionSensitivity.of(
              currencyPair,
              option.getExpiry(),
              strikes[i],
              forwardRate,
              ccyCounter,
              df * signedNotional * x[i] * vegaFwdSmile));
    }
    FxOptionSensitivity sensiAtm = FxOptionSensitivity.of(
        currencyPair, option.getExpiry(), strikes[1], forwardRate, ccyCounter, df * signedNotional * vegaAtm);
    return sensiAtm.combinedWith(sensiSmile);
  }