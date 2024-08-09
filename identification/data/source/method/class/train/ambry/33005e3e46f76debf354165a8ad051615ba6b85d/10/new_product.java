public void getEntriesSince(StoreKey key, long maxTotalSizeOfEntriesInBytes, List<MessageInfo> entries,
      AtomicLong currentTotalSizeOfEntriesInBytes)
      throws IOException {
    if (mapped.get()) {
      int index = 0;
      if (key != null) {
        index = findIndex(key, mmap.duplicate());
      }
      if (index != -1) {
        ByteBuffer readBuf = mmap.duplicate();
        int totalEntries = numberOfEntries(readBuf);
        while (currentTotalSizeOfEntriesInBytes.get() < maxTotalSizeOfEntriesInBytes && index < totalEntries) {
          StoreKey newKey = getKeyAt(readBuf, index);
          byte[] buf = new byte[valueSize];
          readBuf.get(buf);
          if (key == null || newKey.compareTo(key) != 0) {
            IndexValue newValue = new IndexValue(ByteBuffer.wrap(buf));
            MessageInfo info =
                new MessageInfo(newKey, newValue.getSize(), newValue.isFlagSet(IndexValue.Flags.Delete_Index),
                    newValue.getTimeToLiveInMs());
            entries.add(info);
            currentTotalSizeOfEntriesInBytes.addAndGet(newValue.getSize());
          }
          index++;
        }
      } else {
        logger.error("IndexSegment : " + indexFile.getAbsolutePath() +
            " index not found for key " + key);
      }
    } else {
      ConcurrentNavigableMap<StoreKey, IndexValue> tempMap = index;
      if (key != null) {
        tempMap = index.tailMap(key, true);
      }
      for (Map.Entry<StoreKey, IndexValue> entry : tempMap.entrySet()) {
        if (key == null || entry.getKey().compareTo(key) != 0) {
          MessageInfo info = new MessageInfo(entry.getKey(), entry.getValue().getSize(),
              entry.getValue().isFlagSet(IndexValue.Flags.Delete_Index), entry.getValue().getTimeToLiveInMs());
          entries.add(info);
          currentTotalSizeOfEntriesInBytes.addAndGet(entry.getValue().getSize());
          if (currentTotalSizeOfEntriesInBytes.get() >= maxTotalSizeOfEntriesInBytes) {
            break;
          }
        }
      }
    }
  }