public static ReservedList create(
      String name, Boolean shouldPublish, Map<String, ReservedEntry> labelsToReservations) {
    ImmutableList<String> invalidLabels =
        labelsToReservations.entrySet().parallelStream()
            .flatMap(
                entry -> {
                  String label = entry.getKey();
                  if (label.equals(canonicalizeDomainName(label))) {
                    return Stream.empty();
                  } else {
                    return Stream.of(label);
                  }
                })
            .collect(toImmutableList());
    checkArgument(
        invalidLabels.isEmpty(),
        "Label(s) [%s] must be in puny-coded, lower-case form",
        Joiner.on(",").join(sortedCopyOf(invalidLabels)));
    return new ReservedList(name, shouldPublish, labelsToReservations);
  }