public double price(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilitySmileFxProvider volatilityProvider) {

    validate(ratesProvider, volatilityProvider);
    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    ResolvedFxSingle underlyingFx = option.getUnderlying();
    Currency ccyCounter = option.getCounterCurrency();
    double df = ratesProvider.discountFactor(ccyCounter, underlyingFx.getPaymentDate());
    FxRate forward = fxPricer.forwardFxRate(underlyingFx, ratesProvider);
    CurrencyPair currencyPair = underlyingFx.getCurrencyPair();
    double forwardRate = forward.fxRate(currencyPair);
    double strikeRate = option.getStrike();
    boolean isCall = option.getPutCall().isCall();
    SmileDeltaParameters smileAtTime = volatilityProvider.getSmile().smileForTime(timeToExpiry);
    double[] strikes = smileAtTime.getStrike(forwardRate).toArray();
    double[] vols = smileAtTime.getVolatility().toArray();
    double volAtm = vols[1];
    double[] x = vannaVolgaWeights(forwardRate, strikeRate, timeToExpiry, volAtm, strikes);
    double priceFwd = BlackFormulaRepository.price(forwardRate, strikeRate, timeToExpiry, volAtm, isCall);
    for (int i = 0; i < 3; i += 2) {
      double priceFwdAtm = BlackFormulaRepository.price(forwardRate, strikes[i], timeToExpiry, volAtm, isCall);
      double priceFwdSmile = BlackFormulaRepository.price(forwardRate, strikes[i], timeToExpiry, vols[i], isCall);
      priceFwd += x[i] * (priceFwdSmile - priceFwdAtm);
    }
    return df * priceFwd;
  }