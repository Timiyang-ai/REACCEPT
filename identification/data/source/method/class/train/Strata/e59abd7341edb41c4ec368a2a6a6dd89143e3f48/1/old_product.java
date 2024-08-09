@Override
  public BlackFxOptionSurfaceVolatilities volatilities(
      ZonedDateTime valuationDateTime,
      DoubleArray parameters,
      ReferenceData refData) {

    DoubleArray strikes = DoubleArray.ofUnsafe(
        nodes.stream().mapToDouble(n -> n.getStrike().getValue()).toArray()); // TODO must be simple strike
    DoubleArray expiries = DoubleArray.ofUnsafe(
        nodes.stream().mapToDouble(n -> n.timeToExpiry(valuationDateTime.toLocalDate(), dayCount, refData)).toArray());
    SurfaceMetadata metadata = Surfaces.blackVolatilityByExpiryStrike(SurfaceName.of(name.getName()), dayCount)
        .withParameterMetadata(parameterMetadata(expiries));
    
    SurfaceInterpolator interp = GridSurfaceInterpolator.of(
        timeInterpolator, timeExtrapolatorLeft, timeExtrapolatorRight,
        strikeInterpolator, strikeExtrapolatorLeft, strikeExtrapolatorRight);
    InterpolatedNodalSurface surface = InterpolatedNodalSurface.ofUnsorted(metadata, expiries, strikes, parameters, interp);
    return BlackFxOptionSurfaceVolatilities.of(name, currencyPair, valuationDateTime, surface);
  }