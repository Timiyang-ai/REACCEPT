public static PremiumList savePremiumListAndEntries(
      final PremiumList premiumList,
      ImmutableMap<String, PremiumListEntry> premiumListEntries) {
    final Optional<PremiumList> oldPremiumList = PremiumList.get(premiumList.getName());

    // Create the new revision (with its bloom filter) and parent the entries on it.
    final PremiumListRevision newRevision =
        PremiumListRevision.create(premiumList, premiumListEntries.keySet());
    final Key<PremiumListRevision> newRevisionKey = Key.create(newRevision);
    ImmutableSet<PremiumListEntry> parentedEntries =
        parentPremiumListEntriesOnRevision(premiumListEntries.values(), newRevisionKey);

    // Save the new child entities in a series of transactions.
    for (final List<PremiumListEntry> batch :
        partition(parentedEntries, TRANSACTION_BATCH_SIZE)) {
      ofy().transactNew(new VoidWork() {
        @Override
        public void vrun() {
          ofy().save().entities(batch);
        }});
    }

    // Save the new PremiumList and revision itself.
    PremiumList updated = ofy().transactNew(new Work<PremiumList>() {
        @Override
        public PremiumList run() {
          DateTime now = ofy().getTransactionTime();
          // Assert that the premium list hasn't been changed since we started this process.
          PremiumList existing = ofy().load()
              .type(PremiumList.class)
              .parent(getCrossTldKey())
              .id(premiumList.getName())
              .now();
          checkState(
              Objects.equals(existing, oldPremiumList.orNull()),
              "PremiumList was concurrently edited");
          PremiumList newList = premiumList.asBuilder()
              .setLastUpdateTime(now)
              .setCreationTime(
                  oldPremiumList.isPresent() ? oldPremiumList.get().creationTime : now)
              .setRevision(newRevisionKey)
              .build();
          ofy().save().entities(newList, newRevision);
          return newList;
        }});
    // Update the cache.
    cachePremiumLists.put(premiumList.getName(), updated);
    // Delete the entities under the old PremiumList.
    if (oldPremiumList.isPresent()) {
      deleteRevisionAndEntriesOfPremiumList(oldPremiumList.get());
    }
    return updated;
  }