public String exportReservedTerms(Registry registry) {
    StringBuilder termsBuilder = new StringBuilder(reservedTermsExportDisclaimer).append("\n");
    Set<String> reservedTerms = new TreeSet<>();
    for (Key<ReservedList> key : registry.getReservedLists()) {
      ReservedList reservedList = ReservedList.load(key).get();
      if (reservedList.getShouldPublish()) {
        for (ReservedListEntry entry : reservedList.getReservedListEntries().values()) {
          reservedTerms.add(entry.getLabel());
        }
      }
    }
    Joiner.on("\n").appendTo(termsBuilder, reservedTerms);
    return termsBuilder.append("\n").toString();
  }