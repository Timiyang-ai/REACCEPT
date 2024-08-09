public static FpmlPartySelector matching(String partyId) {
    return allParties -> allParties.entries().stream()
        .filter(e -> e.getValue().equals(partyId))
        .map(e -> e.getKey())
        .distinct()
        .collect(toImmutableList());
  }