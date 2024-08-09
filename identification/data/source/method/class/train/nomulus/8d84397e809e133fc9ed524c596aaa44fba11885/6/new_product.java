public static PremiumList savePremiumListAndEntries(
      final PremiumList premiumList,
      ImmutableMap<String, PremiumListEntry> premiumListEntries) {
    final Optional<PremiumList> oldPremiumList = PremiumList.getUncached(premiumList.getName());

    // Create the new revision (with its Bloom filter) and parent the entries on it.
    final PremiumListRevision newRevision =
        PremiumListRevision.create(premiumList, premiumListEntries.keySet());
    final Key<PremiumListRevision> newRevisionKey = Key.create(newRevision);
    ImmutableSet<PremiumListEntry> parentedEntries =
        parentPremiumListEntriesOnRevision(premiumListEntries.values(), newRevisionKey);

    // Save the new child entities in a series of transactions.
    for (final List<PremiumListEntry> batch : partition(parentedEntries, TRANSACTION_BATCH_SIZE)) {
      ofy().transactNew(() -> ofy().save().entities(batch));
    }

    // Save the new PremiumList and revision itself.
    PremiumList updated = ofy().transactNew(() -> {
      DateTime now = ofy().getTransactionTime();
      // Assert that the premium list hasn't been changed since we started this process.
      PremiumList existing = ofy().load()
          .type(PremiumList.class)
          .parent(getCrossTldKey())
          .id(premiumList.getName())
          .now();
      checkState(
          Objects.equals(existing, oldPremiumList.orElse(null)),
          "PremiumList was concurrently edited");
      PremiumList newList = premiumList.asBuilder()
          .setLastUpdateTime(now)
          .setCreationTime(oldPremiumList.isPresent() ? oldPremiumList.get().creationTime : now)
          .setRevision(newRevisionKey)
          .build();
      ofy().save().entities(newList, newRevision);
      return newList;
    });

    // Invalidate the cache on this premium list so the change will take effect instantly. This only
    // clears the cache on the same instance that the update was run on, which will typically be the
    // only tools instance.
    PremiumList.cachePremiumLists.invalidate(premiumList.getName());

    // TODO(b/79888775): Enqueue the oldPremiumList for deletion after at least
    // RegistryConfig.getDomainLabelListCacheDuration() has elapsed.
    return updated;
  }