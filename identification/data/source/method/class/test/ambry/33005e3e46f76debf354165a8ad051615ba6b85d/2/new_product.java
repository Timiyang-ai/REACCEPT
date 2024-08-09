boolean getEntriesSince(StoreKey key, FindEntriesCondition findEntriesCondition, List<MessageInfo> entries,
      AtomicLong currentTotalSizeOfEntriesInBytes) throws IOException {
    if (!findEntriesCondition.proceed(currentTotalSizeOfEntriesInBytes.get(), getLastModifiedTimeSecs())) {
      return false;
    }
    int entriesSizeAtStart = entries.size();
    if (mapped.get()) {
      int index = 0;
      if (key != null) {
        index = findIndex(key, mmap.duplicate());
      }
      if (index != -1) {
        ByteBuffer readBuf = mmap.duplicate();
        int totalEntries = numberOfEntries(readBuf);
        while (findEntriesCondition.proceed(currentTotalSizeOfEntriesInBytes.get(), getLastModifiedTimeSecs())
            && index < totalEntries) {
          StoreKey newKey = getKeyAt(readBuf, index);
          byte[] buf = new byte[valueSize];
          readBuf.get(buf);
          // we include the key in the final list if it is not the initial key or if the initial key was null
          if (key == null || newKey.compareTo(key) != 0) {
            IndexValue newValue = new IndexValue(startOffset.getName(), ByteBuffer.wrap(buf), version);
            MessageInfo info =
                new MessageInfo(newKey, newValue.getSize(), newValue.isFlagSet(IndexValue.Flags.Delete_Index),
                    newValue.getExpiresAtMs());
            entries.add(info);
            currentTotalSizeOfEntriesInBytes.addAndGet(newValue.getSize());
          }
          index++;
        }
      } else {
        logger.error("IndexSegment : " + indexFile.getAbsolutePath() + " index not found for key " + key);
      }
    } else if (key == null || index.containsKey(key)) {
      ConcurrentNavigableMap<StoreKey, IndexValue> tempMap = index;
      if (key != null) {
        tempMap = index.tailMap(key, true);
      }
      for (Map.Entry<StoreKey, IndexValue> entry : tempMap.entrySet()) {
        if (key == null || entry.getKey().compareTo(key) != 0) {
          MessageInfo info = new MessageInfo(entry.getKey(), entry.getValue().getSize(),
              entry.getValue().isFlagSet(IndexValue.Flags.Delete_Index), entry.getValue().getExpiresAtMs());
          entries.add(info);
          currentTotalSizeOfEntriesInBytes.addAndGet(entry.getValue().getSize());
          if (!findEntriesCondition.proceed(currentTotalSizeOfEntriesInBytes.get(), getLastModifiedTimeSecs())) {
            break;
          }
        }
      }
    } else {
      logger.error("IndexSegment : " + indexFile.getAbsolutePath() + " key not found: " + key);
    }
    return entries.size() > entriesSizeAtStart;
  }