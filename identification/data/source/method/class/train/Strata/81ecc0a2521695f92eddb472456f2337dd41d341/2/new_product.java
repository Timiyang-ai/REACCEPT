public static FpmlPartySelector matchingRegex(Pattern partyIdRegex) {
    return allParties -> allParties.entries().stream()
        .filter(e -> partyIdRegex.matcher(e.getValue()).matches())
        .map(e -> e.getKey())
        .distinct()
        .collect(toImmutableList());
  }