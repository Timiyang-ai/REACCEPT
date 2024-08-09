  private void getEntriesSinceTest(NavigableMap<MockId, NavigableSet<IndexValue>> referenceIndex, IndexSegment segment,
      MockId idToCheck, long sizeLeftInSegment) throws StoreException {
    long maxSize = 0;
    MockId idHigherThanIdToCheck = idToCheck == null ? referenceIndex.firstKey() : referenceIndex.higherKey(idToCheck);
    MockId highestIdIncluded = null;
    while (maxSize <= sizeLeftInSegment) {
      MockId nextHighestIdIncluded =
          highestIdIncluded == null ? idHigherThanIdToCheck : referenceIndex.higherKey(highestIdIncluded);
      long existingSize = 0;
      while (existingSize < maxSize) {
        doGetEntriesSinceTest(referenceIndex, segment, idToCheck, maxSize, existingSize, highestIdIncluded);
        for (IndexValue value : referenceIndex.get(highestIdIncluded)) {
          existingSize += value.getSize();
        }
        highestIdIncluded = referenceIndex.lowerKey(highestIdIncluded);
      }
      doGetEntriesSinceTest(referenceIndex, segment, idToCheck, maxSize, maxSize, null);
      if (nextHighestIdIncluded != null) {
        highestIdIncluded = nextHighestIdIncluded;
        for (IndexValue value : referenceIndex.get(highestIdIncluded)) {
          maxSize += value.getSize();
        }
      } else {
        break;
      }
    }
    highestIdIncluded = referenceIndex.lastKey().equals(idToCheck) ? null : referenceIndex.lastKey();
    // check the case where maxSize is more than the number of entries in the segment
    doGetEntriesSinceTest(referenceIndex, segment, idToCheck, maxSize + 1, 0, highestIdIncluded);
  }