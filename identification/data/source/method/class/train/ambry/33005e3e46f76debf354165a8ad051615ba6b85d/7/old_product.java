boolean getEntriesSince(StoreKey key, FindEntriesCondition findEntriesCondition, List<MessageInfo> entries,
      AtomicLong currentTotalSizeOfEntriesInBytes) throws IOException {
    List<IndexEntry> indexEntries = new ArrayList<>();
    boolean areNewEntriesAdded =
        getIndexEntriesSince(key, findEntriesCondition, indexEntries, currentTotalSizeOfEntriesInBytes, true);
    for (IndexEntry indexEntry : indexEntries) {
      IndexValue value = indexEntry.getValue();
      MessageInfo info =
          new MessageInfo(indexEntry.getKey(), value.getSize(), value.isFlagSet(IndexValue.Flags.Delete_Index),
              value.isFlagSet(IndexValue.Flags.Ttl_Update_Index), value.getExpiresAtMs(), value.getAccountId(),
              value.getContainerId(), value.getOperationTimeInMs());
      entries.add(info);
    }
    return areNewEntriesAdded;
  }