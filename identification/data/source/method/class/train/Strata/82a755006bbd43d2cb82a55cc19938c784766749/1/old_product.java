@DerivedProperty
  public AdjustableDate getEndDate() {
    return legs.stream()
        .map(SwapLeg::getEndDate)
        .max(Comparator.comparing(adjDate -> adjDate.getUnadjusted()))
        .get();  // always at least one leg, so get() is safe
  }