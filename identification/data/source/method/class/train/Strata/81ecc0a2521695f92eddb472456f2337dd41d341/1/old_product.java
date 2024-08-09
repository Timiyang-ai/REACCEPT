public static FpmlPartySelector matching(String partyId) {
    return allParties -> allParties.entries().stream()
        .filter(e -> e.getValue().equals(partyId))
        .findFirst()
        .map(e -> e.getKey());
  }