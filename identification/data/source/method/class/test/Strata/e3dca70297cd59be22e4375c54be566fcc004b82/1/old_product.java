public boolean isCrossCurrency() {
    return legs.stream()
        .map(SwapLeg::getCurrency)
        .distinct()
        .count() > 1;
  }