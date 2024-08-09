@Override
  public ResolvedSwap resolve(ReferenceData refData) {
    return ResolvedSwap.builder()
        .legs(legs.stream()
            .map(leg -> leg.resolve(refData))
            .collect(toImmutableList()))
        .build();
  }