public static String exportReservedTerms(Registry registry) {
    StringBuilder termsBuilder =
        new StringBuilder(RegistryEnvironment.get().config().getReservedTermsExportDisclaimer());
    Set<String> reservedTerms = new TreeSet<>();
    for (Key<ReservedList> key : registry.getReservedLists()) {
      ReservedList reservedList = ReservedList.load(key).get();
      if (reservedList.getShouldPublish()) {
        for (ReservedListEntry entry : reservedList.getReservedListEntries().values()) {
          if (entry.getValue() != UNRESERVED) {
            reservedTerms.add(entry.getLabel());
          }
        }
      }
    }
    Joiner.on("\n").appendTo(termsBuilder, reservedTerms);
    return termsBuilder.append("\n").toString();
  }