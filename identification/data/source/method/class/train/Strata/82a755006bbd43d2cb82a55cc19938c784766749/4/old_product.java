@DerivedProperty
  public AdjustableDate getStartDate() {
    return legs.stream()
        .map(SwapLeg::getStartDate)
        .min(Comparator.comparing(adjDate -> adjDate.getUnadjusted()))
        .get();  // always at least one leg, so get() is safe
  }