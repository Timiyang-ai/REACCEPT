boolean getEntriesSince(StoreKey key, FindEntriesCondition findEntriesCondition, List<MessageInfo> entries,
      AtomicLong currentTotalSizeOfEntriesInBytes) throws IOException {
    List<IndexEntry> indexEntries = new ArrayList<>();
    boolean isNewEntriesAdded =
        getIndexEntriesSince(key, findEntriesCondition, indexEntries, currentTotalSizeOfEntriesInBytes);
    for (IndexEntry indexEntry : indexEntries) {
      IndexValue value = indexEntry.getValue();
      MessageInfo info =
          new MessageInfo(indexEntry.getKey(), value.getSize(), value.isFlagSet(IndexValue.Flags.Delete_Index),
              value.getExpiresAtMs());
      entries.add(info);
    }
    return isNewEntriesAdded;
  }