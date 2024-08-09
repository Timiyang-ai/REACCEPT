@DerivedProperty
  public LocalDate getStartDate() {
    return legs.stream()
        .map(ResolvedSwapLeg::getStartDate)
        .min(Comparator.naturalOrder())
        .get();  // always at least one leg, so get() is safe
  }