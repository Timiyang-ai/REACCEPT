public LocalDate getStartDate() {
    return legs.stream()
        .map(SwapLeg::getStartDate)
        .min(Comparator.naturalOrder())
        .get();  // always at least one leg, so get() is safe
  }