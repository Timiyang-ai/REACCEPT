public double price(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    validate(option, ratesProvider, volatilities);
    SimpleConstantContinuousBarrier barrier = (SimpleConstantContinuousBarrier) option.getBarrier();
    ResolvedFxVanillaOption underlyingOption = option.getUnderlyingOption();
    if (volatilities.relativeTime(underlyingOption.getExpiry()) < 0d) {
      return 0d;
    }
    ResolvedFxSingle underlyingFx = underlyingOption.getUnderlying();
    Currency ccyBase = underlyingFx.getBaseCurrencyPayment().getCurrency();
    Currency ccyCounter = underlyingFx.getCounterCurrencyPayment().getCurrency();
    CurrencyPair currencyPair = underlyingFx.getCurrencyPair();
    DiscountFactors baseDiscountFactors = ratesProvider.discountFactors(ccyBase);
    DiscountFactors counterDiscountFactors = ratesProvider.discountFactors(ccyCounter);

    double rateBase = baseDiscountFactors.zeroRate(underlyingFx.getPaymentDate());
    double rateCounter = counterDiscountFactors.zeroRate(underlyingFx.getPaymentDate());
    double costOfCarry = rateCounter - rateBase;
    double dfBase = baseDiscountFactors.discountFactor(underlyingFx.getPaymentDate());
    double dfCounter = counterDiscountFactors.discountFactor(underlyingFx.getPaymentDate());
    double todayFx = ratesProvider.fxRate(currencyPair);
    double strike = underlyingOption.getStrike();
    double forward = todayFx * dfBase / dfCounter;
    double volatility = volatilities.volatility(currencyPair, underlyingOption.getExpiry(), strike, forward);
    double timeToExpiry = volatilities.relativeTime(underlyingOption.getExpiry());
    double price = BARRIER_PRICER.price(
        todayFx, strike, timeToExpiry, costOfCarry, rateCounter, volatility, underlyingOption.getPutCall().isCall(), barrier);
    if (option.getRebate().isPresent()) {
      CurrencyAmount rebate = option.getRebate().get();
      double priceRebate = rebate.getCurrency().equals(ccyCounter) ?
          CASH_REBATE_PRICER.price(todayFx, timeToExpiry, costOfCarry, rateCounter, volatility, barrier.inverseKnockType()) :
          ASSET_REBATE_PRICER.price(todayFx, timeToExpiry, costOfCarry, rateCounter, volatility, barrier.inverseKnockType());
      price += priceRebate * rebate.getAmount() / Math.abs(underlyingFx.getBaseCurrencyPayment().getAmount());
    }
    return price;
  }