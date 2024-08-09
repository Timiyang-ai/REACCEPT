@DerivedProperty
  public LocalDate getEndDate() {
    return legs.stream()
        .map(ResolvedSwapLeg::getEndDate)
        .max(Comparator.naturalOrder())
        .get();  // always at least one leg, so get() is safe
  }