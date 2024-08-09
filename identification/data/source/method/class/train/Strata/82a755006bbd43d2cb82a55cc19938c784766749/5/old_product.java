@DerivedProperty
  public LocalDate getEndDate() {
    return legs.stream()
        .map(SwapLeg::getEndDate)
        .max(Comparator.naturalOrder())
        .get();  // always at least one leg, so get() is safe
  }