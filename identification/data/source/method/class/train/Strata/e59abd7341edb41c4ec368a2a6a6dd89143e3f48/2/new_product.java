@Override
  public BlackFxOptionSurfaceVolatilities volatilities(
      ZonedDateTime valuationDateTime,
      DoubleArray parameters,
      ReferenceData refData) {

    ArgChecker.isTrue(parameters.size() == getParameterCount(),
        Messages.format("size of parameters must be {}, but found {}", getParameterCount(), parameters.size()));
    DoubleArray strikes = DoubleArray.ofUnsafe(
        nodes.stream().mapToDouble(n -> n.getStrike().getValue()).toArray());
    DoubleArray expiries = DoubleArray.ofUnsafe(
        nodes.stream().mapToDouble(n -> n.timeToExpiry(valuationDateTime, dayCount, refData)).toArray());
    SurfaceMetadata metadata = Surfaces.blackVolatilityByExpiryStrike(SurfaceName.of(name.getName()), dayCount)
        .withParameterMetadata(parameterMetadata(expiries));
    
    SurfaceInterpolator interp = GridSurfaceInterpolator.of(
        timeInterpolator, timeExtrapolatorLeft, timeExtrapolatorRight,
        strikeInterpolator, strikeExtrapolatorLeft, strikeExtrapolatorRight);
    InterpolatedNodalSurface surface = InterpolatedNodalSurface.ofUnsorted(metadata, expiries, strikes, parameters, interp);
    return BlackFxOptionSurfaceVolatilities.of(name, currencyPair, valuationDateTime, surface);
  }