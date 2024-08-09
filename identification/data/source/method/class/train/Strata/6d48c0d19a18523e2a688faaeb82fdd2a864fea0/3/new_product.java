public MultiCurrencyAmount currencyExposure(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionSmileVolatilities volatilityProvider) {

    validate(ratesProvider, volatilityProvider);
    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return MultiCurrencyAmount.empty();
    }
    ResolvedFxSingle underlyingFx = option.getUnderlying();
    Currency ccyCounter = option.getCounterCurrency();
    double df = ratesProvider.discountFactor(ccyCounter, underlyingFx.getPaymentDate());
    FxRate forward = fxPricer.forwardFxRate(underlyingFx, ratesProvider);
    CurrencyPair currencyPair = underlyingFx.getCurrencyPair();
    double spot = ratesProvider.fxRate(currencyPair);
    double forwardRate = forward.fxRate(currencyPair);
    double fwdRateSpotSensitivity = fxPricer.forwardFxRateSpotSensitivity(
        option.getPutCall().isCall() ? underlyingFx : underlyingFx.inverse(), ratesProvider);
    double strikeRate = option.getStrike();
    boolean isCall = option.getPutCall().isCall();
    SmileDeltaParameters smileAtTime = volatilityProvider.getSmile().smileForExpiry(timeToExpiry);
    double[] strikes = smileAtTime.strike(forwardRate).toArray();
    double[] vols = smileAtTime.getVolatility().toArray();
    double volAtm = vols[1];
    double[] x = vannaVolgaWeights(forwardRate, strikeRate, timeToExpiry, volAtm, strikes);
    double priceFwd = BlackFormulaRepository.price(forwardRate, strikeRate, timeToExpiry, volAtm, isCall);
    double deltaFwd = BlackFormulaRepository.delta(forwardRate, strikeRate, timeToExpiry, volAtm, isCall);
    for (int i = 0; i < 3; i += 2) {
      double priceFwdAtm = BlackFormulaRepository.price(forwardRate, strikes[i], timeToExpiry, volAtm, isCall);
      double priceFwdSmile = BlackFormulaRepository.price(forwardRate, strikes[i], timeToExpiry, vols[i], isCall);
      priceFwd += x[i] * (priceFwdSmile - priceFwdAtm);
      double deltaFwdAtm = BlackFormulaRepository.delta(forwardRate, strikes[i], timeToExpiry, volAtm, isCall);
      double deltaFwdSmile = BlackFormulaRepository.delta(forwardRate, strikes[i], timeToExpiry, vols[i], isCall);
      deltaFwd += x[i] * (deltaFwdSmile - deltaFwdAtm);
    }
    double price = df * priceFwd;
    double delta = df * deltaFwd * fwdRateSpotSensitivity;
    double signedNotional = signedNotional(option);
    CurrencyAmount domestic = CurrencyAmount.of(currencyPair.getCounter(), (price - delta * spot) * signedNotional);
    CurrencyAmount foreign = CurrencyAmount.of(currencyPair.getBase(), delta * signedNotional);
    return MultiCurrencyAmount.of(domestic, foreign);
  }